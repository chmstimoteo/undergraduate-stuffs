package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import operadores.Mensagem;
import operadores.Servidor;

public class PorteiroCanalServidor extends Thread {

	private Socket cliente; 
	private Servidor servidor;
	private Mensagem mensagem;
	
	public PorteiroCanalServidor(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.mensagem = new Mensagem();
	}
	
	public void run(){
		try {
			ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
			Mensagem mensagem = (Mensagem) input.readObject();
			Mensagem resposta = new Mensagem();
			resposta.setMensagemCanal(mensagem.getMensagemCanal());
			resposta.setTipo(2);
			this.servidor.propagarMensagem(resposta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
