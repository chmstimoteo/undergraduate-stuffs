package samples;

import temp.CODE;
import temp.CrossCorrelation;
import temp.SignCorrelation;
import temp.WaveformDifferences;

public class Testes {

	
	public static void main(String[] args) {
		
		
	
		
	//-----	CrossCorrelation----------
    byte[] b = {0,0,0,1,0,0,0,2,0,5};
		
	CrossCorrelation cc = new CrossCorrelation(b,8,0,4,0,10,CODE.INT_SIGNED);	
		
	double x =  cc.matchingPercentAtPosition(1);
	
	System.out.println(x);
	 
	}
	
	
}
