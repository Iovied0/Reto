package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Aerolineas {
	private String codigo;
	private String nombre;
	private Pais pais;

	public Aerolineas() {
		
	}

	public Aerolineas(String codigo, String nombre, Pais pais) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.pais = pais;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombre, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aerolineas other = (Aerolineas) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "Aerolineas [codigo=" + codigo + ", nombre=" + nombre + ", pais=" + pais + "]";
	}

}
