package sicv.lib;

import java.awt.geom.Rectangle2D;

public class GeographicLimit {

	private Rectangle2D rectangle; 
	
	public boolean contains(Position position) {
		return this.rectangle.contains(position.getX(), position.getY());
	}
	
	public void setLimits(double x,double y,double width,double height)
	{
		this.rectangle.setRect(x, y, width, height);
	}
}
