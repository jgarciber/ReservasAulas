package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {

	private LocalDate dia;
	private Tramo tramo;

	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}
	
	public Permanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}
		setDia(permanencia.getDia());
		setTramo(permanencia.getTramo());
	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		this.dia = dia;
	}

	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null) {
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		}
		this.tramo = tramo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Permanencia)) {
			return false;
		}
		Permanencia other = (Permanencia) obj;
		return Objects.equals(dia, other.dia) && tramo == other.tramo;
	}

	@Override
	public String toString() {
		return String.format("día=%s, tramo=%s", dia.format(FORMATO_DIA), tramo);
	}

}
