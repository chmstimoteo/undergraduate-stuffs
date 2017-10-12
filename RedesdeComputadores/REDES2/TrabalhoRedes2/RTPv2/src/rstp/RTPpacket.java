package rstp;

public class RTPpacket{

	
	  static int HEADER_SIZE = 12;

	
	  public int Version;
	  public int Padding;
	  public int Extension;
	  public int CC;
	  public int Marker;
	  public int PayloadType;
	  public int SequenceNumber;
	  public int TimeStamp;
	  public int Ssrc;
	  
	  
	  public byte[] header;

	  
	  public int payload_size;
	 
	  public byte[] payload;
	  
	 
	  public RTPpacket(int PType, int Framenb, int Time, byte[] data, int data_length){
	    
	    Version = 2;
	    Padding = 0;
	    Extension = 0;
	    CC = 0;
	    Marker = 0;
	    Ssrc = 0;

	   
	    SequenceNumber = Framenb;
	    TimeStamp = Time;
	    PayloadType = PType;
	    
	  
	
	    header = new byte[HEADER_SIZE];

	    
	    header[0] = new Integer((Version<<6)|(Padding<<5)|(Extension<<4)|CC).byteValue();
	    header[1] = new Integer((Marker<<7)|PayloadType).byteValue();

	    header[2] = new Integer(SequenceNumber>>8).byteValue();
	    header[3] = new Integer(SequenceNumber).byteValue();

	    for (int i=0; i < 4; i++)
		header[7-i] = new Integer(TimeStamp>>(8*i)).byteValue();
	     
	    for (int i=0; i < 4; i++)
		header[11-i] = new Integer(Ssrc>>(8*i)).byteValue();

	   
	    payload_size = data_length;
	    payload = new byte[data_length];

	    
	    for (int i=0; i < data_length; i++)
		payload[i] = data[i];
	  }
	    
	 
	  public RTPpacket(byte[] packet, int packet_size)
	  {
	    
	    Version = 2;
	    Padding = 0;
	    Extension = 0;
	    CC = 0;
	    Marker = 0;
	    Ssrc = 0;

	    
	    if (packet_size >= HEADER_SIZE) 
	      {
		
		header = new byte[HEADER_SIZE];
		for (int i=0; i < HEADER_SIZE; i++)
		  header[i] = packet[i];

		
		payload_size = packet_size - HEADER_SIZE;
		payload = new byte[payload_size];
		for (int i=HEADER_SIZE; i < packet_size; i++)
		  payload[i-HEADER_SIZE] = packet[i];

		
		PayloadType = header[1] & 127;
		SequenceNumber = unsigned_int(header[3]) + 256*unsigned_int(header[2]);
		TimeStamp = unsigned_int(header[7]) + 256*unsigned_int(header[6]) + 65536*unsigned_int(header[5]) + 16777216*unsigned_int(header[4]);
	      }
	 }

	  
	  public int getpayload(byte[] data) {

	    for (int i=0; i < payload_size; i++)
	      data[i] = payload[i];

	    return(payload_size);
	  }

	 
	  public int getpayload_length() {
	    return(payload_size);
	  }

	  
	  public int getlength() {
	    return(payload_size + HEADER_SIZE);
	  }

	
	  public int getpacket(byte[] packet)
	  {
	    
	    for (int i=0; i < HEADER_SIZE; i++)
		packet[i] = header[i];
	    for (int i=0; i < payload_size; i++)
		packet[i+HEADER_SIZE] = payload[i];

	  
	    return(payload_size + HEADER_SIZE);
	  }

	 
	  public int gettimestamp() {
	    return(TimeStamp);
	  }


	  public int getsequencenumber() {
	    return(SequenceNumber);
	  }

	 
	  public int getpayloadtype() {
	    return(PayloadType);
	  }


	
	  public void printheader()
	  {
	    
	  }

	 
	  static int unsigned_int(int nb) {
	    if (nb >= 0)
	      return(nb);
	    else
	      return(256+nb);
	  }
	}


