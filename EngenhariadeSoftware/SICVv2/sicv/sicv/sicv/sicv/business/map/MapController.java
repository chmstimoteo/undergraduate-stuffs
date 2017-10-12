package sicv.business.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.lib.Position;
import sicv.model.localization.Cross;
import sicv.model.localization.Localization;
import sicv.model.localization.Map;

public class MapController {
 
	private Map map;
	
	
	public MapController() {
	
		this.map = new Map();
		this.map.loadMap();
	}
	public List<Integer> getDistances(Localization sourceLocalization,List<Position> destinations) {
		
		
		List<Cross> crossDestinations = this.getCrossesByPositions(destinations);
		Cross source = this.getCrossByLocalization(sourceLocalization);
		return this.map.getDistances(source, crossDestinations);
		
	}
	
	
	public Cross getCrossByLocalization(Localization localization) {
		
		 return this.map.getCrossByLocation(localization);
	}
	
	public Cross localizeCrossByPosition(Position position) {
		
		return this.map.getCrossByPosition(position);
	}
	
		
   public List<Cross> getCrossesByPositions(List<Position> positions) {
		
	     List<Cross> crosses = new ArrayList<Cross>();
	   
		for (Iterator<Position> iterator = positions.iterator(); iterator.hasNext();) {
			  crosses.add(this.localizeCrossByPosition(iterator.next()));
			
		}
	 return crosses;
   }
   
   
   
	
}
 
