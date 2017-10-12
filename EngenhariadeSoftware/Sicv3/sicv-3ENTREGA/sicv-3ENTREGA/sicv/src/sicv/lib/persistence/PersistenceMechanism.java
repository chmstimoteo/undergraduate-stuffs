package sicv.lib.persistence;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import sicv.Constants;
import sicv.lib.exceptions.ExceptionMessages;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.TransactionException;


public class PersistenceMechanism implements IPersistenceMechanism {

	private static PersistenceMechanism singleton;

	private static int numberConnections = 2;

	private Connection createdConnections[];

	private Connection freeConnections[];

	private HashMap<String,Connection> usedConnections;

	private String url;

	private String user;

	private String password;

	private boolean unavailable;

	private PersistenceMechanism(String url, String user, String password, String driverClass)
			throws PersistenceMechanismException {
		usedConnections = new HashMap<String,Connection>();
		this.url = url;
		this.user = user;
		this.password = password;
		unavailable = false;
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new PersistenceMechanismException("EXC_CLASSE_NAO_ENCONTRADA");
		}
	}

	public synchronized PreparedStatement getPreparedStatement(String sql)
			throws PersistenceMechanismException {
		try {
			return getCommunicationChannel(false).prepareStatement(sql);
		} catch (SQLException ex) {
			throw new PersistenceMechanismException("SQLException: " + ex.getMessage());
		}
	}

	public synchronized void rollbackTransaction() throws TransactionException {
		try {
			Connection connection = (Connection) getCommunicationChannel(true);
			connection.rollback();
			connection.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {
			throw new TransactionException("EXC_CANCELAR_TRANSACAO");
		} finally {
			try {
				notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void connect() throws PersistenceMechanismException {

		if (createdConnections == null) {
			try {
				freeConnections = new Connection[PersistenceMechanism.numberConnections];
				createdConnections = new Connection[PersistenceMechanism.numberConnections];
				for (int i = 0; i < PersistenceMechanism.numberConnections; i++) {
					createdConnections[i] = DriverManager.getConnection(url, user, password);
					freeConnections[i] = createdConnections[i];
				}

			} catch (Exception e) {
				throw new PersistenceMechanismException(ExceptionMessages.EXC_CONNECT);
			}
		}
	}

	public synchronized void commitTransaction() throws TransactionException {
		try {
			Connection connection = (Connection) getCommunicationChannel(true);
			connection.commit();
			connection.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionException("EXC_CONFIRMAR_TRANSACAO");
		} finally {
			notifyAll();
		}
	}

	public synchronized void disconnect() throws PersistenceMechanismException {
		try {
			if (createdConnections != null) {
				int closed = 0;
				for (int i = 0; i < PersistenceMechanism.numberConnections; i++) {
					System.out.println("Conexao " + i + " " + freeConnections[i]);
				}

				System.out.println("Conexoes alocadas " + usedConnections.size());
				for (int i = 0; i < PersistenceMechanism.numberConnections; i++) {
					if (createdConnections[i] != null) {
						createdConnections[i].close();
						closed++;
					}
				}

				createdConnections = null;
				System.out.println("Foram fechadas " + closed + " conexoes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceMechanismException(ExceptionMessages.EXC_FAIL_DISCONNECT);
		}
	}

	/**
	 * Retorna um java.sql.Statement
	 */
	public synchronized Object getCommunicationChannel() throws PersistenceMechanismException {
		try {
			return getCommunicationChannel(false).createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FAIL_GET_COMUNICATION_CHANNEL);
		}
	}

	/**
	 * Retorna um java.sql.Connection
	 */
	private synchronized Connection getCommunicationChannel(boolean byTransaction)
			throws PersistenceMechanismException {
		Connection response = null;
		try {
			Thread currentThread = Thread.currentThread();
			int threadId = currentThread.hashCode();
			if (usedConnections.containsKey("" + threadId)) {
				response = (Connection) usedConnections.get("" + threadId);
			} else {
				boolean found = false;
				do {
					if (found) {
						break;
					}
					for (int i = 0; !found && i < PersistenceMechanism.numberConnections; i++) {
						if (freeConnections[i] == null) {
							continue;
						}
						found = true;
						response = freeConnections[i];
						freeConnections[i] = null;
						usedConnections.put("" + threadId, response);
						if (byTransaction) {
							usedConnections.put("T" + threadId, null);
						}
					}

					if (!found) {
						unavailable = true;
						wait();
					}
				} while (true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FAIL_GET_COMUNICATION_CHANNEL);
		}
		return response;
	}

	public static synchronized PersistenceMechanism getInstance()
			throws PersistenceMechanismException {
		if (singleton == null)
			singleton = new PersistenceMechanism(Constants.DB_URL, Constants.DB_LOGIN,
					Constants.DB_PASS, Constants.DB_DRIVER);
		return singleton;
	}

	public synchronized void beginTransaction() throws TransactionException {
		try {
			while (unavailable) {
				wait();
			}
			Connection connection = (Connection) getCommunicationChannel(true);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionException("EXC_INICIAR_TRANSACAO");
		}
	}

	public synchronized void releaseCommunicationChannel() throws PersistenceMechanismException {
		releaseCommunicationChannel(false);
	}

	private synchronized void releaseCommunicationChannel(boolean byTransaction)
			throws PersistenceMechanismException {
		try {
			Thread currentThread = Thread.currentThread();
			int threadId = currentThread.hashCode();
			if (byTransaction || !byTransaction && !usedConnections.containsKey("T" + threadId)) {
				Object channel = usedConnections.get("" + threadId);
				boolean found = false;
				for (int i = 0; !found && i < PersistenceMechanism.numberConnections; i++) {
					if (freeConnections[i] != null) {
						continue;
					}
					found = true;
					usedConnections.remove("" + threadId);
					if (usedConnections.containsKey("T" + threadId)) {
						usedConnections.remove("T" + threadId);
					}
					freeConnections[i] = (java.sql.Connection) channel;
				}

				unavailable = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FAIL_GET_COMUNICATION_CHANNEL);
		} finally {
			notifyAll();
		}
	}
}
