/*
 * JChatApplet.java
 *
 * Created on 25 de Mar�o de 2001, 23:29
 */


/*import JChat.JClient;
import JChat.JChatChannel;
import JChat.Message;
import JChat.MessageConstants;

*/
package chat;


import java.awt.*;
import java.util.Vector;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author  Herval Freire
 * @version 0.1a
 */
public class JChatApplet extends JApplet 
{
    
///////////////////////////////////////////////
// Interface com o buffer de JClient

  private class messageThread extends Thread
   {
      public messageThread(JClient eu)
       {
	     	this.setPriority(2);      
       }
       
       public void run()
       {
           while(true)
           {
               Message aux = cliente.checaMensagem();

		switch (aux.getHeader())
		{
			case MessageConstants.NICK_DENY: System.out.println("Nick inv�lido");
			break;
			case MessageConstants.NICK_OK: System.out.println("Nick OK. Recebendo canais");
			break;
			case MessageConstants.JOIN_DENY: System.out.println("Imposs�vel entrar em canal");
                        break;
	        case MessageConstants.MSG_TO_CHANNEL: System.out.println("Recebi mensagem MSG TO CHAN");
	                    ShowMessage(aux.getOrigin(), aux.getMessage(), false);
	        break;
   			case MessageConstants.MSG: System.out.println("Recebi mensagem");
			                    ShowMessage(aux.getOrigin(), aux.getMessage(), true);
			break;
        		case MessageConstants.CLI_UNKNOWN: System.out.println("Cliente n�o localizado: "+aux.getOrigin());
			break;
			case MessageConstants.CH_SYS_MSG: ShowMessage(aux.getOrigin(), aux.getMessage(), true);
	                        	    System.out.println("CH SYS MSG");
        	        break;
			case MessageConstants.CHAN_UPDATE:
                                            Vector nicks = canal.getActualUsers();
                                            jList9.setListData(nicks);
                                            jList9.repaint();
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

    
    private messageThread recepcao;
    
    private JClient cliente;
    private boolean conectado = false;
    private JChatChannel canal;
	private CardLayout cardManager;
    
    protected String SERVER = "localhost";
    protected int IP = 6666;
    


	public void init()
	{

		cardManager = new CardLayout();
		
		initComponents();

        recepcao = new messageThread(cliente);

	}
	

	public void start()
	{

		try
		{
			cliente = new JClient(SERVER, IP, jTextField4.getText());

			recepcao.start();
		}  
		catch(IOException e)
		{
		    System.out.println(e.toString());
		}

	
	}
	


	public void paint( Graphics g )
	{
		jPanel5.show();
	
	}

    /** Creates new form JChatApplet */
    public JChatApplet() 
	{

    }
	

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        jPanel5 = new JPanel ();
        jPanel6 = new JPanel ();
        jLabel6 = new JLabel ();
        jTextField4 = new JTextField ();
        jButton3 = new JButton ();
        jLabel7 = new JLabel ();
        jScrollPane6 = new JScrollPane ();
        jList8 = new JList ();
        jPanel7 = new JPanel ();
        jScrollPane7 = new JScrollPane ();
        jList9 = new JList ();
        jTextField5 = new JTextField ();
        jLabel8 = new JLabel ();
        jLabel9 = new JLabel ();
        jScrollPane8 = new JScrollPane ();
        jTextArea6 = new JTextArea ();
        getContentPane().setLayout (new FlowLayout());

        jPanel5.setLayout (cardManager);
        jPanel5.setPreferredSize (new java.awt.Dimension(400, 400));
        jPanel5.setMinimumSize (new java.awt.Dimension(400, 400));

          jPanel6.setPreferredSize (new java.awt.Dimension(400, 400));
          jPanel6.setMinimumSize (new java.awt.Dimension(400, 400));
  
            jLabel6.setText ("Nickname");
    
            jPanel6.add (jLabel6);
    
            jTextField4.setPreferredSize (new java.awt.Dimension(190, 20));
            jTextField4.setMinimumSize (new java.awt.Dimension(55, 20));
    
            jPanel6.add (jTextField4);
    
            jButton3.setText ("Conectar");
            jButton3.addActionListener (new ActionListener () {
                public void actionPerformed (ActionEvent evt) {
                    jButton3ActionPerformed (evt);
                }
            }
            );
    
            jPanel6.add (jButton3);
    
            jLabel7.setText ("Escolha o canal");
            jLabel7.setEnabled (false);
            jLabel7.setHorizontalTextPosition (SwingConstants.CENTER);
    
            jPanel6.add (jLabel7);
    
            jScrollPane6.setPreferredSize (new java.awt.Dimension(390, 200));
            jScrollPane6.setMinimumSize (new java.awt.Dimension(390, 62));
    
              jList8.setEnabled (false);
      
              jScrollPane6.setViewportView (jList8);
      
            jPanel6.add (jScrollPane6);
    
          jPanel5.add (jPanel6, "card1");
  
          jPanel7.setLayout(new FlowLayout());
  
    
      
              jScrollPane7.setViewportView (jList9);
      
            jPanel7.add (jScrollPane7);
    
            jTextField5.addActionListener (new java.awt.event.ActionListener () {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    jTextField5ActionPerformed (evt);
                }
            }
            );
    
            jPanel7.add (jTextField5);
    
            jLabel8.setText ("JChat v 0.5b");
    
            jPanel7.add (jLabel8);
    
            jLabel9.setText ("jLabel9");
    
            jPanel7.add (jLabel9);
    
    
      
              jScrollPane8.setViewportView (jTextArea6);
      
            jPanel7.add (jScrollPane8);
    
          jPanel5.add (jPanel7, "card2");
  

        getContentPane().add(jPanel5);

    }//GEN-END:initComponents

  private void jTextField5ActionPerformed (ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed

    cliente.MensagemParaCanal(canal.getName(), jTextField5.getText());
  }//GEN-LAST:event_jTextField5ActionPerformed


  private void jButton3ActionPerformed (ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    if(conectado == false)
    {
   
            conectado = true;
            jButton3.setText("Envia Nick");
            
            AtivaSeletorCanal();
    }
    else
    {
        cliente.enviaNick(jTextField4.getText());    
    }
  }//GEN-LAST:event_jButton3ActionPerformed


  private void AtivaSeletorCanal()
  {
        jList8.enable();
        jLabel7.enable();

        
        Vector canais = cliente.listaCanais();
        
        jList8 = new JList(canais);
		
		
        jList8.setVisibleRowCount(15);
        jList8.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList8.setFixedCellWidth(100);

       jList8.addMouseListener( 
	    new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	          if (e.getClickCount() == 2) {

			  try
			  {
	              	    String nomeDoCanal = (String)jList8.getSelectedValue();

                        InitChat();
                          }
			  catch(ArrayIndexOutOfBoundsException ev)
                          {
			  }
                }
	      }
	  }
	);
       
  }


private void InitChat()
{
    

	cardManager.last(jPanel5);

    
    canal = cliente.EntraCanal(canal.getName());
    jList9 = new JList(canal.getActualUsers());
    
}



private void ShowMessage(String origem, String mensagem, boolean privado)
{
    if(privado == false)
        jTextArea6.append(origem+" fala reservadamente para "+cliente.getNickname()+": "+mensagem);
    else
        jTextArea6.append(origem+" fala: "+mensagem);
}

// Variables declaration - do not modify//GEN-BEGIN:variables
private JPanel jPanel5;
private JPanel jPanel6;
private JLabel jLabel6;
private JTextField jTextField4;
private JButton jButton3;
private JLabel jLabel7;
private JScrollPane jScrollPane6;
private JList jList8;
private JPanel jPanel7;
private JScrollPane jScrollPane7;
private JList jList9;
private JTextField jTextField5;
private JLabel jLabel8;
private JLabel jLabel9;
private JScrollPane jScrollPane8;
private JTextArea jTextArea6;
// End of variables declaration//GEN-END:variables



}