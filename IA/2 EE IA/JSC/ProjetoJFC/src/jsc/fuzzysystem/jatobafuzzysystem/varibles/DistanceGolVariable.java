package jsc.fuzzysystem.jatobafuzzysystem.varibles;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.membershipfuction.TrapezoidalMembershipFunction;
import jsc.fuzzysystem.membershipfuction.TriangularMembershipFunction;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public class DistanceGolVariable extends FuzzyVariable {

	@Override
	public void buildFuzzyVariable() {
	
		FuzzySet high = new FuzzySet(Term.NEAR,new TrapezoidalMembershipFunction(0.0,0.0,1.0,2.0));
		FuzzySet mean = new FuzzySet(Term.FAR,new TrapezoidalMembershipFunction(1.0,2.0,4.0,5.0));
		FuzzySet low = new FuzzySet(Term.VERY_NEAR,new TrapezoidalMembershipFunction(4.0,5.0,10.0,10.0));
		List<FuzzySet> sets = new ArrayList<FuzzySet>();
		sets.add(high);
		sets.add(mean);
		sets.add(low);
		this.setFuzzySets(sets);
		
	}
	
	@Override
	public String geSymbol() {
		// TODO Auto-generated method stub
		return "DG";
	}

}
