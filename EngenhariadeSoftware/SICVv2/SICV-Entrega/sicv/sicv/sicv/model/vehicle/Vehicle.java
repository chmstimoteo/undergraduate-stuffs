package sicv.model.vehicle;

import java.util.Vector;

import sicv.model.employee.Employee;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class Vehicle {

	
	private boolean disponible;
	private int  id;
	private Occurrence occurrence;
	private Status status;
	private String plate;
	private Position position;
	private Vector<Employee> employees; 
	
	public Status getStatus(){
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vehicle(int id , boolean disponible) {
		super();
		this.disponible = disponible;
		this.id = id;
	}

	public int getId() {
		
		return this.id;
	}

	public Position getPosition() {
		
		return null;
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
		return occurrence;
	}

	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}
 
	public void addEmployee(Employee employee){
		this.employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}
	
	public String getDescription() {
		return "Polices: " + this.employees.size();
	}
	
	public float getLat() {
		return this.position.getLat();
	}
	
	public float getLng() {
		return this.position.getLng();
	}

	public String getDisponibility() {
		// TODO Auto-generated method stub
		return null;
	}

	public Enum<Priority> getPriority() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
 
