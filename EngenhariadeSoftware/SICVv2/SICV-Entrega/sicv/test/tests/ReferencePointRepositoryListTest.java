
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.ReferencePointRepositoryList;
import sicv.model.localization.ReferencePoint;


public class ReferencePointRepositoryListTest extends TestCase{

    private ReferencePointRepositoryList referencepointrepository;
    
    public ReferencePointRepositoryListTest() {
    }

    
    public static void setUpClass() throws Exception {
    }

    
    public static void tearDownClass() throws Exception {
    }

   
    public void setUp() {
        this.referencepointrepository = new ReferencePointRepositoryList();
    }

    
    public void tearDown() {
        this.referencepointrepository = null;
    }

    /**
     * Test de insert method, da class ReferencePointRepositoryList.
     */
   
    public void testInsert() {
        String name = "casa da mamae";
        int id = 10;
        ReferencePoint referencepoint = new ReferencePoint(name, id);
         referencepointrepository.insert(referencepoint);
        assertEquals("InsertTest failed.",10, referencepointrepository.search(10).getId());
    }

    /**
     * Test de remove method, da class ReferencePointRepositoryList.
     */
    
    public void testRemove() {
    	String name = "casa da mamae";
        int id = 10;
        ReferencePoint referencepoint = new ReferencePoint(name, id);
         referencepointrepository.insert(referencepoint);
        assertEquals("InsertTest failed.",10, referencepointrepository.search(10).getId());
    }

    /**
     * Test de search method, da class ReferencePointRepositoryList.
     */
   
    public void testSearch() {
    	String name = "casa da mamae";
        int id = 10;
        ReferencePoint referencepoint = new ReferencePoint(name, id);
         referencepointrepository.insert(referencepoint);
        assertEquals("InsertTest failed.",10, referencepointrepository.search(10).getId());
    }

    /**
     * Test de update method, da class ReferencePointRepositoryList.
     */
    
    public void testUpdate() {
    	String name = "casa da mamae";
        int id = 10;
        ReferencePoint referencepoint = new ReferencePoint(name, id);
         referencepointrepository.insert(referencepoint);
        assertEquals("InsertTest failed.",10, referencepointrepository.search(10).getId());
    }
    
    public static Test suite() {
        return new TestSuite(ReferencePointRepositoryListTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }

}