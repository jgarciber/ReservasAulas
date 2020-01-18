package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Profesor {
	 
	private static final String ER_TELEFONO = "[96]\\d{8}";
	private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
	
	private String nombre;
	private String correo;
	private String telefono;
	
	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}
	
	public Profesor(String nombre, String correo, String telefono) {
		this(nombre, correo);
		setTelefono(telefono);
	}
	
	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}
		setNombre(profesor.getNombre());
		setCorreo(profesor.getCorreo());
		setTelefono(profesor.getTelefono());
	}
	
	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}
		this.correo = correo;
	}
	
	public void setTelefono(String telefono) {
		if (telefono != null && !telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public static Profesor getProfesorFicticio(String correo) {
		return new Profesor("Bob Esponja", correo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profesor)) {
			return false;
		}
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		String cadenaTelefono = (telefono == null) ? "" : ", teléfono=" + telefono;
		return String.format("nombre=%s, correo=%s%s", nombre, correo, cadenaTelefono);
	}
	
}
