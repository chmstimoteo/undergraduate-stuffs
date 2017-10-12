/*
 * JChatServer.java
 *
 */

package chat;


import java.net.*;
import java.io.*;
import java.util.Vector;


/** <B>JChat</B> Classe Servidor / Server Class
 * @author Herval Freire
 * @version 0.51b
 * Created on: 15/03/2001, 17:11
 * Last Review: 27/03/2001
 */
public class JChatServer
{

    private boolean fileLog;
    private Socket conexion;
    private ServerSocket serv;
    private int servport;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private FileOutputStream outfile;
    private ListenThread ouve;
    private Vector buffer;
    private Vector clientes;
    private Vector channel;

	private boolean client_updating;
	private boolean chan_updating;




/** Cria um novo JChatServer / Creates a new JChat Server
 * @param port a porta de escuta / the server listening port
 * @throws IOException
*/
public JChatServer(int port) throws IOException
{
		chan_updating = false;
		
		client_updating = false;
		
        fileLog = false;
        
        buffer = new Vector(100);
        
        clientes = new Vector(MessageConstants.MAXCONEXIONS);
        
        channel = new Vector(MessageConstants.MAXCHANNELS);

        servport = port;

        serv = new ServerSocket(port, MessageConstants.MAXCONEXIONS);
}


////////////////////////////////////////////////////////////
// Metodos de estado (Listen, porta, etc)

/** Bloqueia a porta e coloca-a em estado de escuta (um thread é criado para isto) / Blocks the port and places it in Listen status (a Thread is created for that)
 * @throws IOException
 */
public void Listen() throws IOException
{
		ouve = new ListenThread();
		
		ouve.start();
}


/** Desbloqueia a porta e para de receber conexoes / Unblocks the port and stops receiving connections
 * @throws IOException
 */
public void stopListening() throws IOException
{
            ouve.disconnect();
}



/** seta a porta de serviço / sets the server's port
 * @param port o número da porta / the port number
 */
public void setPort(int port)
{
    servport = port;
}


/** retorna a porta de escuta / returns the listening port
 * @return a porta atual / the actual port
 */
public int getPort()
{
    return servport;
}


////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////
// Metodos de finalizacao do servidor


/** desconecta todos os clientes / disconnects all clients
 */
private void finalizeAll()
{

for(int i = 0; i<=clientes.size()-1; i++)
    {
	
	ClientThread aux = (ClientThread)clientes.get(i);
	aux.disconnect();
	
    }
	
}


/** finaliza o estado de listen / closes the listening state
 */
protected void disconnect() throws SocketException
{
    try
    {
         ouve.disconnect();                
         serv.close();
		 finalizeAll();
         outfile.close();   
    }
    catch(IOException e)
    {
         Log(e.toString());
    }
	catch(NullPointerException e)
	{
	}
         
}

////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////
// Metodos para geracao de logs

/** seta o log em arquivos / Sets the file logging true or false
 * @param state o estado atual de log - true abre o arquivo, false fecha / the actual logging state - true opens the file for writing, false closes it
 */
public void setFileLogging(boolean state)
{
        fileLog = state;
        
        try
        {
            if(fileLog == true) outfile = new FileOutputStream("log.txt", true);
  			
			else outfile.close();
			
			
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
}
    

    
/** loga erros e eventos para arquivos ou buffer / Logs any errors or events to file or buffer
 * @param line a linha a ser gravada / the line to be recorded
 */
public void Log(String line)
{
     
    try
    {
        buffer.addElement(line+"\n");
       
        if (fileLog == true)
        {
            outfile.write(line.getBytes());
            outfile.flush();
        }
    }
    catch(IOException e)
    {
        System.out.println(e.toString());
    }
        
}

 

/** recupera mensagens gravadas para que as mesmas possam ser tratadas pela interface - as mensagens são gravadas e acessadas de forma sequencial / returns logging messages so they can be shown at a GUI interface - these messages are internally stored and can be accessible sequentially
 * @return a primeira mensagem gravada no buffer / messages stored on the buffer
 */
public String GetBufferMessage()
{
    if(buffer.isEmpty() == false)
    {
        String a = (String)buffer.firstElement();   
        buffer.remove(buffer.firstElement());
        
        return a;
    }
    
    else
        return null;
    
}

////////////////////////////////////////////////////////////



   

////////////////////////////////////////////////////////////
// Metodos de busca


/** procura por um usuário no servidor / searches for a user on the server
 * @param nickname o nickname do cliente / the client's nickname
 * @return retorna uma referência ao ClientThread do cliente procurado / returns a reference to the required client ClientThread
 */
public ClientThread FindUser(String nickname)
{

	while (client_updating == true)
	{
	}
	
	client_updating = true;


//	System.out.println("Procurando usuario: "+nickname);
	
	for(int i = 0; i<=clientes.size()-1; i++)
	{
	   ClientThread aux = (ClientThread)clientes.get(i);
	   if(aux.getNickname() != null)
	   		if (aux.getNickname().equals(nickname)) 
		    {
				client_updating = false;
			   return aux;
		    }
	}

	client_updating = false;
	return null;
}


/** acha um canal no servidor / finds a channel in the server
 * @param nome o nome do canal / the channel's name
 * @return uma referencia ao JChatChannel do canal / a reference to the channel's JChatChannel
 */
public JChatChannel FindChannel(String nome)
{

	while (chan_updating == true)
	{
	}

	chan_updating = true;

	for(int i = 0; i<=channel.size()-1; i++)
	{
	   JChatChannel aux = (JChatChannel)channel.get(i);
	   if (nome.equals(aux.getName())) 
	   {
	   	chan_updating = false;
	    return aux;
	   }
	}

   	chan_updating = false;

	return null;
}


////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////
// Metodos de gerenciamento de canais

/** Cria um canal no servidor / Creates a channel on the server
 * @param nome o nome do canal / the channel's name
 */
public void createChannel(String nome)
{
  if ((nome != null) && (nome.length()>0))
  {

    JChatChannel canal = new JChatChannel(nome);
	JChatChannel existe = FindChannel(nome);
	
	if (existe == null)
	{
	   channel.addElement(canal);
   	   Log("Canal criado: "+nome);
	}
  }
}

    
/** método para retornar os canais no servidor / method to get the server channels
 * @return retorna a lista de canais - um vetor de JChatChannels / returns the list of channels - a Vector of JChatChannels
 */
public Vector getChannels()
{

	return channel;

}


/** remove um canal do servidor / removes a channel from the server
 * @param nome o nome do canal / the channel's name
 */
public void removeChannel(String nome)
{
	JChatChannel existe = FindChannel(nome);
	
	if (existe != null)
	{
	   channel.removeElement(existe);
   	   Log("Canal removido: "+nome);
	}
}



/** remove um usuário de todos os canais / removes the user from all channels
 * @param nick o nick a ser removido / the user to be removed
 */
private void SaiCanais(String nick) throws IOException
{

	for(int i = 0; i<=channel.size()-1; i++)
	{
		JChatChannel a = (JChatChannel)channel.get(i);
		a.removeUser(nick);
		atualizaCanal(a.getName(), nick, false);

		if(a.getTotalUsers() <= 0)
			removeChannel(a.getName());
	}

}


/** adiciona ou tira um usuario de um canal / adds or removes a user from a channel
 * @param canal o canal a ser atualizado / the channel to be atualized
 * @param usuario o usuário a modificar / the user to modify
 * @param entrando true indica um usuário entrando no canal, false indica saindo / true means that 'usuario' is entering channel, false means he is leaving
 * @throws IOException
 */
public void atualizaCanal(String canal, String usuario, boolean entrando) throws IOException
{

	JChatChannel aux = FindChannel(canal);


	if (aux != null)
	{
		Vector usuarios = aux.getActualUsers();

		for(int i = 0; i<=usuarios.size()-1; i++)
		{
			String auxUser = (String)usuarios.get(i);
			ClientThread sender = FindUser(auxUser);

			if (sender.getNickname() != usuario)
			{
				if(entrando == true)
					sender.sendUsuarioEntra(usuario, canal);
				else sender.sendUsuarioSai(usuario, canal);	
			}
		}
	}


}

/*		
public void atualizaCanal(String chan) throws IOException
{
	JChatChannel aux = FindChannel(chan);

//	System.out.println("Cheguei no atualizacanal");

	if (aux != null)
	{
		Vector usuarios = aux.getActualUsers();

//		System.out.println("entrei no if");
	
		for(int i = 0; i<=usuarios.size()-1; i++)
		{
			String auxUser = (String)usuarios.get(i);
			ClientThread sender = FindUser(auxUser);

			sender.sendChannel(chan);

			sender.send(new Message(MessageConstants.CHAN_UPDATE, chan, "", ""));

		}
	}
		
}	

*/

/** envia uma mensagem para todos os clientes no canal / Sends a message to every client on a channel
* @param canal o nome do canal-alvo / name of the target channel
* @param msg a mensagem a ser enviada / message to be sent to it
* @throws IOException
* @see ToChannelMsg
*/
protected void sendToChannel(JChatChannel canal, Message msg) throws IOException
{
	    

		
   	Vector TempClients = canal.getActualUsers();
    	
   	for(int i = 0; i<=TempClients.size()-1; i++)
   	{
		String nick = (String) TempClients.get(i);
		System.out.println("Cliente: "+nick);
			
		ClientThread aux = FindUser(nick);
		System.out.println("Enviando mensagem "+msg.getMessage()+" de "+msg.getOrigin()+" para o canal "+msg.getDest());

		System.out.println(aux.getNickname());
		
		if (aux != null)
   	   		aux.send(msg);

   	}
	    
	    
}



/** troca um nickname por outro em todos os canais / changes a nickname for another in all the servers channels
 * @param antigo o nick antigo / the old nickname
 * @param novo o nick novo / the new nickname
 */
public void chNickOnChannels(String antigo, String novo)
{

  try
  {
	
	for(int i = 0; i<=channel.size()-1; i++)
	{
		JChatChannel toChange = (JChatChannel)channel.get(i);
		
		if(toChange != null)
			if (toChange.removeUser(antigo))
			{

				Vector usuarios = toChange.getActualUsers();
				for(int j = 0; j<=channel.size()-1; j++)
				{
					String us = (String)usuarios.get(i);
					ClientThread aux = FindUser(us);
					aux.sendChUser(antigo, novo);		
				}
				toChange.addUser(novo);
			}
	}
  }
  catch(IOException e)
  {
		System.out.println(e.toString()+"aaa");
  }
  catch(Exception e)
  {

  }
}



////////////////////////////////////////////////////////////
// Classe ClientThread


/** Class responsible for handling every client on the chat system
 */
	class ClientThread extends Thread
	{

	    private Socket client;
	    private String nickname;
	    private ObjectOutputStream out;
	    private ObjectInputStream in;
	    private boolean conectado;

        /** construction of a new client thread
         * @see #Listen
         * @param s the socket of the client
         */												 
	    public ClientThread(Socket s)
	    {
	        try
	        {
	        client = s;
	        
			
	        s.setKeepAlive(true);
			s.setSoTimeout(60000);

	        this.setPriority(3);


	            in = new ObjectInputStream(s.getInputStream());
	            out = new ObjectOutputStream(s.getOutputStream());

	            conectado = true;
	        }
	        catch(Exception e)
	        {
	            System.out.println("Erro no threading de clientes");
	        }
	    }


            /**
             * @return returns the output stream of this client
             */
	    public ObjectOutputStream getOutputStream()
	    {
	        return out;
	    }


            /** gets a client's socket
             * @return this client's socket
             */
		public Socket getSocket()
		{
			return client;
		
		}

            /** gets a client's nickname
             * @return this user's nickname
             */
	    public String getNickname()
	    {
	        return nickname;
	    }


		////////////////////////////////////////////////////////////
		// Laço principal do Thread cliente

            /** ClientThread's main body
             */
	    public void run ()
	    {

	        String mensagem = new String();
	        Message msg = new Message();

	        while(conectado == true)
	        {
	            try
	            {
	                msg = (Message) in.readObject();
	                trataMensagem(msg);

					this.sleep(50);

	            }
				catch(InterruptedIOException e)
				{
					try
					{
						this.sayHello();
					}
					catch(IOException ex)
					{
						Log(client.getInetAddress()+" Timed out");
						this.disconnect();
					}
					
				}
				catch (SocketException e)
				{
					Log(client.getInetAddress()+" Conexao fechada");
					this.disconnect();
				
				}
	            catch (Exception e)
	            {
	                System.out.println (e.toString());
	                this.disconnect();
	            }
	        }

	    }

		////////////////////////////////////////////////////////////

	    /** Filtra cada mensagem recebida para que o servidor saiba o que fazer / Filters every received message so the server can know what to do
	     * @param msg a mensagem a ser enviada / the message to be sent
         * @throws IOException
         */
	    public void trataMensagem(Message msg) throws IOException
	    {
	    		if (getNickname() == null)
	    		{
	    			switch(msg.getHeader())
	        		{
		    			case MessageConstants.HELLO: Log(getSocket().getInetAddress()+" HELLO"); 
													 chooseNick();
		    			break;
		    			case MessageConstants.NICK: Log(getSocket().getInetAddress()+" Nick: "+ msg.getOrigin()); 
													setNickname(msg.getOrigin());
	    				break;
	        			default: break;
	        		}
	    		}
	    		else
	    		{
	    			switch (msg.getHeader())
	    			{
	          			case MessageConstants.HELLO: Log(getSocket().getInetAddress()+" HELLO");
		         		break;
		            	case MessageConstants.LIST_ROOMS: Log(getSocket().getInetAddress()+" LIST ROOMS"); SendRoomList();
		    			break;
	    	            case MessageConstants.MSG_TO_CHANNEL: ToChannelMsg(msg); //System.out.println("Recebi msg to channel de "+msg.getOrigin()); 
	        	        break;
	            	    case MessageConstants.NICK: Log(getSocket().getInetAddress()+" Nick: "+ msg.getOrigin()); 
													resetNickname(msg.getOrigin());
		                break;
		                case MessageConstants.MSG: PvtMsg(msg);
	    		    	break;
	        	        case MessageConstants.INFO_CHAN: sendChannel(msg.getOrigin()); //System.out.println("Recebi INFO CHAN"); 
	            	    break;
	                	case MessageConstants.JOIN: ClientJoin(msg.getDest());
													Log(getSocket().getInetAddress()+ " JOIN "+msg.getDest()); 
		    	    	break;
		    			case MessageConstants.EXIT_CHAN: ClientExit(msg.getDest());
		                default: break;
	    			}
	    		}
	    }





		////////////////////////////////////////////////////////////
		// Metodos de envio de mensagens


	    /** Sends msg to all active ClientThreads
          * @param msg the message to be sent
          * @throws IOException
          */
	    public void SendToAll(Message msg) throws IOException
	    {

	        for(int i = 0; i<=clientes.size()-1; i++)
	        {
	            ClientThread aux = (ClientThread) clientes.get(i);
	            aux.send(msg);
	        }
	    }

	
	
	    /** Sends a message to receiver
	     * @param msg the message to be sent
	     * @param receiver the message's receiver
	     */
	    private void send(Message msg) throws IOException
	    {
	        out.writeObject(msg);
			out.flush();
	    }
	
	    
	         /** sends a channel
	         * @param x the channel to be sent
	         * @throws IOException
	         */
	    private void send(JChatChannel x) throws IOException
	    {
	        out.writeObject(x);
			out.flush();
	    }


            /** sends a vector (of Strings, JChatChannels, etc)
             * @param x the vector to be sent
             * @throws IOException
             */
		private void send(Vector x) throws IOException
		{
	        out.writeObject(x);
			out.flush();					
		}
		
                /** removes this client
                 */
		public void RemoveMe() throws IOException
		{
			SaiCanais(this.getNickname());
			clientes.remove(this);
		}



                /** <B>Server Command:</B> Sends a choose nick package
                 * @throws IOException
                 */
		public void chooseNick() throws IOException
		{

			Message msg = new Message(MessageConstants.CHOOSE_NICK, "Server", "", "");
			send(msg);
		}

                /** <B>Server Command:</B> Sends a Join OK package
                 * @param chan the channel which this user has entered
                 * @throws IOException
                 */
		public void sendJoinOK(String chan) throws IOException
		{
			Message msg = new Message(MessageConstants.JOIN_OK, chan, this.getNickname(), "");
			send(msg);
			
		}
		

                /** sends a 'USER CHANGE' package to this user
                 * @param antigo the old nick
                 * @param novo the new nick
                 * @throws IOException
                 */
		public void sendChUser(String antigo, String novo) throws IOException
		{
			Message msg = new Message(MessageConstants.CH_USER, antigo, novo, "");
		
			send(msg);
		}
		
                /** <B>Server Command:</B> Sends a Join Deny message
                 * @param chan the channel which the user could't join
                 * @throws IOException
                 */
		public void sendJoinDeny(String chan) throws IOException
		{
			Message msg = new Message(MessageConstants.JOIN_DENY, chan, this.getNickname(), "");
			send(msg);
		}


                /** sends a CHANNEL UPDATE package to this user
                 * @param canal the channel to be updated by the interface
                 * @throws IOException
                 */
		public void sendChanUpdated(String canal) throws IOException
		{

			Message msg = new Message(MessageConstants.CHAN_UPDATE, canal, "", "");
			send(msg);
	
		}


                /** <B>Server Command:</B> Sends a NickList (Vector of Strings) of the JChatChannel
                 * @param x the name of the channel which list of users will be sent
                 * @throws IOException
                 */
		public void sendChannel(String x) throws IOException
		{

//			System.out.println("cheguei no send channel");

            JChatChannel canal = FindChannel(x);
			
			if (canal != null)
			{
			
                Message msg = new Message(MessageConstants.CHAN_INFO, x, "Server", "");
			    send(msg);
				
		
			    send(canal);

             }
                        
		}


		
                /** tells that a user joined the channel
                 * @param user the user
                 * @param chan the joined channel
                 * @throws IOException
                 */
		public void sendUsuarioEntra(String user, String chan) throws IOException
		{
		
			Message m = new Message(MessageConstants.ADD_USER, chan, user, "");
		
			send(m);									 
		}


                /** tells that a user leaved a channel
                 * @param user the user
                 * @param chan the channel
                 * @throws IOException
                 */
		public void sendUsuarioSai(String user, String chan) throws IOException
		{
		
			Message m = new Message(MessageConstants.REM_USER, chan, user, "");
		
			send(m);									 
		}

		/** <B>Server Command:</B> Says hello to this client
		 * @throws IOException
		 */
		public void sayHello() throws IOException
		{
			Message msg = new Message();
			msg.setHeader(MessageConstants.HELLO);

			send(msg);
		}



            /** <B>Server Command:</B> Sends a nick deny
             * @throws IOException
             */
		public void sendNickDeny() throws IOException
	    {
	        Message msg = new Message(MessageConstants.NICK_DENY, "Server", 
			getNickname(), "");
	
			System.out.println("Nick Deny");
	
	        send(msg);
	    }
	


	
            /** <B>Server Command:</B> Sends a Nick OK package
             * @throws IOException
             */
	    public void sendNickOK() throws IOException
	    {
			System.out.println("NICK OK");
	        Message msg = new Message(MessageConstants.NICK_OK, getNickname(), "", "");
	
	        send(msg);
	    }
	
	
            /** <B>Server Command:</B> Sends a Room List (Vector of Strings)
             * @throws IOException
             */
		public void SendRoomList() throws IOException
		{
			Vector canais = new Vector();
			
			for(int i = 0; i<=channel.size()-1; i++)
			{
				JChatChannel aux = (JChatChannel)channel.get(i);
			
				canais.addElement(aux.getName());
			}
	
		
			Message msg = new Message(MessageConstants.ROOM_LIST, "Server", this.getNickname(), "");

			send(msg);

			send(canais);
			System.out.println("Enviei os canais");
		}


	
                /** sends a 'client unknown' message
                 * @param nick the nickname
                 * @throws IOException
                 */
		public void sendCliUnknown(String nick) throws IOException
		{
		
			Message msg = new Message(MessageConstants.CLI_UNKNOWN, nick, "", "");

			send(msg);
		
		}
	

		////////////////////////////////////////////////////////////



		////////////////////////////////////////////////////////////
		// Metodos de gerenciamento das mensagens

                /** removes the user from a channel
                 * @param chan the channel's name
                 * @throws IOException
                 */
		public void ClientExit(String chan) throws IOException
		{
		
			JChatChannel canal = FindChannel(chan);
			
			if (canal != null)
			{
				canal.removeUser(this.getNickname());

				System.out.println("Usuarios no canal: "+canal.getTotalUsers());
				if (canal.getTotalUsers() <= 0)
				{
					removeChannel(canal.getName());
				}
				
				else
					atualizaCanal(canal.getName(), this.getNickname(), false);
				
			}										 
	
		}

		/** Sets this user's nickname
		 * @param nick the new nickname
		 * @throws IOException
		 */
		public void setNickname(String nick) throws IOException
		{

		  if ((nick != null) && (nick.length()>0))
		  {
		
			ClientThread aux = FindUser(nick);
		
			if (aux == null)
			{
//			   System.out.println("Ninguém tem o nick "+nick);
			   nickname = nick;

			   sendNickOK();

			}
		  
			
			else
			{
			   sendNickDeny();
			}
		  }
		  else
		  {
		  	 sendNickDeny();
		  }
		
		}


	   /** Changes this user's nickname
		 * @param nick the new nickname
		 * @throws IOException
		 */
		public void resetNickname(String nick) throws IOException
		{

			String atual = this.getNickname();

		  if ((nick != null) && (nick.length()>0))
		  {
		
			ClientThread aux = FindUser(nick);
		
			if (aux == null)
			{
//			   System.out.println("Ninguém tem o nick "+nick);
			   nickname = nick;
			   chNickOnChannels(atual, nick);
			   sendNickOK();
			}
		  	else
			{
			   sendNickDeny();
			}
		  }
		  else
		  {
		  		sendNickDeny();
		  }
		
		}


        /** Tries to find a channel required by the user and places him on it
                 * @see SendJoinOK#
                 * @see SendJoinDeny#
                 * @param chan the joined channel
                 * @throws IOException
                 */
		public void ClientJoin(String chan) throws IOException
		{
		
			JChatChannel canal = FindChannel(chan);
			
			if (canal != null)
			{
				canal.addUser(this.getNickname());
				
				System.out.println(canal.getActualUsers().toString());
				
				sendJoinOK(chan);
				
				System.out.println(" Usuarios atuais: "+canal.getActualUsers().toString());
				
				atualizaCanal(chan, this.getNickname(), true);
		
			}
			else
			{

				createChannel(chan);
				canal = FindChannel(chan);
				
				if (canal != null)
				{
					canal.addUser(this.getNickname());

					System.out.println(canal.getActualUsers().toString());
				
					sendJoinOK(chan);
				
					System.out.println(" Usuarios atuais: "+canal.getActualUsers().toString());
				}
				else sendJoinDeny(chan);

			}
		
		}




                /** <B>Server Command:</B> Sends a Private Message to other user
                 * @param x the message
                 * @throws IOException
                 */
		public void PvtMsg(Message x) throws IOException
		{
		
			ClientThread destino = FindUser(x.getDest());

			System.out.println("Enviando msg: "+x.getOrigin()+ " para "+x.getDest());
			
			if(destino != null)
				destino.send(x);
			else
			{
				sendCliUnknown(x.getDest());
				System.out.println("Cliente desconhecido: "+x.getDest());
			}
		
		}


	
                /** <B>Server Command:</B> Sends a message to a channel
                 * @throws IOException
                 * @param m the message
                 */
		public void ToChannelMsg(Message m) throws IOException
		{
		
			JChatChannel canal = FindChannel(m.getDest());
			
	
			if(canal != null)
				sendToChannel(canal, m);
		
		
		}

		////////////////////////////////////////////////////////////
	    
	
                /** Disconnects this Client and finishes it
                 */
	    public void disconnect()
	    {
	    
			try
			{
			   SaiCanais(this.getNickname());

			   conectado = false;	        
	       	           in.close();
	                   out.close();
					   
			   RemoveMe();
			}
			catch(IOException e)
			{
			   System.out.println(e.toString()+" no disconnect do thread cliente");
			}

	    }
	
	    
	    
	    ////////////////////////////////////////////////////////////
	

	} // fim do ClientThread



////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////
// Classe Listen Thread

private class ListenThread extends Thread
    {
  	   private boolean conectado;


        /** stops the Listen Thread
         */
       public void disconnect()
       {
              conectado = false;
       }
       
	    
    /** ListenThread's constructor
     */
       public ListenThread()
	   {
	  	   conectado = true;
       }


    /** ListenThread's main body
     */
       public void run()
  	   {
	      while (conectado == true)
	      {
		    try
		    {
    	        conexion = serv.accept();
		    	Log("Conexão recebida: " + conexion.getInetAddress()+"\n");
                           
                ClientThread newclient = new ClientThread(conexion);
	            clientes.addElement (newclient);
	            newclient.start();
		    }
			catch(IOException e)
			{
		      System.out.println(e.toString());			
			}
	      }

            
       }
    
    }



////////////////////////////////////////////////////////////


    public static void main(String args[])
    {
 
        try
        {

            JChatServer servidor = new JChatServer(6666);
            servidor.setFileLogging(true);
            servidor.Log("Servidor no ar na porta "+servidor.getPort());

            servidor.Listen();
			

			
			
			while (true)
			{
                            String aux;
                            
                            if((aux = servidor.GetBufferMessage()) != null)
                            System.out.println(aux);
			}
			
            
        }
		catch(BindException e)
		{
			System.out.println("Porta ocupada: "+e.toString()+"\n");
		}
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

}

