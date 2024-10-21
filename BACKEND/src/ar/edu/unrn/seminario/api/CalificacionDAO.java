package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Calificacion;

public interface CalificacionDAO {
	void create(Calificacion calificacion);

	void update(Calificacion calificacion);

	Calificacion find(int id_calificacion);

	void remove(int id_calificacion);

	Set<Calificacion> findAll();
}
