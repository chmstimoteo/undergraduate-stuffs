package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import operadores.Mensagem;
import operadores.PortasConstantes;
import operadores.Servidor;

public class OuvinteCanalServidor extends Thread {

	private Servidor servidor;
	private Mensagem mensagem;
	private ServerSocket serverSocket;
	private Socket socket;
	
	public OuvinteCanalServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public void run(){
		try {
			this.serverSocket = new ServerSocket(PortasConstantes.portaServidorChat);
			while(true){
				Socket cliente = this.serverSocket.accept();
				Thread.sleep(200);
				PorteiroCanalServidor pcs = new PorteiroCanalServidor(cliente, servidor);
				pcs.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
