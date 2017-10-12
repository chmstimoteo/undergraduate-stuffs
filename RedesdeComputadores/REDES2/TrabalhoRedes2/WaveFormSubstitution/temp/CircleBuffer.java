package temp;

import java.util.Arrays;
import java.util.Vector;

import src.RTPpacket;

public class CircleBuffer {

	
	private byte[] buffer;
	
	private int lenghtUnit;
	
	private boolean[] flags;
	
	private int nUnits;
	
	private int lastUnitRead;
	
	

	
	private int lastUnitWrite;
	
	//maxCapacity em termos de unidade
	public CircleBuffer( int maxCapacity,int lenghtUnit) {
		super();
						
		this.nUnits = maxCapacity+1;
		this.lenghtUnit = lenghtUnit;
		
		this.buffer = new byte[lenghtUnit*maxCapacity];
			
		this.flags = new boolean[nUnits];
				
		this.lastUnitRead = this.lastUnitWrite = 0;
		
	    	
	}
	
	
	
	
	public void addUnit(int nseq,byte[] payload) {
		
	 if( !isFull() ){
		
		
		
		
		if(flags[(nseq-1)%nUnits] == false)
		{
			insert( payload , ((nseq-1) % nUnits) *lenghtUnit);
			flags[(nseq-1)%nUnits] = true;
			
			this.lastUnitWrite++;
			
			if( ( (nseq-1) %nUnits) == this.lastUnitWrite )
			{
				
			}
			
		
		}
		
		
		
		return;
	 }
	 
	 throw new RuntimeException("buffer cheio");
		
	}
	
	public byte[] removeUnit() {
		
		if(!isEmpity()){
		
		 if(flags[(this.lastUnitRead)%nUnits] == true)
		 {
		
		   flags[this.lastUnitRead % nUnits] = false;
		   this.lastUnitRead++;
		  return remove(((this.lastUnitRead-1) % nUnits));
		}else{
			
			return runAlgorithm();
		}
		
	  }
		
		throw new RuntimeException("esta vazio");
    }
	
	
	
	private byte[]  remove(int index) { 
		
		byte[] result = new byte[lenghtUnit];
		
	    for (int i = 0; i < result.length; i++) {
	 	     result[i]  = this.buffer[ index  + i];
	    }
	    
	    return result;
  }
	
	
	
	private void insert(byte[] payload,int index) {
		
	
	      for (int i = 0; i < payload.length; i++) {
	 	     this.buffer[index+i] = payload[i];
	      }
	
    }
	
	

	
	
	
	
	public boolean isEmpity() {
		
		 return  this.lastUnitRead  == this.lastUnitWrite;
	}
	
	public  boolean isFull() {
		return  (this.lastUnitWrite +  1)%this.nUnits == this.lastUnitRead;
	}
	
	
	public static void main(String[] args) {
		
		
		CircleBuffer c = new CircleBuffer(3,100);
		
		byte[] unit = new byte[100];
		
		c.addUnit(1, unit);
		c.addUnit(2, unit);
		c.addUnit(3, unit);
	
		
		System.out.println(c.isFull());
		
		c.removeUnit();
		c.removeUnit();
		c.removeUnit();
	
		
		System.out.println(c.isFull());
		
	}




	private byte[] runAlgorithm() {
		
		   CrossCorrelation cc = new CrossCorrelation(buffer,lenghtUnit,0,lenghtUnit,0,buffer.length,CODE.SHORT_SIGNED);
			
		    WaveformSubstitutionPatternAlgorithm w = 
			
		    	
		   new WaveformSubstitutionPatternAlgorithm(buffer,2,10,8,2,0,cc);
			

			
		 RecoveryAlgorithm recoveryAlgorithm = new 
			 WaveformSubstitutionPatternAlgorithm(buffer,2,10,8,2,0,cc);
		 
		 
		 return recoveryAlgorithm.getNextPayloadByteArray();
		 
		
		 
	}
	
}
