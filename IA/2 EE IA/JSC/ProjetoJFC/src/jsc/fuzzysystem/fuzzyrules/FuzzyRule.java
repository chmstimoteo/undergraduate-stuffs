package jsc.fuzzysystem.fuzzyrules;

import java.util.Hashtable;

import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;



public class FuzzyRule {


	
	private AndAssoation antecedent;
	private AndAssoation consequent;
	
	public  FuzzyRule(Hashtable<FuzzyVariable,Term> antecedent,Hashtable<FuzzyVariable,Term> consequent) {
		this.antecedent = new AndAssoation(antecedent);
		this.consequent = new AndAssoation(consequent);
	}
	
	

	public AndAssoation getAntecedent() {
		return antecedent;
	}


	public void setAntecedent(AndAssoation antecedent) {
		this.antecedent = antecedent;
	}


	public AndAssoation getConsequent() {
		return consequent;
	}


	public void setConsequent(AndAssoation consequent) {
		this.consequent = consequent;
	}
}
