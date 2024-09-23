package ar.edu.unrn.seminario.modelo;

public class Servicio {
	private int precio;
	private String descripcion;

	public Servicio(int precio, String descripcion) {
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
