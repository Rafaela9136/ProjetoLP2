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

	static DefaultCategoryDataset estatQuartoGeral( )
	   {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[6], "Presidencial", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[3], "Luxo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[4], "Luxo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[5], "Luxo Triplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[0], "Executivo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[1], "Executivo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[2], "Executivo Triplo", "");
		return dataset;
	}
	
	static void selecionaGrafico(String text){
		if(text.equals("Servicos adicionais"))
			estatServicosAdicionaisGeral();
	}

	static DefaultCategoryDataset  estatQuartosPorMes() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[6], "Presidencial", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[3], "Luxo Simples", "1980");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[4], "Luxo Duplo", "1990");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[5], "Luxo Triplo", "2000");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[0], "Executivo Simples", "2010");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[1], "Executivo Duplo", "2014");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[2], "Executivo Triplo", "2014");
		
		return dataset;
	}

	static DefaultCategoryDataset estatServicosAdicionaisGeral() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[0], "Baba", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[1], "Automoveis", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[2], "Restaurante", "1970");
		
		return dataset;
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