package chat;

import chat.JClient;
import chat.JChatChannel;
import chat.Message;
import chat.MessageConstants;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import java.util.Vector;




/** <B>Java Chat</B> the Java Chat Interface
 * @author Luciana Leal
 * @version 0.5b
 * Created on: 13/03/2001
 * Last Review: 25/03/2001, 16:00
 */
public class JavaChat extends JFrame {

   public JMenuItem newFrame;
   private JClient eu;
   private MessageThread queue;
   private boolean conectado;
   private Vector meus_canais;
   private Vector meus_pvts;
   String nick;
   final JDesktopPane desktop;

   public JavaChat()
   {
      super("Java Chat: desconectado" );

	  conectado = false;

	  meus_canais = new Vector();
	  meus_pvts = new Vector();

      JMenuBar bar = new JMenuBar();
      setJMenuBar( bar );

      JMenu menuArquivo = new JMenu("Conexão");
      menuArquivo.setMnemonic('C');
      final JMenuItem itemConectar = new JMenuItem("Conectar");
      itemConectar.setMnemonic('C');
      menuArquivo.add(itemConectar);

      itemConectar.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ) {

               JanelaConectar jconect = new JanelaConectar(JavaChat.this);
			   	
				
            	}
	       }
      );


      JMenuItem itemSair = new JMenuItem("Desconectar");
      itemSair.setMnemonic('D');
      itemSair.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
			  try
			  {
			   	if(conectado == true)
				   eu.desconectar();
			   
               			System.exit( 0 );
			  }
              catch(IOException ex)
			  {
               	JOptionPane.showMessageDialog(null, "Não foi possível desconectar"); 
			  }
			   
            }
         }
      );
      menuArquivo.add(itemSair);
      bar.add(menuArquivo);

      JMenu menuAcoes = new JMenu("Ações");
      menuAcoes.setMnemonic('e');

	    final JMenuItem itemNick = new JMenuItem("Alterar Nick");
	    itemNick.setMnemonic('N');
	    menuAcoes.add(itemNick);

      itemNick.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ) {

              Altera outroNick = new Altera("Alterar Nick", "Novo Nick :");

            }
	       }
      );

      menuAcoes.addSeparator();

	    JMenuItem itemCanais = new JMenuItem("Listar Canais");
	    itemCanais.setMnemonic('L');
	    menuAcoes.add(itemCanais);

      itemCanais.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ) {

               ListaDeCanais canais = new ListaDeCanais();

            }
	       }
      );




	    JMenuItem itemAdic = new JMenuItem("Entrar em Canal");
	    itemAdic.setMnemonic('C');
	    menuAcoes.add(itemAdic);

      itemAdic.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ) {

              Join adicCanal = new Join("Entrar em Canal", "Canal :");

            }
	       }
      );

	  bar.add(menuAcoes);

      desktop = new JDesktopPane();
	  
	  getContentPane().add(desktop);

      setSize( 700, 400 );
      show();
   }








/////////////////////////////////////////////////////////
// Metodos de interface com a classe cliente


	public void conecta(String host, int ip, String nick)
	{
		try
		{
		eu = new JClient(host, ip, nick);
		
		queue = new MessageThread();
		queue.start();
		
		conectado = true;


		seta_nick(nick);
		


		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Servidor não encontrado"); 
		}
		catch(IOException ex)	
		{
			JOptionPane.showMessageDialog(null, "Erro de conexão"); 
		}


	}


	public void seta_nick(String nick)
	{
		if (conectado == true)
		{
			eu.enviaNick(nick);
		}
	
	}
	

	public void seta_nick()
	{
		if(conectado == true)
		{
			String n = JOptionPane.showInputDialog(null, "Nickname inválido, digite outro");
			eu.enviaNick(n);
		}
	}








/////////////////////////////////////////////////////////
// Metodos de interface com canais

	public void SaiCanal(String nome)
	{
		System.out.println("Cheguei no SaiCanal");
	
		RemoveCanal(nome);
		eu.SaiCanal(nome);
	}



	public void AtivaCanal(String nome)
	{

		Canal aux = AchaCanal(nome);
		if (aux != null)
		{
			aux.toFront();
		}
		else
		{
			JChatChannel entrar = eu.EntraCanal(nome);
			if(entrar != null)
				aux = new Canal(entrar);
	
		
		}
	
	}

	

	public Canal AchaCanal(String nome)
	{
		for(int i = 0; i<=meus_canais.size()-1; i++)
		{
			Canal aux = (Canal)meus_canais.get(i);
			System.out.println("Canal "+i+": "+aux.getNome());
			if(aux.getNome().equals(nome))
				return aux;
		
		}
		
		return null;
	}



	public void RemoveCanal(String x)
	{
		Canal temp = AchaCanal(x);

		if (temp != null)
		{
			meus_canais.removeElement(temp);
		
			System.out.println("canal existe e foi removido");
		}
		else
		{
		}
	}


	public void AdicionaCanal(Canal x)
	{
	
		Canal temp = AchaCanal(x.getNome());
	
		if (temp != null)
		{
			meus_canais.removeElement(temp);
			meus_canais.addElement(x);
		
			System.out.println("canal jah existe e foi atualizado");
		}
		else
		{
			meus_canais.addElement(x);
			System.out.println("canal cadastrado");
		}
	
	}



	public void ShowMessageCh(String origem, String canal, String mensagem)
	{
	
		Canal toShow = AchaCanal(canal);
		
		if (toShow != null)
		{
			toShow.addText("<"+origem+"> "+mensagem);
		}
	
	}
	
	
	public void ChannelNotice(String canal, String msg)
	{
	
		Canal t = AchaCanal(canal);
		
		if (t != null)
			t.addText("Informação do sistema: "+msg);
	
	}
	
	public void Notice(String men)
	{
	
		JOptionPane.showMessageDialog(null, men);
	
	}
/////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////
// Metodos de interface com pvts

	public void SaiPvt(String nome)
	{
		RemovePvt(nome);
	}



	public void AtivaPvt(String nome)
	{

		Pvt aux = AchaPvt(nome);
		if (aux != null)
		{
			aux.toFront();
		}
		else
		{
			aux = new Pvt(nome);
		
		}
	
	}

	

	public Pvt AchaPvt(String nome)
	{
		for(int i = 0; i<=meus_pvts.size()-1; i++)
		{
			Pvt aux = (Pvt)meus_pvts.get(i);
			if(aux.getNome().equals(nome))
				return aux;
		
		}
		
		return null;
	}


	public void RemovePvt(String x)
	{
		Pvt temp = AchaPvt(x);

		if (temp != null)
		{
			meus_pvts.removeElement(temp);
		
			System.out.println("pvt existe e foi removido");
		}
		else
		{
		}
	}


	public void AdicionaPvt(Pvt x)
	{
	
		Pvt temp = AchaPvt(x.getNome());
	
		if (temp != null)
		{
			meus_pvts.removeElement(temp);
			meus_pvts.addElement(x);
		
			System.out.println("pvt jah existe e foi atualizado");
		}
		else
		{
			meus_pvts.addElement(x);
			System.out.println("pvt cadastrado");
		}
	
	}


	public void ShowMessagePvt(String origem, String dest, String message)
	{
	
		Pvt toShow = AchaPvt(dest);
		Pvt sender = AchaPvt(origem);
		
		if (toShow != null)
		{
			toShow.addText("<"+origem+"> "+message);
		}
		else 
		
		if (sender != null)
		{
			sender.addText("<"+origem+"> "+message);
		}
	
	}

/////////////////////////////////////////////////////////







///////////////////////////////////////////////
// Interface com o buffer de JClient

   class MessageThread extends Thread
   {
      public MessageThread()
       {
	     	this.setPriority(2);      
           
       }
       
       public void run()
       {
           while(true)
           {
               Message aux = eu.checaMensagem();

					switch (aux.getHeader())
					{
						case MessageConstants.NICK_DENY: seta_nick();
						break;
						case MessageConstants.NICK_OK: JavaChat.this.setTitle(" Java Chat: "+eu.getNickname());
														nick = eu.getNickname();
						break;
						case MessageConstants.JOIN_DENY: JOptionPane.showMessageDialog(null, "Impossível entrar em canal");
						break;
						case MessageConstants.CH_SYS_MSG: ChannelNotice(aux.getOrigin(), aux.getMessage());
						break;
						case MessageConstants.SYS_MSG: Notice(aux.getMessage());
						break;

						case MessageConstants.CHAN_UPDATE: Canal atualizado = AchaCanal(aux.getOrigin());
														 System.out.println("Passei na recepcao do CHAN_UPDATE");
														 if (atualizado != null)
														 {
														 	atualizado.atualizaCanal();
															System.out.println("Atualizei "+atualizado.getNome());
														 }
														 break;
						case MessageConstants.MSG_TO_CHANNEL: System.out.println("Recebi mensagem MSG TO CHAN");
														ShowMessageCh(aux.getOrigin(), aux.getDest(), aux.getMessage());
														break;
						case MessageConstants.MSG: System.out.println("Recebi mensagem");
													AtivaPvt(aux.getOrigin());
													ShowMessagePvt(aux.getOrigin(), aux.getDest(), aux.getMessage());
													break;
						default: break;
					}
					
					try
					{
						sleep(100);
					}
					catch(Exception e)
					{
					}

                   // trata a mensagem na fila
               
           }
       }
   }


///////////////////////////////////////////////


/////////////////////////////////////////////////////////
// Classe Janela Conectar

class JanelaConectar extends JDialog {

	JLabel nick, servidor, porta;
	JButton okButton;
	JTextField nickText, servidorText, portaText;
    JPanel conectarPanel;


  public JanelaConectar(JFrame janela){

	super(janela, "Conectar", true);

	Container c = getContentPane();
	c.setLayout(new FlowLayout());

	nick = new JLabel("       Nick :");
	c.add(nick);
	nickText = new JTextField(18);
	c.add(nickText);

	servidor = new JLabel("Servidor:");
	c.add(servidor);
	servidorText = new JTextField(18);
	c.add(servidorText);

	porta = new JLabel("    Porta :");
	c.add(porta);
	portaText = new JTextField(18);
	c.add(portaText);

	okButton = new JButton("Ok");
	c.add(okButton);

  final JDesktopPane conectarDesktop = new JDesktopPane();
  getContentPane().add( conectarDesktop );

  okButton.addActionListener(
     new ActionListener() {
         public void actionPerformed( ActionEvent e ) 
		 {
		 	try
		 	{
				int porta = Integer.parseInt(portaText.getText());


				conecta(servidorText.getText(), porta ,nickText.getText());
				
				JanelaConectar.this.dispose();

		 	}
			catch(NumberFormatException err)
			{
				JOptionPane.showMessageDialog(null, "Porta inválida");
			
			}



         }
	   }
  );

  setLocation(150, 100);
  setSize(300, 150);
	show();

  }
}

/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////
// Classe Janela Alterar

class Altera extends JFrame {

	JLabel novoNick;
	JButton nickButton;
	JTextField novoNickText;


  public Altera(String topico, String rotulo){

	super(topico);

	Container c = getContentPane();
	c.setLayout(new FlowLayout());

	novoNick = new JLabel(rotulo);
	c.add(novoNick);
	novoNickText = new JTextField(18);
	c.add(novoNickText);

	nickButton = new JButton("Ok");
	c.add(nickButton);

    final JDesktopPane conectarDesktop = new JDesktopPane();
    getContentPane().add( conectarDesktop );

    nickButton.addActionListener(
       new ActionListener() {
          public void actionPerformed( ActionEvent e ) {
	  		 

          if (conectado == true)
          {
          	seta_nick(novoNickText.getText());
          }
          else
          {
          	JOptionPane.showMessageDialog(null, "Cliente não conectado");
          }

			 Altera.this.dispose();

         }
	   }
  );

  setLocation(150, 100);
  setSize(300, 100);
	show();

  }
}

/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////
// Classe janela Join

class Join extends JFrame {

	JLabel novo;
	JButton cButton;
	JTextField novoText;


  public Join(String topico, String rotulo){

	super(topico);

	Container c = getContentPane();
	c.setLayout(new FlowLayout());

	novo = new JLabel(rotulo);
	c.add(novo);
	novoText = new JTextField(18);
	c.add(novoText);

	cButton = new JButton("Ok");
	c.add(cButton);

    final JDesktopPane conectarDesktop = new JDesktopPane();
    getContentPane().add( conectarDesktop );

    cButton.addActionListener(
       new ActionListener() {
          public void actionPerformed( ActionEvent e ) {
	  		 

          if (conectado == true)
          {
          		AtivaCanal(novoText.getText());
          }
          else
          {
          	JOptionPane.showMessageDialog(null, "Cliente não conectado");JOptionPane.showMessageDialog(null, "Cliente não conectado");
          }

			 Join.this.dispose();

         }
	   }
  );

  setLocation(150, 100);
  setSize(300, 100);
	show();

  }
}

/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////
// Classe janela Lista de canais

class ListaDeCanais extends JFrame{

	private JList canaisList;
    private Container c;
  	private Object canais[];
	private String nomeDoCanal;



	public ListaDeCanais(){


    super("Lista de Canais");
	  
	if (conectado == true)
	{
	  canais = eu.listaCanais().toArray();
	  
	}
	else
	{
		JOptionPane.showMessageDialog(null, "Cliente não conectado");JOptionPane.showMessageDialog(null, "Cliente não conectado");
		canais = new String[2];
		canais[0] = "Não conectado";
	}
	  
	  
	  c = getContentPane();
	  c.setLayout(new FlowLayout());

	  canaisList = new JList(canais);
	  canaisList.setVisibleRowCount(5);
	  canaisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	  canaisList.setFixedCellWidth(320);
	  canaisList.setFixedCellHeight(15);


	  c.add(new JScrollPane(canaisList) );


	  canaisList.addMouseListener( 
	    new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	          if (e.getClickCount() == 2) {
	              int index = canaisList.locationToIndex(e.getPoint());
				  try
				  {
	              	nomeDoCanal = (String)canais[index];

	              	AtivaCanal(nomeDoCanal);
				  }
				  catch(ArrayIndexOutOfBoundsException ev)				  {
				  
				  
				  }

				  
	           }
	      }
	  }
	);

	  setSize(350, 150);
	  show();

	}
}

/////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////
// Classe Canal

class Canal extends JInternalFrame {

	private JChatChannel canal;
	private BorderLayout layout;
	private JPanel painel;
	private JList nickList;
	private JTextArea areaTextArea;
	private JTextField userText;
	private JButton enviarButton;

	public Canal(JChatChannel dados){  //*** receber lista de usuarios do canal como param

  	  super(dados.getName(), true, true, true, true);
  
	  canal = dados;
	  
	  
	  System.out.println("Inicializei o canal");  

	  layout = new BorderLayout();
	  Container c = getContentPane();
	  c.setLayout(layout);

	  Box b = Box.createHorizontalBox();

	  // Lista de Usuários do Canal
	  IniciaLista(dados.getActualUsers());
	  


	  // Caixa de Texto - Mensagem do Usuário
	  userText = new JTextField();

	  TextFieldHandler handler = new TextFieldHandler();
	  userText.addActionListener(handler);

	  // Area de Texto do Chat
	  areaTextArea = new JTextArea();
	  areaTextArea.setEditable(false);
	  areaTextArea.setLineWrap(true);

	  b.add(new JScrollPane(areaTextArea));



	  // Adicionar componentes ao Container
	  c.add(b, BorderLayout.CENTER);
	  c.add(new JScrollPane(nickList), BorderLayout.EAST);
	  c.add(userText, BorderLayout.SOUTH);

	  layout.layoutContainer(getContentPane());
	
	 addInternalFrameListener(
		  new InternalFrameAdapter() {
		     public void internalFrameClosing( InternalFrameEvent e )
		     {
//				Canal.this.toFile();
			 	SaiCanal(getNome());
		     }
		  }
	 );
		
	  

	  setSize(500, 300);

	  AdicionaCanal(this);
	  
	  desktop.add(this);

	  show();
	}


	public String getNome()
	{
		return canal.getName();
	}


	public void addText(String tex)
	{
	
		areaTextArea.append(tex+"\n");
		
		areaTextArea.repaint();

	}




	public void IniciaLista(Vector users)
	{
	// Lista de Usuários do Canal

	  nickList = new JList(users);
	  nickList.setVisibleRowCount(10);
	  nickList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  nickList.setFixedCellWidth(100);
	  getContentPane().add(new JScrollPane(nickList));


	  nickList.addMouseListener( 
	    new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	          if (e.getClickCount() == 2) {
			  
			  	try
			  	{
	              String nome = (String)nickList.getSelectedValue();
				
				  if(nome != null)
	              		AtivaPvt(nome);
			  	}
				catch(Exception ev)
				{
					System.out.println(ev.toString());
				}

	           }
	      }
	   }
  	 );
	
	}

	public void toFile()
	{
		
		try
		{
			FileOutputStream outfile = new FileOutputStream(this.getNome()+".txt");
		
			outfile.write(areaTextArea.getText().getBytes());
			outfile.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Não foi possível salvar arquivo");
		
		}
	
	
	}



	public void atualizaCanal()
	{
		canal = eu.pegaCanal(this.getNome());

	 	System.out.println("atualizei canal");
	
		Vector nicks = canal.getActualUsers();

		System.out.println("Usuarios atuais: "+nicks.toString());
		
		nickList.setListData(nicks);
		
		nickList.repaint();
	
	}



	private class TextFieldHandler implements ActionListener{
	  public void actionPerformed(ActionEvent e){

		if (e.getSource() == userText)
		{
			String toSend = userText.getText();
			eu.MensagemParaCanal(Canal.this.getNome(), toSend);
			userText.setText("");
			
		}

	  }

	 }

}




/////////////////////////////////////////////////////////
// Classe Pvt

class Pvt extends JInternalFrame {

	private BorderLayout layout;
	private JPanel painel;
	private JTextArea areaTextArea;
	private JTextField userText;
	private String nome;

	public Pvt(String usuario){ 

  	  super(usuario, true, true, true, true);
  
	  
	  nome = usuario;
	  
	 System.out.println("Inicializei o PVT");  

	  layout = new BorderLayout();
	  Container c = getContentPane();
	  c.setLayout(layout);



	// Caixa de Texto - Mensagem do Usuário

	  userText = new JTextField();

	  TextFieldHandler handler = new TextFieldHandler();
	  userText.addActionListener(handler);

	// Area de Texto do Chat

	  areaTextArea = new JTextArea();
	  areaTextArea.setEditable(false);
	  areaTextArea.setLineWrap(true);

	  Box b = Box.createHorizontalBox();
	  b.add(new JScrollPane(areaTextArea));



	// Adicionar componentes ao Container


	  c.add(b, BorderLayout.CENTER);
	  c.add(userText, BorderLayout.SOUTH);

	  layout.layoutContainer(getContentPane());
	  
	  addInternalFrameListener(
	    new InternalFrameAdapter() {
	       public void internalFrameClosing( InternalFrameEvent e )
	       {
	  	 	SaiPvt(getNome());
  	       }
		}
	 );
		
	  

	  setSize(500, 300);


	  AdicionaPvt(this);

	  desktop.add(this);

	  show();
	}

	public String getNome()
	{
		return nome;
	}

	public void addText(String tex)
	{
	
		areaTextArea.append(tex+"\n");
		
		areaTextArea.repaint();

	}


	public void toFile()
	{
		
		try
		{
			FileOutputStream outfile = new FileOutputStream(this.getNome()+".txt");
		
			outfile.write(areaTextArea.getText().getBytes());
			outfile.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Não foi possível salvar arquivo");
		
		}
	
	
	}


	private class TextFieldHandler implements ActionListener{
	  public void actionPerformed(ActionEvent e){

		if (e.getSource() == userText)
		{
			String toSend = userText.getText();
			eu.MensagemPvt(Pvt.this.getNome(), toSend);

			Pvt.this.areaTextArea.append("<"+Pvt.this.getNome()+"> "+toSend+"\n");

			userText.setText("");
			
		}

	  }

	 }

}

/////////////////////////////////////////////////////////





    public static void main( String args[] )
    {
       JavaChat app = new JavaChat();

       app.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               System.exit( 0 );
            }
         }
      );
   }



}