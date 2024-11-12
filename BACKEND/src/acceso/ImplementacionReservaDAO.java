package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import ar.edu.unrn.seminario.api.ReservaDAO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Reserva;
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

@SuppressWarnings("unused")
public class ImplementacionReservaDAO implements ReservaDAO {

	private final static String crearReserva = "INSERT INTO reserva (usuario_id, fechaDeInicio, fechaDeSalida, cantidadDePersonas,"
			+ "                      fechaDeReserva, saldoFavor, pagoMinimo) VALUES (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_ALL_RESERVAS = "SELECT * FROM Reserva";
	private static final String SELECT_HABITACIONES_BY_RESERVA = "SELECT h.numHabitaciones, h.cantidadDeCamas, h.descripcion, h.precio, h.habilitado "
			+
			"FROM Habitacion h " +
			"JOIN Reserva_Habitacion rh ON h.numHabitaciones = rh.numHabitacion " +
			"WHERE rh.reserva_id = ?";
	private static final String SELECT_SERVICIOS_BY_RESERVA = "SELECT s.id, s.nombre, s.precio, s.descripcion " +
			"FROM Servicio s " +
			"JOIN Reserva_Servicio rs ON s.id = rs.servicio_id " +
			"WHERE rs.reserva_id = ?";
	private static final String SELECT_CARACTERISTICAS_BY_HABITACION = "SELECT c.nombre, c.descripcion, c.precio " +
			"FROM CaracteristicaEspecial c " +
			"JOIN Habitacion_CaracteristicaEspecial hc ON c.nombre = hc.nombreCaracteristicaEspecial " +
			"WHERE hc.numHabitacion = ?";
	private static final String SELECT_CALIFICACION_BY_RESERVA = "SELECT id, reserva_id, puntaje, descripcion FROM Calificacion WHERE reserva_id = ?";

	@Override
	public void create(Reserva reserva) {
		Connection miConeccion = null;
		PreparedStatement pStamentConsutaCreaReserva = null;
		try {
			miConeccion = Coneccion.conectar();
			miConeccion.setAutoCommit(false);
			pStamentConsutaCreaReserva = (PreparedStatement) miConeccion.prepareStatement(crearReserva,
					Statement.RETURN_GENERATED_KEYS);

			Optional<Integer> obtenerlUserId = findUserId(reserva, miConeccion);
			int idUsuario = obtenerlUserId.get();

			pStamentConsutaCreaReserva.setInt(1, idUsuario);
			pStamentConsutaCreaReserva.setDate(2, Date.valueOf(reserva.getFechaDeInicio()));
			pStamentConsutaCreaReserva.setDate(3, Date.valueOf(reserva.getFechaDESalida()));
			pStamentConsutaCreaReserva.setInt(4, reserva.getCantidadDePersonas());
			pStamentConsutaCreaReserva.setDate(5, Date.valueOf(reserva.getFechaDeReserva()));
			pStamentConsutaCreaReserva.setDouble(6, reserva.getSaldofavor());
			pStamentConsutaCreaReserva.setBoolean(7, reserva.getPagoMinimo());

			pStamentConsutaCreaReserva.executeUpdate();

			ResultSet obtenerIdReserva = pStamentConsutaCreaReserva.getGeneratedKeys();
			int reservaId = 0;
			if (obtenerIdReserva.next()) {
				reservaId = obtenerIdReserva.getInt(1);
			}
			insertRooms(reservaId, reserva, miConeccion);
			insertServices(reservaId, reserva, miConeccion);
			miConeccion.commit();

		} catch (SQLException e) {
			try {
				if (miConeccion != null) {
					miConeccion.rollback();
					System.out.println("hizo rolsasfd");
				}
			} catch (SQLException ex) {
				System.out.println("Error al hacer rollback");
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (pStamentConsutaCreaReserva != null)
					pStamentConsutaCreaReserva.close();
				if (miConeccion != null)
					miConeccion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión");
			}
		}
	}

	@Override
	public void update(Reserva reserva) {
		Connection miConeccion = null;
		PreparedStatement pStamentUpdateReserva = null;
		try {
			miConeccion = Coneccion.conectar();
			String updateReservaSql = "UPDATE reserva SET fechaDeInicio = ?, fechaDeSalida = ?, " +
					"cantidadDePersonas = ?, pagoMinimo = ? WHERE id = ?";
			pStamentUpdateReserva = (PreparedStatement) miConeccion.prepareStatement(updateReservaSql);

			pStamentUpdateReserva.setDate(1, Date.valueOf(reserva.getFechaDeInicio()));
			pStamentUpdateReserva.setDate(2, Date.valueOf(reserva.getFechaDESalida()));
			pStamentUpdateReserva.setInt(3, reserva.getCantidadDePersonas());
			pStamentUpdateReserva.setBoolean(4, reserva.getPagoMinimo());
			pStamentUpdateReserva.setInt(5, reserva.getId());

			int rowsAffected = pStamentUpdateReserva.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Reserva actualizada exitosamente.");
			} else {
				System.out.println("No se pudo encontrar la reserva para actualizar.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar la reserva: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pStamentUpdateReserva != null)
					pStamentUpdateReserva.close();
				if (miConeccion != null)
					miConeccion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión después de la actualización.");
			}
		}
	}

	@Override
	public Optional<Reserva> find(int idReserva) {
		Connection conn = null;
		Reserva reserva = null;

		try {
			conn = Coneccion.conectar();
			String sqlReserva = "SELECT * FROM Reserva WHERE id = ?";
			try (PreparedStatement stmtReserva = (PreparedStatement) conn.prepareStatement(sqlReserva)) {
				stmtReserva.setInt(1, idReserva);
				ResultSet rsReserva = stmtReserva.executeQuery();

				if (rsReserva.next()) {
					LocalDate fechaDeInicio = rsReserva.getDate("fechaDeInicio").toLocalDate();
					LocalDate fechaDeSalida = rsReserva.getDate("fechaDeSalida").toLocalDate();
					LocalDate fechaDeReserva = rsReserva.getDate("fechaDeReserva").toLocalDate();
					int cantidadDePersonas = rsReserva.getInt("cantidadDePersonas");
					boolean pagoMinimo = rsReserva.getBoolean("pagoMinimo");

					ImplementacionUsuarioDAO usuarioDAO = new ImplementacionUsuarioDAO();
					Usuario usuario = usuarioDAO.find(rsReserva.getInt("usuario_id"));

					// Obtener habitaciones asociadas
					ArrayList<Habitacion> habitaciones = obtenerHabitacionesPorReserva(conn, idReserva);

					// Obtener servicios asociados
					ArrayList<Servicio> servicios = obtenerServiciosPorReserva(conn, idReserva);

					// Crear la instancia de Reserva utilizando los datos obtenidos
					reserva = new Reserva(idReserva, habitaciones, usuario, fechaDeInicio, fechaDeSalida,
							cantidadDePersonas, servicios, fechaDeReserva, pagoMinimo);

					// Opcional: Establecer fechas de check-in y check-out
					Optional<LocalDate> fechaCheckIn = Optional.ofNullable(rsReserva.getDate("checkIn"))
							.map(Date::toLocalDate);
					Optional<LocalDate> fechaCheckOut = Optional.ofNullable(rsReserva.getDate("checkOut"))
							.map(Date::toLocalDate);
					Optional<Calificacion> calificacion = findCalificacionByReservaId(idReserva, conn);
					calificacion.ifPresent(reserva::setCalificacion);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al encontrar la reserva con ID " + idReserva + ": " + e.getMessage());
			e.printStackTrace();
		} catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e) {
			System.out.println("Error de consistencia en los datos de la reserva: " + e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión después de buscar la reserva.");
			}
		}

		return Optional.ofNullable(reserva);
	}

	@Override
	public void remove(String nombre) {

	}

	@Override
	public Set<Reserva> findAll() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		Set<Reserva> reservas = new HashSet<>();

		try (Connection conn = Coneccion.conectar();
				java.sql.PreparedStatement stmtReservas = conn.prepareStatement(SELECT_ALL_RESERVAS);
				ResultSet rsReservas = stmtReservas.executeQuery()) {

			while (rsReservas.next()) {
				// Obtener datos básicos de la reserva
				int reservaId = rsReservas.getInt("id");
				LocalDate fechaDeInicio = pasarSqlDate(rsReservas.getDate("fechaDeInicio"));
				LocalDate fechaDeSalida = pasarSqlDate(rsReservas.getDate("fechaDeSalida"));
				LocalDate fechaDeReserva = pasarSqlDate(rsReservas.getDate("fechaDeReserva"));
				int cantidadDePersonas = rsReservas.getInt("cantidadDePersonas");
				boolean pagoMinimo = rsReservas.getBoolean("pagoMinimo");
				System.out.println(fechaDeReserva);
				ImplementacionUsuarioDAO usuarioDAO = new ImplementacionUsuarioDAO();
				Usuario usuario = usuarioDAO.find(rsReservas.getInt("usuario_id"));
				// Crear la lista de habitaciones asociadas a la reserva
				ArrayList<Habitacion> habitaciones = obtenerHabitacionesPorReserva(conn, reservaId);

				// Crear la lista de servicios asociados a la reserva
				ArrayList<Servicio> servicios = obtenerServiciosPorReserva(conn, reservaId);

				// Crear la reserva y agregarla al conjunto

				Reserva reserva = new Reserva(reservaId, habitaciones, usuario,
						fechaDeInicio, fechaDeSalida, cantidadDePersonas, servicios, fechaDeReserva, pagoMinimo);
				// Obtener fechas de check-in y check-out con opcioonal
				Optional<LocalDate> fechaCheckIn = Optional.ofNullable(rsReservas.getDate("checkIn"))
						.map(Date::toLocalDate);
				Optional<LocalDate> fechaCheckOut = Optional.ofNullable(rsReservas.getDate("checkOut"))
						.map(Date::toLocalDate);
				Optional<Calificacion> calificacion = findCalificacionByReservaId(reservaId, conn);
				calificacion.ifPresent(reserva::setCalificacion);

				// Establece true si el Optional tiene un valor (fecha no nula), false si está
				// vacío (fecha nula)
				reserva.setCheckIn(fechaCheckIn.isPresent());
				reserva.setCheckOut(fechaCheckOut.isPresent());
				reservas.add(reserva);
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener todas las reservas: " + e.getMessage());
			e.printStackTrace();
		}

		return reservas;
	}

	private LocalDate pasarSqlDate(java.sql.Date fecha) throws SQLException {
		LocalDate fechaLocalDate = fecha.toLocalDate();
		return fechaLocalDate;
	}

	private ArrayList<Habitacion> obtenerHabitacionesPorReserva(Connection conn, int reservaId)
			throws SQLException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		try (java.sql.PreparedStatement stmtHabitaciones = conn.prepareStatement(SELECT_HABITACIONES_BY_RESERVA)) {
			stmtHabitaciones.setInt(1, reservaId);
			ResultSet rsHabitaciones = stmtHabitaciones.executeQuery();

			while (rsHabitaciones.next()) {
				int numHabitaciones = rsHabitaciones.getInt("numHabitaciones");
				int cantidadDeCamas = rsHabitaciones.getInt("cantidadDeCamas");
				String descripcion = rsHabitaciones.getString("descripcion");
				double precio = rsHabitaciones.getDouble("precio");
				boolean habilitado = rsHabitaciones.getBoolean("habilitado");

				// Obtener características especiales de la habitación
				ArrayList<CaracteristicaEspecial> caracteristicas = obtenerCaracteristicasPorHabitacion(conn,
						numHabitaciones);

				Habitacion habitacion = new Habitacion(cantidadDeCamas, descripcion, precio, habilitado,
						numHabitaciones, caracteristicas);
				habitaciones.add(habitacion);
			}
		}
		return habitaciones;
	}

	private ArrayList<CaracteristicaEspecial> obtenerCaracteristicasPorHabitacion(Connection conn, int numHabitacion)
			throws SQLException {
		ArrayList<CaracteristicaEspecial> caracteristicas = new ArrayList<>();
		try (java.sql.PreparedStatement stmtCaracteristicas = conn
				.prepareStatement(SELECT_CARACTERISTICAS_BY_HABITACION)) {
			stmtCaracteristicas.setInt(1, numHabitacion);
			ResultSet rsCaracteristicas = stmtCaracteristicas.executeQuery();

			while (rsCaracteristicas.next()) {
				String nombre = rsCaracteristicas.getString("nombre");
				String descripcion = rsCaracteristicas.getString("descripcion");
				double precio = rsCaracteristicas.getDouble("precio");

				CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
				caracteristicas.add(caracteristica);
			}
		}
		return caracteristicas;
	}

	private ArrayList<Servicio> obtenerServiciosPorReserva(Connection conn, int reservaId) throws SQLException {
		ArrayList<Servicio> servicios = new ArrayList<>();
		try (java.sql.PreparedStatement stmtServicios = conn.prepareStatement(SELECT_SERVICIOS_BY_RESERVA)) {
			stmtServicios.setInt(1, reservaId);
			ResultSet rsServicios = stmtServicios.executeQuery();

			while (rsServicios.next()) {
				int servicioId = rsServicios.getInt("id");
				String nombre = rsServicios.getString("nombre");
				double precio = rsServicios.getDouble("precio");
				String descripcion = rsServicios.getString("descripcion");

				Servicio servicio = new Servicio(servicioId, nombre, precio, descripcion);
				servicios.add(servicio);
			}
		}
		return servicios;
	}

	private Optional<Integer> findUserId(Reserva r, Connection miConeccion) throws SQLException {
		String buscarIdUsuario = "SELECT id FROM usuarios WHERE nombre = ? AND email = ?";
		PreparedStatement pStamentUsuario = (PreparedStatement) miConeccion.prepareStatement(buscarIdUsuario);
		pStamentUsuario.setString(1, r.getUsuario().getNombre());
		pStamentUsuario.setString(2, r.getUsuario().getEmail());

		ResultSet rs = pStamentUsuario.executeQuery();
		if (rs.next()) {
			return Optional.of(rs.getInt("id"));
		}
		return Optional.empty();
	}

	private void insertRooms(int reservaId, Reserva r, Connection miConeccion) {

		PreparedStatement pStamentBuscarHabitacion = null;
		PreparedStatement pStamentConsutaInsertaHabitacion = null;
		ResultSet rsHabitacion = null;
		try {
			for (Habitacion h : r.getHabitacion()) {
				String buscarHabitacion = "SELECT * FROM habitacion WHERE numHabitaciones = ?";
				pStamentBuscarHabitacion = (PreparedStatement) miConeccion.prepareStatement(buscarHabitacion);
				pStamentBuscarHabitacion.setInt(1, h.getNumHabitaciones());
				rsHabitacion = pStamentBuscarHabitacion.executeQuery();
				if (rsHabitacion.next()) {
					String insertarReservaHabitacion = "INSERT INTO reserva_habitacion (reserva_id, numHabitacion) VALUES (?, ?)";
					pStamentConsutaInsertaHabitacion = (PreparedStatement) miConeccion
							.prepareStatement(insertarReservaHabitacion);
					pStamentConsutaInsertaHabitacion.setInt(1, reservaId);
					pStamentConsutaInsertaHabitacion.setInt(2, rsHabitacion.getInt("numHabitaciones"));
					pStamentConsutaInsertaHabitacion.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsHabitacion != null)
					rsHabitacion.close();
				if (pStamentBuscarHabitacion != null)
					pStamentBuscarHabitacion.close();
				if (pStamentConsutaInsertaHabitacion != null)
					pStamentConsutaInsertaHabitacion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos");
			}
		}
	}

	public Optional<Calificacion> findCalificacionByReservaId(int reservaId, Connection conn) {
		Calificacion calificacion = null;

		try (java.sql.PreparedStatement stmt = conn.prepareStatement(SELECT_CALIFICACION_BY_RESERVA)) {
			stmt.setInt(1, reservaId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int valor = rs.getInt("puntaje");
				String comentario = rs.getString("descripcion");
				int idReservaFK = rs.getInt("reserva_id");

				calificacion = new Calificacion(valor, comentario, idReservaFK);
			}
		} catch (SQLException e) {
			System.out.println("Error al recuperar la calificación: " + e.getMessage());

		}

		return Optional.ofNullable(calificacion);
	}

	private void insertServices(int reservaId, Reserva r, Connection miConeccion) {
		PreparedStatement pStamentBuscarServicio = null;
		PreparedStatement pStamentConsutaInsertaServicio = null;
		ResultSet rsServicio = null;

		try {
			for (Servicio s : r.getServicios()) {
				String buscarServicio = "SELECT * FROM servicio WHERE nombre = ?";
				pStamentBuscarServicio = (PreparedStatement) miConeccion.prepareStatement(buscarServicio);
				pStamentBuscarServicio.setString(1, s.getNombre());
				rsServicio = pStamentBuscarServicio.executeQuery();
				if (rsServicio.next()) {
					String insertarReservaServicio = "INSERT INTO reserva_servicio (reserva_id, servicio_id) VALUES (?, ?)";
					pStamentConsutaInsertaServicio = (PreparedStatement) miConeccion
							.prepareStatement(insertarReservaServicio);
					pStamentConsutaInsertaServicio.setInt(1, reservaId);
					pStamentConsutaInsertaServicio.setInt(2, rsServicio.getInt("id"));
					pStamentConsutaInsertaServicio.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsServicio != null)
					rsServicio.close();
				if (pStamentBuscarServicio != null)
					pStamentBuscarServicio.close();
				if (pStamentConsutaInsertaServicio != null)
					pStamentConsutaInsertaServicio.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos");
			}
		}
	}

}
