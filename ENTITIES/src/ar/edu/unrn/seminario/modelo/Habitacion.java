package ar.edu.unrn.seminario.modelo;

import java.util.ArrayList;

public class Habitacion {
	private int cantidadDeCamas;
	private String descripcion;
	private double precio;
	private boolean habilitado;
	private int numHabitaciones;
	private ArrayList<CaracteristicaEspecial> caracteristicasEspeciales;

	public Habitacion(int cantidadDeCamas, String descripcion, int precio, boolean habilitado, int numHabitaciones,
			ArrayList<CaracteristicaEspecial> caracteristicasEspeciales) {
		this.cantidadDeCamas = cantidadDeCamas;
		this.descripcion = descripcion;
		this.precio = precio;
		this.habilitado = habilitado;
		this.numHabitaciones = numHabitaciones;
		this.caracteristicasEspeciales = caracteristicasEspeciales;
	}

	public double obtenerPrecioTotal() {
		double precioFinal = precio;
		for (CaracteristicaEspecial caracteristicaEspecial : caracteristicasEspeciales) {
			precioFinal += caracteristicaEspecial.getPrecio();
		}
		return precioFinal;
	}

	public int getCantidadDeCamas() {
		return cantidadDeCamas;
	}

	public void setCantidadDeCamas(int cantidadDeCamas) {
		this.cantidadDeCamas = cantidadDeCamas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public ArrayList<CaracteristicaEspecial> getCaracteristicasEspeciale() {
		return caracteristicasEspeciales;
	}

	public void setCaracteristicasEspeciale(ArrayList<CaracteristicaEspecial> caracteristicasEspeciale) {
		this.caracteristicasEspeciales = caracteristicasEspeciale;
	}

}
