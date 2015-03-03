package visual;

import hotel.Hotel;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

public class Main {

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
			JOptionPane.showMessageDialog(null,"Algo esta errado!");
		} finally {
			if (inputHotel != null)
				inputHotel.close();

		}// try-catch-finally

		try {
			UIManager
					.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,"Algo esta errado!");
		}// try-catch
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Algo esta errado!");
				}// try-catch
			}// run
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
	}// salvaHotel

	public static Hotel getHotel() {
		return hotel;
	}// getHotel
}// Main
