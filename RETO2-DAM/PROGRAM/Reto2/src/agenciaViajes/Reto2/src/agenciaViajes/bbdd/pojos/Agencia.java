package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Agencia {
	private int id;
	private String nombre, logo, color, contraseña;
	private NumeroEmpleados numeroEmpleados;
	private TiposAgencia tipoAgencia;

	public Agencia() {

	}

	public Agencia(int id, String nombre, String logo, String color, String contraseña, NumeroEmpleados numeroEmpleados,
			TiposAgencia tipoAgencia) {

		this.id = id;
		this.nombre = nombre;
		this.logo = logo;
		this.color = color;
		this.contraseña = contraseña;
		this.numeroEmpleados = numeroEmpleados;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public NumeroEmpleados getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(NumeroEmpleados limiteEmpleados) {
		this.numeroEmpleados = limiteEmpleados;
	}

	public TiposAgencia getTipoAgencia() {
		return tipoAgencia;
	}

	public void setTipoAgencia(TiposAgencia tipoAgencia) {
		this.tipoAgencia = tipoAgencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, contraseña, id, numeroEmpleados, logo, nombre, tipoAgencia);
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
				&& Objects.equals(numeroEmpleados, other.numeroEmpleados) && Objects.equals(logo, other.logo)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(tipoAgencia, other.tipoAgencia);
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", nombre=" + nombre + ", logo=" + logo + ", color=" + color + ", contraseña="
				+ contraseña + ", numeroEmpleados=" + numeroEmpleados + ", tipoAgencia=" + tipoAgencia + "]";
	}

}
