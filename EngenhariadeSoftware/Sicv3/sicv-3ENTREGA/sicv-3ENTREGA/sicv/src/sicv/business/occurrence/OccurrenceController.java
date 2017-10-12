package sicv.business.occurrence;

import java.util.Iterator;

import sicv.data.IOccurrenceRepository;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Status;


public class OccurrenceController {
 
	private OccurrenceRecord occurrenceRecord;
	
		
	public OccurrenceController(IOccurrenceRepository occurrenceRepository) {
		this.occurrenceRecord = new OccurrenceRecord(occurrenceRepository);
	}
	
	
	 public void insert(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException{
		 this.occurrenceRecord.insert(occurrence);
	 }
	 
	 public void update(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
		this.occurrenceRecord.update(occurrence);
	}
	 
	 public boolean hasOccurrenceWaiting() throws RepositoryException, PersistenceMechanismException{
		 
		 boolean response = false;
		 Iterator<Occurrence> occurrences = this.occurrenceRecord.iterator();
		 
		 while (occurrences.hasNext()) {
			Occurrence occurrence = occurrences.next();
			if (occurrence.getStatus() == Status.WAITING) {
				response = true;
			}
		}
		 
		 return response;
	 }
	 
	public Occurrence getNextOccurrence() throws RepositoryException, PersistenceMechanismException {
		
		Occurrence response = null;
		Iterator<Occurrence> occurrences = this.occurrenceRecord.iterator();
		Priority priority = Priority.LOW;
		
		 while (occurrences.hasNext()) {
			Occurrence occurrence = occurrences.next();
			if (occurrence.getStatus()== Status.WAITING && occurrence.getPriority().compareTo(priority) > 0) {
				response = occurrence;
				priority = occurrence.getPriority();
			}
		}
		 
		 return response;
	}
	
	


	public Occurrence search(int id) throws RepositoryException, ObjectNotFoundException, PersistenceMechanismException {
		return this.occurrenceRecord.search(id);
	}
	
}
 
