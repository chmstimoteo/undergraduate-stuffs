package jsc.fuzzysystem.jatobafuzzysystem;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import jsc.fuzzysystem.fuzzyrules.FuzzyRule;
import jsc.fuzzysystem.fuzzyrules.FuzzyRuleBase;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.DefenseVariable;
import jsc.fuzzysystem.jatobafuzzysystem.varibles.DistanceBollVariable;
import jsc.fuzzysystem.variable.FuzzyVariable;
import jsc.fuzzysystem.variable.fuzzyset.Term;

public class JSCFuzzyRuleBase extends FuzzyRuleBase{





	public JSCFuzzyRuleBase(List<FuzzyRule> rules, List<FuzzyVariable> variables) {
		super(rules, variables);
		// TODO Auto-generated constructor stub
	}


	public JSCFuzzyRuleBase(List<FuzzyVariable> variables) {
		super(variables);
		
	}


	@Override
	public void buildFuzzyRuleBase() {
		// TODO Auto-generated method stub
		
		
		
		
			
		List<String> statements = new ArrayList<String>();
		statements.add("IF DF=LOW DB=FAR DG=FAR THEN V=HIGH F=NONE");
		statements.add("IF DF=LOW DB=NEAR DG=FAR THEN V=LOW F=NONE");
		statements.add("IF DF=LOW DB=NONE DG=FAR THEN V=STOPED F=WEAK");
		statements.add("IF DF=LOW DB=NONE DG=NEAR THEN V=STOPED F=STRONG");
		statements.add("IF DF=LOW DB=NONE DG=VERY_NEAR THEN V=STOPED F=WEAK");
		statements.add("IF DF=MEAN DB=FAR DG=FAR THEN V=HIGH F=NONE");
		statements.add("IF DF=MEAN DB=NEAR DG=FAR THEN V=LOW F=NONE");
		statements.add("IF DF=MEAN DB=NONE DG=FAR THEN V=HIGH F=NONE");
		statements.add("IF DF=MEAN DB=NONE DG=NEAR THEN V=LOW F=NONE");
		statements.add("IF DF=MEAN DB=NONE DG=VERY_NEAR THEN V=STOPED F=STRONG");
		statements.add("IF DF=HIGH DB=FAR DG=FAR THEN V=HIGH F=NONE");
		statements.add("IF DF=HIGH DB=NEAR DG=FAR THEN V=LOW F=NONE");
		statements.add("IF DF=HIGH DB=NONE DG=FAR THEN V=STOPED F=STRONG");
		statements.add("IF DF=HIGH DB=NONE DG=NEAR THEN V=STOPED F=WEAK");
		statements.add("IF DF=HIGH DB=NONE DG=VERY_NEAR THEN V=STOPED F=STRONG");
		buildFuzzyRuleBaseStrings(statements);
		System.out.println(this.getFuzzyRules().size());
	}
	
	public List<FuzzyRule> getFuzzyRules(){
		return super.getFuzzyRules();
	}
	private void buildFuzzyRuleBaseStrings(List<String> statements)
	{
		
		Hashtable<Hashtable<FuzzyVariable, Term>, Hashtable<FuzzyVariable, Term>> fuzzyRules
		= new Hashtable<Hashtable<FuzzyVariable,Term>, Hashtable<FuzzyVariable,Term>>();
	
		Hashtable<String, FuzzyVariable> variables=new Hashtable<String, FuzzyVariable>();
		Hashtable<String, Term> terms = new Hashtable<String, Term>();
		
		
		for(FuzzyVariable variable : super.getVariables()){
			variables.put(variable.geSymbol(), variable);
		}
		
		Term[] array  = Term.values();
		for(Term term : array){
			terms.put(term.toString(), term);
		}
		
		
	
		StringTokenizer expressionTokens = null;
		Hashtable<FuzzyVariable, Term> antecedent;
		Hashtable<FuzzyVariable, Term> consequent;
		
		for(String statement : statements){
			
			
			
			StringTokenizer ruleTokens = new StringTokenizer(statement);
			
		
			
			
			antecedent = new Hashtable<FuzzyVariable, Term>();
			consequent = new Hashtable<FuzzyVariable, Term>();
			
			ruleTokens.nextToken();
			String string = ruleTokens.nextToken();
			while(!string.equals("THEN")){
				
				System.out.println(string);
				expressionTokens = new StringTokenizer(string,"=");
				String variable = expressionTokens.nextToken();
				String term = expressionTokens.nextToken();
				antecedent.put(variables.get(variable), terms.get(term));
				string = ruleTokens.nextToken();	
			}
			
			
			while(ruleTokens.hasMoreTokens()){
				
				
				string = ruleTokens.nextToken();
				
				System.out.println(string);
				expressionTokens = new StringTokenizer(string,"=");
				
				String variable = expressionTokens.nextToken();
				String term = expressionTokens.nextToken();
				
				consequent.put(variables.get(variable), terms.get(term));
			
				
			}
			
			fuzzyRules.put(antecedent, consequent);
		
		}
		
		
		buildFuzzyRuleBase(fuzzyRules);
		
		
	}
	
	private void buildFuzzyRuleBase(
			Hashtable<Hashtable<FuzzyVariable, Term>, Hashtable<FuzzyVariable, Term>> fuzzyRules) {
			Hashtable<FuzzyVariable, Term> antecedent;
			Hashtable<FuzzyVariable, Term> consequent;
			List<FuzzyRule> rules = new ArrayList<FuzzyRule>();
			
			for(int i=0;i<fuzzyRules.size();i++){
				antecedent = fuzzyRules.keys().nextElement();
				consequent = fuzzyRules.get(antecedent);
				FuzzyRule rule = new FuzzyRule(antecedent,consequent);
				rules.add(rule);
			}
			
			System.out.println(fuzzyRules.size());
			super.setFuzzyRules(rules);
	}




	
}
