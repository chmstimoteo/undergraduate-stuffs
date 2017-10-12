package sicv.business;

import sicv.data.IVehicleRepository;
import sicv.model.vehicle.Vehicle;

public class VehicleRecord {
 
	private IVehicleRepository vehicleRepository;
	 
	
	public VehicleRecord(IVehicleRepository vehicleRepository) {
	
		this.vehicleRepository = vehicleRepository;
	}
	
	public void insert(Vehicle vehicle) {
		this.vehicleRepository.insert(vehicle);
	}
	
	public void remove(int id) {
		this.vehicleRepository.remove(id);
	}
	
	public Vehicle search(int id) {
		return this.vehicleRepository.search(id);
	}
	public void update(Vehicle vehicle) {
		this.vehicleRepository.update(vehicle);
	}
}
 
