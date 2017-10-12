package sicv.data.jpa;



import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import sicv.data.IOccurrenceRepository;
import sicv.lib.exceptions.ExceptionMessages;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.employee.Employee;
import sicv.model.occurrence.Occurrence;

public class OccurrenceRepositoryJPA implements IOccurrenceRepository{

	
	private IPersistenceMechanism pm;
	
	
	public OccurrenceRepositoryJPA(IPersistenceMechanism pm) {
		
		this.pm = pm;
	}


	public boolean exist(int id) throws RepositoryException, PersistenceMechanismException {
		
		boolean exist = false;
		try {
		
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			
			List<Employee> list = em.createQuery("select p from Occurrence p where p.id=:pid").setParameter("pid",id).getResultList();
		
			exist = (list.size() > 0);
		} 
		catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		} finally{
			
				this.pm.releaseCommunicationChannel();
			
		}
		
		return exist;
	}


	public void insert(Occurrence occurrence) throws RepositoryException {
		
		int id = 0;
		
		try {
			
		    
			EntityManager em;
			
			
			
			em = (EntityManager)this.pm.getCommunicationChannel();
			
			Iterator<Occurrence> i = (Iterator<Occurrence>) em.createQuery("select c from Occurrence c").getResultList().iterator();
			
			while(i.hasNext()){
				id = i.next().getId();
			}
			id++;
			occurrence.setId(id);
			em.persist(occurrence);
			
			
		} catch (PersistenceMechanismException e) {
			
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);

		}finally{
			try {
				this.pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FAIL_RELEASE_COMUNICATION_CHANNEL);
			}
		}
		
	}


	public Iterator<Occurrence> iterator() throws RepositoryException, PersistenceMechanismException {
		
		EntityManager em = null;	
		List<Occurrence> list = null;
		try {
			
		em = (EntityManager) pm.getCommunicationChannel();			
		list = (List<Occurrence>) em.createQuery("select p from Occurrence p").getResultList();       
        Iterator<Occurrence> iterator = list.iterator();		
		}catch(PersistenceMechanismException pme){
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		}finally{
			
				pm.releaseCommunicationChannel();
			
		}
		
		return list.iterator();
	}


	public void remove(int id) throws RepositoryException, PersistenceMechanismException {
		
		
		EntityManager em = null;
		Occurrence occurrence = null;
		try {
			em = (EntityManager) pm.getCommunicationChannel();
		    occurrence = ( Occurrence )em.createQuery("select p from Occurrence p where p.id = :pid ").setParameter("pid", id).getSingleResult();       
            occurrence = em.merge(occurrence);
            em.remove(occurrence);
		} catch(PersistenceMechanismException pme){
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		}finally{
			
				pm.releaseCommunicationChannel();
			
		}
		
	}


	public Occurrence search(int id) throws RepositoryException, ObjectNotFoundException, PersistenceMechanismException {
		
		
		Occurrence ocurrence = null;
		try {
			
			
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			ocurrence = (Occurrence) em.createQuery("select p from Occurrence p where p.id=:pid").setParameter("pid",id).getSingleResult();
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		}catch (NoResultException nre) {
			throw new ObjectNotFoundException(ExceptionMessages.EXC_FAIL_QUERY);
		}finally{
			
				this.pm.releaseCommunicationChannel();
			
		}
		
		return ocurrence;
		
		
		
	}


	public void update(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
		
			try {
			
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			em.persist(em.merge(occurrence));
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		}finally{
			
				this.pm.releaseCommunicationChannel();
			
		}
		
		
	}
	
	
	
	
	
	
	
}
