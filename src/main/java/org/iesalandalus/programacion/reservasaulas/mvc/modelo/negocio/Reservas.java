package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private int capacidad;
	private Reserva[] coleccionReservas;
	private int tamano;
	
	public Reservas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionReservas = new Reserva[capacidad];
		tamano = 0;
	}
	
	public Reserva[] get() {
		return copiaProfundaReservas();
	}
	
	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaReservas = new Reserva[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaReservas[i] = new Reserva(coleccionReservas[i]);
		}
		return copiaReservas;
	}
	
	public Reserva[] get(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		Reserva[] reservasProfesor = new Reserva[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reservasProfesor[j++] = new Reserva(coleccionReservas[i]);
			}
		}
		return reservasProfesor;
	}
	
	public Reserva[] get(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}
		Reserva[] reservasAula = new Reserva[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reservasAula[j++] = new Reserva(coleccionReservas[i]);
			}
		}
		return reservasAula;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		} 
		if (tamanoSuperado(indice)) {
			coleccionReservas[indice] = new Reserva(reserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un reserva igual.");
		}		
		
	}
	
	private int buscarIndice(Reserva reserva) {
		int indice = 0;
		boolean reservaEncontrado = false;
		while (!tamanoSuperado(indice) && !reservaEncontrado) {
			if (coleccionReservas[indice].equals(reserva)) {
				reservaEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}
	
	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}
	
	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(coleccionReservas[indice]);
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva igual.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionReservas[i] = coleccionReservas[i+1];
		}
		coleccionReservas[i] = null;
		tamano--;
	}

}
