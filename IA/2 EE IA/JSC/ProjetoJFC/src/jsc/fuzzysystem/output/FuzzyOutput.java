package jsc.fuzzysystem.output;

import java.util.Hashtable;

import jsc.fuzzysystem.variable.FuzzyVariable;



public class FuzzyOutput {

	private FuzzyVariable fuzzyVariable; 
	private double value;
	public FuzzyOutput(FuzzyVariable fuzzyVariable, double value) {
		super();
		this.fuzzyVariable = fuzzyVariable;
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
	public void setValue(double value) {
		this.value = value;
	}


}
