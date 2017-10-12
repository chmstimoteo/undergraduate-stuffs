package temp;

import java.io.File;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;

import sun.awt.windows.ThemeReader;

public class Sound {
   
   
    public static void main(String[] args) throws InterruptedException {

        try {
            File soundFile = new File("wave1.WAV");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            
            AudioFormat format = audioStream.getFormat();
            Info info = new Info(SourceDataLine.class, format);
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);
            audioLine.start();
            int nBytesRead = 0;
            byte[] abData = new byte[4];
           
        
            System.out.println(format.toString());
            double frameRate = format.getFrameRate();
            double frameSize = format.getFrameSize();
            double byteRate = frameRate*frameSize;        //por segundo.
            double bytesIn20ms = byteRate*0.02;
            System.out.println("Bytes in 20ms: " + bytesIn20ms);
           
            
//            for (int i = 0; i < abData.length; i++) {
//            	abData[i] = (byte)(10* Math.sin((i/4)*5));
//
//			}
//            
//            double[] abData2 = new double[abData.length];
//            System.out.println(Double.SIZE/8);
//            for(int i = 0; i < abData.length; i++)
//            	abData2[i] = abData[i];
//            double[] fft = FFT.magnitudeSpectrum(abData2);
//            double max= 0;
//            int fp = 0;
//
//            for(int i = 0; i < fft.length; i++) {
//            	if(fft[i] > max) {
//            		//max = fft[i];
//            		fp = i;
//            	}
//            		
//            }
//            
//            System.out.println("f: " + fp + " / " + "a: " + fft[fp]);
            
            
            while(nBytesRead != -1) {
            	
            	//DataInputStream dis = new DataInputStream(audioStream);
            	//DataInputStream dis1;
            	//DataOutputStream dos;

                nBytesRead = audioStream.read(abData, 0, abData.length);
               // for(int i=0;i < abData.length ; i++)System.out.println(abData[i]);
                if(nBytesRead > 0) {
                	//Thread.sleep(200);
                	//System.out.println("fdsf");
                    audioLine.write(abData, 0, nBytesRead);
                    //nBytesRead = -1;
                }
            }
           
           
           
           
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
       
       
    }

}
