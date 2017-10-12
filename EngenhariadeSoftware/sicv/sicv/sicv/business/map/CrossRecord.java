package sicv.business.map;

import java.util.Iterator;

import sicv.data.ICrossRepository;
import sicv.data.mem.CrossRepositoryList;
import sicv.model.localization.Cross;

public class CrossRecord {
 
	private ICrossRepository crossRepository;
	 
	public CrossRecord(ICrossRepository crossRepository) {
		
		this.crossRepository = new CrossRepositoryList();
	}
	
	public void insert(Cross cross) {
		this.crossRepository.insert(cross);
	}
	
	public void remove(int id) {
		this.crossRepository.remove(id);
	}
	
	public Cross search(int id) {
		return this.crossRepository.search(id);
	}
	
	public boolean exist(int id) {
		return this.crossRepository.exist(id);
	}
	public void update(Cross cross) {
		this.crossRepository.update(cross);
	}

	public Cross getCrossByReferencePoint(String referencePoint) {
		
			
		for (Iterator<Cross> iterator = this.crossRepository.iterator(); iterator.hasNext();) {
			Cross cross = iterator.next();
			if(cross.getReferencePointByName(referencePoint).getName().equals(referencePoint))
				return cross;
		}
			
		throw new RuntimeException("Exception");
		
	}
}
 
