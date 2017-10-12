package testes;

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.business.occurrence.OccurrenceRecord;
import sicv.data.rdb.OccurrenceRepositotyRDB;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class OccurrenceRecordTest extends TestCase{
	
private OccurrenceRecord  occurrenceRecord;
    
    public OccurrenceRecordTest() {
    }


    public static void setUpClass() throws Exception {
    }


    public static void tearDownClass() throws Exception {
    }

    
    public void setUp() {
       this.occurrenceRecord =  new OccurrenceRecord(new OccurrenceRepositotyRDB());
    }

   
    public void tearDown() {
       
    }

    /**
     * Test de insert method, da class OccurenceRepositoryList.
     * @throws PersistenceMechanismException 
     * @throws RepositoryException 
     * @throws ObjectNotFoundException 
     */
   
    public void tesInsert() throws RepositoryException, PersistenceMechanismException, ObjectNotFoundException {
    	float lat = 8;
    	float lng = 35;
    	Position position = new Position(lat , lng);
    	int id = 15;
    	
    	Occurrence occurrence = new Occurrence(Priority.AVERAGE,Status.WAITING, position);
    	
    	this.occurrenceRecord.insert(occurrence);
        assertEquals("InsertTest failed.", lat, occurrenceRecord.search(id).getId());
    }

    /**
     * Test de remove method, da class OccurenceRepositoryList.
     */
   
    /*public void testRemove() {
    	float lat = 8;
    	float lng = 35;
    	Position position = new Position(lat , lng);
    	int id = 15;
    	
    	Occurrence occorrence = new Occurrence(Priority.AVERAGE,Status.WAITING, position,id);
    	
    	this.occurrenceRecord.insert(occorrence);
   
    	this.occurrenceRecord.remove(id);
         assertEquals("InsertTest failed.", false, occurrenceRecord.exist(id));
    }*/

    /**
     * Test de search method, da class OccurenceRepositoryList.
     * @throws PersistenceMechanismException 
     * @throws ObjectNotFoundException 
     * @throws RepositoryException 
     */
   
    public void testSearch() throws RepositoryException, ObjectNotFoundException, PersistenceMechanismException {
        float lat = 8;
    	float lng = 35;
    	Position position = new Position(lat , lng);
    	int id = 1;
    	
    	//Occurrence occorrence = new Occurrence(Priority.AVERAGE,Status.WAITING, position,id);
    	
    	//this.occurrenceRecord.insert(occorrence);
    	 assertEquals("searchTest failed.", lat, occurrenceRecord.search(id).getPosition().getLat());
   }
    

    /**
     * Test de update method, da class OccurenceRepositoryList.
     * @throws PersistenceMechanismException 
     * @throws RepositoryException 
     * @throws ObjectNotFoundException 
     */
    
    public void testUpdate() throws RepositoryException, PersistenceMechanismException, ObjectNotFoundException {
    	float lat = 8;
    	float lng = 35;
    	Position position = new Position(lat , lng);
    	int id = 1;
    	Occurrence occurrence = new Occurrence(Priority.AVERAGE,Status.WAITING, position);
    	
    	//this.occurrenceRecord.insert(occurrence);
    	
    	Occurrence occorrenceUpdate = new Occurrence(Priority.AVERAGE,Status.IN_ATTENDANT, position);
    	
    	
    	this.occurrenceRecord.update(occorrenceUpdate);
    	
        assertEquals("updateTest failed.",Status.IN_ATTENDANT, occurrenceRecord.search(id).getStatus());
    }
    
    public static Test suite() {
        return new TestSuite(OccurrenceRecordTest.class);
    }
    
    public void testIterator() throws RepositoryException, PersistenceMechanismException {
		Iterator<Occurrence> itr = this.occurrenceRecord.iterator();
		boolean result = true;
		
		while(itr.hasNext())
		{
			if(    !this.occurrenceRecord.exist(itr.next().getId())  )
			{
				result = false;
				break;
			}
		}
		
		assertEquals("updateTest failed.",result, true);
	}


}
