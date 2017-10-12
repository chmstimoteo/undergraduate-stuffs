package sicv.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import sicv.Constants;
import sicv.business.Employee.EmployeeController;
import sicv.business.occurrence.OccurrenceController;
import sicv.business.vehicle.VehicleController;
import sicv.data.mem.EmployeeRepositoryList;
import sicv.data.mem.OccurenceRepositoryList;
import sicv.data.mem.VehicleRepositoryList;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;
import sicv.model.vehicle.Vehicle;
import sicv.util.Status;
import sicv.util.XmlWriter;

public class SicvFacade {

	private OccurrenceController occurrenceController;
	private EmployeeController employeeController;
	private VehicleController vehicleController;


	
	
	private static SicvFacade facade;
	
	
	private SicvFacade() {
		super();
		this.occurrenceController = new OccurrenceController(new OccurenceRepositoryList());
		this.employeeController = new EmployeeController(new EmployeeRepositoryList());
		this.vehicleController = new VehicleController(new VehicleRepositoryList());
		
	}
	
	public static SicvFacade getInstance() {
		if (facade == null) {
			facade = new SicvFacade();
		}
		return facade;
	}
	
	public void insertVehiclesDistancesByOccurrence(Occurrence occurrence ,String vehiclesDistances) {
		this.occurrenceController.insertVehiclesDistancesByOccurrence(occurrence, vehiclesDistances);
	}
	
	public String getVehiclesPositions(){
		return "";
	}
	
	public void createXmlPositionFile(){
		XmlWriter writer = new XmlWriter(Constants.PATH_XML_POSITION);
		
		Iterator<Vehicle> vehicles = this.vehicleController.iterator();
		while(vehicles.hasNext()){
			Vehicle vehicle = vehicles.next();
			writer.createPlacemarker(vehicle.getPlate(),vehicle.getDescription(),vehicle.getLat()+"", vehicle.getLng()+"");
		}
		
		writer.createXmlDoc(Constants.ICON_HREF);
		
	}
	
	public void registerOccurrence(Occurrence occurrence) {
			
		
		if (this.vehicleController.hasVehiclesFreeByPriority(occurrence.getPriority())
				&& this.employeeController.hasForwardingAgentFree()){
			ForwardingAgent agent = this.employeeController.getForwardingAgentFree();
			
			if (this.occurrenceController.hasOccurrenceWaiting()) {
				Occurrence lastOccurrence = this.occurrenceController.getNextOccurrence();
				if (occurrence.getPriority().compareTo(lastOccurrence.getPriority()) > 0) {
					
						occurrence.setStatus(Status.IN_INITIAL_ATTENDANT);
						agent.registerOccurrence(occurrence);
						this.employeeController.upadte(agent);
						this.occurrenceController.insert(occurrence);
				}else{
					lastOccurrence.setStatus(Status.IN_INITIAL_ATTENDANT);
					agent.registerOccurrence(lastOccurrence);
					this.employeeController.upadte(agent);
					
					occurrence.setStatus(Status.WAITING);
					this.occurrenceController.update(occurrence);
				}
				
			}
		}else{
			occurrence.setStatus(Status.WAITING);
			this.occurrenceController.insert(occurrence);
		}
	}
	
	//formato id;id;id
	private List<Vehicle> convertToVehicles(String vehiclesDistances){
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		
		StringTokenizer tokenizer = new StringTokenizer(";",vehiclesDistances);
		while(tokenizer.hasMoreTokens()){
			int id = Integer.parseInt(tokenizer.nextToken());
			vehicles.add(this.vehicleController.search(id));
		}
		
		return vehicles;
	} 
	
	//formato id;id;id
	public void resgisterVehiclesByOccurrence(Occurrence occurrence,String vehiclesDistances){
		
		Iterator<Vehicle> vehicles = this.convertToVehicles(vehiclesDistances).iterator();
		
		while(vehicles.hasNext()){
			Vehicle vehicle = vehicles.next();
			vehicle.setOccurrence(occurrence);
			this.vehicleController.update(vehicle);
		}
		occurrence.setStatus(Status.IN_ATTENDANT);
		this.occurrenceController.update(occurrence);
	}
	
	
	

}
