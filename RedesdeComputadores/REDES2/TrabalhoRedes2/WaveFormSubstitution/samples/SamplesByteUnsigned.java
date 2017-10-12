package samples;

import temp.TypeConverter;

public class SamplesByteUnsigned extends Samples{

	
	private short[] samples;

	
	
	public SamplesByteUnsigned(byte[] samples) {
	
		
		this.samples = createSamples(samples);
		
		
	}
	
	

	public short[] createSamples(byte[] samples) {
	
		
		short[] temp = new short[samples.length];
		
		
		for (int i = 0; i <temp.length; i++) {
			
			
			
			temp[i] = TypeConverter.byteSignedToUnsegned(samples[i]);
			
		}
		
		
		return temp;
	}



	public short[] getSamples() {
		return samples;
	}



	@Override
	//n 1..n
	public int getSample(int n) {
		// TODO Auto-generated method stub
		return samples[n-1];
	}



	@Override
	public int getQtdeSamples() {
		// TODO Auto-generated method stub
		return samples.length;
	}
	
	
	


}
