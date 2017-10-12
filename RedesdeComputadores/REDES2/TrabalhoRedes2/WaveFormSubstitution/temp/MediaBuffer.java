package temp;

public class MediaBuffer {

	private int nextByteReady;
	private int nextByteWrite;
	private int frameSize;
	private int frameBufferSize;
	
	private byte[] buffer;
	
	
	public int capacity(){
		if(nextByteReady > nextByteWrite){
			return (nextByteReady-nextByteWrite)/frameSize;
		}else{
			return (buffer.length - (nextByteWrite-nextByteReady))/frameSize;
		}
	}
	
	public boolean isEmpty(){
		return nextByteReady==-1;
	}
	
	public void clear(){
		nextByteReady = -1;
		nextByteWrite = 0;
	}
	
	/*public int write(byte[] data,int position,int length,int framePosition){
		int capacity = capacity() * frameSize;
		int writed = length > capacity? capacity:length;

		for(int i = 0 ; i < writed;i++){
			buffer[(end + i)%buffer.length] = data[i + position];
		}
		
		end = (end + writed)%buffer.length;
		return writed;
	}*/
}
