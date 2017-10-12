
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.ForwardingAgentRepositoryList;
import sicv.model.forwardinAgent.ForwardingAgent;


public class ForwardingAgentRepositoryListTest extends TestCase{

    
    private ForwardingAgentRepositoryList forwardingrepository;
    
    public ForwardingAgentRepositoryListTest() {
    }

   
    public static void setUpClass() throws Exception {
    }

   
    public static void tearDownClass() throws Exception {
    }

    
    public void setUp() {
        forwardingrepository = new ForwardingAgentRepositoryList();
    }

   
    public void tearDown() {
        forwardingrepository = null;
    }

    /**
     * Test de insert method, da class ForwardingAgentRepositoryList.
     */
   
    public void testSearch() {
    	 ForwardingAgent forwardingAgent = new ForwardingAgent(1,"a","b","c");
         this.forwardingrepository.insert(forwardingAgent); 
         
         assertEquals("InsertTest failed.","b",this.forwardingrepository.search(1).getLogin());
    }
    
    public void testInsert() {
        ForwardingAgent forwardingAgent = new ForwardingAgent(1,"a","b","c");
        this.forwardingrepository.insert(forwardingAgent); 
        
        assertEquals("InsertTest failed.",true,this.forwardingrepository.exist(1));
    }

    /**
     * Test de remove method, da class ForwardingAgentRepositoryList.
     */
    
    /**
     * Test de search method, da class ForwardingAgentRepositoryList.
     */
   
   
    
    public void testRemove() {
    	ForwardingAgent forwardingAgent = new ForwardingAgent(1,"a","b","c");
        this.forwardingrepository.insert(forwardingAgent); 
        this.forwardingrepository.remove(1); 
        assertEquals("InsertTest failed.",false,this.forwardingrepository.exist(1));
    }

    

    /**
     * Test de update method, da class ForwardingAgentRepositoryList.
     */
   
    public void testUpdate() {
    	ForwardingAgent forwardingAgent = new ForwardingAgent(1,"a","b","c");
        this.forwardingrepository.insert(forwardingAgent); 
        this.forwardingrepository.update(new ForwardingAgent(1,"c","a","b"));
        
        assertEquals("InsertTest failed.","a",this.forwardingrepository.search(1).getLogin());
    }
    
    public static Test suite() {
        return new TestSuite(ForwardingAgentRepositoryListTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }

}