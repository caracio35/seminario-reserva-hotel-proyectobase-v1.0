package ar.edu.unrn.seminario.exception;

import java.sql.SQLException;

public class ConnecionFallidaExeption extends SQLException {
	public ConnecionFallidaExeption(String mensaje) {
		super(mensaje);
	}
	public ConnecionFallidaExeption() {
		super("Coneccion fallida con la base de datos");
}
}
