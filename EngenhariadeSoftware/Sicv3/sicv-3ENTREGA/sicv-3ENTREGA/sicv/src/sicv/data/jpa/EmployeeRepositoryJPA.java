package sicv.data.jpa;



import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import sicv.data.IEmployeeRepository;
import sicv.lib.exceptions.ExceptionMessages;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.employee.Employee;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;




public  class EmployeeRepositoryJPA implements IEmployeeRepository{

	
	private IPersistenceMechanism pm;
	

	public EmployeeRepositoryJPA(IPersistenceMechanism  pm) {
		this.pm = pm;
	}

	
	
	public void insert(Employee employee) throws RepositoryException {
		
		int code = 0;
		
		try {
			
		    
			EntityManager em;
			
			
			
			em = (EntityManager)this.pm.getCommunicationChannel();
			
			Iterator<Employee> i = (Iterator<Employee>) em.createQuery("select c from Employee c").getResultList().iterator();
			
			while(i.hasNext()){
				code = i.next().getCode();
			}
			code++;
			employee.setCode(code);
			em.persist(employee);
			
			
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
	
	
	
	public Employee search(String login) throws RepositoryException, ObjectNotFoundException  
	{
		Employee employee = null;
		try {
			
			
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			employee = (Employee) em.createQuery("select p from Employee p where p.login=:plogin").setParameter("plogin",login).getSingleResult();
		} 
			
		catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		}catch (NoResultException nre) {
			throw new ObjectNotFoundException(ExceptionMessages.EXC_FAIL_QUERY);
		}finally{
			try {
				this.pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FAIL_RELEASE_COMUNICATION_CHANNEL);
			}
		}
		
		return employee;
		
	}

	
	
	public void update(Employee employee) throws RepositoryException  {
		
		
		try {
			
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			em.persist(em.merge(employee));
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		}finally{
			try {
				this.pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FAIL_RELEASE_COMUNICATION_CHANNEL);
			}
		}
		
	}



	public boolean exist(String login) throws RepositoryException {
	
		
		boolean exist = false;
		try {
		
			EntityManager em = (EntityManager) this.pm.getCommunicationChannel();
			
			List<Employee> list = em.createQuery("select p from Employee p where p.login=:plogin").setParameter("plogin",login).getResultList();
		
			exist = (list.size() > 0);
		} 
		catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FAIL_QUERY);
		} finally{
			try {
				this.pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FAIL_RELEASE_COMUNICATION_CHANNEL);
			}
		}
		
		return exist;
	}



	public Iterator<Employee> iterator() throws RepositoryException {
		
		
		EntityManager em = null;	
		List<Employee> list = null;
		try {
			
		em = (EntityManager) pm.getCommunicationChannel();			
		list = (List<Employee>) em.createQuery("select p from Employee p").getResultList();       
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



	public void remove(String login) throws RepositoryException {
	
		
		EntityManager em = null;
		Employee employee = null;
		try {
			em = (EntityManager) pm.getCommunicationChannel();
		    employee = ( Employee )em.createQuery("select p from Employee p where p.login = :plogin ").setParameter("plogin", login).getSingleResult();       
            employee = em.merge(employee);
            em.remove(employee);
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

	 
	
}
