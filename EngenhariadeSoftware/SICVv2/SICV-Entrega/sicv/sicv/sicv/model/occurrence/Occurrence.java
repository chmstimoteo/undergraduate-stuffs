package sicv.model.occurrence;

import sicv.util.Position;
import sicv.util.Status;

public class Occurrence {

	private Status status;

	private Position position;
	
	private Priority priority;

	private int id;

		
	public Occurrence(Priority priority ,Status status, Position position, int id) {
		super();
		this.status = status;
		this.position = position;
		this.id = id;
		this.priority = priority;
	}


	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
		this.position = position;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	public Position getDisponibility() {
		// TODO Auto-generated method stub
		return null;
	}


}

