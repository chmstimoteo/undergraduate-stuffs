package sicv.model.occurrence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import sicv.util.Position;
import sicv.util.Status;

@Entity
public class Occurrence implements Serializable{

	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS",nullable = false,updatable = false)
	private Status status;
	
	// @OneToOne
     private Position position;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "PRIORITY",nullable = false,updatable = false)
	private Priority priority;

	@Id
	private int id;

	public Occurrence() {
		
	}
	
		
	public Occurrence(Priority priority ,Status status,Position position) {
		super();
		this.status = status;
		this.position = position;
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



}

