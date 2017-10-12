package sicv.data;

import java.util.Iterator;

import sicv.model.forwardinAgent.ForwardingAgent;

public interface IForwardingAgentRepository {
 
	public abstract void insert(ForwardingAgent forwardingAgent);
	public abstract ForwardingAgent search(int id);
	public abstract void remove(int id);
	public abstract void update(ForwardingAgent forwardingAgent);
	public abstract Iterator<ForwardingAgent> iterator();
	public abstract boolean exist(int id);
}
 
