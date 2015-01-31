package testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import hotel.Carro;
import hotel.TipoCarro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarroTest {

	Carro carro1, carro2, carro3;
	private TipoCarro carroExecutivo = TipoCarro.EXECUTIVO;
	private TipoCarro carroLuxo = TipoCarro.LUXO;
	Calendar dataInicio;
	Calendar dataTermino = new GregorianCalendar(2015, 1, 5);
	Calendar dataTermino2 = new GregorianCalendar(2015, 1, 3);
	Calendar dataTermino3 = new GregorianCalendar(2015, 1, 12);

	@Before
	public void criaObjetos() {
		carro1 = new Carro(carroExecutivo, dataTermino, true, true);
		carro2 = new Carro(carroLuxo, dataTermino2, true, false);
		carro3 = new Carro(carroLuxo, dataTermino3, false, true);
	}
	
	@Test
	public void test() {

	}

}
