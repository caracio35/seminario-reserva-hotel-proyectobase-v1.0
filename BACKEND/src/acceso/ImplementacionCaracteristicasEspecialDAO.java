package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CaracteristicaEspecialDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.DuplicadaExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

@SuppressWarnings("unused")
public class ImplementacionCaracteristicasEspecialDAO implements CaracteristicaEspecialDAO {
	private final static String nuevaCaracteristica = "INSERT INTO CaracteristicaEspecial (nombre,descripcion,precio) VALUES (?,?,?) ";
	private final static String eliminarCaracteristica = "DELETE FROM CaracteristicaEspecial WHERE nombre = ?";
	private final static String encontrarCaracteristica = "SELECT * FROM `CaracteristicaEspecial` WHERE nombre = ?";
	private final static String encontrarTodasLasCaracteristicas = "SELECT * FROM CaracteristicaEspecial";
	private final static String modificarCaracteristica = "UPDATE CaracteristicaEspecial SET descripcion = ?, precio = ? WHERE nombre = ?";
	private final static String buscarCaracteristicasPorHabitacion = "SELECT ce.nombre, ce.descripcion, ce.precio "
			+ "FROM CaracteristicaEspecial ce "
			+ "JOIN Habitacion_CaracteristicaEspecial hce ON ce.nombre = hce.nombreCaracteristicaEspecial "
			+ "WHERE hce.numHabitacion = ?;"; // Filtro por el número de habitación

	public ImplementacionCaracteristicasEspecialDAO() {

	}

	@Override
	public void create(CaracteristicaEspecial caracteristicas) throws ConexionFallidaExeption, DuplicadaExeption {
		CaracteristicaEspecial c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(nuevaCaracteristica);

			pStament.setString(1, c.getNombre());
			pStament.setString(2, c.getDescripcion());
			pStament.setDouble(3, c.getPrecio());
			pStament.execute();
			pStament.close();
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) { //VERIFICAR ESTOOOOO!!!!!!!
			throw new DuplicadaExeption("Ya existe esta caracteristica");
		} catch (SQLException e) {
			try {
				if (miConeccion != null) {
					miConeccion.rollback();

				}
			} catch (SQLException ex) {
				throw new ConexionFallidaExeption("Ocurrio un problema con la conexion");
			}
		}finally {
		
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}

	}

	@Override
	public void update(CaracteristicaEspecial caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		CaracteristicaEspecial c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(modificarCaracteristica);

			pStament.setString(1, c.getDescripcion());
			pStament.setDouble(2, c.getPrecio());
			pStament.setString(3, c.getNombre());
			pStament.execute();
			pStament.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}

	}

	@Override
	public CaracteristicaEspecial find(String caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		String c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarCaracteristica);
			pStament.setString(1, c);
			ResultSet rs = pStament.executeQuery();
			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				CaracteristicaEspecial a = new CaracteristicaEspecial(nombre, descripcion, precio);
				return a;
			}
			pStament.execute();
			pStament.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}
		return null;
	}

	@Override
	public void remove(String id_caracteristicas) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarCaracteristica);
			pStament.setString(1, id_caracteristicas);
			pStament.executeUpdate();
			pStament.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}

	}

	@Override
	public Set<CaracteristicaEspecial> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Set<CaracteristicaEspecial> caracteristicasList = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarTodasLasCaracteristicas);

			ResultSet rs = pStament.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");

				CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
				caracteristicasList.add(caracteristica);

			}
			pStament.execute();
			pStament.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}
		return caracteristicasList;
	}

	public Set<CaracteristicaEspecial> obtenerCaracteristicasPorHabitacion(int idNumeroHabitacion) throws ConexionFallidaExeption, ErrorConsultaExeption {
		Set<CaracteristicaEspecial> caracteristicaSet = new HashSet<>();
		Connection miConexion = null;
		PreparedStatement pStamentBuscarCar = null;
		miConexion = Coneccion.conectar();
		try {
			
			pStamentBuscarCar = (PreparedStatement) miConexion.prepareStatement(buscarCaracteristicasPorHabitacion);
			pStamentBuscarCar.setInt(1, idNumeroHabitacion);

			ResultSet rsCaracteristica = pStamentBuscarCar.executeQuery();

			while (rsCaracteristica.next()) {
				String nombre = rsCaracteristica.getString("nombre");
				String descripcion = rsCaracteristica.getString("descripcion");
				double precio = rsCaracteristica.getDouble("precio");

				CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
				caracteristicaSet.add(caracteristica);
			}
			pStamentBuscarCar.close();
		} catch (SQLException e) {
			throw new ErrorConsultaExeption();
		} finally {
			if (miConexion != null) {
				Coneccion.disconnect();
			}
		}

		return caracteristicaSet;
	}

}
