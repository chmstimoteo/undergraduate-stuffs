package jsf.tests;

import jsc.fuzzysystem.membershipfuction.TrapezoidalMembershipFunction;

public class TesteFunction {

	public static void main(String[] args) {
		
		TrapezoidalMembershipFunction t = new TrapezoidalMembershipFunction(0.0,0.0,1.0,2.0);
		System.out.println(t.evaluate(0.5));
	}
}
