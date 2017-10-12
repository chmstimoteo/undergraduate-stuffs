
public class Question2 {
	public static void main(String[] args) {
		double[][] patterns1 = 
		{
			{1, 0.5 , 0.8},
			{1, -0.5 , 0.4},
			{1, 0.5 , -0.4},
			{1, 0 , 0.2}
		};
	
		double[] d1 = {1,1,0,0};
		
		double[][] patterns2 = 
		{
			{1, 0.5 , 0.8},
			{1, -0.5 , 0.4},
			{1, 0.5 , 0},
			{1, 0.5 , -0.4},
			{1, 0 , 0.2}
		};
		
		double[] d2 = {1,1,1,0,0};
		
		double[][] patterns3 = 
		{
			{1, 0.5 , 0},
			{1, 0.2 , 0.3},
			{1, 0.1 , 0.5},
			{1, 0 , 0.5},
			{1, 0.4 , 0}
		};
		
		double[] d3 = {1,1,1,0,0};
		
		double[] w0 = {0,0,0};
		Perceptron perceptron1 = new Perceptron(1.0, 2, w0);
		//perceptron1.training(patterns1, d1, 4);
		
		Perceptron perceptron2 = new Perceptron(1.0, 2, w0);
		//perceptron2.training(patterns2, d2, 5);
		
		
		Perceptron perceptron3 = new Perceptron(1.0, 2, w0);
		perceptron3.training(patterns3, d3, 5);
		
	}
}
