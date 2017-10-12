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

function ReadRuaAjax(url){

	if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = readRua;
        xmlhttp.open("GET", url, true);
        xmlhttp.send(null);
    // Procura por uma versão ActiveX (IE)
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		if (xmlhttp) {
            xmlhttp.onreadystatechange = readRua;
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

function OnResponseRua(rua){
 
	if(checkReadyState(xmlhttp)){
	document.getElementById('rua').innerHTML = rua;
	}
}

function readRua(){
	if(checkReadyState(xmlhttp)){
	street = xmlhttp.responseText;
	GSearchStreetTemp(street);
	}	
}


function isCompatible(){
	if (GBrowserIsCompatible()) { return true;}
    else { return false;}
}


function GSearchStreetTemp(street){
	OnResponseRua(street);
}

function GSearchStreet(street){
	if(isCompatible()){
			
      var options = {
            zoomControl : GSmapSearchControl.ZOOM_CONTROL_ENABLE_ALL,
            title : "Recife",
            url : "http://maps.google.com/maps?f=q&amp;amp;hl=pt-BR&amp;amp;geocode=&amp;amp;q=Recife&amp;amp;jsv=107&amp;amp;ie=UTF8&amp;amp;ll=-8.046992,-34.881592&amp;amp;spn=0.322265,0.6427&amp;amp;z=11&amp;amp;iwloc=addr",
            idleMapZoom : GSmapSearchControl.ACTIVE_MAP_ZOOM-2,
            activeMapZoom : GSmapSearchControl.ACTIVE_MAP_ZOOM-2
            }

      new GSmapSearchControl(
            document.getElementById("mapsearch"),
            "Av Barbosa Lima 149, Recife, 500",
            options
            );

    }
    
    GSearch.setOnLoadCallback(LoadMapSearchControl);
			
			
	}else{ alert("Sorry, the Google Maps API is not compatible with this browser");}
}
