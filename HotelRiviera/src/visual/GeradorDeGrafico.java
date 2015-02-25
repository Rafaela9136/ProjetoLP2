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
		dataset.addValue(6, "Baba", "Jan");
		dataset.addValue(7, "Carro", "Jan");
		dataset.addValue(3, "Quarto", "Jan");
		
		dataset.addValue(3, "Baba", "Mar");
		dataset.addValue(5, "Carro", "Mar");
		dataset.addValue(5, "Quarto", "Mar");
		
		dataset.addValue(5, "Baba", "Abr");
		dataset.addValue(9, "Carro", "Abr");
		dataset.addValue(7, "Quarto", "Abr");
		
		dataset.addValue(2, "Baba", "Maio");
		dataset.addValue(9, "Carro", "Maio");
		dataset.addValue(2, "Quarto", "Maio");
		
		dataset.addValue(3, "Baba", "Jun");
		dataset.addValue(7, "Carro", "Jun");
		dataset.addValue(3, "Quarto", "Jun");
		
		dataset.addValue(0, "Baba", "Jul");
		dataset.addValue(9, "Carro", "Jul");
		dataset.addValue(7, "Quarto", "Jul");
		
		dataset.addValue(5, "Baba", "Ags");
		dataset.addValue(3, "Carro", "Ags");
		dataset.addValue(5, "Quarto", "Ags");
		
		dataset.addValue(1, "Baba", "Set");
		dataset.addValue(4, "Carro", "Set");
		dataset.addValue(7, "Quarto", "Set");
		
		dataset.addValue(1, "Baba", "Out");
		dataset.addValue(2, "Carro", "Out");
		dataset.addValue(1, "Quarto", "Out");
		
		dataset.addValue(1, "Baba", "Nov");
		dataset.addValue(1, "Carro", "Nov");
		dataset.addValue(5, "Quarto", "Nov");
		
		dataset.addValue(1, "Baba", "Dez");
		dataset.addValue(8, "Carro", "Dez");
		dataset.addValue(7, "Quarto", "Dez");
		return dataset;
	}

	static JFreeChart createBarChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				null, // Titulo
				"Servico", // Eixo X
				"Quantidade", // Eixo Y
				dataset, // Dados para o grafico
				PlotOrientation.VERTICAL, // Orientacao do grafico
				true, false, false); // exibir: legendas, tooltips, url
		return chart;
	}
}