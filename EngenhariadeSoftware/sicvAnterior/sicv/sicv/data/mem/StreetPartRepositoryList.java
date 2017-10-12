package sicv.data.mem;

import java.util.Iterator;
import java.util.List;

import sicv.data.IStreetPartRepository;
import sicv.model.localization.StreetPart;

public class StreetPartRepositoryList implements IStreetPartRepository{

	private List<StreetPart> streetParts;

	
	public void insert(StreetPart streetPart) {
		
		this.streetParts.add(streetPart);
		
	}

	
	public void remove(int id) {
		
		for (Iterator<StreetPart> iterator = this.streetParts.iterator(); iterator.hasNext();) {
			StreetPart streetPart = iterator.next();
			if (streetPart.getId() == id) {
				this.streetParts.remove(streetPart);
			}
		}
		
	}

	
	public StreetPart search(int id) {
		
		
		for (Iterator<StreetPart> iterator = this.streetParts.iterator(); iterator.hasNext();) {
			StreetPart streetPart = iterator.next();
			if (streetPart.getId() == id) {
				return streetPart;
			}
		}
		return null;
	}

	
	public void update(StreetPart streetPart) {
		// TODO Auto-generated method stub
		
	}

	
	public Iterator<StreetPart> iterator() {
		
		return this.streetParts.iterator();
	}

	

	
	
	
	
	
	
}
