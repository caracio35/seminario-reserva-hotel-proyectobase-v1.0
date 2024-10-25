package ar.edu.unrn.seminario.exception;

public class PrecioCeroExeption extends Exception {
	public PrecioCeroExeption(String mensaje) {
		super(mensaje);
	}

	public PrecioCeroExeption() {
		super("el precio no puede ser cero o null");
	}
}
