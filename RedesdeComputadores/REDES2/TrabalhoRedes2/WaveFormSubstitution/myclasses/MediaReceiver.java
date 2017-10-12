package myclasses;

import javax.sound.sampled.AudioFormat;

import temp.CODE;
import temp.CircleBuffer;
import temp.CrossCorrelation;
import temp.RecoveryAlgorithm;
import temp.WaveformSubstitutionPatternAlgorithm;

public class MediaReceiver {

	
private AudioFormat audioFormat;
	
	
	private CircleBuffer byteBuffer;
	
	
	
	private int frameSize;
	
	private int maxCapacity;

	
	
	
	public MediaReceiver(int maxCapacity,int frameSize) {
		this.frameSize = frameSize;
		this.byteBuffer = new CircleBuffer(maxCapacity,frameSize);
		
	}
	
	public byte[] getNextFrame(){
		return byteBuffer.removeUnit();
	}
	
	
	public void addPacket(byte[] packet,int seqnum) {
		this.byteBuffer.addUnit(seqnum, packet);
	}
	
	
	
}
