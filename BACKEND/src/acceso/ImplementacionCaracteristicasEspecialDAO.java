package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement;

import ar.edu.unrn.seminario.api.CaracteristicaEspecialDAO;
import ar.edu.unrn.seminario.exception.ConnecionFallidaExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;

public class ImplementacionCaracteristicasEspecialDAO implements CaracteristicaEspecialDAO {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";
	private final static String nuevaCaracteristica = "INSERT INTO caracteristicaespecial (nombre,descripcion,precio) VALUES (?,?,?) ";
	private final static String removeCaracteristica = "DELETE FROM caracteristicaespecial WHERE nombre = ?";
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
		}

	}

	@Override
	public void update(CaracteristicaEspecial caracteristicas) {
		// TODO Auto-generated method stub

	}

	@Override
	public CaracteristicaEspecial find(int id_caracteristicas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String id_caracteristicas) {
		Connection miConeccion = null;
		PreparedStatement pStament = null;
		try {
			miConeccion = conectar();
			pStament = (PreparedStatement) miConeccion.prepareStatement(removeCaracteristica);
			pStament.setString(1, id_caracteristicas);
			pStament.executeUpdate();
			pStament.close();
			System.out.println("eliminado con exito " + id_caracteristicas);
		} catch(SQLException e){
			System.out.println("hola");
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
