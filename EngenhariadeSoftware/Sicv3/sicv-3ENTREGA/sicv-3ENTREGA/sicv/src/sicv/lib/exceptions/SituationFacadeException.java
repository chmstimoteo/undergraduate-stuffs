package sicv.lib.exceptions;

public class SituationFacadeException extends Exception {

	
	private static final long serialVersionUID = -7804256964445936509L;

	public SituationFacadeException(String error) {
		super("Exception: " + error);
	}
}