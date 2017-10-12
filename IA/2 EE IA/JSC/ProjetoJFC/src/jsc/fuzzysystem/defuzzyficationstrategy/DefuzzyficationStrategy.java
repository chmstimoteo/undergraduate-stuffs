package jsc.fuzzysystem.defuzzyficationstrategy;

import java.util.Hashtable;
import java.util.List;

import jsc.fuzzysystem.output.FuzzyOutput;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public interface DefuzzyficationStrategy {

	public  List<FuzzyOutput> defuzzyfication(Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs);
}
