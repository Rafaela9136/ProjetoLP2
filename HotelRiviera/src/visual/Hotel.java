package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;

public class Hotel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3535604559092678721L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hotel frame = new Hotel();
					frame.setVisible(true);
				} catch (Exception e) {
					AvisoErro erro = new AvisoErro();
					erro.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Hotel() throws ParseException {
		inicializa();
		telaAbas();
	}

	private void inicializa() {
		setTitle("Hotel Riviera Campina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		tabbedPane.addTab("Recursos", null, panelRecursos, null);
		panelRecursos.setLayout(null);
		
		Acoes panel = new Acoes();
		panel.setBackground(Color.WHITE);
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
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(12, 575, 204, 49);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelRecursos.add(btnSair);
	}
	
	private void info(JTabbedPane tabbedPane) {
		JPanel panelInfo = new JPanel();
		tabbedPane.addTab("Informa\u00E7\u00F5es", null, panelInfo, null);
	}
}