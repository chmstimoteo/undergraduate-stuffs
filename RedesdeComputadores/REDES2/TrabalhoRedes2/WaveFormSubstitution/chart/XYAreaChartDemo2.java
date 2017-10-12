package chart;

import java.awt.Image;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYAreaChartDemo2 extends ApplicationFrame{
	private TimeSeries series;
	
	 public XYAreaChartDemo2(String title, byte[] channel1, byte[] channel2) {

	        super(title);

	       series = new TimeSeries("Random 1");
	        /*double value = 0.0;*/
	        /*Day day = new Day();
	        for (int i = 0; i < 200; i++) {
	            value = value + Math.random() - 0.5;
	            series.add(day, value);
	            day = (Day) day.next();
	        }*/

	        JFreeChart chart = createChart(createDataset(channel1,channel2));

	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        setContentPane(chartPanel);

	    }

	    // ****************************************************************************
	    // * JFREECHART DEVELOPER GUIDE                                               *
	    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
	    // * to purchase from Object Refinery Limited:                                *
	    // *                                                                          *
	    // * http://www.object-refinery.com/jfreechart/guide.html                     *
	    // *                                                                          *
	    // * Sales are used to provide funding for the JFreeChart project - please    * 
	    // * support us so that we can continue developing free software.             *
	    // ****************************************************************************
	    
	    /**
	     * Creates a chart.
	     * 
	     * @param dataset  the dataset.
	     * 
	     * @return The chart.
	     */
	 
	 private void createSeries(byte[] channel, String name){
			Day day = new Day();
			
			for (double i = 0; i < channel.length; i+=0.01) {
				this.series.addOrUpdate(day,Math.sin(i));
				day = (Day) day.next();
			}
		}
		
		private TimeSeriesCollection createDataset(byte[] channel1, byte[] channel2) {
			
			
				this.createSeries(channel1,"channel1");
				this.createSeries(channel2,"channel2");
					
			return new TimeSeriesCollection(this.series);
						
		}
		
	    private JFreeChart createChart(XYDataset dataset) {
	        JFreeChart chart = ChartFactory.createXYAreaChart(
	            "XY Area Chart Demo 2",
	            "Time", "Value",
	            dataset,
	            PlotOrientation.VERTICAL,
	            true,  // legend
	            true,  // tool tips
	            false  // URLs
	        );
	        XYPlot plot = chart.getXYPlot();

	        ValueAxis domainAxis = new DateAxis("Time");
	        domainAxis.setLowerMargin(0.0);
	        domainAxis.setUpperMargin(0.0);
	        plot.setDomainAxis(domainAxis);
	        plot.setForegroundAlpha(0.5f);  
	      
	        
	        /*XYItemRenderer renderer = plot.getRenderer();
	        renderer.setToolTipGenerator(
	            (XYToolTipGenerator) new StandardXYItemLabelGenerator(
	                "", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("#,##0.00")
	            )
	        );*/
	        return chart;      
	    }
	    
	    /**
	     * Starting point for the demonstration application.
	     *
	     * @param args  ignored.
	     */
	    public static void main(String[] args) {

	    		byte[] c1 = {0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,0};
	    		byte[] c2 = {5,6,4,3,7,8,9,0,3,2,6,4,5,6,7,5,4,5,9,1};
	    		XYAreaChartDemo2 demo = new XYAreaChartDemo2("teste", c1,c2);
	    		demo.pack();
	    		RefineryUtilities.centerFrameOnScreen(demo);
	    		demo.setVisible(true);	    	

	    }


}
