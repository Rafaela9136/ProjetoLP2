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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import excecoes.AnoInvalidoException;
import excecoes.MesInvalidoException;

import javax.swing.JTextField;
import javax.swing.JButton;

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
	private static String[] colunasFaturamento;
	private static JTable tabela;
	private static JTable tabelaFaturamento;
	private static DefaultTableModel model;
	private static DefaultTableModel modelFaturamento;
	private static JTextArea textArea;
	private static String[][] dados;
	private static String[][] dadosFaturamento;
	private static JPanel estatistica;
	static JTextPane txtpnAMediaDe;
	
	private static JFreeChart chart; 
	//Plot plot; 
	private static ChartPanel chartPanel; 
	private static CategoryDataset dataset;
	private static JTextField textFieldAno;
	
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
		
		panelFaturamento();
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
		txtpnEstatisticas.setText("Estatísticas");
		txtpnEstatisticas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnEstatisticas.setEditable(false);
		estatistica.add(txtpnEstatisticas);
		
		JTextPane txtpnSelecioneOTipo = new JTextPane();
		txtpnSelecioneOTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnSelecioneOTipo.setText("Selecione o tipo de visão que deseja ter:");
		txtpnSelecioneOTipo.setBounds(47, 65, 266, 24);
		estatistica.add(txtpnSelecioneOTipo);
		
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBoxMes.setBounds(506, 97, 144, 24);
		comboBoxMes.setEnabled(false);
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		estatistica.add(comboBoxMes);
		
		comboBoxVisao = new JComboBox<String>();
		comboBoxVisao.setModel(new DefaultComboBoxModel<String>(new String[] {"Geral", "Mensal"}));
		comboBoxVisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxVisao.getSelectedItem().equals("Mensal")) {
					try {
						updateMensal();
					} catch (MesInvalidoException e1) {
						JOptionPane.showMessageDialog(null,"Algo está errado!");
					}
					comboBoxMes.setEnabled(true);					
				} else {
					comboBoxMes.setEnabled(false);
				}
			}
		});
		comboBoxVisao.setBounds(319, 97, 144, 24);
		estatistica.add(comboBoxVisao);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Quartos", "Servicos adicionais"}));
		comboBox.setBounds(129, 98, 144, 24);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		estatistica.add(comboBox);
		
		//Cria o grafico
		DefaultCategoryDataset dataset = estatFaturamentoGeral();

		desenhaGrafico(estatistica, dataset);

	}// painelEstatistica

	private void panelFaturamento() {
		JPanel panelFaturamento = new JPanel();
		panelFaturamento.setBackground(Color.WHITE);
		panel.add(panelFaturamento, "faturamento");
		
		JTextPane txtpnFaturamento = new JTextPane();
		txtpnFaturamento.setBounds(47, 24, 166, 24);
		txtpnFaturamento.setText("Faturamento mensal");
		txtpnFaturamento.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnFaturamento.setEditable(false);
		panelFaturamento.add(txtpnFaturamento);
		
		JTextPane txtpnTipo = new JTextPane();
		txtpnTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipo.setText("Escolha o ano:");
		txtpnTipo.setBounds(47, 65, 105, 24);
		panelFaturamento.add(txtpnTipo);
		
		colunasFaturamento = new String[]{"Mês", "Faturamento" };
		 
		tabelaFaturamento = new JTable();
		tabelaFaturamento.setEnabled(false);
		modelFaturamento = new DefaultTableModel(dadosFaturamento , colunasFaturamento );
		panelFaturamento.setLayout(null);
		tabelaFaturamento.setModel(modelFaturamento);
		JScrollPane scroll = new JScrollPane();
		scroll.setEnabled(false);
		scroll.setBounds(57, 130, 651, 277);
		scroll.setViewportView(tabelaFaturamento);
		panelFaturamento.add(scroll);
		
		textFieldAno = new JTextField();
		textFieldAno.setBounds(164, 65, 134, 21);
		panelFaturamento.add(textFieldAno);
		textFieldAno.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(573, 98, 135, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					atualizaTabelaFaturamento();
				} catch (NumberFormatException | AnoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, "Digite um ano válido!");
				}
			}
		});		
		panelFaturamento.add(btnConfirmar);
	}
	
	static void atualizaTabelaFaturamento() throws NumberFormatException, AnoInvalidoException{
		dadosFaturamento = new String[MesesDoAno.values().length][2];
		
		for (int i = 0; i < MesesDoAno.values().length; i++) {
			dadosFaturamento[i][0] = MesesDoAno.values()[i].getNome();
			dadosFaturamento[i][1] = String.valueOf((Main.getHotel().getFaturamento(Integer.parseInt((textFieldAno.getText())))[i]));
		}
		
		modelFaturamento = new DefaultTableModel(dadosFaturamento, colunasFaturamento);
		tabelaFaturamento.setModel(modelFaturamento);
	}

	private static void desenhaGrafico(JPanel estatistica, DefaultCategoryDataset dataset) {
		chart = createBarChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(47, 154, 674, 341);
		estatistica.add(chartPanel);		
	}

	
	private static void update(){
		if(comboBox.getSelectedItem().equals("Quartos"))
			dataset = estatQuartoGeral();
		if(comboBox.getSelectedItem().equals("Servicos adicionais"))
			dataset = estatServicosAdicionaisGeral();
		else
			dataset = estatFaturamentoGeral();

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDataset(dataset);
    }
	
	private static void updateMensal() throws MesInvalidoException {
		if(comboBox.getSelectedItem().equals("Servicos adicionais")){
			if(comboBoxVisao.getSelectedItem().equals("Mensal")){
				dataset = estatServicosAdicionaisMensal(comboBoxMes.getSelectedIndex() + 1);
			}
		} if(!comboBox.getSelectedItem().equals("Servicos adicionais")){
			if(comboBoxVisao.getSelectedItem().equals("Mensal")){
				dataset = estatQuartoMensal(comboBoxMes.getSelectedIndex() + 1);
			}
		}
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDataset(dataset);
    }

	// Seta informacoes dos graficos
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
	
	private static DefaultCategoryDataset estatFaturamentoGeral() {
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
	//*
	
	private void panelOpinioes(JPanel panel) {
		JPanel panelOpinioes = new JPanel();
		panelOpinioes.setBackground(Color.WHITE);
		panel.add(panelOpinioes, "opinioes");
		panelOpinioes.setLayout(null);
		
		JTextPane txtpnOpiniesDosClientes = new JTextPane();
		txtpnOpiniesDosClientes.setBounds(47, 24, 185, 24);
		txtpnOpiniesDosClientes.setText("Opiniões dos hóspedes");
		txtpnOpiniesDosClientes.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnOpiniesDosClientes.setEditable(false);
		panelOpinioes.add(txtpnOpiniesDosClientes);
		
		JTextPane txtpnSelecioneOTipo_1 = new JTextPane();
		txtpnSelecioneOTipo_1.setBounds(47, 72, 283, 24);
		txtpnSelecioneOTipo_1.setText("Selecione o tipo de visão que deseja ter: ");
		txtpnSelecioneOTipo_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panelOpinioes.add(txtpnSelecioneOTipo_1);
		
		comboBoxSelecao = new JComboBox<String>();
		comboBoxSelecao.setBounds(335, 74, 144, 22);
		comboBoxSelecao.setModel(new DefaultComboBoxModel<String>(new String[] {"Mais recentes", "Por notas"}));
		comboBoxSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMediaOpinioes();
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
		scrollPane.setBounds(47, 107, 671, 447);
		panelOpinioes.add(scrollPane);
		
		txtpnAMediaDe = new JTextPane();
		txtpnAMediaDe.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtpnAMediaDe.setEditable(false);
		txtpnAMediaDe.setBounds(420, 566, 298, 24);
		panelOpinioes.add(txtpnAMediaDe);
	
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
		txtpnInformacoesDosServicos.setText("Informações dos serviços");
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
		txtpnAluguelDeCarro.setBounds(47, 387, 127, 23);
		panelServicos.add(txtpnAluguelDeCarro);
		
		JTextPane txtpnOValor = new JTextPane();
		txtpnOValor.setEditable(false);
		txtpnOValor.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtpnOValor.setText("* Taxa única. O valor desse serviço é adicionado ao montante total a ser pago uma única vez.");
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
		scroll.setBounds(47, 91, 682, 172);
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
		scroll.setBounds(47, 419, 682, 109);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}

	private void tabelaBaba(JPanel panelServicos) {
		String[] colunas = new String[]{"Serviço babysitter","Valor"};
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
		scroll.setBounds(47, 305, 682, 68);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}
	
	//Seleciona a tela a ser mostrada
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}

	static void setMediaOpinioes() {
		txtpnAMediaDe.setText("A média de avaliação atual do hotel é " + Double.toString((Main.getHotel().getMediaOpinioes())) + "!");
	}
}
