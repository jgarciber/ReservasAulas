package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProfesoresTest {

	private static final String ERROR_CAPACIDAD_NO_CORRECTA = "ERROR: La capacidad debe ser mayor que cero.";
	private static final String ERROR_INSERTAR_PROFESOR_NULO = "ERROR: No se puede insertar un profesor nulo.";
	private static final String ERROR_BORRAR_PROFESOR_NULO = "ERROR: No se puede borrar un profesor nulo.";
	private static final String ERROR_BUSCAR_PROFESOR_NULO = "ERROR: No se puede buscar un profesor nulo.";
	private static final String ERROR_NO_MAS_PROFESORES = "ERROR: No se aceptan más profesores.";
	private static final String ERROR_PROFESOR_EXISTE = "ERROR: Ya existe un profesor con ese DNI.";
	private static final String ERROR_PROFESOR_BORRAR_NO_EXISTE = "ERROR: No existe ningún profesor con ese DNI.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String PROFESOR_NULO = "Debería haber saltado una excepción indicando que no se puede operar con un profesor con DNI nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String OPERACION_NO_REALIZADA = "La operación no la ha realizado correctamente.";
	private static final String PROFESORES_NO_CREADOS = "Debería haber creado los profesores correctamente.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String PROFESOR_NO_ESPERADO = "El profesor devuelto no es la que debería ser.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	
	private static Profesor profesor1;
	private static Profesor profesor2;
	private static Profesor profesor3;
	private static Profesor profesorRepetido;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		profesor1 = Profesor.getProfesorFicticio("bob@gmail.com");
		profesor2 = Profesor.getProfesorFicticio("patricio@gmail.com");
		profesor3 = Profesor.getProfesorFicticio("calamardo@gmail.com");
		profesorRepetido = new Profesor(profesor1);
	}
	
	@Test
	public void constructorCapacidadValidaCreaProfesoresCorrectamente() {
		Profesores profesores = new Profesores(5);
		assertThat(PROFESORES_NO_CREADOS, profesores, not(nullValue()));
		assertThat(PROFESORES_NO_CREADOS, profesores.getCapacidad(), is(5));
		assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(0));
	}
	
	@Test
	public void constructorCapacidadNoValidaLanzaExcepcion() {
		Profesores profesores = null;
		try {
			profesores = new Profesores(0);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesores, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesores = new Profesores(-1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesores, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarProfesorValidoConProfesoresVaciosInsertaProfesorCorrectamente() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			Profesor[] copiaProfesores = profesores.get();
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor1), not(sameInstance(profesor1)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[0], is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[0], not(sameInstance(profesor1)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosProfesoresValidosInsertaProfesoresCorrectamente() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			Profesor[] copiaProfesores = profesores.get();
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor1), not(sameInstance(profesor1)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[0], is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[0], not(sameInstance(profesor1)));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor2), not(sameInstance(profesor2)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[1], is(profesor2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[1], not(sameInstance(profesor2)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresProfesoresValidosInsertaProfesoresCorrectamente() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			Profesor[] copiaProfesores = profesores.get();
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(3));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor1), not(sameInstance(profesor1)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[0], is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[0], not(sameInstance(profesor1)));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor2), not(sameInstance(profesor2)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[1], is(profesor2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[1], not(sameInstance(profesor2)));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor3), is(profesor3));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor3), not(sameInstance(profesor3)));
			assertThat(OPERACION_NO_REALIZADA, copiaProfesores[2], is(profesor3));
			assertThat(REFERENCIA_NO_ESPERADA, copiaProfesores[2], not(sameInstance(profesor3)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarProfesorNuloLanzaExcepcion() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(null);
			fail(PROFESOR_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_PROFESOR_NULO));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarProfesorRepetidoLanzaExcepcion() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			profesores.insertar(profesorRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor2);
			profesores.insertar(profesor1);
			profesores.insertar(profesor3);
			profesores.insertar(profesorRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			profesores.insertar(profesor1);
			profesores.insertar(profesorRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarProfesorValidoConProfesoresLlenosLanzaExcepcion() {
		Profesores profesores = new Profesores(2);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NO_MAS_PROFESORES));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor1), not(sameInstance(profesor1)));
			assertThat(OPERACION_NO_REALIZADA, profesores.get()[0], is(profesor1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
			assertThat(REFERENCIA_NO_ESPERADA, profesores.buscar(profesor2), not(sameInstance(profesor2)));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarProfesorExistenteBorraProfesorCorrectamente() throws OperationNotSupportedException {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.borrar(profesor1);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(0));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.borrar(profesor1);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.borrar(profesor2);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			profesores.borrar(profesor1);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(nullValue()));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor3), is(profesor3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			profesores.borrar(profesor2);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(nullValue()));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor3), is(profesor3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.insertar(profesor2);
			profesores.insertar(profesor3);
			profesores.borrar(profesor3);
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(2));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor3), is(nullValue()));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor1), is(profesor1));
			assertThat(PROFESOR_NO_ESPERADO, profesores.buscar(profesor2), is(profesor2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarProfesorNoExistenteLanzaExcepcion() {
		Profesores citas = new Profesores(5);
		try {
			citas.insertar(profesor1);
			citas.borrar(profesor2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Profesores(5);
		try {
			citas.insertar(profesor1);
			citas.insertar(profesor2);
			citas.borrar(profesor3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarProfesorNuloLanzaExcepcion() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.borrar(null);
			fail(PROFESOR_NULO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_PROFESOR_NULO));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarProfesorNuloLanzaExcepcion() {
		Profesores profesores = new Profesores(5);
		try {
			profesores.insertar(profesor1);
			profesores.buscar(null);
			fail(PROFESOR_NULO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_PROFESOR_NULO));
			assertThat(TAMANO_NO_ESPERADO, profesores.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

}
