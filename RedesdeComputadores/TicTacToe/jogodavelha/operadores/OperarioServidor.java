package operadores;

import gui.GuiServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Jogo.Jogo;

public class OperarioServidor extends Thread {		//Vai interagir a mensagem com o sistema.

	
	private Mensagem mensagem;
	private GuiServidor gui;
	private Jogo jogo;
	private Servidor servidor;
	private Socket clientSocket;
	
	public OperarioServidor(GuiServidor gui, Servidor servidor, Socket clientSocket) {
		this.gui = gui;
		this.servidor = servidor;
		this.clientSocket = clientSocket;
	}
//Para cada mensagem eh aberto um OperarioServidor.
	public void run() {
		
		try {
			System.out.println("----------------------");
			System.out.println("Novo cliente conectado");
			System.out.println("Endereço IP: " + clientSocket.getInetAddress());
			System.out.println("----------------------");
			ObjectInputStream input;
			input = new ObjectInputStream(clientSocket.getInputStream());
			this.mensagem = ((Mensagem)input.readObject());
			this.trataMensagem();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private void trataMensagem() {
		
		int tipo = mensagem.getTipo();
		
		switch(tipo){
		
		case (1):{
			
			//Escrever no Canal "Usuario esté Online."
			
			//Atualizar a lista de jogadores em todos os clientes.
			
			//Enviar mensagem de confirmação de logon
		}
		
		case(2):{
			
			//System.out.println(mensagem.getMensagemCanal());
			this.servidor.propagarMensagem(mensagem/*, clientSocket.getInetAddress().getHostAddress()*/);
			
			
		}
		}
	}

	
}