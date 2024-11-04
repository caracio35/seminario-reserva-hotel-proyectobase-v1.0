package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class Calificacion {
	private int valor;
	private String comentario;
	private int idReservaFK;

	public Calificacion(int valor, String comentario, int idReservaFK) {
		this.valor = valor;
		this.comentario = comentario;
		this.idReservaFK = idReservaFK;
	}

	public int getIdReservaFK() {
		return idReservaFK;
	}

	public void getIdReservaFK(int idReservaFK) {
		this.idReservaFK = idReservaFK;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentario, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calificacion other = (Calificacion) obj;
		return Objects.equals(comentario, other.comentario) && valor == other.valor;
	}

}
