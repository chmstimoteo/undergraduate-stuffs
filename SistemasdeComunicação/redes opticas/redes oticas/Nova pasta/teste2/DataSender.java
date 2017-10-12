package teste2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



import javax.swing.Timer;


public class DataSender implements Runnable, ActionListener{

	
	private static final int FRAME_PERIOD = 100;
	private static final int MUSIC_LENGTH = 500; // tamanho do arquivo em frames
	private static final int PCM_TYPE = 10;
	
	
	private static final int RTP_dest_port = 25000;
	
	
	private static final int BUFFER_SERVER = 8820;
	
	private DatagramSocket RTPsocket;
	private DatagramPacket sendPacket;
	private InetAddress clientIPAddress; //endereço ip do cliente
	private Timer timer; // usado para enviar os dados na taxa da musica
	private int nFrameMusic = 0;
	private SoundStream sound;
    byte[] sendData;
	

	public DataSender(DatagramSocket serverSocket, InetAddress inetAddress) {
		
	 this.RTPsocket = serverSocket;
	 this.clientIPAddress = inetAddress;
	 
	  sendData = new byte[BUFFER_SERVER];
	 
	 try {
		this.sound = new SoundStream("tempo.wav");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 timer = new Timer(FRAME_PERIOD, this);
	 timer.setInitialDelay(0);
	 timer.setCoalesce(true);
	 
	 timer.start();
	}


	
	

	public void run() {
		
		
				
		
	
    }



	public void actionPerformed(ActionEvent e ) {
		
		
		//n frame da musica 
		if (nFrameMusic < MUSIC_LENGTH)
		{
		//atualiza o pacote atual
		nFrameMusic++;
		 
		try {
		//obter o proximo frame
		int frameLength = sound.getnextframe(sendData);

	
		//Constroi um objeto RTPpacket  contendo o frame
		RTPpacket rtpPacket = new RTPpacket(PCM_TYPE, nFrameMusic, nFrameMusic*FRAME_PERIOD, sendData, frameLength);

		
		int packetLength = rtpPacket.getlength();

		
		//retira os bits do pacote e armazana em um array de bytes
		byte[] bitsPacket = new byte[packetLength];
		rtpPacket.getpacket(bitsPacket);

		//enviar o pacote como um DatagramPacket sobre o socket UDP 
		
		
		
		sendPacket = new DatagramPacket(bitsPacket, packetLength, this.clientIPAddress, RTP_dest_port);
		RTPsocket.send(sendPacket);

		
       
	
		}
		catch(Exception ex)
		{
		System.out.println("Exception caught: "+ex);
		System.exit(0);
		}
		}
		else
		{
		
		//se foi encontrado o fim do arquivo,pare o timer
		timer.stop();
		}
		
	}
}
