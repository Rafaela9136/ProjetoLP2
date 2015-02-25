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

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.border.LineBorder;

public class AtualizacaoContrato extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6722604404024761631L;
	private static final CardLayout layout = new CardLayout();
	
	private static JPanel panel;
	private static JTextArea textAreaDadosHospede;
	private JComboBox<String> comboBoxNota;
	private JTextArea textArea;
	private JTable tabela;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JTextField textFieldLogradouro;
	private JComboBox<String> comboBoxEstados;
	private JComboBox<String> comboBoxPaises;
	private JTextField textFieldCidade;
	private JTextField textFieldNumero;
	private JTextArea textAreaAcompanhantes;
	private JFormattedTextField formattedTextFieldCartao;
	private JFormattedTextField formattedTextFieldCPF;
	private JTextField textFieldNome;
	
	// Variaveis para criar objetos
	private String[] colunas;	
	private JTextField textFieldTotal;
	
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AtualizacaoContrato() throws ParseException {
		setBorder(new LineBorder(new Color(51, 0, 0), 3));
		inicializa();
		
		barraDeTarefas();
		
		informacoesHospede();
		
		editarServicos();
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
		
		JButton btnEditarDados = new JButton("  Editar dados do hospede titular ");
		btnEditarDados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarDadosHospede");
				setaDados();
			}
		});
		
		JButton btnInformacoesGerais = new JButton(" Informacoes gerais ");
		btnInformacoesGerais.setFont(new Font("Dialog", Font.PLAIN, 14));
		toolBar.add(btnInformacoesGerais);
		btnInformacoesGerais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "infoGerais");
			}
		});
		toolBar.add(btnEditarDados);
		
		JButton btnEditarServicos = new JButton(" Adicionar e remover servicos ");
		btnEditarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEditarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel, "editarServicos");
				EdicaoServicos.selecionaTela("vazio");
			}
		});
		toolBar.add(btnEditarServicos);
		
		JButton btnFecharContrato = new JButton(" Finalizar contrato ");
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
		editarDadosHospede.setLayout(null);
		
		JPanel infoGerais = new JPanel();
		infoGerais.setBackground(Color.WHITE);
		panel.add(infoGerais, "infoGerais");
		infoGerais.setLayout(null);
		
		textAreaDadosHospede = new JTextArea();
		textAreaDadosHospede.setBackground(Color.WHITE);
		textAreaDadosHospede.setEnabled(false);
		textAreaDadosHospede.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaDadosHospede.setEditable(false);
		textAreaDadosHospede.setBounds(47, 24, 681, 245);
		infoGerais.add(textAreaDadosHospede);
		
		JTextArea textAreaDadosServicos = new JTextArea();
		textAreaDadosServicos.setBounds(47, 282, 681, 264);
		infoGerais.add(textAreaDadosServicos);
		layout.show(panel, "infoGerais");
		
		hospedeDadosPrincipais(editarDadosHospede);
		
		hospedeEndereco(editarDadosHospede);
		
		JButton btnConfirma_2 = new JButton("Confirmar");
		btnConfirma_2.setBounds(618, 546, 135, 25);
		btnConfirma_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();
				
				try {
					atualizaDados();
					
					AvisoSucesso sucesso = new AvisoSucesso();
					sucesso.setVisible(true);
					
				} catch (NullPointerException e1) {
					erro.setVisible(true);
				} catch (StringVaziaException e1) {
					erro.setVisible(true);
				} catch (StringInvalidaException e1) {
					erro.setVisible(true);
				} catch (NumeroInvalidoException e1) {
					erro.setVisible(true);
				} catch (CPFInvalidoException e1) {
					erro.setVisible(true);
				} catch (CartaoInvalidoException e1) {
					erro.setVisible(true);
				}
			}
		});
		editarDadosHospede.add(btnConfirma_2);
	}

	static void setaInfoGerais() {
		textAreaDadosHospede.setText(Acoes.getContratoPesquisado().getHospedeTitular().toString());
	}

	private void editarServicos() throws ParseException {
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
		btnAtualizarTabela.setBounds(28, 288, 135, 25);
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		editarServicos.add(btnAtualizarTabela);
		
		JButton btnCancelarServico = new JButton("Cancelar servico");
		btnCancelarServico.setBounds(598, 288, 135, 25);
		btnCancelarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();
				AvisoErro erro = new AvisoErro();
				try {
					if(removeServico(linha)){
						AvisoSucesso sucesso = new AvisoSucesso();
						sucesso.setVisible(true);
					} else {
						erro.setVisible(true);
					}
				} catch (RemocaoInvalidaException e1) {
					erro.setVisible(true);
				}
			}
		});
		editarServicos.add(btnCancelarServico);
		
		JTextPane txtpnAdicionarServicos = new JTextPane();
		txtpnAdicionarServicos.setEditable(false);
		txtpnAdicionarServicos.setText("Adicionar servicos");
		txtpnAdicionarServicos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnAdicionarServicos.setBounds(28, 335, 172, 32);
		editarServicos.add(txtpnAdicionarServicos);
		
		JTextPane txtpnTipoDeServico = new JTextPane();
		txtpnTipoDeServico.setEditable(false);
		txtpnTipoDeServico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnTipoDeServico.setText("Tipo de servico:");
		txtpnTipoDeServico.setBounds(30, 372, 104, 22);
		editarServicos.add(txtpnTipoDeServico);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Baba", "Carro", "Restaurante"}));
		comboBox.setBounds(140, 374, 141, 20);
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
		edicaoServico.setSize(705, 130);
		edicaoServico.setLocation(28, 406);
		editarServicos.add(edicaoServico);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setBounds(598, 547, 135, 25);
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaObjetosServicos();
			}
		});
		editarServicos.add(btnConfirmar_1);
		
		JTextPane txtpnCustoAtual = new JTextPane();
		txtpnCustoAtual.setText("Custo atual:");
		txtpnCustoAtual.setBounds(501, 250, 94, 25);
		editarServicos.add(txtpnCustoAtual);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(599, 250, 134, 21);
		editarServicos.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		fechaContrato();
	}
	
	private void criaObjetosServicos(){
		AvisoErro erro = new AvisoErro();
		Servico servico = null;
		if(comboBox.getSelectedItem().equals("Baba"))
			servico = EdicaoServicos.contrataBaba();
		if(comboBox.getSelectedItem().equals("Carro"))
			servico = EdicaoServicos.alugaCarro();
		if(comboBox.getSelectedItem().equals("Restaurante"))
			servico = EdicaoServicos.geraContaRestaurante();
		
		try {
			Acoes.getContratoPesquisado().adicionaServico(servico);
			
			AvisoSucesso sucesso = new AvisoSucesso();
			sucesso.setVisible(true);
		} catch (AddQuartoContratoException e) {
			erro.setVisible(true);
		}
		
	}

	private void desenhaTabela(JPanel editarServicos) {
		colunas = new String[]{"Servico","Custo"};
		 
		tabela = new JTable();
		tabela.setRowSelectionAllowed(true);
		model = new DefaultTableModel(new String[0][2] , colunas );
		tabela.setModel(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(28, 60, 705, 178);
		scroll.setViewportView(tabela);
		editarServicos.add(scroll);
	}

	private void atualizaTabela() {
		String[][] dados = new String[Acoes.getContratoPesquisado().getServicos().size()][2];
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			dados[i][0] = Acoes.getContratoPesquisado().getServicos().get(i).getNome();
			dados[i][1] = String.valueOf(Acoes.getContratoPesquisado().getServicos().get(i).getPreco());
		}
		
		model = new DefaultTableModel(dados , colunas );
		tabela.setModel(model);
		
		// Atualiza o total
		textFieldTotal.setText(Double.toString(Acoes.getContratoPesquisado().calculaValorTotalServicos()));
	}
	
	private boolean removeServico(int linha) throws RemocaoInvalidaException {
		for (int i = 0; i < Acoes.getContratoPesquisado().getServicos().size(); i++) {
			if(i == linha){
				Acoes.getContratoPesquisado().removeServico(Acoes.getContratoPesquisado().getServicos().get(i));
				return true;
			}
		}	
		return false;
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
		
		hotel.Hotel.removeContrato(Acoes.getContratoPesquisado());
		
		AvisoSucesso sucesso = new AvisoSucesso();
		sucesso.setVisible(true);
	}
	
	private void hospedeDadosPrincipais(JPanel panelNovoContrato) throws ParseException {
		
		final MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
		final MaskFormatter cartaoMask = new MaskFormatter("####.####.####.####");
		
		JTextPane txtpnNomeCompleto = new JTextPane();
		txtpnNomeCompleto.setEditable(false);
		txtpnNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNomeCompleto.setText("Nome completo:");
		txtpnNomeCompleto.setBounds(46, 38, 121, 27);
		panelNovoContrato.add(txtpnNomeCompleto);
		
		JTextPane txtpnNDoCarto = new JTextPane();
		txtpnNDoCarto.setEditable(false);
		txtpnNDoCarto.setText("N\u00FAmero do cart\u00E3o:");
		txtpnNDoCarto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNDoCarto.setBounds(46, 90, 127, 27);
		panelNovoContrato.add(txtpnNDoCarto);
		
		JTextPane txtpnCpf = new JTextPane();
		txtpnCpf.setEditable(false);
		txtpnCpf.setText("CPF:");
		txtpnCpf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCpf.setBounds(477, 87, 42, 27);
		panelNovoContrato.add(txtpnCpf);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(179, 38, 530, 22);
		panelNovoContrato.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		formattedTextFieldCPF = new JFormattedTextField(cpfMask);
		formattedTextFieldCPF.setBounds(537, 87, 172, 22);
		formattedTextFieldCPF.setEnabled(false);
		panelNovoContrato.add(formattedTextFieldCPF);
		
		formattedTextFieldCartao = new JFormattedTextField(cartaoMask);
		formattedTextFieldCartao.setBounds(179, 90, 163, 22);
		panelNovoContrato.add(formattedTextFieldCartao);
		
		JTextPane txtpnAcompanhantes = new JTextPane();
		txtpnAcompanhantes.setEditable(false);
		txtpnAcompanhantes.setText("Acompanhantes:");
		txtpnAcompanhantes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnAcompanhantes.setBounds(46, 240, 127, 27);
		panelNovoContrato.add(txtpnAcompanhantes);
		
		textAreaAcompanhantes = new JTextArea();
		textAreaAcompanhantes.setBackground(UIManager.getColor("CheckBox.background"));
		textAreaAcompanhantes.setLineWrap(true);
		textAreaAcompanhantes.setBounds(179, 238, 530, 53);
		panelNovoContrato.add(textAreaAcompanhantes);
	}

	private void hospedeEndereco(JPanel panelNovoContrato) {
		JTextPane txtpnNascionalidade = new JTextPane();
		txtpnNascionalidade.setEditable(false);
		txtpnNascionalidade.setText("Nascionalidade:");
		txtpnNascionalidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNascionalidade.setBounds(46, 130, 127, 27);
		panelNovoContrato.add(txtpnNascionalidade);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setEditable(false);
		txtpnEstado.setText("Estado:");
		txtpnEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnEstado.setBounds(46, 168, 127, 27);
		panelNovoContrato.add(txtpnEstado);
		
		JTextPane txtpnCidade = new JTextPane();
		txtpnCidade.setEditable(false);
		txtpnCidade.setText("Cidade:");
		txtpnCidade.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnCidade.setBounds(477, 169, 51, 27);
		panelNovoContrato.add(txtpnCidade);
		
		JTextPane txtpnLogradouro = new JTextPane();
		txtpnLogradouro.setEditable(false);
		txtpnLogradouro.setText("Logradouro:");
		txtpnLogradouro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnLogradouro.setBounds(46, 204, 127, 27);
		panelNovoContrato.add(txtpnLogradouro);
		
		JTextPane txtpnNmero = new JTextPane();
		txtpnNmero.setEditable(false);
		txtpnNmero.setText("N\u00FAmero:");
		txtpnNmero.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpnNmero.setBounds(577, 204, 65, 27);
		panelNovoContrato.add(txtpnNmero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(653, 204, 56, 22);
		textFieldNumero.setEnabled(false);
		panelNovoContrato.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(537, 169, 172, 22);
		textFieldCidade.setEnabled(false);
		panelNovoContrato.add(textFieldCidade);
		textFieldCidade.setColumns(10);
		
		comboBoxPaises = new JComboBox<String>();
		comboBoxPaises.setModel(new DefaultComboBoxModel<String>(new String[] {"Afeganistao", "Africa do Sul", "Akrotiri", "Albania", "Alemanha", "Andorra", "Angola", "Anguila", "Antarctida", "Antigua e Barbuda", "Antilhas Neerlandesas", "Arabia Saudita", "Arctic Ocean", "Argelia", "Argentina", "Armenia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Australia", "Austria", "Azerbaijao", "Baamas", "Bangladeche", "Barbados", "Barem", "Belgica", "Belize", "Benim", "Bermudas", "Bielorrussia", "Birmania", "Bolivia", "Bosnia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulgaria", "Burquina Faso", "Burundi", "Butao", "Cabo Verde", "Camaroes", "Camboja", "Canada", "Catar", "Cazaquistao", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Colombia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Croacia", "Cuba", "Dhekelia", "Dinamarca", "Dominica", "Egipto", "Emiratos Arabes Unidos", "Equador", "Eritreia", "Eslovaquia", "Eslovenia", "Espanha", "Estados Unidos", "Estonia", "Etiopia", "Faroe", "Fiji", "Filipinas", "Finlandia", "França", "Gabao", "Gambia", "Gana", "Gaza Strip", "Georgia", "Georgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Grecia", "Gronelandia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guine", "Guine Equatorial", "Guine-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "Iemen", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caimao", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salomao", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Britanicas", "India", "Indian Ocean", "Indonesia", "Irao", "Iraque", "Irlanda", "Islandia", "Israel", "Italia", "Jamaica", "Jan Mayen", "Japao", "Jersey", "Jibuti", "Jordania", "Kuwait", "Laos", "Lesoto", "Letonia", "Libano", "Liberia", "Libia", "Listenstaine", "Lituania", "Luxemburgo", "Macau", "Macedonia", "Madagascar", "Malasia", "Malavi", "Maldivas", "Mali", "Malta", "Isle of Man ", "Marianas do Norte", "Marrocos", "Mauricia", "Mauritania", "Mayotte", "Mexico", "Micronesia", "Mocambique", "Moldavia", "Monaco", "Mongolia", "Monserrate", "Montenegro", "Mundo", "Namibia", "Nauru", "Navassa Island", "Nepal", "Nicaragua", "Niger", "Nigeria", "Niue", "Noruega", "Nova Caledonia", "Nova Zelandia", "Oma", "Pacific Ocean", "Paises Baixos", "Palau", "Panama", "Papua-Nova Guine", "Paquistao", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polinesia Francesa", "Polonia", "Porto Rico", "Portugal", "Quenia", "Quirguizistao", "Quiribati", "Reino Unido", "Republica Centro-Africana", "Republica Checa", "Republica Dominicana", "Romenia", "Ruanda", "Russia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa Lucia", "Sao Cristovao e Neves", "Sao Marinho", "Sao Pedro e Miquelon", "Sao Tome e Principe", "Sao Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "Servia", "Singapura", "Siria", "Somalia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazilandia", "Sudao", "Suecia", "Suiça", "Suriname", "Svalbard e Jan Mayen", "Tailandia", "Taiwan", "Tajiquistao", "Tanzania", "Territorio Britanico do Oceano Indico", "Territorios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tunisia", "Turquemenistao", "Turquia", "Tuvalu", "Ucrania", "Uganda", "Uniao Europeia", "Uruguai", "Usbequistao", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Zambia", "Zimbabue"}));
		comboBoxPaises.setBounds(179, 130, 163, 22);
		comboBoxPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxPaises.getSelectedItem().equals("Brasil")){
					formattedTextFieldCPF.setEnabled(true);
					comboBoxEstados.setEnabled(true);
					textFieldCidade.setEnabled(true);
					textFieldLogradouro.setEnabled(true);
					textFieldNumero.setEnabled(true);
				}else{
					formattedTextFieldCPF.setEnabled(false);
					comboBoxEstados.setEnabled(false);
					textFieldCidade.setEnabled(false);
					textFieldLogradouro.setEnabled(false);
					textFieldNumero.setEnabled(false);
				}// else
			}
		}); // ActionListener
		panelNovoContrato.add(comboBoxPaises);
		
		comboBoxEstados = new JComboBox<String>();
		comboBoxEstados.setModel(new DefaultComboBoxModel<String>(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxEstados.setBounds(179, 170, 163, 22);
		comboBoxEstados.setEnabled(false);
		panelNovoContrato.add(comboBoxEstados);
		
		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setColumns(10);
		textFieldLogradouro.setBounds(179, 204, 386, 22);
		textFieldLogradouro.setEnabled(false);
		panelNovoContrato.add(textFieldLogradouro);
		
	}
	
	private void setaDados() {
		textFieldNome.setText(Acoes.getContratoPesquisado().getHospedeTitular().getNome());
		textFieldNumero.setText(Acoes.getContratoPesquisado().getHospedeTitular().getNumero());
		textFieldCidade.setText(Acoes.getContratoPesquisado().getHospedeTitular().getCidade());
		textFieldLogradouro.setText(Acoes.getContratoPesquisado().getHospedeTitular().getLogradouro());
		formattedTextFieldCPF.setText(Acoes.getContratoPesquisado().getHospedeTitular().getCpf());
		formattedTextFieldCartao.setText(Acoes.getContratoPesquisado().getHospedeTitular().getCartaoDeCredito());
		comboBoxPaises.setSelectedItem(Acoes.getContratoPesquisado().getHospedeTitular().getPais());
		comboBoxEstados.setSelectedItem(Acoes.getContratoPesquisado().getHospedeTitular().getEstado());
	}
	
	private void atualizaDados() throws NullPointerException, StringVaziaException, StringInvalidaException, NumeroInvalidoException, CPFInvalidoException, CartaoInvalidoException{
		Acoes.getContratoPesquisado().getHospedeTitular().setNome(textFieldNome.getText());
		Acoes.getContratoPesquisado().getHospedeTitular().setCartaoDeCredito(formattedTextFieldCartao.getText());
		Acoes.getContratoPesquisado().getHospedeTitular().setPais((String) comboBoxPaises.getSelectedItem());
		if(comboBoxPaises.getSelectedItem().equals("Brasil")){
			Acoes.getContratoPesquisado().getHospedeTitular().setNumero(textFieldNumero.getText());
			Acoes.getContratoPesquisado().getHospedeTitular().setCidade(textFieldCidade.getText());
			Acoes.getContratoPesquisado().getHospedeTitular().setLogradouro(textFieldLogradouro.getText());
			Acoes.getContratoPesquisado().getHospedeTitular().setCpf(formattedTextFieldCPF.getText());
			Acoes.getContratoPesquisado().getHospedeTitular().setEstado(Conector.selecionaEstado((String) comboBoxEstados.getSelectedItem()));
		}
	}
	
	public void limpaOpiniao(){
		textArea = null;
	}

	public static void selecionaTela(String string) {
		layout.show(panel, string);
	}
}
