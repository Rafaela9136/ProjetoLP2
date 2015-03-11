package visual;

import hotel.*;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import excecoes.LoginExistenteException;
import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

public class Funcionarios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1910892179168332113L;
	private static final CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JTextField textFieldPesquisa;
	private JPasswordField passwordField;
	private JComboBox<String> comboBoxFuncionario;
	private DefaultTableModel model;
	private JTable tabela;
	private String[] colunas;
	
	//**
	private static String contratosListados;
	private static Conta contaPesquisada;
	
	/**
	 * Create the panel.
	 */
	public Funcionarios() {
		inicializa();
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		panel.setBounds(0, 0, 764, 612);
		add(panel);
		panel.setLayout(layout);
		
		panelCadastro();
		
		panelPesquisa();
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		// Cria painel de atualizacao do contrato
		AtualizacaoConta atualizaConta = new AtualizacaoConta();
		panel.add(atualizaConta, "atualizaConta");
	}
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
		setLayout(null);
	}

	private void panelPesquisa() {
		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBackground(Color.WHITE);
		panel.add(panelPesquisa, "pesquisa");
		panelPesquisa.setLayout(null);
		
		JTextPane textPanePesquisaContas = new JTextPane();
		textPanePesquisaContas.setText("Pesquisa de contas");
		textPanePesquisaContas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textPanePesquisaContas.setEditable(false);
		textPanePesquisaContas.setBounds(46, 24, 131, 27);
		panelPesquisa.add(textPanePesquisaContas);

		JTextPane txtLoginSenha = new JTextPane();
		txtLoginSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtLoginSenha.setText("Nome ou login:");
		txtLoginSenha.setBounds(47, 77, 97, 27);
		panelPesquisa.add(txtLoginSenha);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(154, 80, 581, 20);
		panelPesquisa.add(textFieldPesquisa);

		JButton btnAtualizarTabela = new JButton("Atualizar tabela");
		btnAtualizarTabela.setBounds(47, 115, 135, 25);
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contratosListados = "atualiza";
				atualizaTabela();
		}});
		panelPesquisa.add(btnAtualizarTabela);

		JButton btnPesquisarContrato = new JButton("Pesquisar");
		btnPesquisarContrato.setBounds(600, 113, 135, 25);
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contratosListados = "pesquisa";
				if(Main.getHotel().pesquisaConta(textFieldPesquisa.getText()) == null)
					JOptionPane.showMessageDialog(null,"Conta nao encontrada!");
				else
					pesquisaTabela();
			}
		});
		panelPesquisa.add(btnPesquisarContrato);

		geraTabela(panelPesquisa);

		JButton btnAtualizarConta = new JButton("Atualizar conta");
		btnAtualizarConta.setBounds(600, 562, 135, 25);
		btnAtualizarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				if(contratosListados.equals("atualiza")){
					selecionaConta(linha, Main.getHotel().getContasHotel());
				}
				if(contratosListados.equals("pesquisa")){
					contaPesquisada = Main.getHotel().pesquisaConta(textFieldPesquisa.getText());
				}
				if(getContaPesquisada() == null)
					JOptionPane.showMessageDialog(null,"Conta nao encontrada!");
				else if(getContaPesquisada() != null){
					layout.show(panel, "atualizaConta");
					AtualizacaoConta.setaInfoGerais();
				}
			}
		});
		panelPesquisa.add(btnAtualizarConta);
	}
	
	private void selecionaConta(int linha, List<Conta> contas) {
		for (int i = 0; i < contas.size(); i++) {
			if (i == linha) {
				contaPesquisada = contas.get(i);
			}
		}
	}

	static Conta getContaPesquisada() {
		return contaPesquisada;
	}
	
	private void geraTabela(JPanel panelPesquisar) {
		colunas = new String[] { "Nome", "Login", "Tipo de funcionario" };

		tabela = new JTable();
		tabela.setRowSelectionAllowed(true);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel(new String[0][2], colunas);
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(50, 150, 685, 391);
		scroll.setViewportView(tabela);
		panelPesquisar.add(scroll);
	}
	
	private void atualizaTabela() {
		String[][] dados = new String[Main.getHotel().getContasHotel().size()][3];
		for (int i = 0; i < Main.getHotel().getContasHotel().size(); i++) {
			dados[i][0] = Main.getHotel().getContasHotel().get(i).getNomeCompleto();
			dados[i][1] = Main.getHotel().getContasHotel().get(i).getLogin();
			dados[i][2] = Main.getHotel().getContasHotel().get(i).getTipo().getNome();
		}// for

		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);
	}
	
	private void pesquisaTabela(){
		String[][] dados = new String[1][3];
		dados[0][0] =  Main.getHotel().pesquisaConta(textFieldPesquisa.getText()).getNomeCompleto();
		dados[0][1] =  Main.getHotel().pesquisaConta(textFieldPesquisa.getText()).getLogin();
		dados[0][2] =  Main.getHotel().pesquisaConta(textFieldPesquisa.getText()).getTipo().getNome();
		
		model = new DefaultTableModel(dados, colunas);
		tabela.setModel(model);
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
		txtpnTipoDeFuncionario.setText("Tipo de funcionário:");
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
		
		comboBoxFuncionario = new JComboBox<String>();
		String funcionarios[] = new String[TipoFuncionario.values().length];
		for (int i = 0; i < TipoFuncionario.values().length; i++) {
			funcionarios[i] = TipoFuncionario.values()[i].getNome();
		}// for
		comboBoxFuncionario.setModel(new DefaultComboBoxModel<String>(funcionarios));
		comboBoxFuncionario.setBounds(179, 112, 144, 22);
		panelCadastro.add(comboBoxFuncionario);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(179, 152, 144, 22);
		panelCadastro.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 190, 144, 22);
		panelCadastro.add(passwordField);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(600, 574, 135, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta conta = null;
				try {
					conta = new Conta(textFieldLogin.getText(), String.valueOf(passwordField.getPassword()), textFieldNome.getText(),
							Conector.selecionaTipoFuncionario(comboBoxFuncionario.getSelectedItem()));
					
					Main.getHotel().adicionaConta(conta);
				} catch (LoginInvalidoException e1) {
					JOptionPane.showMessageDialog(null,"Login inválido!");
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null,"Algo está errado. Verifique os campos!");
				} catch (SenhaInvalidaException e1) {
					JOptionPane.showMessageDialog(null,"Senha inválida!");
				} catch (NomeCompletoInvalidoException e1) {
					JOptionPane.showMessageDialog(null,"Algo esta errado. Nome inválido!");
				} catch (LoginExistenteException e1) {
					JOptionPane.showMessageDialog(null,"Login existente!");
				} finally {
					if(conta != null)
						panelCadastro();
				}
				
				layout.show(panel, "vazio");
				JOptionPane.showMessageDialog(null,"Conta criada com sucesso!");
			}
		});
		panelCadastro.add(btnConfirmar);
	}
	
	static void selecionaTela(String tela){
		layout.show(panel, tela);
	}
}
