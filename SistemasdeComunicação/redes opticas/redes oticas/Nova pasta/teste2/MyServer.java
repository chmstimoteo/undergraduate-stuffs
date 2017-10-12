package teste2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

class MyServer 
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
    
         ServerSocket welcomeSocket = new ServerSocket(6789);
         DatagramSocket serverSocket = new DatagramSocket(9876);
         DatagramPacket sendPacket;
         Socket connectionSocket;
         
      
          
         while(true) 
         {
            connectionSocket = welcomeSocket.accept();
           
            
            
            System.out.println("acept");
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(
                  connectionSocket.getInputStream()));
            DataOutputStream outToClient =
              
            	new DataOutputStream(
                  connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            
            StringTokenizer st = new StringTokenizer(clientSentence," ");
            
             String ipClient = st.nextToken();
             String portClient = 	st.nextToken(); 
          
        
            System.out.println("IP: " +  ipClient + "port: " + portClient );
            
          
            outToClient.writeBytes("OK\n");
             System.out.println("ok");
             
            
           Thread s = new Thread(new DataSender(serverSocket,connectionSocket.getInetAddress()));
             s.start();
         }
      }
}
