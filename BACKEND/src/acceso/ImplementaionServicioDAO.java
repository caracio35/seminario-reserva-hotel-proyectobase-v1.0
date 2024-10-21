package acceso;

import java.util.Set;

import ar.edu.unrn.seminario.api.ServicioDAO;
import ar.edu.unrn.seminario.modelo.Servicio;

public class ImplementaionServicioDAO implements ServicioDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";

	@Override
	public void create(Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public Servicio find(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String nombre) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Servicio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
