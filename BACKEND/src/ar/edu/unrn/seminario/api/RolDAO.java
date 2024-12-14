package ar.edu.unrn.seminario.api;

import java.sql.SQLException;
import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Rol;

public interface RolDAO {
	
	void create(Rol rol);
	
	void update(Rol rol);
	
	void remove(String nombre);
	
	Set<Rol> findAll();
	
	Rol find(int id_rol) throws ErrorConsultaExeption, ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;
}
