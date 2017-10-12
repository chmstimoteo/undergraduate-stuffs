package jsc.fuzzysystem.input;

import java.util.Hashtable;

import jsc.fuzzysystem.variable.FuzzyVariable;

public class FuzzyInput {

	FuzzyVariable fuzzyVariable; 
	double value; 

	public FuzzyInput(FuzzyVariable variable, double value) {
		super();
		this.fuzzyVariable = variable;
		this.value = value;
	}

	public FuzzyVariable getFuzzyVariable() {
		return fuzzyVariable;
	}

	public void setFuzzyVariable(FuzzyVariable fuzzyVariable) {
		this.fuzzyVariable = fuzzyVariable;
	}

	public double getValue() {
		return value;
	}

	public void setInput(double input) {
		this.value = input;
	}

	
	
	
	
}
