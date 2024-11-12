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

@SuppressWarnings("unused")
public class ImplementacionFacturaDAO implements FacturaDAO {

	private final static String crearFactura = "INSERT INTO Habitacion (reserva_id , fecha , codigo , monto , descripcion) VALUES (?,?,?,?,?)";

	@Override
	public void create(Factura factura) throws ConexionFallidaExeption, NumeroHabitacionExistenteException {
		Connection miConeccion = null;
		PreparedStatement pStamentConsutaCreFactura = null;
		miConeccion = Coneccion.conectar();
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

	}

	@Override
	public Factura find(String nombre) {

		return null;
	}

	@Override
	public void remove(String nombre) {

	}

	@Override
	public Set<Factura> findAll() {

		return null;
	}

}
