package sicv.business;

import sicv.data.IForwardingAgentRepository;
import sicv.model.forwardinAgent.ForwardingAgent;

public class ForwardingAgentRecord {
 
	private IForwardingAgentRepository forwardingAgentRepository;

	public ForwardingAgentRecord(IForwardingAgentRepository forwardingAgentRepository) {
	
		this.forwardingAgentRepository = forwardingAgentRepository;
	}
	
	
	public void insert(ForwardingAgent  forwardingAgent) {
		this.forwardingAgentRepository.insert(forwardingAgent);

	}
	
	public void remove(int id) {
		this.forwardingAgentRepository.remove(id);
	}
	
	
	public ForwardingAgent search(int id) {
		return this.forwardingAgentRepository.search(id);
	}
	
	public void update(ForwardingAgent forwardingAgent) {
		this.forwardingAgentRepository.update(forwardingAgent);
	}
}
 
