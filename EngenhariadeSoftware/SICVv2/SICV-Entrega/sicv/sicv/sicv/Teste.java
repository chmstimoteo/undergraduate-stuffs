package sicv;

import sicv.data.rdb.EmployeeRepositoryRDB;
import sicv.data.rdb.OccurrenceRepositotyRDB;
import sicv.model.employee.Employee;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class Teste {

	public static void main(String[] args) {
		EmployeeRepositoryRDB rep = new EmployeeRepositoryRDB();
		
		rep.insert(new Employee(2,"diedsd","logindsnjkdj","senhadas"));
		
		
		/*Employee n = new Employee(1,"Elliackin","logindsnjkdj","senhadas");
		rep.update(n);
		Employee p =  rep.search(1);
		System.out.println(p.getName());*/
		
		//Occurrence o = new  Occurrence(Priority.LOW,Status.WAITING,new Position(2,4),5);
		OccurrenceRepositotyRDB r = new OccurrenceRepositotyRDB();
		//r.insert(o);
		Occurrence no = r.search(1);
		System.out.println(no);
		no.setStatus(Status.WAITING);
		r.update(no);
		System.out.println(no);
		
	}	
}
