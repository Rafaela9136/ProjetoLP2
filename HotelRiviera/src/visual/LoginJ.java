package visual;

import hotel.Hotel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

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

	private static Hotel hotel;

	private final static String PATH_HOTEL = "hotel.dat";

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		ObjectInputStream inputHotel = null;

		try {
			inputHotel = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(PATH_HOTEL)));
			hotel = (Hotel) inputHotel.readObject();

		} catch (ClassNotFoundException e) {
			System.out.println("Deu ClasshNotFoundException, ou seja, fudeu!!");

		} finally {
			if (inputHotel != null)
				inputHotel.close();

		}// try-catch-finally
		
		System.out.println(hotel.getOpinioesOrdenadas());

		try {
			UIManager
					.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
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

	}// main

	static void salvaHotel() throws IOException, FileNotFoundException {
		ObjectOutputStream outputHotel = null;
		try {

			outputHotel = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(PATH_HOTEL)));
			outputHotel.writeObject(hotel);

		} finally {
			if (outputHotel != null)
				outputHotel.close();
		}// try-finally
	}

	public static Hotel getHotel() {
		return hotel;
	}

	/**
	 * Create the frame.
	 */
	public LoginJ() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				LoginJ.class.getResource("/resources/logo.png")));
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
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Principal frame = new Principal();
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
		lblLogin.setBounds(12, 220, 46, 25);
		contentPane.add(lblLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSenha.setBounds(12, 261, 52, 25);
		contentPane.add(lblSenha);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginJ.class
				.getResource("/resources/image.jpg")));
		lblNewLabel.setBounds(0, 0, 519, 351);
		contentPane.add(lblNewLabel);
	}

}
