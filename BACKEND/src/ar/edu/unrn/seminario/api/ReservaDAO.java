package ar.edu.unrn.seminario.api;

import java.util.Optional;
import java.util.Set;

import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.Reserva;

public interface ReservaDAO {

	void create(Reserva reserva) throws ConexionFallidaExeption;

	void update(Reserva reserva) throws ConexionFallidaExeption, ErrorConsultaExeption;

	Optional<Reserva> find(int idReserva) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void remove(String nombre);

	Set<Reserva> findAll() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption, ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;
}
