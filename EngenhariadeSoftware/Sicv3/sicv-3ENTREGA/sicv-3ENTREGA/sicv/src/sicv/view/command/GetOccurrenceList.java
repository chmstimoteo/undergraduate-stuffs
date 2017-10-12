package sicv.view.command;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import sicv.Constants;
import sicv.business.SicvFacade;
import sicv.lib.exceptions.InvalidSessionException;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.HTMLCode;
import sicv.util.Position;
import sicv.util.Status;


public class GetOccurrenceList extends Command {

	public GetOccurrenceList(SicvFacade f) {
		super(f);
		
	}
	
	
	public void executeCommand() {
			PrintWriter out= null;
			try {
			out = response.getWriter();

	    	HttpSession session = request.getSession(false);
	    	response.setContentType("text/html");

	        
	        
	        	if (session == null) {
	                throw new InvalidSessionException();
	            }
	        	
	        	
	        	
	        	
	        	out.println(HTMLCode.open("Queries - Complaint information"));
	            out.println("<body><h1>Queries:<br>Querie about complaint</h1>");
	            out.println("<p>Choose a complaint: </p>");
	            //out.println("<form method=\"POST\" action=\""+Constants.SYSTEM_ACTION+"?operation=UpdateComplaintSearch\">");
	             
	            ForwardingAgent agent =  ((ForwardingAgent)session.getValue(Login.EMPLOYEE));
	            
	            System.out.println("agent = " + agent);
	            
	            Occurrence oc = new Occurrence(Priority.LOW,Status.FREE,new Position(0,0));
	            facade.insertOccurrence(oc);
	            
	            agent.registerOccurrence(facade.searchOccurrence(oc.getId()));
	            
	            Iterator<Occurrence> iterator = agent.getOccurrences();

	            if (iterator==null||!iterator.hasNext()) {                
	                out.println("<p><center><font color=\"red\"><b> There isn't any occurrence.</b></font></center></p>");
	            } else {
	            	out.println("<div align=\"center\"><center><p><select name=\"numQueixa\" size=\"1\">");            	
	                Occurrence o;
	                do {
	                    o = (Occurrence) iterator.next();
	                    out.println("<option value=\""      +
	                                o.getId() + "\"> " + 
	                                o.getStatus().toString()      +
	                                " </OPTION>");                    
	                } while (iterator.hasNext());
	                
	                
	                out.println("</select></p></center></div>");
	                out.println("  <div align=\"center\"><center><p><input type=\"submit\" value=\"Search\" name=\"B1\"  onclick=\""+Constants.SYSTEM_ACTION+"?operation=UpdateComplaintSearch\"></p></center></div></form>");
	                
	            
	            }
	            out.println(HTMLCode.closeAdministrator());            
	        } catch(InvalidSessionException e){
	        	out.println(HTMLCode.errorPageAdministrator(e.getMessage()));
	        }catch(IOException e){
	        	out.println(HTMLCode.errorPageQueries(e.getMessage()));
	        }catch(Exception e){
	        	out.println(HTMLCode.errorPageQueries(e.getMessage()));
	        } finally {
	            out.close();
	        }
		}

	}
