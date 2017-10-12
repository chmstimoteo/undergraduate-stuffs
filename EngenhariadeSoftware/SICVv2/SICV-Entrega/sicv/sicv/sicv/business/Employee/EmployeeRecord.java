package sicv.business.Employee;

import java.util.Iterator;

import sicv.data.IEmployeeRepository;
import sicv.model.employee.Employee;

public class EmployeeRecord {

	private IEmployeeRepository employeeRepository;
	
	
	public EmployeeRecord(IEmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public void insert(Employee employee) {
		
		this.employeeRepository.insert(employee);
		
	}

	public Iterator<Employee> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Employee search(int id) {
		return this.employeeRepository.search(id);
	}

	public void update(Employee employee) {
		
		this.employeeRepository.update(employee);
		
	}

	public Object exist(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
