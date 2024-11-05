package acceso;

import java.sql.Connection;
import java.sql.Date;
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
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;

public class ImplementacionHabitacionDAO implements HabitacionDAO {

	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaHabitacion = "INSERT INTO habitacion (cantidadDeCamas, descripcion, precio, "
			+ "habilitado, fechaHastaCuandoEstaDesactivado, numHabitaciones) VALUES (?,?,?,?,?,?)";
	private final static String buscarHabitacion = "SELECT * FROM habitacion WHERE numHabitaciones = ?";

	private final static String modificarHabitacion = "UPDATE Habitacion SET cantidadDeCamas, descripcion , precio , habilitado, WHERE numHabitaciones VALUES (?,?,?,?,?)";
	private final static String eliminarHabitacion = "DELETE FROM habitacion WHERE numHabitaciones = ?";
	private final static String buscarTodaLasHabitaciones = "SELECT * FROM habitacion";
	private final static String modificarFechaDeHabitacion = "UPDATE Habitacion SET habilitado = ?, fechaHastaCuandoEstaDesactivado = ? WHERE numHabitaciones = ?";


	public ImplementacionHabitacionDAO() {

	}

	@Override

	public void create(Habitacion habitacion) throws ConexionFallidaExeption {
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

	private void insertarCaracteristica(Habitacion habitacion, Connection miConeccion) throws SQLException {
		String insertarHabitacionCaracteristica = "INSERT INTO habitacion_caracteristicaespecial (numHabitacion , nombreCaracteristicaEspecial) VALUES (?, ?)";

		PreparedStatement pStamentCaracteristica = (PreparedStatement) miConeccion
				.prepareStatement(insertarHabitacionCaracteristica);
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
	}

	@Override
	public void update(Habitacion habitacion) {
		String modificarHabitacion = "UPDATE Habitacion SET cantidadDeCamas = ?, descripcion = ?, precio = ?, habilitado = ? WHERE numHabitaciones = ?";
		try (Connection miConeccion = conectar();
				PreparedStatement pStament = (PreparedStatement) miConeccion.prepareStatement(modificarHabitacion);) {

			// Asigna los valores a cada parámetro en la consulta
			pStament.setInt(1, habitacion.getCantidadDeCamas());
			pStament.setString(2, habitacion.getDescripcion());
			pStament.setDouble(3, habitacion.getPrecio());
			pStament.setBoolean(4, habitacion.isHabilitado());
			pStament.setInt(5, habitacion.getNumHabitaciones()); // Para el WHERE

			// Ejecuta la actualización
		
			pStament.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al actualizar la habitación: " + e.getMessage());
			 e.printStackTrace();
		}
	}

	@Override
	public Habitacion find(int numHabitaciones) {
		int numeroHabitacionBuscada = numHabitaciones;
		Connection miConeccion = null;
		PreparedStatement pStamentConsutataBuscarHabitacion = null;
		try {
			miConeccion = conectar();
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
		} catch (SQLException | CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e) {
			System.out.println("excepcion propia ");
		} finally {
			if (miConeccion != null) {
				try {
					miConeccion.close();
				} catch (SQLException e) {
					System.out.println("error de conexion");
				}
			}
		}
		return null;
	}

	@Override
	public void remove(int numHabitaciones) {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarHabitacion);
			pStament.setInt(1, numHabitaciones);
			pStament.executeUpdate();
			pStament.close();
		} catch (SQLException e) {
			System.out.println("excepcion propia ");
		} finally {
			if (miConeccion != null) {
				try {
					miConeccion.close();
				} catch (SQLException e) {
					System.out.println("error de conexion");
				}
			}
		}
	}

	@Override
	public Set<Habitacion> findAll() {
		Set<Habitacion> listaHabitaciones = new HashSet<>();
		Set<CaracteristicaEspecial> caracteristicasLista = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStamentConsulta = null;
		ResultSet resultadoBusquedaHab = null;

		try {
			miConeccion = conectar();
			pStamentConsulta = (PreparedStatement) miConeccion.prepareStatement(buscarTodaLasHabitaciones);
			resultadoBusquedaHab = pStamentConsulta.executeQuery();

			while (resultadoBusquedaHab.next()) {
				Habitacion habitacion = new Habitacion();
				habitacion.setCantidadDeCamas(resultadoBusquedaHab.getInt("cantidadDeCamas"));
				habitacion.setDescripcion(resultadoBusquedaHab.getString("descripcion"));
				habitacion.setPrecio(resultadoBusquedaHab.getDouble("precio"));
				habitacion.setHabilitado(resultadoBusquedaHab.getBoolean("habilitado"));
				habitacion.setNumHabitaciones(resultadoBusquedaHab.getInt("numHabitaciones"));
				
				java.sql.Date fechaHastaCuandoEstaDesactivado = resultadoBusquedaHab.getDate("fechaHastaCuandoEstaDesactivado");
				if(fechaHastaCuandoEstaDesactivado != null) {
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
			System.out.println("Error al recuperar habitaciones: " + e.getMessage());
		} finally {
			try {
				if (resultadoBusquedaHab != null)
					resultadoBusquedaHab.close();
				if (pStamentConsulta != null)
					pStamentConsulta.close();
				if (miConeccion != null)
					miConeccion.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
		return listaHabitaciones;
	}
	public void deactivate(int numHabitacion , LocalDate fechaDeSactivacion) {
		Connection miConexion = null;
		PreparedStatement pStamentConsultaHabitacion = null;
		try {
			miConexion = conectar();
			pStamentConsultaHabitacion= (PreparedStatement) miConexion.prepareStatement(modificarFechaDeHabitacion);
			pStamentConsultaHabitacion.setBoolean(1, false);
			pStamentConsultaHabitacion.setDate(2, Date.valueOf(fechaDeSactivacion));
			pStamentConsultaHabitacion.setInt(3, numHabitacion);
			pStamentConsultaHabitacion.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pStamentConsultaHabitacion != null) pStamentConsultaHabitacion.close();
	            if (miConexion != null) miConexion.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void activate(int numHabitacion) {
		Connection miConexion = null;
		PreparedStatement pStamentConsultaHabitacion = null;
		try {
			miConexion = conectar();
			pStamentConsultaHabitacion= (PreparedStatement) miConexion.prepareStatement(modificarFechaDeHabitacion);
			pStamentConsultaHabitacion.setBoolean(1, true);
			pStamentConsultaHabitacion.setNull(2, java.sql.Types.DATE);
			pStamentConsultaHabitacion.setInt(3, numHabitacion);
			pStamentConsultaHabitacion.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pStamentConsultaHabitacion != null) pStamentConsultaHabitacion.close();
	            if (miConexion != null) miConexion.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	private Set<CaracteristicaEspecial> obtenerCaracteristicas(int numHabitacion, Connection miConeccion)
			throws SQLException {
		Set<CaracteristicaEspecial> caracteristicas = new HashSet<>();
		String consultaCaracteristicas = "SELECT ce.nombre, ce.descripcion, ce.precio "
				+ "FROM habitacion_caracteristicaespecial hce "
				+ "JOIN caracteristicaespecial ce ON hce.nombreCaracteristicaEspecial = ce.nombre "
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
