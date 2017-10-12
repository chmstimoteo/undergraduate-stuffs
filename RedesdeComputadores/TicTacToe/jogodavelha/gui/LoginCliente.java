package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import operadores.Cliente;
import Jogo.Jogador;
import chat.OuvinteCanalCliente;

public class LoginCliente extends JFrame {

	private JPanel jContentPane = null;

	private JLabel lbLoginText = null;

	private JLabel lbLoginText2 = null;

	private JTextField tfServerIp = null;

	private JTextField tfNickName = null;

	private JButton jbLogar = null;

	/**
	 * This is the default constructor
	 */
	public LoginCliente() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Login - Jogo da Velha");
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbLoginText2 = new JLabel();
			lbLoginText2.setBounds(new java.awt.Rectangle(36, 66, 63, 20));
			lbLoginText2.setText("Nickname");
			lbLoginText = new JLabel();
			lbLoginText.setBounds(new java.awt.Rectangle(36, 41, 63, 20));
			lbLoginText.setText("Server IP");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbLoginText, null);
			jContentPane.add(lbLoginText2, null);
			jContentPane.add(getTfLoginText(), null);
			jContentPane.add(getTfLoginText2(), null);
			jContentPane.add(getJbLogar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tfLoginText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTfLoginText() {
		if (tfServerIp == null) {
			tfServerIp = new JTextField();
			tfServerIp.setBounds(new java.awt.Rectangle(114, 41, 93, 20));
		}
		return tfServerIp;
	}

	/**
	 * This method initializes tfLoginText2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTfLoginText2() {
		if (tfNickName == null) {
			tfNickName = new JTextField();
			tfNickName.setBounds(new java.awt.Rectangle(114, 66, 93, 20));
		}
		return tfNickName;
	}

	/**
	 * This method initializes jbLogar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbLogar() {
		if (jbLogar == null) {
			jbLogar = new JButton();
			jbLogar.setBounds(new java.awt.Rectangle(76, 98, 92, 27));
			jbLogar.setText("Logar");
			jbLogar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					iniciarFrameCliente();
				}
			});
		}
		return jbLogar;
	}

	private void iniciarFrameCliente() {
		String ipServidor = tfServerIp.getText();
		String  nickname= tfNickName.getText();
		Jogador j1 = new Jogador(nickname);
		Cliente cliente = new Cliente(ipServidor, j1);
		
		if(cliente.conectarServidor()){
			JOptionPane.showInputDialog("Erro ao tentar se conectar com o servidor!"); // Acrecentar verificação se conseguiu conectar.
			this.initialize();
		}
		GuiCliente gui = new GuiCliente(cliente);
		gui.setVisible(true);
		this.setEnabled(false);
		this.setVisible(false);
		
		cliente.aguardarMensagem();
		//OuvinteCliente ouvinte = new OuvinteCliente(gui);
		OuvinteCanalCliente ouvinteCanal = new OuvinteCanalCliente(gui);
		ouvinteCanal.start();
	}

} // @jve:decl-index=0:visual-constraint="210,55"
