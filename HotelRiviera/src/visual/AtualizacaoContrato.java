package visual;

import hotel.*;
import excecoes.*;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class AtualizacaoContrato extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6722604404024761631L;
	private final CardLayout layout = new CardLayout();
	
	private JPanel panel;
	private JComboBox<String> comboBoxNota;
	private JTextArea textArea;
	private JTable tabela;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JTextField textFieldLogradouro;
	private JComboBox<String> comboBoxEstados;
	private JComboBox<String> comboBoxPaises;
	private JTextField textFieldCidade;
	private JTextField textFieldNumero;
	private JTextArea textAreaAcompanhantes;
	private JFormattedTextField formattedTextFieldCartao;
	private JFormattedTextField formattedTextFieldCPF;
	private JFormattedTextField formattedTextFieldData;
	private JTextField textFieldNome;
	
	// Variaveis para criar objetos
	private String[] colunas;	
	
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AtualizacaoContrato() throws ParseException {
		inicializa();
		
		barraDeTarefas();
		
		informacoesHospede();
		
		editarServicos();
	}
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
	}

	private void barraDeTarefas() {
		setLayout(null);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 764, 32);
		add(toolBar);
		
		JButton btnEditarDados = new JButton("   Editar dados do hospede titular   ");
		btnEditarDados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarDadosHospede");
			}
		});
		toolBar.add(btnEditarDados);
		
		JButton btnEditarServicos = new JButton("   Adicionar e remover servicos   ");
		btnEditarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarServicos");
				EdicaoServicos.selecionaTela("vazio");
			}
		});
		toolBar.add(btnEditarServicos);
		
		JButton btnFecharContrato = new JButton("   Finalizar contrato   ");
		btnFecharContrato.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFecharContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "fecharContrato");
			}
		});
		toolBar.add(btnFecharContrato);
	}

	private void informacoesHospede() throws ParseException {
		panel = new JPanel();
		panel.setBounds(0, 30, 764, 582);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(layout);
		
		JPanel editarDadosHospede = new JPanel();
		editarDadosHospede.setBackground(Color.WHITE);
		panel.add(editarDadosHospede, "editarDadosHospede");
		editarDadosHospede.setLayout(null);
		
		hospedeDadosPrincipais(editarDadosHospede);
		
		hospedeEndereco(editarDadosHospede);
	}

	private void editarServicos() throws ParseException {
		JPanel editarServicos = new JPanel();
		editarServicos.setBackground(Color.WHITE);
		panel.add(editarServicos, "editarServicos");
		editarServicos.setLayout(null);
		
		JTextPane txtpnServicosDoContrato = new JTextPane();
		txtpnServicosDoContrato.setEditable(false);
		txtpnServicosDoContrato.setText("Servicos do contrato");
		txtpnServicosDoContrato.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnServicosDoContrato.setBounds(28, 24, 172, 32);
		editarServicos.add(txtpnServicosDoContrato);
		
		desenhaTabela(editarServicos);
		
		JButton btnAtualizarTabela = new JButton("Atualizar tabela");
		btnAtualizarTabela.setBounds(28, 273, 135, 25);
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		editarServicos.add(btnAtualizarTabela);
		
		JButton btnCancelarServico = new JButton("Cancelar servico");
		btnCancelarServico.setBounds(598, 273, 135, 25);
		btnCancelarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				Acoes.getContratoPesquisado().removeServico(selecionaServico(linha));
			}
		});
		editarServicos.add(btnCancelarServico);
		
		JTextPane txtpnAdicionarServicos = new JTextPane();
		txtpnAdicionarServicos.setEditable(false);
		txtpnAdicionarServicos.setText("Adicionar servicos");
		txtpnAdicionarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnAdicionarServicos.setBounds(28, 320, 172, 32);
		editarServicos.add(txtpnAdicionarServicos);
		
		JTextPane txtpnTipoDeServico = new JTextPane();
		txtpnTipoDeServico.setEditable(false);
		txtpnTipoDeServico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeServico.setText("Tipo de servico:");
		txtpnTipoDeServico.setBounds(30, 357, 104, 22);
		editarServicos.add(txtpnTipoDeServico);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Baba", "Carro", "Restaurante"}));
		comboBox.setBounds(140, 359, 141, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Baba"))
					EdicaoServicos.selecionaTela("baba");
				if(comboBox.getSelectedItem().equals("Restaurante"))
					EdicaoServicos.selecionaTela("restaurante");
				if(comboBox.getSelectedItem().equals("Carro"))
					EdicaoServicos.selecionaTela("carro");
			}
		});
		editarServicos.add(comboBox);
		
		EdicaoServicos edicaoServico = new EdicaoServicos();
		edicaoServico.setLocation(32, 391);
		editarServicos.add(edicaoServico);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setBounds(598, 547, 135, 25);
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaObjetosServicos();
			}
		});
		editarServicos.add(btnConfirmar_1);
		
		fechaContrato();
	}
	
	private void criaObjetosServicos(){
		AvisoErro erro = new AvisoErro();
		Servico servico = null;
		if(comboBox.getSelectedItem().equals("Baba"))
			servico = EdicaoServicos.contrataBaba();
		if(comboBox.getSelectedItem().equals("Carro"))
			servico = EdicaoServicos.alugaCarro();
		if(comboBox.getSelectedItem().equals("Restaurante"))
			servico = EdicaoServicos.geraContaRestaurante();
		
		try {
			Acoes.getContratoPesquisado().adicionaServico(servico);
			
			AvisoSucesso sucesso = new AvisoSucesso();
			sucesso.setVisible(true);
		} catch (AddQuartoContratoException e) {
			erro.setVisible(true);
		} catch (FrigobarEmListServicosException e) {
			erro.setVisible(true);
		}
		
	}

	private void desenhaTabela(JPanel editarServicos) {
		colunas = new String[]{"Servico","Custo"};
		 
		tabela = new JTable();
		tabela.setRowSelectionAllowed(true);
		model = new DefaultTableModel(new String[0][2] , colunas );
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(28, 60, 705, 200);
		scroll.setViewportView(tabela);
		editarServicos.add(scroll);
	}

	private void atualizaTabela() {
		String[][] dados = new String[25][2];
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			dados[i][0] = Acoes.getContratoPesquisado().getServicos().get(i).getNome();
			dados[i][1] = String.valueOf(Acoes.getContratoPesquisado().getServicos().get(i).getPreco());
		}
		
		model = new DefaultTableModel(dados , colunas );
		tabela.setModel(model);
	}
	
	private Servico selecionaServico(int linha) {
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			if(i == linha){
				return Acoes.getContratoPesquisado().getServicos().get(i);
			}
		}
		return null;
	}

	private void fechaContrato() {
		JPanel fecharContrato = new JPanel();
		fecharContrato.setBackground(Color.WHITE);
		panel.add(fecharContrato, "fecharContrato");
		fecharContrato.setLayout(null);
		
		JTextPane txtpnOpiniaoDoHospede = new JTextPane();
		txtpnOpiniaoDoHospede.setEditable(false);
		txtpnOpiniaoDoHospede.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnOpiniaoDoHospede.setText("Opiniao do hospede");
		txtpnOpiniaoDoHospede.setBounds(28, 24, 172, 32);
		fecharContrato.add(txtpnOpiniaoDoHospede);
		
		JTextPane txtpnComentario = new JTextPane();
		txtpnComentario.setEditable(false);
		txtpnComentario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnComentario.setText("Comentario:");
		txtpnComentario.setBounds(28, 68, 138, 22);
		fecharContrato.add(txtpnComentario);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(UIManager.getColor("CheckBox.background"));
		textArea.setBounds(28, 93, 705, 145);
		fecharContrato.add(textArea);
	
		JTextPane txtpnAvaliacao = new JTextPane();
		txtpnAvaliacao.setEditable(false);
		txtpnAvaliacao.setText("Avaliacao:");
		txtpnAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnAvaliacao.setBounds(526, 254, 81, 22);
		fecharContrato.add(txtpnAvaliacao);
		
		comboBoxNota = new JComboBox<String>();
		comboBoxNota.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxNota.setBounds(610, 254, 121, 22);
		fecharContrato.add(comboBoxNota);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(600, 544, 135, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeContrato();
			}
		});
		fecharContrato.add(btnConfirmar);
	}
	
	private void removeContrato() {
		AvisoErro erro = new AvisoErro();
		try {					
			Acoes.getContratoPesquisado().inicializaOpiniao(Conector.trasformaNota((String) comboBoxNota.getSelectedItem()), textArea.getText());
		} catch (NullPointerException e1) {
			erro.setVisible(true);
		} catch (NotaInvalidaException e1) {
			erro.setVisible(true);
		} catch (EstouroDeCaracteresException e1) {
			erro.setVisible(true);
		} catch (ComentarioVazioException e1) {
			erro.setVisible(true);
		}
		
		try {
			Acoes.getContratoPesquisado().setIsAberto(false);
		} catch (ContratoFechadoException e1) {
			erro.setVisible(true);
		} catch (ContratoSemOpiniaoException e1) {
			erro.setVisible(true);
		}
		
		hotel.Hotel.removeContrato(Acoes.getContratoPesquisado());
		
		AvisoSucesso sucesso = new AvisoSucesso();
		sucesso.setVisible(true);
	}
	
	private void hospedeDadosPrincipais(JPanel panelNovoContrato) throws ParseException {
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		final MaskFormatter cartaoMask = new MaskFormatter("####.####.####.####");
		
		JTextPane txtpnNomeCompleto = new JTextPane();
		txtpnNomeCompleto.setEditable(false);
		txtpnNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNomeCompleto.setText("Nome completo:");
		txtpnNomeCompleto.setBounds(46, 38, 121, 27);
		panelNovoContrato.add(txtpnNomeCompleto);
		
		JTextPane txtpnDataDeNascimento = new JTextPane();
		txtpnDataDeNascimento.setEditable(false);
		txtpnDataDeNascimento.setText("Data de nascimento:");
		txtpnDataDeNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataDeNascimento.setBounds(46, 87, 127, 27);
		panelNovoContrato.add(txtpnDataDeNascimento);
		
		JTextPane txtpnNDoCarto = new JTextPane();
		txtpnNDoCarto.setEditable(false);
		txtpnNDoCarto.setText("N\u00FAmero do cart\u00E3o:");
		txtpnNDoCarto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNDoCarto.setBounds(46, 127, 127, 27);
		panelNovoContrato.add(txtpnNDoCarto);
		
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setEditable(false);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCpf.setBounds(477, 87, 42, 27);
		panelNovoContrato.add(txtpnCpf);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(179, 38, 530, 22);
		panelNovoContrato.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		formattedTextFieldData = new JFormattedTextField(dateMask);
		formattedTextFieldData.setBounds(179, 87, 163, 22);
		panelNovoContrato.add(formattedTextFieldData);
		
		formattedTextFieldCPF = new JFormattedTextField(cpfMask);
		formattedTextFieldCPF.setBounds(537, 87, 172, 22);
		formattedTextFieldCPF.setEnabled(false);
		panelNovoContrato.add(formattedTextFieldCPF);
		
		formattedTextFieldCartao = new JFormattedTextField(cartaoMask);
		formattedTextFieldCartao.setBounds(179, 127, 163, 22);
		panelNovoContrato.add(formattedTextFieldCartao);
		
		JTextPane txtpnAcompanhantes = new JTextPane();
		txtpnAcompanhantes.setEditable(false);
		txtpnAcompanhantes.setText("Acompanhantes:");
		txtpnAcompanhantes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnAcompanhantes.setBounds(46, 276, 127, 27);
		panelNovoContrato.add(txtpnAcompanhantes);
		
		textAreaAcompanhantes = new JTextArea();
		textAreaAcompanhantes.setBackground(UIManager.getColor("CheckBox.background"));
		textAreaAcompanhantes.setLineWrap(true);
		textAreaAcompanhantes.setBounds(179, 274, 530, 53);
		panelNovoContrato.add(textAreaAcompanhantes);
	}

	private void hospedeEndereco(JPanel panelNovoContrato) {
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setEditable(false);
		txtpnNascionalidade.setText("Nascionalidade:");
		txtpnNascionalidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNascionalidade.setBounds(46, 167, 127, 27);
		panelNovoContrato.add(txtpnNascionalidade);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setEditable(false);
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnEstado.setBounds(46, 205, 127, 27);
		panelNovoContrato.add(txtpnEstado);
		
		JTextPane txtpnCidade = new JTextPane();
		txtpnCidade.setEditable(false);
		txtpnCidade.setText("Cidade:");
		txtpnCidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCidade.setBounds(477, 205, 51, 27);
		panelNovoContrato.add(txtpnCidade);
		
		JTextPane txtpnLogradouro = new JTextPane();
		txtpnLogradouro.setEditable(false);
		txtpnLogradouro.setText("Logradouro:");
		txtpnLogradouro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnLogradouro.setBounds(46, 240, 127, 27);
		panelNovoContrato.add(txtpnLogradouro);
		
		JTextPane txtpnNmero = new JTextPane();
		txtpnNmero.setEditable(false);
		txtpnNmero.setText("N\u00FAmero:");
		txtpnNmero.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNmero.setBounds(577, 240, 65, 27);
		panelNovoContrato.add(txtpnNmero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(653, 240, 56, 22);
		textFieldNumero.setEnabled(false);
		panelNovoContrato.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(537, 205, 172, 22);
		textFieldCidade.setEnabled(false);
		panelNovoContrato.add(textFieldCidade);
		textFieldCidade.setColumns(10);
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"Afeganist\u00E3o", "\u00C1frica do Sul", "Akrotiri", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ant\u00EDgua e Barbuda", "Antilhas Neerlandesas", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "Bar\u00E9m", "B\u00E9lgica", "Belize", "Benim", "Bermudas", "Bielorr\u00FAssia", "Birm\u00E2nia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulg\u00E1ria", "Burquina Faso", "Bur\u00FAndi", "But\u00E3o", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Catar", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Col\u00F4mbia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dhekelia", "Dinamarca", "Dom\u00EDnica", "Egipto", "Emiratos \u00C1rabes Unidos", "Equador", "Eritreia", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Faro\u00E9", "Fiji", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Gab\u00E3o", "G\u00E2mbia", "Gana", "Gaza Strip", "Ge\u00F3rgia", "Ge\u00F3rgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Gr\u00E9cia", "Gronel\u00E2ndia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caim\u00E3o", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salom\u00E3o", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Brit\u00E2nicas", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jan Mayen", "Jap\u00E3o", "Jersey", "Jibuti", "Jord\u00E2nia", "Kuwait", "Laos", "Lesoto", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Listenstaine", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Mal\u00E1vi", "Maldivas", "Mali", "Malta", "Man, Isle of", "Marianas do Norte", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "Mayotte", "M\u00E9xico", "Micron\u00E9sia", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Mundo", "Nam\u00EDbia", "Nauru", "Navassa Island", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Niue", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Om\u00E3", "Pacific Ocean", "Pa\u00EDses Baixos", "Palau", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Quirib\u00E1ti", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa L\u00FAcia", "S\u00E3o Crist\u00F3v\u00E3o e Neves", "S\u00E3o Marinho", "S\u00E3o Pedro e Miquelon", "S\u00E3o Tom\u00E9 e Pr\u00EDncipe", "S\u00E3o Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazil\u00E2ndia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Svalbard e Jan Mayen", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Territ\u00F3rio Brit\u00E2nico do Oceano \u00CDndico", "Territ\u00F3rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Tuvalu", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		comboBoxPaises.setBounds(179, 167, 163, 22);
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
		comboBoxEstados.setBounds(179, 207, 163, 22);
		comboBoxEstados.setEnabled(false);
		panelNovoContrato.add(comboBoxEstados);
		
		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setColumns(10);
		textFieldLogradouro.setBounds(179, 240, 386, 22);
		textFieldLogradouro.setEnabled(false);
		panelNovoContrato.add(textFieldLogradouro);
		
	}
	
	

}
