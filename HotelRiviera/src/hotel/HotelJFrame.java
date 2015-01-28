package hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class HotelJFrame extends JFrame {
	
	private CardLayout layout = new CardLayout();
	private JPanel externo = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelJFrame frame = new HotelJFrame();
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
	public HotelJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 548);
		getContentPane().setLayout(null);
		
		externo.setBounds(0, 0, 770, 519);
		getContentPane().add(externo);
		externo.setLayout(layout);
		
		JPanel panel = new JPanel();
		externo.add(panel, "name_17311967427312");
		panel.setLayout(null);
		
		JButton btnNewButtonavancar = new JButton("avancar");
		btnNewButtonavancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layout.show(externo, "1");
			}
		});
		btnNewButtonavancar.setBounds(609, 464, 117, 25);
		panel.add(btnNewButtonavancar);
		
		JPanel panel_1 = new JPanel();
		externo.add(panel_1, "1");
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(42, 98, 585, 67);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("avancar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(externo, "2");
			}
		});
		button.setBounds(0, 0, 117, 25);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		externo.add(panel_2, "2");
		panel_2.setLayout(null);
		
		JButton btnSohPraVer = new JButton("soh pra ver");
		btnSohPraVer.setBounds(324, 204, 117, 25);
		panel_2.add(btnSohPraVer);
	}
}
