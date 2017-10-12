package sicv.business.map;

import java.util.Iterator;

import sicv.data.ICrossRepository;
import sicv.data.IStreetRepository;
import sicv.model.localization.Cross;
import sicv.model.localization.ReferencePoint;
import sicv.model.localization.Street;
import sicv.model.localization.StreetPart;


public class StreetRecord {
 
	private IStreetRepository streetRepository;

	public StreetRecord(ICrossRepository crossRepository,IStreetRepository streetRepository) {
		this.streetRepository = streetRepository;

	}

	public  void insert(Street street)
	{
		this.insert(street);
	}
	
	public Street search(int id){
		return this.streetRepository.search(id);
	}
	
	public  void remove(int id){
		
	}
	public  void update(Street street)
	{
		this.streetRepository.update(street);
		
	}
	public  Street search(String name)
	{
		return this.streetRepository.search(name);
	}
	
	public Iterator<Street> iterator(){
		return this.streetRepository.iterator();
	}

	public Cross getCrossByReferencePoint(String referencePoint) {

		for (Iterator<Street> iterator = streetRepository.iterator(); iterator.hasNext();) {
			Street street =  iterator.next();
			
			for (Iterator<StreetPart> streetParts = street.getStreetParts(); streetParts.hasNext();) {
				StreetPart streetPart = streetParts.next();
				
				for (Iterator<ReferencePoint> referencesPoints = streetPart.getSource().getReferencePoints(); referencesPoints.hasNext();) {
					
					ReferencePoint current = referencesPoints.next();
					if (current.getName().equals(referencePoint)) {
						return streetPart.getSource();
					}
				}
				
				for (Iterator<ReferencePoint> referencesPoints = streetPart.getDestination().getReferencePoints(); referencesPoints.hasNext();) {
					ReferencePoint current = referencesPoints.next();
					if (current.getName().equals(referencePoint)) {
						return streetPart.getDestination();
					}
			
				}
			}
			
		}
		throw new RuntimeException("erro");
	}
	
	 
}
 
