package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.JTextPane;

import java.awt.Font;

public class AvisoErro extends JFrame {

	/**
	 */
	private static final long serialVersionUID = -2140225497396925853L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AvisoErro() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(266, 94, 381, 144);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		txtpnErro.setText("Algo parece errado, tente novamente.");
		txtpnErro.setBounds(63, 29, 225, 25);
		contentPane.add(txtpnErro);
	}

}