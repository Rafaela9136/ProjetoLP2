package hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HotelJ extends JFrame {

	private JPanel hotel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelJ frame = new HotelJ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HotelJ() {
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 603);
		hotel = new JPanel();
		hotel.setBackground(SystemColor.window);
		hotel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(hotel);
		hotel.setLayout(null);
		
		JTabbedPane menuAbas = new JTabbedPane(JTabbedPane.TOP);
		menuAbas.setForeground(UIManager.getColor("nimbusBase"));
		menuAbas.setBackground(UIManager.getColor("nimbusBase"));
		menuAbas.setBounds(0, 0, 690, 564);
		hotel.add(menuAbas);
		
		JPanel contratos = new JPanel();
		contratos.setBackground(SystemColor.window);
		menuAbas.addTab("Contratos", null, contratos, null);
		contratos.setLayout(null);
		
		JPanel acoes1 = new JPanel();
		acoes1.setBackground(SystemColor.window);
		acoes1.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes1.setBounds(227, 12, 446, 505);
		contratos.add(acoes1);
		acoes1.setLayout(new CardLayout(0, 0));
		
		JPanel novoContrato = new JPanel();
		novoContrato.setBackground(SystemColor.window);
		acoes1.add(novoContrato, "name_444495548988203");
		
		JPanel pesquisarContrato = new JPanel();
		pesquisarContrato.setBackground(SystemColor.window);
		acoes1.add(pesquisarContrato, "name_444520991709439");
		
		JButton btnNovoContrato = new JButton("Novo Contrato");
		btnNovoContrato.setBounds(24, 41, 180, 43);
		contratos.add(btnNovoContrato);
		
		JButton button = new JButton("Pesquisar Contrato");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(24, 105, 180, 43);
		contratos.add(button);
		
		JPanel servicos = new JPanel();
		servicos.setBackground(SystemColor.window);
		menuAbas.addTab("Servi\u00E7os", null, servicos, null);
		servicos.setLayout(null);
		
		JPanel acoes2 = new JPanel();
		acoes2.setBackground(SystemColor.window);
		acoes2.setBounds(227, 12, 446, 505);
		acoes2.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		servicos.add(acoes2);
		acoes2.setLayout(new CardLayout(0, 0));
		
		JPanel pesquisarServico = new JPanel();
		pesquisarServico.setBackground(SystemColor.window);
		acoes2.add(pesquisarServico, "name_444866126480746");
		
		JPanel atualizarServico = new JPanel();
		atualizarServico.setBackground(SystemColor.window);
		acoes2.add(atualizarServico, "name_444881874725997");
		
		JPanel adicionarServico = new JPanel();
		adicionarServico.setBackground(SystemColor.window);
		acoes2.add(adicionarServico, "name_445111540503181");
		adicionarServico.setLayout(null);
		
		JPanel hospedes = new JPanel();
		hospedes.setBackground(SystemColor.window);
		menuAbas.addTab("H\u00F3spedes", null, hospedes, null);
		hospedes.setLayout(null);
		
		JPanel acoes3 = new JPanel();
		acoes3.setBackground(SystemColor.window);
		acoes3.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes3.setBounds(227, 12, 446, 505);
		hospedes.add(acoes3);
		acoes3.setLayout(new CardLayout(0, 0));
		
		JPanel pesquisarHospede = new JPanel();
		pesquisarHospede.setBackground(SystemColor.window);
		acoes3.add(pesquisarHospede, "name_444976330230945");
		
		JPanel editarHospede = new JPanel();
		editarHospede.setBackground(SystemColor.window);
		acoes3.add(editarHospede, "name_444978562121406");
	}
}
