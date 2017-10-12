package sicv.view.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sicv.business.SicvFacade;



 
public abstract class Command {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SicvFacade facade;
	
	public Command(SicvFacade f){
		facade= f;
	}
	
	public abstract void executeCommand();
	
	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	
	
}
