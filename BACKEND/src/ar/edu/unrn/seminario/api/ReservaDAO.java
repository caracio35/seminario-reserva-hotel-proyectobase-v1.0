package ar.edu.unrn.seminario.api;

import java.util.Set;


import ar.edu.unrn.seminario.modelo.Reserva;

public interface ReservaDAO {
	
	void create (Reserva reserva);
	void update(Reserva reserva);
	Reserva find(int idReserva);
	void remove(String nombre);
	Set<Reserva>findAll();
}
