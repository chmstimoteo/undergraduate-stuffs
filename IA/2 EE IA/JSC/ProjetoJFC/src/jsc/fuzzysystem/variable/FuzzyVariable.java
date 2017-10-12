package jsc.fuzzysystem.variable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;




public abstract class FuzzyVariable {

	private List<FuzzySet> fuzzysets;
	
	public FuzzyVariable() {
		
	}
	
	public abstract void  buildFuzzyVariable();
	
	public Hashtable<Term, Double> degreesPertinence(double input) {
		return null;
	}
	
	public List<Term> getTerms() {
		List<Term> terms = new ArrayList<Term>();
		for(FuzzySet setFuzzy : fuzzysets){
			terms.add(setFuzzy.getTerm());
		}
		return terms;
	}

	public List<FuzzySet> getFuzzySets() {
		return fuzzysets;
	}

	public void setFuzzySets(List<FuzzySet> setFuzzys) {
		this.fuzzysets = setFuzzys;
	}

	public abstract String geSymbol();

	
}
