package sicv.data;

import java.util.Iterator;

import sicv.model.localization.Cross;

public interface ICrossRepository {
 
	
	public abstract void insert(Cross cross);
	public abstract Cross search(int id);
	public abstract void remove(int id);
	public abstract void update(Cross cross);
	public abstract Iterator<Cross> iterator();
	public abstract boolean exist(int id);
}
 
