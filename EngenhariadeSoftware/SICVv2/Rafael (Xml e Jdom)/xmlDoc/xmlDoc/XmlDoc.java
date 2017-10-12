package xmlDoc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class XmlDoc {

	private List<Element> placemarkes;
	private List<Element> Styles;

	public XmlDoc(){
		this.placemarkes = new ArrayList<Element>();
		this.Styles = new ArrayList<Element>();
	}

	public void clearListPlacemarkers(){
		this.placemarkes.clear();
	}
	
	public void clearListStyles(){
		this.Styles.clear();
	}
	
	public void createStyle( String idStyle, String iconStyleId,
			String hrefIcon){

	
//		cria tag Style para ser usado no StyleUrl do placemarker
		Element style = new Element("Style");
		style.setAttribute("id",idStyle);

		Element iconStyle = new Element("IconStyle");
		iconStyle.setAttribute("id",iconStyleId);

		Element icon = new Element("Icon");
		Element href = new Element("href");
		href.addContent(hrefIcon);
		icon.addContent(href);

		style.addContent(iconStyle);
		style.addContent(icon);

//		//adiciona a lista de elements
		this.Styles.add(style);
	}

	public void createPlacemarker(String idPlacemarker, String name, String description, 
			String coordinates, String styleUrl){


		// cria tag placamarkers
		Element placemark = new Element("Placemark");
		placemark.setAttribute("id", idPlacemarker);

		Element name0 = new Element("name");
		name0.addContent(name);

		Element description0 = new Element("description");
		description0.addContent(description);

		Element styleUrl0 = new Element("styleUrl");
		styleUrl0.addContent("#"+styleUrl);

		Element point = new Element("Point");
		Element coordinates0 = new Element("coordinates");
		coordinates0.addContent(coordinates);

		point.addContent(coordinates0);

		//adicinonando elementos a tag placemark

		placemark.addContent(name0);
		placemark.addContent(description0);
		placemark.addContent(styleUrl0);
		placemark.addContent(point);


		//adiciona a lista de elements

		this.placemarkes.add(placemark);


	}

	public void createXmlDoc(){

		Element kml = new Element("kml");
		/*kml.setAttribute("xmlns","http://earth.google.com/kml/2.1" );*/

		Element document = new Element("Document");
		
		document.addContent(this.Styles);
		document.addContent(this.placemarkes);
		kml.addContent(document);

//		Criando o documento XML (montando)  
		Document doc = new Document();  
		doc.setRootElement(kml);

		FileOutputStream saida;

		try {
			saida = new FileOutputStream("E:\\JDom\\xmlTeste\\xml\\kml.xml");
			XMLOutputter xout = new XMLOutputter();
			xout.output(doc, saida);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
