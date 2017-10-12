package gui;

import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import exceptions.AvisoException;
import factory.Factory;





public class InicioFrame {

	/**
	 * 
	 */
	private JFrame inicio = null;    //Frame principal
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel optionPanel = null;
	private JButton jButton = null;
	private JPanel tablePanel1 = null;
	private JTabbedPane abasPane = null;
	private JPanel orderedPanel = null;
	private JLabel detalheLabel = null;
	private JTable jTable = null;
	private Factory factory; 
	
	//Classe Factory que vai fabricar os tempos.
	
	/**
	 * This is the default constructor
	 * @throws  
	 */
	
	public InicioFrame(){
		this.inicio = new JFrame();
		initialize();
		this.factory = new Factory();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws InterruptedException 
	 */
	private void initialize(){
		this.inicio.setResizable(false);
		this.inicio.setContentPane(getJContentPane());
		this.inicio.setTitle("Tree Counter");
		this.inicio.setIconImage(Toolkit.getDefaultToolkit().getImage("gui/icone.gif"));
		this.inicio.setName("inicioFrame");
		this.inicio.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.inicio.setBounds(new java.awt.Rectangle(0,0,640,480));
		this.inicio.setVisible(true);
		
		//Setando a visibilidade da Jtable
		this.getJTable().setVisible(true);
		
		
		
	}

	public JFrame getFrame(){
		return this.inicio;
	}
	
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setToolTipText("TreeCounter vs1.0 \nDevelopment by Carlos Henrique Maciel and Elliackin Messias do Nascimento \nDisciplina: Estrutura de Dados UPE / POLI. \n01 de junho de 2007.");
			jContentPane.add(getOptionPanel(), null);
			jContentPane.add(getTablePanel1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes optionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOptionPanel() {
		if (optionPanel == null) {
			detalheLabel = new JLabel();
			detalheLabel.setBounds(new java.awt.Rectangle(4,112,139,339));
			detalheLabel.setIcon(new ImageIcon("gui/detalhe.gif"));
			detalheLabel.setText("");
			optionPanel = new JPanel();
			optionPanel.setLayout(null);
			optionPanel.setBounds(new java.awt.Rectangle(1,0,145,444));
			optionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OPTION", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", java.awt.Font.BOLD, 12), null));
			optionPanel.add(getJButton(), null);
			optionPanel.add(detalheLabel, null);
		}
		return optionPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Executar");
			Icon icon = new ImageIcon(getClass().getResource("icone.gif"));
			jButton.setRolloverIcon(icon);
			jButton.setBounds(new java.awt.Rectangle(5,54,134,31));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					atualizarDadosTabela(getOrderedPanel()); //atualiza os dados da tabela.
				}
			});
		}
		return jButton;
	}
	

	public void atualizarDadosTabela(JPanel atual) {
		// TODO Auto-generated method stub
		//Pegar os dados produzidos pelos metodos.
		String[] timesOfSearch = null;
		String[] timesOfInsertion = null;
		
		try {
	      timesOfInsertion = this.factory.insertion();
	      timesOfSearch    = this.factory.search();
	      
		} catch (AvisoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		String bin = new String("BinaryTree");
		String avl = new String("AVLTree");
		String ins = new String("InsertionTree");
		
		String alg = new String(" Algoritmo");
		Object[] v1 = {alg,ins,bin,avl};
		Object[][] v2 = {{alg,ins,bin,avl},{" Inserção",timesOfInsertion[0] + " us",timesOfInsertion[1] + " us",timesOfInsertion[2] + " us"},{" Pesquisa", timesOfSearch[0] + " us", timesOfSearch[1] + " us", timesOfSearch [2] + " us"}};
		JTable tabela2 = new JTable(v2,v1); // construindo a tabela.
		
		tabela2.setBounds(new java.awt.Rectangle(63,139,350,50));
		tabela2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tabela2.setEnabled(false);  //Faz com que nao possa ser alterado os valores da tabela pelo usuario.
		
		atual.removeAll();
		atual.add(tabela2);
		atual.repaint();
		atual.setFocusable(true);
		getAbasPane().setComponentAt(0,atual);
		getAbasPane().setSelectedIndex(-1);//faz com que 
		getAbasPane().setSelectedIndex(0);//a aba ganhe focus.
	}


	/**
	 * This method initializes tablePanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTablePanel1() {
		if (tablePanel1 == null) {
			tablePanel1 = new JPanel();
			tablePanel1.setLayout(null);
			tablePanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABLE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", java.awt.Font.BOLD, 12), null));
			tablePanel1.setBounds(new java.awt.Rectangle(150,0,478,447));
			tablePanel1.add(getAbasPane(), null);
		}
		return tablePanel1;
	}

	/**
	 * This method initializes abasPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getAbasPane() {
		if (abasPane == null) {
			abasPane = new JTabbedPane();
			abasPane.setPreferredSize(new java.awt.Dimension(478,447));
			abasPane.setComponentOrientation(java.awt.ComponentOrientation.UNKNOWN);
			abasPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
			abasPane.setBounds(new java.awt.Rectangle(5,22,468,421));
			abasPane.addTab("Times", null, getOrderedPanel(), null);
		}
		return abasPane;
	}

	/**
	 * This method initializes orderedPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOrderedPanel() {
		if (orderedPanel == null) {
			orderedPanel = new JPanel();
			orderedPanel.setLayout(null);
			orderedPanel.setPreferredSize(new java.awt.Dimension(100,100));
			orderedPanel.add(getJTable(), null);
		}
		return orderedPanel;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {//Criando os valores padroes.
			String pont = new String("...");
			String ins = new String("InsertionTree");
			String bin = new String("BinaryTree");
			String avl = new String("AVLTree");
			String alg = new String(" Algoritmo");
			
			Object[] v1 = {alg,ins,bin,avl};
			Object[][] v2 = {{alg,ins,bin,avl},{" Inserção",pont,pont,pont,pont},{" Pesquisa",pont,pont,pont,pont}};
			jTable = new JTable(v2,v1);//construindo a tabela
			
			jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			jTable.setBounds(new java.awt.Rectangle(63,139,350,50));
			jTable.setEnabled(false);
		}
		return jTable;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	

	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	
}
