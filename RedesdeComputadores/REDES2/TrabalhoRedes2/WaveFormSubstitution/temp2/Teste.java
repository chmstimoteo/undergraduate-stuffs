package temp2;

import org.jfree.ui.RefineryUtilities;

import teste.ReadSound;
import chart.DrawChart;
public class Teste {


	public static void main(String[] args) {
		
		

			
			
			ReadSound rs;
			try {
				rs = new ReadSound("voz.wav");
				
				rs.fillBuffer();
				DrawChart chart = new DrawChart("",rs,rs.getAudioFormat());
				chart.pack();
				RefineryUtilities.centerFrameOnScreen(chart);
				chart.setVisible(true);
				System.out.println(rs.getAudioFormat().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*System.out.println(rs.getQuantPacket());
			System.out.println(rs.getRest());
			*/
			/*System.out.println(rs.audioFormat.getSampleRate());*/
			//rs.printBuffer();
			
			
			
			/*byte[] buffer = rs.getBufferFile();
			
	CrossCorrelation cc = new CrossCorrelation(buffer,100,96,4,100,buffer.length,CODE.INT_SIGNED);
	System.out.println(cc.matchingPercentAtPosition(1));
	
		
		} catch (Exception e) {
	
			e.printStackTrace();
		}*/
		
	}
	
	
}
