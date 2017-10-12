/*
 * JChatChannel.java
 *
 */


package chat;

import java.io.Serializable;
import java.util.Vector;

/** <B>JChat</B> Classe Canal / Channel Class
 * @author Herval Freire
 * @version 0.5b
 * Created on: 15/03/2001, 17:11
 * Last Review: 25/03/2001, 15:30
 */
public class JChatChannel implements Serializable
{
    /** número máximo de usuários / maximum number of users allowed
    */
	public static int MAXUSERS = 100;

	private String topic, nome;
	private Vector users; // vetor de nicks apenas


        /** construtor padrão / default constructor
         */
	public JChatChannel()
	{
		nome = new String();
		topic = new String();
		users = new Vector();
	}




   /** cria um canal com um nome identificador / creates a channel with a name identifier
     * @param name o nome do canal / the channel's ID
     */
	public JChatChannel(String name)
	{
		topic = new String();
		nome = name;
		users = new Vector(MAXUSERS);
	}


   /** retorna o tópico do canal / gets this channel's topic
    * @return o texto do tópico / the topic text
    */
	public String getTopic()
	{
	   return topic;
	}

   /** seta o nome do canal / sets the channel's ID
    * @param name o novo nome do canal / the new channel's ID
    */
	private void setName(String name)
	{
		nome = name;
	
	}

   /** pega o nome do canal / gets the channel's ID
    * @return a identificação do canal / the channel's identification
    */
	public String getName()
	{
		return nome;
	}


   /** seta o tópico do canal / sets a channel's topic
    * @param titulo o novo tópico / the new topic
    */
	public void setTopic(String titulo)
	{
	   topic = titulo;
	}

   /** retorna uma lista com os usuários atuais / returns a list of actual users
    * @return um vetor de Strings contendo os nicks dos usuários atuais / a vector of Strings containing the user's nicknames
    */
	public Vector getActualUsers()
	{
	   return users;
	}	
	
   /** adiciona um nick ao canal / adds a user to the channel
    * @param x a identificação / the user's id
    */
	public void addUser(String x)
	{
		users.addElement(x);
	}
	

	/** remove um usuário do canal / removes a user from the channel
	 * @param x a identificação do usuário / the user's id
	 */
	public boolean removeUser(String x)
	{
	
		return users.remove(x);
	}


        /** pega o número total de usuários no canal / gets the total users on the channel
         * @return a contagem de usuários atual / the actual user's count
         */
	public int getTotalUsers()
	{
		return users.size();
	}


}