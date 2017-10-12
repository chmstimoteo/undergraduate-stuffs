package jsc.fuzzysystem.fuzzyrules;

import java.util.Hashtable;

import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public class AndAssoation {

	private Hashtable<FuzzyVariable,Term> mapVariableTerm;
	
	
	public AndAssoation(Hashtable<FuzzyVariable, Term> map) {
		super();
		this.mapVariableTerm = map;
		
	}


	public Hashtable<FuzzyVariable, Term> getMapVariableTerm() {
		return mapVariableTerm;
	}


	public void setMapVariableTerm(Hashtable<FuzzyVariable, Term> mapVariableTerm) {
		this.mapVariableTerm = mapVariableTerm;
	}
	
	
}
