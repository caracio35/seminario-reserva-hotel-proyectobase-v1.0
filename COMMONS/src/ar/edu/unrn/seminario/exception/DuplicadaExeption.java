package ar.edu.unrn.seminario.exception;

import java.sql.SQLException;

public class DuplicadaExeption extends SQLException{
	public DuplicadaExeption(String mensaje) {
		super(mensaje);
	}
	public DuplicadaExeption() {
		super("Primary Key duplicada");
	}
}
