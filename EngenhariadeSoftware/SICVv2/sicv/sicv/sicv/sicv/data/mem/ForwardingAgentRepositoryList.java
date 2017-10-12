package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IForwardingAgentRepository;
import sicv.model.forwardinAgent.ForwardingAgent;

public class ForwardingAgentRepositoryList implements IForwardingAgentRepository {
 
	private List<ForwardingAgent> forwardingAgents;

	public ForwardingAgentRepositoryList() {
		this.forwardingAgents = new ArrayList<ForwardingAgent>();
	}
	
	
	public void insert(ForwardingAgent forwardingAgent) {
		
		this.forwardingAgents.add(forwardingAgent);
		
	}

	
	public void remove(int id) {
		for (Iterator<ForwardingAgent> iterator = this.forwardingAgents.iterator(); iterator.hasNext();) {
			ForwardingAgent forwardingAgent =  iterator.next();
			if (forwardingAgent.getId() == id) {
				this.forwardingAgents.remove(forwardingAgent);
				return;
			}
		}
		
	}

	
	public ForwardingAgent search(int id) {
		
		
		for (Iterator<ForwardingAgent> iterator = this.forwardingAgents.iterator(); iterator.hasNext();) {
			ForwardingAgent forwardingAgent =  iterator.next();
			if (forwardingAgent.getId() == id) {
				
				return forwardingAgent;
			}
		}
		
		return null;
	}

	
	public void update(ForwardingAgent forwardingAgent) {
		this.remove(forwardingAgent.getId());
		this.insert(forwardingAgent);
	}

	
	public Iterator<ForwardingAgent> iterator() {
		return this.forwardingAgents.iterator();	}

	
	public boolean exist(int id) {
		for (Iterator<ForwardingAgent> iterator = this.forwardingAgents.iterator(); iterator.hasNext();) {
			ForwardingAgent forwardingAgent =  iterator.next();
			if (forwardingAgent.getId() == id) {
				
				return true;
			}
		}
		
		return false;
	}
	 
}
 
