package testes;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.business.Employee.EmployeeRecord;
import sicv.data.rdb.EmployeeRepositoryRDB;
import sicv.model.employee.ForwardingAgent;

public class EmployeeRecordTest extends TestCase{
	
private EmployeeRecord  employeeRecord;
    
    public EmployeeRecordTest() {
    }


    public static void setUpClass() throws Exception {
    }


    public static void tearDownClass() throws Exception {
    }

  
    public void setUp() {
       this.employeeRecord =  new EmployeeRecord(new  EmployeeRepositoryRDB());
    }

   
    public void tearDown() {
      
    }

    /**
     * Test de insert method, da class OccurenceRepositoryList.
     */
   
    public void tesInsert() {
    	int id = 1;
    	String name = "rafael"; 
    	String login = "rafaprax"; 
    	String password = "2808";
    	ForwardingAgent forwanding = new ForwardingAgent(id,name, login, password);
    	
    	//this.employeeRecord.insert(forwanding);
        //assertEquals("InsertTest failed.", id, employeeRecord.search(id).getId());
    }

    /**
     * Test de remove method, da class OccurenceRepositoryList.
     */
   
    /*public void testRemove() {
    	int id = 1;
    	String name = "rafael"; 
    	String login = "rafaprax"; 
    	String password = "2808";
    	ForwardingAgent forwanding = new ForwardingAgent(id,name, login, password);
    	
    	this.employeeRecord.insert(forwanding);
    	this.employeeRecord.remove(id);
         assertEquals("InsertTest failed.", false, employeeRecord.exist(id));
    }*/

    /**
     * Test de search method, da class OccurenceRepositoryList.
     */
   
    public void testSearch() {
    	int id = 1;
    	String name = "rafael"; 
    	String login = "rafaprax"; 
    	String password = "2808";
    	ForwardingAgent forwanding = new ForwardingAgent(id,name, login, password);
    	
    	//this.employeeRecord.insert(forwanding);
    	 //assertEquals("searchTest failed.", id, employeeRecord.search(id).getId());
    	//assertEquals("searchTest failed.", id, employeeRecord.search(id).getId());
    }
    

    /**
     * Test de update method, da class OccurenceRepositoryList.
     */
    
    public void testUpdate() {
    	int id = 1;
    	String name = "rafael"; 
    	String login = "rafaprax"; 
    	String password = "2808";
    	ForwardingAgent forwanding = new ForwardingAgent(id,name, login, password);
    	//this.employeeRecord.insert(forwanding);
    	
    	String nameUpdate = "praxa";
    	ForwardingAgent forwandingUpdate = new ForwardingAgent(id,nameUpdate, login, password);
    	
    	//this.employeeRecord.update(forwandingUpdate);
    	
       // assertEquals("updateTest failed.",nameUpdate, employeeRecord.search(id).getName());
    }
    
    public static Test suite() {
        return new TestSuite(EmployeeRecordTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }


}
