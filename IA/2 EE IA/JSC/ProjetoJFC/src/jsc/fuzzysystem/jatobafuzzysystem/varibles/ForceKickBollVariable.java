package jsc.fuzzysystem.jatobafuzzysystem.varibles;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.membershipfuction.TrapezoidalMembershipFunction;
import jsc.fuzzysystem.membershipfuction.TriangularMembershipFunction;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;

public class ForceKickBollVariable extends FuzzyVariable{

	@Override
	public void buildFuzzyVariable() {
		
		FuzzySet high = new FuzzySet(Term.STRONG,new TriangularMembershipFunction(0.0,0.0,0.1));
		FuzzySet mean = new FuzzySet(Term.WEAK,new TrapezoidalMembershipFunction(0.0,1.0,2.0,3.0));
		FuzzySet low = new FuzzySet(Term.NONE,new TrapezoidalMembershipFunction(2.0,3.0,5.0,5.0));
		List<FuzzySet> sets = new ArrayList<FuzzySet>();
		sets.add(high);
		sets.add(mean);
		sets.add(low);
		this.setFuzzySets(sets);
	}

	@Override
	public String geSymbol() {
		
		return "F";
	}
	
}
