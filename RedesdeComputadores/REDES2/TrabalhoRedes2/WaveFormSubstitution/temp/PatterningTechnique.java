package temp;

import java.util.Arrays;
import java.util.Iterator;

import samples.SampleShortSigned;
import samples.SamplesByteUnsigned;
import samples.SamplesIntSigned;
import src.Sample;
import samples.Samples;

public abstract class PatterningTechnique {

	private byte[] buffer;
	private int windowLength;
	private int windowStartPosition;
	private int templateLength;
	private int templateStartPosition;
	private int bytesPerSample;
	private int byteArrayLength;
	private Samples windowSamples;
	private Samples templateSamples;
	private int bestPatternStartPosition;
		

	
//Build a Samples of a type c
	private Samples createSamples(CODE code,byte[] samples)
	{
		
		Samples s = null;
		
		switch(code)
		{
		case BYTE_UNSIGNED: s =  new SamplesByteUnsigned(samples) ;break;
		
		case SHORT_SIGNED: s = new SampleShortSigned(samples);  break;
		
		case INT_SIGNED: s = new SamplesIntSigned(samples); break;
		}
		
		
	  return s;
	}
	
	public void run(){
		int numberWindowSamples = this.windowSamples.getQtdeSamples();
		int numberTemplateSamples = this.templateSamples.getQtdeSamples();
		int numberMatchings = numberWindowSamples - numberTemplateSamples + 1;
		double maxMatchingPercent = -1;
		int relativePosition = 1;
		
		Iterator<Sample> iterator = this.windowSamples.getIterator();
		int position = 1;
		
		while(numberMatchings == 0){
				double temp = matchingPercentAtPosition(position);
				if(temp > maxMatchingPercent){
					maxMatchingPercent = temp;
					this.bestPatternStartPosition = (position - 1) * this.bytesPerSample;
				}
				position++;
				numberMatchings--;
		}
		
		this.bestPatternStartPosition += this.windowStartPosition + this.templateLength;
	}
	
	
	
	
	
	public abstract double matchingPercentAtPosition(int n);
	
	
	
	public PatterningTechnique(byte[] buffer, int windowLength,
			int windowStartPosition, int templateLength,
			int templateStartPosition, int byteArrayLength,CODE code) {
		super();
		this.buffer = buffer;
		this.windowLength = windowLength;
		this.windowStartPosition = windowStartPosition;
		this.templateLength = templateLength;
		this.templateStartPosition = templateStartPosition;
		this.bytesPerSample = bytesPerSample;
		this.byteArrayLength = byteArrayLength;
		
		
		///Modificação do copy of range
		
		byte[] templateBuffer = Arrays.copyOfRange(buffer,this.templateStartPosition, templateStartPosition + this.templateLength);
		this.templateSamples  = createSamples(code,templateBuffer);
		
		byte[] windowBuffer = Arrays.copyOfRange(buffer,this.windowStartPosition,windowStartPosition +  this.windowLength);
		this.windowSamples = createSamples(code,windowBuffer);
	}	
	
	
	
	public byte[] getBuffer() {
		return buffer;
	}


	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}


	public int getWindowLength() {
		return windowLength;
	}


	public void setWindowLength(int windowLength) {
		this.windowLength = windowLength;
	}


	public int getWindowStartPosition() {
		return windowStartPosition;
	}


	public void setWindowStartPosition(int windowStartPosition) {
		this.windowStartPosition = windowStartPosition;
	}


	public int getTemplateLength() {
		return templateLength;
	}


	public void setTemplateLength(int templateLength) {
		this.templateLength = templateLength;
	}


	public int getTemplateStartPosition() {
		return templateStartPosition;
	}


	public void setTemplateStartPosition(int templateStartPosition) {
		this.templateStartPosition = templateStartPosition;
	}


	public int getBytesPerSample() {
		return bytesPerSample;
	}


	public void setBytesPerSample(int bytesPerSample) {
		this.bytesPerSample = bytesPerSample;
	}


	public int getByteArrayLength() {
		return byteArrayLength;
	}


	public void setByteArrayLength(int byteArrayLength) {
		this.byteArrayLength = byteArrayLength;
	}


	public int getBestPatternStartPosition() {
		return bestPatternStartPosition;
	}



	public Samples getBufferSampled() {
		return windowSamples;
	}


	public void setBufferSampled(Samples bufferSampled) {
		this.windowSamples = bufferSampled;
	}



	public Samples getWindowSamples() {
		return windowSamples;
	}



	public Samples getTemplateSamples() {
		return templateSamples;
	}

	public void setWindowSamples(Samples windowSamples) {
		this.windowSamples = windowSamples;
	}

	public void setTemplateSamples(Samples templateSamples) {
		this.templateSamples = templateSamples;
	}

	public void setBestPatternStartPosition(int bestPatternStartPosition) {
		this.bestPatternStartPosition = bestPatternStartPosition;
	}

	
		
}
