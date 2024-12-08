package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Rol;

public interface RolDAO {
	
	void create(Rol rol);
	
	void update(Rol rol);
	
	void remove(String nombre);
	
	Set<Rol> findAll();
}
