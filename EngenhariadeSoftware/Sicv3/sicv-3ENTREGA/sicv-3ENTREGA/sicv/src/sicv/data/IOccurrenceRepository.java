package sicv.data;

import java.util.Iterator;

import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.occurrence.Occurrence;

public interface IOccurrenceRepository {
 
	public abstract void insert(Occurrence occurrence) throws RepositoryException;
	public abstract Occurrence search(int id) throws RepositoryException, ObjectNotFoundException, PersistenceMechanismException;
	public abstract void remove(int id) throws RepositoryException, PersistenceMechanismException;
	public abstract void update(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException;
	public abstract boolean exist(int id) throws RepositoryException, PersistenceMechanismException;
	public abstract Iterator<Occurrence> iterator() throws RepositoryException, PersistenceMechanismException;
}
 
