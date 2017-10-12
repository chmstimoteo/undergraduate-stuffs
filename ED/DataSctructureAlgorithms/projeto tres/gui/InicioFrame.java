package gui;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import biblioteca.AvisoException;
import biblioteca.Factory;

public class InicioFrame {

	/**
	 * 
	 */
	private JFrame inicio = null;    //Frame principal
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel optionPanel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JPanel tablePanel1 = null;
	private JTabbedPane abasPane = null;
	private JPanel orderedPanel = null;
	private JPanel degreePanel = null;
	private JPanel randomPanel = null;
	private JLabel detalheLabel = null;
	private JProgressBar loading = null;
	private JTable jTable = null;
	private JTable jTable1 = null;
	private JTable jTable2 = null;
	private Factory factory;  //Classe Factory que vai fabricar os tempos.
	
	/**
	 * This is the default constructor
	 * @throws  
	 */
	
	public InicioFrame(){
		this.inicio = new JFrame();
		this.factory = new Factory();
		initialize();
		
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
		this.inicio.setTitle("Sort Counter");
		this.inicio.setIconImage(Toolkit.getDefaultToolkit().getImage("gui/icone.gif"));
		this.inicio.setName("inicioFrame");
		this.inicio.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.inicio.setBounds(new java.awt.Rectangle(0,0,640,480));
		this.inicio.setVisible(true);
		
		//Setando a visibilidade dos Jtables e mostrando o JProgressBar
		this.getJTable().setVisible(false);
		this.carregarProgress(getOrderedPanel());
		this.getJTable().setVisible(true);
		this.getJTable1().setVisible(false);
		this.carregarProgress(getDegreePanel());
		this.getJTable1().setVisible(true);
		this.getJTable2().setVisible(false);
		this.carregarProgress(getRandomPanel());
		this.getJTable2().setVisible(true);
	}

	public JFrame getFrame(){
		return this.inicio;
	}
	
	private void carregarProgress(JPanel base) {
		// TODO Auto-generated method stub
		this.loading = new JProgressBar();
		this.loading.setStringPainted(true);
		loading.setSize(new java.awt.Dimension(100,20));
		loading.setLocation(new java.awt.Point(180,180));
		base.setLayout(null);
		base.add(this.loading);
		this.loading.setVisible(true);
		
		//Laco que vai gerar a mudanca de estado da barra de carregamento.
		Random random = new Random();
		for(int i=0; i<101; i++){
			this.loading.setValue(i);
			this.loading.setString(i + "%");
			this.loading.repaint();
			this.loading.setVisible(true);
			this.loading.setFocusable(true);
            try {
                    Thread.sleep(random.nextInt(50));//Vai fazer com que ocorra uma pausa na execucao de um numero random entre 0uS e 50uS.
                } catch (InterruptedException ignore) {} //Fazendo 100 vezes da no maximo 5 segundos de espera e em media mais ou menos 1.5s.
			}
		this.loading.setVisible(false);
		base.repaint();
		base.updateUI();
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
			jContentPane.setToolTipText("SortCounter vs1.0 \nDevelopment by Carlos Henrique Maciel and Elliackin Messias do Nascimento \nDisciplina: Estrutura de Dados UPE / POLI. \n09 de maio de 2007.");
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
			optionPanel.add(getJButton1(), null);
			optionPanel.add(getJButton2(), null);
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
			jButton.setText("Ordered");
			jButton.setBounds(new java.awt.Rectangle(5,23,134,31));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					atualizarDadosTabela(getOrderedPanel(),0); //atualiza os dados da tabela.
					getAbasPane().setSelectedIndex(0);//faz com que a aba ganhe focus.
				}
			});
		}
		return jButton;
	}
	

	private void atualizarDadosTabela(JPanel atual,int i) {
		// TODO Auto-generated method stub
		//Pegar os dados produzidos pelos metodos.
		String[] dadosMerge = null;
		String[] dadosQuick = null;
		String[] dadosInsertion = null;
		String[] dadosSelection = null;
		String[] dadosShell = null;
		String[] dadosHeap = null;
		try {
			
			if(i==0){//Se for o orderedPanel.
				dadosMerge = factory.merge(Factory.Opcoes.ORDENADO);
				dadosQuick = factory.quick(Factory.Opcoes.ORDENADO);
				dadosInsertion = factory.insertion(Factory.Opcoes.ORDENADO);
				dadosSelection = factory.selection(Factory.Opcoes.ORDENADO);
				dadosShell = factory.shell(Factory.Opcoes.ORDENADO);
				dadosHeap = factory.heap(Factory.Opcoes.ORDENADO);
			}
			
			else if(i==1){//Se for o degreePanel.
				dadosMerge = factory.merge(Factory.Opcoes.DESORDENADO);
				dadosQuick = factory.quick(Factory.Opcoes.DESORDENADO);
				dadosInsertion = factory.insertion(Factory.Opcoes.DESORDENADO);
				dadosSelection = factory.selection(Factory.Opcoes.DESORDENADO);
				dadosShell = factory.shell(Factory.Opcoes.DESORDENADO);
				dadosHeap = factory.heap(Factory.Opcoes.DESORDENADO);
			}
			else if(i==2){//Se for o randomPanel.
				dadosMerge = factory.merge(Factory.Opcoes.ALEATORIO);
				dadosQuick = factory.quick(Factory.Opcoes.ALEATORIO);
				dadosInsertion = factory.insertion(Factory.Opcoes.ALEATORIO);
				dadosSelection = factory.selection(Factory.Opcoes.ALEATORIO);
				dadosShell = factory.shell(Factory.Opcoes.ALEATORIO);
				dadosHeap = factory.heap(Factory.Opcoes.ALEATORIO);
			}else{
				JOptionPane.showMessageDialog(null, "Nao foi possivel atualizar tabela!!");
				
				
			}
		} catch (AvisoException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		String num10 = new String("10");
		String num100 = new String("100");
		String num500 = new String("500");
		String num2000 = new String("2000");
		String alg = new String(" Algoritmo");
		Object[] v1 = {alg,num10,num100,num500,num2000};
		Object[][] v2 = {{alg,num10,num100,num500,num2000},{" Insertion",dadosInsertion[0],dadosInsertion[1],dadosInsertion[2],dadosInsertion[3]},{" Selection",dadosSelection[0],dadosSelection[1],dadosSelection[2],dadosSelection[3]},{" Shell",dadosShell[0],dadosShell[1],dadosShell[2],dadosShell[3]},{" Heap",dadosHeap[0],dadosHeap[1],dadosHeap[2],dadosHeap[3]},{" Quick",dadosQuick[0],dadosQuick[1],dadosQuick[2],dadosQuick[3]},{" Merge",dadosMerge[0],dadosMerge[1],dadosMerge[2],dadosMerge[3]}};
		JTable tabela2 = new JTable(v2,v1);
		tabela2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tabela2.setBounds(new java.awt.Rectangle(63,139,350,112));
		tabela2.setEnabled(false);  //Faz com que nao possa ser alterado os valores da tabela pelo usuario.
		atual.removeAll();
		atual.add(tabela2);
		getAbasPane().setComponentAt(i,atual);
		atual.repaint();
		atual.setFocusable(true);
	}


	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new java.awt.Rectangle(5,59,136,27));
			jButton1.setText("Degree");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					atualizarDadosTabela(getDegreePanel(),1);//atualiza a tabela
					getAbasPane().setSelectedIndex(1);// ganhando focus.
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new java.awt.Rectangle(5,92,136,26));
			jButton2.setText("Random");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					atualizarDadosTabela(getRandomPanel(),2);//atualiza a tabela
					getAbasPane().setSelectedIndex(2);// ganhando focus.
				}
			});
		}
		return jButton2;
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
			abasPane.addTab("Ordered", null, getOrderedPanel(), null);
			abasPane.addTab("Degree", null, getDegreePanel(), null);
			abasPane.addTab("Random", null, getRandomPanel(), null);
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
	 * This method initializes degreePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDegreePanel() {
		if (degreePanel == null) {
			degreePanel = new JPanel();
			degreePanel.setLayout(null);
			degreePanel.add(getJTable1(), null);
		}
		return degreePanel;
	}

	/**
	 * This method initializes randomPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRandomPanel() {
		if (randomPanel == null) {
			randomPanel = new JPanel();
			randomPanel.setLayout(null);
			randomPanel.add(getJTable2(), null);
		}
		return randomPanel;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {//Criando os valores padroes.
			String pont = new String("...");
			String num10 = new String("10");
			String num100 = new String("100");
			String num500 = new String("500");
			String num2000 = new String("2000");
			String alg = new String(" Algoritmo");
			Object[] v1 = {alg,num10,num100,num500,num2000};
			Object[][] v2 = {{alg,num10,num100,num500,num2000},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont}};
			jTable = new JTable(v2,v1);
			jTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			jTable.setBounds(new java.awt.Rectangle(63,139,350,112));
			jTable.setEnabled(false);
		}
		return jTable;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable1() {
		if (jTable1 == null) {//Criando os valores padroes.
			String pont = new String("...");
			String num10 = new String("10");
			String num100 = new String("100");
			String num500 = new String("500");
			String num2000 = new String("2000");
			String alg = new String(" Algoritmo");
			Object[] v1 = {alg,num10,num100,num500,num2000};
			Object[][] v2 = {{alg,num10,num100,num500,num2000},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont}};
			jTable1 = new JTable(v2,v1);
			jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			jTable1.setBounds(new java.awt.Rectangle(63,139,350,112));
			jTable1.setEnabled(false);
		}
		return jTable1;
	}

	/**
	 * This method initializes jTable2	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable2() {
		if (jTable2 == null) {//Criando os valores padroes.
			String pont = new String("...");
			String num10 = new String("10");
			String num100 = new String("100");
			String num500 = new String("500");
			String num2000 = new String("2000");
			String alg = new String(" Algoritmo");
			Object[] v1 = {alg,num10,num100,num500,num2000};
			Object[][] v2 = {{alg,num10,num100,num500,num2000},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont},{pont,pont,pont,pont,pont}};
			jTable2 = new JTable(v2,v1);
			jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			jTable2.setBounds(new java.awt.Rectangle(63,139,350,112));
			jTable2.setEnabled(false);
		}
		return jTable2;
	}

}
