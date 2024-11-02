package ar.edu.unrn.seminario.exception;

import java.sql.SQLException;

public class ErrorConsultaExeption extends SQLException{
	public ErrorConsultaExeption(String mensaje) {
		super(mensaje);
	}
	public ErrorConsultaExeption() {
		super("Error en la consulta SQL");
	}
}
