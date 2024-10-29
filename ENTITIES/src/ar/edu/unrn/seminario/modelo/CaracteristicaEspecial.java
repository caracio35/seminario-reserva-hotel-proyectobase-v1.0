package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class CaracteristicaEspecial {
	private String nombre;
	private String descripcion;
	private double precio;

	public CaracteristicaEspecial(String nombre , String descripcion , double preci) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = preci;
	}
	public CaracteristicaEspecial() {
		
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
	public void setnNombre(String nombre) {
		this.nombre = nombre ; 
	}
	public void setDecripcion(String descripcion) {
		this.descripcion = descripcion ;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicaEspecial other = (CaracteristicaEspecial) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

}
