package ar.edu.unrn.seminario.api;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import ar.edu.unrn.seminario.modelo.Reserva;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

public class MemoryApi implements IApi {

	private ArrayList<Rol> roles = new ArrayList();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Set<Habitacion> habitaciones = new HashSet<>();
	private ArrayList<CaracteristicaEspecial> caracteristicaEspecial = new ArrayList<>();
	private Map<String , String > usuarios1 = new HashMap<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Reserva> reservas = new ArrayList<>();
	private int ultimoIdReserva = 0;
	
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
	public void crearHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numeroHabitacion, String nombre) {
		// TODO Auto-generated method stub
		Habitacion primerHabitacion = new Habitacion(cantidadDeCamas ,descripcion,precio ,habilitado, numeroHabitacion , caracteristicaEspecial); 
		this.habitaciones.add(primerHabitacion);
	}
	@Override
	public void crearCaracteristicaEspecial(String nombre, String descripcion, int precio) {
		CaracteristicaEspecial primerCaracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
		this.caracteristicaEspecial.add(primerCaracteristica);
	}

	@Override
	public void generarCalificacionHabitacion(int valor, String comentario , int idReserva) {
		
		Reserva reservaOctenida = buscarReserva(idReserva);
		Calificacion calificacion = new Calificacion(valor, comentario);
		reservaOctenida.setCalificacion(calificacion); 
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
	public void generarReserva(int[] habitaciones, String usuario, String fechaInicio, String fechaFin,
			int cantidadPersonas, int[] iDservicios) {
		
		ArrayList<Habitacion> habitacionesObtenidas = this.obtenerHabitacionesPorNumero(habitaciones);
		ArrayList<Servicio> serviciosOctenido = this.buscarServicio(iDservicios);
		Usuario usuarioOctenido = buscarUsuario(usuario);
		LocalDate fech = this.convertirAfecha(fechaInicio);
		LocalDate fechFin = this.convertirAfecha(fechaFin);
		int idReserva = generadorID();
		Reserva reserva = new Reserva(idReserva ,habitacionesObtenidas, usuarioOctenido, fech, fechFin, cantidadPersonas, serviciosOctenido, false, false, null, null, null, false);
		reservas.add(reserva);
	}
	private ArrayList<Habitacion> obtenerHabitacionesPorNumero(int[] numHabitaciones) {
	    ArrayList<Habitacion> habitacionesObtenidas = new ArrayList<>();
	    int i = 0;
	    // Recorrer el array de números de habitación
	    while (i < numHabitaciones.length) {
	        int numeroHabitacion = numHabitaciones[i];
	        for(Habitacion h : habitaciones) {
	        	if(h.getNumHabitaciones()== numeroHabitacion) {
	        		habitacionesObtenidas.add(h);
	        	}
	        }
	        i++;
	    }

	    return habitacionesObtenidas;
	}
	private ArrayList<Servicio> buscarServicio(int[] IdServicio) {
	    ArrayList<Servicio> serviciosObtenidos = new ArrayList<>();
	    int i = 0;
	    // Recorrer el array de números de habitación
	    while (i <  IdServicio.length) {
	        int num = IdServicio[i];
	        
	        for(Servicio s : this.servicios ) {
	        	if (s.getIdServicio() == num) {
					serviciosObtenidos.add(s);
				}
	        }
	        
	        i++;
	    }
	    return serviciosObtenidos;
	}
	private LocalDate convertirAfecha(String fechaTexto) {
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaTexto, formato);
    }
	private int generadorID() {
	        ultimoIdReserva++;  // Incrementar el ID
	        return ultimoIdReserva;
	    }
	private Reserva buscarReserva(int idReserva) {
        int i = 0;
        boolean encontrada = false;
        Reserva reservaEncontrada = null;
        while (!encontrada && i < reservas.size()) {
            Reserva reservaActual = reservas.get(i);
            if (reservaActual.getId() == idReserva) {
                encontrada = true; // Se encontró la reserva
                reservaEncontrada = reservaActual;
            }
            i++;
        }

        // Si se encontró, devolver la reserva, sino null
        return reservaEncontrada;
    }

	@Override
	public void cargarCaracteristica(String[] nombreCaracteristica, int numeroHabitacion[]) {
		ArrayList<CaracteristicaEspecial> caracteriticaObtenida = this.buscarCaracteristica(nombreCaracteristica);
		ArrayList<Habitacion> habitacionesObtenidas = this.obtenerHabitacionesPorNumero(numeroHabitacion); 
		for(Habitacion h : habitacionesObtenidas) {
			h.setCaracteristicasEspeciale(caracteriticaObtenida);
		}
	}

	@Override
	public void modificarReserva() {
		// TODO Auto-generated method stub
		
	}
	private ArrayList<CaracteristicaEspecial> buscarCaracteristica(String caracteristica [] ){
		ArrayList<CaracteristicaEspecial> caracteristicas = new ArrayList<>();
	    int i = 0;
	    // Recorrer el array de números de habitación
	    while (i < caracteristica.length) {
	        String nombreCar = caracteristica[i];
	        for(CaracteristicaEspecial c : caracteristicaEspecial) {
	            if (c.getNombre().equals(nombreCar)) {
	            	caracteristicas.add(c);
	            }
	        }
	        i++;
	    }

	    return caracteristicas;
	}

	@Override
	public void darDeBajaHabitacion(int numeroHabitacion) {
		 for(Habitacion h : habitaciones ) {
			 if (h.getNumHabitaciones() == numeroHabitacion) 
			 {
	            h.setHabilitado(false);
	            }
			 }
	}
}
	///Cargar una habitación
	//Dar de baja una habitación
	//Modificar habitación 
	//Calificar Habitación 



