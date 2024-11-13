package ar.edu.unrn.seminario.dto;

public class UsuarioDTO {
	private String usuario;
	private String contrasena;
	private String nombre;
	private String apelliido;
	private String email;
	private int dni;
	private String telefono;

	public UsuarioDTO( String usuario, String password, String nombre , String apelliido , String email, int dni ,String numeroTelefono ) {
		this.usuario = usuario;
		this.contrasena = password;
		this.nombre = nombre;
		this.apelliido = apelliido;
		this.email = email;
		this.dni = dni; 
		this.telefono = numeroTelefono; 
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String contrasenia() {
		return contrasena;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasena = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getTelefono() {
	return telefono;	
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getApellido() {
		return apelliido;
	}
	public void setApellido(String apellido) {
		this.apelliido = apellido ; 
	}
}
