package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Usuario;

public interface UsuarioDAO {
	void create(Usuario usuario);

	void update(Usuario usuario);

	Usuario find(int usuario) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Usuario find(String usuario) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void remove(int id_usuario);

	Set<Usuario> findAll();
}
