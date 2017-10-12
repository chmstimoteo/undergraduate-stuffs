package jsc.fuzzysystem.function;

import java.util.List;

import jsc.fuzzysystem.util.Point;

public interface Function {

	public double evaluate(double input);
	public double summation(double x0,double xf);
	public double multiplicationX(double x0,double xf);
	public List<Point> intersection(Function function);
	public Domain getDomain();
}
