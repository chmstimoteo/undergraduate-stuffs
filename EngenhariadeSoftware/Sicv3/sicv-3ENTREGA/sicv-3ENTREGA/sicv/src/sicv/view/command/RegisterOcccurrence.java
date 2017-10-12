package sicv.view.command;

import java.io.IOException;
import java.io.PrintWriter;

import sicv.business.SicvFacade;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class RegisterOcccurrence extends Command {

	public RegisterOcccurrence(SicvFacade f) {
		super(f);
		
	}

	@Override
	public void executeCommand() {
		Occurrence occurrence;
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		String priorityStr = request.getParameter("priority");
		Priority priority = covertStringToPriority(priorityStr);
		
		occurrence = 
		 new Occurrence(priority,Status.NOT_REGISTRED,new Position(lat,lng)); 
		
		
		
		try {
			facade.registerOccurrence(occurrence);
			out.println("Thats 's right");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
	}
	
	
	private Priority covertStringToPriority(String p) {
		
		
		Priority out = null;
		
		if(p.equals("grave"))
		{
			out = Priority.HIGH;
		}else if(p.equals("media")){
			
			out = Priority.AVERAGE;
			
		}else  if(p.equals("baixa")){
			
			out = Priority.LOW;
			
		}else{
			
		out = Priority.LOW;
		}
		
		return out;
	}

}
