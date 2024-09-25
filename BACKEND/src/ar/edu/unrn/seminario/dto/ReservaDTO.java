package ar.edu.unrn.seminario.dto;

public class ReservaDTO {
	
	private int [] habitaciones;
	private String usuario;
	private String fechaDeInicio;
	private String fechaDESalida;
	private int cantidadDePersonas;
	private String [] servicios;
	private boolean checkIn;
	private boolean checkOut;
	private int  factura;
	private String fechaDeReserva;
	private String [] calificacion;
	private double saldoFavor;
	private boolean pagoMinimo;

	public ReservaDTO(int[] habitaciones, String usuario, String fechaDeInicio,
			String fechaDESalida, int cantidadDePersonas, String[] servicios, boolean checkIn,
			boolean checkOut, int factura, String fechaDeReserva, String[] calificacion,
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
	public void setHabitacion(int[] habitacion) {
		this.habitaciones = habitacion;
	}
	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
	public void setFechaDESalida(String fechaDESalida) {
		this.fechaDESalida = fechaDESalida;
	}
	public void setCantidadDePersonas(int cantidadDePersonas) {
		this.cantidadDePersonas = cantidadDePersonas;
	}
	public void setServicios(String[] servicios) {
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
	public String getFechaDeInicio() {
		return fechaDeInicio;
	}
	public String getFechaDeSalida() {
		return fechaDESalida;
	}
	public int getCantidadDePersonas() {
		return cantidadDePersonas;
	}
	public String[] getServicios() {
		return servicios;
	}
	public int [] getHabitacion() {
		
		return habitaciones;
	}
	public String getUsuario() {
		return usuario;
	}
	public double getSaldoFavor() {
		return saldoFavor;
	}
	public int getFactura() {
		return factura;
	}
	public boolean getPagoMinimo() {
		return pagoMinimo; 
	}
	public String getFechaDeReserva() {
		return fechaDeReserva;
	}
	public String[] getCalificacion() {
		return calificacion;
	}
	public boolean isCheckOut() {
		return checkOut;
	}
	public boolean isCheckIn() {
		return checkIn;
	}
	
}
