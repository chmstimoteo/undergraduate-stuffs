package aplicacao;

import gui.GuiServidor;
import operadores.Servidor;
import chat.OuvinteCanalServidor;

public class AplicacaoServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GuiServidor gui = new GuiServidor();
		gui.setVisible(true);
		Servidor servidor = new Servidor(gui);
		servidor.aguardarConexoes();
		OuvinteCanalServidor canal = new OuvinteCanalServidor(servidor);
		//OuvinteServidor controle = new OuvinteServidor(servidor);
	}
}
