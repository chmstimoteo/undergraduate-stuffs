package sicv.view.command;



import java.io.IOException;
import java.io.PrintWriter;

import sicv.business.SicvFacade;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.model.occurrence.Occurrence;
import sicv.util.HTMLCode;



public class SearchOccurrenceData extends Command {

	public SearchOccurrenceData(SicvFacade f) {
		super(f);
		
	}
	
	
	public void executeCommand() {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		response.setContentType("text/html");

		int kindCode = Integer.parseInt(request
				.getParameter("codTipoDoenca"));

		try {
			Occurrence occurrence = facade.searchOccurrence(kindCode);

			out.println(HTMLCode.open("Queries - Diseases"));
			out.println("<body><h1>Querie result<br>Disease</h1>");

			out.println("<P><h3>Id: " + occurrence.getId() + "</h3></P>");
			out.println("<P>How manifests: " + occurrence.getPosition().getLat() + " </P>");
			out.println("<P>Duration: " + occurrence.getPosition().getLng() + " </P>");
			out.println("<P>Duration: " + occurrence.getStatus().toString() + " </P>");
			out.println("<P>Symptoms: </P>");

			
			out.println(HTMLCode.closeQueries());

		} catch (ObjectNotFoundException e) {
			out.println(HTMLCode.errorPage(e.getMessage()));
		} catch (Exception e) {
			out.println(HTMLCode.errorPage(e.getMessage()));
		} finally {
			out.close();
		}
	}
}