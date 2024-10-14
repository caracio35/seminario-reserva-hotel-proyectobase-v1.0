package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.CalificacionDTO;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.ReservaDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;

public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol);

	UsuarioDTO obtenerUsuario(String username);

	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles();

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripcion, boolean estado); // crear el objeto de dominio �Rol�

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void crearHabitacion(HabitacionDTO habitacionDTO, String nombreCaracteristicas[]); // crea una nueva habitación con
																						// las características
																						// proporcionadas

	void crearCaracteristicaEspecial(CaracteristicaEspecialDTO caracteristicaEspecialDTO); // crea una nueva
																							// caracteristica especial y
																							// la agrega a la lista de
																							// caracteristicas especiale

	void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO);// carga una o mas caracteristica especiales
																			// a una habitación especifica

	void generarCalificacionHabitacion(CalificacionDTO calificacionDTO, int idReserva);// genera una calificacion
																						// paranbuna habitacion basada
																						// en una reserva

	boolean autenticarContraseña(String username, String password);// autentica un usuario verificando su nombre de
																	// usuario y contrasena

	void generarReserva(ReservaDTO reservaDTO);// Genera una reserva para una o mas habitaciones durante un periodo
												// especifico de tiempo

	void modificarReserva(); // modifica una reserva existente

	void darDeBajaHabitacion(int numeroHabitacion, String fecha, int x);// marca una habitacion como no habilitada segun
																		// su numero de

	List<HabitacionDTO> obtenerTodasLasHabitaciones();

	List<HabitacionDTO> obtenerHabitacionesHabilitada(); // habitacion habilitadas

	List<CaracteristicaEspecialDTO> obtenerCaracteristica();

	List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas);

	void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas) throws NumeroHabitacionExistenteException;

	HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion);

	void modificarHabitacion(int numeroHabitacion, int cantidadCamas, String descripcion, double precio, boolean estado,
			List<CaracteristicaEspecialDTO> caracteristicas);

	void eliminarHabitacion(int numeroHabitacion);
}
