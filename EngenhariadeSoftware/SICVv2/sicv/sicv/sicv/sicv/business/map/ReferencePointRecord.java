package sicv.business.map;

import java.util.Iterator;

import sicv.data.IReferencePointRepository;
import sicv.model.localization.ReferencePoint;

public class ReferencePointRecord {
 
	private IReferencePointRepository referencePointRepository;
	
	public ReferencePointRecord(IReferencePointRepository referencePointRepository) {
		
		this.referencePointRepository = referencePointRepository;
	}
	
	public void insert(ReferencePoint referencePoint) {
		this.referencePointRepository.insert(referencePoint);
	}
	
	public void remove(int id) {
		this.referencePointRepository.remove(id);
	}	
	
	public ReferencePoint search(int id) {
		return this.referencePointRepository.search(id);
	}
	
	public void update(ReferencePoint referencePoint) {
		this.referencePointRepository.update(referencePoint);
	}

	public ReferencePoint search(String referencePoint) {
		return this.referencePointRepository.search(referencePoint);
	}
	
	public Iterator<ReferencePoint> iterator()
	{
		return this.referencePointRepository.iterator();
	}

	
}
 
