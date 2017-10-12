package jsf.tests;

import java.util.ArrayList;
import java.util.List;

import jsc.fuzzysystem.defuzzyficationstrategy.AveragingMethodDefuzzyficationStrategy;
import jsc.fuzzysystem.inferencing.MamdaniFuzzyInferencingStrategy;
import jsc.fuzzysystem.input.FuzzyInput;
import jsc.fuzzysystem.jatobafuzzysystem.JSCFuzzyRuleBase;
import jsc.fuzzysystem.jatobafuzzysystem.JSCFuzzySystem;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.DefenseVariable;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.DistanceBollVariable;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.DistanceGolVariable;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.ForceKickBollVariable;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.VelocityVariable;
import jsc.fuzzysystem.output.FuzzyOutput;
import jsc.fuzzysystem.variable.FuzzyVariable;

public class Main {

	public static void main(String[] args) {
		
		VelocityVariable v = new VelocityVariable();
		ForceKickBollVariable f = new ForceKickBollVariable();
		DefenseVariable df = new DefenseVariable();
		DistanceBollVariable db = new DistanceBollVariable();
		DistanceGolVariable dg = new DistanceGolVariable();
		List<FuzzyVariable> inputs = new ArrayList<FuzzyVariable>();
		List<FuzzyVariable> outputs = new ArrayList<FuzzyVariable>();
		List<FuzzyVariable> all = new ArrayList<FuzzyVariable>();
		
		v.buildFuzzyVariable();
		f.buildFuzzyVariable();
		df.buildFuzzyVariable();
		dg.buildFuzzyVariable();
		db.buildFuzzyVariable();
		
		outputs.add(v);
		outputs.add(f);
		
		inputs.add(df);
		inputs.add(dg);
		inputs.add(db);
		
		all.addAll(inputs);
		all.addAll(outputs);
		
		JSCFuzzyRuleBase base = new JSCFuzzyRuleBase(all); 
		base.buildFuzzyRuleBase();
		
		
		JSCFuzzySystem system = new JSCFuzzySystem(base,inputs,outputs,new MamdaniFuzzyInferencingStrategy(),new AveragingMethodDefuzzyficationStrategy());
		
		List<FuzzyInput> enter = new ArrayList<FuzzyInput>();
		
		enter.add( new FuzzyInput(df,2) );
		enter.add( new FuzzyInput(db,2) );
		enter.add( new FuzzyInput(dg,1) );
		
		system.enterInputs(enter);
		system.execute();
		List<FuzzyOutput> saidas = system.getOutputs();
		
		
		System.out.println("------------");
		
		
		
		for (FuzzyOutput fuzzyOutput : saidas) {
			System.out.println(fuzzyOutput.getValue());
		}
		System.out.println("ok");
	}
}
