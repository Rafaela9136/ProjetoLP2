package visual;

import hotel.*;
import excecoes.*;

import java.awt.Color;

import javax.swing.JPanel;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;

public class Acoes extends JPanel {

	/**
	 */
	private static final long serialVersionUID = -2953581130945541427L;
	private static CardLayout layout = new CardLayout();
	private static JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldNumero;
	private JTextField textFieldCidade;
	private JTextField textFieldLogradouro;
	private JFormattedTextField formattedTextFieldData;
	private JFormattedTextField formattedTextFieldCPF;
	private JFormattedTextField formattedTextFieldCartao;
	private JFormattedTextField formattedTextFieldCheckIn;
	private JFormattedTextField formattedTextFieldCheckOut;
	private JComboBox<String> comboBoxQuarto;
	private JComboBox<String> comboBoxQuartoT;
	private JComboBox<String> comboBoxPaises;
	private JComboBox<String> comboBoxEstados;
	private JRadioButton rdbtnCamaExtra;
	private JTextArea textAreaAcompanhantes;
	private JTextField textFieldPesquisa;
	private DefaultTableModel model;
	private JTable tabela;
	
	// Variaveis para criacao do contrato
	private List<String> acompanhantes;
	private Quarto quarto;
	private Hospede hospede;
	private String[][] dados;
	private String[] colunas;
	private static Contrato contratoPesquisado;
	

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public Acoes() throws ParseException {
		inicializa();
		
		// Cria o painel base, que sera o container para os outros paineis
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 764, 612);
		add(panel);
		panel.setLayout(layout);
		
		// seta um painel limpo
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		novoContrato();
		
		pesquisaContrato();
		
		// Cria painel de atualizacao do contrato
		AtualizacaoContrato atualizaContrato = new AtualizacaoContrato();
		panel.add(atualizaContrato, "atualizaContrato");
	}

	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
	}

	private void novoContrato() throws ParseException {
		JPanel panelNovoContrato = new JPanel();
		panelNovoContrato.setBackground(Color.WHITE);
		panel.add(panelNovoContrato, "novoContrato");
		panelNovoContrato.setLayout(null);
		
		// Dados dos hospedes
		JTextPane txtpnDadosDoHspede = new JTextPane();
		txtpnDadosDoHspede.setEditable(false);
		txtpnDadosDoHspede.setText("Dados do h\u00F3spede");
		txtpnDadosDoHspede.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnDadosDoHspede.setBounds(47, 24, 148, 27);
		panelNovoContrato.add(txtpnDadosDoHspede);
		
		hospedeDadosPrincipais(panelNovoContrato);
		
		hospedeEndereco(panelNovoContrato);
		
		dadosQuarto(panelNovoContrato);
		
		confirmaECriaObjetos(panelNovoContrato);
	}
	
	private void dadosQuarto(JPanel panelNovoContrato) throws ParseException {
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		
		JTextPane txtpnDadosDoQuarto = new JTextPane();
		txtpnDadosDoQuarto.setEditable(false);
		txtpnDadosDoQuarto.setText("Dados do quarto");
		txtpnDadosDoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnDadosDoQuarto.setBounds(47, 371, 148, 27);
		panelNovoContrato.add(txtpnDadosDoQuarto);
		
		JTextPane txtpnTipoDeQuarto = new JTextPane();
		txtpnTipoDeQuarto.setEditable(false);
		txtpnTipoDeQuarto.setText("Tipo de quarto:");
		txtpnTipoDeQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeQuarto.setBounds(47, 410, 121, 27);
		panelNovoContrato.add(txtpnTipoDeQuarto);
		
		comboBoxQuarto = new JComboBox<String>();
		comboBoxQuarto.setModel(new DefaultComboBoxModel<String>(new String[] {"Executivo", "Luxo", "Presidencial"}));
		comboBoxQuarto.setBounds(180, 410, 163, 22);
		comboBoxQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxQuarto.getSelectedItem().equals("Presidencial")){
					comboBoxQuartoT.setEnabled(false);
					rdbtnCamaExtra.setEnabled(false);
				} else{
					comboBoxQuartoT.setEnabled(true);
					rdbtnCamaExtra.setEnabled(true);
				} // else
			}
		});
		panelNovoContrato.add(comboBoxQuarto);
		
		comboBoxQuartoT = new JComboBox<String>();
		comboBoxQuartoT.setModel(new DefaultComboBoxModel<String>(new String[] {"Simples", "Duplo", "Triplo"}));
		comboBoxQuartoT.setBounds(378, 410, 163, 22);
		comboBoxQuartoT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxQuartoT.getSelectedItem().equals("Triplo")){
					rdbtnCamaExtra.setEnabled(false);
				} else{
					rdbtnCamaExtra.setEnabled(true);
				} // else
			}
		});
		panelNovoContrato.add(comboBoxQuartoT);
		
		rdbtnCamaExtra = new JRadioButton("Cama extra");
		rdbtnCamaExtra.setBounds(182, 445, 121, 24);
		panelNovoContrato.add(rdbtnCamaExtra);
		
		JTextPane txtpnDataCheckin = new JTextPane();
		txtpnDataCheckin.setEditable(false);
		txtpnDataCheckin.setText("Data checkin:");
		txtpnDataCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataCheckin.setBounds(47, 486, 121, 27);
		panelNovoContrato.add(txtpnDataCheckin);
		
		JTextPane txtpnDataCheckout = new JTextPane();
		txtpnDataCheckout.setEditable(false);
		txtpnDataCheckout.setText("Data checkout:");
		txtpnDataCheckout.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataCheckout.setBounds(47, 524, 121, 27);
		panelNovoContrato.add(txtpnDataCheckout);
		
		formattedTextFieldCheckIn = new JFormattedTextField(dateMask);
		formattedTextFieldCheckIn.setBounds(180, 486, 163, 22);
		panelNovoContrato.add(formattedTextFieldCheckIn);
		
		formattedTextFieldCheckOut = new JFormattedTextField(dateMask);
		formattedTextFieldCheckOut.setBounds(180, 524, 163, 22);
		panelNovoContrato.add(formattedTextFieldCheckOut);
	}
	
	private void criaQuarto(List<Servico> servicos) {
		
		AvisoErro erro = new AvisoErro();
		if(comboBoxQuarto.getSelectedItem().equals("Presidencial")){
			quarto = new SuitePresidencial();
			servicos.add(quarto);
		} if(comboBoxQuarto.getSelectedItem().equals("Executivo")){
			try {
				quarto = new QuartoExecutivo(rdbtnCamaExtra.isSelected(), Conector.selecionaTipoQuarto(comboBoxQuartoT.getSelectedItem()));
				servicos.add(quarto);
			} catch (NullPointerException e1) {
				erro.setVisible(true);
			} catch (CamaExtraException e1) {
				erro.setVisible(true);
			}
		} if(comboBoxQuarto.getSelectedItem().equals("Luxo")){
			try {
				quarto = new QuartoLuxo(rdbtnCamaExtra.isSelected(), Conector.selecionaTipoQuarto(comboBoxQuartoT.getSelectedItem()));
				servicos.add(quarto);
			} catch (NullPointerException e1) {
				erro.setVisible(true);
			} catch (CamaExtraException e1) {
				erro.setVisible(true);
			}
		}
	}

	// Cria o quarto, o hospede e o contrato
	private void confirmaECriaObjetos(JPanel panelNovoContrato) {
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(600, 574, 135, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();
				List<Servico> servicos = new ArrayList<Servico>();
				
				criaQuarto(servicos);
				criaHospede();
				
				try {
					Contrato contrato = new Contrato(hospede, acompanhantes, Conector.transformaData(formattedTextFieldCheckIn.getText()),
							Conector.transformaData(formattedTextFieldCheckOut.getText()), servicos);
					
					hotel.Hotel.adicionaContrato(contrato);
					
					
					AvisoSucesso sucesso = new AvisoSucesso();
					sucesso.setVisible(true);
					
					layout.show(panel, "vazio");
					limpaCampos();
				} catch (NullPointerException e1) {
					erro.setVisible(true);
				} catch (ContratoSemQuartoException e1) {
					erro.setVisible(true);
				} catch (FrigobarEmListServicosException e1) {
					erro.setVisible(true);
				} catch (DataInvalidaException e1) {
					erro.setVisible(true);
				}
			}
		});
		panelNovoContrato.add(btnConfirmar);
	}
	
	private void criaHospede() {
		AvisoErro erro = new AvisoErro();
		try {
			hospede = new Hospede(textFieldNome.getText(), Conector.transformaData(formattedTextFieldData.getText()), (String) comboBoxPaises.getSelectedItem(),
					Conector.selecionaEstado((String) comboBoxEstados.getSelectedItem()), textFieldCidade.getText(), textFieldLogradouro.getText(), textFieldNumero.getText(),
					formattedTextFieldCPF.getText(), formattedTextFieldCartao.getText());
		} catch (NullPointerException e1) {
			erro.setVisible(true);
		} catch (CPFInvalidoException e1) {
			erro.setVisible(true);
		} catch (DataInvalidaException e1) {
			erro.setVisible(true);
		} catch (StringVaziaException e1) {
			erro.setVisible(true);
		} catch (CartaoInvalidoException e1) {
			erro.setVisible(true);
		} catch (StringInvalidaException e1) {
			erro.setVisible(true);
		} catch (NumeroInvalidoException e1) {
			erro.setVisible(true);
		}
	}//**

	private void hospedeDadosPrincipais(JPanel panelNovoContrato) throws ParseException {
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		final MaskFormatter cartaoMask = new MaskFormatter("####.####.####.####");
		
		JTextPane txtpnNomeCompleto = new JTextPane();
		txtpnNomeCompleto.setEditable(false);
		txtpnNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNomeCompleto.setText("Nome completo:");
		txtpnNomeCompleto.setBounds(47, 77, 121, 27);
		panelNovoContrato.add(txtpnNomeCompleto);
		
		JTextPane txtpnDataDeNascimento = new JTextPane();
		txtpnDataDeNascimento.setEditable(false);
		txtpnDataDeNascimento.setText("Data de nascimento:");
		txtpnDataDeNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataDeNascimento.setBounds(47, 126, 127, 27);
		panelNovoContrato.add(txtpnDataDeNascimento);
		
		JTextPane txtpnNDoCarto = new JTextPane();
		txtpnNDoCarto.setEditable(false);
		txtpnNDoCarto.setText("N\u00FAmero do cart\u00E3o:");
		txtpnNDoCarto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNDoCarto.setBounds(47, 166, 127, 27);
		panelNovoContrato.add(txtpnNDoCarto);
		
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setEditable(false);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCpf.setBounds(478, 126, 42, 27);
		panelNovoContrato.add(txtpnCpf);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(180, 77, 530, 22);
		panelNovoContrato.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		formattedTextFieldData = new JFormattedTextField(dateMask);
		formattedTextFieldData.setBounds(180, 126, 163, 22);
		panelNovoContrato.add(formattedTextFieldData);
		
		formattedTextFieldCPF = new JFormattedTextField(cpfMask);
		formattedTextFieldCPF.setBounds(538, 126, 172, 22);
		formattedTextFieldCPF.setEnabled(false);
		panelNovoContrato.add(formattedTextFieldCPF);
		
		formattedTextFieldCartao = new JFormattedTextField(cartaoMask);
		formattedTextFieldCartao.setBounds(180, 166, 163, 22);
		panelNovoContrato.add(formattedTextFieldCartao);
		
		JTextPane txtpnAcompanhantes = new JTextPane();
		txtpnAcompanhantes.setEditable(false);
		txtpnAcompanhantes.setText("Acompanhantes:");
		txtpnAcompanhantes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnAcompanhantes.setBounds(47, 315, 127, 27);
		panelNovoContrato.add(txtpnAcompanhantes);
		
		textAreaAcompanhantes = new JTextArea();
		textAreaAcompanhantes.setBackground(UIManager.getColor("CheckBox.background"));
		textAreaAcompanhantes.setLineWrap(true);
		textAreaAcompanhantes.setBounds(180, 313, 530, 53);
		panelNovoContrato.add(textAreaAcompanhantes);
		acompanhantes = Conector.transformaVetor(textAreaAcompanhantes.getText().split(","));
	}

	private void hospedeEndereco(JPanel panelNovoContrato) {
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setEditable(false);
		txtpnNascionalidade.setText("Nascionalidade:");
		txtpnNascionalidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNascionalidade.setBounds(47, 206, 127, 27);
		panelNovoContrato.add(txtpnNascionalidade);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setEditable(false);
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnEstado.setBounds(47, 244, 127, 27);
		panelNovoContrato.add(txtpnEstado);
		
		JTextPane txtpnCidade = new JTextPane();
		txtpnCidade.setEditable(false);
		txtpnCidade.setText("Cidade:");
		txtpnCidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCidade.setBounds(478, 244, 51, 27);
		panelNovoContrato.add(txtpnCidade);
		
		JTextPane txtpnLogradouro = new JTextPane();
		txtpnLogradouro.setEditable(false);
		txtpnLogradouro.setText("Logradouro:");
		txtpnLogradouro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnLogradouro.setBounds(47, 279, 127, 27);
		panelNovoContrato.add(txtpnLogradouro);
		
		JTextPane txtpnNmero = new JTextPane();
		txtpnNmero.setEditable(false);
		txtpnNmero.setText("N\u00FAmero:");
		txtpnNmero.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNmero.setBounds(578, 279, 65, 27);
		panelNovoContrato.add(txtpnNmero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(654, 279, 56, 22);
		textFieldNumero.setEnabled(false);
		panelNovoContrato.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(538, 244, 172, 22);
		textFieldCidade.setEnabled(false);
		panelNovoContrato.add(textFieldCidade);
		textFieldCidade.setColumns(10);
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"Afeganist\u00E3o", "\u00C1frica do Sul", "Akrotiri", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ant\u00EDgua e Barbuda", "Antilhas Neerlandesas", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "Bar\u00E9m", "B\u00E9lgica", "Belize", "Benim", "Bermudas", "Bielorr\u00FAssia", "Birm\u00E2nia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulg\u00E1ria", "Burquina Faso", "Bur\u00FAndi", "But\u00E3o", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Catar", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Col\u00F4mbia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dhekelia", "Dinamarca", "Dom\u00EDnica", "Egipto", "Emiratos \u00C1rabes Unidos", "Equador", "Eritreia", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Faro\u00E9", "Fiji", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Gab\u00E3o", "G\u00E2mbia", "Gana", "Gaza Strip", "Ge\u00F3rgia", "Ge\u00F3rgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Gr\u00E9cia", "Gronel\u00E2ndia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caim\u00E3o", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salom\u00E3o", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Brit\u00E2nicas", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jan Mayen", "Jap\u00E3o", "Jersey", "Jibuti", "Jord\u00E2nia", "Kuwait", "Laos", "Lesoto", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Listenstaine", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Mal\u00E1vi", "Maldivas", "Mali", "Malta", "Man, Isle of", "Marianas do Norte", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "Mayotte", "M\u00E9xico", "Micron\u00E9sia", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Mundo", "Nam\u00EDbia", "Nauru", "Navassa Island", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Niue", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Om\u00E3", "Pacific Ocean", "Pa\u00EDses Baixos", "Palau", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Quirib\u00E1ti", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa L\u00FAcia", "S\u00E3o Crist\u00F3v\u00E3o e Neves", "S\u00E3o Marinho", "S\u00E3o Pedro e Miquelon", "S\u00E3o Tom\u00E9 e Pr\u00EDncipe", "S\u00E3o Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazil\u00E2ndia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Svalbard e Jan Mayen", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Territ\u00F3rio Brit\u00E2nico do Oceano \u00CDndico", "Territ\u00F3rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Tuvalu", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		comboBoxPaises.setBounds(180, 206, 163, 22);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxPaises.getSelectedItem().equals("Brasil")){
					formattedTextFieldCPF.setEnabled(true);
					comboBoxEstados.setEnabled(true);
					textFieldCidade.setEnabled(true);
					textFieldLogradouro.setEnabled(true);
					textFieldNumero.setEnabled(true);
				}else{
					formattedTextFieldCPF.setEnabled(false);
					comboBoxEstados.setEnabled(false);
					textFieldCidade.setEnabled(false);
					textFieldLogradouro.setEnabled(false);
					textFieldNumero.setEnabled(false);
				}// else
			}
		}); // ActionListener
		panelNovoContrato.add(comboBoxPaises);
		
		comboBoxEstados = new JComboBox<String>();
		comboBoxEstados.setModel(new DefaultComboBoxModel<String>(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxEstados.setBounds(180, 246, 163, 22);
		comboBoxEstados.setEnabled(false);
		panelNovoContrato.add(comboBoxEstados);
		
		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setColumns(10);
		textFieldLogradouro.setBounds(180, 279, 386, 22);
		textFieldLogradouro.setEnabled(false);
		panelNovoContrato.add(textFieldLogradouro);
		
	}
	
	private void pesquisaContrato() {
		JPanel panelPesquisarContrato = new JPanel();
		panelPesquisarContrato.setBackground(Color.WHITE);
		panel.add(panelPesquisarContrato, "pesquisaContrato");
		panelPesquisarContrato.setLayout(null);
		
		JTextPane txtpnPesquisaDeContratos = new JTextPane();
		txtpnPesquisaDeContratos.setText("Pesquisa de contratos");
		txtpnPesquisaDeContratos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnPesquisaDeContratos.setEditable(false);
		txtpnPesquisaDeContratos.setBounds(47, 24, 170, 27);
		panelPesquisarContrato.add(txtpnPesquisaDeContratos);
		
		JTextPane txtpnHspede = new JTextPane();
		txtpnHspede.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnHspede.setText("H\u00F3spede:");
		txtpnHspede.setBounds(47, 77, 83, 27);
		panelPesquisarContrato.add(txtpnHspede);
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(136, 80, 599, 22);
		panelPesquisarContrato.add(textFieldPesquisa);
		
		JButton btnAtualizarTabela = new JButton("Atualizar tabela");
		btnAtualizarTabela.setBounds(47, 115, 135, 25);
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		panelPesquisarContrato.add(btnAtualizarTabela);
		
		JButton btnPesquisarContrato = new JButton("Pesquisar");
		btnPesquisarContrato.setBounds(600, 113, 135, 25);
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisaTabela();
			}
		});
		panelPesquisarContrato.add(btnPesquisarContrato);
		
		geraTabela(panelPesquisarContrato);
		
		JButton btnAtualizarContrato = new JButton("Atualizar contrato\r\n");
		btnAtualizarContrato.setBounds(600, 562, 135, 25);
		btnAtualizarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				if(selecionaContrato(linha))
					layout.show(panel, "atualizaContrato");
			}
		});
		panelPesquisarContrato.add(btnAtualizarContrato);	
	}
	
	// ***ERRO*** Corrigir o erro de pesquisa substituindo ou usando dados.letgh pra pegar a informacao correta
	private boolean selecionaContrato(int linha){
		for (int i = 0; i < hotel.Hotel.getContratos().size(); i++) {
			if(i == linha){
				contratoPesquisado = hotel.Hotel.getContratos().get(i);
				return true;
			}
		}
		return false;
	}
	
	static Contrato getContratoPesquisado(){
		return contratoPesquisado;
	}

	private void geraTabela(JPanel panelPesquisarContrato) {
		colunas = new String[]{"Nome do hospede","Situacao do contrato"};
		dados = new String[85][2];
		
		tabela = new JTable();
		tabela.setRowSelectionAllowed(true);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(50, 150, 685, 389);
		scroll.setViewportView(tabela);
		panelPesquisarContrato.add(scroll);
	}
	
	private void pesquisaTabela(){
		String[][] dados = new String[85][2];
		int cont = 0;
		for (int i = 0; i < hotel.Hotel.getContratos().size(); i++) {
			if(hotel.Hotel.getContratos().get(i).getHospedeTitular().getNome().equals(textFieldPesquisa.getText())){
				dados[cont][0] = hotel.Hotel.getContratos().get(i).getHospedeTitular().getNome();
				if (hotel.Hotel.getContratos().get(i).getIsAberto())
					dados[cont][1] = "Aberto";
				if (hotel.Hotel.getContratos().get(i).getIsReserva())
					dados[cont][1] = "Reserva";
				cont++;
			} //if
		}//for
		
		if(cont == 0) {
			AvisoNaoEncontrado aviso = new AvisoNaoEncontrado();
			aviso.setVisible(true);
		}
		
		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);		
	}
	
	private void atualizaTabela() {
		for (int i = 0; i < hotel.Hotel.getContratos().size(); i++) {
			dados[i][0] = hotel.Hotel.getContratos().get(i).getHospedeTitular().getNome();
			if (hotel.Hotel.getContratos().get(i).getIsAberto())
				dados[i][1] = "Aberto";
			if (hotel.Hotel.getContratos().get(i).getIsReserva())
				dados[i][1] = "Reserva";
		}//for
		
		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);
	}

	public void limpaCampos(){
		textFieldNome.setText("");
		textFieldNumero.setText("");
		textFieldCidade.setText("");
		textFieldLogradouro.setText("");
		formattedTextFieldData.setText("");
		formattedTextFieldCPF.setText("");
		formattedTextFieldCartao.setText("");
		formattedTextFieldCheckIn.setText("");
		formattedTextFieldCheckOut.setText("");
		comboBoxQuarto.setSelectedIndex(0);
		comboBoxQuartoT.setSelectedIndex(0);
		comboBoxPaises.setSelectedIndex(0);
		comboBoxEstados.setSelectedIndex(0);
		rdbtnCamaExtra.setSelected(false);
		textAreaAcompanhantes.setText("");;
		
	}
	
	// metodo para selecionar a tela a que aparece
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}
}
