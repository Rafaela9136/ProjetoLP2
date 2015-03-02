package visual;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;
import java.io.IOException;

public class Principal extends JFrame implements WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3535604559092678721L;
	private JPanel contentPane;
	private String tipoFuncionario;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Principal(String tipoFuncionario) throws ParseException {
		this.tipoFuncionario = tipoFuncionario;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/logo.png")));
		inicializa();
		telaAbas();
	}

	private void inicializa() {
		setTitle("Hotel Riviera Campina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1024, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(51, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		addWindowListener(new WindowAdapter() {  
		    public void windowClosing(WindowEvent evt) {  
		        if (JOptionPane.showConfirmDialog(null,"Deseja sair?")==JOptionPane.OK_OPTION){
		        	try {
						Main.salvaHotel();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,"Algo esta errado!");
					}
		        	System.exit(0);
		}  
		    }});
	}


	private void telaAbas() throws ParseException {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1018, 677);
		contentPane.add(tabbedPane);
		
		recursos(tabbedPane);
		
		info(tabbedPane);
		
		if(tipoFuncionario.equals("gerente")){
			funcionarios(tabbedPane);
		}
	}

	private void funcionarios(JTabbedPane tabbedPane) {
		JPanel panelFuncionarios = new JPanel();
		panelFuncionarios.setBorder(new LineBorder(new Color(51, 0, 0), 2));
		tabbedPane.addTab("Funcionarios", null, panelFuncionarios, null);
		panelFuncionarios.setLayout(null);
		
		Funcionarios panel = new Funcionarios();
		panel.setBounds(228, 12, 764, 612);
		panelFuncionarios.add(panel);
		
		JButton btnCadastroFunc = new JButton("Cadastrar funcionario");
		btnCadastroFunc.setBounds(12, 37, 204, 49);
		btnCadastroFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios.selecionaTela("cadastro");
			}
		});
		panelFuncionarios.add(btnCadastroFunc);
		
		JButton btnPesquisarFuncionario = new JButton("Pesquisar funcionario");
		btnPesquisarFuncionario.setBounds(12, 109, 204, 49);
		btnPesquisarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios.selecionaTela("pesquisa");
			}
		});
		panelFuncionarios.add(btnPesquisarFuncionario);
		
		botaoSair(panelFuncionarios);
	}

	private void recursos(JTabbedPane tabbedPane) throws ParseException {
		JPanel panelRecursos = new JPanel();
		panelRecursos.setBorder(new LineBorder(new Color(51, 0, 0), 2));
		tabbedPane.addTab("Recursos", null, panelRecursos, null);
		panelRecursos.setLayout(null);
		
		Acoes panel = new Acoes();
		panel.setBounds(228, 12, 764, 612);
		panelRecursos.add(panel);
		
		JButton btnAbrirContrato = new JButton("Abrir contrato");
		btnAbrirContrato.setBounds(12, 37, 204, 49);
		btnAbrirContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acoes.selecionaTela("novoContrato");
			}
		});
		panelRecursos.add(btnAbrirContrato);
		
		JButton btnPesquisarContrato = new JButton("Pesquisar contrato");
		btnPesquisarContrato.setBounds(12, 109, 204, 49);
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acoes.selecionaTela("pesquisaContrato");
				AtualizacaoContrato.selecionaTela("infoGerais");
			}
		});
		panelRecursos.add(btnPesquisarContrato);
		
		botaoSair(panelRecursos);
	}
	
	private void info(JTabbedPane tabbedPane) {
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new LineBorder(new Color(51, 0, 0), 2));
		tabbedPane.addTab("Informa\u00E7\u00F5es", null, panelInfo, null);
		panelInfo.setLayout(null);
		
		Info panel = new Info();
		panel.setBounds(228, 12, 764, 612);
		panelInfo.add(panel);
		
		JButton btnServios = new JButton("Servi\u00E7os");
		btnServios.setBounds(12, 37, 204, 49);
		btnServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("servicos");
				Info.atualizaServico();
			}
		});
		panelInfo.add(btnServios);
		
		JButton btnOpinies = new JButton("Opini\u00F5es");
		btnOpinies.setBounds(12, 109, 204, 49);
		btnOpinies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("opinioes");
			}
		});
		panelInfo.add(btnOpinies);
		
		JButton btnEstatisticas = new JButton("Estatisticas");
		btnEstatisticas.setBounds(12, 178, 204, 49);
		btnEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("estatistica");
			}
		});
		panelInfo.add(btnEstatisticas);
		
		botaoSair(panelInfo);
	}

	private void botaoSair(JPanel panel) {
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(12, 575, 204, 49);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Deseja sair?")==JOptionPane.OK_OPTION){
					try {
						Main.salvaHotel();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Algo esta errado!");
					}
					System.exit(0);
				}
			}
		});
		panel.add(btnSair);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {		
	}

	@Override
	public void windowClosing(WindowEvent e) {		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {	
	}
}
