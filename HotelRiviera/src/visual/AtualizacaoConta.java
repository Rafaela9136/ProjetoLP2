package visual;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.CardLayout;

public class AtualizacaoConta extends JPanel {

	/**
	 */
	private static final long serialVersionUID = 4952634473570361300L;

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
		
		JButton btnInformacoesGerais = new JButton(" Informacoes gerais ");
		btnInformacoesGerais.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		toolBar.add(btnInformacoesGerais);
		
		JButton btnEditarDadosDa = new JButton(" Editar dados da conta ");
		btnEditarDadosDa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		toolBar.add(btnEditarDadosDa);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(2, 32, 760, 577);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel infoGerais = new JPanel();
		infoGerais.setBackground(Color.WHITE);
		panel.add(infoGerais, "name_619798852991873");
		
		JPanel editarDados = new JPanel();
		editarDados.setBackground(Color.WHITE);
		panel.add(editarDados, "name_619801687633591");
	}
}
