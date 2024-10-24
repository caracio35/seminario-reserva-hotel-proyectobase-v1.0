package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.HabitacionDAO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConnecionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCero;
import ar.edu.unrn.seminario.exception.PrecioCero;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;

public class ImplementacionHabitacionDAO implements HabitacionDAO {

	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaHabitacion = "INSERT INTO habitacion (cantidadDeCamas, descripcion, precio, "
			+ "habilitado, fechaHastaCuandoEstaDesactivado, numHabitaciones) VALUES (?,?,?,?,?,?)";
	private final static String buscarHabitacion = "SELECT * FROM habitacion WHERE numHabitaciones = ?";
	private final static String buscarCaracteristicasDeHabitacion = "SELECT c.nombreCaracteristicaEspecial, c.descripcion, c.precio "
	        + "FROM habitacion_caracteristicaespecial ch "
	        + "JOIN caracteristicaespecial c ON ch.nombre = c.nombreCaracteristicaEspecial "
	        + "WHERE ch.numHabitacion = ?";
	private final static String modificarHabitacion = "UPDATE habitacion SET cantidadDeCamas = ?, descripcion = ?, precio = ? WHERE numHabitaciones = ?";
	private final static String eliminarHabitacion = "DELETE FROM habitacion WHERE numHabitaciones = ?";
	
	public ImplementacionHabitacionDAO(){
		
	}
	@Override

	public void create(Habitacion habitacion) {
	    Habitacion h = habitacion; 
	    Connection miConeccion = null;
	    PreparedStatement pStamentHabitacion = null;

	    try {
	        miConeccion = conectar();
	        pStamentHabitacion = (PreparedStatement) miConeccion.prepareStatement(nuevaHabitacion);
	        pStamentHabitacion.setInt(1, h.getCantidadDeCamas());
	        pStamentHabitacion.setString(2, h.getDescripcion());
	        pStamentHabitacion.setDouble(3, h.getPrecio());
	        pStamentHabitacion.setBoolean(4, h.isHabilitado());
	        pStamentHabitacion.setNull(5, java.sql.Types.DATE);
	        pStamentHabitacion.setInt(6, h.getNumHabitaciones());

	        pStamentHabitacion.executeUpdate();

	        PreparedStatement pStamentCaracteristica = insertarCaracterisica(h, miConeccion);

	        pStamentHabitacion.close();
	        pStamentCaracteristica.close();
	        System.out.println("Habitacion creada ee");
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (miConeccion != null) {
	            try {
	                miConeccion.close();
	            } catch (SQLException e) {
	                System.out.println("Error al cerrar la conexión");
	            }
	        }
	    }
	}
	private PreparedStatement insertarCaracterisica(Habitacion h, Connection miConeccion) throws SQLException {
		String insertarHabitacionCaracteristica = "INSERT INTO habitacion_caracteristicaespecial (numHabitacion , nombreCaracteristicaEspecial) VALUES (?, ?)";
		PreparedStatement pStamentCaracteristica = (PreparedStatement) miConeccion.prepareStatement(insertarHabitacionCaracteristica);

		int idHabitacion = h.getNumHabitaciones(); 

		if (h.getCaracteristicasEspeciale() != null) {
		    for (CaracteristicaEspecial car : h.getCaracteristicasEspeciale()) {
		        pStamentCaracteristica.setInt(1, idHabitacion);
		        pStamentCaracteristica.setString(2, car.getNombre()); 
		        pStamentCaracteristica.executeUpdate();
		    }
		} else {
		    System.out.println("No hay características especiales para insertar.");
		}
		return pStamentCaracteristica;
	}

	@Override
	public void update(Habitacion habitacion) {
		Habitacion habitacionNueva = habitacion;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(modificarHabitacion);
			
			pStament.setInt(1, habitacionNueva.getCantidadDeCamas());
			pStament.setString(2, habitacionNueva.getDescripcion());
			pStament.setDouble(3, habitacionNueva.getPrecio());
			pStament.setInt(4, habitacionNueva.getNumHabitaciones());
			pStament.execute();
			pStament.close();
			System.out.println("Habitacion Modificada con exito");
		}
		catch (Exception e) {
			System.out.println("no se subio" + e.getMessage());
		}
		finally {
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
	public Habitacion find(int numHabitaciones) {
		int numeroHabitacionBuscada = numHabitaciones ; 
		Connection miConeccion = null;
		PreparedStatement pStamentBuscarHabitacion = null;
		try {
			miConeccion = conectar();
			pStamentBuscarHabitacion = (PreparedStatement) miConeccion.prepareStatement(buscarHabitacion);
			pStamentBuscarHabitacion.setInt(1, numeroHabitacionBuscada);
			ResultSet habitacionObtenida = pStamentBuscarHabitacion.executeQuery();
			if (habitacionObtenida.next()) {
				int cantidadCamas = habitacionObtenida.getInt("cantidadDeCamas");
				String descripcion = habitacionObtenida.getString("descripcion");
				double precio = habitacionObtenida.getDouble("precio");
				boolean habilitado = (habitacionObtenida.getInt("Habilitado") == 1);
				int numeroHabitacion = habitacionObtenida.getInt("numHabitaciones");
				Habitacion habitacion = new Habitacion(cantidadCamas, descripcion, precio, habilitado, numeroHabitacion ,null);
				return habitacion;
			}
			pStamentBuscarHabitacion.execute();
			pStamentBuscarHabitacion.close();
			System.out.println("Caracteristica Encontrada con exito");
		} catch (SQLException | CampoVacioExeption | EnterosEnCero | PrecioCero e) {
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
			System.out.println("eliminado con exito " + numHabitaciones);
		}
		catch(SQLException e){
			System.out.println("excepcion propia ");
		}
		finally {
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
		// TODO Auto-generated method stub
		return null;
	}

	private Connection conectar() throws ConnecionFallidaExeption {
		Connection miConnecion = null;
		try {
			miConnecion = DriverManager.getConnection(conexion, usuario, clave);
			return miConnecion;
		} catch (Exception e) {
			throw new ConnecionFallidaExeption("no se conecto");

		}
	}
}
