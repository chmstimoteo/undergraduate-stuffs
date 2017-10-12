package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Teste {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InicioFrame inicio;// o frame
		//pegando as aparencias
		UIManager.LookAndFeelInfo[] aparencias = UIManager.getInstalledLookAndFeels();
		
		try {
	        UIManager.setLookAndFeel(aparencias[2].getClassName());//pegando ma das aparencias.
	        inicio = new InicioFrame();
	       SwingUtilities.updateComponentTreeUI(inicio.getFrame());
	      } catch (Exception e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	      }
	      

	}

}
