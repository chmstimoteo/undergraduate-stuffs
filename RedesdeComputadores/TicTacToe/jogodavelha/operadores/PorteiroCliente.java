package operadores;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PorteiroCliente extends Thread {		
											
	//private Socket socket;
	private Cliente cliente;
	private Mensagem mensagem;
	
	public PorteiroCliente(Mensagem mensagem, Cliente cliente) {
		this.cliente = cliente;
		this.mensagem = mensagem;
		//this.socket = socket;
	}

	public void run() {
		Socket socket;
		
		try {
			socket = new Socket(cliente.getIpServidor(), cliente.getPortaServidor());
			ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());   
	        oo.writeObject(mensagem);
	        System.out.println("Porteiro enviando msm.");
	        socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
