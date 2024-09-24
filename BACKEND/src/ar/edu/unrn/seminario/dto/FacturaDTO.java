package ar.edu.unrn.seminario.dto;
import java.time.LocalDate;

public class FacturaDTO{
	private ReservaDTO reserva ;
	private LocalDate fecha ; 
	private Integer codigo ; 
	private double monto;
	private String decripcion ; 
	
	public FacturaDTO(ReservaDTO reserva , LocalDate fecha , Integer codigo , double monto , String decripcion) {
		this.reserva = reserva ; 
		this.fecha = fecha ; 
		this.codigo = codigo ;
		this.monto = monto ; 
		this.decripcion = decripcion ; 
	}
	void setReserva(ReservaDTO reserva) {
		this.reserva = reserva ;
	}
	void setFecha(LocalDate fecha) {
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
	ReservaDTO getReserva() {
		return reserva ; 
	}
	LocalDate getFecha() {
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
