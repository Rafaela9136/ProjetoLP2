package interfaceGrafica;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

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
		setResizable(false);
		setTitle("Hotel Riviera Campina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 688);
		hotel = new JPanel();
		hotel.setBackground(UIManager.getColor("Button.light"));
		hotel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(hotel);
		hotel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 872, 659);
		hotel.add(tabbedPane);
		
		Recursos recursos = new Recursos();
		tabbedPane.addTab("Recursos", null, recursos, null);
		
		Pesquisar pesquisar = new Pesquisar();
		tabbedPane.addTab("Pesquisar", null, pesquisar, null);
		
		Informacoes info = new Informacoes();
		tabbedPane.addTab("Informações", null, info, null);
	}
}
