package visual;

import hotel.*;
import excecoes.*;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class AtualizacaoContrato extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6722604404024761631L;
	private final CardLayout layout = new CardLayout();
	
	private JPanel panel;
	private JComboBox<String> comboBoxNota;
	private JTextArea textArea;
	private JTable tabela;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	
	// Variaveis para criar objetos
	private String[][] dados;
	private String[] colunas;
	
	
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AtualizacaoContrato() throws ParseException {
		inicializa();
		
		barraDeTarefas();
		
		informacoesHospede();
	}
	
	private void inicializa() {
		setBackground(Color.WHITE);
		setBounds(228, 12, 764, 612);
	}

	private void barraDeTarefas() {
		setLayout(null);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 764, 32);
		add(toolBar);
		
		JButton btnEditarDados = new JButton("   Editar dados do hospede titular   ");
		btnEditarDados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarDadosHospede");
			}
		});
		toolBar.add(btnEditarDados);
		
		JButton btnEditarServicos = new JButton("   Adicionar e remover servicos   ");
		btnEditarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarServicos");
			}
		});
		toolBar.add(btnEditarServicos);
		
		JButton btnFecharContrato = new JButton("   Finalizar contrato   ");
		btnFecharContrato.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFecharContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "fecharContrato");
			}
		});
		toolBar.add(btnFecharContrato);
	}

	private void informacoesHospede() throws ParseException {
		panel = new JPanel();
		panel.setBounds(0, 30, 764, 582);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(layout);
		
		JPanel editarDadosHospede = new JPanel();
		editarDadosHospede.setBackground(Color.WHITE);
		panel.add(editarDadosHospede, "editarDadosHospede");
		
		JPanel editarServicos = new JPanel();
		editarServicos.setBackground(Color.WHITE);
		panel.add(editarServicos, "editarServicos");
		editarServicos.setLayout(null);
		
		JTextPane txtpnServicosDoContrato = new JTextPane();
		txtpnServicosDoContrato.setEditable(false);
		txtpnServicosDoContrato.setText("Servicos do contrato");
		txtpnServicosDoContrato.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnServicosDoContrato.setBounds(28, 24, 172, 32);
		editarServicos.add(txtpnServicosDoContrato);
		
		desenhaTabela(editarServicos);
		
		JButton btnAtualizarTabela = new JButton("Atualizar tabela");
		btnAtualizarTabela.setBounds(28, 273, 135, 25);
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		editarServicos.add(btnAtualizarTabela);
		
		JButton btnCancelarServico = new JButton("Cancelar servico");
		btnCancelarServico.setBounds(598, 273, 135, 25);
		btnCancelarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				Acoes.getContratoPesquisado().removeServico(selecionaServico(linha));
			}
		});
		editarServicos.add(btnCancelarServico);
		
		JTextPane txtpnAdicionarServicos = new JTextPane();
		txtpnAdicionarServicos.setEditable(false);
		txtpnAdicionarServicos.setText("Adicionar servicos");
		txtpnAdicionarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnAdicionarServicos.setBounds(28, 320, 172, 32);
		editarServicos.add(txtpnAdicionarServicos);
		
		JTextPane txtpnTipoDeServico = new JTextPane();
		txtpnTipoDeServico.setEditable(false);
		txtpnTipoDeServico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeServico.setText("Tipo de servico:");
		txtpnTipoDeServico.setBounds(30, 357, 104, 22);
		editarServicos.add(txtpnTipoDeServico);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Baba", "Carro", "Restaurante"}));
		comboBox.setBounds(140, 359, 141, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Baba"))
					EdicaoServicos.selecionaTela("baba");
				if(comboBox.getSelectedItem().equals("Restaurante"))
					EdicaoServicos.selecionaTela("restaurante");
				if(comboBox.getSelectedItem().equals("Carro"))
					EdicaoServicos.selecionaTela("carro");
			}
		});
		editarServicos.add(comboBox);
		
		EdicaoServicos edicaoServico = new EdicaoServicos();
		editarServicos.add(edicaoServico);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setBounds(598, 547, 135, 25);
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaObjetosServicos();
			}
		});
		editarServicos.add(btnConfirmar_1);
		
		fechaContrato();
	}
	
	private void criaObjetosServicos(){
		
	}

	private void desenhaTabela(JPanel editarServicos) {
		colunas = new String[]{"Servico","Custo"};
		dados = new String[25][2];
		 
		tabela = new JTable();
		tabela.setRowSelectionAllowed(true);
		model = new DefaultTableModel(dados , colunas );
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(28, 60, 705, 200);
		scroll.setViewportView(tabela);
		editarServicos.add(scroll);
	}

	private void atualizaTabela() {
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			if(Acoes.getContratoPesquisado().getServicos().get(i) instanceof Quarto)
				dados[i][0] = "Quarto";
			if(Acoes.getContratoPesquisado().getServicos().get(i) instanceof Carro)
				dados[i][0] = "Quarto";
			if(Acoes.getContratoPesquisado().getServicos().get(i) instanceof Baba)
				dados[i][0] = "Baba";
			if(Acoes.getContratoPesquisado().getServicos().get(i)instanceof ContaRestaurante)
				dados[i][0] = "Restaurante";
			dados[i][1] = String.valueOf(Acoes.getContratoPesquisado().getServicos().get(i).getPreco());
		}
		
		model = new DefaultTableModel(dados , colunas );
		tabela.setModel(model);
	}
	
	private Servico selecionaServico(int linha) {
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			if(i == linha){
				return Acoes.getContratoPesquisado().getServicos().get(i);
			}
		}
		return null;
	}

	private void fechaContrato() {
		JPanel fecharContrato = new JPanel();
		fecharContrato.setBackground(Color.WHITE);
		panel.add(fecharContrato, "fecharContrato");
		fecharContrato.setLayout(null);
		
		JTextPane txtpnOpiniaoDoHospede = new JTextPane();
		txtpnOpiniaoDoHospede.setEditable(false);
		txtpnOpiniaoDoHospede.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnOpiniaoDoHospede.setText("Opiniao do hospede");
		txtpnOpiniaoDoHospede.setBounds(28, 24, 172, 32);
		fecharContrato.add(txtpnOpiniaoDoHospede);
		
		JTextPane txtpnComentario = new JTextPane();
		txtpnComentario.setEditable(false);
		txtpnComentario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnComentario.setText("Comentario:");
		txtpnComentario.setBounds(28, 68, 138, 22);
		fecharContrato.add(txtpnComentario);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(UIManager.getColor("CheckBox.background"));
		textArea.setBounds(28, 93, 705, 145);
		fecharContrato.add(textArea);
	
		JTextPane txtpnAvaliacao = new JTextPane();
		txtpnAvaliacao.setEditable(false);
		txtpnAvaliacao.setText("Avaliacao:");
		txtpnAvaliacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnAvaliacao.setBounds(526, 254, 81, 22);
		fecharContrato.add(txtpnAvaliacao);
		
		comboBoxNota = new JComboBox<String>();
		comboBoxNota.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxNota.setBounds(610, 254, 121, 22);
		fecharContrato.add(comboBoxNota);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(600, 544, 135, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeContrato();
			}
		});
		fecharContrato.add(btnConfirmar);
	}
	
	private void removeContrato() {
		AvisoErro erro = new AvisoErro();
		try {					
			Acoes.getContratoPesquisado().inicializaOpiniao(Conector.trasformaNota((String) comboBoxNota.getSelectedItem()), textArea.getText());
		} catch (NullPointerException e1) {
			erro.setVisible(true);
		} catch (NotaInvalidaException e1) {
			erro.setVisible(true);
		} catch (EstouroDeCaracteresException e1) {
			erro.setVisible(true);
		} catch (ComentarioVazioException e1) {
			erro.setVisible(true);
		}
		
		try {
			Acoes.getContratoPesquisado().setIsAberto(false);
		} catch (ContratoFechadoException e1) {
			erro.setVisible(true);
		} catch (ContratoSemOpiniaoException e1) {
			erro.setVisible(true);
		}
		
		if(hotel.Hotel.removeContrato(Acoes.getContratoPesquisado())){
			AvisoSucesso sucesso = new AvisoSucesso();
			sucesso.setVisible(true);
		}
	}

}
