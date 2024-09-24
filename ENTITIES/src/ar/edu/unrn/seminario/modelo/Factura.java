package ar.edu.unrn.seminario.modelo;

public class Factura {
	private Reserva reserva;
	private int fecha;
	private Integer codigo;
	private int monto;
	private String descripcion;

	public Factura(Reserva reserva, int fecha, Integer codigo, int monto, String descripcion) {
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

	int getFecha() {
		return fecha;
	}

	void setFecha(int fecha) {
		this.fecha = fecha;
	}

	Integer getCodigo() {
		return codigo;
	}

	void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	int getMonto() {
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

}
