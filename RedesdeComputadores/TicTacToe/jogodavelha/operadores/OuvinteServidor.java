package operadores;

import gui.GuiServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OuvinteServidor extends Thread{   //Eh o Listener.

	private Servidor servidor;
	private ServerSocket servidorSocket;
	private Socket clientSocket;
	private GuiServidor gui;
	private final int portaServidor = PortasConstantes.portaServidor;
	
	
	public OuvinteServidor(Servidor s, GuiServidor gui, Socket cliente){
		this.servidor = s;
		this.gui = gui;
		this.clientSocket = cliente;
	}

	public void run(){
			
		OperarioServidor
	
	}
	
	
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public ServerSocket getServidorSocket() {
		return servidorSocket;
	}

	public GuiServidor getGui() {
		return gui;
	}

	public int getPortaServidor() {
		return portaServidor;
	}

	public void fecharServidor(){
		try {
			this.servidorSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
