package sicv.data;

import java.util.Iterator;

import sicv.model.occurrence.Occurrence;

public interface IOccurrenceRepository {
 
	public abstract void insert(Occurrence occurrence);
	public abstract Occurrence search(int id);
	public abstract void remove(int id);
	public abstract void update(Occurrence occurrence);
	public abstract boolean exist(int id);
	public abstract Iterator<Occurrence> iterator();
}
 
