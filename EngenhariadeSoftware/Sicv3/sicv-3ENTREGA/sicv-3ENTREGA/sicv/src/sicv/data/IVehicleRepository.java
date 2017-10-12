package sicv.data;

import java.util.Iterator;

import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.vehicle.Vehicle;

public interface IVehicleRepository {
 
	public abstract void insert(Vehicle vehicle) throws ObjectNotValidException, RepositoryException;
	public abstract Vehicle search(int id) throws RepositoryException;
	public abstract void remove(int id) throws RepositoryException;
	public abstract void update(Vehicle vehicle) throws ObjectNotValidException;
	public abstract boolean exist(int id) throws RepositoryException;
	public abstract Iterator<Vehicle> iterator() throws RepositoryException;
}
 
