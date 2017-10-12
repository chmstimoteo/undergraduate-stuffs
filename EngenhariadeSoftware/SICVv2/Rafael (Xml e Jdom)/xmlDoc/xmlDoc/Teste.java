package xmlDoc;

public class Teste {
	
	public static void main(String[] args){
		XmlDoc doc = new XmlDoc();
		
		doc.createStyle("restaurantStyle", "restuarantIcon", "http://maps.google.com/mapfiles/kml/pal2/icon63.png");
		
		doc.createPlacemarker("placemark1", "Pan Africa Market", "1521 1st Ave, Seattle, WA"
				, "-122.340141,47.608940", "restaurantStyle");
		doc.createPlacemarker("placemark2", "Pan Africa Market", "2222 2nd Ave, Seattle, WA",
				"-122.356445,47.624561", "restaurantStyle");
		
		doc.createXmlDoc();
		
	}
}
