package jsc.fuzzysystem.function;

public interface  ContinuousDomainFunction extends Function{

	
	
	
	public abstract double integral(double x0,double xf);
	public abstract double integralMultiplicationX(double x0,double xf);
	
}
