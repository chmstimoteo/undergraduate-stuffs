package sicv.model.localization;

public class StreetPart implements Comparable<StreetPart> {
	
	private Cross source;
	private Cross destination;
	private int maxVelocity;
	private int lenght;
	private int id;
	

	public Cross getSource() {
		return source;
	}



	public void setSource(Cross source) {
		this.source = source;
	}



	public Cross getDestination() {
		return destination;
	}



	public void setDestination(Cross destination) {
		this.destination = destination;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId() {
		return this.id;
		
	}



	public int getMaxVelocity() {
		return maxVelocity;
	}



	public void setMaxVelocity(int maxVelocity) {
		this.maxVelocity = maxVelocity;
	}



	public int getLenght() {
		return lenght;
	}



	public void setLenght(int lenght) {
		this.lenght = lenght;
	}



	public int compareTo(StreetPart streetPart) {
		return streetPart.getSource().getId()-this.source.getId();
	}
	
	
	
	
	
	

}
