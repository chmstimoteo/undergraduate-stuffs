package teste2;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundStream {

	
	
	AudioInputStream	 audioInputStream;
	private   int	EXTERNAL_BUFFER_SIZE =  0;
	int	nBytesRead = 0;
	byte[]	abData ;
	
	AudioFormat	audioFormat;
	private static final double FRAME_PERIOD = 0.1; //em segundos

	public SoundStream(String filename) throws Exception{

    File	soundFile = new File(filename);
	audioInputStream =  AudioSystem.getAudioInputStream(soundFile);
	
    audioFormat = audioInputStream.getFormat();
	

    //configura para 8820 bytes
 	EXTERNAL_BUFFER_SIZE = (int)(FRAME_PERIOD * audioFormat.getSampleRate()) * (audioFormat.getSampleSizeInBits()/8);
 	
    abData = new byte[EXTERNAL_BUFFER_SIZE];

	
	
	
	}


	public int getnextframe(byte[] frame) throws Exception
	{
		
		return  audioInputStream.read(frame, 0, frame.length);	 
	
	}




	
	public static void main(String[] args) {
		
		try {
			SoundStream s = new SoundStream("tempo.wav");
		   
			/*byte[] b = new byte[8820];
			
			s.getnextframe(b); 
			s.getnextframe(b); 
			s.getnextframe(b); 
			s.getnextframe(b); 
			s.getnextframe(b); 
			
			for (int i = 0; i < b.length; i++) {
				System.out.print(b[i]+" ");
			}
			*/
			System.out.println(s.getAudioFormat().toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	public AudioFormat getAudioFormat() {
		return audioFormat;
	}


	public void setAudioFormat(AudioFormat audioFormat) {
		this.audioFormat = audioFormat;
	}
	
	
}
