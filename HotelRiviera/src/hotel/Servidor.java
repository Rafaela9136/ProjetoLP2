package hotel;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import excecoes.IPInvalidoException;
import excecoes.PortaInvalidaException;
	
public class Servidor {
	
	private int porta;
	private String IP;
	
	
	public Servidor(int porta, String IP) throws IPInvalidoException, PortaInvalidaException {
		if(porta < 0 || porta > 65535)
			throw new PortaInvalidaException();
		
		verificaIPValido(IP);
	}// Construtor
	
	private void verificaIPValido(String IP) throws IPInvalidoException {
		if(IP == null || IP.isEmpty())
			throw new IPInvalidoException();
		IP = IP.trim();
		
		if(IP.length() > 15)
			throw new IPInvalidoException();
		
		String[] bytesIP = new String[4];
		
		String num = "";
		int quantPontos = 0;
		final int MAXPONTOS = 3;
	
		for (int i = 0; i < IP.length(); i++) {
			if(IP.charAt(i) == '.') {
				bytesIP[quantPontos] = num;
				quantPontos++;
				num = "";
				continue;
			}// if
			num.concat(String.valueOf(IP.charAt(i)));
			if(num.length() > 3 || num)
				throw new IPInvalidoException();
			
		}// for
	}// verificaIPValido
	
   public static void main(String[] args) throws IOException {
     ServerSocket servidor = new ServerSocket(12345);
     System.out.println("Porta 12345 aberta!");
     
     Socket cliente = servidor.accept();
     System.out.println("Nova conexão com o cliente " +   
       cliente.getInetAddress().getHostAddress()
     );
     
     Scanner input = new Scanner(cliente.getInputStream());
     while (input.hasNextLine()) {
       System.out.println(input.nextLine());
     }
     
     input.close();
     servidor.close();
     cliente.close();
   }
 }