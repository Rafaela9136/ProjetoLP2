package interfaceGrafica;

import java.awt.SystemColor;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Informacoes extends JPanel {

	/** 
	 */
	private static final long serialVersionUID = -2821631987652830345L;

	/**
	 * Create the panel.
	 */
	public Informacoes() {
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 856, 652);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 832, 597);
		add(panel);
		panel.setLayout(null);
	}
}
