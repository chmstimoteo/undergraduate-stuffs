package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IReferencePointRepository;
import sicv.model.localization.ReferencePoint;

public class ReferencePointRepositoryList implements IReferencePointRepository{
 
	private List<ReferencePoint> referencePoints;
	 
    
	public ReferencePointRepositoryList() {
  	
		this.referencePoints = new ArrayList<ReferencePoint>();
    }
	
	
	
	public void insert(ReferencePoint referencePoint) {
		
		this.referencePoints.add(referencePoint);
		
	}
	
	public void remove(int id) {
		
		for (Iterator<ReferencePoint> iterator = this.referencePoints.iterator(); iterator.hasNext();) {
			  ReferencePoint referencePoint =  iterator.next();	
			  
			  if (referencePoint.getId() == id) {
				  
				  this.referencePoints.remove(referencePoint);
				  return;
			   }
		}
		
	}

	
	public ReferencePoint search(int id) {
		
		
		for (Iterator<ReferencePoint> iterator = this.referencePoints.iterator(); iterator.hasNext();) {
			  ReferencePoint referencePoint =  iterator.next();	
			  
			  if (referencePoint.getId() == id) {
				  
		
				  return referencePoint;
			   }
		}
		
		
		
		throw new RuntimeException("erro");
	}

	
	public void update(ReferencePoint referencePoint) {
		
		this.insert(referencePoint);
		this.remove(referencePoint.getId());
		
	}


	
	public ReferencePoint search(String referencePoint) {
		
		for (Iterator<ReferencePoint> iterator = this.referencePoints.iterator(); iterator.hasNext();) {
			  ReferencePoint current =  iterator.next();	
			  
			  if (current.getName() == referencePoint) {
				  
				  return current;
			   }
		}
		
		
		
		return null;
	}


	
	public Iterator<ReferencePoint> iterator() {
		return this.referencePoints.iterator();
	}
	 
}
 
