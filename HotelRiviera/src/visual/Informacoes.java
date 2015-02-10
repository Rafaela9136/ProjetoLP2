package visual;

import hotel.*;

import java.awt.SystemColor;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class Informacoes extends JPanel {

	/** 
	 */
	private static final long serialVersionUID = -2821631987652830345L;

	/**
	 * Create the panel.
	 */
	public Informacoes() {
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 856, 652);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(227, 12, 607, 606);
		add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnHotelRivieraCampina = new JTextPane();
		txtpnHotelRivieraCampina.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnHotelRivieraCampina.setText("Hotel Riviera Campina");
		txtpnHotelRivieraCampina.setBounds(21, 31, 186, 32);
		panel.add(txtpnHotelRivieraCampina);
		
		JTextPane txtpnOpiniesDosHspedes = new JTextPane();
		txtpnOpiniesDosHspedes.setText("Opini\u00F5es dos h\u00F3spedes:");
		txtpnOpiniesDosHspedes.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtpnOpiniesDosHspedes.setBounds(21, 199, 186, 32);
		panel.add(txtpnOpiniesDosHspedes);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		editorPane.setBounds(31, 62, 539, 125);
		editorPane.setText("O Hotel Riviera Campina é um resort de luxo na magnífica Serra da Borborema, que conta com piscina, playground, babysitter, aluguel de carro, um transfer para João Pessoa e Campina Grande, wifi gratis para todo o hotel, além de oferecer o que háde melhor em cozinha com duas unidades do seu tradicional restaurante Delícias da Serra.\nEstá há alguns quilômetros da cidade, para que seus hóspedes possam relaxar e respirar um ar puro das montanhas.");
		panel.add(editorPane);
		
		JButton btnHotel = new JButton("Hotel");
		btnHotel.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnHotel.setBounds(24, 42, 180, 43);
		add(btnHotel);
		
		JButton btnServios = new JButton("Servi\u00E7os");
		btnServios.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnServios.setBounds(24, 105, 180, 43);
		add(btnServios);
	}
}
