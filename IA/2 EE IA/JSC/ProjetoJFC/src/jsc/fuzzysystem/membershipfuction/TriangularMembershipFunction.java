package jsc.fuzzysystem.membershipfuction;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.function.Reta;
import jsc.fuzzysystem.util.Point;




public class TriangularMembershipFunction extends ContinuousDomainMembershipFunction{

	private double boundMin;
	private double boundMax;
	private double center;
	private Reta retaCres ;
	private Reta retaDecres;
	
	
	public TriangularMembershipFunction(double boundMin,double center, double boundMax ) {
		super();
		this.boundMin = boundMin;
		this.boundMax = boundMax;
		this.center = center;
		this.retaDecres = new Reta(new Point(center,1),new Point(boundMax,0));
		this.retaCres = new Reta(new Point(boundMin,0),new Point(center,1));
			
		if(boundMin==center)
			this.retaCres = null;
		if(boundMax==center)
			this.retaDecres = null;
		
	}




	public double getMembership(double input) {
		
		return this.evaluate(input);
	}




	@Override
	public double evaluate(double x) {
		
		double mi = 0.0;
		
	
		if(x <= boundMin)
			return mi;
			
		if(x > boundMin && x <= center)
			return (x-boundMin)/(center-boundMin);
		if(x > center && x < boundMax )
			return (boundMax-x)/(boundMax-center);
		if(x >= boundMax)
			return mi;
		
		return 0.0;
	
	}




	@Override
	public Domain getDomain() {
		
		return new Domain(this.boundMin,this.boundMax);
	}




	@Override
	public List<Point> intersection(Function function) {
		MembershipFunction mFunction = null;
		List<Point> points1 = new ArrayList<Point>();
		List<Point> points2 = new ArrayList<Point>();
		
		if(function == null){
		if (function instanceof MembershipFunction) {
			 mFunction = (MembershipFunction) function;
		
		
		 List<Reta> retas = 	 mFunction.getRetas();
		
		
	
		
			for(int i = 0; i < retas.size();i++){
				
				if(retas.get(i) != null){
				points1 = retas.get(i).intersection(this.retaCres);
				points2 = retas.get(i).intersection(this.retaDecres);
				points1.addAll(points2);
				}
			}
		
		}
		
		if (function instanceof Reta) {
			Reta reta = (Reta) function;
			points1.addAll(reta.intersection(this.retaCres));
			points1.addAll(reta.intersection(this.retaDecres	));
		}
		
		}
		
		return  points1;
	}




	@Override
	public double integral(double x0, double xf) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public double integralMultiplicationX(double x0, double xf) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public List<Reta> getRetas() {
		List<Reta> retas = new ArrayList<Reta>();
		retas.add(this.retaCres);
		retas.add(this.retaDecres);
		return retas;
	}




	
	
	

}
