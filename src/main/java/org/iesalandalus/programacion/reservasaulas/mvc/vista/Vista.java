package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;


public class Vista {
	
	private Controlador controlador;
	
	public Vista() {
		Opcion.setVista(this);
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void comenzar() {
		Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void terminar() {
		controlador.terminar();
	}
	
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertar(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscar(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString() : "No existe dicho profesor.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrar(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de Profesores");
		Profesor[] profesores = controlador.getProfesores();
		if (profesores[0] != null) {
			for (Profesor profesor : profesores) {
				if (profesor != null) 
					System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que mostrar.");
		}
	}
	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertar(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		Aula aula;
		try {
			aula = controlador.buscar(Consola.leerAula());
			String mensaje = (aula != null) ? aula.toString() : "No existe dicha aula.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrar(Consola.leerAula());
			System.out.println("Aula borrada satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarAulas() {
		Consola.mostrarCabecera("Listado de Aulas");
		Aula[] aulas = controlador.getAulas();
		if (aulas[0] != null) {
			for (Aula aula : aulas) {
				if (aula != null) 
					System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que mostrar.");
		}
	}
	
	public void insertarReserva() {
		Consola.mostrarCabecera("Insertar Reserva");
		try {
			Reserva reserva = Consola.leerReserva();
			controlador.insertar(reserva);
			System.out.println("Reserva insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarReserva() {
		Consola.mostrarCabecera("Buscar Reserva");
		Reserva reserva;
		try {
			reserva = controlador.buscar(Consola.leerReservaFicticia());
			String mensaje = (reserva != null) ? reserva.toString() : "No existe dicha reserva.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarReserva() {
		Consola.mostrarCabecera("Borrar Reserva");
		try {
			controlador.borrar(Consola.leerReservaFicticia());
			System.out.println("Reserva borrada satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		Consola.mostrarCabecera("Listado de Reservas");
		Reserva[] reservas = controlador.getReservas();
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null) 
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que mostrar.");
		}
	}
	
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		Reserva[] reservas = controlador.getReservas(Consola.leerProfesorFicticio());
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null) 
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicho profesor, que mostrar.");
		}
	}
	
	public void listarReservasAula() {
		Consola.mostrarCabecera("Listado de Reservas por Aula");
		Reserva[] reservas = controlador.getReservas(Consola.leerAula());
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null) 
					System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha aula, que mostrar.");
		}
	}

}
