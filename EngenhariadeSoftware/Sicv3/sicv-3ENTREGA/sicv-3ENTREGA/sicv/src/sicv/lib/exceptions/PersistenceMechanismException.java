package sicv.lib.exceptions;

public class PersistenceMechanismException extends Exception {

	private static final long serialVersionUID = -5659600455520710009L;

	public PersistenceMechanismException(String error) {
		super("ExceptionData: " + error);
	}
}
