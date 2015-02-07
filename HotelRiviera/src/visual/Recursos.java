package visual;

import javax.swing.JPanel;


import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;

public class Recursos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8789089620746000252L;

	/**
	 * Create the panel.
	 * 
	 * @throws ParseException
	 */
	public Recursos() throws ParseException {

		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 856, 645);
		setLayout(null);
		
		// Paineis de acoes
		AcoesGerais acoes = new AcoesGerais();
		acoes.setBounds(227, 12, 607, 606);
		add(acoes);
				
		// Botoes
		JButton btnContrato = new JButton("Novo contrato");
		btnContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnContrato.setBounds(24, 42, 180, 43);
		btnContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcoesGerais.selecionaTela("novoContrato");
			}
		});
		add(btnContrato);

		JButton btnPesquisarContrato = new JButton("Pesquisar contrato");
		btnPesquisarContrato.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPesquisarContrato.setBounds(24, 105, 180, 43);
		btnPesquisarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcoesGerais.selecionaTela("pesquisarContrato");
			}
		});
		add(btnPesquisarContrato);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSair.setBounds(24, 575, 180, 43);
		add(btnSair);

		// ****************** Retirar **********************
		JButton btnteste = new JButton("teste");
		btnteste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcoesGerais.selecionaTela("detalhesContrato");
			}
		});
		btnteste.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnteste.setBounds(51, 160, 145, 25);
		add(btnteste);
		//***************************************************
	}
}
