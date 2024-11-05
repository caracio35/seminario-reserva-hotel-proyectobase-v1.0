package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.Habitacion;

public interface HabitacionDAO {
	void create(Habitacion habitacion)
			throws ConexionFallidaExeption, ErrorConsultaExeption, NumeroHabitacionExistenteException;

	void update(Habitacion habitacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Habitacion find(int numHabitaciones) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption,
			CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;

	void remove(int numHabitaciones) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Set<Habitacion> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;
}
