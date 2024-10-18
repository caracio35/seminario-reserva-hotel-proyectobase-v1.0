package ar.edu.unrn.seminario.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unrn.seminario.dto.CalificacionDTO;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Reserva;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

public class BDApi implements IApi {
	private ArrayList<Rol> roles = new ArrayList<>();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Set<Habitacion> habitaciones = new HashSet<>();
	private ArrayList<CaracteristicaEspecial> caracteristicaEspecial = new ArrayList<>();
	private Map<String, String> usuarios1 = new HashMap<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Reserva> reservas = new ArrayList<>();
	private int ultimoIdReserva = 0;

	public BDApi() {

	}

	private Connection conectar() {
		Connection miConeccion = null;
		try {
			miConeccion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Comarca Hoteles?useSSL=false",
					"root", "");
			// agregamos una sentencia SQL para consultar la base de datos
			System.out.println("Conexión establecida");
			return miConeccion;
		} catch (SQLException e) {
			System.out.println("no se conecto: " + e.getMessage());
			return null;
		}

	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'registrarUsuario'");
	}

	@Override
	public UsuarioDTO obtenerUsuario(String username) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuario'");
	}

	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
	}

	@Override
	public List<RolDTO> obtenerRoles() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerRoles'");
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerRolesActivos'");
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'guardarRol'");
	}

	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerRolPorCodigo'");
	}

	@Override
	public void activarRol(Integer codigo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'activarRol'");
	}

	@Override
	public void desactivarRol(Integer codigo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'desactivarRol'");
	}

	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuarios'");
	}

	@Override
	public void activarUsuario(String username) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'activarUsuario'");
	}

	@Override
	public void desactivarUsuario(String username) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'desactivarUsuario'");
	}

	@Override
	public void crearHabitacion(HabitacionDTO habitacionDTO, String[] nombreCaracteristicas) {

	}

	@Override
	public void crearCaracteristicaEspecial(String nombre, String descripcion, double precio) {
		Connection conn = null;
		com.mysql.jdbc.PreparedStatement pstmt = null;
		try {
			conn = conectar();
			String sql = "INSERT INTO caracteristicaespecial (nombre, descripcion, precio) VALUES (?,?,?)";
			pstmt = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, nombre);
			pstmt.setString(2, descripcion);
			pstmt.setDouble(3, precio);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Caracteristica creada con exito");
		} catch (SQLException e) {
			System.out.println("Error al insertar en la tabla caracteristica especial" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar la conexion");
				}

			}
		}
	}

	@Override
	public void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cargarCaracteristica'");
	}

	@Override
	public void generarCalificacionHabitacion(CalificacionDTO calificacionDTO, int idReserva) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'generarCalificacionHabitacion'");
	}

	@Override
	public boolean autenticarContraseña(String username, String password) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'autenticarContraseña'");
	}

	@Override
	public void generarReserva(int[] habitacion, String usuario, String fechaInicio, String fechaFin,
			String fechaReserva, int cantidadPersonas, String[] servicio, boolean pagoMinimo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'generarReserva'");
	}

	@Override
	public void modificarReserva() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'modificarReserva'");
	}

	@Override
	public void darDeBajaHabitacion(int numeroHabitacion, String fecha, int x) {
		Connection conn = null;
		com.mysql.jdbc.PreparedStatement pstmt = null;
		try {
			conn = conectar();
			String sql = "DELETE FROM Habitacion WHERE numHabitaciones = ?";
			pstmt = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, numeroHabitacion);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Caracteristica elimanda con exito");
		} catch (SQLException e) {
			System.out.println("Error al insertar en la tabla caracteristica especial" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar la conexion");
				}

			}
		}
	}

	@Override
	public List<HabitacionDTO> obtenerTodasLasHabitaciones() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerTodasLasHabitaciones'");
	}

	@Override
	public List<HabitacionDTO> obtenerHabitacionesHabilitada() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerHabitacionesHabilitada'");
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerCaracteristica'");
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerCaracteristica'");
	}

	@Override
	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas)
			throws NumeroHabitacionExistenteException {
		Connection conn = null;
		com.mysql.jdbc.PreparedStatement pstmt = null;
		try {
			conn = conectar();
			String sql = "INSERT INTO habitacion (cantidadDeCamas, descripcion, precio, habilitado, fechaHastaCuandoEstaDesactivado, numHabitaciones) VALUES (?,?,?,?,?,?)";
			pstmt = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, cantidadDeCamas);
			pstmt.setString(2, descripcion);
			pstmt.setDouble(3, precio);
			pstmt.setBoolean(4, habilitado);
			pstmt.setDate(5, null);
			pstmt.setInt(6, numHabitacion);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Habitacion creada con exito ");

		} catch (Exception e) {
			System.out.println("Erro al cargar Habitacion" + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					System.out.println("Error al cerrar la coneccion");
				}
			}
		}

	}

	@Override
	public HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buscarHabitacionDTOPorNumero'");
	}

	@Override
	public void modificarHabitacion(int numeroHabitacion, int cantidadCamas, String descripcion, double precio,
			boolean estado, List<CaracteristicaEspecialDTO> caracteristicas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'modificarHabitacion'");
	}

	@Override
	public void eliminarHabitacion(int numeroHabitacion) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminarHabitacion'");
	}
}
