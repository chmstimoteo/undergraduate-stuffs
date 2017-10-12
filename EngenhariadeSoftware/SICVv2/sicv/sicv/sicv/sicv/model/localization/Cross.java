package sicv.model.localization;

import java.util.Iterator;

import sicv.business.map.ReferencePointRecord;
import sicv.lib.GeographicLimit;

public class Cross {
 
	private int id;
	 
	private GeographicLimit geographicLimit;
	
	private ReferencePointRecord referencePoints;

	public Cross(int id) {
		this.id = id;
	}
	public int getId() {
		
		return this.id;
	}

	
	public Iterator<ReferencePoint> getReferencePoints() {
		return referencePoints.iterator();
	}

	public void setReferencePoints(ReferencePointRecord referencePoints) {
		this.referencePoints = referencePoints;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ReferencePoint getReferencePointByName(String referencePoint)
	{
		return this.referencePoints.search(referencePoint);
	}

	public GeographicLimit getGeographicLimit() {
		return geographicLimit;
	}

	public void setGeographicLimit(int x,int y,int width, int height) {
		this.geographicLimit.setLimits(x, y, width, height);
	}
	

	 
}
 
