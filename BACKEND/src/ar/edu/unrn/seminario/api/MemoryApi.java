package ar.edu.unrn.seminario.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.ReservaDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.DuplicadaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;
import ar.edu.unrn.seminario.modelo.Calificacion;
import ar.edu.unrn.seminario.modelo.CaracteristicaEspecial;
import ar.edu.unrn.seminario.modelo.Habitacion;
import ar.edu.unrn.seminario.modelo.Reserva;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

@SuppressWarnings("unused")
public class MemoryApi implements IApi {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Rol> roles = new ArrayList();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Set<Habitacion> habitaciones = new HashSet<>();
	private ArrayList<CaracteristicaEspecial> caracteristicaEspecial = new ArrayList<>();
	private Map<String, String> usuarios1 = new HashMap<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Reserva> reservas = new ArrayList<>();
	private int ultimoIdReserva = 0;

	public MemoryApi() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {

		// datos iniciales
		inicializarPrueva();
	}

	private void inicializarPrueva() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		rolesPrueva();
		inicializarUsuarios();
		caracteristicasPrueva();
		habitacionesPrueba();
	}

	private void habitacionesPrueba() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		ArrayList<CaracteristicaEspecial> caracteristicas = new ArrayList<>();
		caracteristicas.add(caracteristicaEspecial.get(0));
		caracteristicas.add(caracteristicaEspecial.get(1));

		this.habitaciones.add(new Habitacion(3, "Habitacion grande para una familia", 250, true, 2, caracteristicas));
		this.habitaciones.add(new Habitacion(1, "Habitacion Economica", 50.00, true, 3, caracteristicas));
		this.habitaciones.add(new Habitacion(4, "Suite de Lujo", 300.00, true, 4, caracteristicas));
		this.habitaciones.add(new Habitacion(1, "Habitacion Sencilla", 80.00, true, 5, caracteristicas));
		this.habitaciones.add(new Habitacion(2, "Habitacion Doble", 120.00, true, 6, caracteristicas));
		this.habitaciones.add(new Habitacion(2, "Habitacion Doble Deluxe", 150.00, true, 7, caracteristicas));
		this.habitaciones.add(new Habitacion(3, "Habitacion Triple", 180.00, true, 8, caracteristicas));
	}

	private void caracteristicasPrueva() {
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("Pileta", "pileta Grande", 100.00));
		this.caracteristicaEspecial.add(new CaracteristicaEspecial("Jacuzzi", "Grande", 100.00));
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

	/*
	 * void registrarUsuario(String username, String password, String email, String
	 * nombre, Integer rol);
	 */
	public void registrarUsuario(String usuario, String contrasena, String nombre, String apellido, String email,
			int dni, String telefono) {

		Usuario usuario1 = new Usuario(usuario, contrasena, nombre, apellido, email, dni, telefono, null);
		this.usuarios.add(usuario1);
		this.usuarios1.put(usuario, contrasena);

	}

	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		// for (Usuario u : this.usuarios) {
		// dtos.add(new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(),
		// u.getEmail(), null, false, null));
		// }
		return dtos;
	}

	@Override
	public UsuarioDTO obtenerUsuario(int username) {

		return null;
	}

	@Override
	public void eliminarUsuario(String username) {

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

		Rol rol = new Rol(codigo, descripcion);
		this.roles.add(rol);
	}

	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {

		return null;
	}

	@Override
	public void activarRol(Integer codigo) {

	}

	@Override
	public void desactivarRol(Integer codigo) {

	}

	@Override
	public void activarUsuario(String usuario) {

		Usuario user = this.buscarUsuario(usuario);

	}

	@Override
	public void desactivarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);

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
	public void crearCaracteristicaEspecial(String nombre, String descripcion, double precio) {
		// falta agregar excepciones
		CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(nombre, descripcion, precio);
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

	public void darDeBajaHabitacion(int numeroHabitacion, String fecha, int x)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		Habitacion habitacionObtenida = this.buscarHabitacion(numeroHabitacion);
		if (habitacionObtenida != null) {
			habitaciones.remove(habitacionObtenida);
			habitacionObtenida.setHabilitado(false);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaDesactivacion = LocalDate.parse(fecha, formatter);
			habitacionObtenida.setFechaHastaCuandoEstaDesactivado(fechaDesactivacion);
			habitaciones.add(habitacionObtenida);
		} else {
			throw new ErrorDatosNoEncontradosExeption();
		}
	}

	// @Override
	// public void crearHabitacion(HabitacionDTO habitacionDTO, String
	// nombreCaracteristicas[]) {
	// ArrayList<CaracteristicaEspecial> caracteristicas =
	// this.buscarCaracteristica(nombreCaracteristicas);

	// Habitacion habitacion;
	// try {
	// habitacion = new Habitacion(habitacionDTO.getCantidadDeCamas(),
	// habitacionDTO.getDescripcion(),
	// habitacionDTO.getPrecio(), habitacionDTO.isHabilitado(),
	// habitacionDTO.getNumHabitacion(),
	// caracteristicas);
	// habitaciones.add(habitacion);
	// } catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e) {
	// System.out.println(e.getMessage());
	// }

	// }

	@Override
	public void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO) {

		CaracteristicaEspecial caracteristica = new CaracteristicaEspecial(caracteristicaDTO.getNombre(),
				caracteristicaDTO.getDescricion(), caracteristicaDTO.getPrecio());
		caracteristicaEspecial.add(caracteristica);
	}

	/*
	 * vvoid generarCalificacionHabitacion(int idReserva , int calificacion , String
	 * comentario);
	 */

	public void generarCalificacionHabitacion(int idReserva, Calificacion calificacion, String comentario) {
		Reserva reservaObtenida = this.buscarReserva(idReserva);
		reservaObtenida.setCalificacion(new Calificacion(calificacion.getValor(), comentario, idReserva));

	}

	@Override
	public void generarReserva(int habitacion[], String usuario, String fechaInicio, String fechaFinal,
			String fechaReserva, int cantidadPersonas, String servicio[], boolean pagoMinimo) {

		ArrayList<Habitacion> habitacinesObtenidos = this.obtenerHabitacionesPorNumero(habitacion);
		Usuario usuarioObtenido = this.buscarUsuario(usuario);
		LocalDate fechaIni = this.convertirAfecha(fechaInicio);
		LocalDate fechaFin = this.convertirAfecha(fechaFinal);
		ArrayList<Servicio> serviciosObtenidos = this.buscarServicio(null);// pregunta como hacer esto
		LocalDate fechaReserva1 = this.convertirAfecha(fechaReserva);
		int id = this.generadorID();
		/*
		 * public Reserva(int id , ArrayList<Habitacion> habitaciones, Usuario usuario,
		 * LocalDate fechaDeInicio, LocalDate fechaDESalida, int cantidadDePersonas,
		 * ArrayList<Servicio> servicios, LocalDate fechaDeReserva, boolean pagoMinimo){
		 * 
		 */
		Reserva reserva = new Reserva(id, habitacinesObtenidos, usuarioObtenido, fechaIni, fechaFin, 0,
				serviciosObtenidos, fechaReserva1, pagoMinimo);

		reservas.add(reserva);
	}

	public List<HabitacionDTO> obtenerTodasLasHabitaciones() {
		List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

		for (Habitacion h : habitaciones) {

			// Crear la lista de características especiales para la habitación
			List<CaracteristicaEspecialDTO> caracteristicasDTO = new ArrayList<>();
			for (CaracteristicaEspecial carac : h.getCaracteristicasEspeciale()) {
				caracteristicasDTO.add(
						new CaracteristicaEspecialDTO(carac.getNombre(), carac.getDescripcion(), carac.getPrecio()));
			}

			// Create the HabitacionDTO object and add it to the list
			habitacionesDTO.add(new HabitacionDTO(h.getCantidadDeCamas(), h.getDescripcion(), h.getPrecio(),
					h.isHabilitado(), h.getNumHabitaciones(), caracteristicasDTO, traerFechaHastaCuando(h)));
			// Get the deactivation date and convert it to a string if it's not null

		}
		return habitacionesDTO;
	}

	private String traerFechaHastaCuando(Habitacion h) {
		if (h.getFechaHastaCuandoEstaDesactivado() != null) {
			String fechaDesactivacion = h.getFechaHastaCuandoEstaDesactivado().toString();
			return fechaDesactivacion;
		}
		return null;
	}

	@Override
	public List<HabitacionDTO> obtenerHabitacionesHabilitada() {
		List<HabitacionDTO> obtenerHabitacon = new ArrayList<>();
		for (Habitacion h : habitaciones) {
			if (h.isHabilitado() == true) {
				List<CaracteristicaEspecialDTO> car = new ArrayList<>();
				for (CaracteristicaEspecial carac : h.getCaracteristicasEspeciale()) {
					car.add(new CaracteristicaEspecialDTO(carac.getNombre(), carac.getDescripcion(),
							carac.getPrecio()));
				}
				obtenerHabitacon.add(new HabitacionDTO(h.getCantidadDeCamas(), h.getDescripcion(), h.getPrecio(),
						h.isHabilitado(), h.getNumHabitaciones(), car, null));
				System.out.println("Este campo no pueden estar en cero ");
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

	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas)
			throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, DuplicadaExeption {

		if (existeHabitacionConNumero(numHabitacion)) {
			throw new NumeroHabitacionExistenteException(
					"La habitación con el número " + numHabitacion + " ya existe.");
		}
		if (!existeHabitacionConNumero(numHabitacion)) {
			// try {
			Habitacion habitacion = new Habitacion(cantidadDeCamas, descripcion, precio, habilitado, numHabitacion,
					pasarDesdeCaracteristicasDTO(caracteristicas));
			habitaciones.add(habitacion);
			// } catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e) {
			// throw new Exception(e.getMessage());
			// }
		}

	}

	private ArrayList<CaracteristicaEspecial> pasarDesdeCaracteristicasDTO(
			List<CaracteristicaEspecialDTO> caracteristicas) {
		ArrayList<CaracteristicaEspecial> caracteristicasEspeciales = new ArrayList<>();
		for (CaracteristicaEspecialDTO caracteristica : caracteristicas) {
			caracteristicasEspeciales.add(new CaracteristicaEspecial(caracteristica.getNombre(),
					caracteristica.getDescricion(), caracteristica.getPrecio()));
		}
		return caracteristicasEspeciales;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas) {
		List<CaracteristicaEspecialDTO> obtenerCaracteristicas = new ArrayList<>();
		for (String car : caracteristicas) {
			caracteristicaEspecial.stream().filter(c -> c.getNombre().equals(car)).forEach(c -> {
				obtenerCaracteristicas
						.add(new CaracteristicaEspecialDTO(c.getNombre(), c.getDescripcion(), c.getPrecio()));
			});
		}
		return obtenerCaracteristicas;
	}

	public HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion) {
		Habitacion habitacionOptenida = this.buscarHabitacion(numeroHabitacion);
		List<CaracteristicaEspecialDTO> car = new ArrayList<>();
		ArrayList<CaracteristicaEspecial> prueba = habitacionOptenida.getCaracteristicasEspeciale();
		for (CaracteristicaEspecial carac : prueba) {
			car.add(new CaracteristicaEspecialDTO(carac.getNombre(), carac.getDescripcion(), carac.getPrecio()));
		}

		HabitacionDTO habitacionNueva = null;

		habitacionNueva = new HabitacionDTO(habitacionOptenida.getCantidadDeCamas(),
				habitacionOptenida.getDescripcion(), habitacionOptenida.getPrecio(), habitacionOptenida.isHabilitado(),
				habitacionOptenida.getNumHabitaciones(), car, null);

		return habitacionNueva;
	}

	public void modificarHabitacion(int numeroHabitacion, int cantidadCamas, String descripcion, double precio,
			boolean estado, List<CaracteristicaEspecialDTO> caracteristicas)
			throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
		Habitacion habitacionOptenida = this.buscarHabitacion(numeroHabitacion);
		ArrayList<CaracteristicaEspecial> caracteristica = this.pasarDesdeCaracteristicasDTO(caracteristicas);
		if (habitacionOptenida != null) {

			habitaciones.remove(habitacionOptenida);
			Habitacion nuevaHabitacion = new Habitacion(cantidadCamas, descripcion, precio, estado, numeroHabitacion,
					caracteristica);
			habitaciones.add(nuevaHabitacion);

		}
	}

	public void eliminarHabitacion(int numeroHabitacion) {
		Habitacion habitacionObtenida = this.buscarHabitacion(numeroHabitacion);
		if (habitacionObtenida != null) {
			habitaciones.remove(habitacionObtenida);
		}
	}

	private boolean existeHabitacionConNumero(int numeroHabitacion) {
		for (Habitacion h : habitaciones) {
			if (h.getNumHabitaciones() == numeroHabitacion) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void eliminarCaracteristica(String nombreCaracteristica) {

	}

	@Override
	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, String[] caracteristicas) throws NumeroHabitacionExistenteException, CampoVacioExeption,
			EnterosEnCeroExeption, PrecioCeroExeption, ConexionFallidaExeption, DuplicadaExeption {

	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

	}

	@Override
	public HabitacionDTO dameLaHabitacion() {

		throw new UnsupportedOperationException("Unimplemented method 'dameLaHabitacion'");
	}

	@Override
	public boolean modificamosHabitacion() {

		throw new UnsupportedOperationException("Unimplemented method 'modificamosHabitacion'");
	}

	@Override
	public void habitacionAModificar(int numeroHabitacion) throws ConexionFallidaExeption {

		throw new UnsupportedOperationException("Unimplemented method 'habitacionAModificar'");
	}

	@Override
	public void modificarHabitacion(int numeroHabitacion, String string, double d, boolean habilitado, int i,
			String[] strings) throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption,
			PrecioCeroExeption, ConexionFallidaExeption, DuplicadaExeption {

		throw new UnsupportedOperationException("Unimplemented method 'modificarHabitacion'");
	}

	public void resetearMemoria() {

	}

	@Override
	public void desactivarHabitacion(int numeroHabitacion, String fecha)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {

		throw new UnsupportedOperationException("Unimplemented method 'desactivarHabitacion'");
	}

	@Override
	public void activarHabitacion(int numHabitacion) {

		throw new UnsupportedOperationException("Unimplemented method 'activarHabitacion'");
	}

	@Override
	public List<ReservaDTO> obtenerReserva() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {

		throw new UnsupportedOperationException("Unimplemented method 'obtenerReserva'");
	}

	@Override
	public void updateCalificacionReserva(int reservaId, int ratingValue) {

		throw new UnsupportedOperationException("Unimplemented method 'updateCalificacionReserva'");
	}

	@Override
	public void modificarFalse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generarCalificacionHabitacion(int idReserva, int calificacion, String comentario)
			throws ConexionFallidaExeption {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'generarCalificacionHabitacion'");
	}

	@Override
	public boolean iniciarSesion(String text, String text2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
	}

}

// Dar de baja una habitación
// Modificar habitación
// Calificar Habitación
