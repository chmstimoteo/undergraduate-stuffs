package operadores;

import gui.GuiCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class OperarioCliente extends Thread{

	private Mensagem mensagem;
	private GuiCliente gui;
	private Socket clientSocket;
	private Cliente cliente;
	
	public OperarioCliente(Socket clientSocket,GuiCliente gui, Cliente cliente){
		this.gui = gui;
		this.clientSocket = clientSocket;
		this.cliente = cliente;
	}

	public void run(){
		try {
			ObjectInputStream input =  new ObjectInputStream(this.clientSocket.getInputStream());
			this.mensagem = ((Mensagem)input.readObject());
			this.trataMensagem();
			this.clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void trataMensagem() {

		int tipo = this.mensagem.getTipo();

		switch (tipo) {
						case (2): {
								System.out.println(mensagem.getMensagemCanal());
						}
						case(1):{
							Mensagem msm = new Mensagem("OK, saindo msm.",2 );
							//cliente.enviarMensagem(msm);
				}

		}

	}
	

}
