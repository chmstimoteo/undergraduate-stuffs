package sicv.model.localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.Constants;
import sicv.business.map.StreetRecord;


public class Neighborhood {
 
	private String name;
	 	
	private StreetRecord streetRecord;
	 
	
	public Neighborhood() {

	}
	
	public Cross getCrossByLocalization(Localization localization) {
		
		String referencePoint = localization.getReferencePoint();
		
		if (localization.getReferencePoint().equals(Constants.NOT_INFORMED)) {
			
			return this.streetRecord.search(localization.getStreet()).getReferenceCross();
		}else{
			return streetRecord.getCrossByReferencePoint(referencePoint);
		}
		
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<StreetPart> getStreetParts() {
	
		List<StreetPart> streetParts = new ArrayList<StreetPart>();
		for (Iterator<Street> iterator = this.streetRecord.iterator(); iterator.hasNext();) {
			Street street = iterator.next();
			for (Iterator<StreetPart> iterator2 = street.getStreetParts(); iterator2.hasNext();) {
					streetParts.add(iterator2.next());
			}
			
		}
		
		return streetParts;
	}
	
	
	 
}
 
