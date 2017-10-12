
var lat;
var lng;

var map;
var dirn;
var baseIcon;
var icon1;
var marker=null;


function send()
{
   var form = document.getElementById("form");
   form.lat.value = lat;
   form.lng.value = lng;
   form.submit();
 //A comunicacao do JavaScript com o Java eh por arquivos XML.  
}

function checkcampos(){
        	var username = document.getElementById("nome").value;
        	var password = document.getElementById("cpf").value;
    		if(username==""){
        		document.getElementById("namewrong").value = "Nome em branco";
        		
    		}
        	if(password==""){
        		document.getElementById("cpfwrong").value = "CPF em branco";
        		
    		}
    		
        	
	    }
	    
	    
function clearPoints(){
	lat = 200;
	lng = 200;
}


      function createMarker(point,icon) {
	    if(!marker){
        marker = new GMarker(point, {draggable:false,icon:icon});
        map.addOverlay(marker);
    	}
        lat = point.x;
       	lng = point.y;
      }

function call(){

if (GBrowserIsCompatible()) {
 
      map = new GMap2(document.getElementById("map"));
      map.setCenter(new GLatLng(-8.050391,-34.94236),12)
      map.addControl(new GLargeMapControl());
      map.addControl(new GMapTypeControl());
      dirn = new GDirections();
	  
	  baseIcon = new GIcon(G_DEFAULT_ICON); 
	  baseIcon.iconSize=new GSize(24,38);
      icon1 = new GIcon(baseIcon,"http://maps.google.com/mapfiles/ms/micons/caution.png");
      //icon1.iconAnchor = new GPoint(6, 20);
      
      GEvent.addListener(map, "click", function(overlay,point) {
        if (!overlay && !marker){
	    	createMarker(point,icon1);
	    	
        }
        if (overlay && marker) {
          map.removeOverlay(overlay);
          marker=null;
          clearPoints();
        }
      });


      GEvent.addListener(dirn,"error", function() {
        GLog.write("Failed: "+dirn.getStatus().code);
      });

    }
    else {
      alert("Sorry, the Google Maps API is not compatible with this browser");
    }
    
    
 }