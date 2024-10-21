package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

public interface CaracteristicaEspecialDAO {
	void create(CaracteristicaEspecial caracteristicas);
	void update(CaracteristicaEspecial caracteristicas);
	Calificacion find (int id_caracteristicas);
	void remove(int id_caracteristicas);
	Set<CaracteristicaEspecial> findAll();
}
