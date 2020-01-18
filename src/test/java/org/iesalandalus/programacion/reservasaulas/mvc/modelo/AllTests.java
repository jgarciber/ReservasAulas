package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.AulaTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.ProfesorTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.ReservaTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.TramoTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.AulasTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ProfesoresTest;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ReservasTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProfesorTest.class, AulaTest.class, TramoTest.class, PermanenciaTest.class, ReservaTest.class,
	ProfesoresTest.class, AulasTest.class, ReservasTest.class,
	ModeloTest.class })
public class AllTests {

}
