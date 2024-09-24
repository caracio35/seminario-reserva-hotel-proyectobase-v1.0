package ar.edu.unrn.seminario.dto;

public class CaracteristicaEspecialDTO {

	private String nombre; 
	private String descripcion; 
	private double precio ; 
	
	public CaracteristicaEspecialDTO(String nombre ,String descripcion, double precio) {
		this.nombre = nombre ; 
		this.descripcion = descripcion ; 
		this.precio = precio ; 
	}
	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion ; 
	}
	public void setPrecio(double precio) {
		this.precio = precio; 
	}
	public String getNombre() {
		return nombre ; 
	}
	public String getDescricion() {
		return descripcion ; 
	}
	public double getPrecio() {
		return precio ;
	}
	
}
