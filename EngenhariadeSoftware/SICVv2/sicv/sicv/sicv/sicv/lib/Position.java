package sicv.lib;

import java.awt.geom.Point2D;

public class Position extends Point2D {

	private double x;
	private double y; 
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
	
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
	
		this.x = x;
		this.y = y;
		
	}

	
	
	
}
