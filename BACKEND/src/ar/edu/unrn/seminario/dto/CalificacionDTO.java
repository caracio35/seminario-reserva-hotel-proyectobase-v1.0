package ar.edu.unrn.seminario.dto;

public class CalificacionDTO {
	
	private int valor;
	private String comentario ; 
	
	public CalificacionDTO(int valor , String comentario) {
		this.valor = valor ; 
		this.comentario = comentario; 
	}
	void setValor(int valor) {
		this.valor = valor ; 
	}
	void setComentario(String comentario) {
		this.comentario = comentario ;
	}
	int getValor() {
		return valor ; 
	}
	String getComentario() {
		return comentario ; 
	}
}
