package visual;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class LoginJ extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4033714415257302983L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblLogin;
	private JLabel lblSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJ frame = new LoginJ();
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
	public LoginJ() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 374);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(102, 51, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(106, 302, 117, 25);
		btnOk.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Hotel frame = new Hotel();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnOk);

		passwordField = new JPasswordField();
		passwordField.setBounds(67, 261, 158, 25);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setBounds(67, 220, 158, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblLogin.setBounds(18, 221, 46, 25);
		contentPane.add(lblLogin);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSenha.setBounds(18, 261, 46, 25);
		contentPane.add(lblSenha);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginJ.class.getResource("/resources/logo.png")));
		lblNewLabel.setBounds(2, 1, 519, 343);
		contentPane.add(lblNewLabel);
	}
}
