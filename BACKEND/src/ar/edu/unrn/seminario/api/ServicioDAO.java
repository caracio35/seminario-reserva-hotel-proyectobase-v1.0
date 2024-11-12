package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Servicio;

public interface ServicioDAO {

	void create(Servicio servicio) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void update(Servicio servicio) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Servicio find(String nombre) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void remove(int id) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Set<Servicio> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

}
