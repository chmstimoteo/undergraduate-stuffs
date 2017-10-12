/*
 * MessageConstants.java
 *
 */


package chat;

/** <B>JChat</B> Interface de Constantes de Mensagem / Message Constants Interface
 * @author Herval Freire
 * @author Sabrina Fabel
 * @version 0.51b
 * Created on: 15/03/2001, 17:11
 * Last Review: 27/03/2001
 */


// the MessageConstants define the packages which will cycle through server and client
/** Esta interface guarda todos os códigos de mensagem usados nas comunicações cliente/servidor
 * this Interface holds all the message codes used for the client/server communication
 *
 * nas descriçoes, 'blank' representa um campo irrelevante / in the descriptions, 'blank' means a unnecessary field
 */
public interface MessageConstants
{

	// constantes de capacidade

    /** O número máximo de conexões / The maximum number of connections allowed
     */
    public static final int MAXCONEXIONS = 100;
    /** O número máximo de canais / the maximum number of channels which can be created
     */
    public static final int MAXCHANNELS = 100;



    // constantes de Header

    // constantes recebidas pelo servidor

    
    // o cliente envia (HELLO, ANY, ANY, ANY) para dizer que se conectou
    /** Usado no pacote HELLO / Used in the HELLO package
     * Use: client/server side
     * Formato do pacote / Package format: (HELLO, blank, blank, blank)
     */
    public static final int HELLO				= 0;

    
    
    // o cliente envia (NICK, nickname novo, ANY, ANY) para informar seu nick
    /** Usado para enviar um nickname ao servidor / Used for sending a nickname to the server
     * Use: client side
     * Formato do pacote / Package format: (NICK, new nick, blank, blank)
     */
    public static final int NICK 				= 1;
	    
    
	// o cliente envia (LIST_ROOMS, ANY, ANY, ANY) para pedir a listagem de canais
        /** Usado para pedir por listagem de salas / Used to asking for a room list
         * Use: client side
         * Formato do pacote / Package format: (LIST_ROOMS, blank, blank, blank)
         */
    public static final int LIST_ROOMS			= 2;

        
        
	// o cliente envia (MSG, origem, destino, mensagem), onde origem eh ele proprio e destino eh o receptor
        /** Usado para enviar uma mensagem a outro usuário / Used to send a message to another user
         * Use: client/server side
         * Formato do pacote / Package format: (MSG, origin, destination, message)
         */
    public static final int MSG					= 3;

        
        
    // o cliente envia (MSG_TO_CHANNEL, orig, dest, msg), onde dest eh o nome do canal de destino
        /** Usado para enviar uma mensagem a um canal / Used to send a message to a channel
         * Use: client/server side
         * Formato do pacote / Package format: (MSG_TO_CHANNEL, origin (user), destinatio (channel), message)
         */
    public static final int MSG_TO_CHANNEL 		= 4;

    
    
    // o cliente envia (JOIN, ANY, canal a entrar, ANY)
    /** Usado para pedir para entrar em um canal / Used to ask to join a channel
     * Use: client side
     * Formato do pacote / Package format: (JOIN, blank, channel to join, blank)
     */
    public static final int JOIN				= 10;
    
    
    
    // o cliente envia(INFO_CHAN, canal, ANY, ANY)
    /** Usado para pedir informaçoes sobre um canal / Used for asking for a channel's info
     * Use: client side
     * Formato do pacote / Package format: (INFO_CHAN, channel, blank, blank)
     */
    public static final int INFO_CHAN = 13;
	
	
    
    // o cliente envia(EXIT_CHAN, nick, canal, ANY)
    /** Usado para pedir saida de um canal / Used for asking a channel's leaving
     * Use: client side
     * Formato do pacote / Package format: (EXIT_CHAN, nick, canal, blank)
     */
    public static final int EXIT_CHAN = 14;
	
	

	
// constantes recebidas pelo cliente:
	// o servidor manda (CHOOSE_NICK, ANY, ANY, ANY) pedindo o nick do cliente
    /** Pergunta o nickname / Asks for a nickname
     * Use: server side
     * Formato do pacote / Package format: (CHOOSE_NICK, blank, blank, blank)
     */
	public static final int CHOOSE_NICK			= 5;

        
	// o servidor manda (NICK_DENY, ANY, ANY, ANY) nega o nick do cliente
        /** Diz que o nick foi negado / Says that the nick was denied
         * Use: server side
        * Formato do pacote / Package format: (NICK_DENY, nick, blank, blank)
         */
    public static final int NICK_DENY			= 6;

        
    // o servidor manda (NICK_OK, nick, ANY, ANY) aceita o nick
        /** O nickname está OK / The nickname is OK
         * Use: server side
        * Formato do pacote / Package format: (NICK_OK, nick, blank, blank)
         */
    public static final int NICK_OK   			= 7;

    
    // o servidor manda (ROOM_LIST, ANY, ANY, ANY)
	// logo em seguida manda o Vector dos canais
    /** Usado para sinalizar uma listagem de canais. A lista e um Vector enviado em sequência / Used to sinalize a room list. The list is a Vector which is sent next
     * Use: server side
    * Formato do pacote / Package format: (ROOM_LIST, canal, blank, blank)
     */
    public static final int ROOM_LIST			= 8;

    
    	// o servidor manda (CHAN_INFO, nome do canal, ANY, ANY)
	// logo em seguida envia o JChatChannel do canal
    /** O servidor está enviando informaçoes do canal / The server is sending a channel's info
     * Use: server side
    * Formato do pacote / Package format: (CHAN_INFO, canal, blank, blank)
     */
	public static final int CHAN_INFO			= 9;

	
        
        // o servidor manda (JOIN_OK, canal, ANY, ANY), o cliente entrou no canal
        /** O cliente entrou no canal / The client joined the channel
         * Use: server side
        * Formato do pacote / Package format: (JOIN_OK, canal, blank, blank)
        */
	public static final int JOIN_OK				= 11;

        
	
        // o servidor manda (JOIN_DENY, ANY, ANY, ANY), o cliente nao pode entrar no canal
        /** O cliente não pode entrar no canal / The Client coudn't join the channel
         * Use: server side
        * Formato do pacote / Package format: (JOIN_DENY, blank, blank, blank)
         */
	public static final int JOIN_DENY			= 12;


	// o servidor manda (CHAN_UPDATE, canal, ANY, ANY);
        /** O canal foi atualizado / the channel was updated
         * Use: server side
        * Formato do pacote / Package format: (CHAN_UPDATE, canal, blank, blank)
         */
        public static final int CHAN_UPDATE			= 15;


//  public static final int MSG					= 8;  // jah definido
//	public static final int MSG_TO_CHANNEL 		= 10; // jah definido


	// o servidor manda(CLI_UNKNOWN, cliente, ANY, ANY);
        /** O cliente é deconhecido / Destination client is unknown
         * Use: server side
        * Formato do pacote / Package format: (CLI_UNKNOWN, client, blank, blank)
         */
        public static final int CLI_UNKNOWN			= 16;

        
        
	// o servidor manda(ADD_USER, canal, user, ANY);
        /** sinaliza que o usuário user foi adicionado ao canal / signs that user was added to channel
         * Use: server side
        * Formato do pacote / Package format: (ADD_USER, canal, user, blank)
        */
        public static final int ADD_USER			= 17;


	// o servidor manda(REM_USER, canal, user, ANY);
        /** sinaliza que o usuário foi removido do canal / signs that the user was removed from the channel
         * Use: server side
        * Formato do pacote / Package format: (REM_USER, canal, user, blank)
         */
        public static final int REM_USER			= 18;


        
	// o servidor manda (CH_USER, nick, nick novo, ANY);
        /** o servidor sinaliza que o usuário 'old' agora chama-se 'new' / server signs that 'old' user now is 'new'
         * Use: server side
        * Formato do pacote / Package format: (CH_USER, old nick, new nick, blank)
         */
        public static final int CH_USER				= 19;
	

	// o servidor manda (CH_SYS_MSG, canal, ANY, mensagem);
        /** O servidor manda uma mensagem de sistema ao canal / server sends a system message to channel
         * Use: server side
        * Formato do pacote / Package format: (CH_SYS_MSG, canal, blank, mensagem)
         */
        public static final int CH_SYS_MSG			= 20;


        // o servidor manda (SYS_MSG, ANY, ANY, mensagem);
            /** O servidor manda uma mensagem de sistema / server sends a system message
             * Use: server side
             * Formato do pacote / Package format: (SYS_MSG, blank, blank, mensagem)
             */
		public static final int SYS_MSG				= 21;    
}
