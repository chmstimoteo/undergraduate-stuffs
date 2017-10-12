package sicv.lib.exceptions;

public class ObjectAlreadyInsertedException extends Exception {

	
	private static final long serialVersionUID = 2127604770641246458L;

	public ObjectAlreadyInsertedException(String error) {
		super("ExceptionData: " + error);
	}
}
