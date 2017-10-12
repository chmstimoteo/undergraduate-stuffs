package sicv.view.servlets;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sicv.Constants;
import sicv.util.HTMLCode;
import sicv.util.Library;




public class SicvWebServer extends SicvServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3486272116279028353L;

	private String[] keywords = { "##SYSTEM_ROOT##", "##SYSTEM_ACTION##",
			"##SERVLET_SERVER_PATH##", "##CLOSE##" };

	private String[] newWords = { Constants.SYSTEM_ROOT,
			Constants.SYSTEM_ACTION, Constants.SERVLET_SERVER_PATH,
			HTMLCode.closeAdministrator() };

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 PrintWriter out = null;

         response.setContentType("text/html");

         String file = request.getParameter("file");
         
         if (file == null) file = "index.html";
         
         try {
 			out = response.getWriter();
 		} catch (IOException e) {
 		
 			e.printStackTrace();
 		}
         
         try {
 			out.println(Library.getFileListReplace(keywords, newWords, Constants.FORM_PATH+file));
 		} catch (FileNotFoundException e) {
 			
 			e.printStackTrace();
 		}
         out.close();
        
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	PrintWriter out = null;

        response.setContentType("text/html");

        String file = request.getParameter("file");

        try {
			out = response.getWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        try {
			out.println(Library.getFileListReplace(keywords, newWords, Constants.FORM_PATH+file));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
        out.close();
       
    }
}