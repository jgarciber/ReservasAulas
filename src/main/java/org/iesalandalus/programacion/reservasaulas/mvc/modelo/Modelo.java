package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;


public class Modelo {

	private static final int CAPACIDAD = 50;
	
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;
	
	public Modelo() {
		profesores = new Profesores(CAPACIDAD);
		aulas = new Aulas(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}
	
	public void insertar(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		Profesor profesor = profesores.buscar(reserva.getProfesor());
		if (profesor == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		}
		Aula aula = aulas.buscar(reserva.getAula());
		if (aula == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		reservas.insertar(new Reserva(profesor, aula, reserva.getPermanencia()));
	}
	
	public Profesor buscar(Profesor profesor) {
		return profesores.buscar(profesor);
	}
	
	public Aula buscar(Aula aula) {
		return aulas.buscar(aula);
	}
	
	public Reserva buscar(Reserva reserva) {
		return reservas.buscar(reserva);
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}
	
	public Aula[] getAulas() {
		return aulas.get();
	}
	
	public Reserva[] getReservas() {
		return reservas.get();
	}
	
	public Reserva[] getReservas(Profesor profesor) {
		return reservas.get(profesor);
	}
	
	public Reserva[] getReservas(Aula aula) {
		return reservas.get(aula);
	}

}

