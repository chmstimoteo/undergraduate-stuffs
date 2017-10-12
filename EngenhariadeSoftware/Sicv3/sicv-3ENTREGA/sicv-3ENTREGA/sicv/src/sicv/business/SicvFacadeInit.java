package sicv.business;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import sicv.Constants;
import sicv.business.Employee.EmployeeController;
import sicv.business.occurrence.OccurrenceController;
import sicv.business.vehicle.VehicleController;
import sicv.data.jpa.EmployeeRepositoryJPA;
import sicv.data.jpa.OccurrenceRepositoryJPA;
import sicv.data.jpa.VehicleRepositoryJPA;
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
import sicv.model.occurrence.Priority;
import sicv.model.vehicle.Vehicle;
import sicv.util.Status;
import sicv.util.XmlWriter;


public class SicvFacadeInit {

	
	
	private VehicleController vehicleController;
	private EmployeeController employeeController;
	private OccurrenceController occurrenceController;
	
	
	public SicvFacadeInit() {

		
		
		if (Constants.isPersistent()) {
			
			try{
			
			this.vehicleController = new VehicleController(new VehicleRepositoryJPA(
					(HibernatePersistenceMechanism) SicvFacade.getPm()));
		
			}catch(Exception e){
				
			}
			
		}
		

		
		if (Constants.isPersistent()) {
			this.occurrenceController = new OccurrenceController(new OccurrenceRepositoryJPA(
					(HibernatePersistenceMechanism) SicvFacade.getPm()));
		}
		
		
		
		if (Constants.isPersistent()) {
			this.employeeController = new EmployeeController(new EmployeeRepositoryJPA(
					(HibernatePersistenceMechanism) SicvFacade.getPm()));
		}
		
	}

	
	
	
	
	public IPersistenceMechanism getPm() {
		return SicvFacade.getPm();
	}

	

	

	public Employee searchEmployee(String login) throws RepositoryException, ObjectNotFoundException,
	TransactionException {
		Employee q = null;
		try {
			getPm().beginTransaction();
			q = this.employeeController.search(login);
			getPm().commitTransaction();
		} catch (RepositoryException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (ObjectNotFoundException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return q;
	}

	
	
	
	
	public Occurrence searchOccurrence(int code) throws RepositoryException,
	ObjectNotFoundException, TransactionException {
		
		Occurrence tp = null;
		try {
			getPm().beginTransaction();
			tp = this.occurrenceController.search(code);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		return tp;
	}

	
		

	public void insertEmployee(Employee employee) throws ObjectAlreadyInsertedException,
	ObjectNotValidException, TransactionException {
		try {
			getPm().beginTransaction();
			employeeController.insert(employee);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}

	public void insertOccurence(Occurrence occurrence) throws RepositoryException,
	ObjectAlreadyInsertedException, ObjectNotValidException, TransactionException, PersistenceMechanismException {
		
		try {
			getPm().beginTransaction();
			occurrenceController.insert(occurrence);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		}
		
	}
	
	public void insertVehicle(Vehicle vehicle) throws RepositoryException,
	ObjectAlreadyInsertedException, ObjectNotValidException, TransactionException {
		
		try {
			getPm().beginTransaction();
			vehicleController.insert(vehicle);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		}
		
	}
	
	public void updateEmployee(Employee employee) throws TransactionException, RepositoryException,
	ObjectNotFoundException, ObjectNotValidException {
		try {
			getPm().beginTransaction();
			employeeController.update(employee);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
	}





	public void insertOccurrence(Occurrence occurrence) throws RepositoryException, PersistenceMechanismException {
		
		try {
			getPm().beginTransaction();
			occurrenceController.insert(occurrence);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			try {
				getPm().rollbackTransaction();
			} catch (TransactionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}





	public Vehicle searchVehicle(int id) {
		
		return null;
	}





	public void updateVehicle(Vehicle vehicle) throws TransactionException {
		
		try {
			getPm().beginTransaction();
			vehicleController.update(vehicle);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}

		
		
	}





	public void updateOcurrence(Occurrence occurrence) throws TransactionException {
		
		
		try {
			getPm().beginTransaction();
			occurrenceController.update(occurrence);
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		
	}





	




	


	public void registerOccurrence(Occurrence occurrence) throws RepositoryException, TransactionException, ObjectNotFoundException, ObjectNotValidException, ObjectAlreadyInsertedException {
			
		
		try {
			this.getPm().beginTransaction();
			System.out.println("vehicle : " + this.vehicleController.hasVehiclesFreeByPriority(occurrence.getPriority()));
			System.out.println("agente : " + this.employeeController.hasForwardingAgentFree());
			
			if (this.vehicleController.hasVehiclesFreeByPriority(occurrence.getPriority())
					&& this.employeeController.hasForwardingAgentFree()){
				ForwardingAgent agent = this.employeeController.getForwardingAgentFree();
				
				System.out.println("tem veiculo livre e tem agnte livre");
					if (this.occurrenceController.hasOccurrenceWaiting()) {
						Occurrence lastOccurrence = this.occurrenceController.getNextOccurrence();
						System.out.println("tem occorrencia em espera");
							if (occurrence.getPriority().compareTo(lastOccurrence.getPriority()) > 0) {
								System.out.println("a prioridade da nova e maior");
									occurrence.setStatus(Status.IN_INITIAL_ATTENDANT);
									agent.registerOccurrence(occurrence);
									this.occurrenceController.insert(occurrence);
									this.employeeController.update(agent);								
									
							}else{
								System.out.println("a prioridade da nova e memo");
								lastOccurrence.setStatus(Status.IN_INITIAL_ATTENDANT);
								agent.registerOccurrence(lastOccurrence);
								this.occurrenceController.update(lastOccurrence);
								this.employeeController.update(agent);
								
								
								occurrence.setStatus(Status.WAITING);
								
								this.occurrenceController.insert(occurrence);
								
							}
						
					}else{
						System.out.println("nao tem occorrencia esperando");
						occurrence.setStatus(Status.IN_INITIAL_ATTENDANT);
						agent.registerOccurrence(occurrence);
						this.occurrenceController.insert(occurrence);
						this.employeeController.update(agent);
						
						System.out.println("entao inseriu e atualizou");
					}
			}else{
				System.out.println("nao tem veicle ou agente livre");
				occurrence.setStatus(Status.WAITING);
				
				this.occurrenceController.insert(occurrence);
				
			}
			this.createXmlPositionFile();
			this.getPm().commitTransaction();
			
		} catch (PersistenceMechanismException e) {

			e.printStackTrace();
			this.getPm().rollbackTransaction();
		}
		
		
	}

	private void createXmlPositionFile() throws RepositoryException{
		XmlWriter writer = new XmlWriter(Constants.PATH_XML_POSITION);
		
		Iterator<Vehicle> vehicles = null;
		List<Vehicle> working = new Vector<Vehicle>();
		
			vehicles = this.vehicleController.iterator();
			while(vehicles.hasNext()){
				Vehicle vehicle = vehicles.next();
				if(vehicle.getStatus().compareTo(Status.NO_WORKING) != 0){
					System.out.println("add" + vehicle.getStatus());
					working.add(vehicle);
				}
			}
			
		
		vehicles = working.iterator();
		while(vehicles.hasNext()){
			Vehicle vehicle = vehicles.next();
			writer.createPlacemarker(vehicle.getPlate(),vehicle.getDescription(),vehicle.getLat()+"", vehicle.getLng()+"");
		}
		
		writer.createXmlDoc(Constants.ICON_HREF);
		
	}



	

	public Iterator<Vehicle> iteratorVehicles() throws TransactionException {
		
		Iterator<Vehicle> q = null;
		try {
			getPm().beginTransaction();
			q = this.vehicleController.iterator();
			getPm().commitTransaction();
		} catch (TransactionException e) {
			getPm().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			getPm().rollbackTransaction();
		}
		
		return q;
	}





	





	
	
	

	
	
	
}
