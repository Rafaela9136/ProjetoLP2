package visual;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AvisoQuartoIndisponivel extends JFrame {


	/**
	 */
	private static final long serialVersionUID = -2140225497396925853L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AvisoQuartoIndisponivel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(266, 94, 381, 144);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(122, 59, 117, 25);
		contentPane.add(btnOk);
		
		JTextPane txtpnErro = new JTextPane();
		txtpnErro.setEditable(false);
		txtpnErro.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnErro.setText("Oops! O quarto selecionado nao esta disponivel.");
		txtpnErro.setBounds(28, 30, 321, 25);
		contentPane.add(txtpnErro);
	}


}
