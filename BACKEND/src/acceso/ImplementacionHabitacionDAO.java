package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.HabitacionDAO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;

public class ImplementacionHabitacionDAO implements HabitacionDAO {

	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaHabitacion = "INSERT INTO Habitacion (cantidadDeCamas, descripcion, precio, "
			+ "habilitado, fechaHastaCuandoEstaDesactivado, numHabitaciones) VALUES (?,?,?,?,?,?)";
	private final static String buscarHabitacion = "SELECT * FROM Habitacion WHERE numHabitaciones = ?";

	private final static String modificarHabitacion = "UPDATE Habitacion SET cantidadDeCamas = ?, descripcion = ?, precio = ?, habilitado = ? WHERE numHabitaciones = ?";
	private final static String eliminarHabitacion = "DELETE FROM Habitacion WHERE numHabitaciones = ?";
	private final static String buscarTodaLasHabitaciones = "SELECT * FROM Habitacion";
	private final static String modificarFechaDeHabitacion = "UPDATE Habitacion SET habilitado = ?, fechaHastaCuandoEstaDesactivado = ? WHERE numHabitaciones = ?";
	private final static String eliminarCaracteristicasSQL = "DELETE FROM Habitacion_CaracteristicaEspecial WHERE numHabitacion = ?";
	private final static String insertarCaracteristicaSQL = "INSERT INTO Habitacion_CaracteristicaEspecial (numHabitacion, nombreCaracteristicaEspecial) VALUES (?, ?)";

	public ImplementacionHabitacionDAO() {

	}

	@Override

	public void create(Habitacion habitacion)
			throws ConexionFallidaExeption, ErrorConsultaExeption, NumeroHabitacionExistenteException {
		Connection miConeccion = null;
		PreparedStatement pStamentConsutaCreaHabitacion = null;
		miConeccion = conectar();
		try {

			miConeccion.setAutoCommit(false);

			pStamentConsutaCreaHabitacion = (PreparedStatement) miConeccion.prepareStatement(nuevaHabitacion);
			pStamentConsutaCreaHabitacion.setInt(1, habitacion.getCantidadDeCamas());
			pStamentConsutaCreaHabitacion.setString(2, habitacion.getDescripcion());
			pStamentConsutaCreaHabitacion.setDouble(3, habitacion.getPrecio());
			pStamentConsutaCreaHabitacion.setBoolean(4, habitacion.isHabilitado());
			pStamentConsutaCreaHabitacion.setNull(5, java.sql.Types.DATE);
			pStamentConsutaCreaHabitacion.setInt(6, habitacion.getNumHabitaciones());
			pStamentConsutaCreaHabitacion.executeUpdate();

			insertarCaracteristica(habitacion, miConeccion);

			miConeccion.commit();

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			throw new NumeroHabitacionExistenteException();
		} catch (SQLException e) {
			try {
				if (miConeccion != null) {
					miConeccion.rollback();
				}
			} catch (SQLException ex) {
				System.out.println("Error al hacer rollback");
				throw new ConexionFallidaExeption();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pStamentConsutaCreaHabitacion != null)
					pStamentConsutaCreaHabitacion.close();
				if (miConeccion != null)
					miConeccion.close();
			} catch (SQLException e) {
				throw new ConexionFallidaExeption();
			}
		}
	}

	private void insertarCaracteristica(Habitacion habitacion, Connection miConeccion) throws ErrorConsultaExeption {
		String insertarHabitacionCaracteristica = "INSERT INTO Habitacion_CaracteristicaEspecial (numHabitacion , nombreCaracteristicaEspecial) VALUES (?, ?)";

		PreparedStatement pStamentCaracteristica;
		try {
			pStamentCaracteristica = (PreparedStatement) miConeccion.prepareStatement(insertarHabitacionCaracteristica);
			if (habitacion.getCaracteristicasEspeciale() != null) {
				for (CaracteristicaEspecial car : habitacion.getCaracteristicasEspeciale()) {
					if (car != null) {
						pStamentCaracteristica.setInt(1, habitacion.getNumHabitaciones());
						pStamentCaracteristica.setString(2, car.getNombre());
						pStamentCaracteristica.addBatch();
					}
				}
				pStamentCaracteristica.executeBatch();
			} else {
				System.out.println("No hay características especiales para insertar.");
			}
		} catch (SQLException e) {
			throw new ErrorConsultaExeption("los datos habitacion y caracteristicas no estan bien definidos ");
		}

	}

	@Override
	public void update(Habitacion habitacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConeccion = conectar();
		try (PreparedStatement pStament = (PreparedStatement) miConeccion.prepareStatement(modificarHabitacion);
				PreparedStatement eliminarStmt = (PreparedStatement) miConeccion
						.prepareStatement(eliminarCaracteristicasSQL);
				PreparedStatement insertarStmt = (PreparedStatement) miConeccion
						.prepareStatement(insertarCaracteristicaSQL)) {

			// Actualizar los datos de la habitación en la tabla Habitacion
			pStament.setInt(1, habitacion.getCantidadDeCamas());
			pStament.setString(2, habitacion.getDescripcion());
			pStament.setDouble(3, habitacion.getPrecio());
			pStament.setBoolean(4, habitacion.isHabilitado());
			pStament.setInt(5, habitacion.getNumHabitaciones()); // Para el WHERE
			pStament.executeUpdate();

			// Eliminar las relaciones de características especiales existentes
			eliminarStmt.setInt(1, habitacion.getNumHabitaciones());
			eliminarStmt.executeUpdate();

			// Insertar las nuevas relaciones de características especiales
			for (CaracteristicaEspecial caracteristica : habitacion.getCaracteristicasEspeciale()) {
				insertarStmt.setInt(1, habitacion.getNumHabitaciones());
				insertarStmt.setString(2, caracteristica.getNombre());
				insertarStmt.executeUpdate();
			}

			System.out.println("Habitación y características especiales actualizadas con éxito.");

		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				try {
					miConeccion.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption();
				}
			}
		}
	}

	@Override
	public Habitacion find(int numHabitaciones) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption,
			CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		int numeroHabitacionBuscada = numHabitaciones;
		Connection miConeccion = null;
		PreparedStatement pStamentConsutataBuscarHabitacion = null;
		miConeccion = conectar();
		try {
			Set<CaracteristicaEspecial> caracteristicas = new HashSet<>();
			ImplementacionCaracteristicasEspecialDAO carEspDAO = new ImplementacionCaracteristicasEspecialDAO();
			caracteristicas = carEspDAO.obtenerCaracteristicasPorHabitacion(numeroHabitacionBuscada);
			// pasar de set a lista
			ArrayList<CaracteristicaEspecial> caracteristicasList = new ArrayList<>(caracteristicas);
			pStamentConsutataBuscarHabitacion = (PreparedStatement) miConeccion.prepareStatement(buscarHabitacion);
			pStamentConsutataBuscarHabitacion.setInt(1, numeroHabitacionBuscada);
			ResultSet habitacionObtenida = pStamentConsutataBuscarHabitacion.executeQuery();
			if (habitacionObtenida.next()) {
				int cantidadCamas = habitacionObtenida.getInt("cantidadDeCamas");
				String descripcion = habitacionObtenida.getString("descripcion");
				double precio = habitacionObtenida.getDouble("precio");
				boolean habilitado = (habitacionObtenida.getInt("Habilitado") == 1);
				int numeroHabitacion = habitacionObtenida.getInt("numHabitaciones");
				Habitacion habitacion = new Habitacion(cantidadCamas, descripcion, precio, habilitado, numeroHabitacion,
						caracteristicasList);
				return habitacion;
			}
			pStamentConsutataBuscarHabitacion.execute();
			pStamentConsutataBuscarHabitacion.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				try {
					miConeccion.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption();
				}
			}
		}
		return null;
	}

	@Override
	public void remove(int numHabitaciones) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = conectar();
		try {

			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarHabitacion);
			pStament.setInt(1, numHabitaciones);
			pStament.executeUpdate();
			pStament.close();
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				try {
					miConeccion.close();
				} catch (SQLException e) {
					throw new ConexionFallidaExeption();
				}
			}
		}
	}

	@Override
	public Set<Habitacion> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Set<Habitacion> listaHabitaciones = new HashSet<>();
		Set<CaracteristicaEspecial> caracteristicasLista = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStamentConsulta = null;
		ResultSet resultadoBusquedaHab = null;
		miConeccion = conectar();
		try {

			pStamentConsulta = (PreparedStatement) miConeccion.prepareStatement(buscarTodaLasHabitaciones);
			resultadoBusquedaHab = pStamentConsulta.executeQuery();

			while (resultadoBusquedaHab.next()) {
				Habitacion habitacion = new Habitacion();
				habitacion.setCantidadDeCamas(resultadoBusquedaHab.getInt("cantidadDeCamas"));
				habitacion.setDescripcion(resultadoBusquedaHab.getString("descripcion"));
				habitacion.setPrecio(resultadoBusquedaHab.getDouble("precio"));
				habitacion.setHabilitado(resultadoBusquedaHab.getBoolean("habilitado"));
				habitacion.setNumHabitaciones(resultadoBusquedaHab.getInt("numHabitaciones"));

				java.sql.Date fechaHastaCuandoEstaDesactivado = resultadoBusquedaHab
						.getDate("fechaHastaCuandoEstaDesactivado");
				if (fechaHastaCuandoEstaDesactivado != null) {
					LocalDate fechaLocalDate = fechaHastaCuandoEstaDesactivado.toLocalDate();
					habitacion.setFechaHastaCuandoEstaDesactivado(fechaLocalDate);
				} else {
					habitacion.setFechaHastaCuandoEstaDesactivado(null);
				}
				caracteristicasLista = obtenerCaracteristicas(habitacion.getNumHabitaciones(), miConeccion);
				ArrayList<CaracteristicaEspecial> arrayCar = new ArrayList<>(caracteristicasLista);
				habitacion.setCaracteristicasEspeciale(arrayCar);
				listaHabitaciones.add(habitacion);
			}
		} catch (SQLException e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			try {
				if (resultadoBusquedaHab != null)
					resultadoBusquedaHab.close();
				if (pStamentConsulta != null)
					pStamentConsulta.close();
				if (miConeccion != null)
					miConeccion.close();
			} catch (SQLException e) {
				throw new ConexionFallidaExeption("error al cerrar los recursos");
			}
		}
		return listaHabitaciones;
	}

	private Set<CaracteristicaEspecial> obtenerCaracteristicas(int numHabitacion, Connection miConeccion)
			throws SQLException {
		Set<CaracteristicaEspecial> caracteristicas = new HashSet<>();
		String consultaCaracteristicas = "SELECT ce.nombre, ce.descripcion, ce.precio "
				+ "FROM Habitacion_CaracteristicaEspecial hce "
				+ "JOIN CaracteristicaEspecial ce ON hce.nombreCaracteristicaEspecial = ce.nombre "
				+ "WHERE hce.numHabitacion = ?";

		PreparedStatement pStamentConsuta = (PreparedStatement) miConeccion.prepareStatement(consultaCaracteristicas);
		pStamentConsuta.setInt(1, numHabitacion);
		ResultSet resutadoBusquedaCar = pStamentConsuta.executeQuery();
		while (resutadoBusquedaCar.next()) {
			CaracteristicaEspecial caracteristica = new CaracteristicaEspecial();
			caracteristica.setnNombre(resutadoBusquedaCar.getString("nombre"));
			caracteristica.setDecripcion(resutadoBusquedaCar.getString("descripcion"));
			caracteristica.setPrecio(resutadoBusquedaCar.getDouble("precio"));
			caracteristicas.add(caracteristica);
		}

		return caracteristicas;
	}

	private Connection conectar() throws ConexionFallidaExeption {
		Connection miConnecion = null;
		try {
			miConnecion = DriverManager.getConnection(conexion, usuario, clave);
			return miConnecion;
		} catch (SQLException e) {
			throw new ConexionFallidaExeption();

		}
	}
}
