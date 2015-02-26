package visual;

import hotel.*;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import java.awt.Font;

public class Info extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5526402980300263893L;
	private static CardLayout layout = new CardLayout();
	private static JPanel panel;

	/**
	 * Create the panel.
	 */
	public Info() {
		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		inicializa();
	}
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 764, 612);
		add(panel);
		panel.setLayout(layout);
		
		panelHotel(panel);
		
		panelServicos(panel);
		
		panelOpinioes(panel);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		panelEstatistica();
	}

	private void panelEstatistica() {
		JPanel estatistica = new JPanel();
		estatistica.setBackground(Color.WHITE);
		panel.add(estatistica, "estatistica");
		estatistica.setLayout(null);
		
		JTextPane txtpnEstatisticas = new JTextPane();
		txtpnEstatisticas.setBounds(47, 24, 166, 24);
		txtpnEstatisticas.setText("Estatisticas");
		txtpnEstatisticas.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnEstatisticas.setEditable(false);
		estatistica.add(txtpnEstatisticas);
		
		criaGrafico(estatistica);
		
	}

	private void criaGrafico(JPanel estatistica) {
		CategoryDataset dataset = GeradorDeGrafico.createDataset();
		JFreeChart chart = GeradorDeGrafico.createBarChart(dataset);
		ChartPanel panel = new ChartPanel(chart);
		panel.setBounds(47, 287, 680, 296);
		estatistica.add(panel);
	}

	private void panelOpinioes(JPanel panel) {
		JPanel panelOpinioes = new JPanel();
		panelOpinioes.setBackground(Color.WHITE);
		panel.add(panelOpinioes, "opinioes");
		panelOpinioes.setLayout(null);
		
		JTextPane txtpnOpiniesDosClientes = new JTextPane();
		txtpnOpiniesDosClientes.setText("Opinioes dos hospedes");
		txtpnOpiniesDosClientes.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnOpiniesDosClientes.setEditable(false);
		txtpnOpiniesDosClientes.setBounds(47, 24, 185, 24);
		panelOpinioes.add(txtpnOpiniesDosClientes);
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
		String[] colunas = new String[]{"Tipo de quarto","Valor da diaria","Total no hotel", "Total disponivel"};
		String[][] dados = new String[][]{
		    {"Presidencial", "R$ " + Double.toString(SuitePresidencial.DIARIA_SUITE_PRESIDENCIAL), Integer.toString(hotel.SuitePresidencial.TOTAL_DISPONIVEL),
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.SUITE_PRESIDENCIAL.ordinal()))},
		    {"Luxo Simples", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_SIMPLES), "5",
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.LUXO_SIMPLES.ordinal()))},
		    {"Luxo Duplo", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_DUPLO), "15", 
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.LUXO_DUPLO.ordinal()))},
		    {"Luxo Triplo", "R$ " + Double.toString(QuartoLuxo.DIARIA_LUXO_TRIPLO), "20",
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.LUXO_TRIPLO.ordinal()))},
		    {"Executivo Simples", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_SIMPLES), "5",
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.EXECUTIVO_SIMPLES.ordinal()))},
		    {"Executivo Duplo", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_DUPLO), "15",
		    	Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.EXECUTIVO_DUPLO.ordinal()))},
		    {"Executivo Triplo", "R$ " + Double.toString(QuartoExecutivo.DIARIA_EXECUTIVO_TRIPLO), "20",
		    		Integer.toString(LoginJ.getHotel().getQuartosDesocupados(IndexQuartosDesocupados.EXECUTIVO_TRIPLO.ordinal()))},
		};
		
		JTable tabela = new JTable();
		tabela.setEnabled(false);
		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		panelServicos.setLayout(null);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(47, 91, 682, 172);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
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
		scroll.setBounds(47, 419, 682, 110);
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
		scroll.setBounds(47, 305, 682, 67);
		scroll.setViewportView(tabela);
		panelServicos.add(scroll);
	}

	private void panelHotel(JPanel panel) {
		JPanel panelHotel = new JPanel();
		panelHotel.setBackground(Color.WHITE);
		panel.add(panelHotel, "estabelecimento");
		panelHotel.setLayout(null);
		
		JTextPane txtpnHotelRivieraCampina = new JTextPane();
		txtpnHotelRivieraCampina.setText("Hotel Riviera Campina");
		txtpnHotelRivieraCampina.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtpnHotelRivieraCampina.setEditable(false);
		txtpnHotelRivieraCampina.setBounds(47, 24, 185, 24);
		panelHotel.add(txtpnHotelRivieraCampina);
	}
	
	//Seleciona a tela a ser mostrada
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}
}
