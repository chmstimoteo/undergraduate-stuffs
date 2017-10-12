package sicv.lib.exceptions;

public class InvalidSessionException extends Exception {

	
	private static final long serialVersionUID = -3998256523724530302L;

	public InvalidSessionException(String error) {
		super(error);
	}

	public InvalidSessionException() {
		this("Invalid session, go to the first page!");
	}
}