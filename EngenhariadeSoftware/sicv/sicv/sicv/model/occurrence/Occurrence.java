package sicv.model.occurrence;

import sicv.model.localization.Localization;

public class Occurrence {
 
	private Priority priority;
	 
	private int status;

	private Localization localization;
	
	private int id;
	
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	




	public Occurrence(Priority priority, int status, Localization localization,
			int id) {
		super();
		this.priority = priority;
		this.status = status;
		this.localization = localization;
		this.id = id;
	}


	


	public Priority getPriority() {
		return priority;
	}




	public void setPriority(Priority priority) {
		this.priority = priority;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public Localization getLocalization() {
		return localization;
	}




	public void setLocalization(Localization localization) {
		this.localization = localization;
	}





	 
}
 
