package sicv.view.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sicv.business.SicvFacade;
import sicv.view.command.Command;
import sicv.view.command.GetOccurrenceById;
import sicv.view.command.GetOccurrenceList;
import sicv.view.command.GetOccurrencesByAgent;
import sicv.view.command.GetVehicles;
import sicv.view.command.Login;
import sicv.view.command.RegisterOcccurrence;
import sicv.view.command.SearchOccurrenceData;

public class SicvServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4260149512666593230L;
	
	protected SicvFacade facade  = null;
	
		
	private Hashtable<String,Command> commandTable;
	
	


	private static final String CommandLogin                     = "login";
	private static final String CommandGetVehicles               = "getvehicles";
	private static final String CommandGetOccurrenceList         = "GetOccurrenceList";
	private static final String CommandGetOccurrenceData         = "GetOccurrenceData";
	private static final String CommandRegisterOcccurrence		 = "RegisterOccurrence";
	private static final String CommandGetOccurrencesByAgent	 = "GetOccurrencesByAgent";
	private static final String CommandGetOccurrenceById		 ="GetOccurrenceById";
	
	public void init(ServletConfig config) throws ServletException {
		
		this.facade = SicvFacade.getInstance();
		/*
		ForwardingAgent agent = new ForwardingAgent(1,"elli","elli","1");
		ForwardingAgent agent1 = new ForwardingAgent(1,"diego","diego","2");
		
		
		Vehicle vehicle = new Vehicle(true,1,Status.FREE,new Position(1,1),"123");
		Vehicle vehicle1 = new Vehicle(true,2,Status.FREE,new Position(2,2),"123");
		
		try{
			facade.insertEmployee(agent);
			facade.insertEmployee(agent1);
			facade.insertVehicle(vehicle);
			facade.insertVehicle(vehicle1);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		initCommands();
	}

    private void initCommands() {
    	commandTable = new Hashtable<String,Command>();
    	
    	commandTable.put(SicvServlet.CommandLogin, new Login(facade));
    	commandTable.put(SicvServlet.CommandGetVehicles, new GetVehicles(facade));
    	commandTable.put(SicvServlet.CommandGetOccurrenceList, new GetOccurrenceList(facade));
    	commandTable.put(SicvServlet.CommandGetOccurrenceData, new SearchOccurrenceData(facade));
    	commandTable.put(SicvServlet.CommandRegisterOcccurrence, new RegisterOcccurrence(facade));
    	commandTable.put(SicvServlet.CommandGetOccurrencesByAgent, new GetOccurrencesByAgent(facade));
    	commandTable.put(SicvServlet.CommandGetOccurrenceById, new GetOccurrenceById(facade));
    	
    }

    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	handleRequest(request,response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	handleRequest(request,response);
    }
    
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	String operation = request.getParameter("operation");
    	Command command = (Command) commandTable.get(operation);
    	command.setRequest(request);
    	command.setResponse(response);
    	command.executeCommand();
    } 
}
