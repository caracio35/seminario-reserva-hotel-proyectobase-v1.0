package ar.edu.unrn.seminario.dto;

public class ServicioDTO {
	private double precio;
	private String descripcion;
	
	public ServicioDTO(int precio, String descripcion) {
		this.precio = precio;
		this.descripcion = descripcion;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion ; 
	}
	public double getPrecio() {
		return precio;
	}
	public String getDescripcion() {
		return descripcion;
	}

}
