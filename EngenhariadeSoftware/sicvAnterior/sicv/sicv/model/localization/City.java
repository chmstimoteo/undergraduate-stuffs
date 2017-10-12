package sicv.model.localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class City {
 
	private String name;
	 
	private List<Neighborhood> neighborhoods;


	 
	public City() {
		

	}
	
	public List<Neighborhood> getNeighborhoods() {
		return this.neighborhoods;
	}
	 
	public String getName()
	{
		return name;
	}


	
	public Cross getCrossByLocation(Localization localization) {
		
		
		String neighborhood = localization.getNeighborhood();
		for (Iterator<Neighborhood> iterator = this.neighborhoods.iterator(); iterator.hasNext();) {
			Neighborhood current = (Neighborhood) iterator.next();
			if (current.getName().equals(neighborhood)) {
				return current.getCrossByLocalization(localization);		
				}
		}
		throw new RuntimeException("erro");
	}

	public List<StreetPart> getStreetParts() {
	
		List<StreetPart> streetParts = new ArrayList<StreetPart>();
		for (Iterator<Neighborhood> iterator = this.neighborhoods.iterator(); iterator.hasNext();) {
			Neighborhood neighborhood = iterator.next();
			streetParts.addAll(neighborhood.getStreetParts());
		}
		
		return streetParts;
	}
}
 
