package testes;

import java.util.*;

import hotel.*;

import org.junit.*;

import excecoes.NomeVazioException;

public class BabaTest {
	
	private Calendar horarioInicio1 = new GregorianCalendar(2014, Calendar.DECEMBER, 31, 22, 0);
	private Calendar horarioFim1 =  new GregorianCalendar(2015, Calendar.JANUARY, 0, 7, 0);
	
	private Calendar horarioInicio2 = new GregorianCalendar(2014, Calendar.JUNE, 20, 13, 0);
	private Calendar horarioFim2 =  new GregorianCalendar(2015, Calendar.JUNE, 0, 19, 0);
	
	private Baba gabriela;
	private Baba rafaela;	
	
	@Before
	public void criaBabas() throws NullPointerException, NomeVazioException {
		
		gabriela = new Baba("Gabriela", horarioInicio1, horarioFim1);
		rafaela = new Baba("Rafaela", horarioInicio2, horarioFim2);
	}
	
	@Test
	public void testaConstrutor() throws NomeVazioException {
		try {
			Baba Gabriela = new Baba("", horarioInicio1, horarioFim1);
		} catch (NomeVazioException e) {
			e.printStackTrace();
		}
		
		try {
			Baba Gabriela = new Baba(null, horarioInicio1, horarioFim1);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		try {
			Baba Gabriela = new Baba("Gabriela", null, horarioFim1);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		try {
			Baba Gabriela = new Baba("Gabriela", horarioInicio1, null);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Gabriela", gabriela.getNome());
		Assert.assertEquals(horarioInicio1, gabriela.getInicioDoServico());
		Assert.assertEquals(horarioFim1, gabriela.getTerminoDoServico());
		Assert.assertEquals("Rafaela", rafaela.getNome());
		Assert.assertEquals(horarioInicio2, rafaela.getInicioDoServico());
		Assert.assertEquals(horarioFim2, rafaela.getTerminoDoServico());
		
	}

}
