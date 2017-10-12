package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IVehicleRepository;
import sicv.model.vehicle.Vehicle;

public class VehicleRepositoryList implements IVehicleRepository {
 
	private List<Vehicle> vehicles;

	
	
	public VehicleRepositoryList(){
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	
	public void insert(Vehicle vehicle) {
		
		this.vehicles.add(vehicle);
		
	}

	
	public void remove(int id) {
		for (Iterator<Vehicle> iterator = this.vehicles.iterator(); iterator.hasNext();) {
			Vehicle vehicle =  iterator.next();
			
			if(vehicle.getId() == id){
				this.vehicles.remove(vehicle);
				return;
			 }
		}
		
	}

	
	public Vehicle search(int id) {
		
		for (Iterator<Vehicle> iterator = this.vehicles.iterator(); iterator.hasNext();) {
			Vehicle vehicle =  iterator.next();
			
			if(vehicle.getId() == id){
				return vehicle;
			 }
		}
		
		return null;
	}

	
	public void update(Vehicle vehicle) {
		
		this.remove(vehicle.getId());
		this.insert(vehicle);
		
	}

	
	public Iterator<Vehicle> iterator() {
		
		return  this.vehicles.iterator();
	}

	
	public boolean exist(int id) {
		for (Iterator<Vehicle> iterator = this.vehicles.iterator(); iterator.hasNext();) {
			Vehicle vehicle =  iterator.next();
			
			if(vehicle.getId() == id){
				return true;
			 }
		}
		
		return false;
	}
	 
}
 
