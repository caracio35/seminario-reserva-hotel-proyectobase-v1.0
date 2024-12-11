package acceso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.RolDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.Rol;

public class ImplementacionRolDAO implements RolDAO{

	private final static String buscarRol = "SELECT id_rol FROM rol WHERE id_rol = ?";
	
	
	@Override
	public void create(Rol rol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Rol rol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Rol> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol find(int id_rol) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConexion = null;
		PreparedStatement pStatementConsultaRol = null;
		ResultSet resultSet = null;
		Rol rolObtenido = null;

		miConexion = Coneccion.conectar();
		
		try {
			pStatementConsultaRol = (PreparedStatement) miConexion.prepareStatement(buscarRol);
			pStatementConsultaRol.setInt(1, id_rol);
			resultSet = pStatementConsultaRol.executeQuery();
			if (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				rolObtenido = new Rol(id_rol,nombre); 
			}
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		}
		return rolObtenido;
		
		
	}

}
