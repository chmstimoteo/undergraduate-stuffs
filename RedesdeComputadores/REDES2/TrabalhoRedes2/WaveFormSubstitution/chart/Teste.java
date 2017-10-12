package chart;

import java.awt.Color;

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

public class Teste extends ApplicationFrame{
	private static final long serialVersionUID = 1L;
	private XYSeriesCollection dataset;
	
	
	public Teste(String title, byte[] channel1,byte[] channel2 ) {
		
		super(title);
		this.dataset = new XYSeriesCollection();
		XYDataset dataset = createDataset(channel1,channel2);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));
		setContentPane(chartPanel);
		
	}
	
	
	private void createSeries(byte[] channel, String name){
		XYSeries series = new XYSeries(name);
		
		for (double i = 0; i < channel.length; i+=0.01) {
			series.add(i,Math.sin(i));
		}
		
		this.dataset.addSeries(series);
	}
	
	private XYDataset createDataset(byte[] channel1, byte[] channel2) {
		
		
			this.createSeries(channel1,"channel1");
			this.createSeries(channel2,"channel2");
				
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
	
	public static void main(String[] args) {
		byte[] c1 = {0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,0};
		byte[] c2 = {5,6,4,3,7,8,9,0,3,2,6,4,5,6,7,5,4,5,9,1};
		Teste demo = new Teste("teste", c1,c2);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		
	}
	
}
