package sicv.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class XmlWriter {

	private List<Element> placemarkes;

	private String path;
	
	public XmlWriter(String path){
		this.path = path;
		this.placemarkes = new ArrayList<Element>();
	}

	public void clearListPlacemarkers(){
		this.placemarkes.clear();
	}
	
/*	<?xml version="1.0" encoding="UTF-8"?>
	<kml iconherf="">
	<Placemark name="Pan Africa Market" description="1521 1st Ave, Seattle, WA" lat="-8.2" lng="-35.2" />
	<Placemark name="Pan Africa Market" description="2222 2nd Ave, Seattle, WA" lat="-8.0" lng="-35.0" />
	</kml>*/

	public void createPlacemarker(String name, String description,String lat, String lng){


		// cria tag placamarkers
		Element placemark = new Element("Placemark");

		
		placemark.setAttribute("name",name);
		placemark.setAttribute("description",description);
		placemark.setAttribute("lat",lat);
		placemark.setAttribute("lng",lng);
		
		this.placemarkes.add(placemark);


	}
	
	

	public void createXmlDoc(String iconRef){

		Element xml = new Element("xml");

		xml.setAttribute("iconhref",iconRef);
		
		Document doc = new Document();  
		doc.setRootElement(xml);

		FileOutputStream saida;

		try {
			saida = new FileOutputStream(path);
			XMLOutputter xout = new XMLOutputter();
			xout.output(doc, saida);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
