package jsc.fuzzysystem.inferencing;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jsc.fuzzysystem.fuzzyrules.FuzzyRule;
import jsc.fuzzysystem.fuzzyrules.FuzzyRuleBase;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;



public class MamdaniFuzzyInferencingStrategy implements FuzzyInferencingSrategy{

	
	@Override
	public Hashtable<FuzzyVariable, Hashtable<Term, Double>> inference(FuzzyRuleBase base,Hashtable<FuzzyVariable, Hashtable<Term, Double>> input) {
		
	
		Hashtable<FuzzyVariable, Hashtable<Term, Double>> output
		= new Hashtable<FuzzyVariable, Hashtable<Term,Double>>();
	
		double degree = 0;
		List<FuzzyRule> rules = base.getFuzzyRules();
		
		
		
		for(FuzzyRule rule  : rules){
			
			Hashtable<FuzzyVariable, Term> antecedent = rule.getAntecedent().getMapVariableTerm();
			Hashtable<FuzzyVariable, Term> consequent = rule.getConsequent().getMapVariableTerm();
			Set<FuzzyVariable> antecedentVariables = antecedent.keySet();
			Set<FuzzyVariable> consequentVariables = consequent.keySet();
			
		
			
			
			double min = Double.MAX_VALUE;
			double max = 0;
			
			
			for (Iterator<FuzzyVariable> iterator = antecedentVariables.iterator(); iterator.hasNext();) {
				FuzzyVariable fuzzyVariable =  iterator.next();
				
				Double menbership = input.get(fuzzyVariable).get(antecedent.get(fuzzyVariable));
				
				
				min = Math.min(min, menbership);
				
				
			}
								
			for (Iterator<FuzzyVariable> iterator = consequentVariables.iterator(); iterator.hasNext();) {
				FuzzyVariable fuzzyVariable =  iterator.next();
				
				if(output.containsKey(fuzzyVariable))
				{
					double value = output.get(fuzzyVariable).get(consequent.get(fuzzyVariable));
					output.get(fuzzyVariable).put(consequent.get(fuzzyVariable), Math.max(min, value));
				}else{
					
					Hashtable<Term, Double> map = new Hashtable<Term, Double>();
					for (FuzzySet set : fuzzyVariable.getFuzzySets() ) {
						map.put(set.getTerm(), 0.0);
					}
					
					output.put(fuzzyVariable,map);
					output.get(fuzzyVariable).put(consequent.get(fuzzyVariable), min);
					
				}
			}
			
			
		}
		
		return output;
	}



	


}
