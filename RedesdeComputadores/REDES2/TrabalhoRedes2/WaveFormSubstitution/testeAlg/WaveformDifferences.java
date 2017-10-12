package testeAlg;

import java.util.Iterator;

import samples.Samples;
import src.Sample;

public class WaveformDifferences extends PatterningTechnique{

	

	public WaveformDifferences(byte[] buffer, int windowLength,
			int windowStartPosition, int templateLength,
			int templateStartPosition, int byteArrayLength,
			CODE code) {
		super(buffer, windowLength, windowStartPosition, templateLength,
				templateStartPosition, byteArrayLength, code);
		// TODO Auto-generated constructor stub
	}

	public void run(){
		int numberWindowSamples = getWindowSamples().getQtdeSamples();
		int numberTemplateSamples = getTemplateSamples().getQtdeSamples();
		int numberMatchings = numberWindowSamples - numberTemplateSamples + 1;
		double maxMatchingPercent = -1;
		int relativePosition = 1;
		
		Iterator<Sample> iterator = getWindowSamples().getIterator();
		int position = 1;
		
		while(numberMatchings == 0){
				double temp = matchingPercentAtPosition(position);
				
				if(Math.abs(temp-0) < Math.abs(maxMatchingPercent-0)){
					maxMatchingPercent = temp;
					setBestPatternStartPosition((position - 1) * getBytesPerSample());
				}
				
				position++;
				numberMatchings--;
		}
	}
	
	@Override
	public double matchingPercentAtPosition(int n) {
		
		
		double sum = 0;
		Samples templateSamples = this.getTemplateSamples();
		Samples windowSamples = this.getWindowSamples();
		double sumx = 0.0;
		double sumy = 0.0;
		
		
		if( windowSamples.getQtdeSamples() < n + templateSamples.getQtdeSamples())
		  throw new RuntimeException("Passou o tamanho da janela");
			
			
		for(int j=1;j<=templateSamples.getQtdeSamples();j++)
		{
			 sumx+=Math.abs(templateSamples.getSample(j));
			 sumy += Math.abs(windowSamples.getSample(j+n));
		}
		
		for(int i =1;i<=templateSamples.getQtdeSamples() ;i++)
		{
			
	     sum+= Math.abs((templateSamples.getSample(i)/sumx)-(windowSamples.getSample(n+i) / sumy) );
			 
		}
		
		return sum;

	}

}
