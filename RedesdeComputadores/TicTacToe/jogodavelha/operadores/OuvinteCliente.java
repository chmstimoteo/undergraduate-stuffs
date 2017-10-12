package operadores;

import gui.GuiCliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Jogo.Jogador;

//Fica ouvindo mensagens do jogo
//Ouve numa porta diferente 7071

public class OuvinteCliente extends Thread {
	
	
	private ServerSocket server;
	private Jogador jogador;
	private GuiCliente gui;
	private Cliente cliente;
	
	
	public OuvinteCliente(GuiCliente gui, Cliente cliente /*, Jogador jogador*/){
		//this.jogador = jogador;
		this.gui = gui;
		this.cliente = cliente;
		
	}

	public void run(){		
		try {
			this.server = new ServerSocket(PortasConstantes.portaCliente);
			Socket clientSocket = server.accept();
			this.server.close();
			System.out.println("Cliente ouvindo " + clientSocket.getInetAddress().toString());
			OperarioCliente handler = new OperarioCliente(clientSocket, gui, cliente);
			handler.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


}
