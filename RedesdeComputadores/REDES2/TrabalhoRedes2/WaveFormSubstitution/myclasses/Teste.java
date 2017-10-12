package myclasses;

import temp.CODE;
import temp.CrossCorrelation;
import temp.PatterningTechnique;
import temp.WaveformSubstitutionPatternAlgorithm;

public class Teste {

	
	
	
   
	public static void main(String[] args) {
		
		
		byte[] b = {1,2,3,4,5,6,7,8,9,10};
		
		//byte[] buffer,int bytesPerSample,int byteArrayLength, int windowLength,
		//int windowStartPosition, int templateLength,PatterningTechnique patterningTechnique
		//
		
		
				MediaReceiver mediaReceiver = new MediaReceiver(10,10);
		
				mediaReceiver.addPacket(b, 1);
				mediaReceiver.addPacket(b, 2);
			
				
				mediaReceiver.getNextFrame();
				byte[] x = mediaReceiver.getNextFrame();
				
				for (int i = 0; i < x.length; i++) {
					System.out.print(x[i] + " ");
				}
		
		      
		
	}
	
	
	
}
