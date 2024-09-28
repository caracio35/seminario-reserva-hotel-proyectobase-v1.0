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
	
	void crearHabitacion(int cantidadDeCamas, String descripcion, double precio , boolean habilitado , int numeroHabitacion , String nombre ); //CREA EL OBJETO DE DOMINIO HABITACION
	
	void crearCaracteristicaEspecial(String nombre , String descripcion , int precio);  //CREA EL OBJETO DE CARACTERISTICA ESPECIAL DEL DOMINIO 
	
	void cargarCaracteristica(String nombreCaracteristica[] , int numeroHabitacion []); // CARGA LAS CARACTERISTICAS MISMAS CARACTERISTICAS A UNA O VARIAS HABITACION 
	
	void generarCalificacionHabitacion(int valor , String comentario , int idReserva); //CREA LA CALIFICACION Y LA CARGA A UNA RESERVA 
	
	boolean autenticarContraseña(String username, String password) ; //VERIFICA QUE LA CONTRASENIA SEA CORRECTA 
	
	void generarReserva(int habitaciones[] , String usuario , String fechaInicio , String fechaFin , int cantidadPersonas , int iDservicios[] ); //CREA LA RESERVA DEL DOMINIO 
	
	void modificarReserva(int idReserva , int habitaciones[] , String fechaInico , String fechaDeSalida, int cantidadDePersona , String servicio); //MODIFICA LA RESERVA 
	
	void darDeBajaHabitacion(int numeroHabitacion); //GENERA UNA BAJA DE LA HABITACION 
}
