package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.CalificacionDTO;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;

public class PersistenceApi implements IApi {

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
	public void crearHabitacion(HabitacionDTO habitacionDTO, String[] nombreCaracteristicas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearCaracteristicaEspecial(String nombre, String descripcion, double precio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargarCaracteristica(CaracteristicaEspecialDTO caracteristicaDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarCalificacionHabitacion(CalificacionDTO calificacionDTO, int idReserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean autenticarContraseña(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generarReserva(int[] habitacion, String usuario, String fechaInicio, String fechaFin,
			String fechaReserva, int cantidadPersonas, String[] servicio, boolean pagoMinimo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarReserva() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void darDeBajaHabitacion(int numeroHabitacion, String fecha, int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HabitacionDTO> obtenerTodasLasHabitaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HabitacionDTO> obtenerHabitacionesHabilitada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CaracteristicaEspecialDTO> obtenerCaracteristica(List<String> caracteristicas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeAltaHabitacion(int cantidadDeCamas, String descripcion, double precio, boolean habilitado,
			int numHabitacion, List<CaracteristicaEspecialDTO> caracteristicas)
			throws NumeroHabitacionExistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HabitacionDTO buscarHabitacionDTOPorNumero(int numeroHabitacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarHabitacion(int numeroHabitacion, int cantidadCamas, String descripcion, double precio,
			boolean estado, List<CaracteristicaEspecialDTO> caracteristicas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarHabitacion(int numeroHabitacion) {
		// TODO Auto-generated method stub
		
	}

}