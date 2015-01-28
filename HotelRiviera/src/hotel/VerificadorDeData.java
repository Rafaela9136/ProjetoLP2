package hotel;

import excecoes.DataInvalidaException;

public class VerificadorDeData {
	
	public void verificaParametrosDeDataValidos(int dia, int mes, int ano)
			throws DataInvalidaException {
		if (mes > 12)
			throw new DataInvalidaException();

		else if (dia > 31)
			throw new DataInvalidaException();

		else if (dia == 31) {
			if ((mes % 2 == 0 && mes <= 6) || mes == 9 || mes == 11)
				throw new DataInvalidaException();

		} else if (mes == 2)
			verificaCasoFevereiro(dia, ano);
	}// verificaParametrosDeDataValidos
	

	private void verificaCasoFevereiro(int dia, int ano)
			throws DataInvalidaException {
		if (ano % 100 == 0) {
			if (dia > 28)
				throw new DataInvalidaException();
		} else if (ano % 4 == 0 || ano % 400 == 0) {
			if (dia > 29)
				throw new DataInvalidaException();
		} else {
			if (dia > 28)
				throw new DataInvalidaException();
		}
	}// verificaCasoFevereiro
	
}// VerificadorDeData
