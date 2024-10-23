package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Habitacion;

public interface HabitacionDAO {
	void create(Habitacion habitacion);

	void update(Habitacion habitacion);

	Habitacion find(int numHabitaciones);

	void remove(int numHabitaciones);

	Set<Habitacion> findAll();
}
