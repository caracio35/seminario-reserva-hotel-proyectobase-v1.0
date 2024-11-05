package ar.edu.unrn.seminario.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import acceso.ImplementacionCalificacionDAO;
import acceso.ImplementacionCaracteristicasEspecialDAO;
import acceso.ImplementacionHabitacionDAO;
import acceso.ImplementacionReservaDAO;
import acceso.ImplementacionUsuarioDAO;
import acceso.ImplementaionServicioDAO;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
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
import ar.edu.unrn.seminario.modelo.Servicio;
import ar.edu.unrn.seminario.modelo.Usuario;

public class PersistenceApi implements IApi {
	private int habitacionAModificar = 0;
	private Habitacion habitacion;

	public void resetearMemoria() {
		this.habitacionAModificar = 0;
		this.habitacion = null;
	}

	public boolean modificamosHabitacion() {
		return habitacionAModificar != 0;
	}

	public void habitacionAModificar(int numeroHabitacion) throws ConexionFallidaExeption {
		ImplementacionHabitacionDAO habitacion1 = new ImplementacionHabitacionDAO();
		this.habitacionAModificar = numeroHabitacion;
		this.habitacion = habitacion1.find(numeroHabitacion);
	}

	public HabitacionDTO dameLaHabitacion() {

		List<CaracteristicaEspecialDTO> caracteristicasDTO = new ArrayList<>();

		// Verificar si la lista de características especiales es nula
		if (habitacion.getCaracteristicasEspeciale() != null) {
			for (CaracteristicaEspecial caracteristica : habitacion.getCaracteristicasEspeciale()) {
				CaracteristicaEspecialDTO caracteristicaDTO = new CaracteristicaEspecialDTO(caracteristica.getNombre(),
						caracteristica.getDescripcion(), caracteristica.getPrecio());
				caracteristicasDTO.add(caracteristicaDTO);
			}
		}

		// Convertir fecha de desactivación a String, si existe
		String fechaDesactivacion = habitacion.getFechaHastaCuandoEstaDesactivado() != null
				? habitacion.getFechaHastaCuandoEstaDesactivado().toString()
				: null;

		return new HabitacionDTO(habitacion.getCantidadDeCamas(), habitacion.getDescripcion(), habitacion.getPrecio(),
				habitacion.isHabilitado(), habitacion.getNumHabitaciones(), caracteristicasDTO, fechaDesactivacion);
	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		// TODO Auto-generated method stub

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
	public List<UsuarioDTO> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void crearCaracteristicaEspecial(String nombre, String descripcion, double precio) {
		CaracteristicaEspecial c = new CaracteristicaEspecial(nombre, descripcion, precio);
		ImplementacionCaracteristicasEspecialDAO i = new ImplementacionCaracteristicasEspecialDAO();
		i.create(c);

	}

	@Override
	public void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generarCalificacionHabitacion(int idReserva, Calificacion calificacion, String cometario) {
		ImplementacionCalificacionDAO calificacionDAO = new ImplementacionCalificacionDAO();
		Calificacion calificion = new Calificacion(calificacion.getValor(), cometario, idReserva);
		calificacionDAO.create(calificacion, idReserva);

	}

	@Override
	public boolean autenticarContraseña(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generarReserva(int[] habitacion, String usuario, String fechaInicio, String fechaFin,
			String fechaReserva, int cantidadPersonas, String[] servicio, boolean pagoMinimo)
			throws ConexionFallidaExeption {
		ImplementacionReservaDAO reservaDAO = new ImplementacionReservaDAO();
		ImplementacionUsuarioDAO usuarioDAO = new ImplementacionUsuarioDAO();
		Usuario usuario1 = usuarioDAO.find(usuario);
		ArrayList<Habitacion> habitacionObtenida = new ArrayList<>();
		ArrayList<Servicio> servicioObten = new ArrayList<>();
		for (int n : habitacion) {
			Habitacion habitacin = buscarHabitacionPorNumero(n);
			habitacionObtenida.add(habitacin);
		}
		for (String s : servicio) {
			Servicio servicioJ = buscarServicio(s);
			servicioObten.add(servicioJ);
		}

		Reserva reserva2 = new Reserva(0, habitacionObtenida, usuario1, convertiFecha(fechaInicio),
				convertiFecha(fechaFin), cantidadPersonas, servicioObten, convertiFecha(fechaReserva), pagoMinimo);
		reservaDAO.create(reserva2);
	}

	@Override
	public void modificarReserva() {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivarHabitacion(int numeroHabitacion, String fecha) {
		
		ImplementacionHabitacionDAO habitacionDAO = new ImplementacionHabitacionDAO();
		LocalDate fechaDesactivacion = convertiFecha(fecha);
		habitacionDAO.deactivate(numeroHabitacion, fechaDesactivacion);
	}

	@Override
	public List<HabitacionDTO> obtenerTodasLasHabitaciones() throws ConexionFallidaExeption {
		ImplementacionHabitacionDAO habitacion = new ImplementacionHabitacionDAO();
		Set<Habitacion> ListaHabitaciones = habitacion.findAll();
		List<HabitacionDTO> listaHabitacionesDTO = ListaHabitaciones.stream()
				.map(h -> new HabitacionDTO(
						h.getCantidadDeCamas(),
						h.getDescripcion(),
						h.getPrecio(),
						h.isHabilitado(),
						h.getNumHabitaciones(),
						mapearCaracteriticasDTO(h),
						tranformaFechaString(h)))
				.collect(Collectors.toList());
		return listaHabitacionesDTO;
	}

	private List<CaracteristicaEspecialDTO> mapearCaracteriticasDTO(Habitacion h) {
		List<CaracteristicaEspecialDTO> car = h.getCaracteristicasEspeciale().stream()
				.map(c -> new CaracteristicaEspecialDTO(
						c.getNombre(),
						c.getDescripcion(),
						c.getPrecio()))
				.collect(Collectors.toList());
		return car;
	}

	private String tranformaFechaString(Habitacion h) {
		// fijase esto
		if (h.getFechaHastaCuandoEstaDesactivado() != null) {
			String fechaDesactivacion = h.getFechaHastaCuandoEstaDesactivado().toString();
			return fechaDesactivacion;
		}
		return null;
	}

	@Override
	public List<HabitacionDTO> obtenerHabitacionesHabilitada() throws ConexionFallidaExeption {
		List<HabitacionDTO> listaHabitacionesDTO = this.obtenerTodasLasHabitaciones();
		List<HabitacionDTO> HabitacionHabilitada = listaHabitacionesDTO.stream().filter(h -> h.isHabilitado() == true)
				.sorted(Comparator.comparingInt(h -> h.getNumHabitacion())).collect(Collectors.toList());
		return HabitacionHabilitada;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica() {
		ImplementacionCaracteristicasEspecialDAO i = new ImplementacionCaracteristicasEspecialDAO();
		Set<CaracteristicaEspecial> caracteristicas = i.findAll();

		List<CaracteristicaEspecialDTO> car = caracteristicas.stream()
				.map(c -> new CaracteristicaEspecialDTO(
						c.getNombre(),
						c.getDescripcion(),
						c.getPrecio()))
				.collect(Collectors.toList());

		return car;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, String[] caracteristicas) throws NumeroHabitacionExistenteException, CampoVacioExeption,
			EnterosEnCeroExeption, PrecioCeroExeption, ConexionFallidaExeption, DuplicadaExeption {

		ImplementacionHabitacionDAO habitacion = new ImplementacionHabitacionDAO();
		ImplementacionCaracteristicasEspecialDAO caracteristicaDAO = new ImplementacionCaracteristicasEspecialDAO();
		ArrayList<CaracteristicaEspecial> obtenidaCar = buscarCaracteristica(caracteristicas, caracteristicaDAO);

		Habitacion habitacion1 = new Habitacion(cantidadDeCamas, descripcion, precio, habilitado, numHabitacion,
				obtenidaCar);
		habitacion.create(habitacion1);

	}

	private ArrayList<CaracteristicaEspecial> buscarCaracteristica(String[] caracteristicas,
			ImplementacionCaracteristicasEspecialDAO caracteristicaDAO) {
		ArrayList<CaracteristicaEspecial> caracteristicasLista = new ArrayList<>();
		for (String caracteristica : caracteristicas) {
			CaracteristicaEspecial especial = caracteristicaDAO.find(caracteristica);

			caracteristicasLista.add(especial);
		}
		return caracteristicasLista;
	}

	@Override
	public HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, String[] caracteristicas) throws NumeroHabitacionExistenteException, CampoVacioExeption,
			EnterosEnCeroExeption, PrecioCeroExeption, ConexionFallidaExeption, DuplicadaExeption {

		ImplementacionHabitacionDAO habitacion = new ImplementacionHabitacionDAO();
		ImplementacionCaracteristicasEspecialDAO caracteristicaDAO = new ImplementacionCaracteristicasEspecialDAO();
		ArrayList<CaracteristicaEspecial> obtenidaCar = buscarCaracteristica(caracteristicas, caracteristicaDAO);
		// fijase esto aca debe ser tratadas las excepciones??
		try {
			Habitacion habitacion1 = new Habitacion(cantidadDeCamas, descripcion, precio, habilitado, numHabitacion,
					obtenidaCar);
			habitacion.update(habitacion1);
			this.habitacionAModificar = 0;
			this.habitacion = null;
		} catch (CampoVacioExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EnterosEnCeroExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrecioCeroExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarHabitacion(int numeroHabitacion)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption {
		ImplementacionHabitacionDAO i = new ImplementacionHabitacionDAO();
		i.remove(numeroHabitacion);

	}

	@Override
	public void eliminarCaracteristica(String nombreCaracteristica) {
		ImplementacionCaracteristicasEspecialDAO i = new ImplementacionCaracteristicasEspecialDAO();
		i.remove(nombreCaracteristica);

	}

	@Override
	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas)
			throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, DuplicadaExeption {

		// TODO Auto-generated method stub
		// Eliminar depues metodo sobre cargado para que no falle memoryApi al ejecutar
	}

	private Habitacion buscarHabitacionPorNumero(int idNumHabitacion) throws ConexionFallidaExeption {
		ImplementacionHabitacionDAO habitacionDAO = new ImplementacionHabitacionDAO();
		ImplementacionCaracteristicasEspecialDAO caracteristicasDAO = new ImplementacionCaracteristicasEspecialDAO();

		Set<CaracteristicaEspecial> caracteristicasSet = caracteristicasDAO
				.obtenerCaracteristicasPorHabitacion(idNumHabitacion);

		ArrayList<CaracteristicaEspecial> caracteristicasList = new ArrayList<>(caracteristicasSet);

		Habitacion habitacion = habitacionDAO.find(idNumHabitacion);

		habitacion.setCaracteristicasEspeciale(caracteristicasList);

		return habitacion;
	}

	private Servicio buscarServicio(String nombre) {
		ImplementaionServicioDAO servicioDAO = new ImplementaionServicioDAO();
		Servicio servicio = servicioDAO.find(nombre);
		return servicio;
	}

	private LocalDate convertiFecha(String fecha) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(fecha, formato);
	}

	@Override
	public void activarHabitacion(int numHabitacion) {
		ImplementacionHabitacionDAO habitacionDAO = new ImplementacionHabitacionDAO();
		habitacionDAO.activate(numHabitacion);
	}

}
