package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

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

    public static void connect() {
        try {
            prop = getProperties();
            conn = DriverManager.getConnection(
                    prop.getProperty("connection"),
                    prop.getProperty("username"),
                    prop.getProperty("password")
            );
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException sqlEx) {
            System.err.println(
                    "No se pudo conectar a " + prop.getProperty("connection") + ". " + sqlEx.getMessage()
            );
        }
    }

    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static Connection conectar() {
        if (conn == null) {
            connect();
        }
        return conn;
    }

}
