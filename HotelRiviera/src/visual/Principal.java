package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Principal extends JFrame {

	/** 
	 */
	private static final long serialVersionUID = 1711982736031976761L;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelF frame = new HotelF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
