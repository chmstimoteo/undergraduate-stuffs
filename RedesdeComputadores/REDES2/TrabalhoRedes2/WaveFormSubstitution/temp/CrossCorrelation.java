package temp;

import samples.Samples;



public class CrossCorrelation extends PatterningTechnique{

	
	

	public CrossCorrelation(byte[] buffer, int windowLength,
			int windowStartPosition, int templateLength,
			int templateStartPosition, int byteArrayLength,
			CODE code) {
		
		
		super(buffer, windowLength, windowStartPosition, templateLength,
				templateStartPosition, byteArrayLength, code);
		
	}
	
	

	
	
	public double matchingPercentAtPosition(int n) {
		
		double sum1 = 0;
		double sum2 = 0;
		double prod = 1;
		Samples templateSamples = this.getTemplateSamples();
		Samples windowSamples = this.getWindowSamples();
		
		
		
		if( windowSamples.getQtdeSamples() < n + templateSamples.getQtdeSamples())
			  throw new RuntimeException("Passou o tamanho da janela");
				
			
		
		
		for(int i =1;i<=templateSamples.getQtdeSamples() ;i++)
		{
			
	         prod = templateSamples.getSample(i)
	          			 * windowSamples.getSample(i+n);
	        
	         
	        
			 sum1+= prod;
			
			 prod = windowSamples.getSample(i+n)
			        * windowSamples.getSample(i+n);
			 
			 sum2 += prod;
			 
		}
		
	
		
		return sum1/sum2;
	}

}
