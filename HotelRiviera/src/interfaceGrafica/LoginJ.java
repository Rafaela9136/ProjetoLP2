package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.SystemColor;

public class LoginJ extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4033714415257302983L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextPane txtpnLogin;
	private JTextPane txtpnSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
		setBounds(100, 100, 534, 469);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(108, 367, 117, 25);
		btnOk.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HotelF frame = new HotelF();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnOk);

		JPanel panel = new JPanel();
		panel.setBounds(73, 27, 399, 231);
		contentPane.add(panel);

		textField = new JTextField();
		textField.setBounds(182, 290, 227, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(182, 327, 227, 25);
		contentPane.add(passwordField);

		txtpnLogin = new JTextPane();
		txtpnLogin.setBounds(108, 290, 64, 32);
		txtpnLogin.setEditable(false);
		txtpnLogin.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtpnLogin.setBackground(SystemColor.activeCaptionBorder);
		txtpnLogin.setText("Login:");
		contentPane.add(txtpnLogin);

		txtpnSenha = new JTextPane();
		txtpnSenha.setBounds(108, 327, 64, 32);
		txtpnSenha.setEditable(false);
		txtpnSenha.setText("Senha:");
		txtpnSenha.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtpnSenha.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(txtpnSenha);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(292, 367, 117, 25);
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(btnCancelar);
	}
}
