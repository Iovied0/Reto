package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class TipoViaje {
	private String codigo, descripcion;
	private Viaje viaje;

	public TipoViaje() {

	}

	public TipoViaje(String codigo, String descripcion, Viaje viaje) {

		this.codigo = codigo;
		this.descripcion = descripcion;
		this.viaje = viaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoViaje other = (TipoViaje) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(viaje, other.viaje);
	}

	@Override
	public String toString() {
		return "TipoViaje [codigo=" + codigo + ", descripcion=" + descripcion + ", viaje=" + viaje + "]";
	}
	
}
