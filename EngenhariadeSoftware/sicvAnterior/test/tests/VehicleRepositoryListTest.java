
package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sicv.data.mem.VehicleRepositoryList;
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;


public class VehicleRepositoryListTest extends TestCase{

    private VehicleRepositoryList vehiclerepository;
            
    public VehicleRepositoryListTest() {
    }

  
    public static void setUpClass() throws Exception {
    }

   
    public static void tearDownClass() throws Exception {
    }

   
    public void setUp() {
        vehiclerepository = new VehicleRepositoryList();
    }


    public void tearDown() {
        vehiclerepository = null;
    }

    /**
     * Test de insert method, da class VehicleRepositoryList.
     */
   
    public void testinsert() {
       Vehicle vehicle = new Vehicle(true,Priority.LOW,1);
       vehiclerepository.insert(vehicle);
        assertEquals("InsertTest failed.",Priority.LOW, vehiclerepository.search(1).getPriority());
    }

    /**
     * Test de remove method, da class VehicleRepositoryList.
     */
   
    public void testremove() {
    	Vehicle vehicle = new Vehicle(true,Priority.LOW,1);
        vehiclerepository.insert(vehicle);
        vehiclerepository.remove(1);
         assertEquals("InsertTest failed.",false, vehiclerepository.exist(1));
    }

    /**
     * Test de search method, da class VehicleRepositoryList.
     */
  
    public void testsearch() {
    	Vehicle vehicle = new Vehicle(true,Priority.LOW,1);
        vehiclerepository.insert(vehicle);
         assertEquals("InsertTest failed.",Priority.LOW, vehiclerepository.search(1).getPriority());
    }

    /**
     * Test de update method, da class VehicleRepositoryList.
     */
   
    public void testUpdate() {
    	Vehicle vehicle = new Vehicle(true,Priority.LOW,1);
        vehiclerepository.insert(vehicle);
         assertEquals("InsertTest failed.",Priority.LOW, vehiclerepository.search(1).getPriority());
    }
    
    public static Test suite() {
        return new TestSuite(VehicleRepositoryListTest.class);
        //junit.textui.TestRunner.run(new TestSuite(GraphTest.class));
    }

}