package visual;


import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.Toolkit;

public class HotelF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6551904514606622380L;
	private JPanel hotel;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public HotelF() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HotelF.class.getResource("/resources/hotelRiviera.png")));
		setResizable(false);
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 687);
		hotel = new JPanel();
		hotel.setBackground(UIManager.getColor("Button.light"));
		hotel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(hotel);
		hotel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 872, 662);
		hotel.add(tabbedPane);
		
		Recursos recursos = new Recursos();
		tabbedPane.addTab("Recursos", null, recursos, null);
	}
}
