package testes;


import junit.framework.TestCase;
import sicv.data.jpa.EmployeeRepositoryJPA;
import sicv.data.jpa.OccurrenceRepositoryJPA;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.TransactionException;
import sicv.lib.persistence.HibernatePersistenceMechanism;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.employee.ForwardingAgent;


public class EmployeeRepositoryTest extends TestCase {


	public void testInsertEmployee()
	{
	
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		EmployeeRepositoryJPA er = new EmployeeRepositoryJPA(mp);
		OccurrenceRepositoryJPA or = new OccurrenceRepositoryJPA(mp);
		
		
		ForwardingAgent agent = new ForwardingAgent(1,"Diego","i","123");
		
		
		
		
		//Occurrence o = new Occurrence(Priority.HIGH,Status.BUSY,new Position(0,0));
		//o.setId(1);
	    //agent.registerOccurrence(o);
		
		try {
			mp.beginTransaction();
			er.insert(agent);
			mp.commitTransaction();
			
			mp.beginTransaction();
			ForwardingAgent agentSearch = (ForwardingAgent) er.search("i");
			mp.commitTransaction();
			
			assertEquals("Fail",agent.getName(),agentSearch.getName());
			assertEquals("Fail",agent.getLogin(),agentSearch.getLogin());
			assertEquals("Fail",agent.getPassword(),agentSearch.getPassword());
			assertEquals("Fail",agent.getId(),agentSearch.getId());
			//assertEquals("Fail",agent.getOccurrence().getId(),agentSearch.getOccurrence().getId());
			
		} catch (Exception e) {
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		
	}
	
	
	/*public void testUpdateEmployee()
	{
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmployeeRepositoryJPA ar = new EmployeeRepositoryJPA(mp);
		Employee agent = new  ForwardingAgent(0,"","i","");
	   
		
		try {
			mp.beginTransaction();
			ar.update(agent);
			mp.commitTransaction();
			
			mp.beginTransaction();
			ForwardingAgent agentSearch = (ForwardingAgent) ar.search("i"); 
			mp.commitTransaction();
			
			assertEquals("Fail",agent.getName(),agentSearch.getName());
			assertEquals("Fail",agent.getLogin(),agentSearch.getLogin());
			assertEquals("Fail",agent.getPassword(),agentSearch.getPassword());
			assertEquals("Fail",agent.getId(),agentSearch.getId());
			
		} catch (Exception e) {
			
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public void testRemoveEmployee() {
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmployeeRepositoryJPA ar = new EmployeeRepositoryJPA(mp);
		
		
		try {
			
			 mp.beginTransaction();
		     ar.remove("i"); 
		     mp.commitTransaction();
			
		     mp.beginTransaction();
		     boolean exist = ar.exist("i"); 
		     mp.commitTransaction();
		     
		     assertEquals("Fail", false,exist);
		     
		} catch (Exception e) {
			
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	
	
	public void  testExitsEmployee() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EmployeeRepositoryJPA ar = new EmployeeRepositoryJPA(mp);
		
		
		try {
			
			ForwardingAgent agent = new ForwardingAgent(1,"Diego","o","123");
			
			mp.beginTransaction();
			ar.insert(agent);
			mp.commitTransaction();
			
			mp.beginTransaction();
			boolean exist =  ar.exist("o");
			mp.commitTransaction();
			
			assertEquals("Fail", true,exist	);
			
			
			
			
		}catch (Exception e) {

			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} 
	}
	
	public void testSearchEmployee() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		EmployeeRepositoryJPA er = new EmployeeRepositoryJPA(mp);
		
		ForwardingAgent agent = new ForwardingAgent(1,"Diego","u","123");
		
		
		try {
			mp.beginTransaction();
			er.insert(agent);
			mp.commitTransaction();
			
			mp.beginTransaction();
			ForwardingAgent agentSearch = (ForwardingAgent) er.search("u");
			mp.commitTransaction();
			
			assertEquals("Fail",agent.getName(),agentSearch.getName());
			assertEquals("Fail",agent.getLogin(),agentSearch.getLogin());
			assertEquals("Fail",agent.getPassword(),agentSearch.getPassword());
			assertEquals("Fail",agent.getId(),agentSearch.getId());
			
		} catch (Exception e) {
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		
	}*/
}
