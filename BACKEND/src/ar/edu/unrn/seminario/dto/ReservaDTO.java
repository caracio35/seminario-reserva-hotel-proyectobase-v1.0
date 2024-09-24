package ar.edu.unrn.seminario.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Servicio;

public class ReservaDTO {
	
	private ArrayList<Habitacion> habitaciones;
	private UsuarioDTO usuario;
	private LocalDate fechaDeInicio;
	private LocalDate fechaDESalida;
	private int cantidadDePersonas;
	private ArrayList<Servicio> servicios;
	private boolean checkIn;
	private boolean checkOut;
	private FacturaDTO factura;
	private LocalDate fechaDeReserva;
	private CalificacionDTO calificacion;
	private double saldoFavor;
	private boolean pagoMinimo;

	public ReservaDTO(ArrayList<Habitacion> habitaciones, UsuarioDTO usuario, LocalDate fechaDeInicio,
			LocalDate fechaDESalida, int cantidadDePersonas, ArrayList<Servicio> servicios, boolean checkIn,
			boolean checkOut, FacturaDTO factura, LocalDate fechaDeReserva, CalificacionDTO calificacion,
			boolean pagoMinimo) {
		this.habitaciones = habitaciones;
		this.usuario = usuario;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDESalida = fechaDESalida;
		this.cantidadDePersonas = cantidadDePersonas;
		this.servicios = servicios;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.factura = factura;
		this.fechaDeReserva = fechaDeReserva;
		this.calificacion = calificacion;
		this.saldoFavor = 0;
		this.pagoMinimo = pagoMinimo;
	}
	public void setHabitacion(ArrayList<Habitacion> habitacion) {
		this.habitaciones = habitacion;
	}
	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
	public void setFechaDESalida(LocalDate fechaDESalida) {
		this.fechaDESalida = fechaDESalida;
	}
	public void setCantidadDePersonas(int cantidadDePersonas) {
		this.cantidadDePersonas = cantidadDePersonas;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}
	public void setSaldoFavor(double saldoFavor) {
		this.saldoFavor = saldoFavor; 
	}
	public void setPagoMinimo(boolean pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}
	public LocalDate getFechaDeSalida() {
		return fechaDESalida;
	}
	public int getCantidadDePersonas() {
		return cantidadDePersonas;
	}
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	public ArrayList<Habitacion> getHabitacion() {
		return habitaciones;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public double getSaldoFavor() {
		return saldoFavor;
	}
	public FacturaDTO getFactura() {
		return factura;
	}
	public boolean getPagoMinimo() {
		return pagoMinimo; 
	}
	public LocalDate getFechaDeReserva() {
		return fechaDeReserva;
	}
	public CalificacionDTO getCalificacion() {
		return calificacion;
	}
	public boolean isCheckOut() {
		return checkOut;
	}
	public boolean isCheckIn() {
		return checkIn;
	}
	
}
