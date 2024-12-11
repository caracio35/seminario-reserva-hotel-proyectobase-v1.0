package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class Rol {
	private int codigo;
	private String nombre;
	private boolean activo;

	public Rol() {

	}

	public Rol(int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.activo = true;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void activar() {
		this.activo = true;
	}

	public void desactivar() {
		this.activo = false;
	}



	@Override
	public int hashCode() {
		return Objects.hash(activo, codigo, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return activo == other.activo && codigo == other.codigo && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Rol [codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + "]";
	}

}
