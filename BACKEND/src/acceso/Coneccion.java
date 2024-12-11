package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;

public class Coneccion {
	private static Connection conn = null;
    private static Properties prop = null;

    private static Properties getProperties() throws RuntimeException {
        Properties prop = new Properties();
        try {
            ResourceBundle infoDataBase = ResourceBundle.getBundle("database");
            prop.setProperty("connection", infoDataBase.getString("db.url"));
            prop.setProperty("username", infoDataBase.getString("db.user"));
            prop.setProperty("password", infoDataBase.getString("db.password"));
        } catch (Exception e1) {
            throw new RuntimeException("Error al leer la configuración desde el archivo de propiedades.");
        }
        return prop;
    }

    public static void connect() throws ConexionFallidaExeption {
        try {
            prop = getProperties();
            conn = DriverManager.getConnection(
                    prop.getProperty("connection"),
                    prop.getProperty("username"),
                    prop.getProperty("password")
            );
        } catch (SQLException sqlEx) {
        	throw new ConexionFallidaExeption("no se conecto");
        
        }
    }

    public static void disconnect() throws ConexionFallidaExeption {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
            	throw new ConexionFallidaExeption("error al cerrar los recursos");            }
        }
    }

    public static void reconnect() throws ConexionFallidaExeption {
        disconnect();
        connect();
    }

    public static Connection conectar() throws ConexionFallidaExeption {
        if (conn == null) {
            connect();
        }
        return conn;
    }

}
