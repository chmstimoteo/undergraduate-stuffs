package sicv.util;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position implements Serializable{

	
	@Id
	private double lat;
	private double lng;
	
	
	
	
	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLng() {
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}


	public Position(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}


	public Position() {
		
	}
	

	public String printInTag(){
		String tag = "";
		tag = "<markers>" + "\n\t" + "<marker> lat= \"" + this.getLat() +
			  "\" lng= \"" + this.getLat() + "\" </marker>" + "\n" + "</markers>";
		return tag;
	}
	
}
