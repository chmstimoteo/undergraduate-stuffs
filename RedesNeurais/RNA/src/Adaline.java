import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Adaline {

	private double[][] w;
	private double alfa;
	private int NP;
	private int NN;
	private double[] EMQ;
	
	public Adaline(double alfa, int NP,int NN, double[][] w0) {
		this.w = w0;
		this.alfa = alfa;
		this.NP = NP;
		this.NN = NN;
	}
	
	
	public double[] getOutput(double[] x){
		double net = 0.0;
		double[] y = new double[NN];
		for(int neuron=0; neuron < NN ;neuron++){
			net = 0;
			for(int j=0; j<= NP; j++){
				net += w[neuron][j] * x[j];
			}
			y[neuron] = 1.0/(1.0 + Math.exp(-net));
		}
		return y;
	}

	public void training(double[][] patterns, double[][] d,int numPatterns, int maxCycles){
		double net = 0;
		double y = 0;
		double e;
		double[] x;
		int cycle = 0;
		EMQ = new double[maxCycles];
		
		while( cycle < maxCycles){
			for(int data=0;data < numPatterns;data++){
				x = patterns[data];
				for(int neuron=0;neuron < NN;neuron++){
					net = 0;
					for(int j=0; j <= NP ;j++){
						net += w[neuron][j]* x[j];
					}
					//funcao sigmoide logistica
					y = 1.0/(1.0+Math.exp(-net));
					System.out.println("y= " + y);
					e = d[data][neuron] - y;
					EMQ[cycle] +=  Math.pow(e, 2.0);
					for(int j=0;j <= NP ;j++){
						w[neuron][j] = w[neuron][j] + alfa * e * x[j] * y  * (1-y);
						System.out.println("w" +neuron+""+j + "= " + w[neuron][j]);
					}
				}
			}
			
			EMQ[cycle] = (EMQ[cycle] / numPatterns);
			System.out.println("Cycle = " + cycle + " EMQ = " +  EMQ[cycle] + "\n");
			cycle++;
		}
		
		System.out.println("Training Finalized in " + cycle + " cycles");
	}

	public void printEMQ(){
		File file = new File("C:/Users/Carlos/Desktop/Cadeiras2010.1/RedesNeurais/RNA_elliackinrar/RNA/EMQ.txt");
		try {
			PrintWriter print = new PrintWriter(file);
			for(int i=0; i < EMQ.length; i++){
				print.println((i+1) + " " + EMQ[i]);
			}
			print.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
