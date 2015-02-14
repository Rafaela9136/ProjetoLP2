package visual;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTextPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.MaskFormatter;

public class EdicaoServicos extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5388902120933331662L;
	private final static CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private JTextField textField;
	private JComboBox<String> comboBoxHoraI;
	private JComboBox<String> comboBoxHoraF;
	private JComboBox<String> comboBoxCarros;
	private JFormattedTextField formattedTextFieldFinal;
	private JFormattedTextField formattedTextField;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public EdicaoServicos() throws ParseException {
		final MaskFormatter dataMask = new MaskFormatter("##/##/####");
		
		inicializa();
		
		panel = new JPanel();
		panel.setBounds(0, 0, 705, 145);
		add(panel);
		panel.setLayout(layout);
		
		edicaoRestaurante();
		
		edicaoBaba(dataMask);
		
		edicaoCarro(dataMask);
	}

	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(28, 391, 705, 145);
		setLayout(null);
	}

	private void edicaoCarro(final MaskFormatter dataMask) {
		JPanel carro = new JPanel();
		carro.setBackground(Color.WHITE);
		panel.add(carro, "carro");
		carro.setLayout(null);
		
		JTextPane txtpnDataInicial = new JTextPane();
		txtpnDataInicial.setText("Data inicial:");
		txtpnDataInicial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataInicial.setEditable(false);
		txtpnDataInicial.setBounds(0, 12, 72, 24);
		carro.add(txtpnDataInicial);
		
		JTextPane txtpnDataFinal = new JTextPane();
		txtpnDataFinal.setText("Data final:");
		txtpnDataFinal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataFinal.setEditable(false);
		txtpnDataFinal.setBounds(259, 12, 64, 24);
		carro.add(txtpnDataFinal);
		
		formattedTextField = new JFormattedTextField(dataMask);
		formattedTextField.setBounds(88, 15, 139, 20);
		carro.add(formattedTextField);
		
		formattedTextFieldFinal = new JFormattedTextField(dataMask);
		formattedTextFieldFinal.setBounds(345, 15, 139, 20);
		carro.add(formattedTextFieldFinal);
		
		JTextPane txtpnTipoDeCarro = new JTextPane();
		txtpnTipoDeCarro.setText("Tipo de carro:");
		txtpnTipoDeCarro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeCarro.setEditable(false);
		txtpnTipoDeCarro.setBounds(0, 47, 89, 24);
		carro.add(txtpnTipoDeCarro);
		
		comboBoxCarros = new JComboBox<String>();
		comboBoxCarros.setModel(new DefaultComboBoxModel<String>(new String[] {"Executivo", "Luxo"}));
		comboBoxCarros.setBounds(90, 47, 137, 24);
		carro.add(comboBoxCarros);
	}

	private void edicaoBaba(final MaskFormatter dataMask) {
		JPanel baba = new JPanel();
		baba.setBackground(Color.WHITE);
		panel.add(baba, "baba");
		baba.setLayout(null);
		
		JTextPane txtpnDataInicial = new JTextPane();
		txtpnDataInicial.setText("Data inicial:");
		txtpnDataInicial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataInicial.setEditable(false);
		txtpnDataInicial.setBounds(0, 12, 82, 23);
		baba.add(txtpnDataInicial);
		
		JTextPane txtpnHoraInicial = new JTextPane();
		txtpnHoraInicial.setText("Hora inicial:");
		txtpnHoraInicial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnHoraInicial.setEditable(false);
		txtpnHoraInicial.setBounds(0, 47, 82, 23);
		baba.add(txtpnHoraInicial);
		
		JTextPane txtpnDataFinal = new JTextPane();
		txtpnDataFinal.setText("Data final:");
		txtpnDataFinal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnDataFinal.setEditable(false);
		txtpnDataFinal.setBounds(259, 12, 82, 23);
		baba.add(txtpnDataFinal);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Hora inicial:");
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textPane.setEditable(false);
		textPane.setBounds(259, 47, 82, 23);
		baba.add(textPane);
		
		formattedTextField = new JFormattedTextField(dataMask);
		formattedTextField.setBounds(88, 15, 139, 20);
		baba.add(formattedTextField);
		
		formattedTextFieldFinal = new JFormattedTextField(dataMask);
		formattedTextFieldFinal.setBounds(345, 15, 139, 20);
		baba.add(formattedTextFieldFinal);
		
		comboBoxHoraI = new JComboBox<String>();
		comboBoxHoraI.setModel(new DefaultComboBoxModel<String>(new String[] {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"}));
		comboBoxHoraI.setBounds(88, 47, 139, 20);
		baba.add(comboBoxHoraI);
		
		comboBoxHoraF = new JComboBox<String>();
		comboBoxHoraF.setModel(new DefaultComboBoxModel<String>(new String[] {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"}));
		comboBoxHoraF.setBounds(345, 47, 139, 20);
		baba.add(comboBoxHoraF);
	}

	private void edicaoRestaurante() {
		JPanel restaurante = new JPanel();
		restaurante.setBackground(Color.WHITE);
		panel.add(restaurante, "restaurante");
		restaurante.setLayout(null);
		
		JTextPane txtpnTotalDaConta = new JTextPane();
		txtpnTotalDaConta.setEditable(false);
		txtpnTotalDaConta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTotalDaConta.setText("Total da conta:");
		txtpnTotalDaConta.setBounds(0, 11, 99, 23);
		restaurante.add(txtpnTotalDaConta);
		
		textField = new JTextField();
		textField.setBounds(101, 14, 140, 20);
		restaurante.add(textField);
		textField.setColumns(10);
	}
	
	// metodo para selecionar a tela a que aparece
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}
}
