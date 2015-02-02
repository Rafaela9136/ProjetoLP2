package interfaceGrafica;


import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class Hospedes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2326746010080093439L;
	private final CardLayout layout = new CardLayout();
	
	private JPanel acoes1;

	/**
	 * Create the panel.
	 */
	public Hospedes() {
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 856, 645);
		setLayout(null);
		
		acoes1 = new JPanel();
		acoes1.setBackground(SystemColor.window);
		acoes1.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		acoes1.setBounds(227, 12, 607, 606);
		add(acoes1);
		acoes1.setLayout(layout);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(SystemColor.window);
		acoes1.add(vazio, "vazio");
		layout.show(acoes1, "vazio");
		
		JPanel pesquisarHospede = new JPanel();
		pesquisarHospede.setBackground(SystemColor.window);
		acoes1.add(pesquisarHospede, "pesquisarHospede");
		
		// Botoes
		JButton btnPesquisarHospede = new JButton("Pesquisar h\u00F3spede");
		btnPesquisarHospede.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPesquisarHospede.setBounds(24, 42, 180, 43);
		btnPesquisarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(acoes1, "pesquisarHospede");
			}
		});
		add(btnPesquisarHospede);
	}

}
