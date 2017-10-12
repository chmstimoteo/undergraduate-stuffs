package sicv.data;

import java.util.Iterator;
import sicv.model.employee.Employee;

public interface IEmployeeRepository {

	public abstract void insert(Employee employee);
	public abstract Employee search(int id);
	public abstract void remove(int id);
	public abstract void update(Employee employee);
	public abstract Iterator<Employee> iterator();
	public abstract boolean exist(int id);
}
