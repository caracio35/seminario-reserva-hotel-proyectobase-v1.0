package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class Servicio {
	private int IdServico;
	private String nombre;
	private double precio;
	private String descripcion;

	public Servicio(int IdServico, String nombre, double precio, String descripcion) {
		this.IdServico = IdServico;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public String getNombre() {
		return nombre;

	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdServicio() {
		return IdServico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdServico, descripcion, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return IdServico == other.IdServico && Objects.equals(descripcion, other.descripcion)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

}
