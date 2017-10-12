package sicv.util;

public enum Status {

	FREE(0),
	IN_ATTENDANT(1),
	IN_INITIAL_ATTENDANT(2),
	WAITING(3),
	BUSY(4);
	
	private int status;
	
	private Status(int status){
		this.status = status;
	}
}
