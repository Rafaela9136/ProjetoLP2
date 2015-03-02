package visual;

import hotel.IndexOutrosServicos;
import hotel.IndexQuartos;

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

	static DefaultCategoryDataset estatQuartoGeral() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.SUITE_PRESIDENCIAL.ordinal()], "Presidencial", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_SIMPLES.ordinal()], "Luxo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_DUPLO.ordinal()], "Luxo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_TRIPLO.ordinal()], "Luxo Triplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_SIMPLES.ordinal()], "Executivo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_DUPLO.ordinal()], "Executivo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_TRIPLO.ordinal()], "Executivo Triplo", "");
		return dataset;
	}
	
	static void selecionaGrafico(String text){
		if(text.equals("Servicos adicionais"))
			estatServicosAdicionaisGeral();
	}

	static DefaultCategoryDataset  estatQuartosPorMes() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.SUITE_PRESIDENCIAL.ordinal()], "Presidencial", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_SIMPLES.ordinal()], "Luxo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_DUPLO.ordinal()], "Luxo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.LUXO_TRIPLO.ordinal()], "Luxo Triplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_SIMPLES.ordinal()], "Executivo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_DUPLO.ordinal()], "Executivo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos()[IndexQuartos.EXECUTIVO_TRIPLO.ordinal()], "Executivo Triplo", "");
		
		return dataset;
	}

	static DefaultCategoryDataset estatServicosAdicionaisGeral() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.BABA.ordinal()], "Baba", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CARRO.ordinal()], "Automoveis", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()], "Restaurante", "1970");
		
		return dataset;
	}

	static JFreeChart createBarChart(CategoryDataset dataset) {
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