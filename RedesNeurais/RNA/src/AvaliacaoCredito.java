
public class AvaliacaoCredito {

	public static void main(String[] args) {
		/*****************************
		 * 
		 *****************************/
		
		double[][] patterns=
			{
				 {1,1,0,1,1,0,0,0,1,1},
				 {1,0,1,0,1,1,1,0,1,0},
				 {1,1,1,1,0,0,0,1,0,0},
				 {1,1,1,1,1,1,1,1,0,0},
				 {1,1,0,0,1,1,1,0,0,0},
				 {1,0,0,1,0,0,0,1,1,1}
			} ;
		
		double[] d = {1,1,1,0,0,0};
		double[] w0 = {1,1,-1,1,1,1,1,1,0,0};
		Perceptron perceptron = new Perceptron(1.0, 9,w0);
		perceptron.training(patterns, d, 6);
		
	}


}
