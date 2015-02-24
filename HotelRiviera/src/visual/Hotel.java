package visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class Hotel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3535604559092678721L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Hotel() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Hotel.class.getResource("/resources/icon.jpg")));
		inicializa();
		telaAbas();
	}

	private void inicializa() {
		setTitle("Hotel Riviera Campina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(51, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void telaAbas() throws ParseException {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1018, 677);
		contentPane.add(tabbedPane);
		
		recursos(tabbedPane);
		
		info(tabbedPane);
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
		
		JButton btnEstabelecimento = new JButton("Estabelecimento");
		btnEstabelecimento.setBounds(12, 37, 204, 49);
		btnEstabelecimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("estabelecimento");
			}
		});
		panelInfo.add(btnEstabelecimento);
		
		JButton btnServios = new JButton("Serviços");
		btnServios.setBounds(12, 109, 204, 49);
		btnServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("servicos");
			}
		});
		panelInfo.add(btnServios);
		
		JButton btnOpinies = new JButton("Opiniões");
		btnOpinies.setBounds(12, 180, 204, 49);
		btnOpinies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("opinioes");
			}
		});
		panelInfo.add(btnOpinies);
		
		JButton btnEstatisticas = new JButton("Estatisticas");
		btnEstatisticas.setBounds(12, 247, 204, 49);
		btnEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info.selecionaTela("estatisticas");
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
				System.exit(0);
			}
		});
		panel.add(btnSair);
	}
}
