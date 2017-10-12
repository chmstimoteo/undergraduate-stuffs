/*
 * JServer.java
 *
 * Created on 25 de Mar�o de 2001, 22:30
 */

package chat;


import javax.swing.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author  Herval
 * @version 
 */
public class JServer extends javax.swing.JFrame {

    
    class OuveThread extends Thread
    {
        public OuveThread()
        {
            this.setPriority(2);
        }
        
        public void run()
        {
        
			try
			{
        	    while (true)
		    	{
            
            	    String aux;
                            
	                if((aux = servidor.GetBufferMessage()) != null)
    	                jTextArea1.append(aux);
        	        
            	    this.sleep(100);
	            }
			}
			catch(InterruptedException e)
			{
			}
        }
        
    }
    

    JChatServer servidor;
    OuveThread listening;
    /** Creates new form JServer */
    public JServer() {
        initComponents ();
        pack ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents () {//GEN-BEGIN:initComponents
        jLabel2 = new javax.swing.JLabel ();
        jTextField1 = new javax.swing.JTextField ();
        jTextArea1 = new javax.swing.JTextArea ();
        jButton1 = new javax.swing.JButton ();
        jCheckBox1 = new javax.swing.JCheckBox ();
        getContentPane ().setLayout (new java.awt.FlowLayout ());
        setTitle ("JServer: JChat Server version 0.5b");
        setForeground (java.awt.Color.lightGray);
        setDefaultCloseOperation (javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener (new java.awt.event.WindowAdapter () {
            public void windowClosing (java.awt.event.WindowEvent evt) {
                exitForm (evt);
            }
        }
        );

        jLabel2.setText ("Porta de servi\u00e7o:");


        getContentPane ().add (jLabel2);

        jTextField1.setText ("6666");


        getContentPane ().add (jTextField1);

        jTextArea1.setPreferredSize (new java.awt.Dimension(300, 300));
        jTextArea1.setMinimumSize (new java.awt.Dimension(300, 300));


        getContentPane ().add (jTextArea1);

        jButton1.setName ("AtivarButton");
        jButton1.setText ("Ativar");
        jButton1.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed (evt);
            }
        }
        );


        getContentPane ().add (jButton1);

        jCheckBox1.setText ("Gravar log");


        getContentPane ().add (jCheckBox1);

    }//GEN-END:initComponents

  private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    try
    {

    if(jButton1.getText() == "Ativar")
    {
        jButton1.setText("Desativar");
       
        int porta = Integer.parseInt(jTextField1.getText());
        servidor = new JChatServer(porta);
        if(jCheckBox1.isSelected())
            servidor.setFileLogging(true);

        listening = new OuveThread();
        listening.start();
        
        servidor.Listen();
        servidor.Log("Servidor no ar na porta "+servidor.getPort());

    }
    else
    {
        servidor.disconnect();
        jButton1.setText("Ativar");
		servidor.Log("Servidor desativado");
    }
        
    }
    catch(NumberFormatException e)
    {
        JOptionPane.showMessageDialog(null, "Porta inv�lida");
    }
    catch(BindException e)
    {
         JOptionPane.showMessageDialog(null, "Imposs�vel inicializar servidor");
    }
    catch(SocketException e)
    {
        JOptionPane.showMessageDialog(null, "Porta fechada");	
    }
    catch(IOException e)
    {
         JOptionPane.showMessageDialog(null, "Erro na abertura do servidor");        
    }
    
    // Add your handling code here:
  }//GEN-LAST:event_jButton1ActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new JServer ().show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    // End of variables declaration//GEN-END:variables

}