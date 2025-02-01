package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class Aeropuerto {
    private String codigo;
    private Ciudad ciudad;

    public Aeropuerto() {
		
	}

	public Aeropuerto(String codigo, Ciudad ciudad) {
      this.codigo = codigo;
      this.ciudad = ciudad;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Aeropuerto [codigo=" + codigo + ", ciudad=" + ciudad + "]";
	}

  
}
