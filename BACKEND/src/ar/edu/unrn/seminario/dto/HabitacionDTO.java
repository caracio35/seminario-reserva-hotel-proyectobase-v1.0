package ar.edu.unrn.seminario.dto;


public class HabitacionDTO {
	
	private int cantidadDeCamas;
	private String descripcion;
	private double precio;
	private boolean habilitado;
	private int numHabitaciones;
	private String [] caracteristicasEspeciales;

	public HabitacionDTO(int cantidadDeCamas, String descripcion, double precio, boolean habilitado, int numHabitaciones,
			String [] caracteristicasEspeciales) {
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
	public void setNumHabitaciones(int numHabitaciones) {
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
	public int getNumHabitaciones() {
		return numHabitaciones;
	}
	public String[] getCaracteristicasEspeciale() {
		return caracteristicasEspeciales;
	}

}
