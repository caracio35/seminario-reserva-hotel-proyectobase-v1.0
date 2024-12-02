package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.DuplicadaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

public interface CaracteristicaEspecialDAO {
	void create(CaracteristicaEspecial caracteristicas) throws ConexionFallidaExeption, DuplicadaExeption;

	void update(CaracteristicaEspecial caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	CaracteristicaEspecial find(String caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void remove(String id_caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	Set<CaracteristicaEspecial> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

}
