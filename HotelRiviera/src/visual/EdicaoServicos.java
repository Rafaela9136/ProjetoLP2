package visual;

import hotel.Baba;
import hotel.Carro;
import hotel.Conector;
import hotel.ContaRestaurante;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTextPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.MaskFormatter;

import excecoes.DataInvalidaException;
import excecoes.ValorNegativoException;

import javax.swing.JRadioButton;

public class EdicaoServicos extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5388902120933331662L;
	private final static CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private static JTextField textField;
	private static JComboBox<String> comboBoxHoraI;
	private static JComboBox<String> comboBoxHoraF;
	private static JComboBox<String> comboBoxCarros;
	private static JFormattedTextField formattedTextFieldFinal;
	private static JFormattedTextField formattedTextField;
	private static JFormattedTextField formattedTextFieldDataI;
	private static JFormattedTextField formattedTextFieldDataF;
	private static JRadioButton rdbtnTanqueCheio;
	private static JRadioButton rdbtnSeguro;

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
		layout.show(panel, "vazio");
		
		edicaoRestaurante();
		
		edicaoBaba(dataMask);
		
		edicaoCarro(dataMask);
		
//		edicaoFrigobar();
		
		// painel que permanece
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
	}

	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(28, 391, 705, 145);
		setLayout(null);
	}
	
//	private void edicaoFrigobar() {
//		JPanel frigobar = new JPanel();
//		frigobar.setBackground(Color.WHITE);
//		panel.add(frigobar, "frigobar");
//		frigobar.setLayout(null);
//		
//		JTextPane txtpnTotalDaConta = new JTextPane();
//		txtpnTotalDaConta.setEditable(false);
//		txtpnTotalDaConta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
//		txtpnTotalDaConta.setText("Total da conta:");
//		txtpnTotalDaConta.setBounds(0, 11, 101, 23);
//		frigobar.add(txtpnTotalDaConta);
//		
//		textField = new JTextField();
//		textField.setBounds(107, 14, 134, 21);
//		frigobar.add(textField);
//		textField.setColumns(10);
//	}

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
		txtpnDataFinal.setBounds(262, 12, 64, 24);
		carro.add(txtpnDataFinal);
		
		formattedTextFieldDataI = new JFormattedTextField(dataMask);
		formattedTextFieldDataI.setBounds(107, 15, 139, 20);
		carro.add(formattedTextFieldDataI);
		
		formattedTextFieldDataF = new JFormattedTextField(dataMask);
		formattedTextFieldDataF.setBounds(354, 15, 139, 20);
		carro.add(formattedTextFieldDataF);
		
		JTextPane txtpnTipoDeCarro = new JTextPane();
		txtpnTipoDeCarro.setText("Tipo de carro:");
		txtpnTipoDeCarro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeCarro.setEditable(false);
		txtpnTipoDeCarro.setBounds(0, 47, 89, 24);
		carro.add(txtpnTipoDeCarro);
		
		comboBoxCarros = new JComboBox<String>();
		comboBoxCarros.setModel(new DefaultComboBoxModel<String>(new String[] {"Executivo", "Luxo"}));
		comboBoxCarros.setBounds(107, 47, 137, 24);
		carro.add(comboBoxCarros);
		
		rdbtnTanqueCheio = new JRadioButton("Tanque cheio");
		rdbtnTanqueCheio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdbtnTanqueCheio.setBounds(107, 84, 121, 24);
		carro.add(rdbtnTanqueCheio);
		
		rdbtnSeguro = new JRadioButton("Seguro");
		rdbtnSeguro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdbtnSeguro.setBounds(262, 84, 121, 24);
		carro.add(rdbtnSeguro);
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
		txtpnDataFinal.setBounds(262, 12, 82, 23);
		baba.add(txtpnDataFinal);
		
		JTextPane txtpnHoraFinal = new JTextPane();
		txtpnHoraFinal.setText("Hora final:");
		txtpnHoraFinal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnHoraFinal.setEditable(false);
		txtpnHoraFinal.setBounds(262, 47, 82, 23);
		baba.add(txtpnHoraFinal);
		
		formattedTextField = new JFormattedTextField(dataMask);
		formattedTextField.setBounds(107, 15, 139, 20);
		baba.add(formattedTextField);
		
		formattedTextFieldFinal = new JFormattedTextField(dataMask);
		formattedTextFieldFinal.setBounds(354, 15, 139, 20);
		baba.add(formattedTextFieldFinal);
		
		comboBoxHoraI = new JComboBox<String>();
		comboBoxHoraI.setModel(new DefaultComboBoxModel<String>(new String[] {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"}));
		comboBoxHoraI.setBounds(107, 47, 139, 20);
		baba.add(comboBoxHoraI);
		
		comboBoxHoraF = new JComboBox<String>();
		comboBoxHoraF.setModel(new DefaultComboBoxModel<String>(new String[] {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"}));
		comboBoxHoraF.setBounds(354, 47, 139, 20);
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
		txtpnTotalDaConta.setBounds(0, 11, 101, 23);
		restaurante.add(txtpnTotalDaConta);
		
		textField = new JTextField();
		textField.setBounds(107, 14, 140, 20);
		restaurante.add(textField);
		textField.setColumns(10);
	}
	
	static ContaRestaurante geraContaRestaurante(){
		
		ContaRestaurante conta = null;
		try {
			conta = new ContaRestaurante(Double.parseDouble(textField.getText()));
		} catch (NumberFormatException | ValorNegativoException e) {
			JOptionPane.showMessageDialog(null,"Algo está errado. Verifique o valor da conta!");
		}
		return conta;
	}
	
//	static double geraContaFrigobar(){
//		Double conta = Double.valueOf(textField.getText());
//		return conta;
//	}
	
	static Baba contrataBaba(){
		
		Baba baba = null;
		try {
			baba = new Baba(Conector.transformaDataHora(formattedTextField.getText(),(String) comboBoxHoraI.getSelectedItem()),
					Conector.transformaDataHora(formattedTextFieldFinal.getText(), (String) comboBoxHoraF.getSelectedItem()));
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Algo está errado. Verifique os campos!");
		} catch (DataInvalidaException e) {
			JOptionPane.showMessageDialog(null,"Algo está errado. Verifique as datas!");
		}
		return baba;
	}
	
	static Carro alugaCarro(){
		
		Carro carro = null;
		try {
			carro = new Carro(Conector.selecionaTipoCarro((String) comboBoxCarros.getSelectedItem()), Conector.transformaData(formattedTextFieldDataI.getText()),
					Conector.transformaData(formattedTextFieldDataF.getText()), rdbtnTanqueCheio.isSelected(), rdbtnSeguro.isSelected());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Algo está errado. Verifique os campos!");
		} catch (DataInvalidaException e) {
			JOptionPane.showMessageDialog(null,"Algo está errado. Verifique as datas!");
		}
		return carro;
	}
	
	// metodo para selecionar a tela a que aparece
	static void selecionaTela(String nomeDaTela){
		layout.show(panel, nomeDaTela);
	}
}
