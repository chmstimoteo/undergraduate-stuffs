/******************************************************************
 * 
 * @author Carlos Timoteo
 * 1 Questao - Lista de Exercicio - Redes Neurais (07) 
 ******************************************************************/
public class Question1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double[][] patterns=
			{
				 {1,1,3},
				 {1,2,2},
				 {1,2,2},
				 {1,4,2},
				 {1,2,3}
			} ;
		
		double[] d = {0,1,1,0,1};
		double[] w0 = {0,0,0};
		Perceptron perceptron = new Perceptron(1.0,2,w0);
		perceptron.training(patterns, d, 5);
		
		double[] dataInput = {1,2,1};
		double result = perceptron.evaluateResult(dataInput, 2);
		
		System.out.println("\n\nResult = " + result);
		
	}

}
