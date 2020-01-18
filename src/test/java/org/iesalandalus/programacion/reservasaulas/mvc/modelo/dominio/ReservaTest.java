package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReservaTest {
	
	private static final String ERROR_PROFESOR_NULO = "ERROR: La reserva debe estar a nombre de un profesor.";
	private static final String ERROR_AULA_NULA = "ERROR: La reserva debe ser para un aula concreta.";
	private static final String ERROR_PERMANENCIA_NULA = "ERROR: La reserva se debe hacer para una permanencia concreta.";
	private static final String ERROR_COPIAR_RESERVA_NULA = "ERROR: No se puede copiar una reserva nula.";
	private static final String PROFESOR_INCORRECTO = "Debería haber saltado una excepción indicando que el profesor es incorrecto";
	private static final String AULA_INCORRECTA = "Debería haber saltado una excepción indicando que el aula es incorrecta";
	private static final String PERMANENCIA_INCORRECTA = "Debería haber saltado una excepción indicando que la permanencia es incorrecta";
	private static final String RESERVA_NULA = "Debería haber saltado una excepción indicando que no se puede copiar una reserva nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String RESERVA_NO_ESPERADA = "La reserva copiada debería ser la misma que la pasada como parámetro.";
	private static final String PROFESOR_NO_ESPERADO = "El profesor devuelto no es el mismo que el pasado al constructor.";
	private static final String AULA_NO_ESPERADA = "El aula devuelta no es la misma que la pasada al constructor.";
	private static final String PERMANENCIA_NO_ESPERADA = "La permanencia devuelta no es la misma que la pasada al constructor.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto profesor.";
	private static Profesor profesor;
	private static Aula aula;
	private static Permanencia permanencia;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		profesor = Profesor.getProfesorFicticio("bob@gmail.com");
		aula = new Aula("Aula 1");
		permanencia = new Permanencia(LocalDate.now(), Tramo.MANANA);
	}

	@Test
	public void constructorProfesorValidoAulaValidaPermanenciaValidaCreaReservaCorrectamente() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		assertThat(PROFESOR_NO_ESPERADO, reserva.getProfesor(), is(profesor));
		assertThat(REFERENCIA_NO_ESPERADA, reserva.getProfesor(), not(sameInstance(profesor)));
		assertThat(AULA_NO_ESPERADA, reserva.getAula(), is(aula));
		assertThat(REFERENCIA_NO_ESPERADA, reserva.getAula(), not(sameInstance(aula)));
		assertThat(PERMANENCIA_NO_ESPERADA, reserva.getPermanencia(), is(permanencia));
		assertThat(REFERENCIA_NO_ESPERADA, reserva.getPermanencia(), not(sameInstance(permanencia)));
	}
	
	@Test
	public void constructorProfesorNoValidoAulaValidaPermanenciaValidaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(null, aula, permanencia);
			fail(PROFESOR_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorProfesorValidoAulaNoValidaPermanenciaValidaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(profesor, null, permanencia);
			fail(AULA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorProfesorValidoAulaValidaPermanenciaNoValidaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(profesor, aula, null);
			fail(PERMANENCIA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PERMANENCIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorReservaValidaCopiaReservaCorrectamente() {
		Reserva reserva1 = new Reserva(profesor, aula, permanencia);
		Reserva reserva2 = new Reserva(reserva1);
		assertThat(RESERVA_NO_ESPERADA, reserva2, is(reserva1));
		assertThat(REFERENCIA_NO_ESPERADA, reserva2, not(sameInstance(reserva1)));
		assertThat(PROFESOR_NO_ESPERADO, reserva2.getProfesor(), is(profesor));
		assertThat(REFERENCIA_NO_ESPERADA, reserva2.getProfesor(), not(sameInstance(profesor)));
		assertThat(AULA_NO_ESPERADA, reserva2.getAula(), is(aula));
		assertThat(REFERENCIA_NO_ESPERADA, reserva2.getAula(), not(sameInstance(aula)));
		assertThat(PERMANENCIA_NO_ESPERADA, reserva2.getPermanencia(), is(permanencia));
		assertThat(REFERENCIA_NO_ESPERADA, reserva2.getPermanencia(), not(sameInstance(permanencia)));
	}
	
	@Test
	public void getReservaFicticiaAulaCorrectaPermanenciaCorrectaDevuelveReservaCorrecta() {
		Reserva reserva = Reserva.getReservaFicticia(aula, permanencia);
		assertThat(AULA_NO_ESPERADA, reserva.getAula(), is(aula));
		assertThat(PERMANENCIA_NO_ESPERADA, reserva.getPermanencia(), is(permanencia));
	}
	
	@Test
	public void getReservaFicticiaAulaNoValidaPermanenciaValidaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = Reserva.getReservaFicticia(null, permanencia);
			fail(AULA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
	}
	
	@Test
	public void getReservaFicticiaAulaValidaPermanenciaNoValidaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = Reserva.getReservaFicticia(aula, null);
			fail(PERMANENCIA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PERMANENCIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		
	}
	
	@Test
	public void constructorReservaNulaLanzaExcepcion() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(null);
			fail(RESERVA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_RESERVA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reserva, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		assertThat(CADENA_NO_ESPERADA, reserva.toString(), is(String.format("%s, %s, %s", profesor, aula, permanencia)));
	}

}
