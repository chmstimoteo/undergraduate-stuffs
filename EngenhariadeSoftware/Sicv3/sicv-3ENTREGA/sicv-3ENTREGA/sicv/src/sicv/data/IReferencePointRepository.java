package sicv.data;

import java.util.Iterator;

import sicv.model.location.ReferencePoint;

public interface IReferencePointRepository {
 
	public abstract void insert(ReferencePoint  referencePoint);
	public abstract ReferencePoint search(int id);
	public abstract void remove(int id);
	public abstract void update(ReferencePoint  referencePoint);
	public abstract ReferencePoint search(String referencePoint);
	public abstract Iterator<ReferencePoint> iterator();
}
 
