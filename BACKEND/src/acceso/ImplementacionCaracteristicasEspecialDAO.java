package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CaracteristicaEspecialDAO;
import ar.edu.unrn.seminario.exception.ConnecionFallidaExeption;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

public class ImplementacionCaracteristicasEspecialDAO implements CaracteristicaEspecialDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaCaracteristica = "INSERT INTO caracteristicaespecial (nombre,descripcion,precio) VALUES (?,?,?) ";
	private final static String eliminarCaracteristica = "DELETE FROM caracteristicaespecial WHERE nombre = ?";
	private final static String encontrarCaracteristica = "SELECT * FROM Habitacion WHERE nombre = ?";
	private final static String encontrarTodasLasCaracteristicas = "SELECT * FROM CaracteristicaEspecial";
	private final static String modificarCaracteristica = "UPDATE caracteristicaespecial SET descripcion = ?, precio = ? WHERE nombre = ?";

	public ImplementacionCaracteristicasEspecialDAO() {

	}

	@Override
	public void create(CaracteristicaEspecial caracteristicas) {
		CaracteristicaEspecial c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(nuevaCaracteristica);

			pStament.setString(1, c.getNombre());
			pStament.setString(2, c.getDescripcion());
			pStament.setDouble(3, c.getPrecio());
			pStament.execute();
			pStament.close();
			System.out.println("Caracteristica Creada con exito");
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
	public void update(CaracteristicaEspecial caracteristicas) {
		CaracteristicaEspecial c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(modificarCaracteristica);

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
	public CaracteristicaEspecial find(String caracteristicas) {
		String c = caracteristicas;
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarCaracteristica);
			pStament.setString(1, c);
			ResultSet rs = pStament.executeQuery();
			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				CaracteristicaEspecial a = new CaracteristicaEspecial(nombre, descripcion, precio);
				return a;
			}
			pStament.execute();
			pStament.close();
			System.out.println("Caracteristica Encontrada con exito");
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
	public void remove(String id_caracteristicas) {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(eliminarCaracteristica);
			pStament.setString(1, id_caracteristicas);
			pStament.executeUpdate();
			pStament.close();
			System.out.println("eliminado con exito " + id_caracteristicas);
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
	public Set<CaracteristicaEspecial> findAll() {
		Set<CaracteristicaEspecial> caracteristicasList = new HashSet<>();
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(encontrarTodasLasCaracteristicas);

			ResultSet rs = pStament.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");

				CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
				caracteristicasList.add(caracteristica);

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
		return caracteristicasList;
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
