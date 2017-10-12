/*
 * Message.java
 *
 */


package chat;

import java.io.Serializable;

/** <B>JChat</B> Classe Mensagem / Message class
 * esta classe é responsável pela manipulaçao das mensagens em todos os pontos do pacote JChat
 * this class is responsible for the Message handling on every instance of the package
 * @author Herval Freire
 * @version 0.5b
 * Created on: 15/03/2001, 17:11
 * Last Review: 25/03/2001, 15:30
 */
public class Message implements MessageConstants, Serializable
{

    private Integer Header; // o primeiro cabecalho
    private String Origin; // origem
    private String Dest;	// destino
    private String Body;	// corpo da mensagem
 



/** cria um novo pacote / creates a new Message packet
 */
  public Message()
  {
	Header = new Integer(0);
	Origin = new String();
	Dest = new String();
	Body = new String();
  }
  

/** cria um novo pacote / creates a new Message packet
 * @param header o codigo da mensagem (seguindo convenção da interface MessageConstants) / the code's header (following the conveniences of MessageConstants)
 * @param origem a origem da mensagem / the message's origin
 * @param destino o destino da mensagem / the message's destination
 * @param mensagem a mensagem / the main message
 */
  public Message(int header, String origem, String destino, String mensagem)
  {
    setHeader(header);
    setOrigin(origem);
    setDest(destino);
    setMessage(mensagem);
  }

/** converte uma mensagem em string / converts a message to string
 * @return o string / the string
 */
  public String toString()
  {
    return getHeader()+" "+getOrigin()+" "+getDest()+" "+getMessage();
  }
 
  

/** retorna o corpo da mensagem / gets the message contents
 * @return texto da mensagem / message text
 */
  public String getMessage()
  {
    return this.Body;
  }
  
  
/** retorna o código da mensagem / gets the message header
 * @return o código da mensagem / the message packet code
 */
  public int getHeader()
  {
    return Header.intValue();
  }

  
/** retorna a origem da mensagem / gets the origin of the message
 * @return identificaçao do emissário / identification of the sender
 */
  public String getOrigin()
  {
    return Origin;
  }


/** retorna o destino da mensagem / gets the destination of the message
 * @return a identificação do destinatário / the destination's identification
 */
  public String getDest()
  {
    return Dest;
  }


/** seta o destino / sets the destination
 * @param dest a identificacao do destino / the destination's id
 */
  public void setDest(String dest)
  {
      Dest = dest;
  }
  
/** seta a origem da mensagem / sets the message's origin
 * @param orig a identificaçao da origem / the origin's identification
 */
  public void setOrigin(String orig)
  {
      
    Origin = orig;
  }
  
/** seta o corpo da mensagem / sets the message's body
 * @param mes o string da mensagem / the message string
 */
  public void setMessage(String mes)
  {
  	Body = mes;
  }

/** seta o tipo de mensagem / sets the kind of the message
 * @param o codigo do cabeçalho (seguindo especificações de MessageConstants) / code the header code (following the MessageConstants convention)
 */
  public void setHeader(int code)
  {
    Header = new Integer(code);
  }

}

