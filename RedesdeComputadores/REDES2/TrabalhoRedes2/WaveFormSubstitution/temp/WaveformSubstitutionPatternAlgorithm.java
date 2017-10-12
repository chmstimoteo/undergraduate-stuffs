package temp;

import java.util.Arrays;

public class WaveformSubstitutionPatternAlgorithm extends RecoveryAlgorithm{


	private int windowLength;
	private int windowStartPosition;
	private int templateLength;
	private int templateStartPosition;
	private PatterningTechnique patterningTechnique;

	
	
	@Override
	public byte[] getNextPayloadByteArray() {
		byte[] byteArray = new byte[this.getByteArrayLength()];
		int startPosition;
		
		if(this.patterningTechnique != null){
			this.patterningTechnique.run();
			startPosition = this.patterningTechnique.getBestPatternStartPosition();
		}
		else{
			startPosition = 0;
		}
		
		//Arrays.copyOfRange(buffer,startPosition,this.getByteArrayLength());
		
		return byteArray;
	}
	
	
	public WaveformSubstitutionPatternAlgorithm(byte[] buffer,int bytesPerSample,int byteArrayLength, int windowLength,
			int windowStartPosition, int templateLength,PatterningTechnique patterningTechnique){
		super(buffer,bytesPerSample,byteArrayLength);
		this.buffer = buffer;
		this.windowLength = windowLength;
		this.windowStartPosition = windowStartPosition;
		this.templateLength = templateLength;
		this.patterningTechnique = patterningTechnique;
	}
	

	public final PatterningTechnique getPatterningTechnique() {
		return patterningTechnique;
	}


	public final void setPatterningTechnique(PatterningTechnique patterningTechnique) {
		this.patterningTechnique = patterningTechnique;
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
	
}
