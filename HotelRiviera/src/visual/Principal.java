package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Principal extends JFrame {
	public Principal() {
	}

	/** 
	 */
	private static final long serialVersionUID = 1711982736031976761L;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
}
