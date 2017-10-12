package sicv.model.vehicle;

import sicv.lib.Position;
import sicv.model.occurrence.Priority;

public class Vehicle {

	
	private boolean disponible;
	private Priority priority;
	private int  id;
	
	
	
	
	public Vehicle(boolean disponible, Priority priority, int id) {
		super();
		this.disponible = disponible;
		this.priority = priority;
		this.id = id;
	}

	public int getId() {
		
		return this.id;
	}

	public Position getPosition() {
		
		return null;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
 
}
 
