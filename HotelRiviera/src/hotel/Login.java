package hotel;

import interfaceGrafica.HotelJ;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setBounds(139, 126, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(139, 151, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtLogin = new JTextField();
		txtLogin.setBounds(183, 120, 86, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(183, 145, 86, 20);
		contentPane.add(txtSenha);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Acesso cl = new Acesso();
				cl.Acesso(txtLogin.getText(), txtSenha.getText());
				if (cl.acesso == true) {
					HotelJ tl = null;
					try {
						tl = new HotelJ();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					tl.show();
					try {
						tl.setExtendedState(new HotelJ().MAXIMIZED_BOTH);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					dispose();
				}
				if (cl.acesso == false) {
					txtLogin.setText("");
					txtSenha.setText("");
					txtLogin.requestFocus();
				}

				cl.acesso = false;

			}
		});
		btnOK.setBounds(109, 187, 89, 23);
		contentPane.add(btnOK);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		btnCancelar.setBounds(208, 187, 89, 23);
		contentPane.add(btnCancelar);
	}
}
