package sicv.model.location;

public class ReferencePoint {
	
	private int id;
	private String name;
	private Street street;
	
	
	
	public ReferencePoint(int id, String name, Street street) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
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
	public Street getStreet() {
		return street;
	}
	public void setStreet(Street street) {
		this.street = street;
	}
	
	

}
