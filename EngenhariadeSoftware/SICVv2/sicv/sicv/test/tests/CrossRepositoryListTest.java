
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.CrossRepositoryList;
import sicv.model.localization.Cross;



public class CrossRepositoryListTest extends TestCase{

    private CrossRepositoryList crossrepository;
    
    public CrossRepositoryListTest() {
    }

 
    public static void setUpClass() throws Exception {
    }

  
    public static void tearDownClass() throws Exception {
    }

   
    public void setUp() {
        crossrepository = new CrossRepositoryList();
    }

    
    public void tearDown() {
        crossrepository = null;
    }

    /**
     * Test de insert method, da class CrossRepositoryList.
     */
   
    public void testInsert() {
    	Cross cross = new Cross(1);
        cross.setId(1);
        crossrepository.insert(cross);
               
        assertEquals("UpdateTest failed.",1, crossrepository.search(1).getId());
    }
    /**
     * Test de remove method, da class CrossRepositoryList.
     */
   
    public void testRemove() {
    	Cross cross = new Cross(1);
        cross.setId(1);
        crossrepository.insert(cross);
        crossrepository.remove(1);
        
        assertEquals("UpdateTest failed.",false, crossrepository.exist(1));
        
    }

    /**
     * Test de search method, da class CrossRepositoryList.
     */
   
    public void testSearch() {
    	Cross cross = new Cross(1);
        cross.setId(1);
        crossrepository.insert(cross);
        
        Cross crossUpdate = new Cross(1);
        crossrepository.update(crossUpdate);
        
        assertEquals("UpdateTest failed.",cross.getId(), crossrepository.search(1).getId());
 
    }

    /**
     * Test de update method, da class CrossRepositoryList.
     */
   
    public void testUpdate() {
        Cross cross = new Cross(1);
        cross.setId(1);
        crossrepository.insert(cross);
        
        Cross crossUpdate = new Cross(1);
        crossrepository.update(crossUpdate);
        
        assertEquals("UpdateTest failed.",cross.getId(), crossrepository.search(1).getId());
    }
    
    public static Test suite() {
        return new TestSuite(CrossRepositoryListTest.class);
       
    }

}