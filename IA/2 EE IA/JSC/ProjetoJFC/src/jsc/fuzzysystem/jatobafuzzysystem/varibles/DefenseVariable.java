package jsc.fuzzysystem.jatobafuzzysystem.varibles;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.membershipfuction.TriangularMembershipFunction;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public class DefenseVariable extends FuzzyVariable{

	@Override
	public void buildFuzzyVariable() {
		
		FuzzySet low = new FuzzySet(Term.LOW,new TriangularMembershipFunction(-4.0,-4.0,4.0));
		FuzzySet mean = new FuzzySet(Term.MEAN,new TriangularMembershipFunction(-4.0,0.0,4.0));
		FuzzySet high = new FuzzySet(Term.HIGH,new TriangularMembershipFunction(-4.0,4.0,4.0));
		List<FuzzySet> sets = new ArrayList<FuzzySet>();
		sets.add(low);
		sets.add(mean);
		sets.add(high);
		this.setFuzzySets(sets);
		
	}

	@Override
	public String geSymbol() {
		// TODO Auto-generated method stub
		return "DF";
	}

}
