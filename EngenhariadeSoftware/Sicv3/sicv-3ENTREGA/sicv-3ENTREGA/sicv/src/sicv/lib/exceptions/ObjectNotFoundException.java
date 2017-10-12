package sicv.lib.exceptions;

public class ObjectNotFoundException extends Exception {

	
	private static final long serialVersionUID = -8490661543462780697L;

	public ObjectNotFoundException(String error) {
		super("ExceptioData: " + error);
	}
}
