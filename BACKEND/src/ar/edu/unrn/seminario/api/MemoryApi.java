package ar.edu.unrn.seminario.api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

public class MemoryApi implements IApi {

	private ArrayList<Rol> roles = new ArrayList();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Set<Habitacion> habitacion = new HashSet<>();; 
	private ArrayList<CaracteristicaEspecial> caracteristicaEspecial = new ArrayList<>();
	private Map<String , String > usuarios1 = new HashMap<>();
	
	public MemoryApi() {
		
		// datos iniciales
		this.roles.add(new Rol(1, "ADMIN"));
		this.roles.add(new Rol(2, "ESTUDIANTE"));
		this.roles.add(new Rol(3, "INVITADO"));
		inicializarUsuarios();
		
		// 
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("Pileta", "pileta Grande", 100.00));
	}

	private void inicializarUsuarios() {
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);
		registrarUsuario("ldifabio", "4", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("bjosito", "1234", "bjorosito@unrn.edu.ar", "Bruna", 3);

	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

		Rol role = this.buscarRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		this.usuarios.add(usuario);
		this.usuarios1.put(username, password);

	}
	 
	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			dtos.add(new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(), u.getEmail(),
					u.getRol().getNombre(), u.isActivo(), u.obtenerEstado()));
		}
		return dtos;
	}

	@Override
	public UsuarioDTO obtenerUsuario(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RolDTO> obtenerRoles() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			if (r.isActivo())
				dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		// TODO Auto-generated method stub
		Rol rol = new Rol(codigo, descripcion);
		this.roles.add(rol);
	}

	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);
		user.activar();
	}

	@Override
	public void desactivarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);
		user.desactivar();
	}

	private Rol buscarRol(Integer codigo) {
		for (Rol rol : roles) {
			if (rol.getCodigo().equals(codigo))
				return rol;
		}
		return null;
	}

	private Usuario buscarUsuario(String usuario) {
		for (Usuario user : usuarios) {
			if (user.getUsuario().equals(usuario))
				return user;
		}
		return null;
	}
	
	@Override
	public void cargarHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numeroHabitacion, String nombre) {
		// TODO Auto-generated method stub
		Habitacion primerHabitacion = new Habitacion(cantidadDeCamas , descripcion , precio , habilitado, numeroHabitacion , caracteristicaEspecial); 
		this.habitacion.add(primerHabitacion);
	}

	@Override
	public void cargarCaracteristicaEspecial(String nombre, String descripcion, int precio) {
		CaracteristicaEspecial primerCaracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
		this.caracteristicaEspecial.add(primerCaracteristica);
	}

	@Override
	public void generarCalificacionHabitacion(int valor, String comentario , int idReserva) {
		
		Calificacion calificacion = new Calificacion(valor, comentario);
		
	}

	@Override
	public boolean autenticarContraseña(String username, String password) {
		 if (usuarios1.containsKey(username)) {
	            String compararContrasenia = usuarios1.get(username);
	            return compararContrasenia.equals(password); 
	        }
		return false;
	}

	@Override
	public void crearFactura(int reserva, String fecha, Integer codigo, double monto, String descripcion) {
		
	}
}
