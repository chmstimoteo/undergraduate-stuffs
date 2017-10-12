package jsc.fuzzysystem.function;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.util.Point;



public class Reta implements ContinuousDomainFunction{


	private double m;
	private double b;
	private double boundMin;
	private double boundMax;
	private Domain domain;
	private double c;
	
	public Reta(Point p0,Point p1) {
		
		
		
		
		
		if(p1.getX()-p0.getX() != 0){
			this.m = (p1.getY()-p0.getY())/(p1.getX()-p0.getX());
			this.b = (p0.getY()-(this.m*p0.getX()));
			this.c = (-this.b)/(this.m);
		}else{
			this.m = Double.POSITIVE_INFINITY;
			this.b = Double.NaN;
			this.c = p0.getX();
		}
		

		
		
		
		
		if(p0.getX() < p1.getX()){
			this.boundMin = p0.getX();
			this.boundMax=  p1.getX();
		}else{
			this.boundMin = p1.getX();
			this.boundMax = p0.getX();
		}
		
		this.domain = new Domain(boundMin,boundMax);
		
	}
	
	public Reta(double m , double b,double boundMin,double boundMax) {
		this.m = m;
		this.b = b;
		this.c = -this.b/this.m;
		this.boundMin = boundMin;
		this.boundMax = boundMax;
		this.domain = new Domain(boundMin,boundMax);
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

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

	public double evaluate(double x) {
		
		
		//if(this.m == Double.POSITIVE_INFINITY)
			//return Double.POSITIVE_INFINITY;
		
		return this.m * x + this.b;
	}

	@Override
	public double integral(double x0, double xf) {
		
		this.verificaX(x0);
		this.verificaX(xf);
		verificaAMenorB(x0,xf);
		return this.integralPartialReta(xf)-this.integralPartialReta(x0);
	}
	
	private void verificaX(double x){
	
		
		if( x < boundMin || x > boundMax )
		throw new RuntimeException("Fora do dominio");
	}
	
	
	private double integralPartialReta(double x){
		double v =( this.m * Math.pow(x, 2))/2.0 + this.b * x;
		return v;
	}
	
	private double integralPartialProdutoX(double x) {
		
		double v = (this.m * Math.pow(x, 3))/3.0  + this.b * (Math.pow(x, 2)/2.0);
		return x;
	}


	@Override
	public List<Point> intersection(Function function) {
	
		List<Point> point = new ArrayList<Point>();
		
		if (function instanceof Reta) {
			Reta reta = (Reta) function;
			
			if(reta != null){
				
	         double value = (reta.getB()-this.b)/(this.m-reta.getM());
				
				if(value >= this.boundMin && value <= boundMax){
					if(value >= reta.getBoundMin() && value <= reta.getBoundMax()){
						
						point.add(new Point(value,this.evaluate(value)));
					}	
				}
			}
		}
		
		return point;
		/*List<Point> point = new ArrayList<Point>();
		double value = 0.0;
		if (function instanceof Reta) {
			Reta reta = (Reta) function;
		
			
			
			
				
			if(reta.getM() != this.m){
				
				if(this.m == Double.POSITIVE_INFINITY)
					point.add(new Point(reta.getC(),reta.evaluate(reta.getC())));
				else
				if(reta.getM()== Double.POSITIVE_INFINITY){
					point.add(new Point(this.c,this.evaluate(this.c)));
					System.out.println(this.c);
				}else{
				
				value = (reta.getB()-this.b)/(this.m-reta.getM());
				
				if(value >= this.boundMin && value <= boundMax){
					if(value >= reta.getBoundMin() && value <= reta.getBoundMax()){
						
						point.add(new Point(value,this.evaluate(value)));
					}	
				}
			 }
		 }
			
			return point;
		
		}
		
		throw new RuntimeException("No intersection waith line");*/
		
		
	}

	public double getC() {
		// TODO Auto-generated method stub
		return this.c;
	}

	@Override
	public Domain getDomain() {
		
		return this.domain;
	}

	@Override
	public double integralMultiplicationX(double x0, double xf) {
		this.verificaX(x0);
		this.verificaX(xf);
		 return this.integralPartialProdutoX(xf)-this.integralPartialProdutoX(x0);
	}
	
	private void verificaAMenorB(double x0,double xf)
	{
		if( x0 < boundMin || xf > boundMax )
			throw new RuntimeException("x0<xf");
	}

	@Override
	public double multiplicationX(double x0, double xf) {
		// TODO Auto-generated method stub
		verificaAMenorB(x0,xf);
		return this.integralMultiplicationX(x0, xf);
	}

	@Override
	public double summation(double x0, double xf) {
		// TODO Auto-generated method stub
		return this.integral(x0, xf);
	}
	
	

}
