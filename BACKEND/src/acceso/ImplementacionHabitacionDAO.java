package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.HabitacionDAO;
import ar.edu.unrn.seminario.exception.ConnecionFallidaExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;

public class ImplementacionHabitacionDAO implements HabitacionDAO {

	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaHabitacion = "INSERT INTO habitacion (cantidadDeCamas, descripcion, precio, "
			+ "habilitado, fechaHastaCuandoEstaDesactivado, numHabitaciones) VALUES (?,?,?,?,?,?)";
	
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
		// TODO Auto-generated method stub

	}

	@Override
	public Habitacion find(int numHabitaciones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int numHabitaciones) {
		// TODO Auto-generated method stub

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
