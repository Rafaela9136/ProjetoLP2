package visual;

import hotel.*;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import excecoes.MesInvalidoException;

public class Info extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5526402980300263893L;
	private static CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private static JComboBox<String> comboBoxMes;
	private static JComboBox<String> comboBoxVisao;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBoxSelecao;
	private static String[] colunas;
	private static JTable tabela;
	private static DefaultTableModel model;
	private static JTextArea textArea;
	private static String[][] dados;
	private static JFreeChart chart;
	private static JPanel estatistica;
	
	/**
	 * Create the panel.
	 */
	public Info() {
		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		inicializa();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 764, 612);
		add(panel);
		panel.setLayout(layout);
		
		panelServicos(panel);
		
		panelOpinioes(panel);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		panelEstatistica();
	}// Construtor
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
	}

	private void panelEstatistica() {
		estatistica = new JPanel();
		estatistica.setBackground(Color.WHITE);
		panel.add(estatistica, "estatistica");
		estatistica.setLayout(null);
		
		JTextPane txtpnEstatisticas = new JTextPane();
		txtpnEstatisticas.setBounds(47, 24, 166, 24);
		txtpnEstatisticas.setText("Estatisticas");
		txtpnEstatisticas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnEstatisticas.setEditable(false);
		estatistica.add(txtpnEstatisticas);
		
		JTextPane txtpnSelecioneOTipo = new JTextPane();
		txtpnSelecioneOTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnSelecioneOTipo.setText("Selecione o tipo de visao que deseja ter:");
		txtpnSelecioneOTipo.setBounds(47, 65, 266, 24);
		estatistica.add(txtpnSelecioneOTipo);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Quartos", "Servicos adicionais"}));
		comboBox.setBounds(129, 98, 144, 24);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setaDataset((String) comboBox.getSelectedItem());
				
			}
		});
		estatistica.add(comboBox);
		
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBoxMes.setBounds(506, 97, 144, 24);
		comboBoxMes.setEnabled(false);
		estatistica.add(comboBoxMes);
		
		comboBoxVisao = new JComboBox<String>();
		comboBoxVisao.setModel(new DefaultComboBoxModel<String>(new String[] {"Geral", "Mensal"}));
		comboBoxVisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxVisao.getSelectedItem().equals("Mensal")) {
					comboBoxMes.setEnabled(true);
					setaDatasetEspecifico((String) comboBox.getSelectedItem(), comboBoxMes.getSelectedIndex() + 1);
					
				} else {
					comboBoxMes.setEnabled(false);
				}
			}
		});
		comboBoxVisao.setBounds(319, 97, 144, 24);
		estatistica.add(comboBoxVisao);
		
		//Cria o grafico
		DefaultCategoryDataset dataset = estatQuartoGeral();

		desenhaGrafico(estatistica, dataset);
	}// painelEstatistica

	private static void desenhaGrafico(JPanel estatistica, DefaultCategoryDataset dataset) {
		chart = createBarChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(47, 154, 674, 341);
		estatistica.add(chartPanel);
	}
	
	private static void setaDataset(String tipo){
		DefaultCategoryDataset dataset;
		if(tipo.equals("Servicos adicionais")){
			dataset = estatServicosAdicionaisGeral();
			desenhaGrafico(estatistica, dataset);
		}
		else{
			dataset = estatQuartoGeral();
			
			desenhaGrafico(estatistica, dataset);
		}
	}
	
	private void setaDatasetEspecifico(String tipo, int mes){
		DefaultCategoryDataset dataset;
		
		if (tipo.equals("Servicos adicionais")) {
			try {
				dataset = estatServicosAdicionaisMensal(mes);
				
				desenhaGrafico(estatistica, dataset);
			} catch (MesInvalidoException e) {
				JOptionPane.showMessageDialog(null, "Algo esta errado!");
			}
		} else{
			try {
				dataset = estatQuartoMensal(mes);
				
				desenhaGrafico(estatistica, dataset);
			} catch (MesInvalidoException e) {
				JOptionPane.showMessageDialog(null, "Algo esta errado!");
			}
		}
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
	
	private static DefaultCategoryDataset estatQuartoMensal(int mes) throws MesInvalidoException  {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.SUITE_PRESIDENCIAL.ordinal()], "Presidencial", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.LUXO_SIMPLES.ordinal()], "Luxo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.LUXO_DUPLO.ordinal()], "Luxo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.LUXO_TRIPLO.ordinal()], "Luxo Triplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.EXECUTIVO_SIMPLES.ordinal()], "Executivo Simples", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.EXECUTIVO_DUPLO.ordinal()], "Executivo Duplo", "");
		dataset.addValue(Main.getHotel().getEstatisticaQuartos(mes)[IndexQuartos.EXECUTIVO_TRIPLO.ordinal()], "Executivo Triplo", "");
		return dataset;
	}
	
	private static DefaultCategoryDataset estatServicosAdicionaisGeral() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.BABA.ordinal()], "Baba", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CARRO.ordinal()], "Automoveis", "1970");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos()[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()], "Restaurante", "1970");
		
		return dataset;
	}
	
	private static DefaultCategoryDataset estatServicosAdicionaisMensal(int mes) throws MesInvalidoException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos(mes)[IndexOutrosServicos.BABA.ordinal()], "Baba", "");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos(mes)[IndexOutrosServicos.CARRO.ordinal()], "Automoveis", "");
		dataset.addValue(Main.getHotel().getEstatisticaOutrosServicos(mes)[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()], "Restaurante", "");
		
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
	
	private void panelOpinioes(JPanel panel) {
		JPanel panelOpinioes = new JPanel();
		panelOpinioes.setBackground(Color.WHITE);
		panel.add(panelOpinioes, "opinioes");
		panelOpinioes.setLayout(null);
		
		JTextPane txtpnOpiniesDosClientes = new JTextPane();
		txtpnOpiniesDosClientes.setBounds(47, 24, 185, 24);
		txtpnOpiniesDosClientes.setText("Opinioes dos hospedes");
		txtpnOpiniesDosClientes.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnOpiniesDosClientes.setEditable(false);
		panelOpinioes.add(txtpnOpiniesDosClientes);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 72, 266, 24);
		textPane.setText("Selecione o tipo de visao que deseja ter: ");
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panelOpinioes.add(textPane);
		
		comboBoxSelecao = new JComboBox<String>();
		comboBoxSelecao.setBounds(314, 74, 144, 22);
		comboBoxSelecao.setModel(new DefaultComboBoxModel<String>(new String[] {"Mais recentes", "Por notas"}));
		comboBoxSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSelecao.getSelectedItem().equals("Por notas"))
					opinioesPorNota();
				else
					opinioesRecentes();
			}
		});
		panelOpinioes.add(comboBoxSelecao);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(47, 122, 672, 464);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(47, 107, 671, 466);
		panelOpinioes.add(scrollPane);
	
	}

	static void opinioesRecentes() {
		String text = "";
		for (Opiniao opiniao :Main.getHotel().getOpinioes()) 
			text += opiniao.toString() + "\n\n";
		
		textArea.setText(text);
	}
	
	private void opinioesPorNota() {
		String text = "";
		for (Opiniao opiniao : Main.getHotel().getOpinioesOrdenadas())
			text += opiniao.toString() + "\n\n";
		
		textArea.setText(text);
	}

	private void panelServicos(JPanel panel) {
		JPanel panelServicos = new JPanel();
		panelServicos.setBackground(Color.WHITE);
		panel.add(panelServicos, "servicos");
		
		tabelaBaba(panelServicos);
		
		tabelaCarro(panelServicos);
		
		JTextPane txtpnInformacoesDosServicos = new JTextPane();
		txtpnInformacoesDosServicos.setEditable(false);
		txtpnInformacoesDosServicos.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnInformacoesDosServicos.setText("Informacoes dos servicos");
		txtpnInformacoesDosServicos.setBounds(47, 24, 209, 31);
		panelServicos.add(txtpnInformacoesDosServicos);
		
		JTextPane txtpnQuartos = new JTextPane();
		txtpnQuartos.setText("Quartos:");
		txtpnQuartos.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtpnQuartos.setEditable(false);
		txtpnQuartos.setBounds(47, 65, 74, 23);
		panelServicos.add(txtpnQuartos);
		
		tabelaQuarto(panelServicos);
		
		JTextPane txtpnBabysytter = new JTextPane();
		txtpnBabysytter.setText("Babysitter:");
		txtpnBabysytter.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtpnBabysytter.setEditable(false);
		txtpnBabysytter.setBounds(47, 278, 74, 23);
		panelServicos.add(txtpnBabysytter);
		
		JTextPane txtpnAluguelDeCarro = new JTextPane();
		txtpnAluguelDeCarro.setText("Aluguel de carro:");
		txtpnAluguelDeCarro.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtpnAluguelDeCarro.setEditable(false);
		txtpnAluguelDeCarro.setBounds(47, 385, 127, 23);
		panelServicos.add(txtpnAluguelDeCarro);
		
		JTextPane txtpnOValor = new JTextPane();
		txtpnOValor.setEditable(false);
		txtpnOValor.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtpnOValor.setText("* Taxa unica. O valor desse servico e adicionado ao montante total a ser pago uma unica vez.");
		txtpnOValor.setBounds(47, 533, 682, 23);
		panelServicos.add(txtpnOValor);
		
		
	}

	private void tabelaQuarto(JPanel panelServicos) {
		colunas = new String[]{"Tipo de quarto","Valor da diaria","Total no hotel", "Total disponivel"};
		dados = new String[][]{
			    {"Presidencial", "R$ " + Double.toString(SuitePresidencial.DIARIA_SUITE_PRESIDENCIAL), Integer.toString(hotel.SuitePresidencial.TOTAL_DISPONIVEL),
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.SUITE_PRESIDENCIAL.ordinal()))},
			    {"Luxo Simples", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_SIMPLES), "5",
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_SIMPLES.ordinal()))},
			    {"Luxo Duplo", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_DUPLO), "15", 
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_DUPLO.ordinal()))},
			    {"Luxo Triplo", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_TRIPLO), "20",
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_TRIPLO.ordinal()))},
			    {"Executivo Simples", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_SIMPLES), "5",
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_SIMPLES.ordinal()))},
			    {"Executivo Duplo", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_DUPLO), "15",
			    	Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_DUPLO.ordinal()))},
			    {"Executivo Triplo", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_TRIPLO), "20",
			    		Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_TRIPLO.ordinal()))},
			};
		
		tabela = new JTable();
		tabela.setEnabled(false);
		model = new DefaultTableModel(dados , colunas);
		panelServicos.setLayout(null);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(47, 91, 682, 180);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}

	static void atualizaServico() {
		dados[0][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.SUITE_PRESIDENCIAL.ordinal()));
		dados[1][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_SIMPLES.ordinal()));
		dados[2][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_DUPLO.ordinal()));
		dados[3][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.LUXO_TRIPLO.ordinal()));
		dados[4][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_SIMPLES.ordinal()));
		dados[5][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_DUPLO.ordinal()));
		dados[6][3] = Integer.toString(Main.getHotel().getQuartosDesocupados(IndexQuartos.EXECUTIVO_TRIPLO.ordinal()));

		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		tabela.setModel(model);
	}

	private void tabelaCarro(JPanel panelServicos) {
		String[] colunas = new String[]{"Aluguel de carro","Valor da diaria"};
		String[][] dados = new String[][]{
			    {"Automovel Luxo", "R$ 100.00"},
			    {"Automovel Executivo", "R$ 60.00"},
			    {"Tanque Cheio*", "R$ " + Double.toString(Carro.VALOR_TANQUE_CHEIO)},
			    {"Seguro*", "R$ " + Double.toString(Carro.VALOR_DO_SEGURO)}
		};
		
		JTable tabela = new JTable();
		tabela.setEnabled(false);
		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		panelServicos.setLayout(null);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(47, 419, 682, 114);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}

	private void tabelaBaba(JPanel panelServicos) {
		String[] colunas = new String[]{"Servico babysitter","Valor"};
		String[][] dados = new String[][]{
				{"Babysitter (hora normal)", "R$ " + Double.toString(Baba.VALOR_HORA)},
			    {"Babysitter (hora dobrada)", "R$ " + Double.toString(Baba.VALOR_HORA_DOBRADA)},
		};
		
		JTable tabela = new JTable();
		tabela.setEnabled(false);
		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		panelServicos.setLayout(null);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(47, 305, 682, 70);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}
	
	//Seleciona a tela a ser mostrada
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}
}
