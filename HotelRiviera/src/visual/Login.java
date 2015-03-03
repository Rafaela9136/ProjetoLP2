package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4399034281301820841L;
	private JPanel contentPane;
	private final JTextField textField = new JTextField();;
	private final JPasswordField passwordField = new JPasswordField();;
	private JLabel lblNewLabel;
	private JLabel lblLogin;
	private JLabel lblSenha;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Hotel Riviera Campina");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Login.class.getResource("/resources/logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 374);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(106, 302, 117, 25);
		btnOk.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		contentPane.add(btnOk);

		passwordField.setBounds(67, 261, 158, 25);
		contentPane.add(passwordField);

		textField.setBounds(67, 220, 158, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblLogin.setBounds(12, 220, 46, 25);
		contentPane.add(lblLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSenha.setBounds(12, 261, 52, 25);
		contentPane.add(lblSenha);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class
				.getResource("/resources/image.jpg")));
		lblNewLabel.setBounds(0, 0, 519, 351);
		contentPane.add(lblNewLabel);
		
	
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaLogin();
			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaLogin();
			}
		});
		
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaLogin();
			}
		});
		
	}// Construtor

	private void verificaLogin() {
		if (Main.getHotel().pesquisaConta(textField.getText(), String.valueOf(passwordField.getPassword()))) {
			try {
				Principal frame = new Principal(textField.getText());
				frame.setVisible(true);
				dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Algo esta errado!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login e/ou senha incorreto!");
		}//if
	}// verificaLogin
		
}
	
	
