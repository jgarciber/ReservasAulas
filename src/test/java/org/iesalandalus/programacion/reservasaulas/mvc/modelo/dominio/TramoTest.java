package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TramoTest {
	
	private static final String TRAMO_NO_ESPERADO = "El tramo devuelto no es el esperado.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String MANANA = "Mañana";
	private static final String TARDE = "Tarde";


	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Tramo tramo;
		tramo = Tramo.MANANA;
		assertThat(TRAMO_NO_ESPERADO, tramo, is(Tramo.MANANA));
		assertThat(CADENA_NO_ESPERADA, tramo.toString(), is(MANANA));
		tramo = Tramo.TARDE;
		assertThat(TRAMO_NO_ESPERADO, tramo, is(Tramo.TARDE));
		assertThat(CADENA_NO_ESPERADA, tramo.toString(), is(TARDE));
	}

}
