package src;

public class PatternMatchingConstants {
	
	/* 1 fonema tem aproximadamente 600 ms
	 * 1 pacote dura aproximadamente 150 ms em PCM lei u
	 * 1 amostra de PCM lei u tem aproximadamente 32 bits = 4 bytes
	 * O buffer tem aproximadamente 4000 bytes.
	 * Qtas amostras eu coloco num pacote?
	*/
	protected static final double TM = 0.001;
	protected static final double TP = 0.008;
	protected static final int M = 8;
	protected static final int N = 128;
	protected static final int bytesPerSample = 4;
	protected static final int bytesPerPacket = 256;
	protected static final int samplesPerpacket = 64;

}
