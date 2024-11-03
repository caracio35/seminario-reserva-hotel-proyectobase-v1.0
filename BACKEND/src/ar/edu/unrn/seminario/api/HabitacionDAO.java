package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Habitacion;

public interface HabitacionDAO {
	void create(Habitacion habitacion) throws ConexionFallidaExeption;

	void update(Habitacion habitacion) throws ConexionFallidaExeption;

	Habitacion find(int numHabitaciones) throws ConexionFallidaExeption;

	void remove(int numHabitaciones) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Set<Habitacion> findAll() throws ConexionFallidaExeption;
}
