package visual;

import javax.swing.JFrame;

import org.jfree.util.Rotation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class GeradorDeGrafico extends JFrame {

  private static final long serialVersionUID = 1L;
  
  public static void main(String[] args) {
	  GeradorDeGrafico grafico = new GeradorDeGrafico("oi");
	  grafico.setVisible(true);
}

  public GeradorDeGrafico(String chartTitle) {
	  // This will create the dataset 
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // add it to our application
        setContentPane(chartPanel);

    }
    
    
/** * Creates a sample dataset */

    private  PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
        return result;
        
    }
    
    
/** * Creates a chart */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        Plot plot = chart.getPlot();
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
} 