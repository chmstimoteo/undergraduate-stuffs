package sicv.view.command;

import java.io.PrintWriter;

import sicv.business.SicvFacade;
import sicv.model.occurrence.Occurrence;

public class GetOccurrenceById extends Command {

	public GetOccurrenceById(SicvFacade f) {
		super(f);
		
	}

	@Override
	public void executeCommand() {
		
		int id;
		Occurrence occurrence;
		PrintWriter out;
		String text;
		
		try {
			out = response.getWriter();
			id =  Integer.parseInt(request.getParameter("id"));
			occurrence = facade.searchOccurrence(id);
			
			double lat = occurrence.getPosition().getLat();
			double lng = occurrence.getPosition().getLng();
			
			
			text = +lat+":"+lng+":"+occurrence.getPriority()+":"+occurrence.getStatus();
			
			out.print(text);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
	}

}
