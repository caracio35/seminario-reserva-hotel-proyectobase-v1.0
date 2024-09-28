package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class Reserva {
	private int id ; 
	private ArrayList<Habitacion> habitaciones;
	private Usuario usuario;
	private LocalDate fechaDeInicio;
	private LocalDate fechaDESalida;
	private int cantidadDePersonas;
	private ArrayList<Servicio> servicios;
	private boolean checkIn;
	private boolean checkOut;
	private Factura factura;
	private LocalDate fechaDeReserva;
	private Calificacion calificacion;
	private double saldoFavor;
	private boolean pagoMinimo;

	public Reserva(int id , ArrayList<Habitacion> habitaciones, Usuario usuario, LocalDate fechaDeInicio,
			LocalDate fechaDESalida, int cantidadDePersonas, ArrayList<Servicio> servicios, boolean checkIn,
			boolean checkOut, Factura factura, LocalDate fechaDeReserva, Calificacion calificacion,
			boolean pagoMinimo){
		this.id = id;
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

	public void modificarReserva(ArrayList<Habitacion> habitaciones, LocalDate fechaDeInicio, LocalDate fechaDESalida,
			int cantidadDePersonas, ArrayList<Servicio> servicios) {
		double precioAnterior = obtenerPrecioTotal();
		this.habitaciones = habitaciones;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDESalida = fechaDESalida;
		this.cantidadDePersonas = cantidadDePersonas;
		this.servicios = servicios;
		double precioFinal = obtenerPrecioTotal() - precioAnterior;
		if (precioFinal < 0.0) {
			this.saldoFavor += precioFinal * -1;
		}

	}

	public static int calcularDiferenciaDias(LocalDate fecha1, LocalDate fecha2) {
		return (int) ChronoUnit.DAYS.between(fecha1, fecha2);
	}

	public double obtenerPrecioTotal() {
		double precioTotal = 0;
		for (Habitacion habitacion : habitaciones) {
			precioTotal += habitacion.obtenerPrecioTotal();
		}
		for (Servicio servicio : servicios) {
			precioTotal += servicio.getPrecio();
		}
		return precioTotal * calcularDiferenciaDias(fechaDeInicio, fechaDESalida);
	}

	public double obtenerPrecioMinimo() {
		double precioMinimo = obtenerPrecioTotal() * 0.15;
		return precioMinimo;
	}

	public ArrayList<Habitacion> getHabitacion() {
		return habitaciones;
	}

	public void setHabitacion(ArrayList<Habitacion> habitacion) {
		this.habitaciones = habitacion;
	}

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public LocalDate getFechaDESalida() {
		return fechaDESalida;
	}

	public void setFechaDESalida(LocalDate fechaDESalida) {
		this.fechaDESalida = fechaDESalida;
	}

	public int getCantidadDePersonas() {
		return cantidadDePersonas;
	}

	public void setCantidadDePersonas(int cantidadDePersonas) {
		this.cantidadDePersonas = cantidadDePersonas;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	public boolean isCheckOut() {
		return checkOut;
	}

	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Factura getFactura() {
		return factura;
	}

	public LocalDate getFechaDeReserva() {
		return fechaDeReserva;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}
	public int getId() {
		return id;
	}
	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calificacion, cantidadDePersonas, checkIn, checkOut, factura, fechaDESalida, fechaDeInicio,
				fechaDeReserva, habitaciones, id, pagoMinimo, saldoFavor, servicios, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(calificacion, other.calificacion) && cantidadDePersonas == other.cantidadDePersonas
				&& checkIn == other.checkIn && checkOut == other.checkOut && Objects.equals(factura, other.factura)
				&& Objects.equals(fechaDESalida, other.fechaDESalida)
				&& Objects.equals(fechaDeInicio, other.fechaDeInicio)
				&& Objects.equals(fechaDeReserva, other.fechaDeReserva)
				&& Objects.equals(habitaciones, other.habitaciones) && id == other.id && pagoMinimo == other.pagoMinimo
				&& Double.doubleToLongBits(saldoFavor) == Double.doubleToLongBits(other.saldoFavor)
				&& Objects.equals(servicios, other.servicios) && Objects.equals(usuario, other.usuario);
	}
	
}
