package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {

	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("Hasta luego Lucas!!!");
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertar(profesor);
	}
	
	public void insertar(Aula aula) throws OperationNotSupportedException {
		modelo.insertar(aula);
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		modelo.insertar(reserva);
	}
	
	public Profesor buscar(Profesor profesor) {
		return modelo.buscar(profesor);
	}
	
	public Aula buscar(Aula aula) {
		return modelo.buscar(aula);
	}
	
	public Reserva buscar(Reserva reserva) {
		return modelo.buscar(reserva);
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrar(profesor);
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		modelo.borrar(aula);
	}
	
	public void borrar(Reserva aula) throws OperationNotSupportedException {
		modelo.borrar(aula);
	}
	
	public Profesor[] getProfesores() {
		return modelo.getProfesores();
	}
	
	public Aula[] getAulas() {
		return modelo.getAulas();
	}
	
	public Reserva[] getReservas() {
		return modelo.getReservas();
	}
	
	public Reserva[] getReservas(Profesor profesor) {
		return modelo.getReservas(profesor);
	}
	
	public Reserva[] getReservas(Aula aula) {
		return modelo.getReservas(aula);
	}

}
