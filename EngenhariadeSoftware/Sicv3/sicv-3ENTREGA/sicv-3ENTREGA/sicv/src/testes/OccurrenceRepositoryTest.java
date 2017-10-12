package testes;

import junit.framework.TestCase;
import sicv.data.jpa.OccurrenceRepositoryJPA;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.lib.persistence.HibernatePersistenceMechanism;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class OccurrenceRepositoryTest  extends TestCase{

	
	public void testInsertOccurence()
	{
	
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		OccurrenceRepositoryJPA er = new OccurrenceRepositoryJPA(mp);
		
		Occurrence occurrence = new Occurrence(Priority.HIGH,Status.BUSY,new Position(0,0));
		
		
		try {
			mp.beginTransaction();
			er.insert(occurrence);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Occurrence occurrenceSearch =  er.search(1);
			mp.commitTransaction();
			
			assertEquals("Fail",occurrence.getPosition(),occurrenceSearch.getPosition());
			assertEquals("Fail",occurrence.getPriority(),occurrenceSearch.getPriority());
			assertEquals("Fail",occurrence.getStatus(),occurrenceSearch.getStatus());
			assertEquals("Fail",occurrence.getId(),occurrenceSearch.getId());
			
		} catch (Exception e) {
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		
	}
	
	
	public void testUpdateOccurence()
	{
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OccurrenceRepositoryJPA er = new OccurrenceRepositoryJPA(mp);
		
		Occurrence occurrence = new Occurrence(Priority.LOW,Status.FREE,new Position(0,0));
		occurrence.setId(1);
	   
		
		try {
			mp.beginTransaction();
			er.update(occurrence);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Occurrence occurrenceSearch  = (Occurrence) er.search(1); 
			mp.commitTransaction();
			
			assertEquals("Fail",occurrence.getPosition(),occurrenceSearch.getPosition());
			assertEquals("Fail",occurrence.getPriority(),occurrenceSearch.getPriority());
			assertEquals("Fail",occurrence.getStatus(),occurrenceSearch.getStatus());
			assertEquals("Fail",occurrence.getId(),occurrenceSearch.getId());
			
			
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
	
	
	public void testRemoveOccurence() {
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OccurrenceRepositoryJPA er = new OccurrenceRepositoryJPA(mp);
		
		
		
		try {
			
			 mp.beginTransaction();
		     er.remove(1); 
		     mp.commitTransaction();
			
		     mp.beginTransaction();
		     boolean exist = er.exist(1); 
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
	
	
	public void  testExitsOccurence() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OccurrenceRepositoryJPA er = new OccurrenceRepositoryJPA(mp);
		
		Occurrence occurrence = new Occurrence(Priority.HIGH,Status.BUSY,new Position(0,0));
		
		
		try {
			
			mp.beginTransaction();
			er.insert(occurrence);
			mp.commitTransaction();
			
			mp.beginTransaction();
			boolean exist =  er.exist(occurrence.getId());
			mp.commitTransaction();
			
			assertEquals("Fail",true,exist);
			
			
			
		} catch (RepositoryException e) {
			
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void testSearchOccurence() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		OccurrenceRepositoryJPA er = new OccurrenceRepositoryJPA(mp);
		
		Occurrence occurrence = new Occurrence(Priority.HIGH,Status.BUSY,new Position(0,0));
		
		try {
			mp.beginTransaction();
			er.insert(occurrence);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Occurrence occurrenceSearch = (Occurrence) er.search(occurrence.getId());
			mp.commitTransaction();
			
			assertEquals("Fail",occurrence.getPosition(),occurrenceSearch.getPosition());
			assertEquals("Fail",occurrence.getPriority(),occurrenceSearch.getPriority());
			assertEquals("Fail",occurrence.getStatus(),occurrenceSearch.getStatus());
			assertEquals("Fail",occurrence.getId(),occurrenceSearch.getId());
			
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
}
