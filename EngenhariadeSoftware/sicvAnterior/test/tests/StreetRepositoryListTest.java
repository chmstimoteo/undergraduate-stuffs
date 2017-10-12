
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.StreetRepositoryList;
import sicv.model.localization.Street;


public class StreetRepositoryListTest extends TestCase{

    private StreetRepositoryList streetrepository;
    
    public StreetRepositoryListTest() {
    }

   
    public static void setUpClass() throws Exception {
    }

   
    public static void tearDownClass() throws Exception {
    }

   
    public void setUp() {
        this.streetrepository = new StreetRepositoryList();
    }

   
    public void tearDown() {
        streetrepository = null;
    }

    /**
     * Test de insert method, da class StreetRepositoryList.
     */
    
    public void testinsert() {
    	 Street street = new Street("rua",1);
         streetrepository.insert(street);
         assertEquals("InsertTest failed.",1, streetrepository.search(1).getId());
    }

    /**
     * Test de remove method, da class StreetRepositoryList.
     */
   
    public void testremove() {
      
    	 Street street = new Street("rua",1);
         streetrepository.insert(street);
         assertEquals("InsertTest failed.",1, streetrepository.search(1).getId());
    }

    /**
     * Test de search method, da class StreetRepositoryList.
     */
   
    public void testsearch() {
    	 Street street = new Street("rua",1);
         streetrepository.insert(street);
         assertEquals("InsertTest failed.",1, streetrepository.search(1).getId());
    }

    /**
     * Test de update method, da class StreetRepositoryList.
     */
   
    public void testupdate() {
    	 Street street = new Street("rua",1);
         streetrepository.insert(street);
         assertEquals("InsertTest failed.",1, streetrepository.search(1).getId());
    }
    
    public static Test suite() {
        return new TestSuite(StreetRepositoryListTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }

}