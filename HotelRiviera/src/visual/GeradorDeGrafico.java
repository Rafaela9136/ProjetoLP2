package visual;

import java.awt.CardLayout;

import hotel.IndexOutrosServicos;
import hotel.IndexQuartos;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;

public class GeradorDeGrafico extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5929599302970979854L;
	private static final CardLayout layout = new CardLayout();
	private static JPanel panel;
	private static JPanel panelQuarto;
	private static JPanel panelServicos;
	
	public GeradorDeGrafico() {
		setBounds(47, 156, 658, 371);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 658, 371);
		panel.setLayout(layout);
		add(panel);
		
		graficoQuarto();
		
		
		graficoServicos();
		

	}

	private void graficoServicos() {
		DefaultCategoryDataset dataset = GeradorDeGrafico.estatServicosAdicionaisGeral();
		
		panelServicos = new JPanel();
		panelServicos.setBackground(Color.WHITE);
		panel.add(panelServicos, "servicoGeral");
		
		JFreeChart chart = GeradorDeGrafico.createBarChart(dataset);
		panelServicos.setLayout(null);
		ChartPanel chartPanel = new ChartPanel(chart);
		panelServicos.add(chartPanel);
		chartPanel.setLayout(null);
		chartPanel.setBounds(0, 0, 658, 348);
	}

	private void graficoQuarto() {
		DefaultCategoryDataset dataset = GeradorDeGrafico.estatQuartoGeral();
				
		panelQuarto = new JPanel();
		panelQuarto.setBackground(Color.WHITE);
		panel.add(panelQuarto, "quartoGeral");
		
		JFreeChart chart = GeradorDeGrafico.createBarChart(dataset);
		panelQuarto.setLayout(null);
		ChartPanel chartPanel = new ChartPanel(chart);
		panelQuarto.add(chartPanel);
		chartPanel.setLayout(null);
		chartPanel.setBounds(0, 0, 658, 348);
	}

	private static DefaultCategoryDataset estatQuartoGeral() {
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

	private DefaultCategoryDataset  estatQuartosPorMes() {
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

	private static DefaultCategoryDataset estatServicosAdicionaisGeral() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.BABA.ordinal()], "Baba", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CARRO.ordinal()], "Automoveis", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()], "Restaurante", "1970");
		
		return dataset;
	}

	private static JFreeChart createBarChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D(
				null, // Titulo
				"Servico", // Eixo X
				"Quantidade", // Eixo Y
				dataset, // Dados para o grafico
				PlotOrientation.VERTICAL, // Orientacao do grafico
				true, false, false); // exibir: legendas, tooltips, url
		return chart;
	}
	
	//Seleciona o grafico a ser mostrado
	static void mudaTela(String titulo){
		layout.show(panel, titulo);
	}
}