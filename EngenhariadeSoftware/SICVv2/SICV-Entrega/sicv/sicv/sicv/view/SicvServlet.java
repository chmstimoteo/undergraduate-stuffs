package sicv.view;

import java.io.IOException;

import sicv.business.SicvFacade;

public class SicvServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1168831505312561567L;
	
	private SicvFacade facade;
	
	
	public void init(ServletConfig config) throws ServletException {
     
            facade = SicvFacade.getInstance(); 
       
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		
		try {
			int  lat = Integer.parseInt(request.getParameter("lat"));
			int  lng = Integer.parseInt(request.getParameter("lng"));
			//String priority = request.getParameter("priority");
			
			response.getWriter().println("Ocorrencia Registrada.<br>Nossos despachantes ja estao enviando uma viatura.");
			//this.facade.registerOccurrence(new Occurrence(null,null,new Position(lat,lng),0));
		} catch (Exception e) {
			response.getWriter().println("Ocorrencia Registrada.<br>Nossos despachantes ja estao enviando uma viatura...");
		}
    	
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			response.getWriter().println("lat=" + lat + "lng = " + lng);
		} catch (Exception e) {
			response.getWriter().println("pegou!!!");
		}
    }

}
