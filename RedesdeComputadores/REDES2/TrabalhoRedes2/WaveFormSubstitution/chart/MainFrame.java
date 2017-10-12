package chart;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;

import org.jfree.ui.RefineryUtilities;

import teste.ReadSound;

public class MainFrame extends JFrame {

	private JPanel jContentPane = null;
	private JPanel jGraficoPanel = null;
	private JPanel jControlePanel = null;
	private JLabel jMenuLabel = null;
	private JLabel jTamanhoJanelaLabel = null;
	private JSlider jTamanhoJanelaSlider = null;
	private JLabel jTamanhoTemplateLabel = null;
	private JLabel jTamanhoPacoteLabel = null;
	private JSlider jTamanhoTemplateSlider = null;
	private JSlider jTamanhoPacoteSlider = null;
	private JButton jPlayButton = null;
	private JFileChooser chooser = null;
	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("WaveForm Substitution");
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setBounds(new java.awt.Rectangle(0,0,1280,770));
		this.setVisible(true);
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
			jContentPane.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			jContentPane.add(getJGraficoPanel(), null);
			jContentPane.add(getJControlePanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jGraficoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJGraficoPanel() {
		if (jGraficoPanel == null) {
			jGraficoPanel = new JPanel();
			jGraficoPanel.setBounds(new java.awt.Rectangle(28,59,842,628));
			jGraficoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red,2));
			addGrafico(this.jGraficoPanel);
		}
		return jGraficoPanel;
	}

	private void addGrafico(JPanel jGraficoPanel) {
		ReadSound rs;
		try {
			rs = new ReadSound("voz.wav");
			
			rs.fillBuffer();
			DrawChart chart = new DrawChart("",rs,rs.getAudioFormat());
			Container painel = chart.getContentPane();
			painel.setBounds(jGraficoPanel.getBounds());
			jGraficoPanel.add(painel);
			chart.setVisible(true);
			System.out.println(rs.getAudioFormat().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * This method initializes jControlePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJControlePanel() {
		if (jControlePanel == null) {
			jTamanhoPacoteLabel = new JLabel();
			jTamanhoPacoteLabel.setBounds(new java.awt.Rectangle(15,285,140,28));
			jTamanhoPacoteLabel.setText("Tamanho do Pacote");
			jTamanhoTemplateLabel = new JLabel();
			jTamanhoTemplateLabel.setBounds(new java.awt.Rectangle(16,181,133,28));
			jTamanhoTemplateLabel.setText("Tamanho do Template");
			jTamanhoJanelaLabel = new JLabel();
			jTamanhoJanelaLabel.setBounds(new java.awt.Rectangle(17,67,143,27));
			jTamanhoJanelaLabel.setText("Tamanho da Janela");
			jMenuLabel = new JLabel();
			jMenuLabel.setBounds(new java.awt.Rectangle(170,16,39,37));
			jMenuLabel.setText("Menu");
			jControlePanel = new JPanel();
			jControlePanel.setLayout(null);
			jControlePanel.setBounds(new java.awt.Rectangle(891,62,355,624));
			jControlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.activeCaption,2));
			jControlePanel.add(jMenuLabel, null);
			jControlePanel.add(jTamanhoJanelaLabel, null);
			jControlePanel.add(getJTamanhoJanelaSlider(), null);
			jControlePanel.add(jTamanhoTemplateLabel, null);
			jControlePanel.add(jTamanhoPacoteLabel, null);
			jControlePanel.add(getJTamanhoTemplateSlider(), null);
			jControlePanel.add(getJTamanhoPacoteSlider(), null);
			jControlePanel.add(getJPlayButton(), null);
		}
		return jControlePanel;
	}

	/**
	 * This method initializes jTamanhoJanelaSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJTamanhoJanelaSlider() {
		if (jTamanhoJanelaSlider == null) {
			jTamanhoJanelaSlider = new JSlider();
			jTamanhoJanelaSlider.setBounds(new java.awt.Rectangle(32,108,283,48));
			jTamanhoJanelaSlider.setMinimum(1);
			jTamanhoJanelaSlider.setMinorTickSpacing(1);
			jTamanhoJanelaSlider.setPaintLabels(true);
			jTamanhoJanelaSlider.setPaintTicks(true);
			jTamanhoJanelaSlider.setToolTipText("Tamanho da Janela em Pacotes");
			jTamanhoJanelaSlider.setMajorTickSpacing(1);
			jTamanhoJanelaSlider.setSnapToTicks(true);
			jTamanhoJanelaSlider.setValue(1);
			jTamanhoJanelaSlider.setMaximum(10);
		}
		return jTamanhoJanelaSlider;
	}

	/**
	 * This method initializes jTamanhoTemplateSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJTamanhoTemplateSlider() {
		if (jTamanhoTemplateSlider == null) {
			jTamanhoTemplateSlider = new JSlider();
			jTamanhoTemplateSlider.setBounds(new java.awt.Rectangle(35,224,291,47));
			jTamanhoTemplateSlider.setMaximum(50);
			jTamanhoTemplateSlider.setMinimum(10);
			jTamanhoTemplateSlider.setMinorTickSpacing(1);
			jTamanhoTemplateSlider.setPaintLabels(true);
			jTamanhoTemplateSlider.setPaintTicks(true);
			jTamanhoTemplateSlider.setSnapToTicks(true);
			jTamanhoTemplateSlider.setToolTipText("Tamanho do Template em Bytes");
			jTamanhoTemplateSlider.setValue(10);
			jTamanhoTemplateSlider.setMajorTickSpacing(5);
		}
		return jTamanhoTemplateSlider;
	}

	/**
	 * This method initializes jTamanhoPacoteSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJTamanhoPacoteSlider() {
		if (jTamanhoPacoteSlider == null) {
			jTamanhoPacoteSlider = new JSlider();
			jTamanhoPacoteSlider.setBounds(new java.awt.Rectangle(18,330,328,49));
			jTamanhoPacoteSlider.setMinimum(20);
			jTamanhoPacoteSlider.setMajorTickSpacing(10);
			jTamanhoPacoteSlider.setMinorTickSpacing(5);
			jTamanhoPacoteSlider.setSnapToTicks(true);
			jTamanhoPacoteSlider.setPaintLabels(true);
			jTamanhoPacoteSlider.setPaintTicks(true);
			jTamanhoPacoteSlider.setToolTipText("Tamanho do Pacote em Bytes");
			jTamanhoPacoteSlider.setValue(20);
			jTamanhoPacoteSlider.setMaximum(150);
		}
		return jTamanhoPacoteSlider;
	}

	/**
	 * This method initializes jPlayButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJPlayButton() {
		if (jPlayButton == null) {
			jPlayButton = new JButton();
			jPlayButton.setBounds(new java.awt.Rectangle(95,534,165,34));
			jPlayButton.setText("PLAY");
		}
		return jPlayButton;
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();

	}
}  //  @jve:decl-index=0:visual-constraint="149,90"
