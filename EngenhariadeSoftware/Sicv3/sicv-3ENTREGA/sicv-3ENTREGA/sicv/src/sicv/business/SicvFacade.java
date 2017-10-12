package sicv.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import sicv.Constants;
import sicv.lib.exceptions.ObjectAlreadyInsertedException;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.ObjectNotValidException;
import sicv.lib.exceptions.PersistenceMechanismException;
import sicv.lib.exceptions.RepositoryException;
import sicv.lib.exceptions.TransactionException;
import sicv.lib.persistence.HibernatePersistenceMechanism;
import sicv.lib.persistence.IPersistenceMechanism;
import sicv.model.employee.Employee;
import sicv.model.employee.ForwardingAgent;
import sicv.model.occurrence.Occurrence;
import sicv.model.vehicle.Vehicle;
import sicv.util.Status;
import sicv.util.XmlWriter;

public class SicvFacade {

	private static IPersistenceMechanism pm = null;
	private static boolean pmCreated = false;
	private static SicvFacade singleton;
	private SicvFacadeInit fCid;
	
	
	
	
	private SicvFacade() {
		super();
		fCid = new SicvFacadeInit();
		
		
	}
	
	
	static boolean isPersistent() {
		return Constants.isPersistent();
	}
	
	public void insertEmployee(Employee employee) throws ObjectAlreadyInsertedException, ObjectNotValidException, TransactionException{
		this.fCid.insertEmployee(employee);
	}
	
	
	static IPersistenceMechanism pmInit() {
		IPersistenceMechanism returnValue = null;
		if (isPersistent()) {
			try {
				returnValue = HibernatePersistenceMechanism.getInstance();
				
				returnValue.connect();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			} catch (Exception e) {
				
				try {
					if (getPm() != null) {
						getPm().disconnect();
					}
				} catch (PersistenceMechanismException mpe) {
					mpe.printStackTrace();
				}
			}
		}
		return returnValue;
	}
	
	
	
	public Iterator<Occurrence> getOccurrenceInAttendentByAgent(String login) throws RepositoryException, ObjectNotFoundException, TransactionException {
		
	
		ForwardingAgent agent = (ForwardingAgent) this.fCid.searchEmployee(login);
		Iterator<Occurrence> iterator =agent.getOccurrences();
		List<Occurrence> list = new ArrayList<Occurrence>();
	
	
		while (iterator.hasNext()) {
			Occurrence	current	 = iterator.next();
			if (current.getStatus().compareTo(Status.IN_ATTENDANT) == 0 || current.getStatus().compareTo(Status.IN_INITIAL_ATTENDANT) == 0){
				list.add(current);	
			}
			
		}
		
		
		return list.iterator();
	
	}
	
	
	
	
	public static SicvFacade getInstance() {
		if (singleton == null) {
			singleton = new SicvFacade();
		}
		return singleton;
	}
	
	public static IPersistenceMechanism getPm() {
		if (!pmCreated) {
			pm = pmInit();
			if (pm != null) {
				pmCreated = true;
			}
		}
		return pm;
	}
	
	public void insertVehiclesDistancesByOccurrence(Occurrence occurrence ,String vehiclesDistances) {
		//this.fCid.insertVehiclesDistancesByOccurrence(occurrence, vehiclesDistances);
	}
	
	
	
	
	
	public void registerOccurrence(Occurrence occurrence) throws RepositoryException, TransactionException, ObjectNotFoundException, ObjectNotValidException, ObjectAlreadyInsertedException {
			
		this.fCid.registerOccurrence(occurrence);
	}
	
	//formato id;id;id
	private List<Vehicle> convertToVehicles(String vehiclesDistances){
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		
		StringTokenizer tokenizer = new StringTokenizer(";",vehiclesDistances);
		while(tokenizer.hasMoreTokens()){
			int id = Integer.parseInt(tokenizer.nextToken());
			vehicles.add(this.fCid.searchVehicle(id));
		}
		
		return vehicles;
	} 
	
	//formato id;id;id
	public void resgisterVehiclesByOccurrence(Occurrence occurrence,String vehiclesDistances){
		
		Iterator<Vehicle> vehicles = this.convertToVehicles(vehiclesDistances).iterator();
		
		try {
			while(vehicles.hasNext()){
				Vehicle vehicle = vehicles.next();
				vehicle.setOccurrence(occurrence);
				this.fCid.updateVehicle(vehicle);
			}
			
			occurrence.setStatus(Status.IN_ATTENDANT);
			
			this.fCid.updateOcurrence(occurrence);
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Employee searchEmployee(String login) throws RepositoryException, ObjectNotFoundException, TransactionException {
		
		return this.fCid.searchEmployee(login);
	}


	public void insertVehicle(Vehicle vehicle) throws RepositoryException, ObjectAlreadyInsertedException, ObjectNotValidException, TransactionException {
		
		this.fCid.insertVehicle(vehicle);
		
	}


	public Iterator<Vehicle> iteratorVehicles() throws TransactionException {
		
		return this.fCid.iteratorVehicles();
	}


	public void insertOccurrence(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
	
		this.fCid.insertOccurrence(occurrence);
		
	}


	public Occurrence searchOccurrence(int id) throws RepositoryException, ObjectNotFoundException, TransactionException {
		
		return this.fCid.searchOccurrence(id);
	}
	
	
	

}
