package jsc.fuzzysystem;

import java.util.Hashtable;
import java.util.List;

import jsc.fuzzysystem.input.FuzzyInput;
import jsc.fuzzysystem.output.FuzzyOutput;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public interface FuzzySystem {

	
	public Hashtable<FuzzyVariable, Hashtable<Term, Double>> fuzzyfication();
	public  List<FuzzyOutput> getOutputs();
	public void defuzzyfication(
			Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs);
	public void enterInputs(List<FuzzyInput> inputs);
	public void execute();
	Hashtable<FuzzyVariable, Hashtable<Term, Double>> inference(
			Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs);
}
