package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TabuleiroCliente extends JFrame {

	private JPanel jContentPane = null;
	private JButton l1c1 = null;
	private JButton l1c2 = null;
	private JButton l1c3 = null;
	private JButton l2c1 = null;
	private JButton l2c2 = null;
	private JButton l2c3 = null;
	private JButton l3c1 = null;
	private JButton l3c2 = null;
	private JButton l3c3 = null;
	private JTextArea jtChatCliente = null;
	private JTextField tfChatCliente = null;
	/**
	 * This is the default constructor
	 */
	public TabuleiroCliente() {
		super("L&F");
		initialize();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(423, 351);
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
			jContentPane.add(getL1c1(), null);
			jContentPane.add(getL1c2(), null);
			jContentPane.add(getL1c3(), null);
			jContentPane.add(getL2c1(), null);
			jContentPane.add(getL2c2(), null);
			jContentPane.add(getL2c3(), null);
			jContentPane.add(getL3c1(), null);
			jContentPane.add(getL3c2(), null);
			jContentPane.add(getL3c3(), null);
			jContentPane.add(getJtChatCliente(), null);
			jContentPane.add(getTfChatCliente(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes l1c1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL1c1() {
		if (l1c1 == null) {
			l1c1 = new JButton();
			l1c1.setBounds(new java.awt.Rectangle(50,32,50,50));
		}
		return l1c1;
	}

	/**
	 * This method initializes l1c2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL1c2() {
		if (l1c2 == null) {
			l1c2 = new JButton();
			l1c2.setBounds(new java.awt.Rectangle(125,32,50,50));
		}
		return l1c2;
	}

	/**
	 * This method initializes l1c3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL1c3() {
		if (l1c3 == null) {
			l1c3 = new JButton();
			l1c3.setBounds(new java.awt.Rectangle(204,32,50,50));
		}
		return l1c3;
	}

	/**
	 * This method initializes l2c1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL2c1() {
		if (l2c1 == null) {
			l2c1 = new JButton();
			l2c1.setBounds(new java.awt.Rectangle(50,103,50,50));
		}
		return l2c1;
	}

	/**
	 * This method initializes l2c2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL2c2() {
		if (l2c2 == null) {
			l2c2 = new JButton();
			l2c2.setBounds(new java.awt.Rectangle(125,103,50,50));
		}
		return l2c2;
	}

	/**
	 * This method initializes l2c3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL2c3() {
		if (l2c3 == null) {
			l2c3 = new JButton();
			l2c3.setBounds(new java.awt.Rectangle(204,103,50,50));
		}
		return l2c3;
	}

	/**
	 * This method initializes l3c1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL3c1() {
		if (l3c1 == null) {
			l3c1 = new JButton();
			l3c1.setBounds(new java.awt.Rectangle(50,174,50,50));
		}
		return l3c1;
	}

	/**
	 * This method initializes l3c2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL3c2() {
		if (l3c2 == null) {
			l3c2 = new JButton();
			l3c2.setBounds(new java.awt.Rectangle(125,174,50,50));
		}
		return l3c2;
	}

	/**
	 * This method initializes l3c3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getL3c3() {
		if (l3c3 == null) {
			l3c3 = new JButton();
			l3c3.setBounds(new java.awt.Rectangle(204,174,50,50));
		}
		return l3c3;
	}

	/**
	 * This method initializes jtChatCliente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtChatCliente() {
		if (jtChatCliente == null) {
			jtChatCliente = new JTextArea();
			jtChatCliente.setBounds(new java.awt.Rectangle(52,242,208,44));
			jtChatCliente.setEditable(false);
			jtChatCliente.setLineWrap(true);
		}
		return jtChatCliente;
	}

	/**
	 * This method initializes tfChatCliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfChatCliente() {
		if (tfChatCliente == null) {
			tfChatCliente = new JTextField();
			tfChatCliente.setBounds(new java.awt.Rectangle(51,286,210,27));
		}
		return tfChatCliente;
	}

	public static void main (String[] args) {
		new TabuleiroCliente().setVisible(true);
	}
}  //  @jve:decl-index=0:visual-constraint="145,11"
