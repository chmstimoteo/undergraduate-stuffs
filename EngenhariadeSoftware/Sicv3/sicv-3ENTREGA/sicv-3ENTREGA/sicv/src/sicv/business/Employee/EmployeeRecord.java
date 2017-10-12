package sicv.business.Employee;

import java.util.Iterator;

import sicv.data.IEmployeeRepository;
import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.employee.Employee;

public class EmployeeRecord {

	private IEmployeeRepository employeeRepository;
	
	
	public EmployeeRecord(IEmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public void insert(Employee employee) throws RepositoryException {
		
		this.employeeRepository.insert(employee);
		
	}

	public Iterator<Employee> iterator() throws RepositoryException {
		return this.employeeRepository.iterator();
	}
	
	public Employee search(String login) throws RepositoryException, ObjectNotFoundException {
		return this.employeeRepository.search(login);
	}

	public void update(Employee employee) throws RepositoryException {
		
		this.employeeRepository.update(employee);
		
	}

	public Object exist(String login) throws RepositoryException {
		
		return this.employeeRepository.exist(login);
	}

	
	
	public void remove(String login) throws RepositoryException {
		
		this.employeeRepository.remove(login);
		
	}

}
