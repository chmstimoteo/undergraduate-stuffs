package sicv.business.occurrence;

import sicv.data.IOccurrenceRepository;
import sicv.model.occurrence.Occurrence;

public class OccurenceRecord {
 
	private IOccurrenceRepository repositoryOccurrence;
	 
	public OccurenceRecord(IOccurrenceRepository occurrenceRepository) {
		this.repositoryOccurrence = occurrenceRepository;
	}
	
	
	
	public void insert(Occurrence occurence) {
		this.repositoryOccurrence.insert(occurence);
	}
	
	public void remove(int id) {
		this.repositoryOccurrence.remove(id);
	}
	
	public Occurrence search(int id) {
		return this.repositoryOccurrence.search(id);
	}
	
	
	public void update(Occurrence occurrence) {
		this.repositoryOccurrence.update(occurrence);
	}
}
 
