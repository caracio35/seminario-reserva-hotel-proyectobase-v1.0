package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

public interface CaracteristicaEspecialDAO {
	void create(CaracteristicaEspecial caracteristicas);

	void update(CaracteristicaEspecial caracteristicas);

	CaracteristicaEspecial find(String caracteristicas);

	void remove(String id_caracteristicas);

	Set<CaracteristicaEspecial> findAll();

}
