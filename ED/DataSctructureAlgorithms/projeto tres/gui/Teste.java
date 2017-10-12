package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Teste {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InicioFrame inicio;
		
		UIManager.LookAndFeelInfo[] aparencias = UIManager.getInstalledLookAndFeels();
		
		try {
	        UIManager.setLookAndFeel(aparencias[1].getClassName());
	        inicio = new InicioFrame();
	       SwingUtilities.updateComponentTreeUI(inicio.getFrame());
	      } catch (Exception e) {
	        
	        e.printStackTrace();
	      }
	      

	}

}
