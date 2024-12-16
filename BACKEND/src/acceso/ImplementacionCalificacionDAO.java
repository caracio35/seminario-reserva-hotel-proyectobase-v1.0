package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CalificacionDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;

@SuppressWarnings("unused")
public class ImplementacionCalificacionDAO implements CalificacionDAO {
	private final static String nuevoCalificacion = "INSERT INTO calificacion (reserva_id, puntaje, descripcion) VALUES (?, ?, ?)";
	private final static String buscarCalificacion = "SELECT puntaje , descripcion FROM Calificacion WHERE = reserva_id ?";

	@Override

	public void create(Calificacion calificacion) throws ConexionFallidaExeption {
		Connection miConexion = null;
		PreparedStatement pStamentConsutaCrearCalificacion = null;
		miConexion = Coneccion.conectar();
		try {

			pStamentConsutaCrearCalificacion = (PreparedStatement) miConexion.prepareStatement(nuevoCalificacion);
			pStamentConsutaCrearCalificacion.setInt(1, calificacion.getIdReservaFK());
			pStamentConsutaCrearCalificacion.setInt(2, calificacion.getValor());
			pStamentConsutaCrearCalificacion.setString(3, calificacion.getComentario());
			pStamentConsutaCrearCalificacion.executeUpdate();

		} catch (SQLException e) {
			try {
				if (miConexion != null) {
					miConexion.rollback();

				}
			} catch (SQLException ex) {
				throw new ConexionFallidaExeption("Ocurrio un problema con la conexion");
			}
		} finally {
			try {
				if (pStamentConsutaCrearCalificacion != null)
					pStamentConsutaCrearCalificacion.close();
				if (miConexion != null)
					Coneccion.disconnect();
			} catch (SQLException e) {
				throw new ConexionFallidaExeption("Error al cerrar los recursos");
			}
		}
	}

	@Override
	public void update(Calificacion calificacion) {

	}

	@Override
	public Calificacion find(int idReserva) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConexion = null;
		PreparedStatement pStatementConsultaBuscarCalificacion = null;
		ResultSet resultSet = null;
		Calificacion calificacionObtenida = null;

		miConexion = Coneccion.conectar();
		try {

			pStatementConsultaBuscarCalificacion = (PreparedStatement) miConexion.prepareStatement(buscarCalificacion);
			pStatementConsultaBuscarCalificacion.setInt(1, idReserva);

			resultSet = pStatementConsultaBuscarCalificacion.executeQuery();

			if (resultSet.next()) {
				int valor = resultSet.getInt("valor");
				String comentario = resultSet.getString("comentario");
				calificacionObtenida = new Calificacion(valor, comentario, idReserva);
			}
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (pStatementConsultaBuscarCalificacion != null)
					pStatementConsultaBuscarCalificacion.close();
				if (miConexion != null)
					Coneccion.disconnect();
			} catch (SQLException e) {
				throw new ConexionFallidaExeption("Error al cerrar los recursos");
			}
		}
		return calificacionObtenida;
	}

	@Override
	public void remove(int id_calificacion) {

	}

	@Override
	public Set<Calificacion> findAll() {

		return null;
	}

}
