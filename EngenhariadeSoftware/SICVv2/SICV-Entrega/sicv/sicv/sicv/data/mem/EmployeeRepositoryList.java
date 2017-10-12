package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IEmployeeRepository;
import sicv.model.employee.Employee;

public class EmployeeRepositoryList implements IEmployeeRepository {
 
	private List<Employee> employees;

	public EmployeeRepositoryList() {
		this.employees = new ArrayList<Employee>();
	}
	
	
	public void insert(Employee Employee) {
		
		this.employees.add(Employee);
		
	}

	
	public void remove(int id) {
		for (Iterator<Employee> iterator = this.employees.iterator(); iterator.hasNext();) {
			Employee Employee =  iterator.next();
			if (Employee.getId() == id) {
				this.employees.remove(Employee);
				return;
			}
		}
		
	}

	
	public Employee search(int id) {
		
		
		for (Iterator<Employee> iterator = this.employees.iterator(); iterator.hasNext();) {
			Employee Employee =  iterator.next();
			if (Employee.getId() == id) {
				
				return Employee;
			}
		}
		
		return null;
	}

	
	public void update(Employee Employee) {
		this.remove(Employee.getId());
		this.insert(Employee);
	}

	
	public Iterator<Employee> iterator() {
		return this.employees.iterator();	
	}



	public boolean exist(int id) {

		for (Iterator<Employee> iterator = this.employees.iterator(); iterator.hasNext();) {
			Employee Employee =  iterator.next();
			if (Employee.getId() == id) {
				
				return true;
			}
		}
		
		return false;
	}

	
	
	 
}
 
