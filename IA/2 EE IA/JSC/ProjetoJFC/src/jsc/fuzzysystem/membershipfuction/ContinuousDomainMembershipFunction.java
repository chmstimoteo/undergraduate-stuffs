package jsc.fuzzysystem.membershipfuction;

import java.util.List;

import jsc.fuzzysystem.function.ContinuousDomainFunction;
import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.util.Point;


public abstract class ContinuousDomainMembershipFunction implements MembershipFunction,ContinuousDomainFunction {


	public double summation(double x0,double xf){
		return this.integral(x0, xf);
	}
	public double multiplicationX(double x0,double xf){
		return this.integralMultiplicationX(x0, xf);
	}
	
	
	public abstract double integral(double x0,double xf);
	public abstract double integralMultiplicationX(double x0,double xf);
	public abstract  double getMembership(double input);
}
