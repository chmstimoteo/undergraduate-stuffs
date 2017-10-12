package sicv.model.employee;

import java.util.Vector;

import sicv.Constants;
import sicv.model.occurrence.Occurrence;
import sicv.util.Status;

public class ForwardingAgent extends Employee {
	
	private Vector<Occurrence> occurrences;
	
	public ForwardingAgent(int id, String name, String login, String password) {
		super(id, name, login, password);
		
	}
 
	public void  registerOccurrence(Occurrence occurrence) {
			this.occurrences.add(occurrence);
			if (this.occurrences.size() == Constants.MAX_OCCURRENCES_BY_AGENT) {
				this.setStatus(Status.BUSY);
			}
	}
	
	public void  closeOccurrence(int id) {
		for (Occurrence current : this.occurrences) {
			if (current.getId() == id) {
				this.occurrences.remove(current);
				this.setStatus(Status.FREE);
				break;
			}
		}
	}
	
	
	 
}
 
