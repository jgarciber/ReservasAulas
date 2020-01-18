package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProfesorTest {
	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre del profesor no puede ser nulo.";
	private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre del profesor no puede estar vacío.";
	private static final String ERROR_CORREO_NULO = "ERROR: El correo del profesor no puede ser nulo.";
	private static final String ERROR_CORREO_NO_VALIDO = "ERROR: El correo del profesor no es válido.";
	private static final String ERROR_TELEFONO_NO_VALIDO = "ERROR: El teléfono del profesor no es válido.";
	private static final String ERROR_COPIAR_PROFESOR_NULO = "ERROR: No se puede copiar un profesor nulo.";
	private static final String NOMBRE_INCORRECTO = "Debería haber saltado una excepción indicando que el nombre es incorrecto";
	private static final String CORREO_INCORRECTO = "Debería haber saltado una excepción indicando que el correo es incorrecto";
	private static final String TELEFONO_INCORRECTO = "Debería haber saltado una excepción indicando que el teléfono es incorrecto";
	private static final String PROFESOR_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un profesor nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String PROFESOR_NO_ESPERADO = "El profesor copiado debería ser el mismo que el pasado como parámetro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String CORREO_NO_ESPERADO = "El correo devuelto no es el mismo que el pasado al constructor.";
	private static final String TELEFONO_NO_ESPERADO = "El DNI devuelto no es el mismo que el pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto profesor.";
	private static final String NOMBRE_JRJR = "José Ramón Jiménez Reyes";
	private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
	private static final String TELEFONO_JRJR = "950112233";
	private static final String NOMBRE_ARDR = "Andrés Rubio Del Río";
	private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";
	private static final String TELEFONO_ARDR = "650112233";

	@Test
	public void constructorNombreValidoCorreoValidoCreaProfesorCorrectamente() {
		Profesor profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_JRJR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_JRJR));
		profesor = new Profesor(NOMBRE_ARDR, CORREO_ARDR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_ARDR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_ARDR));
	}
	
	@Test
	public void constructorNombreNoValidoCorreoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("", CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("  ", CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoCorreoNoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(NOMBRE_JRJR, null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "   ");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "a@a");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "@kk.es");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "pepe@kk.es.");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoCorreoValidoTelefonoValidoCreaProfesorCorrectamente() {
		Profesor profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, TELEFONO_JRJR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_JRJR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_JRJR));
		assertThat(TELEFONO_NO_ESPERADO, profesor.getTelefono(), is(TELEFONO_JRJR));
		profesor = new Profesor(NOMBRE_ARDR, CORREO_ARDR, TELEFONO_ARDR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_ARDR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_ARDR));
		assertThat(TELEFONO_NO_ESPERADO, profesor.getTelefono(), is(TELEFONO_ARDR));
	}

	@Test
	public void constructorNombreNoValidoCorreoValidoTelefonoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null, CORREO_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(null, CORREO_JRJR, null);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("", CORREO_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("  ", CORREO_JRJR, TELEFONO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoCorreoNoValidoTelefonoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(NOMBRE_JRJR, null, TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, null, null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "", TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "   ", TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "a@a", TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "@kk.es", TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "pepe@kk.es.", TELEFONO_JRJR);
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoCorreoValidoTelefonoNoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, "");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, "   ");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, "95012345");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, "9501234567");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR, "9501234567");
			fail(TELEFONO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TELEFONO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorProfesorValidoCopiaProfesorCorrectamente() {
		Profesor profesor1 = new Profesor(NOMBRE_JRJR, CORREO_JRJR, TELEFONO_JRJR);
		Profesor profesor2 = new Profesor(profesor1);
		assertThat(PROFESOR_NO_ESPERADO, profesor2, is(profesor1));
		assertThat(NOMBRE_NO_ESPERADO, profesor2.getNombre(), is(NOMBRE_JRJR));
		assertThat(CORREO_NO_ESPERADO, profesor2.getCorreo(), is(CORREO_JRJR));
		assertThat(TELEFONO_NO_ESPERADO, profesor2.getTelefono(), is(TELEFONO_JRJR));
	}
	
	@Test
	public void constructorProfesorNuloLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null);
			fail(PROFESOR_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_PROFESOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getProfesorFicticioCorreoValidoDevuelveProfesorConDichoCorreo() {
		Profesor profesor = Profesor.getProfesorFicticio(CORREO_JRJR);
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_JRJR));
	}
	
	@Test
	public void getProfesorFicticioCorreoNoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = Profesor.getProfesorFicticio(null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("   ");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("a@a");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("@kk.es");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("pepe@kk.es.");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Profesor profesor = new Profesor(NOMBRE_JRJR, CORREO_JRJR);
		assertThat(CADENA_NO_ESPERADA, profesor.toString(), is(String.format("nombre=%s, correo=%s", NOMBRE_JRJR, CORREO_JRJR)));
		profesor = new Profesor(NOMBRE_ARDR, CORREO_ARDR, TELEFONO_ARDR);
		assertThat(CADENA_NO_ESPERADA, profesor.toString(), is(String.format("nombre=%s, correo=%s, teléfono=%s", NOMBRE_ARDR, CORREO_ARDR, TELEFONO_ARDR)));
	}

}
