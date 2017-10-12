package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GuiServidor extends JFrame {

	private JPanel jContentPane = null;
	private JTextArea taConsole = null;

	/**
	 * This is the default constructor
	 */
	public GuiServidor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(446, 337);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTaConsole(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes taConsole	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTaConsole() {
		if (taConsole == null) {
			taConsole = new JTextArea();
			taConsole.setBounds(new java.awt.Rectangle(-1,0,440,304));
		}
		return taConsole;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
