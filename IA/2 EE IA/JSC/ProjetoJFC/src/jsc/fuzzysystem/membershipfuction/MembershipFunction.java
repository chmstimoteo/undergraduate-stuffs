package jsc.fuzzysystem.membershipfuction;

import java.util.List;

import jsc.fuzzysystem.function.Function;
import jsc.fuzzysystem.function.Reta;

public interface MembershipFunction extends Function{

	public double getMembership(double input);
	public abstract List<Reta> getRetas();
}
