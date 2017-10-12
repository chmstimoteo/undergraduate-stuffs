package sicv.business.vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IVehicleRepository;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;

public class VehicleController {
 
	private VehicleRecord vehicleRecord;
	
	
	
	public VehicleController(IVehicleRepository vehicleRepository) {
		super();
		this.vehicleRecord = new VehicleRecord(vehicleRepository); 
	}


	public List<Vehicle> getVehiclesByPriority(Priority	 priority) throws RepositoryException {
		
	   
	    List<Vehicle> list = new  ArrayList<Vehicle>();
	    for (Iterator<Vehicle> iterator = this.vehicleRecord.iterator(); iterator.hasNext();) {
			Vehicle vehicle =  iterator.next();
			
			
			if (vehicle.isDisponible()) {
				list.add(vehicle);
				
			}else{
				if (vehicle.getOccurrence().getPriority().compareTo(priority) < 0) {
					list.add(vehicle);
				}
			}
		
		}
	    
	    return list;
	
	}
	 
	
	public boolean hasVehiclesFreeByPriority(Priority	 priority) throws RepositoryException {
		
	    boolean response = false;
	
	    for (Iterator<Vehicle> iterator = this.vehicleRecord.iterator(); iterator.hasNext();) {
			Vehicle vehicle =  iterator.next();
			
			
			if (vehicle.isDisponible()) {
				response = true;
				break;
				
			}else{
				if (vehicle.getPriority().compareTo(priority) < 0) {
					response = true;
				}
			}
		
		}
	    
	    return response;
	
	}
	public List<Vehicle> getVehiclesByDisponibility() throws RepositoryException {
		
		List<Vehicle> list = new ArrayList<Vehicle>();
		
		 for (Iterator<Vehicle> iterator = this.vehicleRecord.iterator(); iterator.hasNext();) {
				Vehicle vehicle = (Vehicle) iterator.next();
				
				if(vehicle.isDisponible())
					list.add(vehicle);
					
			}
		 
		return list;
	}
	 

	public Vehicle search(int id) throws RepositoryException {
		return this.vehicleRecord.search(id);
	}


	public void update(Vehicle vehicle) throws ObjectNotValidException {
		this.vehicleRecord.update(vehicle);
		
	}


	public Iterator<Vehicle> iterator() throws RepositoryException {
		return this.vehicleRecord.iterator();
	}


	public void insert(Vehicle vehicle) throws ObjectNotValidException, RepositoryException {
		
		this.vehicleRecord.insert(vehicle);
		
	}
	
	
	
	
	 
}
 
