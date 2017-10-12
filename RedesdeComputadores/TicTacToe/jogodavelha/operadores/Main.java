package operadores;

import gui.GuiServidor;
import Jogo.Jogador;

public class Main {

	
	public static void main(String[] args) {
		
			Jogador jogador = new Jogador("caique");
			Cliente cliente = new Cliente(PortasConstantes.ipServidor,jogador);
			Servidor servidor = new Servidor(new GuiServidor());
			servidor.aguardarConexoes();
			cliente.conectarServidor();
			cliente.aguardarMensagem();
			servidor.addJogadores(jogador);
			servidor.addCliente(cliente);
			//Scanner in = new Scanner(System.in);
			//String msn = in.nextLine();
			//in.close();
			Mensagem mensagem = new Mensagem("carlos", 2);
			//cliente.enviarMensagem(mensagem);
			servidor.propagarMensagem(mensagem);
			
			//cliente.enviarMensagem(new Mensagem("beleza",2));
	}

}
