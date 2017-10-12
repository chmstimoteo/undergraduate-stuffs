package jsc.fuzzysystem.variable.fuzzyset;

import jsc.fuzzysystem.membershipfuction.MembershipFunction;

public  class FuzzySet {

	private MembershipFunction function;
	private Term term;
	
	
	
	
	public FuzzySet(Term term,MembershipFunction function) {
		super();
		this.function = function;
		this.term = term;
	
	}
	
	public MembershipFunction getFunction() {
		return function;
	}
	public void setFunction(MembershipFunction function) {
		this.function = function;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	
	
	
	
	
	
	

}
