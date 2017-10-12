
public class Question3 {
	
	public static void main(String[] args) {
	    
		double[][] patterns = 
				{
					{1,0,0,1,0,0,1,0,0,1},
					{1,1,0,0,1,0,0,1,0,0}
				};
		
		/****************************
		 * Codificacao usada:
		 * 0 1 = pattern1
		 * 1 0 = pattern2
		 **************************/
		double[][] d = 
				{
					{0,1},
					{1,0}
				};
		
		double[][] w0 = 
		{
				{0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7},
				{0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7}
		};
		
		double[] testPattern1 = {1,1,0,0,1,1,0,1,0,0};
		double[] testPattern2 = {1,0,0,1,0,1,1,0,0,1};
		
		Adaline adaline = new Adaline(0.8, 9, 2, w0);
		adaline.training(patterns, d, 2, 50);
		double[] y1 = adaline.getOutput(testPattern1);
		double[] y2 = adaline.getOutput(testPattern2);
		
		System.out.printf("Output of pattern 1 = [%f %f]\n",y1[0],y1[1]);
		System.out.printf("Output of pattern 2 = [%f %f]\n",y2[0],y2[1]);
		adaline.printEMQ();
	}
}
