package operadores;

import gui.GuiCliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Jogo.Jogador;

public class Cliente {

	private String ipServidor;
	private int portaServidor;
	private Jogador jogador;
	private GuiCliente guiclient;
	private Socket clientSocket;
	
	//Inserir na GUICliente um textfield para leitura do ipnumero do servidor
	
	public Cliente(String ipServidor, Jogador jogador/*, String ipServidor*/){
		//this.ipServidor = ipServidor;
		this.ipServidor = PortasConstantes.ipServidor;
		this.portaServidor = PortasConstantes.portaServidor;
		this.jogador = jogador;
		
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public boolean conectarServidor(){
		
		try {
			clientSocket = new Socket(this.ipServidor, this.portaServidor);
			//this.jogador.setIpCliente(clientSocket.getLocalAddress().getHostAddress());
			this.jogador.setIpCliente("localhost");
			System.out.println("Ip do jogador setado como: " + this.jogador.getIpCliente());
			System.out.println("Conectado a " + this.ipServidor + ":" + this.portaServidor);      
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void aguardarMensagem(){
		OuvinteCliente listener = new OuvinteCliente(guiclient, this);
		listener.start();
	}
	
	public void enviarMensagem(Mensagem mensagem){
		PorteiroCliente worker = new PorteiroCliente(mensagem, this);
		worker.start();
	}
	
public String getIpServidor() {
	return ipServidor;
}

public void setIpServidor(String ipServidor) {
	this.ipServidor = ipServidor;
}

public int getPortaServidor() {
	return portaServidor;
}

public GuiCliente getGuiclient() {
	return guiclient;
}

public Socket getClientSocket() {
	return clientSocket;
}

public void setPortaServidor(int portaServidor) {
	this.portaServidor = portaServidor;
}

}
