package acceso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.FacturaDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.modelo.Factura;

public class ImplementacionFacturaDAO implements FacturaDAO {

	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String crearFactura = "INSERT INTO Habitacion (reserva_id , fecha , codigo , monto , descripcion) VALUES (?,?,?,?,?)"; 
	@Override
	public void create(Factura factura) throws ConexionFallidaExeption, NumeroHabitacionExistenteException {
		Connection miConeccion = null;
		PreparedStatement pStamentConsutaCreFactura = null;
		miConeccion = conectar();
		try {
		
			
			pStamentConsutaCreFactura = (PreparedStatement) miConeccion.prepareStatement(crearFactura);
			pStamentConsutaCreFactura.setInt(1, factura.getReserva().getId());
			pStamentConsutaCreFactura.setDate(2, Date.valueOf(factura.getFecha()));
			pStamentConsutaCreFactura.setInt(3, factura.getCodigo());
			pStamentConsutaCreFactura.setDouble(4, factura.getMonto());
			pStamentConsutaCreFactura.setString(5, factura.getDescripcion());
			
			
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
	} finally {
		try {
			if (pStamentConsutaCreFactura != null)
				pStamentConsutaCreFactura.close();
			if (miConeccion != null)
				miConeccion.close();
		} catch (SQLException e) {
			throw new ConexionFallidaExeption();
		}
	}
			
	}

	@Override
	public void update(Factura factura) {
		// TODO Auto-generated method stub

	}

	@Override
	public Factura find(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String nombre) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Factura> findAll() {
		// TODO Auto-generated method stub
		return null;
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
