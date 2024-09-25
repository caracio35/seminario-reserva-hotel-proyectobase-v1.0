package ar.edu.unrn.seminario.dto;

public class FacturaDTO{
	private int reserva ;
	private String fecha ; 
	private Integer codigo ; 
	private double monto;
	private String decripcion ; 
	
	public FacturaDTO(int reserva , String fecha , Integer codigo , double monto , String decripcion) {
		this.reserva = reserva ; 
		this.fecha = fecha ; 
		this.codigo = codigo ;
		this.monto = monto ; 
		this.decripcion = decripcion ; 
	}
	void setReserva(int reserva) {
		this.reserva = reserva ;
	}
	void setFecha(String fecha) {
		this.fecha = fecha ;
	}
	void setCodigo(Integer codigo) {
		this.codigo = codigo ; 
	}
	void setMonto(double monto) {
		this.monto = monto ;
	}
	void decripcion(String descripcion) {
		this.decripcion = descripcion ; 
	}
	int getReserva() {
		return reserva ; 
	}
	String getFecha() {
		return fecha ; 
	}
	Integer getCodigo() {
		return codigo ; 
	}
	double getMonto() {
		return monto ; 
	}
	String getDescripcion() {
		return decripcion ; 
	}
	
}
