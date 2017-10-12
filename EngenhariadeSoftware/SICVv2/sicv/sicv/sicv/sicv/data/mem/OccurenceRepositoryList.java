package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IOccurrenceRepository;
import sicv.model.occurrence.Occurrence;

public class OccurenceRepositoryList implements IOccurrenceRepository {
 
	private List<Occurrence> occurences;

	
	public OccurenceRepositoryList() {
	this.occurences = new ArrayList<Occurrence>();
	}
	 
	public void insert(Occurrence occurrence) {
		
		this.occurences.add(occurrence);
	}

	 
	public void remove(int id) {

		for (Iterator<Occurrence> iterator = this.occurences.iterator(); iterator.hasNext();) {
			 Occurrence occurrence =  iterator.next();
			 if (occurrence.getId() == id) {
				this.occurences.remove(occurrence);
				return;
			}
		}
	}

	 
	public Occurrence search(int id) {
		
		for (Iterator<Occurrence> iterator = this.occurences.iterator(); iterator.hasNext();) {
			 Occurrence occurrence =  iterator.next();
			 if (occurrence.getId() == id) {
				return occurrence;
			}
		}
		
		return null;
	}

	 
	public void update(Occurrence occurrence) {
		this.remove(occurrence.getId());
		this.insert(occurrence);
		
	}
	 
	public boolean exist(int id) {
		for (Iterator<Occurrence> iterator = this.occurences.iterator(); iterator.hasNext();) {
			 Occurrence occurrence =  iterator.next();
			 if (occurrence.getId() == id) {
				return true;
			}
		}
		
		return false;
	}
	 
}
 
