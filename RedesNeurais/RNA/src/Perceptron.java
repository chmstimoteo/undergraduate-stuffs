/****************************************
 * 
 * @author Carlos Timoteo
 * Perceptron Single Layer
 ****************************************/

public class Perceptron {

	private double[] w;
	private double alfa;
	private int NP;
	
	public Perceptron(double alfa, int NP,double[] ws) {
		this.w = ws;
		this.alfa = alfa;
		this.NP = NP;
	}
	
	

	public void training(double[][] patterns, double[] d,int numPatterns){
		boolean stopCodition = false;
		double net = 0;
		double y = 0;
		double e;
		int countFaults = 0;
		double[] x;
		int cycle = 0;
		
		while(!stopCodition){
			countFaults = 0;
			for(int data=0;data < numPatterns;data++){
				x = patterns[data];
				net = 0;
				for(int j=0; j <= NP ;j++){
					net += w[j]* x[j];
				}
				y = ( net >= 0 ) ? 1 : 0;
				e = d[data] - y; 
				if( e != 0){
					for(int j=0;j <= NP ;j++){
						w[j] = w[j] + alfa * e * x[j];    
					}
				}else{
					countFaults++;
				}
			}
			
			//Parou de aprender
			if(countFaults==numPatterns){
				stopCodition = true;
			}
			
			cycle++;
			System.out.println("Cycle = " + cycle);
		}
		
		System.out.println("Training Finalized in cycle " + cycle);
	}
	
	public double evaluateResult(double[] dataInput, int numPatterns) {
        double net = 0;
        double y = 0;
        double[] x;
        
        x = dataInput;
        net = 0;
        for(int j=0; j <= numPatterns ;j++){
            net += w[j]* x[j];
        }
        y = ( net >= 0 ) ? 1 : 0;
        return y;
	}
}
