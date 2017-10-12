package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

public class MyFrame {

	private JFrame inicio = null;  //  @jve:decl-index=0:visual-constraint="144,33"
	private JPanel jContentPane = null;
	private JPanel jPanelOption = null;
	private JPanel jPanelTabbe = null;
	private JLabel jLabel = null;
	private JProgressBar jProgressBar = null;  //  @jve:decl-index=0:visual-constraint="789,50"
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (inicio == null) {
			inicio = new JFrame();
			inicio.setBounds(new java.awt.Rectangle(0,0,640,480));
			inicio.setTitle("Sort count");
			inicio.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gui/icone.gif")));
			inicio.setContentPane(getJContentPane());
		}
		return inicio;
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
			jContentPane.add(getJPanelOption(), null);
			jContentPane.add(getJPanelTabbe(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelOption	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOption() {
		if (jPanelOption == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(4,101,145,330));
			jLabel.setIcon(new ImageIcon(getClass().getResource("/gui/detalhe.gif")));
			jLabel.setText("JLabel");
			jPanelOption = new JPanel();
			jPanelOption.setLayout(null);
			jPanelOption.setBounds(new java.awt.Rectangle(2,3,150,441));
			jPanelOption.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,160),2), "OPTION", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			jPanelOption.add(jLabel, null);
		}
		return jPanelOption;
	}

	/**
	 * This method initializes jPanelTabbe	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTabbe() {
		if (jPanelTabbe == null) {
			jPanelTabbe = new JPanel();
			jPanelTabbe.setBounds(new java.awt.Rectangle(148,3,483,441));
			jPanelTabbe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRINCIPAL", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
		}
		return jPanelTabbe;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setSize(new java.awt.Dimension(142,29));
		}
		return jProgressBar;
	}

}
