package jsc.fuzzysystem.defuzzyficationstrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.function.Reta;
import jsc.fuzzysystem.output.FuzzyOutput;
import jsc.fuzzysystem.util.Point;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public class AveragingMethodDefuzzyficationStrategy implements DefuzzyficationStrategy{

	private Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs;
	@Override
	public List<FuzzyOutput> defuzzyfication(
			Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs) {
		    
		this.inputs = inputs;
		Set<FuzzyVariable> variables = inputs.keySet();
		FuzzyOutput output;
		List<FuzzyOutput> outputs = new ArrayList<FuzzyOutput>();
		
		
		
		for (FuzzyVariable fuzzyVariable : variables) {
			
		
			double centroid = calculateCentroid(fuzzyVariable);
			output = new FuzzyOutput(fuzzyVariable,centroid);
			outputs.add(output);
			
		}
		
		return outputs;
	}
	
	private double calculateCentroid(FuzzyVariable variable){
	
		 Hashtable<Term, Double> map = this.inputs.get(variable);
		 double average = 0.0;
		 List<Function> functions = new ArrayList<Function>();
		 List<FuzzySet> sets = variable.getFuzzySets();
		
		 
		
		 
		 for (Double value : map.values()) {
			
			 
			 average+= value;
			 

		 }
		 
		
		 
		 average /= ((double)map.values().size());
		
		
		 
		 for(FuzzySet set : sets){
			 functions.add(set.getFunction());
		 }
		
		 List<Reta> retas = buildFunction(functions, average);
		 double integral = 0.0 ;
		 double integralProdutoX = 0.0;
		 
		 for (Reta reta : retas) {
			integral+=reta.integral(reta.getDomain().getBoundMin(), reta.getDomain().getBoundMax());
			integralProdutoX+=reta.integralMultiplicationX(reta.getDomain().getBoundMin(), reta.getDomain().getBoundMax());
		 }
		 
		return integralProdutoX/integral;
	}
	
	
	private List<Reta> buildFunction(List<Function> functions,double average){
		
		 List<Point> points = new ArrayList<Point>();
		 Domain domain = functions.get(0).getDomain();
		 
		for(int i = 0 ; i < functions.size()-1 ;i++){
			
			for(int j = i+1;j<functions.size();j++){
				
				  points.addAll(functions.get(i).intersection(functions.get(j))); 
			}
		}
		
		
	
		for (int i = 0 ; i < points.size() ;i++) {
			
			
			if(points.get(i).getY() >= average){
				points.remove(i);
			}
		}
		
		
		Reta reta = new Reta(0.0,average,domain.getBoundMin(),domain.getBoundMax());
		List<Reta> retas = new ArrayList<Reta>();
		
		if(points.size() > 0){
			
			
			
			for(int i = 0 ; i < functions.size() ;i++){
				
				
					 points.addAll(functions.get(i).intersection(reta)); 
					  
			}
			
			Object[] sorted = points.toArray();
			Arrays.sort(sorted);
			
			for(int i = 0;i<sorted.length-1 ;i++){
				retas.add(new Reta((Point)sorted[i],(Point)sorted[i]));
			}
			
			retas.add(new Reta(new Point(domain.getBoundMin(),average),(Point)sorted[0]));
			retas.add(new Reta(new Point(domain.getBoundMin(),average),(Point)sorted[sorted.length-1]));
			return retas;
		}
		
		
		
		retas.add(reta);
		return retas;
	}
	
	
	

}
