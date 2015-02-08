package testes;

import static org.junit.Assert.*;
import hotel.Opiniao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.ComentarioVazioException;
import excecoes.EstouroDeCaracteresException;
import excecoes.NotaInvalidaException;

public class OpiniaoTest {

	private static final String comentExcessivo = "Something is starting today Where did he go? Why you wanted to be? Well you know,"
			+ "November has come When it's gone away omething is starting today Where did he go? Why you wanted to be? Well you know,"
			+ "November has come gone away can you dig it like a spigot My guess is yes you can like, can I kick it, wicked Liquor shot,"
			+ "if you happy and you know it As you clap our hands to the thick snot of a poet flow i";
	private Opiniao opiniao;
	private final int NOTA = 1;
	private final String COMENT = "Programacao II Universidade Federal de Campina Grande";
	
	@Before
	public void criaObjetos() throws EstouroDeCaracteresException,
			NotaInvalidaException, NullPointerException, ComentarioVazioException {
		opiniao = new Opiniao(COMENT, NOTA);
	}

	@Test
	public void TestOpiniaoConstrutor() {
		try {
			new Opiniao(COMENT, 15);
			Assert.fail("Esperava excecao");
		} catch (NotaInvalidaException e) {

		} catch (Exception e) {
			fail();
		}// try-catch
		try {
			new Opiniao("", 6);
			Assert.fail("Esperava excecao");
		} catch (ComentarioVazioException e) {

		} catch (Exception e) {
			fail();
		}// try-catch
		try {
			new Opiniao(COMENT, -1);
			Assert.fail("Esperava excecao");
		} catch (NotaInvalidaException e) {
		} catch (Exception e) {
			fail();
		}// try-catch
		try {
			new Opiniao(null, NOTA);
			Assert.fail("Esperava excecao");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			
		}// try-catch
		
		try {
			new Opiniao(comentExcessivo, 7);
			Assert.fail("Esperava excecao");
		} catch (EstouroDeCaracteresException e) {

		} catch (Exception e) {
			
		}// try-catch
	}// TestOpiniaoConstrutor

	@Test
	public void testEquals() throws Exception {
		Opiniao opiniao2;
		opiniao2 = new Opiniao("Rafaela Lacerda de Araujo, 114110483", 5);
		Assert.assertFalse(opiniao.equals(opiniao2));
		opiniao2 = new Opiniao(COMENT, NOTA);
		Assert.assertTrue(opiniao.equals(opiniao2));
		opiniao = new Opiniao("hotel riviera campina", 9);
		Assert.assertFalse(opiniao.equals(opiniao2));
	}

	@Test
	public void testToString() {
		String esperado = "Opiniao [comentario=Programacao II Universidade Federal de Campina Grande, nota=1.0]";
		Assert.assertTrue(esperado.equals(opiniao.toString()));
	}
}