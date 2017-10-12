package sicv.business.Employee;

import java.util.Iterator;

import sicv.data.IEmployeeRepository;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.employee.Employee;
import sicv.model.employee.ForwardingAgent;
import sicv.util.Status;

public class EmployeeController {

	private EmployeeRecord employeeRecord;

	public EmployeeController(IEmployeeRepository employeeRepository) {
		super();
		this.employeeRecord = new EmployeeRecord(employeeRepository);
	}
	
	public void insert(Employee employee) throws RepositoryException {
		this.employeeRecord.insert(employee);
	}
	
	public boolean hasForwardingAgentFree() throws RepositoryException {
		 boolean response = false;
		 Iterator<Employee> employees = this.employeeRecord.iterator();
		 
		 while (employees.hasNext()) {
			Employee employee = employees.next();
			if (employee instanceof ForwardingAgent && employee.getStatus().compareTo(Status.FREE) == 0) {
				response = true;
				break;
			}
		}
		 
		 return response;
	}
	
	public ForwardingAgent getForwardingAgentFree() throws RepositoryException{
		 ForwardingAgent response = null;
		 Iterator<Employee> employees = this.employeeRecord.iterator();
		 
		 while (employees.hasNext()) {
			Employee employee = employees.next();
			if (employee instanceof ForwardingAgent && employee.getStatus().compareTo(Status.FREE) == 0) {
				response = (ForwardingAgent)employee;
				break;
			}
		}
		 
		 return response;
	}
	
	public void update(Employee employee) throws RepositoryException{
		this.employeeRecord.update(employee);
	}

	public Employee search(String login) throws RepositoryException, ObjectNotFoundException {
		
		return this.employeeRecord.search(login);
	}

	
	
}
