package src;

import java.net.DatagramPacket;

public class WaveformSubstitutionPerformance {

	//packetDuration
	//Examples: Tp=8 ms, 16 ms, and 32 ms
	private double _Tp;
	//missingPacketProbability
	private double _p;
	//maximumNumberPacketIntervalsStationarySpecchSignal
	// Isso é o número maximo de pacotes que o sinal de voz pode ser considerado estacionario.
	private double _C = (32.00/_Tp);
	//probability that there is no category transition during any particular packet.
	private double _t = Math.exp(-0.0052*_Tp);
	//probability of unsuccessful waveform substitution
	private double _Pf = 1 - (1 - _p)*((1 - Math.pow((_p*_t),(_C+1)))/(1 - (_p*_t)));
	
	//Waveform Substitution fails when _t occurs or  (missingsPackets > _C) 
	private double missingPackets = ( new DatagramPacket(new byte[1500], 1500).getLength()/ 1411200); /*bitsloss/PCMulaw*/
	
	//Taxa de pacotes perdidos. Ex: 0.19 -> _tp = 8ms, 0.10 -> _tp = 16ms e 0.05 -> _tp = 32ms  
	private double missingPacketRate;
	
	
}
