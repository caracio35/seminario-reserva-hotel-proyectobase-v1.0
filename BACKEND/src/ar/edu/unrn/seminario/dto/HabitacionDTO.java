package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.CampoVacioExeption;

public class HabitacionDTO {

	private int cantidadDeCamas;
	private String descripcion;
	private double precio;
	private boolean habilitado;
	private int numHabitaciones;
	private String[] caracteristicasEspeciales;

	public HabitacionDTO(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitaciones,
			String[] caracteristicasEspeciales) throws CampoVacioExeption {
		if (descripcion.isEmpty()) {
			throw new CampoVacioExeption("El campo descripcion no puede ser vacio");
		}
		if (descripcion.equals(" ")) {
			throw new CampoVacioExeption("El campo descripcion no puede ser vacio");
		}
		this.cantidadDeCamas = cantidadDeCamas;
		this.descripcion = descripcion;
		this.precio = precio;
		this.habilitado = habilitado;
		this.numHabitaciones = numHabitaciones;
		this.caracteristicasEspeciales = caracteristicasEspeciales;
	}

	public void setCantidadDeCamas(int cantidadDeCamas) {
		this.cantidadDeCamas = cantidadDeCamas;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void setNumHabitacion(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public void setCaracteristicasEspeciale(String[] caracteristicasEspeciale) {
		this.caracteristicasEspeciales = caracteristicasEspeciale;
	}

	public int getCantidadDeCamas() {
		return cantidadDeCamas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public int getNumHabitacion() {
		return numHabitaciones;
	}

	public String[] getCaracteristicasEspeciale() {
		return caracteristicasEspeciales;
	}

}
