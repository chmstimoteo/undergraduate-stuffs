package chart;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import teste.ReadSound;

public class DrawChart extends ApplicationFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XYSeriesCollection dataset;
	private AudioFormat audioFormat;
	
	public DrawChart(String title, ReadSound readSound, AudioFormat audioFormat) {
		
		super(title);
		this.audioFormat = audioFormat;
		this.dataset = new XYSeriesCollection();
		XYDataset dataset = createDataset(readSound);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));
		setContentPane(chartPanel);
		
	}
	
	
	private void createSeries(Vector channel, String name, int qtdeCanal){
		XYSeries series = new XYSeries(name);
		double timeSample = (1/this.audioFormat.getSampleRate())/qtdeCanal;
		int bytesSample = (this.audioFormat.getSampleSizeInBits())/8;
		double timeByte = timeSample/bytesSample;
		
		Iterator it = channel.iterator();
		for (double i = 0;it.hasNext(); i+=timeByte) {
			
			Byte value = (Byte)it.next();
			
			series.add(i,value.doubleValue()/(Math.pow(2, this.audioFormat.getSampleSizeInBits()-1)));
		}
		
		this.dataset.addSeries(series);
	}
	
	
	
	private XYDataset createDataset(ReadSound readSound) {
		
		for (int i = 1; i <=readSound.getNumChannel(); i++) {
			Vector data = readSound.getChannel(i);
			this.createSeries(data,"",readSound.getNumChannel());
			
		}
				
		return this.dataset;
					
	}
	
	
	private JFreeChart createChart(XYDataset dataset) {
		
		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Charts",      			  // chart title
				"byte",                      // x axis label
				"time",                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
		);
		
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);
		return chart;
		
		//		get a reference to the plot for further customisation...
		/* XYPlot plot = (XYPlot) chart.getPlot();
		 plot.setBackgroundPaint(Color.lightGray);
		 plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
		 plot.setDomainGridlinePaint(Color.cyan);
		 plot.setRangeGridlinePaint(Color.cyan);
		 
		 //      NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		  chart.setBackgroundPaint(Color.white);
		  //         get a reference to the plot for further customisation...
		   XYPlot plot1 = (XYPlot) chart.getPlot();
		   plot.setBackgroundPaint(Color.lightGray);
		   plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		   plot.setDomainGridlinePaint(Color.white);
		   plot.setRangeGridlinePaint(Color.white);
		   XYLineAndShapeRenderer renderer
		   = (XYLineAndShapeRenderer) plot.getRenderer();
		   renderer.setShapesVisible(true);
		   renderer.setShapesFilled(true);
		   //         change the auto tick unit selection to integer units only...
		    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());*/
//		OPTIONAL CUSTOMISATION COMPLETED.
		
		/* StandardLegend legend = (StandardLegend) chart.getLegend();
		 legend.setDisplaySeriesShapes(true);
		 
		 // get a reference to the plot for further customisation...
		  XYPlot plot = chart.getXYPlot();
		  plot.setBackgroundPaint(Color.lightGray);
		  plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		  plot.setDomainGridlinePaint(Color.white);
		  plot.setRangeGridlinePaint(Color.white);
		  
		  plot.setDomainCrosshairLockedOnData(true);
		  plot.setDomainCrosshairVisible(true);
		  plot.setRangeCrosshairLockedOnData(true);
		  plot.setRangeCrosshairVisible(true);
		  
		  StandardXYItemRenderer renderer = (StandardXYItemRenderer) plot.getRenderer();
		  renderer.setPlotShapes(true);
		  renderer.setShapesFilled(true);
		  renderer.setItemLabelsVisible(true);
		  ItemLabelPosition p = new ItemLabelPosition(
		  ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, Math.PI / 4
		  );
		  renderer.setPositiveItemLabelPosition(p);
		  
		  // change the auto tick unit selection to integer units only...
		   NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		   rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		   // OPTIONAL CUSTOMISATION COMPLETED.
		    */
		
		
	}
	
	
	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args  ignored.
	 */
	
	/*public static void main(String[] args) {
		
		DrawChart demo = new DrawChart("Line Chart Demo 2", );
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		
	}*/
	
	
}

