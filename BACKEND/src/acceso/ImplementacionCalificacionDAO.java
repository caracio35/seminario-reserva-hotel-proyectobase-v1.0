package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CalificacionDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;

public class ImplementacionCalificacionDAO implements CalificacionDAO{
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevoCalificacion  = "INSERT INTO calificacion (reserva_id, puntaje, descripcion) VALUES (?, ?, ?)";
	@Override
	public void create(Calificacion calificacion, int idRecerva) {
		Connection miConexion = null;
	    PreparedStatement pStamentConsutaCrearCalificacion = null;
	    
	    try {
		miConexion = conectar();
        miConexion.setAutoCommit(false);
        pStamentConsutaCrearCalificacion = (PreparedStatement) miConexion.prepareStatement(nuevoCalificacion);
        pStamentConsutaCrearCalificacion.setInt(1, idRecerva);
        pStamentConsutaCrearCalificacion.setInt(2, calificacion.getValor());
        pStamentConsutaCrearCalificacion.setString(3, calificacion.getComentario());
        pStamentConsutaCrearCalificacion.executeUpdate();
        miConexion.commit();
        System.out.println("SE CREO LA CALIFICACION");
	    }catch (SQLException e) {
	        try {
	            if (miConexion != null) {
	                miConexion.rollback(); 
	                System.out.println("hizo rolsasfd");
	            }
	        } catch (SQLException ex) {
	            System.out.println("Error al hacer rollback");
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pStamentConsutaCrearCalificacion != null) pStamentConsutaCrearCalificacion.close();
	            if (miConexion != null) miConexion.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión");
	        }
	    }
	}

	@Override
	public void update(Calificacion calificacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calificacion find(int id_calificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id_calificacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Calificacion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	private Connection conectar() throws ConexionFallidaExeption {
		Connection miConexion = null;
		try {
			miConexion = DriverManager.getConnection(conexion, usuario, clave);
			return miConexion;
		} catch (Exception e) {
			throw new ConexionFallidaExeption("no se conecto");

		}
	}
}
