package sicv.util;

public class Position {

	private float lat;
	private float lng;
	
	
	public Position(float lat, float lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	
	public String printInTag(){
		String tag = "";
		tag = "<markers>" + "\n\t" + "<marker> lat= \"" + this.getLat() +
			  "\" lng= \"" + this.getLat() + "\" </marker>" + "\n" + "</markers>";
		return tag;
	}
	
}
