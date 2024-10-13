package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Habitacion {
	private int cantidadDeCamas;
	private String descripcion;
	private double precio;
	private boolean habilitado;
	private LocalDate fechaHastaCuandoEstaDesactivado;
	private int numHabitaciones;
	private ArrayList<CaracteristicaEspecial> caracteristicasEspeciales;

	public Habitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado, int numHabitaciones,
			ArrayList<CaracteristicaEspecial> caracteristicasEspeciales) {
		this.cantidadDeCamas = cantidadDeCamas;
		this.descripcion = descripcion;
		this.precio = precio;
		this.habilitado = habilitado;
		this.numHabitaciones = numHabitaciones;
		this.caracteristicasEspeciales = caracteristicasEspeciales;
		this.fechaHastaCuandoEstaDesactivado = null;
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

	@Override
	public int hashCode() {
		return Objects.hash(cantidadDeCamas, caracteristicasEspeciales, descripcion, habilitado, numHabitaciones,
				precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitacion other = (Habitacion) obj;
		return cantidadDeCamas == other.cantidadDeCamas
				&& Objects.equals(caracteristicasEspeciales, other.caracteristicasEspeciales)
				&& Objects.equals(descripcion, other.descripcion) && habilitado == other.habilitado
				&& numHabitaciones == other.numHabitaciones
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

	public LocalDate getFechaHastaCuandoEstaDesactivado() {
		return fechaHastaCuandoEstaDesactivado;
	}

	public void setFechaHastaCuandoEstaDesactivado(LocalDate fechaHastaCuandoEstaDesactivado) {
		this.fechaHastaCuandoEstaDesactivado = fechaHastaCuandoEstaDesactivado;
	}

}
