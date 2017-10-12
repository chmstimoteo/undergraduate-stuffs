package src;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.Timer;



class MyClient extends JFrame implements ActionListener
{
 

 private Timer timer; //timer usado para receber dados do UDP socket
 private byte[] bufferClient;
 
 
 private DatagramPacket receavePacket;
 private DatagramSocket RTPsocket; //socket usado para enviar e receber pacotes UDP 
 private static int RTP_RCV_PORT = 25000; //porta onde o cliente ira receber os pacotes RTP
 private static int SIZE_BUFFER_CLINT = 15000;
 
 //tocador
 private DataPlayer dataPlayer;
 
 public MyClient() {
	
	 timer = new Timer(20, this);
	 timer.setInitialDelay(0);
	 timer.setCoalesce(true);


	 bufferClient = new byte[SIZE_BUFFER_CLINT];    

	 init();
	 

     timer.start();
     
    
	 
}
 
public static void main(String argv[]) throws Exception
 {
	 
 
  
  
  String sentence;
  String sentenceFromServer;
  
  Socket clientSocket = new Socket("localhost", 6789);
  
  DataOutputStream outToServer = new DataOutputStream(
     clientSocket.getOutputStream());
  
  DataInputStream inFromServer = new DataInputStream(  clientSocket.getInputStream());
  
  
  sentence = clientSocket.getInetAddress().getHostAddress()
    + " " + clientSocket.getPort() ;
  
  outToServer.writeBytes(sentence + '\n');
  
  System.out.println("enviei");
  sentenceFromServer = inFromServer.readLine();
  System.out.println("FROM SERVER: " + 
          sentenceFromServer);
  
  MyClient mClient  = new MyClient();
  mClient.setVisible(true);
  mClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  

 
  
}


private void init()
{
	//Init non-blocking RTPsocket that will be used to receive data
	  try{
	    //construct a new DatagramSocket to receive RTP packets from the server, on port RTP_RCV_PORT
	    RTPsocket = new DatagramSocket(RTP_RCV_PORT);

	    //set TimeOut value of the socket to 5msec.
	    RTPsocket.setSoTimeout(5);
	  }
	  catch (SocketException se)
	    {
	      System.out.println("Socket exception: "+se);
	      System.exit(0);
	    }
}




public void actionPerformed(ActionEvent e) {
	
	
    //Construct a DatagramPacket to receive data from the UDP socket
    receavePacket = new DatagramPacket(bufferClient, bufferClient.length);

    try{
	//recebendo o DatagramPacket do socket UDP :
	RTPsocket.receive(receavePacket);
	  
	
	RTPpacket rtp_packet = new RTPpacket(receavePacket.getData(), receavePacket.getLength());



	System.out.println("Got RTP packet with SeqNum # "+rtp_packet.getsequencenumber()+" TimeStamp "+rtp_packet.gettimestamp()+" ms, of type "+rtp_packet.getpayloadtype());
	
	


	//get the payload bitstream from the RTPpacket object
	int payload_length = rtp_packet.getpayload_length();
	byte [] payload = new byte[payload_length];
	rtp_packet.getpayload(payload);

	
	if(dataPlayer == null)
	{
		 dataPlayer = new DataPlayer();
	     dataPlayer.start();
	}
	
    
	/*for (int i = 0; i < payload.length; i++) {
	
		 System.out.print(payload[i]);
	}
	System.out.println();*/
	
	dataPlayer.play(payload,0,payload_length);

  }
    catch (InterruptedIOException iioe){
	 System.out.println("Nada para ler");
    }
    catch (IOException ioe) {
	System.out.println("Exception caught: "+ioe);
    }
	
}




 
 
}

