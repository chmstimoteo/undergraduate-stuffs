package sicv.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import sicv.business.SicvFacade;
import sicv.lib.exceptions.TransactionException;
import sicv.model.vehicle.Vehicle;
import sicv.util.Position;
import sicv.util.Status;

public class GetVehicles extends Command {

	public GetVehicles(SicvFacade f) {
		super(f);
		
	}

	@Override
	public void executeCommand() {
		
		
		PrintWriter out = null;

		response.setContentType("text/html");

		try {
			out = response.getWriter();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}

		
		try {
		/*	facade.insertVehicle(new Vehicle(true,1,Status.BUSY,new Position(1,1),"1"));
			facade.insertVehicle(new Vehicle(true,2,Status.BUSY,new Position(2,2),"2"));
			facade.insertVehicle(new Vehicle(true,3,Status.BUSY,new Position(3,3),"3"));
			
			*/
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		Iterator<Vehicle> v = null;
		try {
			v = facade.iteratorVehicles();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		while(v.hasNext()) 
		{
			out.println("Vehicle position:" +v.next().getPosition());
		}
		
		
	}

	
}
