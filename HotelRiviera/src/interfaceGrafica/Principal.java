package interfaceGrafica;

import java.awt.EventQueue;
import javax.swing.UIManager;

public class Principal {

	/**
	 * Launch the application
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
