package sicv.business.occurrence;

import java.util.Iterator;

import sicv.data.IOccurrenceRepository;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.occurrence.Occurrence;

public class OccurrenceRecord {
 
	private IOccurrenceRepository repositoryOccurrence;
	 
	public OccurrenceRecord(IOccurrenceRepository occurrenceRepository) {
		this.repositoryOccurrence = occurrenceRepository;
	}
	
	
	
	public void insert(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
		if (!this.repositoryOccurrence.exist(occurrence.getId())) {
			this.repositoryOccurrence.insert(occurrence);
		}else{
			throw new RuntimeException("ja existe");
		}
		
	}
	
	public void remove(int id) throws RepositoryException, PersistenceMechanismException {
		this.repositoryOccurrence.remove(id);
	}
	
	public Occurrence search(int id) throws RepositoryException, ObjectNotFoundException, PersistenceMechanismException {
		return this.repositoryOccurrence.search(id);
	}
	
	
	public void update(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
		this.repositoryOccurrence.update(occurrence);
	}
	
	public Iterator<Occurrence> iterator() throws RepositoryException, PersistenceMechanismException {
		return this.repositoryOccurrence.iterator();
	}



	



	public boolean exist(int id) throws RepositoryException, PersistenceMechanismException {
		// TODO Auto-generated method stub
		return this.repositoryOccurrence.exist(id);
	}
	
	
	
}
 
