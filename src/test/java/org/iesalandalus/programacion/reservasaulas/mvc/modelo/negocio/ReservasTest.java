package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReservasTest {

	private static final String ERROR_CAPACIDAD_NO_CORRECTA = "ERROR: La capacidad debe ser mayor que cero.";
	private static final String ERROR_INSERTAR_RESERVA_NULA = "ERROR: No se puede insertar una reserva nula.";
	private static final String ERROR_BORRAR_RESERVA_NULA = "ERROR: No se puede borrar una reserva nula.";
	private static final String ERROR_BUSCAR_RESERVA_NULA = "ERROR: No se puede buscar una reserva nula.";
	private static final String ERROR_NO_MAS_RESERVAS = "ERROR: No se aceptan más reservas.";
	private static final String ERROR_RESERVA_EXISTE = "ERROR: Ya existe un reserva igual.";
	private static final String ERROR_RESERVA_BORRAR_NO_EXISTE = "ERROR: No existe ninguna reserva igual.";
	private static final String ERROR_PROFESOR_NULO = "ERROR: El profesor no puede ser nulo.";
	private static final String ERROR_AULA_NULA = "ERROR: El aula no puede ser nula.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String RESERVA_NULA = "Debería haber saltado una excepción indicando que no se puede operar con una reserva nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String OPERACION_NO_REALIZADA = "La operación no la ha realizado correctamente.";
	private static final String RESERVAS_NO_CREADAS = "Debería haber creado las reservas correctamente.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String RESERVA_NO_ESPERADA = "El reserva devuelta no es la que debería ser.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	
	private static Profesor profesor1;
	private static Profesor profesor2;
	private static Profesor profesor3;
	private static Aula aula1;
	private static Aula aula2;
	private static Aula aula3;
	private static Permanencia permanencia1;
	private static Permanencia permanencia2;
	private static Permanencia permanencia3;
	private static Reserva reserva1;
	private static Reserva reserva2;
	private static Reserva reserva3;
	private static Reserva reservaRepetida;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		profesor1 = Profesor.getProfesorFicticio("bob@gmail.com");
		profesor2 = Profesor.getProfesorFicticio("calamardo@gmail.com");
		profesor3 = Profesor.getProfesorFicticio("patricio@gmail.com");
		aula1 = new Aula("Aula 1");
		aula2 = new Aula("Aula 2");
		aula3 = new Aula("Aula 3");
		permanencia1 = new Permanencia(LocalDate.now(), Tramo.MANANA);
		permanencia2 = new Permanencia(LocalDate.now(), Tramo.TARDE);
		permanencia3 = new Permanencia(LocalDate.now().plusDays(1), Tramo.MANANA);
		reserva1 = new Reserva(profesor1, aula1, permanencia1);
		reserva2 = new Reserva(profesor2, aula2, permanencia2);
		reserva3 = new Reserva(profesor3, aula3, permanencia3);
		reservaRepetida = new Reserva(profesor2, aula1, permanencia1);
	}
	
	@Test
	public void constructorCapacidadValidaCreaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		assertThat(RESERVAS_NO_CREADAS, reservas, not(nullValue()));
		assertThat(RESERVAS_NO_CREADAS, reservas.getCapacidad(), is(5));
		assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
	}
	
	@Test
	public void constructorCapacidadNoValidaLanzaExcepcion() {
		Reservas reservas = null;
		try {
			reservas = new Reservas(0);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			reservas = new Reservas(-1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarReservaValidaConReservasVaciasInsertaReservaCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			Reserva[] copiaReservas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva1), not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[0], is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[0], not(sameInstance(reserva1)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosReservasValidasInsertaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			Reserva[] copiaReservas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva1), not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[0], is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[0], not(sameInstance(reserva1)));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva2), not(sameInstance(reserva2)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[1], is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[1], not(sameInstance(reserva2)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresReservasValidasInsertaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Reserva[] copiaReservas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva1), not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[0], is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[0], not(sameInstance(reserva1)));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva2), not(sameInstance(reserva2)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[1], is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[1], not(sameInstance(reserva2)));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva3), not(sameInstance(reserva3)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[2], is(reserva3));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[2], not(sameInstance(reserva3)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(null);
			fail(RESERVA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarReservaRepetidaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva2);
			reservas.insertar(reserva1);
			reservas.insertar(reserva3);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva1);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarReservaValidaConReservasLlenasLanzaExcepcion() {
		Reservas reservas = new Reservas(2);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NO_MAS_RESERVAS));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva1), not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, reservas.get()[0], is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva2), not(sameInstance(reserva2)));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarReservaExistenteBorraReservaCorrectamente() throws OperationNotSupportedException {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.borrar(reserva2);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva2);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva3);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getProfesorValidoDevuelveReservasProfesor() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Reserva[] reservasProfesor = reservas.get(profesor1);
			assertThat(OPERACION_NO_REALIZADA, reservasProfesor[0], is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservasProfesor[0], not(sameInstance(reserva1)));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservasProfesor[1], is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getProfesorNoValidoLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		Reserva[] reservasProfesor = null;
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Profesor profesor = null;
			reservasProfesor = reservas.get(profesor);
			fail(OPERACION_NO_PERMITIDA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservasProfesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getAulaValidaDevuelveReservasTutoria() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Reserva[] reservasAula = reservas.get(new Aula("Aula 1"));
			assertThat(OPERACION_NO_REALIZADA, reservasAula[0], is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservasAula[0], not(sameInstance(reserva1)));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservasAula[1], is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getAulaNoValidaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		Reserva[] reservasAula = null;
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Aula aula = null;
			reservasAula = reservas.get(aula);
			fail(OPERACION_NO_PERMITIDA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservasAula, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarReservaNoExistenteLanzaExcepcion() {
		Reservas citas = new Reservas(5);
		try {
			citas.insertar(reserva1);
			citas.borrar(reserva2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Reservas(5);
		try {
			citas.insertar(reserva1);
			citas.insertar(reserva2);
			citas.borrar(reserva3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.borrar(null);
			fail(RESERVA_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.buscar(null);
			fail(RESERVA_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

}
