package visual;

import hotel.Contrato;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AtualizarContrato extends JPanel {

	/**
	 */
	private static final long serialVersionUID = -2433143749466773160L;
	private static JPanel panel;
	private final static CardLayout layout = new CardLayout();
	
	private JTextField textFieldEstado;
	private JTextField textFieldCidade;
	private JTextField textFieldEndereco;
	private JTextField textFieldNumero;
	private JFormattedTextField textFieldCPF;
	
	private String[] hospedesAcompanhantes;
	private Contrato contrato;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AtualizarContrato() throws ParseException {
		final MaskFormatter dateMask = new MaskFormatter("##/##/####");
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 603, 569);
		add(panel);
		panel.setLayout(layout);
		
		// Editar H�spede
		JPanel editarHospede = new JPanel();
		editarHospede.setBackground(Color.WHITE);
		panel.add(editarHospede, "editarHospede");
		editarHospede.setLayout(null);
		
		hospedeDadosPrincipais(dateMask, cpfMask, panel, editarHospede);
		hospedeEndereco(editarHospede);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnConfirmar.setBounds(418, 533, 145, 25);
		editarHospede.add(btnConfirmar);
		
		// Edicao de servicos
		JPanel editarServicos = new JPanel();
		editarServicos.setBackground(Color.WHITE);
		panel.add(editarServicos, "adicionarServico");
		editarServicos.setLayout(null);

		tabelaServicosContratados(editarServicos);
		
		JButton btnCancelarServio = new JButton("Cancelar servi\u00E7o");
		btnCancelarServio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCancelarServio.setBounds(436, 291, 145, 25);
		editarServicos.add(btnCancelarServio);

		JTextPane txtpnServicosContratados = new JTextPane();
		txtpnServicosContratados.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnServicosContratados.setText("Servi\u00E7os contratados: ");
		txtpnServicosContratados.setBounds(15, 23, 223, 21);
		editarServicos.add(txtpnServicosContratados);

		JTextPane txtpnAdicionarServio = new JTextPane();
		txtpnAdicionarServio.setEditable(false);
		txtpnAdicionarServio.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnAdicionarServio.setText("Adicionar servi\u00E7o:");
		txtpnAdicionarServio.setBounds(15, 330, 160, 23);
		editarServicos.add(txtpnAdicionarServio);

		JTextPane txtpnTipoDeServico = new JTextPane();
		txtpnTipoDeServico.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnTipoDeServico.setEditable(false);
		txtpnTipoDeServico.setText("Tipo de servi\u00E7o:");
		txtpnTipoDeServico.setBounds(15, 365, 113, 21);
		editarServicos.add(txtpnTipoDeServico);
		
		JTextPane txtpnDataInicial = new JTextPane();
		txtpnDataInicial.setText("Data inicial:");
		txtpnDataInicial.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnDataInicial.setEditable(false);
		txtpnDataInicial.setBounds(15, 402, 90, 21);
		editarServicos.add(txtpnDataInicial);

		JTextPane txtpnTermino = new JTextPane();
		txtpnTermino.setText("T\u00E9rmino:");
		txtpnTermino.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnTermino.setEditable(false);
		txtpnTermino.setBounds(292, 402, 73, 21);
		editarServicos.add(txtpnTermino);
		
		JFormattedTextField formattedTextFieldDataI = new JFormattedTextField(
				dateMask);
		formattedTextFieldDataI.setBounds(135, 401, 121, 27);
		editarServicos.add(formattedTextFieldDataI);

		JFormattedTextField formattedTextFieldDataF = new JFormattedTextField(
				dateMask);
		formattedTextFieldDataF.setBounds(374, 398, 121, 27);
		editarServicos.add(formattedTextFieldDataF);
		
		final AcoesNovoServico painelDeServico = new AcoesNovoServico();
		painelDeServico.setBounds(15, 439, 566, 90);
		editarServicos.add(painelDeServico);
		
		// Escolhe o tipo e paineis de opcao
		final JComboBox<String> comboBoxTipoServico = new JComboBox<String>();
		comboBoxTipoServico.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxTipoServico.setModel(new DefaultComboBoxModel<String>(
				new String[] { "(selecionar)", "Bab\u00E1", "Carro",
						"Restaurante" }));
		comboBoxTipoServico.setBounds(135, 365, 154, 24);
		comboBoxTipoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcoesNovoServico.selecionaServico(comboBoxTipoServico.getSelectedItem());
			}
		});
		editarServicos.add(comboBoxTipoServico);

		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar_1.setBounds(436, 540, 145, 25);
		editarServicos.add(btnConfirmar_1);
		
		fecharContrato(panel);
	}


	private void fecharContrato(JPanel panel) {
		// Fechar Contrato
		JPanel fecharContrato = new JPanel();
		fecharContrato.setBackground(Color.WHITE);
		panel.add(fecharContrato, "fecharContrato");
		fecharContrato.setLayout(null);

		// Avaliacao do hospede
		JButton btnConfirmarFechar = new JButton("Confirmar");
		btnConfirmarFechar.setBounds(436, 524, 145, 25);
		btnConfirmarFechar.setFont(new Font("Verdana", Font.PLAIN, 12));
		fecharContrato.add(btnConfirmarFechar);

		JTextPane txtpnOpiniaoDosHospede = new JTextPane();
		txtpnOpiniaoDosHospede.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnOpiniaoDosHospede.setEditable(false);
		txtpnOpiniaoDosHospede.setText("Opini\u00E3o dos h\u00F3spede:");
		txtpnOpiniaoDosHospede.setBounds(12, 27, 219, 25);
		fecharContrato.add(txtpnOpiniaoDosHospede);

		JTextPane txtpnComentario = new JTextPane();
		txtpnComentario.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnComentario.setEditable(false);
		txtpnComentario.setText("Coment\u00E1rio:");
		txtpnComentario.setBounds(12, 64, 95, 21);
		fecharContrato.add(txtpnComentario);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(12, 85, 568, 187);
		fecharContrato.add(textArea);

		JTextPane textpnAvaliacao = new JTextPane();
		textpnAvaliacao.setFont(new Font("Verdana", Font.PLAIN, 12));
		textpnAvaliacao.setEditable(false);
		textpnAvaliacao.setText("Avalia\u00E7\u00E3o:");
		textpnAvaliacao.setBounds(383, 294, 76, 21);
		fecharContrato.add(textpnAvaliacao);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"(selecionar)", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10" }));
		comboBox.setBounds(465, 294, 112, 24);
		fecharContrato.add(comboBox);
	}
	
	static void selecionaTela(String tela){
		layout.show(panel, tela);
	}
	
	@SuppressWarnings("serial")
	private void tabelaServicosContratados(JPanel editarServicos) {
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 56, 566, 223);
		editarServicos.add(scrollPane_1);

		JTable table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Servi\u00E7o", "T\u00E9rmino", "Data inicial" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_1.setViewportView(table_1);
	}
	
	private void hospedeDadosPrincipais(final MaskFormatter dateMask, final MaskFormatter cpfMask,
			JPanel panel, JPanel editarHospede) {
		JTextPane txtpnDadosDoHspede = new JTextPane();
		txtpnDadosDoHspede.setBounds(21, 31, 192, 36);
		txtpnDadosDoHspede.setEditable(false);
		txtpnDadosDoHspede.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnDadosDoHspede.setText("Dados do h\u00F3spede");
		editarHospede.add(txtpnDadosDoHspede);

		// Nome
		JTextPane txtpnNome = new JTextPane();
		txtpnNome.setBounds(32, 76, 54, 28);
		txtpnNome.setEditable(false);
		txtpnNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNome.setText("Nome:");
		editarHospede.add(txtpnNome);

		JTextField textFieldNome = new JTextField();
		textFieldNome.setBounds(147, 74, 420, 28);
		textFieldNome.setFont(new Font("Verdana", Font.PLAIN, 12));
		editarHospede.add(textFieldNome);
		textFieldNome.setColumns(10);

		// Data de nascimento
		JTextPane txtpnNascimento = new JTextPane();
		txtpnNascimento.setBounds(32, 110, 85, 28);
		txtpnNascimento.setText("Nascimento:");
		txtpnNascimento.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascimento.setEditable(false);
		editarHospede.add(txtpnNascimento);

		dateMask.setPlaceholderCharacter(' ');
		JFormattedTextField textFieldData = new JFormattedTextField(dateMask);
		textFieldData.setBounds(147, 110, 117, 28);
		textFieldData.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldData.setColumns(10);
		editarHospede.add(textFieldData);
		
		// CPF
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setBounds(336, 110, 42, 28);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCpf.setEditable(false);
		editarHospede.add(txtpnCpf);

		textFieldCPF = new JFormattedTextField(cpfMask);
		textFieldCPF.setBounds(386, 108, 181, 28);
		textFieldCPF.setEnabled(false);
		textFieldCPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldCPF.setColumns(10);
		editarHospede.add(textFieldCPF);
		
		// Acompanhantes
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 280, 112, 28);
		textPane.setText("Acompanhantes:");
		textPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		textPane.setEditable(false);
		editarHospede.add(textPane);

		JTextArea textAreaAcompanhantes = new JTextArea();
		textAreaAcompanhantes.setLineWrap(true);
		textAreaAcompanhantes.setBounds(147, 280, 420, 73);
		editarHospede.add(textAreaAcompanhantes);
		hospedesAcompanhantes = textAreaAcompanhantes.getText().split(",");
	}
	
	private void hospedeEndereco(JPanel editarHospede) {		
		// Estado
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setBounds(32, 176, 85, 28);
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnEstado.setEditable(false);
		editarHospede.add(txtpnEstado);

		textFieldEstado = new JTextField();
		textFieldEstado.setBounds(147, 176, 221, 28);
		textFieldEstado.setEnabled(false);
		textFieldEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		editarHospede.add(textFieldEstado);

		// Cidade
		JTextPane txtpnCidade = new JTextPane();
		txtpnCidade.setBounds(32, 214, 85, 28);
		txtpnCidade.setText("Cidade:");
		txtpnCidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnCidade.setEditable(false);
		editarHospede.add(txtpnCidade);

		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(147, 210, 221, 28);
		textFieldCidade.setEnabled(false);
		textFieldCidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		editarHospede.add(textFieldCidade);

		// Endereco
		JTextPane txtpnEndereco = new JTextPane();
		txtpnEndereco.setBounds(32, 249, 85, 28);
		txtpnEndereco.setText("Endere\u00E7o:");
		txtpnEndereco.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnEndereco.setEditable(false);
		editarHospede.add(txtpnEndereco);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(147, 244, 339, 28);
		textFieldEndereco.setEnabled(false);
		textFieldEndereco.setFont(new Font("Verdana", Font.PLAIN, 12));
		editarHospede.add(textFieldEndereco);

		// Numero
		JTextPane txtpnN = new JTextPane();
		txtpnN.setBounds(491, 246, 33, 28);
		txtpnN.setText("N\u00BA:");
		txtpnN.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnN.setEditable(false);
		editarHospede.add(txtpnN);

		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(525, 244, 42, 28);
		textFieldNumero.setEnabled(false);
		textFieldNumero.setFont(new Font("Verdana", Font.PLAIN, 12));
		editarHospede.add(textFieldNumero);
		
		// Pais
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setBounds(32, 142, 85, 28);
		txtpnNascionalidade.setText("Pa\u00EDs:");
		txtpnNascionalidade.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnNascionalidade.setEditable(false);
		editarHospede.add(txtpnNascionalidade);
				
		final JComboBox<String> comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Afeganist\u00E3o", "\u00C1frica do Sul", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "B\u00E9lgica", "Belize", "Bermudas", "Bielorr\u00FAssia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Bulg\u00E1ria", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Col\u00F4mbia", "Comores", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dinamarca", "Dom\u00EDnica", "Egipto", "Equador", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Ge\u00F3rgia", "Granada", "Gr\u00E9cia", "Guatemala", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jap\u00E3o", "Jord\u00E2nia", "Kuwait", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "M\u00E9xico", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Pacific Ocean", "Pa\u00EDses Baixos", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vaticano", "Venezuela", "Vietname", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		comboBoxPaises.setBounds(147, 142, 117, 28);
		editarHospede.add(comboBoxPaises);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxPaises.getSelectedItem().equals("Brasil")){
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
}