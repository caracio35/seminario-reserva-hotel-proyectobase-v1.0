package ar.edu.unrn.seminario.dto;

public class CalificacionDTO {
	
	private int valor;
	private String comentario ; 
	
	public CalificacionDTO(int valor , String comentario) {
		this.valor = valor ; 
		this.comentario = comentario; 
	}
	public void setValor(int valor) {
		this.valor = valor ; 
	}
	public void setComentario(String comentario) {
		this.comentario = comentario ;
	}
	public int getValor() {
		return valor ; 
	}
	public String getComentario() {
		return comentario ; 
	}
}
