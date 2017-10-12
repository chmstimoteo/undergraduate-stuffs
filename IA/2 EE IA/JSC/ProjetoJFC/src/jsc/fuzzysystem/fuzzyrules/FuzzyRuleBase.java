package jsc.fuzzysystem.fuzzyrules;

import java.util.Hashtable;
import java.util.List;

import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public abstract class FuzzyRuleBase {

	private List<FuzzyRule> rules;
	private List<FuzzyVariable> variables;
	
	public FuzzyRuleBase(List<FuzzyRule> rules,List<FuzzyVariable> variables) {
		
		this.rules = rules;
		this.variables = variables;
	}
	
	public FuzzyRuleBase(List<FuzzyVariable> variables) {
		this.variables = variables;
	}

	public List<FuzzyRule> getFuzzyRules() {
		return rules;
	}

	public void setFuzzyRules(List<FuzzyRule> rules) {
		this.rules = rules;
	}
	
	public abstract void buildFuzzyRuleBase();

	public List<FuzzyVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<FuzzyVariable> variables) {
		this.variables = variables;
	}
}
