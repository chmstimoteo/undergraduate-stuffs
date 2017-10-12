package sicv.data;

import java.util.Iterator;

import sicv.model.location.Street;

public interface IStreetRepository {
 
	public abstract void insert(Street street);
	public abstract Street search(int id);
	public abstract void remove(int id);
	public abstract void update(Street street);
	public abstract Street search(String name);
	public abstract Iterator<Street> iterator();
}
 
