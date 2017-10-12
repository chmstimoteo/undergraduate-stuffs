
// ====== Array for decoding the failure codes ======
      var reasons=[];
      reasons[G_GEO_SUCCESS]            = "Success";
      reasons[G_GEO_MISSING_ADDRESS]    = "Missing Address: The address was either missing or had no value.";
      reasons[G_GEO_UNKNOWN_ADDRESS]    = "Unknown Address:  No corresponding geographic location could be found for the specified address.";
      reasons[G_GEO_UNAVAILABLE_ADDRESS]= "Unavailable Address:  The geocode for the given address cannot be returned due to legal or contractual reasons.";
      reasons[G_GEO_BAD_KEY]            = "Bad Key: The API key is either invalid or does not match the domain for which it was given";
      reasons[G_GEO_TOO_MANY_QUERIES]   = "Too Many Queries: The daily geocoding quota for this site has been exceeded.";
      reasons[G_GEO_SERVER_ERROR]       = "Server error: The geocoding request could not be successfully processed.";
      reasons[G_GEO_BAD_REQUEST]        = "A directions request could not be successfully parsed.";
      reasons[G_GEO_MISSING_QUERY]      = "No query was specified in the input.";
      reasons[G_GEO_UNKNOWN_DIRECTIONS] = "The GDirections object could not compute directions between the points.";
      
var map;
var bounds;
var geo;
var baseIcon;
var icon1;
var icon2;
var icon3;
var icon4;
// ===== Array to contain the points of the path =====
var path = [];
var active = [true,false,false,false,true];
var gmarkers = [];
var addresses = [];

// ===== State Driven Processing =====
var state = 0;


var gdir;
var poly;

function start(){
if (GBrowserIsCompatible()) { 

      map = new GMap(document.getElementById("map"));
      map.addControl(new GLargeMapControl());
      map.addControl(new GMapTypeControl());
      map.setCenter(new GLatLng(-8.050391,-34.94236),11);

      bounds = new GLatLngBounds();
      
      // ====== Create a Client Geocoder ======
      geo = new GClientGeocoder(new GGeocodeCache()); 
      
      
      baseIcon = new GIcon(G_DEFAULT_ICON);
      baseIcon.iconSize=new GSize(24,38);

      icon1 = new GIcon(baseIcon,"http://maps.google.com/mapfiles/ms/micons/cabs.png");
      icon1.iconAnchor = new GPoint(6, 20);
      icon2 = G_PAUSE_ICON;
      icon3 = new GIcon(baseIcon,"http://maps.google.com/mapfiles/ms/micons/caution.png");
      icon3.infoWindowAnchor = new GPoint(5, 1);

            
      handleState();

      GEvent.addListener(map, "click", function(overlay,point) {
        if (point) {
          if (state == 1) { doEnd(point) }
          if (state == 0) { doStart(point) }
        }
      });

      
      gdir=new GDirections(null, document.getElementById("path"));

      
      GEvent.addListener(gdir,"error", function() {
        var code = gdir.getStatus().code;
        var reason="Code "+code;
        if (reasons[code]) {
          reason = "Code "+code +" : "+reasons[code]
        } 
        alert("Failed to obtain directions, "+reason);
      });

      
      GEvent.addListener(gdir, "load", function() {
        if (poly) map.removeOverlay(poly);
        poly = gdir.getPolyline();
        map.addOverlay(poly);
      });
        
  }
    // display a warning if the browser was not compatible
    else {
      alert("Sorry, the Google Maps API is not compatible with this browser");
    }
      
}

      
      // ====== Geocoding ======
function showAddress() {
        if (state==0) {
          var search = document.getElementById("search").value;
          addresses[0] = search;
        }
        if (state==1) {
          var search = document.getElementById("search2").value;
          addresses[4] = search;
        }
        geo.getLatLng(search, function (point)
          { 
            if (point) {
              if (state==1) {doEnd(point)}
              if (state==0) {doStart(point)}
            }
            else {
              var result=geo.getCache().get(search);
              if (result) {
                var reason="Code "+result.Status.code;
                if (reasons[result.Status.code]) {
                  reason = reasons[result.Status.code]
                }
              } else {
                var reason = "";
              } 
              alert('Could not find "'+search+ '" ' + reason);
            }
          }
        );
	}

//Comandos para o arrasto.
      function swapMarkers(i) {
        map.removeOverlay(gmarkers[i]);
        createMarker(path[i],i,icon2);
      }

      function createMarker(point,i,icon) {
        var marker = new GMarker(point, {draggable:true,icon:icon});
        gmarkers[i]=marker;
        
        GEvent.addListener(marker, "dragend", function() {
          path[i] = marker.getPoint();
          if (!active[i]) {
            setTimeout('swapMarkers('+i+')', 1000);
          }
          active[i] = true;
          addresses[i] = "";
        });
        map.addOverlay(marker);
      }
//End Comandos para o arrasto.
      

function carregarPosicaoGPS(){

	//Ler do arquivo as posicoes.
	animate(0);	
}

function carregarform(){
	//Leitura do arquivo para preencher o formulario.
}

function animate(d){
		if (d>eol) {
          document.getElementById("step").innerHTML = "<b>Trip completed</b><br>" + positions;
          document.getElementById("distance").innerHTML =  "Kilometros: "+(d/1000.00).toFixed(2);
          return ;
        }
        var p = poly.GetPointAtDistance(d);
        if (k++>=180/step) {
          map.panTo(p);
          k=0;
        }
        
        if(control==5){
        	positions[ind] = "new Position" + p;
        	ind++;
        	control=0;
    	}else{ control++;}
    	
        marker.setPoint(p); //modifica a posicao do carro.
        document.getElementById("distance").innerHTML =  "Kilometres: "+(d/1000.00).toFixed(2)+speed +temporized;
        if (stepnum+1 < dirn.getRoute(0).getNumSteps()) {
          if (dirn.getRoute(0).getStep(stepnum).getPolylineIndex() < poly.GetIndexAtDistance(d)) {
            stepnum++;
            var steptext = dirn.getRoute(0).getStep(stepnum).getDescriptionHtml();
            document.getElementById("step").innerHTML = "<b>Next:</b> "+steptext;
            var stepdist = dirn.getRoute(0).getStep(stepnum-1).getDistance().meters;
            var steptime = dirn.getRoute(0).getStep(stepnum-1).getDuration().seconds;
            var stepspeed = ((stepdist/steptime) * 3.6).toFixed(0);
            var totaltime = ((dirn.getDuration().seconds)/60).toFixed(0);
            var distatual = d/1000.00;
            var elapsedtime = ((d / eol) * totaltime).toFixed(0);
            step = stepspeed/0.9;     				// fiz regra de tres ja que estava 2.5  acima estava 2.24
            speed = "<br>Velocidade: " + stepspeed +" km/h";
            temporized = "<br>Tempo Restante: " + (totaltime - elapsedtime).toFixed(0)+" min"; 
          }
        } else {
          if (dirn.getRoute(0).getStep(stepnum).getPolylineIndex() < poly.GetIndexAtDistance(d)) {
            document.getElementById("step").innerHTML = "<b>Next: Muito proximo do destino.</b>";
          }
        }
        setTimeout("animate("+(d+step)+")", tick);
}


//Configura o HTML para mostrar o interesse.
      function handleState() {
        if (state == 0) {
	      document.getElementById("path").style.display = "none";
          document.getElementById("form").style.display = "none";
	      startCar();
          
        }
        if (state == 1) {
          //Chegou a ocorrencia, modifica o caminho para continuar animate.
	      document.getElementById("path").style.display = "block";
          document.getElementById("form").style.display = "block";
          carregarform();
          ;
        }
                                                               
      }
//End Configura o HTML para mostrar o interesse.
      


	function startCar(){
		
		
		
	}


      function doStart(point) {
        createMarker(point,0,icon1);
        path[0] = point;
        state = 1;
        handleState();
      }
//Monta o array de pontos intermediarios, ao clicar no ponto destino. Alem disso, prepara o mapa para mostrar a ocorrencia com mais detalhes.
      function doEnd(point) {
        createMarker(point,4,icon3);
        path[4] = point;
        state = 2;
        handleState();
        for (var i=1; i<4; i++) {
          var lat = (path[0].lat()*(4-i) + path[4].lat()*i)/4;
          var lng = (path[0].lng()*(4-i) + path[4].lng()*i)/4;
          var p = new GLatLng(lat,lng);
          createMarker(p,i,icon4);
          path[i] = p;
        }
        bounds.extend(path[0]);
        bounds.extend(path[4]);
        map.setZoom(map.getBoundsZoomLevel(bounds));
        map.setCenter(bounds.getCenter());
      }
//End doEnd()
      
//Monta o objeto de Direcoes a partir dos pontos do caminho.
      function directions() {
        if (addresses[0]) {var a = addresses[0] + "@" + path[0].toUrlValue(6)}
          else {var a = path[0].toUrlValue(6)} 
        if (addresses[4]) {var b = addresses[4] + "@" + path[4].toUrlValue(6)}
          else {var b = path[4].toUrlValue(6)} 
        for (var i=3; i>0; i--) {
          if (active[i]) {
            b = path[i].toUrlValue(6) +" to: "+b;
          }
        }
        var a = "from: "+a + " to: " + b;
        gdir.load(a, {getPolyline:true});
      }
//End directions
    