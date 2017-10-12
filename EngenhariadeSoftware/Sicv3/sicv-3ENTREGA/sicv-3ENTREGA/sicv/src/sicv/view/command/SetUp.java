package sicv.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import sicv.business.SicvFacade;
import sicv.lib.exceptions.ObjectAlreadyInsertedException;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;
import sicv.util.Position;
import sicv.util.Status;

public class SetUp extends Command {

	public SetUp(SicvFacade f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeCommand() {
		// TODO Auto-generated method stub

		ForwardingAgent agent = new ForwardingAgent(1,"d","i","1");

		
		Vehicle vehicle = new Vehicle(true,3,Status.FREE,new Position(3,3),"3");
		Occurrence o = new Occurrence(Priority.HIGH,Status.NOT_REGISTRED,new Position(0,0));
		PrintWriter out ;
		
		try {
			
			
			out = response.getWriter();
			facade.insertEmployee(agent);
			facade.insertVehicle(vehicle);
			facade.registerOccurrence(o);
			
			agent = (ForwardingAgent) facade.searchEmployee("i");
			
			Iterator<Occurrence> i = agent.getOccurrences();
			
			out.println("p1");
			
			
			while(i.hasNext())
			{
				o = i.next();
				out.println(o.getId());
				out.println(o.getStatus());
				out.println();
			}
			
			out.println("p2");
			
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
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
