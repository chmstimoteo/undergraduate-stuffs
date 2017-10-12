package temp;

import samples.Samples;

public class SignCorrelation extends PatterningTechnique {

	
	public SignCorrelation(byte[] buffer, int windowLength,
			int windowStartPosition, int templateLength,
			int templateStartPosition, int byteArrayLength,
			CODE code) {
		super(buffer, windowLength, windowStartPosition, templateLength,
				templateStartPosition, byteArrayLength, code);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double matchingPercentAtPosition(int n) {
		
		double sum = 0;
		Samples templateSamples = this.getTemplateSamples();
		Samples windowSamples = this.getWindowSamples();
		
		
		if( windowSamples.getQtdeSamples() < n + templateSamples.getQtdeSamples())
			  throw new RuntimeException("Passou o tamanho da janela");
				
			
		
		for(int i =1;i<=templateSamples.getQtdeSamples() ;i++)
		{
			
	     sum+= sgn(templateSamples.getSample(i) * windowSamples.getSample(n+i));
			 
		}
		
		return sum;
	}
	
	private short sgn(int x)
	{
		if(x > 0)
			return 1;
		
		if(x < 0)
		return -1;
		
		return 0;
		
	}
	
	

}
