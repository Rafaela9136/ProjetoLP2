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
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.Font;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HotelJ frame = new HotelJ();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(214, 385, 117, 25);
		contentPane.add(btnOk);

		JPanel panel = new JPanel();
		panel.setBounds(154, 45, 399, 211);
		contentPane.add(panel);

		textField = new JTextField();
		textField.setBounds(263, 308, 227, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(263, 345, 227, 25);
		contentPane.add(passwordField);

		txtpnLogin = new JTextPane();
		txtpnLogin.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtpnLogin.setBackground(UIManager.getColor("Button.background"));
		txtpnLogin.setText("Login:");
		txtpnLogin.setBounds(189, 308, 64, 32);
		contentPane.add(txtpnLogin);

		txtpnSenha = new JTextPane();
		txtpnSenha.setText("Senha:");
		txtpnSenha.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtpnSenha.setBackground(UIManager.getColor("Button.background"));
		txtpnSenha.setBounds(189, 345, 64, 32);
		contentPane.add(txtpnSenha);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(364, 385, 117, 25);
		contentPane.add(btnCancelar);
	}
}
