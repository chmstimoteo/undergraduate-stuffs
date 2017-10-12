package sicv.data.jpa;






import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import sicv.data.IVehicleRepository;
import sicv.lib.exceptions.ExceptionMessages;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.vehicle.Vehicle;

public class VehicleRepositoryJPA implements IVehicleRepository{

	IPersistenceMechanism pm;
	
	public VehicleRepositoryJPA(IPersistenceMechanism pm) {
		this.pm = pm;
	}
	
	
	public boolean exist(int id) throws RepositoryException {
		
		EntityManager em = null;
		boolean exist = true;
		
		try{
		em = (EntityManager) pm.getCommunicationChannel();
		em.createQuery("select p from Vehicle p where p.id = :pid ").setParameter("pid", id).getSingleResult();       
		}catch(PersistenceMechanismException ex){
			ex.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		} catch(NoResultException nre){
			exist = false;
		}
		 finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}

	public void insert(Vehicle vehicle) throws ObjectNotValidException, RepositoryException {
		
		
		EntityManager em = null;
		
		if (vehicle == null) {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULL);
		}
		
		try {

			em = (EntityManager) pm.getCommunicationChannel();
			
			 List<Vehicle> list = (List<Vehicle>) em.createQuery("select p from Vehicle p").getResultList();       
             Iterator<Vehicle> i = list.iterator();
			 int code = 0;
             while(i.hasNext())
             {
            	 code = i.next().getId();
            	 System.out.println(code);
             } 
            code++; 
            vehicle.setId(code);
			em.persist(vehicle);
		} catch (PersistenceMechanismException e) {
			
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		} 
	    finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public Iterator<Vehicle> iterator() throws RepositoryException {
		
		EntityManager em = null;	
		List<Vehicle> list = null;
		try {
			
		em = (EntityManager) pm.getCommunicationChannel();			
		list = (List<Vehicle>) em.createQuery("select p from Vehicle p").getResultList();       
        Iterator<Vehicle> iterator = list.iterator();		
		}catch(PersistenceMechanismException pme){
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		}finally{
				try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			}
		}
		
		return list.iterator();
	}

	public void remove(int id) throws RepositoryException {
	
		EntityManager em = null;
		Vehicle vehicles = null;
		try {
			em = (EntityManager) pm.getCommunicationChannel();
		    vehicles = ( Vehicle )em.createQuery("select p from Vehicle p where p.id = :pid ").setParameter("pid", id).getSingleResult();       
            vehicles = em.merge(vehicles);
            em.remove(vehicles);
		} catch(PersistenceMechanismException pme){
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public Vehicle search(int id) throws RepositoryException {
		
		EntityManager em = null;
		Vehicle vehicle = null;
		try{
		em = (EntityManager) pm.getCommunicationChannel();
		vehicle = (Vehicle) em.createQuery("select p from Vehicle p where p.id = :pid ").setParameter("pid", id).getSingleResult();       
		}catch(PersistenceMechanismException pme){
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_INCLUSION);
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			}
		}
		return vehicle;
	
	}

	public void update(Vehicle vehicle) throws ObjectNotValidException {
		
		
		EntityManager em = null;
		
		if (vehicle == null) {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULL);
		}
		
		try {
			em = (EntityManager) pm.getCommunicationChannel();
			vehicle = em.merge(vehicle);
			em.persist(vehicle);
		} catch (Exception e){
			e.printStackTrace();
			try {
				pm.rollbackTransaction();
			} catch (TransactionException e1) {
				
				e1.printStackTrace();
			}
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	
	
}
