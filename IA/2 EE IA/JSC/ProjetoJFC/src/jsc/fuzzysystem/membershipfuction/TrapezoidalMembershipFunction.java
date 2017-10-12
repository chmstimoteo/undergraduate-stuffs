package jsc.fuzzysystem.membershipfuction;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.function.Reta;
import jsc.fuzzysystem.util.Point;



public class TrapezoidalMembershipFunction extends ContinuousDomainMembershipFunction{

	
	private double boundMin;
	private double boundMax;
	private double center1;
	private double center2;
	private Reta retaCres ;
	private Reta retaDecres;
	
	public TrapezoidalMembershipFunction(double boundMin, double center1, double center2, double boundMax) {
		super();
		this.center1 = center1;
		 this.center2 = center2;
		this.retaCres = new Reta(new Point(boundMin,0.0),new Point(center1,1.0));
		this.retaDecres = new Reta(new Point(center2,1.0),new Point(boundMax,0.0));
		
		if(boundMin==center1)
			this.retaCres = null;
		if(boundMax==center2)
			this.retaDecres = null;
		// TODO Auto-generated constructor stub
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
	public double evaluate(double x) {

		if(x <= boundMin)
			return 0.0;
		
		if(x > boundMin && x < center1)
			return (x-boundMin)/(center1-boundMin);
		
		if(x>=center1 && x<=center2)
			  return 1.0;
		
		if(x > center2 && x < boundMax )
			return (boundMax-x)/(boundMax-center2);
		
		if(x >= boundMax)
			return 0.0;
		
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
		
		if(function != null){
			 
		
		if (function instanceof MembershipFunction) {
			 mFunction = (MembershipFunction) function;
		
		
		 List<Reta> retas = 	 mFunction.getRetas();
		
		
	
		
			for(int i = 0; i < retas.size();i++){
				
				if(retas.get(i)!=null){
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
	public List<Reta> getRetas() {
		List<Reta> retas = new ArrayList<Reta>();
		retas.add(this.retaCres);
		retas.add(this.retaDecres);
		return retas;
	}





	@Override
	public double getMembership(double input) {
		return this.evaluate(input);
	}







	

}
