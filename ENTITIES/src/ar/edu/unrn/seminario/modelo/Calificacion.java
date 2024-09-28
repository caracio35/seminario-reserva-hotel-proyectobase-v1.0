package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class Calificacion {
	private int valor;
	private String comentario;

	public Calificacion(int valor, String comentario) {
		this.valor = valor;
		this.comentario = comentario;
	}

	int getValor() {
		return valor;
	}

	void setValor(int valor) {
		this.valor = valor;
	}

	String getComentario() {
		return comentario;
	}

	void setComentario(String comentario) {
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
