package ar.edu.unrn.seminario.dto;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unrn.seminario.exception.CampoVacioExeption;

public class HabitacionDTO {

	private int cantidadDeCamas;
	private String descripcion;
	private double precio;
	private boolean habilitado;
	private String fechaHastaCuandoEstaDesactivado;
	private int numHabitaciones;
	private List<CaracteristicaEspecialDTO> caracteristicasEspeciales;

	public HabitacionDTO(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitaciones,List<CaracteristicaEspecialDTO> caracteristicasEspeciales, 
			String fechaHastaCuandoEstaDesactivado) {

		this.cantidadDeCamas = cantidadDeCamas;
		this.descripcion = descripcion;
		this.precio = precio;
		this.habilitado = habilitado;
		this.numHabitaciones = numHabitaciones;
		this.caracteristicasEspeciales = caracteristicasEspeciales;
		this.fechaHastaCuandoEstaDesactivado = fechaHastaCuandoEstaDesactivado;

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

	public void setCaracteristicasEspeciale(List<CaracteristicaEspecialDTO> caracteristicasEspeciale) {
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

	public List<CaracteristicaEspecialDTO> getCaracteristicasEspeciale() {
		return caracteristicasEspeciales;
	}

	public String getFechaHastaCuandoEstaDesactivado() {
		return fechaHastaCuandoEstaDesactivado;
	}

	public void setFechaHastaCuandoEstaDesactivado(String fechaHastaCuandoEstaDesactivado) {
		this.fechaHastaCuandoEstaDesactivado = fechaHastaCuandoEstaDesactivado;
	}

}
