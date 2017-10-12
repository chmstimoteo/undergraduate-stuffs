package testes;

import junit.framework.TestCase;
import sicv.data.jpa.VehicleRepositoryJPA;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.TransactionException;
import sicv.lib.persistence.HibernatePersistenceMechanism;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.vehicle.Vehicle;
import sicv.util.Status;

public class VehicleRepositoryTest extends TestCase {

	public void testInsertVehicle()
	{
	
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		VehicleRepositoryJPA er = new VehicleRepositoryJPA(mp);
		
		Vehicle vehicle = new Vehicle(1,true,Status.BUSY,1);
		
		
		try {
			mp.beginTransaction();
			er.insert(vehicle);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Vehicle vehicleSearch = (Vehicle) er.search(vehicle.getId());
			mp.commitTransaction();
			
			assertEquals("Fail",vehicle.getDescription(),vehicleSearch.getDescription());
			assertEquals("Fail",vehicle.getPosition(),vehicleSearch.getPosition());
			assertEquals("Fail",vehicle.getId(),vehicleSearch.getId());
			
		} catch (Exception e) {
			try {
				mp.rollbackTransaction();
			} catch (TransactionException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		
	}
	
	
	public void testUpdateVehicle()
	{
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VehicleRepositoryJPA er = new VehicleRepositoryJPA(mp);
		
		Vehicle vehicle = 
			new Vehicle(1,true,Status.BUSY,2);
	   
		
		try {
			mp.beginTransaction();
			er.update(vehicle);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Vehicle vehicleSearch = (Vehicle) er.search(vehicle.getId()); 
			mp.commitTransaction();
			
			assertEquals("Fail",vehicle.getDescription(),vehicleSearch.getDescription());
			assertEquals("Fail",vehicle.getPosition(),vehicleSearch.getPosition());
			assertEquals("Fail",vehicle.getId(),vehicleSearch.getId());
			
			
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
	
	
	public void testRemoveVehicle() {
		
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VehicleRepositoryJPA ar = new VehicleRepositoryJPA(mp);
		
		
		try {
			
			 mp.beginTransaction();
		     ar.remove(1); 
		     mp.commitTransaction();
			
		     mp.beginTransaction();
		     boolean exist = ar.exist(1); 
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
	
	
	public void  testExitsVehicle() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VehicleRepositoryJPA er = new VehicleRepositoryJPA(mp);
		
		
		try {
			
			Vehicle vehicle =new Vehicle(1,true,Status.BUSY,2);
			
			mp.beginTransaction();
			er.insert(vehicle);
			mp.commitTransaction();
			
			mp.beginTransaction();
			boolean exist =  er.exist(vehicle.getId());
			mp.commitTransaction();
			
			assertEquals("Fail", true,exist	);
			
			
			
			
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
	
	public void testSearchVehicle() {
		
		IPersistenceMechanism mp = null;
		
		try {
			mp = HibernatePersistenceMechanism.getInstance();
			mp.connect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		VehicleRepositoryJPA er = new VehicleRepositoryJPA(mp);
		
		Vehicle vehicle = new Vehicle(1,true,Status.BUSY,3);
		
		
		try {
			mp.beginTransaction();
			er.insert(vehicle);
			mp.commitTransaction();
			
			mp.beginTransaction();
			Vehicle vehicleSearch = (Vehicle) er.search(vehicle.getId());
			mp.commitTransaction();
			
			assertEquals("Fail",vehicle.getDescription(),vehicleSearch.getDescription());
			assertEquals("Fail",vehicle.getPosition(),vehicleSearch.getPosition());
			assertEquals("Fail",vehicle.getId(),vehicleSearch.getId());
			
			
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
