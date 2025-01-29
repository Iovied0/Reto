package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Agencia {
	private int id;
	private String nombre, logo, color, usuario, contraseña;
	private int numeroEmpleados;
	private NumeroEmpleados limiteEmpleados;
	private TiposAgencia tipoAgencia;

	public Agencia() {

	}

	public Agencia(int id, String nombre, String logo, String color, int numeroEmpleados, String usuario,
			String contraseña, NumeroEmpleados limiteEmpleados, TiposAgencia tipoAgencia) {

		this.id = id;
		this.nombre = nombre;
		this.logo = logo;
		this.color = color;
		this.numeroEmpleados = numeroEmpleados;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.limiteEmpleados = limiteEmpleados;
		this.tipoAgencia = tipoAgencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public NumeroEmpleados getLimiteEmpleados() {
		return limiteEmpleados;
	}

	public void setLimiteEmpleados(NumeroEmpleados limiteEmpleados) {
		this.limiteEmpleados = limiteEmpleados;
	}

	public TiposAgencia getTipoAgencia() {
		return tipoAgencia;
	}

	public void setTipoAgencia(TiposAgencia tipoAgencia) {
		this.tipoAgencia = tipoAgencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, contraseña, id, limiteEmpleados, logo, nombre, numeroEmpleados, tipoAgencia,
				usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agencia other = (Agencia) obj;
		return Objects.equals(color, other.color) && Objects.equals(contraseña, other.contraseña) && id == other.id
				&& Objects.equals(limiteEmpleados, other.limiteEmpleados) && Objects.equals(logo, other.logo)
				&& Objects.equals(nombre, other.nombre) && numeroEmpleados == other.numeroEmpleados
				&& Objects.equals(tipoAgencia, other.tipoAgencia) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", nombre=" + nombre + ", logo=" + logo + ", color=" + color + ", usuario="
				+ usuario + ", contraseña=" + contraseña + ", numeroEmpleados=" + numeroEmpleados + ", limiteEmpleados="
				+ limiteEmpleados + ", tipoAgencia=" + tipoAgencia + "]";
	}

}
