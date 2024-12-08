package acceso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.UsuarioDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

@SuppressWarnings("unused")
public class ImplementacionUsuarioDAO implements UsuarioDAO {

	private final static String buscarUsuarioPorNombre = "SELECT * FROM Usuarios WHERE usuario = ?";;
	private static final String buscarUsuarioPorId = "SELECT nombre, apellido, email, usuario, contrasena, telefono, dni FROM Usuarios WHERE id = ?";
	private static final String buscarUsuarioPorNombreConContrasenia = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasena = ?";

	@Override
	public void create(Usuario usuario) {

	}

	@Override
	public void update(Usuario usuario) {

	}

	@Override
	public Usuario find(String usuario) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		String usu = usuario;
		Connection miConexion = null;
		PreparedStatement pStamentConsultaUsuario = null;
		ResultSet rs = null;
		miConexion = Coneccion.conectar();
		try {
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
				int rol = rs.getInt("rol");
				int dni = rs.getInt("dni");
				Rol rol1 = obtenerRolPorId(rol, miConexion);
				Usuario usu2 = new Usuario(usuario1, contrasenia, nombres, apellido, email, dni, telefono, rol1);
				return usu2;
			}
			pStamentConsultaUsuario.execute();
			pStamentConsultaUsuario.close();
		} catch (SQLException e) {

			throw new ErrorDatosNoEncontradosExeption("Usuario no encontrado");

		} finally {
			if (miConexion != null) {
				try {
					miConexion.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption("Error al cerrar los recursos");
				}
			}

		}
		return null;
	}

	public static Usuario authenticate(String usuario, String contrasenia)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConexion = null;
		java.sql.PreparedStatement pStamentConsultaUsuario = null;
		ResultSet rs = null;
		miConexion = Coneccion.conectar();

		try {
			String query = buscarUsuarioPorNombreConContrasenia;
			pStamentConsultaUsuario = miConexion.prepareStatement(query);
			pStamentConsultaUsuario.setString(1, usuario);
			pStamentConsultaUsuario.setString(2, contrasenia);
			rs = pStamentConsultaUsuario.executeQuery();

			if (rs.next()) {
				String nombres = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String usuario1 = rs.getString("usuario");
				String telefono = rs.getString("telefono");
				int rolId = rs.getInt("rol");
				System.err.println("rolId: " + rolId); //ES NECESARIO IMPRIMIR EL ROL?
				int dni = rs.getInt("dni");

				Rol rol = obtenerRolPorId(rolId, miConexion);
				Usuario usu2 = new Usuario(usuario1, contrasenia, nombres, apellido, email, dni, telefono, rol);
				return usu2;
			}
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption("Usuario o contraseña incorrectos");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption("Error al cerrar el ResultSet");
				}
			}
			if (pStamentConsultaUsuario != null) {
				try {
					pStamentConsultaUsuario.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption("Error al cerrar los recursos de declaración");
				}
			}
			if (miConexion != null) {
				try {
					miConexion.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption("Error al cerrar la conexión");
				}
			}
		}
		return null;
	}

	private static Rol obtenerRolPorId(int rolId, Connection miConexion) throws SQLException {
		String query = "SELECT nombre, activo FROM rol WHERE id_rol = ?";
		try (PreparedStatement pStmt = (PreparedStatement) miConexion.prepareStatement(query)) {
			pStmt.setInt(1, rolId);
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("nombre");
					boolean activo = rs.getBoolean("activo");
					Rol rol = new Rol(rolId, nombre);

					rol.setActivo(activo);
					return rol;
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
	public Usuario find(int idUsuario) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConexion = null;
		PreparedStatement pStamentConsultaUsuario = null;
		ResultSet rs = null;
		miConexion = Coneccion.conectar();
		try {
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
				int rol = rs.getInt("rol");
				int dni = rs.getInt("dni");
				Rol rol1 = obtenerRolPorId(rol, miConexion);
				return new Usuario(usuario1, contrasenia, nombres, apellido, email, dni, telefono, rol1);
			}
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption("Usuario no encontrado");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pStamentConsultaUsuario != null)
					pStamentConsultaUsuario.close();
				if (miConexion != null)
					miConexion.close();
			} catch (SQLException e) {
				throw new ConexionFallidaExeption("Error al cerrar los recursos");
			}
		}
		return null;
	}

}
