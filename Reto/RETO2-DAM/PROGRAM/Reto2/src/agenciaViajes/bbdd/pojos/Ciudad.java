package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Ciudad {
	private int id;
	private String nombre;
	private Pais pais;
	private Aeropuerto aeropuerto;

	public Ciudad() {

	}

	public Ciudad(int id, String nombre, Pais pais, Aeropuerto aeropuerto) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.aeropuerto = aeropuerto;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Aeropuerto getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, pais, aeropuerto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais)
				&& Objects.equals(aeropuerto, other.aeropuerto);
	}

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", aeropuerto=" + aeropuerto + "]";
	}

}
