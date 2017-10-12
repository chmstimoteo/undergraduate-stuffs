package jsc.fuzzysystem.membershipfuction;

import java.util.List;

import jsc.fuzzysystem.function.DiscreteDomainFunction;
import jsc.fuzzysystem.function.Domain;
import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.util.Point;

public abstract class DiscreteDomainPertinenceFunction implements MembershipFunction,DiscreteDomainFunction{

	public double getMembership(double input){
		return this.evaluate(input);
	}


}
