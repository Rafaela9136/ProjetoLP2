package hotel;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.CardLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class HotelJ extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9029353019037585688L;
	private final JComboBox<String> comboBoxPaises, comboBoxQuarto, comboBoxQuartoQ ;
	private final JPanel acoes1, acoes2, acoes3, acoes4;
	private final JCheckBox chckbxCamaExtra;
	
	private JPanel hotel;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldData, formattedTextFieldCPF;
	private JTextField textFieldEstado;
	private JTextField textFieldCidade;
	private JTextField textFieldEndereco;
	private JTextField textFieldNumero;
	private CardLayout layout = new CardLayout();
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public HotelJ() throws ParseException {
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		
		setResizable(false);
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 660);
		hotel = new JPanel();
		hotel.setBackground(SystemColor.window);
		hotel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(hotel);
		hotel.setLayout(null);
		
		JTabbedPane menuAbas = new JTabbedPane(JTabbedPane.TOP);
		menuAbas.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuAbas.setForeground(UIManager.getColor("nimbusBase"));
		menuAbas.setBackground(UIManager.getColor("nimbusBase"));
		menuAbas.setBounds(0, 0, 859, 631);
		hotel.add(menuAbas);
		
		JPanel contratos = new JPanel();
		contratos.setBackground(SystemColor.window);
		menuAbas.addTab("Contratos", null, contratos, null);
		contratos.setLayout(null);
		
		acoes1 = new JPanel();
		acoes1.setBackground(SystemColor.window);
		acoes1.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes1.setBounds(227, 12, 607, 573);
		contratos.add(acoes1);
		acoes1.setLayout(layout);
		
		JPanel novoContrato = new JPanel();
		novoContrato.setBackground(SystemColor.window);
		acoes1.add(novoContrato, "novoContrato");
		novoContrato.setLayout(null);
		
		JTextPane txtpnDadosDoHspede = new JTextPane();
		txtpnDadosDoHspede.setBounds(21, 31, 192, 36);
		txtpnDadosDoHspede.setEditable(false);
		txtpnDadosDoHspede.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDadosDoHspede.setText("Dados do h\u00F3spede");
		novoContrato.add(txtpnDadosDoHspede);
		
		JTextPane txtpnNome = new JTextPane();
		txtpnNome.setBounds(32, 76, 54, 28);
		txtpnNome.setEditable(false);
		txtpnNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNome.setText("Nome:");
		novoContrato.add(txtpnNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(147, 74, 420, 28);
		textFieldNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		novoContrato.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JTextPane txtpnNascimento = new JTextPane();
		txtpnNascimento.setBounds(32, 110, 85, 28);
		txtpnNascimento.setText("Nascimento:");
		txtpnNascimento.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascimento.setEditable(false);
		novoContrato.add(txtpnNascimento);
		
		dateMask.setPlaceholderCharacter(' ');
		textFieldData = new JFormattedTextField(dateMask);
		textFieldData.setBounds(147, 110, 117, 28);
		textFieldData.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldData.setColumns(10);
		novoContrato.add(textFieldData);
		
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setBounds(32, 142, 85, 28);
		txtpnNascionalidade.setText("Pa\u00EDs:");
		txtpnNascionalidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascionalidade.setEditable(false);
		novoContrato.add(txtpnNascionalidade);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setBounds(32, 176, 85, 28);
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnEstado.setEditable(false);
		novoContrato.add(txtpnEstado);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setBounds(147, 176, 221, 28);
		textFieldEstado.setEnabled(false);
		textFieldEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldEstado.setColumns(10);
		novoContrato.add(textFieldEstado);
		
		JTextPane txtpnCidade = new JTextPane();
		txtpnCidade.setBounds(32, 214, 85, 28);
		txtpnCidade.setText("Cidade:");
		txtpnCidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCidade.setEditable(false);
		novoContrato.add(txtpnCidade);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(147, 210, 221, 28);
		textFieldCidade.setEnabled(false);
		textFieldCidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCidade.setColumns(10);
		novoContrato.add(textFieldCidade);
		
		JTextPane txtpnEndereco = new JTextPane();
		txtpnEndereco.setBounds(32, 249, 85, 28);
		txtpnEndereco.setText("Endere\u00E7o:");
		txtpnEndereco.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnEndereco.setEditable(false);
		novoContrato.add(txtpnEndereco);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(147, 244, 339, 28);
		textFieldEndereco.setEnabled(false);
		textFieldEndereco.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldEndereco.setColumns(10);
		novoContrato.add(textFieldEndereco);
		
		JTextPane txtpnN = new JTextPane();
		txtpnN.setBounds(491, 246, 33, 28);
		txtpnN.setText("N\u00BA:");
		txtpnN.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnN.setEditable(false);
		novoContrato.add(txtpnN);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(525, 244, 42, 28);
		textFieldNumero.setEnabled(false);
		textFieldNumero.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldNumero.setColumns(10);
		novoContrato.add(textFieldNumero);
		
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setBounds(336, 110, 42, 28);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCpf.setEditable(false);
		novoContrato.add(txtpnCpf);
		
		dateMask.setPlaceholderCharacter(' ');
		formattedTextFieldCPF = new JFormattedTextField(cpfMask);
		formattedTextFieldCPF.setBounds(386, 108, 181, 28);
		formattedTextFieldCPF.setEnabled(false);
		formattedTextFieldCPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextFieldCPF.setColumns(10);
		novoContrato.add(formattedTextFieldCPF);
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setBounds(147, 142, 117, 26);
		comboBoxPaises.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Afeganist\u00E3o", "\u00C1frica do Sul", "Akrotiri", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ant\u00EDgua e Barbuda", "Antilhas Neerlandesas", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "Bar\u00E9m", "B\u00E9lgica", "Belize", "Benim", "Bermudas", "Bielorr\u00FAssia", "Birm\u00E2nia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulg\u00E1ria", "Burquina Faso", "Bur\u00FAndi", "But\u00E3o", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Catar", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Col\u00F4mbia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dhekelia", "Dinamarca", "Dom\u00EDnica", "Egipto", "Emiratos \u00C1rabes Unidos", "Equador", "Eritreia", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Faro\u00E9", "Fiji", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Gab\u00E3o", "G\u00E2mbia", "Gana", "Gaza Strip", "Ge\u00F3rgia", "Ge\u00F3rgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Gr\u00E9cia", "Gronel\u00E2ndia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caim\u00E3o", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salom\u00E3o", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Brit\u00E2nicas", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jan Mayen", "Jap\u00E3o", "Jersey", "Jibuti", "Jord\u00E2nia", "Kuwait", "Laos", "Lesoto", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Listenstaine", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Mal\u00E1vi", "Maldivas", "Mali", "Malta", "Man, Isle of", "Marianas do Norte", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "Mayotte", "M\u00E9xico", "Micron\u00E9sia", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Mundo", "Nam\u00EDbia", "Nauru", "Navassa Island", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Niue", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Om\u00E3", "Pacific Ocean", "Pa\u00EDses Baixos", "Palau", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Quirib\u00E1ti", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa L\u00FAcia", "S\u00E3o Crist\u00F3v\u00E3o e Neves", "S\u00E3o Marinho", "S\u00E3o Pedro e Miquelon", "S\u00E3o Tom\u00E9 e Pr\u00EDncipe", "S\u00E3o Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazil\u00E2ndia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Svalbard e Jan Mayen", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Territ\u00F3rio Brit\u00E2nico do Oceano \u00CDndico", "Territ\u00F3rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Tuvalu", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		novoContrato.add(comboBoxPaises);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(comboBoxPaises.getSelectedItem().equals("Brasil")) { 
					textFieldEstado.setEnabled(true);
					textFieldCidade.setEnabled(true);
					textFieldEndereco.setEnabled(true);
					textFieldNumero.setEnabled(true);
					formattedTextFieldCPF.setEnabled(true); 
					} 
				} 
		});
		
		JTextPane txtpnDetalhesDoContrato = new JTextPane();
		txtpnDetalhesDoContrato.setBounds(21, 343, 192, 36);
		txtpnDetalhesDoContrato.setText("Detalhes do contrato");
		txtpnDetalhesDoContrato.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDetalhesDoContrato.setEditable(false);
		novoContrato.add(txtpnDetalhesDoContrato);
		
		JTextPane txtpnQuarto = new JTextPane();
		txtpnQuarto.setBounds(32, 391, 85, 28);
		txtpnQuarto.setText("Quarto:");
		txtpnQuarto.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnQuarto.setEditable(false);
		novoContrato.add(txtpnQuarto);
		
		comboBoxQuartoQ = new JComboBox<String>();
		comboBoxQuartoQ.setBounds(276, 391, 117, 26);
		comboBoxQuartoQ.setEnabled(false);
		comboBoxQuartoQ.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Simples", "Duplo", "Triplo"}));
		novoContrato.add(comboBoxQuartoQ);
		
		comboBoxQuarto = new JComboBox<String>();
		comboBoxQuarto.setBounds(147, 391, 117, 26);
		comboBoxQuarto.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Presidencial", "Luxo", "Executivo"}));
		novoContrato.add(comboBoxQuarto);
		comboBoxQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(comboBoxQuarto.getSelectedItem().equals("Luxo") || comboBoxQuarto.getSelectedItem().equals("Executivo")) { 
					comboBoxQuartoQ.setEnabled(true);
					} 
				} 
		});
		
		JCheckBox chckbxReserva = new JCheckBox("Reserva");
		chckbxReserva.setBackground(SystemColor.window);
		chckbxReserva.setBounds(449, 394, 104, 18);
		chckbxReserva.setFont(new Font("Verdana", Font.PLAIN, 12));
		novoContrato.add(chckbxReserva);
		
		JTextPane txtpnCheckIn = new JTextPane();
		txtpnCheckIn.setBounds(32, 426, 85, 28);
		txtpnCheckIn.setText("Check In:");
		txtpnCheckIn.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCheckIn.setEditable(false);
		novoContrato.add(txtpnCheckIn);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(dateMask);
		formattedTextField.setBounds(148, 429, 117, 28);
		formattedTextField.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextField.setColumns(10);
		novoContrato.add(formattedTextField);
		
		JTextPane txtpnCheckOut = new JTextPane();
		txtpnCheckOut.setBounds(32, 466, 85, 28);
		txtpnCheckOut.setText("Check Out:");
		txtpnCheckOut.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCheckOut.setEditable(false);
		novoContrato.add(txtpnCheckOut);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(dateMask);
		formattedTextField_1.setBounds(148, 464, 117, 28);
		formattedTextField_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		formattedTextField_1.setColumns(10);
		novoContrato.add(formattedTextField_1);
		
		JTextPane txtpnAcompanhantes = new JTextPane();
		txtpnAcompanhantes.setBounds(32, 503, 117, 28);
		txtpnAcompanhantes.setText("Acompanhantes:");
		txtpnAcompanhantes.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnAcompanhantes.setEditable(false);
		novoContrato.add(txtpnAcompanhantes);
		
		textField = new JTextField();
		textField.setBounds(147, 503, 42, 28);
		textField.setFont(new Font("Verdana", Font.PLAIN, 12));
		textField.setColumns(10);
		novoContrato.add(textField);
		
		chckbxCamaExtra = new JCheckBox("Cama extra");
		chckbxCamaExtra.setBackground(SystemColor.window);
		chckbxCamaExtra.setEnabled(false);
		chckbxCamaExtra.setBounds(449, 424, 104, 18);
		chckbxCamaExtra.setFont(new Font("Verdana", Font.PLAIN, 12));
		novoContrato.add(chckbxCamaExtra);
		chckbxCamaExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(comboBoxQuarto.getSelectedItem().equals("Luxo") || comboBoxQuarto.getSelectedItem().equals("Executivo") ||
						comboBoxQuartoQ.getSelectedItem().equals("Simples") || comboBoxQuartoQ.getSelectedItem().equals("Duplo")) { 
					chckbxCamaExtra.setEnabled(true);
					} 
				} 
		});
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 280, 117, 28);
		textPane.setText("Acompanhantes:");
		textPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		textPane.setEditable(false);
		novoContrato.add(textPane);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(147, 278, 420, 28);
		novoContrato.add(textField_1);

		JPanel pesquisarContrato = new JPanel();
		pesquisarContrato.setBackground(SystemColor.window);
		acoes1.add(pesquisarContrato, "pesquisarContrato");
		
		JPanel stand = new JPanel();
		acoes1.add(stand, "stand");
		layout.show(acoes1, "stand");
		stand.setBackground(SystemColor.window);
		
		JButton btnNovoContrato = new JButton("Novo Contrato");
		btnNovoContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNovoContrato.setBounds(24, 41, 180, 43);
		btnNovoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "novoContrato");
			}
		});
		contratos.add(btnNovoContrato);
		
		JButton btnPesquisarContrato = new JButton("Pesquisar Contrato");
		btnPesquisarContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPesquisarContrato.setBounds(24, 105, 180, 43);
		contratos.add(btnPesquisarContrato);
		
		JPanel servicos = new JPanel();
		servicos.setBackground(SystemColor.window);
		menuAbas.addTab("Servi\u00E7os", null, servicos, null);
		servicos.setLayout(null);
		
		acoes2 = new JPanel();
		acoes2.setBackground(SystemColor.window);
		acoes2.setBounds(227, 12, 607, 573);
		acoes2.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		servicos.add(acoes2);
		acoes2.setLayout(new CardLayout(0, 0));
		
		JPanel pesquisarServico = new JPanel();
		pesquisarServico.setBackground(SystemColor.window);
		acoes2.add(pesquisarServico, "pesquisarServico");
		
		JPanel atualizarServico = new JPanel();
		atualizarServico.setBackground(SystemColor.window);
		acoes2.add(atualizarServico, "atualizarServico");
		
		JPanel adicionarServico = new JPanel();
		adicionarServico.setBackground(SystemColor.window);
		acoes2.add(adicionarServico, "adicionarServico");
		adicionarServico.setLayout(null);
		
		JPanel hospedes = new JPanel();
		hospedes.setBackground(SystemColor.window);
		menuAbas.addTab("H\u00F3spedes", null, hospedes, null);
		hospedes.setLayout(null);
		
		acoes3 = new JPanel();
		acoes3.setBackground(SystemColor.window);
		acoes3.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes3.setBounds(227, 12, 607, 573);
		hospedes.add(acoes3);
		acoes3.setLayout(new CardLayout(0, 0));
		
		JPanel pesquisarHospede = new JPanel();
		pesquisarHospede.setBackground(SystemColor.window);
		acoes3.add(pesquisarHospede, "name_444976330230945");
		
		JPanel editarHospede = new JPanel();
		editarHospede.setBackground(SystemColor.window);
		acoes3.add(editarHospede, "name_444978562121406");
		
		JPanel opinioes = new JPanel();
		opinioes.setBackground(SystemColor.window);
		menuAbas.addTab("Opini\u00F5es", null, opinioes, null);
		opinioes.setLayout(null);
		
		acoes4 = new JPanel();
		acoes4.setBackground(SystemColor.window);
		acoes4.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes4.setBounds(227, 12, 607, 573);
		opinioes.add(acoes4);
	}
}
