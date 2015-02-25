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

	static CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(6, "Preto", "Corsa");
		dataset.addValue(4, "Preto", "Fiesta");
		dataset.addValue(3, "Preto", "Gol");
		dataset.addValue(5, "Vermelho", "Corsa");
		dataset.addValue(2, "Vermelho", "Fiesta");
		dataset.addValue(3, "Vermelho", "Gol");
		dataset.addValue(2, "Azul", "Corsa");
		dataset.addValue(8, "Azul", "Fiesta");
		dataset.addValue(1, "Azul", "Gol");
		return dataset;
	}

	static JFreeChart createBarChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				"Escolha de cor por veículo", // Titulo
				"Veículo", // Eixo X
				"Quantidade", // Eixo Y
				dataset, // Dados para o grafico
				PlotOrientation.VERTICAL, // Orientacao do grafico
				true, false, false); // exibir: legendas, tooltips, url
		return chart;
	}
}