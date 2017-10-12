
//Leitura dos pontos de um arquivo XML
var points = new Array(2);
	points[0] = new GLatLng(-8.00,-35.0);
	points[1] = new GLatLng(-8.3,-35.0);

var map;
var dirn;

function recebedados(form){

	var nome = 	form.nome.value;
	var lastnome = document.getElementById("name").value;
	if(nome != lastnome){
		document.getElementById("name").value = nome;
	}
}






function start(){
if (GBrowserIsCompatible()) {

      map = new GMap2(document.getElementById("map"));
      dirn = new GDirections(map);


      GEvent.addListener(dirn,"error", function() {
        alert("Directions Failed: "+dirn.getStatus().code);
      });

            // ========== launch the custom Panel creator a millisecond after the GDirections finishes loading ==========
      // == The delay is required in case we rely on GDirections to perform the initial setCenter ==
      GEvent.addListener(dirn,"load", function() {
        setTimeout("call()", 1);
      });
      
       dirn.loadFromWaypoints(points,{getSteps:true});
	
      
	}
}

function call(){
	
	customPanel(map,"map",dirn,document.getElementById("path"));
}
    
      
      
      // ============ custom direction panel ===============
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





   