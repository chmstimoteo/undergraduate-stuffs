package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.ICrossRepository;
import sicv.model.localization.Cross;

public class CrossRepositoryList implements ICrossRepository {
 
	private List<Cross> crosses;

	public CrossRepositoryList() {
	this.crosses = new ArrayList<Cross>();
	}
	
	
	public void insert(Cross cross) {
		
		this.crosses.add(cross);
		
	}

	
	public void remove(int id) {
		
		for (Iterator<Cross> iterator = this.crosses.iterator(); iterator.hasNext();) {
			Cross cross =  iterator.next();
			
			if (cross.getId() == id) {
				this.crosses.remove(cross);
				return;
			}
		}
	}

	
	public Cross search(int id) {
		
		for (Iterator<Cross> iterator = this.crosses.iterator(); iterator.hasNext();) {
			Cross cross =  iterator.next();
			
			if (cross.getId() == id) {
				
				return cross;
			}
		}
		
		return null;
	}

	
	public void update(Cross cross) {
		
		this.remove(cross.getId());
		this.insert(cross);
		
	}
	
	public Iterator<Cross> iterator() {
		
		return this.crosses.iterator();
	}

	
	public boolean exist(int id) {
		for (Iterator<Cross> iterator = this.crosses.iterator(); iterator.hasNext();) {
			Cross cross =  iterator.next();
			
			if (cross.getId() == id) {
				
				return true;
			}
		}
		
		return false;
	}
	 
}
 
