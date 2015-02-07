package visual;

import hotel.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import excecoes.CPFInvalidoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.FrigobarEmListServicosException;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class AcoesGerais extends JPanel implements contratosExistentes {

	/**
	 */
	private static final long serialVersionUID = -3539467665785505533L;
	private static JPanel panel;
	private final static CardLayout layout = new CardLayout();
	
	private JComboBox<String> comboBoxQuarto;
	private JComboBox<String> comboBoxQuartoQ;
	private JComboBox<String> comboBoxPaises;
	private JRadioButton rdbtnReserva;
	private JRadioButton rdbtnCamaExtra;
	private JTextField textFieldNome;
	private JTextField textFieldEstado;
	private JTextField textFieldCidade;
	private JTextField textFieldEndereco;
	private JTextField textFieldNumero;
	private JFormattedTextField textFieldCPF;
	private JFormattedTextField textFieldData;
	private JFormattedTextField textFieldCheckIn;
	private JFormattedTextField textFieldCheckOut;
	
	// Dados para criacao dos objetos
	private String[] hospedesAcompanhantes;
	private List<Servico> servicos;
	private EstrategiaAplicavel estrategia;
	private Quarto quarto;
	private boolean brasileiro = false;
	
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AcoesGerais() throws ParseException {
		setLayout(null);
		
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 607, 606);
		add(panel);
		panel.setLayout(layout);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		// Abrir Contrato
		JPanel novoContrato = new JPanel();
		novoContrato.setBackground(Color.WHITE);
		panel.add(novoContrato, "novoContrato");
		novoContrato.setLayout(null);
		
		// Dados do hospede
		hospedeDadosPrincipais(dateMask, cpfMask, panel, novoContrato);
		hospedeEndereco(novoContrato);
		
		// Tipo de quarto
		detalhesQuarto(dateMask, novoContrato);
		
		// Finaliza, gerando o novo hospede e novo contrato
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar.setBounds(432, 562, 145, 28);
		btnConfirmar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();

				if (comboBoxQuarto.getSelectedItem().equals("Presidencial"))
					quarto = new SuitePresidencial();
				if (comboBoxQuarto.getSelectedItem().equals("Luxo"))
					quarto = new QuartoLuxo(rdbtnCamaExtra.isSelected(),
							Conector.selecionaTipoQuarto(comboBoxQuartoQ
									.getSelectedItem()));
				if (comboBoxQuarto.getSelectedItem().equals("Executivo"))
					quarto = new QuartoExecutivo(rdbtnCamaExtra.isSelected(),
							Conector.selecionaTipoQuarto(comboBoxQuartoQ
									.getSelectedItem()));

				servicos.add(quarto);
				try {
					Hospede novoHospede = new Hospede(
							textFieldNome.getText(),
							Conector.transformaData(textFieldData.getText()),
							brasileiro,
							(String) comboBoxPaises.getSelectedItem(),
							Conector.selecionaEstado(textFieldEstado.getText()),
							textFieldCidade.getText(), textFieldEndereco
									.getText(), textFieldNumero.getText(),
							textFieldCPF.getText());

					Contrato contrato = new Contrato(
							novoHospede,
							Conector.transformaVetor(hospedesAcompanhantes),
							estrategia,
							Conector.transformaData(textFieldCheckIn.getText()),
							Conector.transformaData(textFieldCheckOut.getText()),
							rdbtnReserva.isSelected(), servicos);

					if (Hotel.adicionaContrato(contrato)) {
						AvisoSucesso aviso = new AvisoSucesso();
						aviso.setVisible(true);
						layout.show(panel, "vazio");
					}
				} catch (NullPointerException e1) {
					erro.setVisible(true);
				} catch (CPFInvalidoException e1) {
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
		novoContrato.add(btnConfirmar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCancelar.setBounds(21, 562, 145, 28);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "vazio");
			}
		});
		novoContrato.add(btnCancelar);
		
		//**
		
		//AtualizarContrato
		JPanel atualizarContrato = new JPanel();
		atualizarContrato.setBackground(Color.WHITE);
		panel.add(atualizarContrato, "atualizarContrato");
		atualizarContrato.setLayout(null);
		
		// Barra de opcoes
		barraOpcoes(panel, atualizarContrato);
		
		AtualizarContrato atualizar = new AtualizarContrato();
		atualizar.setBounds(0, 34, 607, 572);
		atualizarContrato.add(atualizar);
		//**
		
		// Pesquisa Contrato
		JPanel pesquisarContrato = new JPanel();
		pesquisarContrato.setBackground(Color.WHITE);
		panel.add(pesquisarContrato, "pesquisarContrato");
		pesquisarContrato.setLayout(null);
		
		// pesquisa
		JTextPane txtpnHspedeTitular = new JTextPane();
		txtpnHspedeTitular.setEditable(false);
		txtpnHspedeTitular.setBounds(24, 26, 78, 21);
		pesquisarContrato.add(txtpnHspedeTitular);
		txtpnHspedeTitular.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnHspedeTitular.setText("H\u00F3spede:");

		final JTextField textFieldHospedeTitular = new JTextField();
		textFieldHospedeTitular.setBounds(104, 26, 476, 28);
		pesquisarContrato.add(textFieldHospedeTitular);
		textFieldHospedeTitular.setColumns(10);

		JButton btnConfirmarP = new JButton("Confirmar");
		btnConfirmarP.setBounds(435, 60, 145, 28);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Contrato> contratoPesquisado = Hotel.pesquisaContrato(textFieldHospedeTitular.getText());
			}
		});
		pesquisarContrato.add(btnConfirmarP);
		
		btnConfirmarP.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		// tabela de contratos
		contratosExistentes(pesquisarContrato);
		
		// Detalhes
		JPanel detalhesContrato = new JPanel();
		detalhesContrato.setBackground(Color.WHITE);
		panel.add(detalhesContrato, "detalhesContrato");
		detalhesContrato.setLayout(null);

		JButton btnAtualizarContrato = new JButton("Atualizar contrato");
		btnAtualizarContrato.setBounds(432, 562, 145, 25);
		btnAtualizarContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAtualizarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(panel, "atualizarContrato");
			}
		});
		detalhesContrato.add(btnAtualizarContrato);
	}
	
	static void selecionaTela(String tela){
		layout.show(panel, tela);
	}

	@SuppressWarnings("serial")
	private void contratosExistentes(JPanel pesquisarContrato) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 568, 478);
		pesquisarContrato.add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"H\u00F3spede", "", "Contrato" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		scrollPane.setViewportView(table);
	}

	private void barraOpcoes(final JPanel panel, JPanel atualizarContrato) {
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 609, 34);
		atualizarContrato.add(toolBar);

		JButton btnEditarDados = new JButton("Editar dados do h\u00F3spede");
		btnEditarDados.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarContrato.selecionaTela("editarHospede");
			}
		});
		toolBar.add(btnEditarDados);

		JButton btnServicos = new JButton("Editar servi\u00E7os");
		btnServicos.setFont(new Font("Verdana", Font.BOLD, 12));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarContrato.selecionaTela("adicionarServico");
			}
		});
		toolBar.add(btnServicos);

		JButton btnFecharContrato = new JButton("Fechar contrato");
		btnFecharContrato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnFecharContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarContrato.selecionaTela("fecharContrato");
			}
		});
		toolBar.add(btnFecharContrato);
	}

	private void detalhesQuarto(final MaskFormatter dateMask,
			JPanel novoContrato) {
		JTextPane txtpnDetalhesDoContrato = new JTextPane();
		txtpnDetalhesDoContrato.setBounds(21, 355, 192, 36);
		txtpnDetalhesDoContrato.setText("Detalhes do contrato");
		txtpnDetalhesDoContrato.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDetalhesDoContrato.setEditable(false);
		novoContrato.add(txtpnDetalhesDoContrato);

		JTextPane txtpnQuarto = new JTextPane();
		txtpnQuarto.setBounds(33, 399, 85, 28);
		txtpnQuarto.setText("Quarto:");
		txtpnQuarto.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnQuarto.setEditable(false);
		novoContrato.add(txtpnQuarto);

		// Data Check in
		JTextPane txtpnCheckIn = new JTextPane();
		txtpnCheckIn.setBounds(33, 434, 85, 28);
		txtpnCheckIn.setText("Check In:");
		txtpnCheckIn.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCheckIn.setEditable(false);
		novoContrato.add(txtpnCheckIn);

		JFormattedTextField textFieldCheckIn = new JFormattedTextField(dateMask);
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

		JFormattedTextField textFieldCheckOut = new JFormattedTextField(dateMask);
		textFieldCheckOut.setBounds(149, 472, 117, 28);
		textFieldCheckOut.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCheckOut.setColumns(10);
		novoContrato.add(textFieldCheckOut);
		
		rdbtnCamaExtra = new JRadioButton("Cama extra");
		rdbtnCamaExtra.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnCamaExtra.setBounds(463, 434, 104, 18);
		novoContrato.add(rdbtnCamaExtra);

		comboBoxQuartoQ = new JComboBox<String>();
		comboBoxQuartoQ.setModel(new DefaultComboBoxModel<String>(new String[] {
				"(selecionar)", "Simples", "Duplo", "Triplo" }));
		comboBoxQuartoQ.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxQuartoQ.setBounds(277, 399, 117, 26);
		comboBoxQuartoQ.setEnabled(false);
		novoContrato.add(comboBoxQuartoQ);
		comboBoxQuartoQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxQuartoQ.getSelectedItem().equals("Triplo")) {
					rdbtnCamaExtra.setEnabled(false);
				} else {
					rdbtnCamaExtra.setEnabled(true);
				}
			}
		});

		comboBoxQuarto = new JComboBox<String>();
		comboBoxQuarto.setModel(new DefaultComboBoxModel<String>(new String[] {
				"(selecionar)", "Presidencial", "Luxo", "Executivo" }));
		comboBoxQuarto.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxQuarto.setBounds(148, 399, 117, 26);
		novoContrato.add(comboBoxQuarto);
		comboBoxQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxQuarto.getSelectedItem().equals("Presidencial")){
					comboBoxQuartoQ.setEnabled(false);
				} if (comboBoxQuarto.getSelectedItem().equals("Luxo")
					|| comboBoxQuarto.getSelectedItem().equals("Executivo")) {
					comboBoxQuartoQ.setEnabled(true);
				}
			}
		});
		
		rdbtnReserva = new JRadioButton("Reserva");
		rdbtnReserva.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnReserva.setBounds(463, 399, 85, 18);
		novoContrato.add(rdbtnReserva);
	}
	

	private void hospedeEndereco(JPanel novoContrato) {		
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
		novoContrato.add(textFieldEstado);

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
		novoContrato.add(textFieldNumero);
		
		// Pais
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setBounds(32, 142, 85, 28);
		txtpnNascionalidade.setText("Pa\u00EDs:");
		txtpnNascionalidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascionalidade.setEditable(false);
		novoContrato.add(txtpnNascionalidade);
				
		final JComboBox<String> comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Afeganist\u00E3o", "\u00C1frica do Sul", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "B\u00E9lgica", "Belize", "Bermudas", "Bielorr\u00FAssia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Bulg\u00E1ria", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Col\u00F4mbia", "Comores", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dinamarca", "Dom\u00EDnica", "Egipto", "Equador", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Ge\u00F3rgia", "Granada", "Gr\u00E9cia", "Guatemala", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jap\u00E3o", "Jord\u00E2nia", "Kuwait", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "M\u00E9xico", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Pacific Ocean", "Pa\u00EDses Baixos", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vaticano", "Venezuela", "Vietname", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		comboBoxPaises.setBounds(147, 142, 117, 28);
		novoContrato.add(comboBoxPaises);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxPaises.getSelectedItem().equals("Brasil")){
					brasileiro = true;
					textFieldCPF.setEnabled(true);
					textFieldEstado.setEnabled(true);
					textFieldCidade.setEnabled(true);
					textFieldEndereco.setEnabled(true);
					textFieldNumero.setEnabled(true);
				} else {
					textFieldCPF.setEnabled(false);
					textFieldEstado.setEnabled(false);
					textFieldCidade.setEnabled(false);
					textFieldEndereco.setEnabled(false);
					textFieldNumero.setEnabled(false);
				}
			}
		});
	}
	

	private void hospedeDadosPrincipais(final MaskFormatter dateMask, final MaskFormatter cpfMask,
			JPanel panel, JPanel novoContrato) {
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
		
		// CPF
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setBounds(336, 110, 42, 28);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCpf.setEditable(false);
		novoContrato.add(txtpnCpf);

		textFieldCPF = new JFormattedTextField(cpfMask);
		textFieldCPF.setBounds(386, 108, 181, 28);
		textFieldCPF.setEnabled(false);
		textFieldCPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCPF.setColumns(10);
		novoContrato.add(textFieldCPF);
		
		// Acompanhantes
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
	}
}