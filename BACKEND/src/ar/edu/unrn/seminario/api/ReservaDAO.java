package ar.edu.unrn.seminario.api;

import java.util.Optional;
import java.util.Set;

import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.Reserva;

public interface ReservaDAO {

	void create(Reserva reserva);

	void update(Reserva reserva);

	Optional<Reserva> find(int idReserva);

	void remove(String nombre);

	Set<Reserva> findAll() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;
}
