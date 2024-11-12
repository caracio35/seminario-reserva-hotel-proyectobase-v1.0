package acceso;

import java.sql.Connection;
import java.sql.DriverManager;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;

public class Coneccion {
	private final static String conexion = "jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false";
	private final static String usuario = "root";
	private final static String clave = "";

	static Connection conectar() throws ConexionFallidaExeption {
		Connection miConexion = null;
		try {
			miConexion = DriverManager.getConnection(conexion, usuario, clave);
			return miConexion;
		} catch (Exception e) {
			throw new ConexionFallidaExeption("no se conecto");

		}
	}

}
