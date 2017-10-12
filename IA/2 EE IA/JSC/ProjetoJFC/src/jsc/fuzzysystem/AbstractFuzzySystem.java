package jsc.fuzzysystem;

import java.util.Hashtable;
import java.util.List;

import jsc.fuzzysystem.defuzzyficationstrategy.DefuzzyficationStrategy;
import jsc.fuzzysystem.fuzzyrules.FuzzyRuleBase;
import jsc.fuzzysystem.inferencing.FuzzyInferencingSrategy;
import jsc.fuzzysystem.input.FuzzyInput;
import jsc.fuzzysystem.output.FuzzyOutput;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.FuzzySet;
import jsc.fuzzysystem.variable.fuzzyset.Term;


public abstract class AbstractFuzzySystem implements FuzzySystem{

	private FuzzyRuleBase base;
	private List<FuzzyVariable> inputVariables;
	private List<FuzzyVariable> outputVariables;
	private FuzzyInferencingSrategy inferenceStrategy;
	private List<FuzzyInput> inputs;
	private List<FuzzyOutput> outputs;
	private DefuzzyficationStrategy defuzzyficationStrategy;
	
	
	
	
	



	
	public AbstractFuzzySystem(FuzzyRuleBase base,
			List<FuzzyVariable> inputVariables,
			List<FuzzyVariable> outputVariables,
			FuzzyInferencingSrategy inferenceStrategy,
			DefuzzyficationStrategy defuzzyficationStrategy) {
		super();
		this.base = base;
		this.inputVariables = inputVariables;
		this.outputVariables = outputVariables;
		this.inferenceStrategy = inferenceStrategy;
		this.defuzzyficationStrategy = defuzzyficationStrategy;
	}

	public void enterInputs(List<FuzzyInput> inputs){
		this.inputs = inputs;
	}
	
	public void execute(){
		Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs = this.fuzzyfication();
		Hashtable<FuzzyVariable, Hashtable<Term, Double>> alfas = this.inference(inputs);
		this.defuzzyfication(alfas);
	
	}

	@Override
	public Hashtable<FuzzyVariable, Hashtable<Term, Double>> fuzzyfication() {
		
		Hashtable<FuzzyVariable, Hashtable<Term, Double>> memberships
		 = new Hashtable<FuzzyVariable, Hashtable<Term,Double>>();
		
		Hashtable<Term, Double> mapTermValue;
		FuzzyVariable v;
		double value;
		
		int i = 0;
		
		if(inputs.size() != this.inputVariables.size())
			throw new RuntimeException("entras != n de variaveis");
		
		for(FuzzyInput input : inputs){
			
			mapTermValue = new Hashtable<Term, Double>();
			value = input.getValue();
			List<FuzzySet> fuzzySets= this.inputVariables.get(i).getFuzzySets();
			
			
			for(FuzzySet set : fuzzySets){
				
				double result = set.getFunction().getMembership(value);
				mapTermValue.put(set.getTerm(),result);
			}
			
			memberships.put(this.inputVariables.get(i), mapTermValue);
			i++;
		}
		
		return memberships;
	}



	@Override
	public Hashtable<FuzzyVariable, Hashtable<Term, Double>> inference(Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs) {
		
		
		return this.inferenceStrategy.inference(this.base,inputs);
	}
	
	@Override
	public void defuzzyfication(Hashtable<FuzzyVariable, Hashtable<Term, Double>> inputs) {
		
		this.outputs =  defuzzyficationStrategy.defuzzyfication(inputs);
	}
	
	@Override
	public  List<FuzzyOutput> getOutputs() {
		
		return this.outputs;
	}

	public FuzzyRuleBase getBase() {
		return base;
	}

	public void setBase(FuzzyRuleBase base) {
		this.base = base;
	}

	public List<FuzzyVariable> getVariaveis() {
		return inputVariables;
	}

	public void setVariaveis(List<FuzzyVariable> variaveis) {
		this.inputVariables = variaveis;
	}

	public FuzzyInferencingSrategy getInferenceStrategy() {
		return inferenceStrategy;
	}

	public void setInferenceStrategy(FuzzyInferencingSrategy inferenceStrategy) {
		this.inferenceStrategy = inferenceStrategy;
	}

	

}
