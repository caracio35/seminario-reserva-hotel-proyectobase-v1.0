package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;

public interface CalificacionDAO {
	void create(Calificacion calificacion, int idReserva) throws ConexionFallidaExeption;

	void update(Calificacion calificacion);

	Calificacion find(int id_calificacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void remove(int id_calificacion);

	Set<Calificacion> findAll();
}
