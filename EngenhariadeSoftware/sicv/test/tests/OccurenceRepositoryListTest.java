
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.OccurenceRepositoryList;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;


public class OccurenceRepositoryListTest extends TestCase{

    private OccurenceRepositoryList occurrencerepository;
    
    public OccurenceRepositoryListTest() {
    }


    public static void setUpClass() throws Exception {
    }


    public static void tearDownClass() throws Exception {
    }

  
    public void setUp() {
        this.occurrencerepository = new OccurenceRepositoryList();
    }

   
    public void tearDown() {
        this.occurrencerepository = null;
    }

    /**
     * Test de insert method, da class OccurenceRepositoryList.
     */
   
    public void tesInsert() {
        Occurrence occurrence = new Occurrence(Priority.VERYHIGH,1,null,1);
      
        occurrencerepository.insert(occurrence);
        assertEquals("InsertTest failed.", Priority.VERYHIGH, occurrencerepository);
    }

    /**
     * Test de remove method, da class OccurenceRepositoryList.
     */
   
    public void testRemove() {
    	 Occurrence occurrence = new Occurrence(Priority.VERYHIGH,1,null,1);
         
         occurrencerepository.insert(occurrence);
         occurrencerepository.remove(1);
         assertEquals("InsertTest failed.", false, occurrencerepository.exist(1));
    }

    /**
     * Test de search method, da class OccurenceRepositoryList.
     */
   
    public void testSearch() {
    	Occurrence occurrence = new Occurrence(Priority.VERYHIGH,1,null,1);
        
        occurrencerepository.insert(occurrence);

        assertEquals("InsertTest failed.",Priority.VERYHIGH, occurrencerepository.search(1).getPriority());
   }
    

    /**
     * Test de update method, da class OccurenceRepositoryList.
     */
    
    public void testUpdate() {
    	Occurrence occurrence = new Occurrence(Priority.VERYHIGH,1,null,1);
        
        occurrencerepository.insert(occurrence);

        occurrence = new Occurrence(Priority.LOW,1,null,1);
        occurrencerepository.update(occurrence);
        assertEquals("InsertTest failed.",Priority.LOW, occurrencerepository.search(1).getPriority());
    }
    
    public static Test suite() {
        return new TestSuite(OccurenceRepositoryListTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }

}