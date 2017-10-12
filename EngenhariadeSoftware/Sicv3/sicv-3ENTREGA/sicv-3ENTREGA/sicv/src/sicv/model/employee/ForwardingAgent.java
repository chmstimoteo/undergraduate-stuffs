package sicv.model.employee;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import sicv.Constants;
import sicv.model.occurrence.Occurrence;
import sicv.util.Status;

@Entity
@DiscriminatorValue("FA")
public class ForwardingAgent extends Employee {
	
	@OneToMany
	private List<Occurrence> occurrences;
	
	public ForwardingAgent(int id, String name, String login, String password) {
		super(id, name, login, password);
		this.occurrences = new Vector<Occurrence>();
		
	}
 
	public ForwardingAgent() {
		super();
	}
	
	/**Esse metodo so e chamdo se o atendente estiver livre*/
	public void  registerOccurrence(Occurrence occurrence) {
			
			Iterator<Occurrence> iterator = this.occurrences.iterator();
			int inAttendant = 0;
			while (iterator.hasNext()) {
				Occurrence	current	 = iterator.next();
				if (current.getStatus().compareTo(Status.IN_ATTENDANT) == 0 || current.getStatus().compareTo(Status.IN_INITIAL_ATTENDANT) == 0){
					inAttendant++;
				}
				
			}
			if (inAttendant == Constants.MAX_OCCURRENCES_BY_AGENT-1) {
				this.setStatus(Status.BUSY);
			}
			this.occurrences.add(occurrence);
			
	}
	
	public void  closeOccurrence(int id) {
		for (Occurrence current : this.occurrences) {
			if (current.getId() == id) {
				this.setStatus(Status.FREE);
				break;
			}
		}
	}

	public Occurrence getOccurrence() {
		
		 return occurrences.get(0);
	}

	public Iterator<Occurrence> getOccurrences() {
		
		return this.occurrences.iterator();
	}

	
	
	
	 
}
 
