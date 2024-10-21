package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Servicio;



public interface ServicioDAO {
	
	void create (Servicio servicio);
	void update(Servicio servicio);
	Servicio find(String nombre);
	void remove(String nombre);
	Set<Servicio>findAll();
}
