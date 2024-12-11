package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.ServicioDAO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.modelo.Servicio;

@SuppressWarnings("unused")
public class ImplementaionServicioDAO implements ServicioDAO {

	private final static String nuevoServicio = "INSERT INTO Servicio (id,nombre,precio,descripcion) VALUES (?,?,?,?) ";
	private final static String eliminarServicio = "DELETE FROM Sevicio WHERE id = ?";
	private final static String encontrarServicio = "SELECT * FROM Servicio WHERE nombre = ?";
	private final static String encontrarTodasLosServicios = "SELECT * FROM Servicio";
	private final static String modificarServicio = "UPDATE Servicio SET nombre = ? ,precio = ?, descripcion = ? WHERE id = ?";

	@Override
	public void create(Servicio servicio) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Servicio s = servicio;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			pStament = (PreparedStatement) miConeccion.prepareStatement(nuevoServicio);

			pStament.setString(1, s.getNombre());
			pStament.setDouble(2, s.getPrecio());
			pStament.setString(3, s.getDescripcion());
			pStament.setInt(4, s.getIdServicio());
			pStament.execute();
			pStament.close();

		} catch (Exception e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}

	}

	@Override
	public void update(Servicio servicio) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Servicio c = servicio;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {

			pStament = (PreparedStatement) miConeccion.prepareStatement(modificarServicio);
			pStament.setInt(1, 0);
			pStament.setString(1, c.getDescripcion());
			pStament.setDouble(2, c.getPrecio());
			pStament.setString(3, c.getNombre());
			pStament.execute();
			pStament.close();

		} catch (Exception e) {
			throw new ErrorDatosNoEncontradosExeption();
		} finally {
			if (miConeccion != null) {
				Coneccion.disconnect();
			}
		}

	}

	@Override
	public Servicio find(String nombre) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		String n = nombre;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {

			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarServicio);
			pStament.setString(1, n);
			ResultSet rs = pStament.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String nombres = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				Servicio a = new Servicio(id, nombres, precio, descripcion);
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
	public void remove(int id) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarServicio);
			pStament.setInt(1, id);
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
	public Set<Servicio> findAll() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Set<Servicio> servicioList = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		miConeccion = Coneccion.conectar();
		try {
			
			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarTodasLosServicios);

			ResultSet rs = pStament.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");

				Servicio servicio = new Servicio(id, nombre, precio, descripcion);
				servicioList.add(servicio);

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
		return servicioList;
	}

}
