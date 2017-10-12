package sicv.model.employee;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import sicv.util.Status;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE	)
public  abstract class Employee implements Serializable{
	
		
	private int id;
	 
	private String name;
	 
	@Id
	private String login;
	 
	private String password;

	private Status status;
	
	public Employee(int id, String name, String login, String password) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.status = Status.FREE;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean validatePassword(String password) {
	
		return this.password.equals(password);
	}

	public int getCode() {
		
		return this.id;
	}

	public void setCode(int code) {
		this.id = code;
		
	}

	

}
