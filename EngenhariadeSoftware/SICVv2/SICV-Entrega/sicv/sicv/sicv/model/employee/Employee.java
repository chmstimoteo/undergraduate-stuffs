package sicv.model.employee;

import sicv.util.Status;

public  class Employee {
	
		
	private int id;
	 
	private String name;
	 
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

}
