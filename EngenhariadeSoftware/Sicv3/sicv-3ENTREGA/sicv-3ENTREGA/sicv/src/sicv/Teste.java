package sicv;

import java.io.PrintWriter;

import sicv.business.SicvFacade;
import sicv.data.rdb.EmployeeRepositoryRDB;
import sicv.data.rdb.OccurrenceRepositotyRDB;
import sicv.lib.exceptions.ObjectAlreadyInsertedException;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;
import sicv.util.Position;
import sicv.util.Status;

public class Teste {

	public static void main(String[] args) {
		
		ForwardingAgent agent = new ForwardingAgent(1,"elli","elli","1");
		ForwardingAgent agent1 = new ForwardingAgent(1,"diego","diego","2");
		
		
		Vehicle vehicle = new Vehicle(true,1,Status.FREE,new Position(1,1),"123");
		Vehicle vehicle1 = new Vehicle(true,2,Status.FREE,new Position(2,2),"123");
		
		SicvFacade facade = SicvFacade.getInstance();
		
		
		
		try {
			facade.insertEmployee(agent);
			facade.insertEmployee(agent1);
			facade.insertVehicle(vehicle);
			facade.insertVehicle(vehicle1);
		} catch (ObjectAlreadyInsertedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectNotValidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
