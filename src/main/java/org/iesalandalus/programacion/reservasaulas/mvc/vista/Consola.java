package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {	
	}
	
	public static void mostrarMenu() {
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Profesor leerProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce el teléfono del profesor: ");
		String telefono = Entrada.cadena();
		Profesor profesor = null;
		if (telefono == null || telefono.trim().equals("")) {
			profesor = new Profesor(nombre, correo);
		} else {
			profesor = new Profesor(nombre, correo, telefono);
		}
		return profesor;
	}
	
	public static Profesor leerProfesorFicticio() {
		System.out.print("Introduce el correo del profesor: ");
		return Profesor.getProfesorFicticio(Entrada.cadena());
	}
	
	public static Aula leerAula() {
		System.out.print("Introduce el nombre del aula: ");
		String nombre = Entrada.cadena();
		return new Aula(nombre);
	}
	
	public static Permanencia leerPermanencia() {
		return new Permanencia(leerDia(), leerTramo());
	}
	
	private static LocalDate leerDia() {
		LocalDate dia = null;
		String cadenaFormato = "dd/MM/yyyy";
		DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern(cadenaFormato);
		System.out.printf("Introduce el día (%s): ", formatoDia);
		String diaLeido = Entrada.cadena();
		try {
			dia = LocalDate.parse(diaLeido, formatoDia);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato del día no es correcto.");
		}
		return dia;
	}
	
	private static Tramo leerTramo( ) {
		System.out.print("Introduce el tramo de la reserva (0.- Mañana, 1.- Tarde): ");
		int tramoLeido = Entrada.entero();
		Tramo tramo = null;
		if (tramoLeido < 0 || tramoLeido >= Tramo.values().length) {
			System.out.println("ERROR: La opción elegida no corresponde con ningún tramo.");
		} else {
			tramo = Tramo.values()[tramoLeido];
		}
		return tramo;
	}
	
	public static Reserva leerReserva() {
		Profesor profesor = leerProfesorFicticio();
		Aula aula = leerAula();
		Permanencia permanencia = leerPermanencia();
		return new Reserva(profesor, aula, permanencia);
	}
	
	public static Reserva leerReservaFicticia() {
		return Reserva.getReservaFicticia(leerAula(), leerPermanencia());
	}

}
