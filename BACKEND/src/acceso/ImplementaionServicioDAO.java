package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.ServicioDAO;
import ar.edu.unrn.seminario.exception.ConnecionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Servicio;

public class ImplementaionServicioDAO implements ServicioDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevoServicio = "INSERT INTO servicio (id,nombre,precio,descripcion) VALUES (?,?,?,?) ";
	private final static String eliminarServicio = "DELETE FROM sevicio WHERE id = ?";
	private final static String encontrarServicio = "SELECT * FROM servicio WHERE id = ?";
	private final static String encontrarTodasLosServicios = "SELECT * FROM servicios";
	private final static String modificarServicio = "UPDATE servicio SET nombre = ? ,precio = ?, descripcion = ? WHERE id = ?";

	@Override
	public void create(Servicio servicio) {
		Servicio s = servicio;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(nuevoServicio);

			pStament.setString(1, s.getNombre());
			pStament.setDouble(2, s.getPrecio());
			pStament.setString(3, s.getDescripcion());
			pStament.setInt(4, s.getIdServicio());
			pStament.execute();
			pStament.close();
			System.out.println("Servicio CreadoS con exito");
		} catch (Exception e) {
			System.out.println("no se subio" + e.getMessage());
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
	public void update(Servicio servicio) {
		Servicio c = servicio;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(modificarServicio);
			pStament.setInt(1, 0);
			pStament.setString(1, c.getDescripcion());
			pStament.setDouble(2, c.getPrecio());
			pStament.setString(3, c.getNombre());
			pStament.execute();
			pStament.close();
			System.out.println("Caracteristica Modificada con exito");
		} catch (Exception e) {
			System.out.println("no se subio" + e.getMessage());
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
	public Servicio find(String nombre) {
		String n = nombre;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
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
			System.out.println("Servicio Encontrado con exito");
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
		return null;
	}

	@Override
	public void remove(int id) {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarServicio);
			pStament.setInt(1, id);
			pStament.executeUpdate();
			pStament.close();
			System.out.println("eliminado con exito " + id);
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
	public Set<Servicio> findAll() {
		Set<Servicio> servicioList = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
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
			System.out.println("Caracteristicas Encontradas con exito");

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
		return servicioList;
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
