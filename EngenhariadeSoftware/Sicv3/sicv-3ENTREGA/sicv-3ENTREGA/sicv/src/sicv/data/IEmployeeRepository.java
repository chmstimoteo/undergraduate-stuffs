package sicv.data;

import java.util.Iterator;

import sicv.lib.exceptions.ObjectNotFoundException;
import sicv.lib.exceptions.RepositoryException;
import sicv.model.employee.Employee;

public interface IEmployeeRepository {

	public abstract void insert(Employee employee) throws RepositoryException;
	public abstract Employee search(String login) throws RepositoryException, ObjectNotFoundException;
	public abstract void remove(String login) throws RepositoryException;
	public abstract void update(Employee employee) throws RepositoryException;
	public abstract Iterator<Employee> iterator() throws RepositoryException;
	public abstract boolean exist(String login) throws RepositoryException;
}
