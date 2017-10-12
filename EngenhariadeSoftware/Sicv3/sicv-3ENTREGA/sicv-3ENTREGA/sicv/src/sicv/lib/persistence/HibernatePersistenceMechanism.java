package sicv.lib.persistence;



import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import sicv.Constants;
import sicv.lib.exceptions.ExceptionMessages;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.TransactionException;


public class HibernatePersistenceMechanism implements IPersistenceMechanism{

	
	private static HibernatePersistenceMechanism singleton;

	private static int numberConnections = 2;

	private EntityManager createdConnections[];

	private EntityManager freeConnections[];

	private HashMap<String,EntityManager> usedConnections;

	
	private boolean unavailable;

	
	EntityManagerFactory emf;
	
	private HibernatePersistenceMechanism(String database)
			throws PersistenceMechanismException {
		
		usedConnections = new HashMap<String,EntityManager>();
		
		unavailable = false;
		emf = javax.persistence.Persistence.createEntityManagerFactory(database); 
		
		
	}

	public synchronized PreparedStatement getPreparedStatement(String sql)
			throws PersistenceMechanismException {
		
		throw new PersistenceMechanismException(
				ExceptionMessages.EXC_FAIL_GET_COMUNICATION_CHANNEL);
	    
	}

	public synchronized void rollbackTransaction() throws TransactionException {
		try {
			EntityManager connection = (EntityManager) getCommunicationChannel(true);
			
			connection.getTransaction().rollback();
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
				freeConnections = new EntityManager[HibernatePersistenceMechanism.numberConnections];
				createdConnections = new EntityManager[HibernatePersistenceMechanism.numberConnections];
				for (int i = 0; i < HibernatePersistenceMechanism.numberConnections; i++) {
					createdConnections[i] = emf.createEntityManager();
					freeConnections[i] = createdConnections[i];
				}

			} catch (Exception e) {
				throw new PersistenceMechanismException(ExceptionMessages.EXC_CONNECT);
			}
		}
	}

	public synchronized void commitTransaction() throws TransactionException {
		try {
			EntityManager connection = (EntityManager) getCommunicationChannel(true);
			connection.getTransaction().commit();
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
				for (int i = 0; i < HibernatePersistenceMechanism.numberConnections; i++) {
					System.out.println("Conexao " + i + " " + freeConnections[i]);
				}

				System.out.println("Conexoes alocadas " + usedConnections.size());
				for (int i = 0; i < HibernatePersistenceMechanism.numberConnections; i++) {
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
			return getCommunicationChannel(false);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FAIL_GET_COMUNICATION_CHANNEL);
		}
	}

	/**
	 * Retorna um java.sql.Connection
	 */
	private synchronized EntityManager getCommunicationChannel(boolean byTransaction)
			throws PersistenceMechanismException {
		EntityManager response = null;
		try {
			Thread currentThread = Thread.currentThread();
			int threadId = currentThread.hashCode();
			if (usedConnections.containsKey("" + threadId)) {
				response = (EntityManager) usedConnections.get("" + threadId);
			} else {
				boolean found = false;
				do {
					if (found) {
						break;
					}
					for (int i = 0; !found && i < HibernatePersistenceMechanism.numberConnections; i++) {
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

	public static synchronized HibernatePersistenceMechanism getInstance()
			throws PersistenceMechanismException {
		if (singleton == null)
			singleton = new HibernatePersistenceMechanism(Constants.PERSISTENCE_UNIT_NAME);
		return singleton;
	}

	public synchronized void beginTransaction() throws TransactionException {
		try {
			while (unavailable) {
				wait();
			}
			EntityManager connection = (EntityManager) getCommunicationChannel(true);
			//connection.setAutoCommit(false);
		    connection.getTransaction().begin();
		 
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
				for (int i = 0; !found && i < HibernatePersistenceMechanism.numberConnections; i++) {
					if (freeConnections[i] != null) {
						continue;
					}
					found = true;
					usedConnections.remove("" + threadId);
					if (usedConnections.containsKey("T" + threadId)) {
						usedConnections.remove("T" + threadId);
					}
					freeConnections[i] = (EntityManager) channel;
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
