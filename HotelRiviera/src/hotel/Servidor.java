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
		if(IP == null || IP.equals(""))
			throw new IPInvalidoException();
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