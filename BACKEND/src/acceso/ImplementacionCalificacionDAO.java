package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CalificacionDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;

public class ImplementacionCalificacionDAO implements CalificacionDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevoCalificacion = "INSERT INTO calificacion (reserva_id, puntaje, descripcion) VALUES (?, ?, ?)";
	private final static String buscarCalificacion = "SELECT puntaje , descripcion FROM Calificacion WHERE = reserva_id ?";
	@Override
	public void create(Calificacion calificacion, int idReserva) {
		Connection miConexion = null;
		PreparedStatement pStamentConsutaCrearCalificacion = null;

		try {
			miConexion = conectar();
			miConexion.setAutoCommit(false);
			pStamentConsutaCrearCalificacion = (PreparedStatement) miConexion.prepareStatement(nuevoCalificacion);
			pStamentConsutaCrearCalificacion.setInt(1, idReserva);
			pStamentConsutaCrearCalificacion.setInt(2, calificacion.getValor());
			pStamentConsutaCrearCalificacion.setString(3, calificacion.getComentario());
			pStamentConsutaCrearCalificacion.executeUpdate();
			miConexion.commit();
			
		} catch (SQLException e) {
			try {
				if (miConexion != null) {
					miConexion.rollback();
					
				}
			} catch (SQLException ex) {
				
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pStamentConsutaCrearCalificacion != null)
					pStamentConsutaCrearCalificacion.close();
				if (miConexion != null)
					miConexion.close();
			} catch (SQLException e) {
				
			}
		}
	}

	@Override
	public void update(Calificacion calificacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public Calificacion find(int idReserva) {
	    Connection miConexion = null;
	    PreparedStatement pStatementConsultaBuscarCalificacion = null;
	    ResultSet resultSet = null;
	    Calificacion calificacionObtenida = null;

	    try {
	        miConexion = conectar();
	        
	        pStatementConsultaBuscarCalificacion = (PreparedStatement) miConexion.prepareStatement(buscarCalificacion);
	        pStatementConsultaBuscarCalificacion.setInt(1, idReserva);
	        
	        resultSet = pStatementConsultaBuscarCalificacion.executeQuery();
	        
	        if (resultSet.next()) {
	            int valor = resultSet.getInt("valor");
	            String comentario = resultSet.getString("comentario");
	            calificacionObtenida = new Calificacion(valor, comentario, idReserva);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (pStatementConsultaBuscarCalificacion != null) pStatementConsultaBuscarCalificacion.close();
	            if (miConexion != null) miConexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return calificacionObtenida;
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
