package ar.edu.unrn.seminario.api;

import java.util.Set;

import ar.edu.unrn.seminario.modelo.Factura;

public interface FacturaDAO {
	
	void create (Factura factura);
	void update(Factura factura);
	Factura find(String nombre);
	void remove(String nombre);
	Set<Factura>findAll();
}
