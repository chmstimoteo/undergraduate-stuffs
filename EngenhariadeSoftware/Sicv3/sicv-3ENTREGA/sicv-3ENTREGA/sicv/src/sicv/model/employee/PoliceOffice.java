package sicv.model.employee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PO")
public class PoliceOffice extends Employee{

	
	
	
	public PoliceOffice(int id, String name, String login, String password) {
		super(id, name, login, password);
		
	}

	public PoliceOffice() {
		super();
	}
	
	
}
