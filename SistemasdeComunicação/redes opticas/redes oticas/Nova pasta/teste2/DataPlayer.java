package teste2;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat.Encoding;

public class DataPlayer {

	SourceDataLine	line = null;
	DataLine.Info	info = null;
	AudioFormat audioFormat = null;
	
	
	public  DataPlayer() {
		
		
		
		audioFormat = new AudioFormat(Encoding.PCM_SIGNED,11025,16,1,2,11025,false);
		
		info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try
		{
			line = (SourceDataLine) AudioSystem.getLine(info);

			
			line.open(audioFormat);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}


	}
	
	public void start() {
		line.start();
	}
	
	public void play(byte[] abData,int offset,int lenght)
	{
		 line.write(abData, offset, lenght);
	}
}
