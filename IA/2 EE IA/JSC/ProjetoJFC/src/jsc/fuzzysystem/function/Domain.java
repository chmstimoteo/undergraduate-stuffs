package jsc.fuzzysystem.function;

public class Domain {

	private double boundMin;
	private double boundMax;
	public double getBoundMin() {
		return boundMin;
	}
	public void setBoundMin(double boundMin) {
		this.boundMin = boundMin;
	}
	public double getBoundMax() {
		return boundMax;
	}
	public void setBoundMax(double boundMax) {
		this.boundMax = boundMax;
	}
	public Domain(double boundMin,double boundMax) {
		super();
		this.boundMax = boundMax;
		this.boundMin = boundMin;
	}
	
	
}
