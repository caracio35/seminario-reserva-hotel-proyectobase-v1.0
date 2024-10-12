package ar.edu.unrn.seminario.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unrn.seminario.dto.CalificacionDTO;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.ReservaDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCero;
import ar.edu.unrn.seminario.exception.PrecioCero;
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
	private Map<String, String> usuarios1 = new HashMap<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Reserva> reservas = new ArrayList<>();
	private int ultimoIdReserva = 0;

	public MemoryApi() {

		// datos iniciales
		inicializarPrueva();
	}

	private void inicializarPrueva() {
		rolesPrueva();
		inicializarUsuarios();
		caracteristicasPrueva();
		habitacionesPrueba();
	}

	private void habitacionesPrueba() {
		ArrayList<CaracteristicaEspecial> caracteristicas = new ArrayList<>();
		caracteristicas.add(caracteristicaEspecial.get(0));
		this.habitaciones.add(new Habitacion(2, "Habitacion", 100.00, true, 1, caracteristicas));
		caracteristicas.add(caracteristicaEspecial.get(1));
		this.habitaciones.add(new Habitacion(3, "habitacion grande para una familia", 250, true, 2, caracteristicas));
	}

	private void caracteristicasPrueva() {
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("Pileta", "pileta Grande", 100.00));
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("jacuzzi", "Grande", 100.00));
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("Balcon", " Grande", 100.00));
	}

	private void rolesPrueva() {
		this.roles.add(new Rol(1, "ADMIN"));
		this.roles.add(new Rol(2, "USUARIO REGISTRADO"));
		this.roles.add(new Rol(3, "INVITADO"));
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
	// Crea una nueva caracteristica
	public void crearCaracteristicaEspecial(CaracteristicaEspecialDTO caracteristicaEspecialDTO) {
		// falta agregar excepciones
		CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(caracteristicaEspecialDTO.getNombre(),
				caracteristicaEspecialDTO.getDescricion(), caracteristicaEspecialDTO.getPrecio());
		caracteristicaEspecial.add(caracteristica);
	}

	@Override
	public boolean autenticarContraseña(String username, String password) {
		if (usuarios1.containsKey(username)) {
			String compararContrasenia = usuarios1.get(username);
			return compararContrasenia.equals(password);
		}
		return false;
	}

	// obtiene una lista de habitaciones que coinciden con los números de habitacion
	// si no se encuentran ninguna habitacion la lista retornada sera vacía
	private ArrayList<Habitacion> obtenerHabitacionesPorNumero(int[] numHabitaciones) {
		ArrayList<Habitacion> habitacionesObtenidas = new ArrayList<>();
		int i = 0;
		// Recorrer el array de números de habitación
		while (i < numHabitaciones.length) {
			int numeroHabitacion = numHabitaciones[i];
			Habitacion habitacionBuscada = buscarHabitacion(numeroHabitacion);
			if (habitacionBuscada != null) {
				habitacionesObtenidas.add(habitacionBuscada);
				i++;
			}
			i++;
		}

		return habitacionesObtenidas;
	}

	// busca y devuelve una lista de servicios según los identificadores pasado por
	// parametro
	// Si no se encuentra ningun servicio se devuelve una lista vacia
	private ArrayList<Servicio> buscarServicio(int[] IdServicio) {
		ArrayList<Servicio> serviciosObtenidos = new ArrayList<>();
		int i = 0;
		// Recorrer el array de números de habitación
		while (i < IdServicio.length) {
			int num = IdServicio[i];

			for (Servicio s : this.servicios) {
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

	// crea identificadores unico para la reserva
	private int generadorID() {
		ultimoIdReserva++; // Incrementar el ID
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

	// busca y devuelve una habitacion especifica basada en su numero de habitacin
	// si no se encuentra ninguna habitacion con ese numero devuelve null
	private Habitacion buscarHabitacion(int numeroHabitacion) {
		for (Habitacion h : habitaciones) {
			if (h.getNumHabitaciones() == numeroHabitacion) {
				return h;
			}
		}
		return null;
	}

	@Override
	public void modificarReserva() {
		// TODO Auto-generated method stub

	}

	// busca y devuelve una lista de caracteristica especiale que coincidan con los
	// nombre pasados
	private ArrayList<CaracteristicaEspecial> buscarCaracteristica(String caracteristica[]) {
		ArrayList<CaracteristicaEspecial> caracteristicas = new ArrayList<>();
		int i = 0;
		// Recorrer el array de números de habitación
		while (i < caracteristica.length) {
			String nombreCar = caracteristica[i];
			for (CaracteristicaEspecial c : caracteristicaEspecial) {
				if (c.getNombre().equals(nombreCar)) {
					caracteristicas.add(c);
				}
			}
			i++;
		}

		return caracteristicas;
	}

	@Override
	// busca una habitacion y la da de baja
	public void darDeBajaHabitacion(int numeroHabitacion) {
		// falta agregar excepciones
		Habitacion habitacionOctenida = this.buscarHabitacion(numeroHabitacion);
		habitaciones.remove(habitacionOctenida);
		habitacionOctenida.setHabilitado(false);
		habitaciones.add(habitacionOctenida);
	}

	@Override
	public void crearHabitacion(HabitacionDTO habitacionDTO) {
		String[] car = { "Pileta", "jacuzzi", "balcon" };
		ArrayList<CaracteristicaEspecial> caracteristicas = this.buscarCaracteristica(car);

		Habitacion habitacion = new Habitacion(habitacionDTO.getCantidadDeCamas(), habitacionDTO.getDescripcion(),
				habitacionDTO.getPrecio(), habitacionDTO.isHabilitado(), habitacionDTO.getNumHabitacion(),
				caracteristicas);
		habitaciones.add(habitacion);

	}

	@Override
	public void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO) {

		CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(caracteristicaDTO.getNombre(),
				caracteristicaDTO.getDescricion(), caracteristicaDTO.getPrecio());
		caracteristicaEspecial.add(caracteristica);
	}

	@Override
	public void generarCalificacionHabitacion(CalificacionDTO calificacioDTO, int idReserva) {
		Reserva reservaObtenida = this.buscarReserva(idReserva);
		reservaObtenida.setCalificacion(new Calificacion(calificacioDTO.getValor(), calificacioDTO.getComentario()));

	}

	@Override
	public void generarReserva(ReservaDTO reservaDTO) {

		ArrayList<Habitacion> habitacinesObtenidos = this.obtenerHabitacionesPorNumero(reservaDTO.getHabitacion());
		Usuario usuario = this.buscarUsuario(reservaDTO.getUsuario());
		LocalDate fechaIni = this.convertirAfecha(reservaDTO.getFechaDeInicio());
		LocalDate fechaFin = this.convertirAfecha(reservaDTO.getFechaDeSalida());
		ArrayList<Servicio> serviciosObtenidos = this.buscarServicio(null);// pregunta como hacer esto
		LocalDate fechaReserva = this.convertirAfecha(reservaDTO.getFechaDeReserva());

		Reserva reserva = new Reserva(generadorID(), habitacinesObtenidos, usuario, fechaIni, fechaFin,
				reservaDTO.getCantidadDePersonas(), serviciosObtenidos, false, false, null, fechaReserva, null,
				reservaDTO.getPagoMinimo());
		reservas.add(reserva);
	}

	public List<HabitacionDTO> obtenerTodasLasHabitaciones() {
		List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

		for (Habitacion h : habitaciones) {
			try {
				// Crear la lista de características especiales para la habitación
				List<CaracteristicaEspecialDTO> caracteristicasDTO = new ArrayList<>();
				for (CaracteristicaEspecial carac : h.getCaracteristicasEspeciale()) {
					caracteristicasDTO.add(new CaracteristicaEspecialDTO(
							carac.getNombre(),
							carac.getDescripcion(),
							carac.getPrecio()));
				}

				// Crear el objeto HabitacionDTO y agregarlo a la lista
				habitacionesDTO.add(new HabitacionDTO(
						h.getCantidadDeCamas(),
						h.getDescripcion(),
						h.getPrecio(),
						h.isHabilitado(),
						h.getNumHabitaciones(),
						caracteristicasDTO));
			} catch (PrecioCero e) {
				System.out.println("El precio no puede ser cero.");
			} catch (CampoVacioExeption e) {
				System.out.println("Un campo requerido está vacío.");
			} catch (EnterosEnCero e) {
				System.out.println("Un campo que requiere un número mayor a cero tiene un valor inválido.");
			}
		}

		return habitacionesDTO;
	}

	@Override
	public List<HabitacionDTO> obtenerHabitacionesHabilitada() {
		List<HabitacionDTO> obtenerHabitacon = new ArrayList<>();
		for (Habitacion h : habitaciones) {
			if (h.isHabilitado() == true) {
				try {
					try {
						List<CaracteristicaEspecialDTO> car = new ArrayList<>();
						for (CaracteristicaEspecial carac : h.getCaracteristicasEspeciale()) {
							car.add(new CaracteristicaEspecialDTO(carac.getNombre(), carac.getDescripcion(),
									carac.getPrecio()));
						}
						obtenerHabitacon.add(new HabitacionDTO(h.getCantidadDeCamas(), h.getDescripcion(),
								h.getPrecio(), h.isHabilitado(), h.getNumHabitaciones(), car));
					} catch (PrecioCero e) {
						System.out.println("Este campo no pueden estar en cero ");
					}
				} catch (CampoVacioExeption e) {
					System.out.println("Este campo no puede estar vacio");
				} catch (EnterosEnCero e) {
					System.out.println("Este campo no puede estar en cero ");
				}
			}
		}
		return obtenerHabitacon;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica() {
		List<CaracteristicaEspecialDTO> obtenerCaracteristicas = new ArrayList<>();
		for (CaracteristicaEspecial car : caracteristicaEspecial) {
			obtenerCaracteristicas
					.add(new CaracteristicaEspecialDTO(car.getNombre(), car.getDescripcion(), car.getPrecio()));
		}
		return obtenerCaracteristicas;
	}

}
// Dar de baja una habitación
// Modificar habitación
// Calificar Habitación
