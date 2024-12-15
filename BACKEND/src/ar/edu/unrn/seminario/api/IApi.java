package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.ReservaDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.DuplicadaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

@SuppressWarnings("unused")
public interface IApi {

	HabitacionDTO dameLaHabitacion();

	boolean modificamosHabitacion();

	void resetearMemoria();

	void habitacionAModificar(int numeroHabitacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption,
			CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol);

	UsuarioDTO obtenerUsuario(int idUsuario) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	public UsuarioDTO obtenerUsuario() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles();

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripcion, boolean estado); // crear el objeto de dominio �Rol�

	RolDTO obtenerRolPorCodigo(int codigo)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption, ErrorConsultaExeption; // recuperar el rol
																									// almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void crearCaracteristicaEspecial(String nombre, String descripcion, double precio)
			throws ConexionFallidaExeption, DuplicadaExeption; // crea una nueva
	// caracteristica especial y
	// la agrega a la lista de
	// caracteristicas especiale

	void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO);// carga una o mas caracteristica especiales
																			// a una habitación especifica

	boolean autenticarContraseña(String username, String password);// autentica un usuario verificando su nombre de
																	// usuario y contrasena

	void generarReserva(int habitacion[], String usuario, String fechaInicio, String fechaFin, String fechaReserva,
			int cantidadPersonas, String servicio[], boolean pagoMinimo)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption, CampoVacioExeption, EnterosEnCeroExeption,
			PrecioCeroExeption, ErrorConsultaExeption;// Genera una
	// reserva para
	// una o mas
	// habitaciones durante un periodo
	// especifico de tiempo

	void modificarReserva(); // modifica una reserva existente

	void desactivarHabitacion(int numeroHabitacion, String fecha) throws ConexionFallidaExeption,
			ErrorDatosNoEncontradosExeption, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;// marca una
																											// habitacion
																											// como no
																											// habilitada
	// segun
	// su numero de

	List<HabitacionDTO> obtenerTodasLasHabitaciones() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	List<HabitacionDTO> obtenerHabitacionesHabilitada() throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption; // habitacion
																															// habilitadas

	List<CaracteristicaEspecialDTO> obtenerCaracteristica()
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas);

	void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas)
			throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, DuplicadaExeption;

	HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion) throws ConexionFallidaExeption,
			ErrorDatosNoEncontradosExeption, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;

	void modificarHabitacion(int numeroHabitacion, String string, double d, boolean habilitado, int i, String[] strings)
			throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, DuplicadaExeption, ErrorDatosNoEncontradosExeption;

	void eliminarHabitacion(int numeroHabitacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void eliminarCaracteristica(String nombreCaracteristica)
			throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, String[] caracteristicas)
			throws NumeroHabitacionExistenteException, CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, DuplicadaExeption, ErrorConsultaExeption, ErrorDatosNoEncontradosExeption;

	void generarCalificacionHabitacion(int idReserva, int calificacion, String comentario)
			throws ConexionFallidaExeption;

	void activarHabitacion(int numHabitacion) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption,
			CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption;

	List<ReservaDTO> obtenerReserva() throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption,
			ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	void updateCalificacionReserva(int reservaId, int ratingValue) throws Exception;

	void modificarFalse();

	boolean iniciarSesion(String text, String text2) throws ConexionFallidaExeption, ErrorDatosNoEncontradosExeption;

	boolean esAdmin();

	void cancelarReserva(int numReserva);

}
