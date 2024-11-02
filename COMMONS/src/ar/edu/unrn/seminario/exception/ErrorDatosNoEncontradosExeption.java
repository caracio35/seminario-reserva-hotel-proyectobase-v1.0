package ar.edu.unrn.seminario.exception;

import java.sql.SQLException;

public class ErrorDatosNoEncontradosExeption extends SQLException {
	public ErrorDatosNoEncontradosExeption(String mensaje) {
		super(mensaje);
	}
	public ErrorDatosNoEncontradosExeption() {
		super("Datos no encontrados en la base de datos");
	}
}
