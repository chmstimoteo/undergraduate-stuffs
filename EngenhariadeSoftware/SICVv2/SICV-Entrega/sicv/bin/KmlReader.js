
	function KmlReader(url){
		

		this.url = url;
		this.getVehiclesPositions = 	function(){
		
		var positions = [];
				GDownloadUrl(url, function(requestData) {
			          var kmlfile = GXml.parse(requestData);
			          var markers = xml.documentElement.getElementsByTagName("Placemark");
			          
			          for (var i = 0; i < element.length; i++) {
				          				         	
				            var lat = parseFloat(markers[i].getAttribute("lat"));
				            var lng = parseFloat(markers[i].getAttribute("lng")));
				            
				            this.positions[i] = new GLatLng(lat,lng);
			          }
			        });
			        
			return this.positions;	        
		}
	
	
	}








try{
    xmlhttp = new XMLHttpRequest();
//if (http_request.overrideMimeType) {
//http_request.overrideMimeType('text/xml'); }
    
    }catch(ee){
    try{
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    }catch(e){
        try{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }catch(E){
            xmlhttp = false;
        	
		}
    }
}


function ReadVehiclePositionXml(url){   //URL do .xml e a SAtring do Marcador KML a ser lida

GDownloadUrl(url, function(data) {
          var xml = GXml.parse(data);
          var element = xml.documentElement.getElementsByTagName("marker");
          for (var i = 0; i < element.length; i++) {
            var elementValue1 = parseFloat(markers[i].getAttribute("lat"));
            var elementValue2 = parseFloat(markers[i].getAttribute("lng")));
          }
        });
        
        return [elementValue1, elementValue2]
        
 }
 
 
 function ReadVehiclePositionXmlAjax(url){
 		if (window.XMLHttpRequest) {
       	 	xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = ReadVehiclePositionXml_;
        	xmlhttp.open("GET", url, true);
        	xmlhttp.send(null);
    	// Procura por uma versão ActiveX (IE)
    	} else if (window.ActiveXObject) {
        	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			if (xmlhttp) {
            	xmlhttp.onreadystatechange = ReadVehiclePositionXml_;
            	xmlhttp.open("GET", url, true);
            	xmlhttp.send();
        	}
    	}return true;
}
 
 
 function checkReadyState(obj){ 
   
   if(obj.readyState == 4){ 
		if(obj.status == 200) return true;
		else return false;
	} return false;
}


function OnResponseReadVehiclePositionXmlAjax_(){
	if(checkReadyState(xmlhttp)){
	document.getElementById('Position').innerHTML = ReadVehiclePositionXml(xmlhttp.getUrl());
	}
}



function ReadVehiclePositionXmlAjax_(){
	if(checkReadyState(xmlhttp)){
		street = xmlhttp.responseText;
		GSearchStreetTemp(street);
	}	
}
