package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import operadores.Cliente;

public class GuiCliente extends JFrame {

	private Cliente cliente;
	
	private JPanel jContentPane = null;
	private JList jogadoresCanal = null;
	private JTextField inputMensagensCanal = null;
	private JTextArea mensagensCanal = null;
	private JList jogosCanal = null;

	/**
	 * This is the default constructor
	 * @param cliente 
	 */
	public GuiCliente(Cliente cliente) {
		super();
		this.cliente = cliente;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(570, 303);
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
			jContentPane.add(getJogadoresCanal(), null);
			jContentPane.add(getInputMensagensCanal(), null);
			jContentPane.add(getMensagensCanal(), null);
			jContentPane.add(getJogosCanal(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jlJogadores	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJogadoresCanal() {
		if (jogadoresCanal == null) {
			jogadoresCanal = new JList();
			jogadoresCanal.setBounds(new java.awt.Rectangle(457,44,105,223));
		}
		return jogadoresCanal;
	}

	/**
	 * This method initializes tfCanal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInputMensagensCanal() {
		if (inputMensagensCanal == null) {
			inputMensagensCanal = new JTextField();
			inputMensagensCanal.setBounds(new java.awt.Rectangle(104,238,355,30));
		}
		return inputMensagensCanal;
	}

	/**
	 * This method initializes taCanal	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getMensagensCanal() {
		if (mensagensCanal == null) {
			mensagensCanal = new JTextArea();
			mensagensCanal.setBounds(new java.awt.Rectangle(106,44,351,194));
		}
		return mensagensCanal;
	}

	/**
	 * This method initializes jlCanalJogos	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJogosCanal() {
		if (jogosCanal == null) {
			jogosCanal = new JList();
			jogosCanal.setBounds(new java.awt.Rectangle(1,44,105,223));
		}
		return jogosCanal;
	}

}  //  @jve:decl-index=0:visual-constraint="78,15"
