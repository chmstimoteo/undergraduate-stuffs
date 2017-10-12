
      var step = 5; // metres
      var tick = 100; // milliseconds
      var eol;
      var k=0;
      var stepnum=0;
      var speed = "";
      var temporized = "";
      var positions = [];   
	  var ind = 0;
	  var control=0;
	 
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


var dirn= new GDirections(null, document.getElementById("path"));
var poly;


function carregar(){
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
      icon3 = new GIcon(baseIcon,"http://maps.google.com/mapfiles/ms/micons/caution.png");
      icon3.infoWindowAnchor = new GPoint(5, 1);
     
      
    
      GEvent.addListener(dirn,"load", function() {
        poly=dirn.getPolyline();
        eol=poly.Distance();
        map.setCenter(poly.getVertex(0),17);
        //map.addOverlay(new GMarker(poly.getVertex(0),G_START_ICON));
        map.addOverlay(new GMarker(poly.getVertex(poly.getVertexCount()-1),icon3));
        marker = new GMarker(poly.getVertex(0),{icon:icon1});
        map.addOverlay(marker);
        map.addOverlay(poly);
        var steptext = dirn.getRoute(0).getStep(stepnum).getDescriptionHtml();
        document.getElementById("step").innerHTML = steptext;
        setTimeout("call()", 1);
        setTimeout("animate(0)",2000);  // Allow time for the initial map display
      });
      
      setTimeout('start()',10000);
	}
    // display a warning if the browser was not compatible
    else {
      alert("Sorry, the Google Maps API is not compatible with this browser");
    }
}

      function animate(d) {
        if (d>eol){
          if(state==1){
	      document.getElementById("step").innerHTML = "<b>Trip completed</b><br>" + positions;
          document.getElementById("distance").innerHTML =  "Kilometros: "+(d/1000.00).toFixed(2);
      	  }
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
            step = stepspeed/10.8;     				// fiz regra de tres ja que estava 2.5  acima estava 2.24
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

      
      function start() {
	      //Leitura dos dados do arquivo XML
        //var startpoint = "-8.059336,-34.893758";
        //var endpoint = "-8.063882,-34.887643";
        
        dirn.loadFromWaypoints(["-8.059336,-34.893758","-8.063882,-34.887643"],{getPolyline:true,getSteps:true});
      }
      
      
function call(){
	
	customPanel(map,"map",dirn,document.getElementById("path"));
}

      function customPanel(map,mapname,dirn,div) {
        var html = "";
      
        // ===== local functions =====

        // === waypoint banner ===
        function waypoint(point, type, address) {
          var target = '"' + mapname+".showMapBlowup(new GLatLng("+point.toUrlValue(6)+"))"  +'"';
          html += '<table style="border: 1px solid silver; margin: 10px 0px; background-color: rgb(238, 238, 238); border-collapse: collapse; color: rgb(0, 0, 0);">';
          html += '  <tr style="cursor: pointer;" onclick='+target+'>';
          html += '    <td style="padding: 4px 15px 0px 5px; vertical-align: middle; width: 20px;">';
          html += '      <img src="http://www.google.com/intl/en_ALL/mapfiles/icon-dd-' +type+ '-trans.png">'
          html += '    </td>';
          html += '    <td style="vertical-align: middle; width: 100%;">';
          html +=        address;
          html += '    </td>';
          html += '  </tr>';
          html += '</table>';
        }

        // === route distance ===
        function routeDistance(dist) {
          html += '<div style="text-align: left; padding-bottom: 0.3em;">' + dist + '</div>';
        }      

        // === step detail ===
        function detail(point, num, description, dist) {
          var target = '"' + mapname+".showMapBlowup(new GLatLng("+point.toUrlValue(6)+"))"  +'"';
          html += '<table style="margin: 0px; padding: 0px; border-collapse: collapse;">';
          html += '  <tr style="cursor: pointer;" onclick='+target+'>';
          html += '    <td style="border-top: 1px solid rgb(205, 205, 205); margin: 0px; padding: 0.3em 3px; vertical-align: top; text-align: right;">';
          html += '      <a href="javascript:void(0)"> '+num+'. </a>';
          html += '    </td>';
          html += '    <td style="border-top: 1px solid rgb(205, 205, 205); margin: 0px; padding: 0.3em 3px; vertical-align: top; width: 100%;">';
          html +=        description;
          html += '    </td>';
          html += '    <td style="border-top: 1px solid rgb(205, 205, 205); margin: 0px; padding: 0.3em 3px 0.3em 0.5em; vertical-align: top; text-align: right;">';
          html +=        dist;
          html += '    </td>';
          html += '  </tr>';
          html += '</table>';
        }

        // === Copyright tag ===
        function copyright(text) {
          html += '<div style="font-size: 0.86em;">' + text + "</div>";
        }
        

        // === read through the GRoutes and GSteps ===

        for (var i=0; i<dirn.getNumRoutes(); i++) {
          if (i==0) {
            var type="play";
          } else {
            var type="pause";
          }
          var route = dirn.getRoute(i);
          var geocode = route.getStartGeocode();
          var point = route.getStep(0).getLatLng();
          // === Waypoint at the start of each GRoute
          waypoint(point, type, geocode.address);
          routeDistance(route.getDistance().html+" (about "+route.getDuration().html+")");

          for (var j=0; j<route.getNumSteps(); j++) {
            var step = route.getStep(j);
            // === detail lines for each step ===
            detail(step.getLatLng(), j+1, step.getDescriptionHtml(), step.getDistance().html);
          }
        }
        
        // === the final destination waypoint ===   
        var geocode = route.getEndGeocode();
        var point = route.getEndLatLng();
        waypoint(point, "stop", geocode.address);
                 
        // === the copyright text ===
        copyright(dirn.getCopyrightsHtml());

        // === drop the whole thing into the target div
        div.innerHTML = html;

} // ============ end of customPanel function ===========
      
      
      
// === A method for testing if a point is inside a polygon
// === Returns true if poly contains point
// === Algorithm shamelessly stolen from http://alienryderflex.com/polygon/ 
GPolygon.prototype.Contains = function(point) {
  var j=0;
  var oddNodes = false;
  var x = point.lng();
  var y = point.lat();
  for (var i=0; i < this.getVertexCount(); i++) {
    j++;
    if (j == this.getVertexCount()) {j = 0;}
    if (((this.getVertex(i).lat() < y) && (this.getVertex(j).lat() >= y))
    || ((this.getVertex(j).lat() < y) && (this.getVertex(i).lat() >= y))) {
      if ( this.getVertex(i).lng() + (y - this.getVertex(i).lat())
      /  (this.getVertex(j).lat()-this.getVertex(i).lat())
      *  (this.getVertex(j).lng() - this.getVertex(i).lng())<x ) {
        oddNodes = !oddNodes
      }
    }
  }
  return oddNodes;
}

// === A method which returns the approximate area of a non-intersecting polygon in square metres ===
// === It doesn't fully account for spechical geometry, so will be inaccurate for large polygons ===
// === The polygon must not intersect itself ===
GPolygon.prototype.Area = function() {
  var a = 0;
  var j = 0;
  var b = this.Bounds();
  var x0 = b.getSouthWest().lng();
  var y0 = b.getSouthWest().lat();
  for (var i=0; i < this.getVertexCount(); i++) {
    j++;
    if (j == this.getVertexCount()) {j = 0;}
    var x1 = this.getVertex(i).distanceFrom(new GLatLng(this.getVertex(i).lat(),x0));
    var x2 = this.getVertex(j).distanceFrom(new GLatLng(this.getVertex(j).lat(),x0));
    var y1 = this.getVertex(i).distanceFrom(new GLatLng(y0,this.getVertex(i).lng()));
    var y2 = this.getVertex(j).distanceFrom(new GLatLng(y0,this.getVertex(j).lng()));
    a += x1*y2 - x2*y1;
  }
  return Math.abs(a * 0.5);
}

// === A method which returns the length of a path in metres ===
GPolygon.prototype.Distance = function() {
  var dist = 0;
  for (var i=1; i < this.getVertexCount(); i++) {
    dist += this.getVertex(i).distanceFrom(this.getVertex(i-1));
  }
  return dist;
}


// === A method which returns the bounds as a GLatLngBounds ===
GPolygon.prototype.Bounds = function() {
  var bounds = new GLatLngBounds();
  for (var i=0; i < this.getVertexCount(); i++) {
    bounds.extend(this.getVertex(i));
  }
  return bounds;
}

// === A method which returns a GLatLng of a point a given distance along the path ===
// === Returns null if the path is shorter than the specified distance ===
GPolygon.prototype.GetPointAtDistance = function(metres) {
  // some awkward special cases
  if (metres == 0) return this.getVertex(0);
  if (metres < 0) return null;
  var dist=0;
  var olddist=0;
  for (var i=1; (i < this.getVertexCount() && dist < metres); i++) {
    olddist = dist;
    dist += this.getVertex(i).distanceFrom(this.getVertex(i-1));
  }
  if (dist < metres) {return null;}
  var p1= this.getVertex(i-2);
  var p2= this.getVertex(i-1);
  var m = (metres-olddist)/(dist-olddist);
  return new GLatLng( p1.lat() + (p2.lat()-p1.lat())*m, p1.lng() + (p2.lng()-p1.lng())*m);
}

// === A method which returns the Vertex number at a given distance along the path ===
// === Returns null if the path is shorter than the specified distance ===
GPolygon.prototype.GetIndexAtDistance = function(metres) {
  // some awkward special cases
  if (metres == 0) return this.getVertex(0);
  if (metres < 0) return null;
  var dist=0;
  var olddist=0;
  for (var i=1; (i < this.getVertexCount() && dist < metres); i++) {
    olddist = dist;
    dist += this.getVertex(i).distanceFrom(this.getVertex(i-1));
  }
  if (dist < metres) {return null;}
  return i;
}

// === A function which returns the bearing between two vertices in decgrees from 0 to 360===
// === If v1 is null, it returns the bearing between the first and last vertex ===
// === If v1 is present but v2 is null, returns the bearing from v1 to the next vertex ===
// === If either vertex is out of range, returns void ===
GPolygon.prototype.Bearing = function(v1,v2) {
  if (v1 == null) {
    v1 = 0;
    v2 = this.getVertexCount()-1;
  } else if (v2 ==  null) {
    v2 = v1+1;
  }
  if ((v1 < 0) || (v1 >= this.getVertexCount()) || (v2 < 0) || (v2 >= this.getVertexCount())) {
    return;
  }
  var from = this.getVertex(v1);
  var to = this.getVertex(v2);
  if (from.equals(to)) {
    return 0;
  }
  var lat1 = from.latRadians();
  var lon1 = from.lngRadians();
  var lat2 = to.latRadians();
  var lon2 = to.lngRadians();
  var angle = - Math.atan2( Math.sin( lon1 - lon2 ) * Math.cos( lat2 ), Math.cos( lat1 ) * Math.sin( lat2 ) - Math.sin( lat1 ) * Math.cos( lat2 ) * Math.cos( lon1 - lon2 ) );
  if ( angle < 0.0 ) angle  += Math.PI * 2.0;
  angle = angle * 180.0 / Math.PI;
  return parseFloat(angle.toFixed(1));
}




// === Copy all the above functions to GPolyline ===
GPolyline.prototype.Contains             = GPolygon.prototype.Contains;
GPolyline.prototype.Area                 = GPolygon.prototype.Area;
GPolyline.prototype.Distance             = GPolygon.prototype.Distance;

GPolyline.prototype.Bounds               = GPolygon.prototype.Bounds;
GPolyline.prototype.GetPointAtDistance   = GPolygon.prototype.GetPointAtDistance;
GPolyline.prototype.GetIndexAtDistance   = GPolygon.prototype.GetIndexAtDistance;
GPolyline.prototype.Bearing              = GPolygon.prototype.Bearing;