package sicv.lib.exceptions;

public class ObjectNotValidException extends Exception {

	
	private static final long serialVersionUID = -6586566609542205774L;

	public ObjectNotValidException(String error) {
		super("ExceptionData: " + error);
	}
}
