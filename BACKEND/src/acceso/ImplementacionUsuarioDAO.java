package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.UsuarioDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

@SuppressWarnings("unused")
public class ImplementacionUsuarioDAO implements UsuarioDAO {

	private final static String buscarUsuarioPorNombre = "SELECT * FROM usuarios WHERE usuario = ?";;
	private static final String buscarUsuarioPorId = "SELECT nombre, apellido, email, usuario, contrasena, telefono, dni FROM usuarios WHERE id = ?";

	@Override
	public void create(Usuario usuario) {

	}

	@Override
	public void update(Usuario usuario) {

	}

	@Override
	public Usuario find(String usuario) {
		String usu = usuario;
		Connection miConexion = null;
		PreparedStatement pStamentConsultaUsuario = null;
		ResultSet rs = null;
		try {
			miConexion = Coneccion.conectar();
			pStamentConsultaUsuario = (PreparedStatement) miConexion.prepareStatement(buscarUsuarioPorNombre);
			pStamentConsultaUsuario.setString(1, usu);
			rs = pStamentConsultaUsuario.executeQuery();

			if (rs.next()) {
				String nombres = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String usuario1 = rs.getString("usuario");
				String contrasenia = rs.getString("contrasena");
				String telefono = rs.getString("telefono");
				int dni = rs.getInt("dni");

				Usuario usu2 = new Usuario(usuario1, contrasenia, nombres, apellido, email, dni, telefono);
				return usu2;
			}
			pStamentConsultaUsuario.execute();
			pStamentConsultaUsuario.close();
		} catch (SQLException e) {
			System.out.println("faloooooooosda");
		} finally {
			if (miConexion != null) {
				try {
					miConexion.close();
				} catch (SQLException e) {
					System.out.println("error de conexion");
				}
			}

		}
		return null;
	}

	@Override
	public void remove(int id_usuario) {

	}

	@Override
	public Set<Usuario> findAll() {

		return null;
	}

	@Override
	public Usuario find(int idUsuario) {
		Connection miConexion = null;
		PreparedStatement pStamentConsultaUsuario = null;
		ResultSet rs = null;
		try {
			miConexion = Coneccion.conectar();
			pStamentConsultaUsuario = (PreparedStatement) miConexion.prepareStatement(buscarUsuarioPorId);
			pStamentConsultaUsuario.setInt(1, idUsuario);
			rs = pStamentConsultaUsuario.executeQuery();

			if (rs.next()) {
				String nombres = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String usuario1 = rs.getString("usuario");
				String contrasenia = rs.getString("contrasena");
				String telefono = rs.getString("telefono");
				int dni = rs.getInt("dni");

				return new Usuario(usuario1, contrasenia, nombres, apellido, email, dni, telefono);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar usuario por ID");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pStamentConsultaUsuario != null)
					pStamentConsultaUsuario.close();
				if (miConexion != null)
					miConexion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión");
			}
		}
		return null;
	}

}
