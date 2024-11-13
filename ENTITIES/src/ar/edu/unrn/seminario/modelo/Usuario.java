package ar.edu.unrn.seminario.modelo;

@SuppressWarnings("unused")
public class Usuario {
	private String usuario;
	private String contrasena;
	private String nombre;
	private String apelliido;
	private String email;
	private int dni;
	private String telefono;

	public Usuario(String usuario, String contrasena, String nombre, String apellido, String email, int dni,
			String telefono) {

		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apelliido = apellido;
		this.email = email;
		this.dni = dni;
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
