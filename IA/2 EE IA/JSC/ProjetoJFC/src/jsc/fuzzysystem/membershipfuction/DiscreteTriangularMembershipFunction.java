package jsc.fuzzysystem.membershipfuction;

import java.util.List;

import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.function.Reta;
import jsc.fuzzysystem.util.Point;

public class DiscreteTriangularMembershipFunction extends DiscreteDomainPertinenceFunction{

	private TriangularMembershipFunction function;
	
	public  DiscreteTriangularMembershipFunction(double boundMin,double center, double boundMax ) {
	
		super();
		this.function = new TriangularMembershipFunction(boundMin,center,boundMax);
	
	}

	
	public DiscreteTriangularMembershipFunction() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Reta> getRetas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double evaluate(double input) {
		
		return this.function.evaluate(input);
	}

	@Override
	public Domain getDomain() {
		// TODO Auto-generated method stub
		return this.function.getDomain();
	}

	@Override
	public List<Point> intersection(Function function) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double multiplicationX(double x0, double xf) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double summation(double x0, double xf) {
		// TODO Auto-generated method stub
		return 0;
	}

}
