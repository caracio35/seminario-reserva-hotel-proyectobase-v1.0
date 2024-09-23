package ar.edu.unrn.seminario.modelo;

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

}
