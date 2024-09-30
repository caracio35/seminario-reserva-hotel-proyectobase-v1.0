package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

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
	
	void crearHabitacion(int cantidadDeCamas, String descripcion, double precio , boolean habilitado , int numeroHabitacion ); //crea una nueva habitación con las características proporcionadas
	
	void crearCaracteristicaEspecial(String nombre , String descripcion , int precio); // crea una nueva caracteristica especial y la agrega a la lista de caracteristicas especiale
	
	void cargarCaracteristica(int numeroHabitacion ,String nombreCaracteristica[] );//carga una o mas caracteristica especiales a una habitación especifica
	
	void generarCalificacionHabitacion(int valor , String comentario , int idReserva);//genera una calificacion para una habitacion basada en una reserva
	
	boolean autenticarContraseña(String username, String password) ;//autentica un usuario verificando su nombre de usuario y contrasena
	
	void generarReserva(int habitaciones[] , String usuario , String fechaInicio , String fechaFin , int cantidadPersonas , int iDservicios[] );//Genera una reserva para una o mas habitaciones durante un periodo especifico de tiempo
	
	void modificarReserva(); //modifica una reserva existente
	
	void darDeBajaHabitacion(int numeroHabitacion);//marca una habitacion como no habilitada segun su numero de habitacion
}
