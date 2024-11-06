package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import ar.edu.unrn.seminario.api.ReservaDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Reserva;
import ar.edu.unrn.seminario.modelo.Servicio;

public class ImplementacionReservaDAO implements ReservaDAO{
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String crearHabitacion = "INSERT INTO reserva (usuario_id, fechaDeInicio, fechaDeSalida, cantidadDePersonas,"
			+ "                      fechaDeReserva, saldoFavor, pagoMinimo) VALUES (?, ?, ?, ?, ?, ?, ?);" ;  
	
	@Override
	public void create(Reserva reserva) {
		Connection miConeccion = null;
	    PreparedStatement pStamentConsutaCreaReserva = null;
	    try {
	    miConeccion = conectar();
        miConeccion.setAutoCommit(false);
        pStamentConsutaCreaReserva = (PreparedStatement) miConeccion.prepareStatement(crearHabitacion, Statement.RETURN_GENERATED_KEYS);
        int idUsuario = findUserId(reserva, miConeccion);
        
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
	            if (pStamentConsutaCreaReserva != null) pStamentConsutaCreaReserva.close();
	            if (miConeccion != null) miConeccion.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión");
	        }
	    }
	}


	@Override
	public void update(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reserva find(int idReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Reserva> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	private int findUserId(Reserva r , Connection miConeccion) throws SQLException {
		String buscarIdUsuario = "SELECT id FROM usuarios WHERE nombre = ? AND email = ?";
		PreparedStatement pStamentUsuario = (PreparedStatement) miConeccion.prepareStatement(buscarIdUsuario);
		pStamentUsuario.setString(1, r.getUsuario().getNombre());
	    pStamentUsuario.setString(2, r.getUsuario().getEmail());
	    
	    ResultSet rs = pStamentUsuario.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("id");
	    }
		return -1; 
	}
	private void insertRooms(int reservaId , Reserva r ,Connection miConeccion) {
		
		 PreparedStatement pStamentBuscarHabitacion = null;
		    PreparedStatement pStamentConsutaInsertaHabitacion = null;
		    ResultSet rsHabitacion = null;
		    try { 
		    	for (Habitacion h : r.getHabitacion()) 
		    	{ 
		    		String buscarHabitacion = "SELECT * FROM habitacion WHERE numHabitaciones = ?";
		    		pStamentBuscarHabitacion = (PreparedStatement) miConeccion.prepareStatement(buscarHabitacion);
		    		pStamentBuscarHabitacion.setInt(1, h.getNumHabitaciones());
		    		rsHabitacion = pStamentBuscarHabitacion.executeQuery();
		    		if (rsHabitacion.next())
		    		{
		    			String insertarReservaHabitacion = "INSERT INTO reserva_habitacion (reserva_id, numHabitacion) VALUES (?, ?)";
		    			pStamentConsutaInsertaHabitacion = (PreparedStatement) miConeccion.prepareStatement(insertarReservaHabitacion);
		    			pStamentConsutaInsertaHabitacion.setInt(1, reservaId);
		    			pStamentConsutaInsertaHabitacion.setInt(2, rsHabitacion.getInt("numHabitaciones")); 
		    			pStamentConsutaInsertaHabitacion.executeUpdate();
                }
            }
		}catch (SQLException e) {
        e.printStackTrace();
        } finally {
        try {
            if (rsHabitacion != null) rsHabitacion.close();
            if (pStamentBuscarHabitacion != null) pStamentBuscarHabitacion.close();
            if (pStamentConsutaInsertaHabitacion != null) pStamentConsutaInsertaHabitacion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar los recursos");
        }
    }
	}
	private void insertServices(int reservaId, Reserva r, Connection miConeccion) {
	    PreparedStatement pStamentBuscarServicio = null;
	    PreparedStatement pStamentConsutaInsertaServicio = null;
	    ResultSet rsServicio = null;

	    try {
	        for (Servicio s : r.getServicios()) { 
	        	System.out.println(s.getNombre());
	            String buscarServicio = "SELECT * FROM servicio WHERE nombre = ?";
	            pStamentBuscarServicio = (PreparedStatement) miConeccion.prepareStatement(buscarServicio);
	            pStamentBuscarServicio.setString(1,s.getNombre()); 
	            rsServicio = pStamentBuscarServicio.executeQuery();
	            if (rsServicio.next()) {
	                String insertarReservaServicio = "INSERT INTO reserva_servicio (reserva_id, servicio_id) VALUES (?, ?)";
	                pStamentConsutaInsertaServicio = (PreparedStatement) miConeccion.prepareStatement(insertarReservaServicio);
	                pStamentConsutaInsertaServicio.setInt(1, reservaId);
	                pStamentConsutaInsertaServicio.setInt(2, rsServicio.getInt("id")); 
	                pStamentConsutaInsertaServicio.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        try {
	            if (rsServicio != null) rsServicio.close();
	            if (pStamentBuscarServicio != null) pStamentBuscarServicio.close();
	            if (pStamentConsutaInsertaServicio != null) pStamentConsutaInsertaServicio.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar los recursos");
	        }
	    }
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
