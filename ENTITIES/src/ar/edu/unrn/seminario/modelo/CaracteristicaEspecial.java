package ar.edu.unrn.seminario.modelo;

public class CaracteristicaEspecial {
	private String nombre;
	private String descripcion;
	private double precio;

	public CaracteristicaEspecial(String nombre, String descripcion, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
