package temp;

import java.util.Hashtable;
import java.util.Set;

import src.RTPpacket;

public class MediaPacketReceiver {

	private static int BufferSizeInPackets;
	private byte[] buffer;
	private boolean isEmpty;
	private int windowLengthInPackets;
	private int nextPacketReadyPosition;
	private int nextPacketReadySeqNum;
	private Set<Integer> receivedPackets;
	private int packetSize;
	
	private RecoveryAlgorithm recoveryAlgorithm;
	
	public MediaPacketReceiver() {
		nextPacketReadyPosition = nextPacketReadySeqNum = 0;
	}
	
	public void wite(RTPpacket packet){	
		
		int startPosition = 0;
		
		packetSize = packet.getpayload_length();				
		startPosition = ((packet.getsequencenumber() - nextPacketReadySeqNum) * packetSize + nextPacketReadyPosition)%buffer.length;
		
		if(packet.getsequencenumber() > nextPacketReadySeqNum)// pacote depois do que vai ser reproduzido
		{
			int windowStartPosition = nextPacketReadyPosition - windowLengthInPackets * packetSize;
			windowStartPosition = windowStartPosition < 0 ? buffer.length - windowStartPosition : windowStartPosition ;
			
			if(startPosition < windowStartPosition)//nao pode escrever sobre o espaco da janela
			{
				addPacket(startPosition,packet.getpayload());
				receivedPackets.add(packet.getsequencenumber());
			}
			
		}
		
	}
		
	public byte[] read(){
		byte[] nextPayload = null;
		
		boolean received = receivedPackets.contains(nextPacketReadySeqNum);
		
		if (received) {
			readPacket(nextPacketReadyPosition,nextPayload);
			
		}else{
			chooseRecoveryAlgorithm();			
			nextPayload = recoveryAlgorithm.getNextPayloadByteArray();
		}
		
		nextPacketReadyPosition = (nextPacketReadyPosition + packetSize) % buffer.length;
		nextPacketReadySeqNum++;
		return nextPayload;
	}
	
	private void chooseRecoveryAlgorithm() {
		boolean patternAlgorithm = false;
		
		for (int i = 1; i <= windowLengthInPackets; i++) {
			patternAlgorithm = patternAlgorithm && receivedPackets.contains(nextPacketReadySeqNum - i);
		}
		if (patternAlgorithm) {
		//	recoveryAlgorithm = new WaveformSubstitutionPatternAlgorithm();
		}

		  //recoveryAlgorithm = ruido ou repeticao;
	}

	private void readPacket(int startPosition,byte[] data){
		for (int i = 0; i < data.length; i++) {
			data[i] = buffer[nextPacketReadyPosition+i];
		}
	}
	private void addPacket(int startPosition,byte[] payload){
		for(int i = startPosition; i < payload.length; i++ ){
			buffer[i + startPosition] = payload[i];
		}
	}
}
