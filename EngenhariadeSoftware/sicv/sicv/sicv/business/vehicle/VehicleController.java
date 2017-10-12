package sicv.business.vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.lib.Position;
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;

public class VehicleController {
 
	private VehicleRecord vehicleRecord;
	 
	public List<Vehicle> getVehiclesByPriority(Priority	 priority) {
		
	   
	    List<Vehicle> list = new  ArrayList<Vehicle>();
	    for (Iterator<Vehicle> iterator = this.vehicleRecord.iterator(); iterator.hasNext();) {
			Vehicle vehicle = (Vehicle) iterator.next();
			
			if (vehicle.getPriority().equals(priority)) {
				list.add(vehicle);
			}
		
		}
	    
	    return list;
	
	}
	 
	public List<Vehicle> getVehiclesByDisponibility() {
		
		List<Vehicle> list = new ArrayList<Vehicle>();
		
		 for (Iterator<Vehicle> iterator = this.vehicleRecord.iterator(); iterator.hasNext();) {
				Vehicle vehicle = (Vehicle) iterator.next();
				
				if(vehicle.isDisponible())
					list.add(vehicle);
					
			}
		 
		return list;
	}
	 
	public Position getPosition(Vehicle vehicle) {
	
	     return vehicle.getPosition();
	     
	}
	
	
	public List<Position> getVehiclePositionByDisponibility() {
	
		List<Vehicle> vehicles = this.getVehiclesByDisponibility();
		List<Position> list = new ArrayList<Position>();
		
		for (Iterator<Vehicle> iterator = vehicles.iterator(); iterator.hasNext();) {
			
			list.add(iterator.next().getPosition());
		}
	 
		return list;
		
		
	}
	public List<Position> getVehiclePositionByPriority(Priority priority)
	{
		List<Vehicle> vehicles = this.getVehiclesByPriority(priority);
		List<Position> list = new ArrayList<Position>();
		
		for (Iterator<Vehicle> iterator = vehicles.iterator(); iterator.hasNext();) {
			
			list.add(iterator.next().getPosition());
		}
	 
		return list;
		
	}
	
	
	
	 
}
 
