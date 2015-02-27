package visual;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GeradorDeGrafico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929599302970979854L;

	static DefaultCategoryDataset createDataset( )
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	    	  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[6], "Presidencial" , "");
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[3] , "Luxo Simples" , "" );
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[4] , "Luxo Duplo" ,  "" );
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[5], "Luxo Triplo" , "" );
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[0] , "Executivo Simples" , "" );
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[1] , "Executivo Duplo" , "" );
		      dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[2] , "Executivo Triplo" , "" );
	      return dataset;
	   }

	private static void estatQuartosPorMes(DefaultCategoryDataset dataset) {
		dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[6], "Presidencial" , "1970" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[3] , "Luxo Simples" , "1980" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[4] , "Luxo Duplo" ,  "1990" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[5], "Luxo Triplo" , "2000" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[0] , "Executivo Simples" , "2010" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[1] , "Executivo Duplo" , "2014" );
		  dataset.addValue( LoginJ.getHotel().getEstatisticaGeralQuartos()[2] , "Executivo Triplo" , "2014" );
	}

	private static void estatServicosAdicionais(DefaultCategoryDataset dataset) {
		dataset.addValue( LoginJ.getHotel().getEstatisticaGeralOutrosServicos()[0], "Baba" , "1970" );
		dataset.addValue( LoginJ.getHotel().getEstatisticaGeralOutrosServicos()[1], "Automoveis" , "1970" );
		dataset.addValue( LoginJ.getHotel().getEstatisticaGeralOutrosServicos()[2], "Restaurante" , "1970" );
	}

	static JFreeChart createLineChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D(
				null, // Titulo
				"Servico", // Eixo X
				"Quantidade", // Eixo Y
				dataset, // Dados para o grafico
				PlotOrientation.VERTICAL, // Orientacao do grafico
				true, false, false); // exibir: legendas, tooltips, url
		return chart;
	}
}