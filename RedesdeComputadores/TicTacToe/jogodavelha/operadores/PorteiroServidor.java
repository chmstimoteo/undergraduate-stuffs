package operadores;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PorteiroServidor extends Thread {   //Eh a classe que se comunica com os clientes.
												//Eh o Worker.
	private Socket clientSocket;
	private Mensagem mensagemEnviarCliente;
	private String ipCliente;
	private int portaCliente;
	
	public PorteiroServidor(Mensagem msm,String ip, Socket socket) {
	this.ipCliente = ip;
	//this.portaCliente= portaCliente;
	this.mensagemEnviarCliente = msm;
	this.clientSocket = socket;
	}
	
	public void run(){
		try {
			System.out.println("Porteiro enviando msm.");
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.writeObject(this.mensagemEnviarCliente);
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}