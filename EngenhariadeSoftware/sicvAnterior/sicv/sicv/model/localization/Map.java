package sicv.model.localization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import sicv.business.map.GridController;
import sicv.lib.Graph;
import sicv.lib.Position;

public class Map {
 
	private RM rmr;
	private GridController gridController; 
	private Graph streetMap;
	 
	
	
	public Map() {
		super();
		this.rmr = new RM();
		this.gridController = new GridController();
		
	}

	public Cross getCrossByLocation(Localization localization) {
		
		return this.rmr.getCrossByLocalization(localization);
	}
	 
	public Cross getCrossByPosition(Position position) {
		
		return this.gridController.searchCrossByPosition(position);
	}
	 
		
	public int getDistance(Cross source,Cross destination)
	{
		return this.streetMap.getDistance(source.getId(), destination.getId());
	}
	
	public List<Integer> getDistances(Cross source, List<Cross> crossDestinations) {
		
		
		List<Integer> distances = new ArrayList<Integer>();
		
		for (Iterator<Cross> iterator = crossDestinations.iterator(); iterator.hasNext();) {
			distances.add(this.getDistance(source, iterator.next()));			
		}
		
		return distances;
	}
	
	public void loadMap()
	{
		List<StreetPart> streetParts = this.rmr.getStreetParts();
		
		 Collections.sort(streetParts); 
		 
		 int nCross = streetParts.get(0).getId();
		 
		 streetMap = new Graph(nCross);
		 
		 for (Iterator<StreetPart> iterator = streetParts.iterator(); iterator.hasNext();) {
			StreetPart streetPart =  iterator.next();
			streetMap.insertEdge(streetPart.getSource().getId(), streetPart.getDestination().getId(), streetPart.getLenght()/streetPart.getMaxVelocity(),true);		
		}
		 
		 
	}
	 
}
 
