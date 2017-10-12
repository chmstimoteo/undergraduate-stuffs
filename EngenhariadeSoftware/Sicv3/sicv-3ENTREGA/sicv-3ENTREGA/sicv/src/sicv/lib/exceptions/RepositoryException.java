package sicv.lib.exceptions;

public class RepositoryException extends Exception {

	
	private static final long serialVersionUID = -7174952949830286948L;

	public RepositoryException(String error) {
		super("ExcecptionData: " + error);
	}
}
