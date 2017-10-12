package chat;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Vector;



/** <B>JClient</B> Classe cliente / Client Side Class
 * @author Sabrina Fabel
 * @version 0.51b
 * Created on: 15/03/2001, 13:00
 * Last Review: 27/03/2001
 */
public class JClient
{
  private	Socket socket;
  private   	String nickName;	
  private	Message message;
  private	ObjectOutputStream output;
  private	ObjectInputStream input;
  private	Vector mensagens;
  private 	Vector channels;
  private	receiveThread receber;
  private	Vector all_channels;
  private	boolean conectado;
  private	boolean receiving;



/** Cria um novo Cliente JChat / Creates a new JChat Client
 * @param ip nome ou endereço do servidor / server name or address
 * @param porta porta para conexão / connection port
 * @param nick o nickname do cliente / client's nickname
 * @throws IOException
*/
public JClient(String ip,int porta,String nick) throws IOException
{
	nickName = nick;
 	mensagens = new Vector(100);
	channels = new Vector(MessageConstants.MAXCHANNELS);


	conectado = false;

	receiving = false;

	conectar(ip,porta);	
	
}




/////////////////////////////////////////////////////
// Metodos de conexao

/** Conecta o cliente com um servidor / connects a client to the server
 * @param ip nome ou endereço do servidor / server's name or address
 * @param porta porta para conexão / connection port
 * @throws IOException
*/
private void conectar(String ip, int porta) throws IOException
{
	socket = new Socket(ip,porta);
	output = new ObjectOutputStream(socket.getOutputStream());
	receiveThread receber = new receiveThread();
	
	System.out.println("Cliente conectado");
	
	conectado = true;

	receber.start(); 
}

/** Desconecta o cliente / disconnects the client
 * @throws IOException
*/
public void desconectar() throws IOException
{
	conectado = false;

	receber.desconectar();
	input.close();
	output.close();
	socket.close();	
	
}



/** Indica se o cliente está conectado / indicates if client is connected
 * @return true se o cliente estiver conectado, false caso contrário
 */
public boolean isConected()
{

	return conectado;

}

/////////////////////////////////////////////////////




/////////////////////////////////////////////////////
// Metodos de manipulação de mensagens


/** Adiciona a mensagem num vetor de mensagens / stores message into a messages vector
 *@param men messagem que vai ser adicionada / message to be added
*/
public void ArmazenaMensagem(Message men)
{
	mensagens.addElement(men);
}


/** Retorna o primeiro elemento do vetor de mensagens / returns the first element of the message vector
 * @return a primeira mensagem adicionada ao do Vetor (a mensagem e removida)
 */
public Message checaMensagem()
{
	Message auxi = null;

	while (auxi == null)
	{
		if (mensagens.isEmpty() == false)
		{
		  auxi = (Message)mensagens.firstElement();
	 	  mensagens.remove(mensagens.firstElement());
		}
	}

	return auxi;
}




/////////////////////////////////////////////////////




/////////////////////////////////////////////////////
// Métodos de canais

/** Pede ao servidor a lista de canais, espera receber e retorna esta lista
 * @return um Vetor de canais (Strings)
 */
public Vector listaCanais()
{
	  lista_ROOMS();		

	  while (all_channels == null)
	  {
	  }
	
	  return all_channels;

}


/** Sai de um determinado canal
*@param nome nome do canal que se deseja sair
*/
public void SaiCanal(String nome)
{

	exit(nome);

}


/** Entra no canal. O método só retorna o canal quando a entrada tiver sido finalizada
 * @param nome nome do canal que se deseja entrar
 * @return o canal (JChatChannel)
 */
public JChatChannel EntraCanal(String nome)
{


	join(nome);

	while (receiving == true)
	{
		// espere...
	}

	JChatChannel a = FindChannel(nome);
	
	return a;

}


/** Retorna um determinado canal já armazenado no lado cliente
 * @param nome nome do canal que se deseja retornar
 * @return um JChatChannel com referência ao canal
 */
public JChatChannel pegaCanal(String nome)
{
	while (receiving == true)
	{
	}


	JChatChannel a = FindChannel(nome);
	
	return a;
}


/** Pede as informações de um determinado canal ao servidor. Só retorna os dados quando a informação chegar
 *@param nome nome do canal que se quer informações
*/
private JChatChannel pedeCanal(String nome)
{

	JChatChannel a = null;

	sendInfoCanal(nome);

	while (receiving == true)
	{
	}

	a = FindChannel(nome);
	
	return a;

}


/** Procura um determinado canal no vetor de canais e o retorna
 *@param nome nome do canal a ser procurado
*/
private JChatChannel FindChannel(String nome)
{


	for(int i = 0; i<=channels.size()-1; i++)
	{
	   JChatChannel aux = (JChatChannel)channels.get(i);
	   if (nome.equals(aux.getName())) 
	   {
//	   	System.out.println("achei!");
	    return aux;
	   }
	}
	
		System.out.println("canal nao encontrado");

	return null;
}



/**Seta localmente as informações de um determinado canal
 *@param nome nome do canal cujas informações vão ser recebidas
*/
private void setChannelInfo(String nome)
{

	try
	{
		receiving = true;

		JChatChannel auxi = null;
		auxi = (JChatChannel)input.readObject();
		
		System.out.println("Chegaram os dados do canal: "+auxi.getName()+": "+auxi.getActualUsers().toString());
		

		JChatChannel auxbusca = FindChannel(auxi.getName());
		


	
		if (auxbusca != null)
		{
		
			channels.set(channels.indexOf(auxbusca), auxi);
			
			
			System.out.println("canal jah existe e foi atualizado");
		}
		else
		{
			channels.addElement(auxi);
			System.out.println("canal cadastrado: "+auxi.getName());
		}
		
		receiving = false;
		
	  
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	
}


/** Retorna o vetor de canais do cliente
 * @return o vetor de canais em que o cliente se encontra atualmente
 */

public Vector get_Channels()
{
	return channels;
}


/**Recebe o vetor de canais do servidor 
*/
private void receive_channels()
{

	try
	{

		Vector aux = (Vector)input.readObject();

		all_channels = aux;
		System.out.println("Recebi os canais");		

	}
	catch (Exception e)
	{
	
	}

}


/** Adiciona um nickname no canal
 *@param men mensagem enviada pelo servidor que contém o nickname e o canal onde ele vai ser adicionado
*/
private void Adiciona_Usuario(Message men)
{
	JChatChannel aux = FindChannel(men.getOrigin());
	
	if(aux != null)
		aux.addUser(men.getDest());

	ArmazenaMensagem(new Message(MessageConstants.CHAN_UPDATE, men.getOrigin(), "", ""));


}


/** Remove um nickname do canal
 *@param men mensagem enviada pelo servidor que contém o nickname e o canal onde ele vai ser removido
*/
private void Remove_Usuario(Message men)
{

	JChatChannel aux = FindChannel(men.getOrigin());
	
	if(aux != null)
		aux.removeUser(men.getDest());

	ArmazenaMensagem(new Message(MessageConstants.CHAN_UPDATE, men.getOrigin(), "", ""));

}


/** Troca todas as ocorrências de 'antigo' por 'novo' nos canais
 *@param s mensagem a ser enviada
*/
private void Muda_Usuario(String antigo, String novo)
{
	for(int i = 0; i <= channels.size()-1; i++)
	{
		JChatChannel aux = (JChatChannel)channels.get(i);
		
		if (aux.removeUser(antigo))
		{
			aux.addUser(novo);
			ArmazenaMensagem(new Message(MessageConstants.CH_SYS_MSG, aux.getName(), "", "Usuário "+antigo+" agora é conhecido como "+novo));
			ArmazenaMensagem(new Message(MessageConstants.CHAN_UPDATE, aux.getName(), "", ""));
		}
	}


}


/////////////////////////////////////////////////////





/////////////////////////////////////////////////////
// Métodos de envio de pacotes


/** Envia mensagem para o servidor
 *@param s mensagem a ser enviada
*/
private void send(Message s)
{	
		try	{
 			output.writeObject(s);		
			output.flush();
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
}



/** <B>Client Command:</B> Envia um pacote HELLO
*/
private void send_HELLO()
{
	Message aux = new Message(MessageConstants.HELLO,"","Server","");
	send(aux);
}

/** <B>Client Command:</B> Envia um pacote com o nickname
*/
private void send_NICK(String nick)
{
	Message aux = new Message(MessageConstants.NICK,nick,"Server","");
	send(aux);
}


/** <B>Client Command:</B> Requisita listagem de salas
*/
private void lista_ROOMS()
{
	all_channels = null;
	Message aux = new Message(MessageConstants.LIST_ROOMS,nickName,"Server", "");
	send(aux);
}
	

/** <B>Client Command:</B> Envia uma mensagem privada
*/
private void msg_PRIVATE(String destino, String texto)
{
	Message aux = new Message(MessageConstants.MSG,nickName,destino,texto);
	send(aux);

}


/** <B>Client Command:</B> Envia uma mensagem para canal
*/
private void msg_CHANNEL(String canal,String texto)
{
	System.out.println("Enviando mensagem para canal: "+canal+" Mensagem: "+texto);
	Message aux = new Message(MessageConstants.MSG_TO_CHANNEL,nickName,canal,texto);
	send(aux);
}


/** <B>Client Command:</B> Envia uma requisição de JOIN
*/
private void join(String canal)
{
	Message aux = new Message(MessageConstants.JOIN,nickName,canal,"");
    send(aux);
    receiving = true;
}


/** <B>Client Command:</B> Envia uma requisição de saída de canal
*/
private void exit(String chan)
{
	Message aux = new Message(MessageConstants.EXIT_CHAN,nickName,chan,"");
    send(aux);

}


/** <B>Client Command:</B> Envia um pedido de informações de canal
*/
private void sendInfoCanal(String canal)
{
	System.out.println("Estou no sendInfoCanal");
	Message aux = new Message(MessageConstants.INFO_CHAN,canal, "ANY","");
    send(aux);
	receiving = true;
}




/** Método público para envio de mensagens privadas
 * @param destino o destino da mensagem
 * @param mensagem a mensagem
 */
public void MensagemPvt(String destino, String mensagem)
{
	msg_PRIVATE(destino, mensagem);
}


/** Método público para envio de nickname
 * @param nick o nickname a ser enviado
 */
 public void enviaNick(String nick)
 {
  send_NICK(nick);
 }


 /** Método público para envio de mensagens em canal
 * @param canal o canal que vai receber a mensagem
 * @param mensagem a mensagem a ser enviada
 */
  public void MensagemParaCanal(String canal, String mensagem)
  {
      msg_CHANNEL(canal,mensagem);
  }
 /////////////////////////////////////////////////////
 /////////////////////////////////////////////////////
 // Métodos de controle de Nickname
 /** Pega o nickname atual do usuário
 * @return o nickname do usuário
 */
public String getNickname()
{
	return nickName;
}

/** Seta o nickname atual do usuário
*/
private void setNickname(String nick)
{
	nickName = nick;
}



/////////////////////////////////////////////////////



/////////////////////////////////////////////////////
// Método de checagem de mensagens


/** Verifica as mensagens enviadas pelo servidor
 *@param men mensagem que foi enviada pelo servidor
*/
public void verifica_mensagem(Message men)
{
	switch (men.getHeader()){

    case MessageConstants.HELLO : send_HELLO(); System.out.println("Servidor mandou HELLO");
	break;
	case MessageConstants.NICK_DENY: System.out.println("NICK_DENY");
									 ArmazenaMensagem(new Message(MessageConstants.SYS_MSG, "", "", "Nick inválido"));
									 ArmazenaMensagem(men);
	break;
	case MessageConstants.NICK_OK:  System.out.println("NICK_OK");
									Muda_Usuario(getNickname(), men.getOrigin());
									setNickname(men.getOrigin());
									System.out.println("Meu nick agora é "+men.getOrigin());
									ArmazenaMensagem(men);
	break;
	case MessageConstants.CHOOSE_NICK: System.out.println("CHOOSE NICK");
									   send_NICK(nickName);
	break;
	case MessageConstants.ROOM_LIST : receive_channels();
	break;
    case MessageConstants.CHAN_INFO : System.out.println("recebi chan info");
									  setChannelInfo(men.getOrigin());
	break; 
	case MessageConstants.CHAN_UPDATE: System.out.println("Update do canal "+men.getOrigin());
									  ArmazenaMensagem(men);
	break;
    case MessageConstants.JOIN_OK   : System.out.println("JOIN OK");
									  sendInfoCanal(men.getOrigin());
	break;
	case MessageConstants.JOIN_DENY :System.out.println("JOIN DENY");
									 receiving = false;
									 ArmazenaMensagem(new Message(MessageConstants.SYS_MSG, "", "", "Impossível entrar em canal"));
	break;
	case MessageConstants.CLI_UNKNOWN: ArmazenaMensagem(new Message(MessageConstants.SYS_MSG, "", "", "Usuário desconhecido "+men.getOrigin()));
	break;
	case MessageConstants.MSG: ArmazenaMensagem(men);
							   System.out.println("<"+men.getOrigin()+"> para <"+men.getDest()+"> "+men.getMessage());
	break;
    case MessageConstants.MSG_TO_CHANNEL: ArmazenaMensagem(men);
	   							System.out.println("<"+men.getOrigin()+"> para <"+men.getDest()+"> "+men.getMessage());
	break;
	case MessageConstants.ADD_USER: Adiciona_Usuario(men);
									ArmazenaMensagem(new Message(MessageConstants.CH_SYS_MSG, men.getOrigin(), "", men.getDest()+" entrou no canal"));
	break;
	case MessageConstants.REM_USER: Remove_Usuario(men);
									ArmazenaMensagem(new Message(MessageConstants.CH_SYS_MSG, men.getOrigin(), "", men.getDest()+" deixou o canal"));
	break;
	case MessageConstants.CH_USER: Muda_Usuario(men.getOrigin(), men.getDest());
	break;
	case MessageConstants.CH_SYS_MSG: ArmazenaMensagem(men);
	break;
	case MessageConstants.SYS_MSG: ArmazenaMensagem(men);
	break;

    default: break;
  	}
}



/////////////////////////////////////////////////////







/////////////////////////////////////////////////////
// Classe de recebimento de mensagens

class receiveThread extends Thread
{
	boolean conectado;
	Message men;
	 	

/** constroi um novo Thread de recepção
  */	 	
	public receiveThread()
  	{
		try	
		{

		this.setPriority(1);

		input = new ObjectInputStream(socket.getInputStream());
		conectado = true; 
		} 
		catch(IOException e)
		{
			System.err.println(e); 
		}
  	}


	/** Coloca false em conectado e o thread acaba
	*/
	public void desconectar()
	{
		conectado = false;
	}


/** Corpo principal do Receive Tread
  */
	public void run()
	{	
	    while (conectado == true)
		{
			try
			{
				men = (Message)input.readObject();
				verifica_mensagem(men);
			
				
				this.sleep(100);
			}
			catch(SocketException e)
			{
				System.out.println("Desconectado pelo servidor");
				ArmazenaMensagem(new Message(MessageConstants.SYS_MSG, "", "", "Desconectado pelo servidor"));
				conectado = false;
			
			}
			catch(IOException e)
			{
				System.err.println(e);
				desconectar(); 
			}
			catch(ClassNotFoundException e) 
			{
				System.err.println(e);
				desconectar(); 
			 }
			 catch(InterruptedException e)
			 {
			 	System.out.println("Erro de threading");
			 }
		}//fim do while
		
	}//fim do run


}//fim da classe receiveThread

/////////////////////////////////////////////////////





public static void main(String a[])
{


	try
	{

		JClient cli = new JClient("localhost",6668,"Bibi");
		cli.send_HELLO();
		cli.send_NICK(cli.getNickname());
	
	
		cli.join("novo2");
		cli.MensagemParaCanal("novo2", "aloooooo!!!!");
	
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}

	
}


}