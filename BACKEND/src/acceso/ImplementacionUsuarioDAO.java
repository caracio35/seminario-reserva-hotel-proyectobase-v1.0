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

public class ImplementacionUsuarioDAO implements UsuarioDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String buscarUsuarioPorNombre = "SELECT * FROM usuarios WHERE usuario = ?";;
	@Override
	public void create(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario find(String usuario) {
		String usu = usuario;
		Connection miConexion = null;
	    PreparedStatement pStamentConsultaUsuario = null;
	    ResultSet rs = null;
		try {
			miConexion = conectar();
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
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	private Connection conectar() throws ConexionFallidaExeption {
		Connection miConnecion = null;
		try {
			miConnecion = DriverManager.getConnection(conexion, usuario, clave);
			return miConnecion;
		} catch (Exception e) {
			throw new ConexionFallidaExeption("no se conecto");

		}
	}

}
