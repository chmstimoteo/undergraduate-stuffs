package jsc.fuzzysystem.jatobafuzzysystem;

import java.util.List;

import jsc.fuzzysystem.AbstractFuzzySystem;
import jsc.fuzzysystem.inferencing.FuzzyInferencingSrategy;
import jsc.fuzzysystem.defuzzyficationstrategy.AveragingMethodDefuzzyficationStrategy;
import jsc.fuzzysystem.defuzzyficationstrategy.DefuzzyficationStrategy;
import jsc.fuzzysystem.fuzzyrules.FuzzyRuleBase;
import jsc.fuzzysystem.variable.FuzzyVariable;

public class JSCFuzzySystem extends AbstractFuzzySystem{

	public JSCFuzzySystem(FuzzyRuleBase base,
			List<FuzzyVariable> inputVariables,
			List<FuzzyVariable> outputVariables,
			FuzzyInferencingSrategy inferenceStrategy,
			DefuzzyficationStrategy defuzzyficationStrategy) {
		super(base, inputVariables, outputVariables, inferenceStrategy,
				defuzzyficationStrategy);
		// TODO Auto-generated constructor stub
	}

	
	
}
