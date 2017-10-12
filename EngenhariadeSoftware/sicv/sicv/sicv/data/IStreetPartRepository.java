package sicv.data;


import java.util.Iterator;

import sicv.model.localization.StreetPart;

public interface IStreetPartRepository {
	
	
	
	public abstract void insert(StreetPart  streetPart);
	public abstract StreetPart  search(int id);
	public abstract void remove(int id);
	public abstract void update(StreetPart  streetPart);
	public abstract Iterator<StreetPart> iterator();
	
	
}
