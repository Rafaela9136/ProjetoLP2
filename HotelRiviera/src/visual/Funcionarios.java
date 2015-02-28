package visual;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Funcionarios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1910892179168332113L;
	private static final CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private JTextField textFieldNome;
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Create the panel.
	 */
	public Funcionarios() {
		inicializa();
	}
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel.setBounds(0, 0, 764, 612);
		add(panel);
		panel.setLayout(layout);
		
		panelCadastro();
		
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBackground(Color.WHITE);
		panel.add(panelPesquisa, "pesquisa");
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
	}

	private void panelCadastro() {
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBackground(Color.WHITE);
		panel.add(panelCadastro, "cadastro");
		panelCadastro.setLayout(null);
		
		JTextPane txtpnDadosDoHospede = new JTextPane();
		txtpnDadosDoHospede.setEditable(false);
		txtpnDadosDoHospede.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnDadosDoHospede.setText("Dados do cadastro");
		txtpnDadosDoHospede.setBounds(46, 24, 151, 27);
		panelCadastro.add(txtpnDadosDoHospede);
		
		JTextPane txtpnNomeCompleto = new JTextPane();
		txtpnNomeCompleto.setEditable(false);
		txtpnNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNomeCompleto.setText("Nome completo:");
		txtpnNomeCompleto.setBounds(46, 70, 121, 27);
		panelCadastro.add(txtpnNomeCompleto);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(179, 71, 530, 22);
		panelCadastro.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JTextPane txtpnTipoDeFuncionario = new JTextPane();
		txtpnTipoDeFuncionario.setText("Tipo de funcionario:");
		txtpnTipoDeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeFuncionario.setEditable(false);
		txtpnTipoDeFuncionario.setBounds(46, 112, 129, 27);
		panelCadastro.add(txtpnTipoDeFuncionario);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setText("Login:");
		txtpnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnLogin.setEditable(false);
		txtpnLogin.setBounds(46, 152, 112, 27);
		panelCadastro.add(txtpnLogin);
		
		JTextPane txtpnSenha = new JTextPane();
		txtpnSenha.setText("Senha:");
		txtpnSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnSenha.setEditable(false);
		txtpnSenha.setBounds(46, 190, 112, 27);
		panelCadastro.add(txtpnSenha);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Gerente", "Outro '-'"}));
		comboBox.setBounds(179, 112, 144, 22);
		panelCadastro.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(179, 152, 144, 22);
		panelCadastro.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 190, 144, 22);
		panelCadastro.add(textField_1);
	}
	
	static void selecionaTela(String tela){
		layout.show(panel, tela);
	}
}
