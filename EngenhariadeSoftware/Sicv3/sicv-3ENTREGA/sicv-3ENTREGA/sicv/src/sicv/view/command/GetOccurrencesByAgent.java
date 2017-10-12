package sicv.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import sicv.business.SicvFacade;
import sicv.lib.exceptions.InvalidSessionException;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;

public class GetOccurrencesByAgent extends Command {

	public GetOccurrencesByAgent(SicvFacade f) {
		super(f);
		
	}

	@Override
	public void executeCommand() {
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession(false);
    	response.setContentType("text/html");

        
        
        if (session == null) {
                try {
					throw new InvalidSessionException();
				} catch (InvalidSessionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }else{
        	

		
		ForwardingAgent agent =  ((ForwardingAgent)session.getValue(Login.EMPLOYEE));
		
		String idStr = "ok";
		
		try {
			Iterator<Occurrence>  i = facade.getOccurrenceInAttendentByAgent(agent.getLogin());
		
			while(i.hasNext())
			{
			   idStr += ":" + i.next().getId() ;	

			}
			

			
		
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		
		
		out.print(idStr);
		
		
       }
		

	}
	
	

}
