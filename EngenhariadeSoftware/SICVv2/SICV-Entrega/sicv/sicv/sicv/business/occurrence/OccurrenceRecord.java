package sicv.business.occurrence;

import java.util.Iterator;

import sicv.data.IOccurrenceRepository;
import sicv.model.occurrence.Occurrence;

public class OccurrenceRecord {
 
	private IOccurrenceRepository repositoryOccurrence;
	 
	public OccurrenceRecord(IOccurrenceRepository occurrenceRepository) {
		this.repositoryOccurrence = occurrenceRepository;
	}
	
	
	
	public void insert(Occurrence occurrence) {
		if (!this.repositoryOccurrence.exist(occurrence.getId())) {
			this.repositoryOccurrence.insert(occurrence);
		}else{
			throw new RuntimeException("ja existe");
		}
		
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
	
	public Iterator<Occurrence> iterator() {
		return this.repositoryOccurrence.iterator();
	}



	public void insertVehiclesDistancesByOccurrence(Occurrence occurrence,
			String vehiclesDistances) {
		// TODO Auto-generated method stub
		
	}



	public boolean exist(int id) {
		// TODO Auto-generated method stub
		return this.repositoryOccurrence.exist(id);
	}
	
	
	
}
 
