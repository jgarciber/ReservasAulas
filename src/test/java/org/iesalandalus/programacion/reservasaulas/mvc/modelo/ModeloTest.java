package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModeloTest {
	
	private static final String ERROR_RESERVA_NULA = "ERROR: No se puede insertar una reserva nula.";
	private static final String ERROR_RESERVA_PROFESOR_NO_EXISTENTE = "ERROR: No existe ningún profesor con ese DNI.";
	private static final String ERROR_RESERVA_AULA_NO_EXISTENTE = "ERROR: No existe ningún aula con ese nombre.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String EXCEPCION_ESPERADA = "Debería haber saltado la excepción.";
	
	private static Profesor profesorExistente;
	private static Profesor profesorNoExistente;
	private static Aula aulaExistente;
	private static Aula aulaNoExistente;
	private static Permanencia permanencia;
	private static Reserva reserva;
		
	@InjectMocks private Modelo modelo;
	
	@Mock private Profesores profesoresSimulados;
	@Mock private Aulas aulasSimuladas;
	@Mock private Reservas reservasSimuladas;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		//MockitoAnnotations.initMocks(this);
		profesorExistente = Profesor.getProfesorFicticio("bob@gmail.com");
		profesorNoExistente = Profesor.getProfesorFicticio("patricio@gmail.com");
		aulaExistente = new Aula("Aula 1");
		aulaNoExistente = new Aula("Aula 2");
		permanencia = new Permanencia(LocalDate.now(), Tramo.MANANA);
		reserva = new Reserva(profesorExistente, aulaExistente, permanencia);
	}
	
	@Test
	public void insertarProfesorLlamaProfesoresInsertar() {
		try {
			modelo.insertar(profesorExistente);
			verify(profesoresSimulados).insertar(profesorExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarProfesorLlamaProfesoresBuscar() {
		modelo.buscar(profesorExistente);
		verify(profesoresSimulados).buscar(profesorExistente);
	}
	
	@Test
	public void borrarProfesorLlamaProfesoresBorrar() {
		try {
			modelo.borrar(profesorExistente);
			verify(profesoresSimulados).borrar(profesorExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getProfesoresLlamaProfesoresGet() {
		modelo.getProfesores();
		verify(profesoresSimulados).get();
	}
	
	@Test
	public void insertarAulaLlamaAulasInsertar() {
		try {
			modelo.insertar(aulaExistente);
			verify(aulasSimuladas).insertar(aulaExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarAulaLlamaAulasBuscar() {
		modelo.buscar(aulaExistente);
		verify(aulasSimuladas).buscar(aulaExistente);
	}
	
	@Test
	public void borrarAulaLlamaAulasBorrar() {
		try {
			modelo.borrar(aulaExistente);
			verify(aulasSimuladas).borrar(aulaExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getAulasLlamaAulasGet() {
		modelo.getAulas();
		verify(aulasSimuladas).get();
	}

	@Test
	public void insertarReservaValidaLlamaProfesoresBuscarAulasBuscarReservasInsertar() {
		try {
			simularComportamientoInsertarReservaValida();
			InOrder orden = Mockito.inOrder(profesoresSimulados, aulasSimuladas, reservasSimuladas);
			modelo.insertar(reserva);
			orden.verify(profesoresSimulados).buscar(profesorExistente);
			orden.verify(aulasSimuladas).buscar(aulaExistente);
			orden.verify(reservasSimuladas).insertar(reserva);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoInsertarReservaValida() throws OperationNotSupportedException {
		when(profesoresSimulados.buscar(profesorExistente)).thenReturn(profesorExistente);
		when(aulasSimuladas.buscar(aulaExistente)).thenReturn(aulaExistente);
	}
	
	@Test
	public void insertarReservaProfesorNoExistenteLanzaExcepcion() {
		Reserva reservaProfesorNoExistente = new Reserva(profesorNoExistente, aulaExistente, permanencia);
		try {
			simularComportamientoInsertarReservaProfesorNoExistente();
			modelo.insertar(reservaProfesorNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_PROFESOR_NO_EXISTENTE));
		}
	}
	
	private void simularComportamientoInsertarReservaProfesorNoExistente() throws OperationNotSupportedException {
		when(profesoresSimulados.buscar(profesorNoExistente)).thenReturn(null);
	}
	
	@Test
	public void insertarReservaAulaNoExistenteLanzaExcepcion() {
		Reserva reservaAulaNoExistente = new Reserva(profesorExistente, aulaNoExistente, permanencia);
		try {
			simularComportamientoInsertarReservaAulaNoExistente();
			modelo.insertar(reservaAulaNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_AULA_NO_EXISTENTE));
		}
	}
	
	private void simularComportamientoInsertarReservaAulaNoExistente() throws OperationNotSupportedException {
		when(profesoresSimulados.buscar(profesorExistente)).thenReturn(profesorExistente);
		when(aulasSimuladas.buscar(aulaNoExistente)).thenReturn(null);
	}
	
	@Test
	public void insertarReservaNulaLanzaExcepcion() {
		Reserva reservaNula = null;
		try {
			modelo.insertar(reservaNula);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_NULA));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarReservaLlamaReservasBuscar() {
		modelo.buscar(reserva);
		verify(reservasSimuladas).buscar(reserva);
	}
	
	@Test
	public void borrarReservaLlamaReservasBorrar() {
		try {
			modelo.borrar(reserva);
			verify(reservasSimuladas).borrar(reserva);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getReservasLlamaReservasGet() {
		modelo.getReservas();
		verify(reservasSimuladas).get();
	}
	
	@Test
	public void getReservasProfesorLlamaReservasGetConParametroProfesor() {
		modelo.getReservas(profesorExistente);
		verify(reservasSimuladas).get(profesorExistente);
	}
	
	@Test
	public void getReservasAulaLlamaReservasGetConParametroAula() {
		modelo.getReservas(aulaExistente);
		verify(reservasSimuladas).get(aulaExistente);
	}
	
}
