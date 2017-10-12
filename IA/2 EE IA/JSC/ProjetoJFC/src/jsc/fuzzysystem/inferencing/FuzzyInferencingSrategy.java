package jsc.fuzzysystem.inferencing;

import java.util.Hashtable;

import jsc.fuzzysystem.fuzzyrules.FuzzyRuleBase;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;



public interface FuzzyInferencingSrategy {

	

	public Hashtable<FuzzyVariable, Hashtable<Term, Double>> inference(
			FuzzyRuleBase base,
			Hashtable<FuzzyVariable, Hashtable<Term, Double>> input);
}
