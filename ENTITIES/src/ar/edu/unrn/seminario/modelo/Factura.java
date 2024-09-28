package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Factura {
	private Reserva reserva;
	private LocalDate fecha;
	private Integer codigo;
	private double monto;
	private String descripcion;

	public Factura(Reserva reserva,  LocalDate fecha, Integer codigo, double monto, String descripcion) {
		this.reserva = reserva;
		this.fecha = fecha;
		this.codigo = codigo;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	Reserva getReserva() {
		return reserva;
	}

	void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	LocalDate getFecha() {
		return fecha;
	}

	void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	Integer getCodigo() {
		return codigo;
	}

	void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	double getMonto() {
		return monto;
	}

	void setMonto(int monto) {
		this.monto = monto;
	}

	String getDescripcion() {
		return descripcion;
	}

	void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion, fecha, monto, reserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(fecha, other.fecha)
				&& Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto)
				&& Objects.equals(reserva, other.reserva);
	}

	

}
