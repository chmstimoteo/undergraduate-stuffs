package sicv.model.vehicle;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import sicv.model.employee.Employee;
import sicv.model.employee.PoliceOffice;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

@Entity
public class Vehicle implements Serializable{

	
	private boolean disponible;
	@Id
	private int  id;
	
	private int  idOccurrence;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS",nullable = false,updatable = false)
	private Status status;
	
	private String plate;
	
	private Position position;
	
	@OneToMany
	private List<PoliceOffice> police; 
	
	public Status getStatus(){
		return status;
	}
	
	public Vehicle() {
		
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vehicle(int id , boolean disponible,Status s,int idO) {
		super();
		this.disponible = disponible;
		this.id = id;
		this.status = s;
		this.idOccurrence = idO;
		this.plate = "sdss";
		this.police = new Vector<PoliceOffice>();
	}

	
	public Vehicle(boolean disponible, int id, Status status, Position position,String plate) {
		super();
		this.disponible = disponible;
		this.id = id;
		this.status = status;
		this.position = position;
		this.plate = plate;
	}

	public int getId() {
		
		return this.id;
	}

	public Position getPosition() {
		return this.position;
	}

	
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void releaseVehicle()
	{
		this.disponible = true;
	}
	
	
	
	public void occupyVehicle()
	{
		this.disponible = false;
	}
	
	
	public boolean isDisponible() {
		
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

		
	public void setId(int id) {
		this.id = id;
	}

	public Occurrence getOccurrence() {
		return null;
	}

	public void setOccurrence(Occurrence occurrence) {
		//this.occurrence = occurrence;
	}
 
	public void addEmployee(PoliceOffice p){
		this.police.add(p);
	}
	
	public void removeEmployee(Employee employee) {
		this.police.remove(employee);
	}
	
	public String getDescription() {
		return "Polices: ";
	}
	
	public double getLat() {
		return this.position.getLat();
	}
	
	public double getLng() {
		return this.position.getLng();
	}


	public Enum<Priority> getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
 
