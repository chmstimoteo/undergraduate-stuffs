package sicv.lib.exceptions;

public class InvalidDateException extends Exception {

	
	private static final long serialVersionUID = 6117265917835495475L;

	public InvalidDateException(int segundos, int minutos, int hora) {
		super("ExceptionHour: ");
	}

	public InvalidDateException(String erro) {
		super("ExceptionHour:" + erro);
	}
}