package acceso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.RolDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.Rol;

public class ImplementacionRolDAO implements RolDAO{

	private final static String buscarRol = "SELECT nombre, activo FROM rol WHERE id_rol = ?";
	
	
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
	public Rol find(int idRol) throws ErrorConsultaExeption, ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConexion = null;

		miConexion = Coneccion.conectar();
	
		try (PreparedStatement pStmt = (PreparedStatement) miConexion.prepareStatement(buscarRol)) {
			pStmt.setInt(1, idRol);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("nombre");
					boolean activo = rs.getBoolean("activo");
					Rol rol = new Rol(idRol, nombre);

					rol.setActivo(activo);
					return rol;
				}
			}catch(SQLException e) {
				throw new ErrorConsultaExeption();
			}finally {
				if (pStmt != null)
					pStmt.close();}
	
		return null;
	} catch (SQLException e1) {
		throw new ErrorDatosNoEncontradosExeption();
	} finally {
		if (miConexion != null)
			Coneccion.disconnect();
	}
}
}
		
	


