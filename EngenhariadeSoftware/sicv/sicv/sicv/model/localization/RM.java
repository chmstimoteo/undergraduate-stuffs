package sicv.model.localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RM {
 
	private String name;
	 
	private List<City> cities;
	
	
	 
	
	
	public RM() {
	
		 
	}
	
	
	
	public List<City> getCities() {
		return this.getCities();
	}
	
	public List<StreetPart> getStreetParts(){
		
		List<StreetPart> streetParts = new ArrayList<StreetPart>();
		for (Iterator<City> iterator = cities.iterator(); iterator.hasNext();) {
			City city = iterator.next();
			streetParts.addAll(city.getStreetParts());
		}
		
		return streetParts;
	}
	
	public Cross getCrossByLocalization(Localization localization)
	{
		String city = localization.getCity();
		for (Iterator<City> iterator = cities.iterator(); iterator.hasNext();) {
			City current =  iterator.next();
			
			if( current.getName().equals( city ) )
				 return current.getCrossByLocation(localization);
		}
		
		throw new RuntimeException("Problem");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	
}
 
