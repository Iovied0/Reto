package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Pais {
	private String codigo, nombre;
	private Viaje viaje;
	private Ciudad ciudad;

	public Pais() {

	}

	public Pais(String codigo, String nombre, Viaje viaje, Ciudad ciudad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.viaje = viaje;
		this.ciudad = ciudad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Ciudad getCiudad(Ciudad ciudad) {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombre, viaje, ciudad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(viaje, other.viaje) && Objects.equals(ciudad, other.ciudad);
	}

	@Override
	public String toString() {
		return "Pais [codigo=" + codigo + ", nombre=" + nombre + ", viaje=" + viaje + ", ciudad=" + ciudad + "]";
	}

}