package visual;

import hotel.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import excecoes.ListaVaziaException;
import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

public class AtualizacaoConta extends JPanel {

	/**
	 */
	private static final long serialVersionUID = 4952634473570361300L;
	private static CardLayout layout = new CardLayout();
	
	private static JTextArea textArea;
	private static JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JComboBox<String> comboBoxFuncionario;

	/**
	 * Create the panel.
	 */
	public AtualizacaoConta() {
		inicializa();
	}
	
	private void inicializa() {
		setBorder(new LineBorder(new Color(51, 0, 0), 3));
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 764, 32);
		add(toolBar);
		
		JButton btnInformacoesGerais = new JButton(" Informações gerais ");
		btnInformacoesGerais.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnInformacoesGerais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "infoGerais");
				setaInfoGerais();
			}
		});
		toolBar.add(btnInformacoesGerais);
		
		JButton btnEditarDadosDa = new JButton(" Editar dados da conta ");
		btnEditarDadosDa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarDadosDa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editar");
				setaDados();
			}
		});
		toolBar.add(btnEditarDadosDa);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(2, 32, 760, 577);
		add(panel);
		panel.setLayout(layout);
		
		panelInfoGerais();
		
		panelEditarDados();
	}

	private void panelEditarDados() {
		JPanel editarDados = new JPanel();
		editarDados.setBackground(Color.WHITE);
		panel.add(editarDados, "editar");
		editarDados.setLayout(null);
		
		JTextPane txtpnNomeCompleto = new JTextPane();
		txtpnNomeCompleto.setEditable(false);
		txtpnNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNomeCompleto.setText("Nome completo:");
		txtpnNomeCompleto.setBounds(47, 38, 104, 24);
		editarDados.add(txtpnNomeCompleto);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(177, 38, 541, 22);
		editarDados.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JTextPane txtpnTipoDeFuncionario = new JTextPane();
		txtpnTipoDeFuncionario.setText("Tipo de funcionário:");
		txtpnTipoDeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeFuncionario.setEditable(false);
		txtpnTipoDeFuncionario.setBounds(46, 85, 123, 24);
		editarDados.add(txtpnTipoDeFuncionario);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setText("Login:");
		txtpnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnLogin.setEditable(false);
		txtpnLogin.setBounds(47, 125, 41, 24);
		editarDados.add(txtpnLogin);
		
		JTextPane txtpnSenha = new JTextPane();
		txtpnSenha.setText("Senha:");
		txtpnSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnSenha.setEditable(false);
		txtpnSenha.setBounds(47, 161, 44, 24);
		editarDados.add(txtpnSenha);
		
		comboBoxFuncionario = new JComboBox<String>();
		String funcionarios[] = new String[TipoFuncionario.values().length];
		for (int i = 0; i < TipoFuncionario.values().length; i++) {
			funcionarios[i] = TipoFuncionario.values()[i].getNome();
		}// for
		comboBoxFuncionario.setModel(new DefaultComboBoxModel<String>(funcionarios));
		comboBoxFuncionario.setBounds(177, 85, 147, 22);
		editarDados.add(comboBoxFuncionario);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(177, 127, 147, 22);
		editarDados.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 163, 147, 22);
		editarDados.add(passwordField);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(618, 546, 135, 24);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarDados();
				JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!");
			}
		});
		editarDados.add(btnConfirmar);
		
		JButton btnExcluirConta = new JButton("Excluir conta");
		btnExcluirConta.setBounds(16, 546, 135, 24);
		btnExcluirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Deseja excluir a conta?")==JOptionPane.OK_OPTION){
					try {
						Main.getHotel().removeConta(textFieldLogin.getText());
					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(null,"Algo está errado!");
					} catch (ListaVaziaException e1) {
						JOptionPane.showMessageDialog(null,"Algo está errado. Verifique as contas existentes!");
					}
					
					JOptionPane.showMessageDialog(null,"Conta excluída com sucesso!");
					Funcionarios.selecionaTela("pesquisa");
				}
			}
		});
		editarDados.add(btnExcluirConta);
	}
	
	private void setaDados() {
		textFieldNome.setText(Funcionarios.getContaPesquisada().getNomeCompleto());
		comboBoxFuncionario.setSelectedItem(Funcionarios.getContaPesquisada().getTipo());
		textFieldLogin.setText(Funcionarios.getContaPesquisada().getLogin());
		passwordField.setText(Funcionarios.getContaPesquisada().getSenha());
	}
	
	private void editarDados(){
		try {
			Funcionarios.getContaPesquisada().setNomeCompleto(textFieldNome.getText());
		} catch (NomeCompletoInvalidoException e) {
			JOptionPane.showMessageDialog(null,"Nome inválido!");
		}
		try {
			Funcionarios.getContaPesquisada().setLogin(textFieldLogin.getText());
		} catch (LoginInvalidoException e) {
			JOptionPane.showMessageDialog(null,"Login inválido!");
		}
		try {
			Funcionarios.getContaPesquisada().setSenha(String.valueOf(passwordField.getPassword()));
		} catch (SenhaInvalidaException e) {
			JOptionPane.showMessageDialog(null,"Senha inválida!");
		}
		Funcionarios.getContaPesquisada().setTipo(Conector.selecionaTipoFuncionario(comboBoxFuncionario.getSelectedItem()));
	}

	private void panelInfoGerais() {
		JPanel infoGerais = new JPanel();
		infoGerais.setBackground(Color.WHITE);
		panel.add(infoGerais, "infoGerais");
		infoGerais.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBounds(47, 24, 670, 244);
		infoGerais.add(textArea);
	}
	
	static void setaInfoGerais(){
		textArea.setText(Funcionarios.getContaPesquisada().toString());
	}
}
