package sicv.business.Employee;

import java.util.Iterator;

import sicv.data.IEmployeeRepository;
import sicv.model.employee.Employee;
import sicv.model.employee.ForwardingAgent;
import sicv.util.Status;

public class EmployeeController {

	private EmployeeRecord employeeRecord;

	public EmployeeController(IEmployeeRepository employeeRepository) {
		super();
		this.employeeRecord = new EmployeeRecord(employeeRepository);
	}
	
	public void insert(Employee employee) {
		this.employeeRecord.insert(employee);
	}
	
	public boolean hasForwardingAgentFree() {
		 boolean response = false;
		 Iterator<Employee> employees = this.employeeRecord.iterator();
		 
		 while (employees.hasNext()) {
			Employee employee = employees.next();
			if (employee instanceof ForwardingAgent && employee.getStatus() == Status.FREE) {
				response = true;
			}
		}
		 
		 return response;
	}
	
	public ForwardingAgent getForwardingAgentFree(){
		 ForwardingAgent response = null;
		 Iterator<Employee> employees = this.employeeRecord.iterator();
		 
		 while (employees.hasNext()) {
			Employee employee = employees.next();
			if (employee instanceof ForwardingAgent && employee.getStatus() == Status.FREE) {
				response = (ForwardingAgent)employee;
			}
		}
		 
		 return response;
	}
	
	public void upadte(Employee employee){
		this.employeeRecord.update(employee);
	}
}
