package sicv.business.vehicle;

import java.util.Iterator;

import sicv.data.IVehicleRepository;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.vehicle.Vehicle;

public class VehicleRecord {
 
	private IVehicleRepository vehicleRepository;
	 
	
	public VehicleRecord(IVehicleRepository vehicleRepository) {
	
		this.vehicleRepository = vehicleRepository;
	}
	
	public void insert(Vehicle vehicle) throws ObjectNotValidException, RepositoryException {
		this.vehicleRepository.insert(vehicle);
	}
	
	public void remove(int id) throws RepositoryException {
		this.vehicleRepository.remove(id);
	}
	
	public Vehicle search(int id) throws RepositoryException {
		return this.vehicleRepository.search(id);
	}
	public void update(Vehicle vehicle) throws ObjectNotValidException {
		this.vehicleRepository.update(vehicle);
	}

	public Iterator<Vehicle> iterator() throws RepositoryException {
		
		return this.vehicleRepository.iterator();
	}
}
 
