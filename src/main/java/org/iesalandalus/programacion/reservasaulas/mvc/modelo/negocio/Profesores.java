package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private int capacidad;
	private Profesor[] coleccionProfesores;
	private int tamano;
	
	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionProfesores = new Profesor[capacidad];
		tamano = 0;
	}
	
	public Profesor[] get() {
		return copiaProfundaProfesores();
	}
	
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaProfesores[i] = new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesores;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} 
		if (tamanoSuperado(indice)) {
			coleccionProfesores[indice] = new Profesor(profesor);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		}		
		
	}
	
	private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (!tamanoSuperado(indice) && !profesorEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				profesorEncontrado = true;
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
	
	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionProfesores[i] = coleccionProfesores[i+1];
		}
		coleccionProfesores[i] = null;
		tamano--;
	}

}
