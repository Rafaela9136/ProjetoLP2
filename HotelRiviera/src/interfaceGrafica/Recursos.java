package interfaceGrafica;

import hotel.Contrato;
import hotel.Estados;
import hotel.Hospede;
import hotel.Hotel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JButton;

import excecoes.CPFInvalidoException;
import excecoes.DataInvalidaException;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Recursos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8789089620746000252L;
	
	private CardLayout layout = new CardLayout(), layout2 = new CardLayout();
	private boolean brasileiro = false;
	private String[] hospedesAcompanhantes;
	private JComboBox<String> comboBoxPaises, comboBoxQuarto, comboBoxQuartoQ, comboBoxCamaExtra;
	private JPanel acoes1, panel;
	private JFormattedTextField textFieldData, textFieldCPF, textFieldCheckIn, textFieldCheckOut;
	private JTextField textFieldNome, textFieldEstado, textFieldCidade, textFieldEndereco, textFieldNumero, textFieldAcompanhantes;
	private JTextField textFieldHospedeTitular;
	private JTable table;
	private JTable table_1;
	
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public Recursos() throws ParseException {
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		setBounds(100, 100, 856, 645);
		setLayout(null);
		
		// Botoes
		JButton btnContrato = new JButton("Novo contrato");
		btnContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnContrato.setBounds(24, 42, 180, 43);
		btnContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "novoContrato");
			}
		});
		add(btnContrato);
		
		JButton btnPesquisarContrato = new JButton("Pesquisar contrato");
		btnPesquisarContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPesquisarContrato.setBounds(24, 105, 180, 43);
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "pesquisarContrato");
			}
		});
		add(btnPesquisarContrato);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSair.setBounds(24, 575, 180, 43);
		add(btnSair);
		
		// Paineis de acoes
		acoes1 = new JPanel();
		acoes1.setBackground(SystemColor.window);
		acoes1.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		acoes1.setBounds(227, 12, 607, 606);
		add(acoes1);
		acoes1.setLayout(layout);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		acoes1.add(vazio, "vazio");
		layout.show(acoes1, "vazio");
		
		// Novo Contrato
		JPanel novoContrato = new JPanel();
		novoContrato.setBackground(Color.WHITE);
		acoes1.add(novoContrato, "novoContrato");
		novoContrato.setLayout(null);
		acoes1.add(novoContrato, "novoContrato");
		
		JTextPane txtpnDadosDoHspede = new JTextPane();
		txtpnDadosDoHspede.setBounds(21, 31, 192, 36);
		txtpnDadosDoHspede.setEditable(false);
		txtpnDadosDoHspede.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDadosDoHspede.setText("Dados do h\u00F3spede");
		novoContrato.add(txtpnDadosDoHspede);

		// Nome
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

		// Data de nascimento
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

		// Pais
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setBounds(32, 142, 85, 28);
		txtpnNascionalidade.setText("Pa\u00EDs:");
		txtpnNascionalidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascionalidade.setEditable(false);
		novoContrato.add(txtpnNascionalidade);
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setBounds(147, 142, 117, 26);
		comboBoxPaises.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(
				new String[] { "(selecionar)", "Afeganist\u00E3o", "\u00C1frica do Sul", "Akrotiri", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ant\u00EDgua e Barbuda","Antilhas Neerlandesas", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "Bar\u00E9m", "B\u00E9lgica", "Belize", "Benim", "Bermudas", "Bielorr\u00FAssia", "Birm\u00E2nia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulg\u00E1ria", "Burquina Faso", "Bur\u00FAndi", "But\u00E3o", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Catar", "Cazaquist\u00E3o", "Chile", "China", "Chipre", "Col\u00F4mbia", "Comores", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dinamarca", "Dom\u00EDnica", "Egipto", "Equador", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Fiji", "Filipinas", "Finl\u00E2ndia", 
						"Fran\u00E7a", "Gab\u00E3o", "G\u00E2mbia", "Gana", "Ge\u00F3rgia", "Gr\u00E9cia", "Gronel\u00E2ndia", "Guatemala", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jap\u00E3o", "Jord\u00E2nia", "Kuwait","Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria",	"Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Mal\u00E1vi", "Maldivas", "Mali", "Malta", "Marrocos", "M\u00E9xico", "Micron\u00E9sia", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Nam\u00EDbia", "Nauru", "Navassa Island", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Noruega", "Nova Zel\u00E2ndia", "Pacific Ocean", "Pa\u00EDses Baixos","Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paraguai", "Peru", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Quirib\u00E1ti", "Reino Unido",
						 "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa L\u00FAcia", "S\u00E3o Crist\u00F3v\u00E3o e Neves", "S\u00E3o Marinho", "S\u00E3o Pedro e Miquelon", "S\u00E3o Tom\u00E9 e Pr\u00EDncipe", "S\u00E3o Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazil\u00E2ndia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Svalbard e Jan Mayen", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Territ\u00F3rio Brit\u00E2nico do Oceano \u00CDndico", "Territ\u00F3rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Tuvalu", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vaticano", "Venezuela", "Vietname", "Zimbabu\u00E9" }));
		novoContrato.add(comboBoxPaises);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPaises.getSelectedItem().equals("Brasil")) {
					brasileiro = true;
					textFieldEstado.setEnabled(true);
					textFieldCidade.setEnabled(true);
					textFieldEndereco.setEnabled(true);
					textFieldNumero.setEnabled(true);
					textFieldCPF.setEnabled(true);
				} else {
					textFieldEstado.setEnabled(false);
					textFieldCidade.setEnabled(false);
					textFieldEndereco.setEnabled(false);
					textFieldNumero.setEnabled(false);
					textFieldCPF.setEnabled(false);
				}
			}
		});

		// Estado
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
		
		selecionaEstado();

		// Cidade
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

		// Endereco
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
		
		// Numero
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
		
		// CPF
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setBounds(336, 110, 42, 28);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCpf.setEditable(false);
		novoContrato.add(txtpnCpf);

		dateMask.setPlaceholderCharacter(' ');
		textFieldCPF = new JFormattedTextField(cpfMask);
		textFieldCPF.setBounds(386, 108, 181, 28);
		textFieldCPF.setEnabled(false);
		textFieldCPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCPF.setColumns(10);
		novoContrato.add(textFieldCPF);

		JTextPane txtpnDetalhesDoContrato = new JTextPane();
		txtpnDetalhesDoContrato.setBounds(21, 355, 192, 36);
		txtpnDetalhesDoContrato.setText("Detalhes do contrato");
		txtpnDetalhesDoContrato.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDetalhesDoContrato.setEditable(false);
		novoContrato.add(txtpnDetalhesDoContrato);

		// Tipos de quarto
		JTextPane txtpnQuarto = new JTextPane();
		txtpnQuarto.setBounds(33, 399, 85, 28);
		txtpnQuarto.setText("Quarto:");
		txtpnQuarto.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnQuarto.setEditable(false);
		novoContrato.add(txtpnQuarto);

		comboBoxCamaExtra = new JComboBox<String>();
		comboBoxCamaExtra.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "sim", "n\u00E3o"}));
		comboBoxCamaExtra.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxCamaExtra.setBounds(450, 511, 117, 26);
		novoContrato.add(comboBoxCamaExtra);

		comboBoxQuartoQ = new JComboBox<String>();
		comboBoxQuartoQ.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Simples", "Duplo", "Triplo"}));
		comboBoxQuartoQ.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxQuartoQ.setBounds(277, 399, 117, 26);
		comboBoxQuartoQ.setEnabled(false);
		novoContrato.add(comboBoxQuartoQ);
		comboBoxQuartoQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxQuartoQ.getSelectedItem().equals("Triplo")) {
					comboBoxCamaExtra.setEnabled(false);
				} else {
					comboBoxCamaExtra.setEnabled(true);
				}
			}
		});

		comboBoxQuarto = new JComboBox<String>();
		comboBoxQuarto.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Presidencial", "Luxo", "Executivo"}));
		comboBoxQuarto.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxQuarto.setBounds(148, 399, 117, 26);
		novoContrato.add(comboBoxQuarto);
		comboBoxQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxQuarto.getSelectedItem().equals("Luxo")
						|| comboBoxQuarto.getSelectedItem().equals("Executivo")) {
					comboBoxQuartoQ.setEnabled(true);
				} else {
					comboBoxQuartoQ.setEnabled(false);
				}
			}
		});

		// Data Check in
		JTextPane txtpnCheckIn = new JTextPane();
		txtpnCheckIn.setBounds(33, 434, 85, 28);
		txtpnCheckIn.setText("Check In:");
		txtpnCheckIn.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCheckIn.setEditable(false);
		novoContrato.add(txtpnCheckIn);

		textFieldCheckIn = new JFormattedTextField(
				dateMask);
		textFieldCheckIn.setBounds(149, 437, 117, 28);
		textFieldCheckIn.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCheckIn.setColumns(10);
		novoContrato.add(textFieldCheckIn);

		// Data Check Out
		JTextPane txtpnCheckOut = new JTextPane();
		txtpnCheckOut.setBounds(33, 474, 85, 28);
		txtpnCheckOut.setText("Check Out:");
		txtpnCheckOut.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCheckOut.setEditable(false);
		novoContrato.add(txtpnCheckOut);

		textFieldCheckOut = new JFormattedTextField(
				dateMask);
		textFieldCheckOut.setBounds(149, 472, 117, 28);
		textFieldCheckOut.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCheckOut.setColumns(10);
		novoContrato.add(textFieldCheckOut);

		// Acompanhantes
		JTextPane txtpnAcompanhantes = new JTextPane();
		txtpnAcompanhantes.setBounds(33, 511, 112, 28);
		txtpnAcompanhantes.setText("Acompanhantes:");
		txtpnAcompanhantes.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnAcompanhantes.setEditable(false);
		novoContrato.add(txtpnAcompanhantes);

		textFieldAcompanhantes = new JTextField();
		textFieldAcompanhantes.setBounds(148, 511, 42, 28);
		textFieldAcompanhantes.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldAcompanhantes.setColumns(10);
		novoContrato.add(textFieldAcompanhantes);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 280, 112, 28);
		textPane.setText("Acompanhantes:");
		textPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		textPane.setEditable(false);
		novoContrato.add(textPane);
		
		JTextArea textAreaAcompanhantes = new JTextArea();
		textAreaAcompanhantes.setLineWrap(true);
		textAreaAcompanhantes.setBounds(147, 280, 420, 73);
		novoContrato.add(textAreaAcompanhantes);		
		hospedesAcompanhantes = textAreaAcompanhantes.getText().split(",");

		// Cama extra
		JTextPane txtpnCamasExtra = new JTextPane();
		txtpnCamasExtra.setText("Camas extra:");
		txtpnCamasExtra.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCamasExtra.setEditable(false);
		txtpnCamasExtra.setBounds(336, 511, 107, 28);
		novoContrato.add(txtpnCamasExtra);

		// Finaliza, gerando o novo hospede e novo contrato
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar.setBounds(432, 562, 145, 28);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();
				try {
					Hospede novoHospede = new Hospede(textFieldNome.getText(), Calendar.getInstance(), brasileiro, (String) comboBoxPaises.getSelectedItem(), selecionaEstado(),
							textFieldCidade.getText(), textFieldEndereco.getText(), textFieldNumero.getText(), textFieldCPF.getText());
					
//					Contrato novoContrato = new Contrato(novoHospede, hospedesAcompanhantes, );
				} catch (NullPointerException e1) {
					erro.setVisible(true);
				} catch (CPFInvalidoException e1) {
					erro.setVisible(true);
				}
				AvisoSucesso aviso = new AvisoSucesso();
				aviso.setVisible(true);
				layout.show(acoes1, "vazio");
			}
		});
		novoContrato.add(btnConfirmar);
		
		// Pesquisar Contrato
		JPanel pesquisarContrato = new JPanel();
		pesquisarContrato.setBackground(Color.WHITE);
		acoes1.add(pesquisarContrato, "pesquisarContrato");
		pesquisarContrato.setLayout(null);
		
		JTextPane txtpnHspedeTitular = new JTextPane();
		txtpnHspedeTitular.setEditable(false);
		txtpnHspedeTitular.setBounds(24, 26, 78, 21);
		pesquisarContrato.add(txtpnHspedeTitular);
		txtpnHspedeTitular.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnHspedeTitular.setText("Hóspede:");
		
		textFieldHospedeTitular = new JTextField();
		textFieldHospedeTitular.setBounds(104, 26, 476, 28);
		pesquisarContrato.add(textFieldHospedeTitular);
		textFieldHospedeTitular.setColumns(10);
		
		JButton btnConfirmarP = new JButton("Confirmar");
		btnConfirmarP.setBounds(435, 60, 145, 28);
		pesquisarContrato.add(btnConfirmarP);
		btnConfirmarP.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 568, 478);
		pesquisarContrato.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"H\u00F3spede", "", "Contrato"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		// Atualizar Contrato
		JPanel atualizarContrato = new JPanel();
		atualizarContrato.setBackground(Color.WHITE);
		acoes1.add(atualizarContrato, "atualizarContrato");
		atualizarContrato.setLayout(null);
		
		// Barra de opcoes
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 609, 34);
		atualizarContrato.add(toolBar);
		
		//Acoes atualizar Contrato
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 33, 603, 569);
		atualizarContrato.add(panel);
		panel.setLayout(layout2);
		
		JPanel editarHospede = new JPanel();
		editarHospede.setBackground(Color.WHITE);
		panel.add(editarHospede, "editarHospede");
		
		JPanel editarServicos = new JPanel();
		editarServicos.setBackground(Color.WHITE);
		panel.add(editarServicos, "adicionarServico");
		editarServicos.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 56, 566, 210);
		editarServicos.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Servi\u00E7o", "T\u00E9rmino", "Data inicial"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_1.setViewportView(table_1);
		
		JTextPane txtpnServiosContratados = new JTextPane();
		txtpnServiosContratados.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnServiosContratados.setText("Serviços contratados: ");
		txtpnServiosContratados.setBounds(15, 23, 223, 21);
		editarServicos.add(txtpnServiosContratados);
		
		JTextPane txtpnAdicionarServio = new JTextPane();
		txtpnAdicionarServio.setEditable(false);
		txtpnAdicionarServio.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnAdicionarServio.setText("Adicionar serviço:");
		txtpnAdicionarServio.setBounds(15, 317, 160, 23);
		editarServicos.add(txtpnAdicionarServio);
		
		JButton btnCancelarServio = new JButton("Cancelar serviço");
		btnCancelarServio.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCancelarServio.setBounds(436, 278, 145, 25);
		editarServicos.add(btnCancelarServio);
		
		JTextPane txtpnTipoDeServio = new JTextPane();
		txtpnTipoDeServio.setEditable(false);
		txtpnTipoDeServio.setText("Tipo de serviço:");
		txtpnTipoDeServio.setBounds(15, 345, 113, 21);
		editarServicos.add(txtpnTipoDeServio);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"(selecionar)", "Babá", "Carro executivo", "Carro luxo", "Restaurante"}));
		comboBox_1.setBounds(135, 345, 154, 24);
		editarServicos.add(comboBox_1);
		
		JPanel fecharContrato = new JPanel();
		fecharContrato.setBackground(Color.WHITE);
		panel.add(fecharContrato, "fecharContrato");
		fecharContrato.setLayout(null);
		
		JButton btnConfirmarFechar = new JButton("Confirmar");
		btnConfirmarFechar.setBounds(432, 531, 145, 25);
		btnConfirmarFechar.setFont(new Font("Dialog", Font.PLAIN, 12));
		fecharContrato.add(btnConfirmarFechar);
		
		JTextPane txtpnOpiniaoDosHospede = new JTextPane();
		txtpnOpiniaoDosHospede.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnOpiniaoDosHospede.setEditable(false);
		txtpnOpiniaoDosHospede.setText("Opinião dos hóspede:");
		txtpnOpiniaoDosHospede.setBounds(12, 27, 219, 25);
		fecharContrato.add(txtpnOpiniaoDosHospede);
		
		JTextPane txtpnComentario = new JTextPane();
		txtpnComentario.setEditable(false);
		txtpnComentario.setText("Comentário:");
		txtpnComentario.setBounds(12, 64, 95, 21);
		fecharContrato.add(txtpnComentario);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(12, 85, 568, 187);
		fecharContrato.add(textArea);
		
		JTextPane textpnAvaliacao = new JTextPane();
		textpnAvaliacao.setEditable(false);
		textpnAvaliacao.setText("Avaliação:");
		textpnAvaliacao.setBounds(383, 294, 76, 21);
		fecharContrato.add(textpnAvaliacao);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(465, 294, 112, 24);
		fecharContrato.add(comboBox);
	
		JButton btnEditarDados = new JButton("Editar dados do h\u00F3spede");
		btnEditarDados.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout2.show(panel, "editarHospede");
			}
		});
		toolBar.add(btnEditarDados);
		
		JButton btnServicos = new JButton("Editar serviços");
		btnServicos.setFont(new Font("Dialog", Font.BOLD, 12));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout2.show(panel, "adicionarServico");
			}
		});
		toolBar.add(btnServicos);
		
		JButton btnFecharContrato = new JButton("Fechar contrato");
		btnFecharContrato.setFont(new Font("Dialog", Font.BOLD, 12));
		btnFecharContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout2.show(panel, "fecharContrato");
			}	
		});
		toolBar.add(btnFecharContrato);
		
		// Detalhes Contrato
		JPanel detalhesContrato = new JPanel();
		detalhesContrato.setBackground(Color.WHITE);
		acoes1.add(detalhesContrato, "detalhesContrato");
		detalhesContrato.setLayout(null);
		
		JButton btnAtualizarContrato = new JButton("Atualizar contrato");
		btnAtualizarContrato.setBounds(432, 562, 145, 25);
		btnAtualizarContrato.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAtualizarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "atualizarContrato");
			}
		});
		detalhesContrato.add(btnAtualizarContrato);

		
		
		//**** retirar ****
		JButton btnteste = new JButton("teste");
		btnteste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "detalhesContrato");
			}
		});
		btnteste.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnteste.setBounds(51, 160, 145, 25);
		add(btnteste);
	}

	private Estados selecionaEstado() {
		for (int i = 0; i < Estados.values().length; i++) {
			if(Estados.values()[i].equals(textFieldEstado.getText()))
				return Estados.values()[i];
		}
		return null;
	}
}
