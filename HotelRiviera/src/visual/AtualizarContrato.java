package visual;

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
		
		JPanel editarHospede = new JPanel();
		editarHospede.setBackground(Color.WHITE);
		panel.add(editarHospede, "editarHospede");
		editarHospede.setLayout(null);
		
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

	static void selecionaTela(String tela){
		layout.show(panel, tela);
	}
}
