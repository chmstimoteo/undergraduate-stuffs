package sicv.view.command;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import sicv.Constants;
import sicv.business.SicvFacade;
import sicv.model.employee.Employee;
import sicv.model.employee.PoliceOffice;
import sicv.util.HTMLCode;
import sicv.util.Library;


public class Login extends Command {

	private String[] keywords = { "##SYSTEM_ROOT##", "##SERVLET_SERVER_PATH##",
			"##CLOSE##", "##SYSTEM_ACTION##" };

	private String[] newWords = { Constants.SYSTEM_ROOT,
			Constants.SERVLET_SERVER_PATH, HTMLCode.closeAdministrator(), Constants.SYSTEM_ACTION };

	public static  final String EMPLOYEE = "employee";

	public Login(SicvFacade f) {
		super(f);
	
	}
	
	public void executeCommand() {
		PrintWriter out = null;
        HttpSession session = request.getSession(true);
        
        response.setContentType("text/html");

        try {
        	out = response.getWriter();
        } catch (Exception e) {
        	e.printStackTrace();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");        

        try {
        	
        
        	
        	Employee employee = facade.searchEmployee(login);
        
        	if (employee.validatePassword(password)) {
            	
                session.putValue(Login.EMPLOYEE, employee);
                String type = "Agent";                                              
                if(employee instanceof PoliceOffice){
                	type = "PoliceOffice";
                }
               
                out.println(Library.getFileListReplace(keywords, newWords, Constants.FORM_PATH+"Menu"+type+".html"));                
            
            } else {                                 
                out.println(HTMLCode.errorPage("Invalid password! <br><a href=\""+Constants.SYSTEM_LOGIN+"\">Try again</a>"));
            
            }
            
        } catch (FileNotFoundException e) {
            out.println(HTMLCode.errorPage(e.getMessage()));
		} catch (Exception e) {
        	out.println(HTMLCode.errorPage(e.getMessage()));
		} finally {           
            out.close();
        }
	}
}