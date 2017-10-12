package jsc.fuzzysystem.jatobafuzzysystem.varibles;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.membershipfuction.TrapezoidalMembershipFunction;
import jsc.fuzzysystem.membershipfuction.TriangularMembershipFunction;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;




public class VelocityVariable extends FuzzyVariable{

	

	@Override
	public void buildFuzzyVariable() {
		
		FuzzySet fastest = new FuzzySet(Term.HIGH,new TriangularMembershipFunction(0.0,0.0,0.2));
		FuzzySet fast = new FuzzySet(Term.LOW,new TrapezoidalMembershipFunction(0.0,2.5,5.0,7.5));
		FuzzySet stoped = new FuzzySet(Term.STOPED,new TrapezoidalMembershipFunction(2.5,7.5,10.0,10.0));
		List<FuzzySet> sets = new ArrayList<FuzzySet>();
		sets.add(fastest);
		sets.add(fast);
		sets.add(stoped);
		this.setFuzzySets(sets);
	}
	@Override
	public String geSymbol() {
		// TODO Auto-generated method stub
		return "V";
	}

}
