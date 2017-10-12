package sicv.data;

import java.util.Iterator;

import sicv.model.vehicle.Vehicle;

public interface IVehicleRepository {
 
	public abstract void insert(Vehicle vehicle);
	public abstract Vehicle search(int id);
	public abstract void remove(int id);
	public abstract void update(Vehicle vehicle);
	public abstract boolean exist(int id);
	public abstract Iterator<Vehicle> iterator();
}
 
