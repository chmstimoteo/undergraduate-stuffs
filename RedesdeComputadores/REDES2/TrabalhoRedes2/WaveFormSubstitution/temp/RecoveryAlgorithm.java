package temp;

public abstract class RecoveryAlgorithm {

	/*** past samples ***/
	protected byte[] buffer;
	private  int bytesPerSample;
	private int bytePayloadLength;
		

	
	public RecoveryAlgorithm(byte[] buffer,int bytesPerSample,int byteArrayLength) {
		super();
		this.buffer = buffer;
		this.bytesPerSample = bytesPerSample;
		this.bytePayloadLength = byteArrayLength;
	}
	
	
	public abstract byte[] getNextPayloadByteArray();


	public final int getBytesPerSample() {
		return bytesPerSample;
	}


	public final void setBytesPerSample(int bytesPerSample) {
		this.bytesPerSample = bytesPerSample;
	}


	public final int getByteArrayLength() {
		return bytePayloadLength;
	}


	public final void setByteArrayLength(int byteArrayLength) {
		this.bytePayloadLength = byteArrayLength;
	}
	
	

}
