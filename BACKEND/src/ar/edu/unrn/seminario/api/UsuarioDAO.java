package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.Usuario;

public interface UsuarioDAO {
	void create(Usuario usuario);

	void update(Usuario usuario);

	Calificacion find(int id_usuario);

	void remove(int id_usuario);

	Set<Usuario> findAll();
}
