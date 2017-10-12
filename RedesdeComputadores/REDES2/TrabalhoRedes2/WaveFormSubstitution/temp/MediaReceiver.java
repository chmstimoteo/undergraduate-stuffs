package temp;

import javax.sound.sampled.AudioFormat;

public class MediaReceiver {

	private AudioFormat audioFormat;
	
	
	private byte[] byteBuffer;
	
	private RecoveryAlgorithm recoveryAlgorithm;	
	
	private int frameSize;

	
	
	
	public MediaReceiver(int frameSize) {
		this.frameSize = frameSize;
		
	}
	
	public byte[] getNextFrame(){
		return null;
	}

}
