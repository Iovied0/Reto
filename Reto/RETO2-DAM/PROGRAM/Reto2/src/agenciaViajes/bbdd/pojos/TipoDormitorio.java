package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class TipoDormitorio {
    private String codigo, descripcion;
    private Alojamiento alojamiento;

    public TipoDormitorio() {
	
	}

	public TipoDormitorio(String codigo, String descripcion, Alojamiento alojamiento) {
      
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.alojamiento = alojamiento;
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

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alojamiento, codigo, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoDormitorio other = (TipoDormitorio) obj;
		return Objects.equals(alojamiento, other.alojamiento) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(descripcion, other.descripcion);
	}

	@Override
	public String toString() {
		return "TipoDormitorio [codigo=" + codigo + ", descripcion=" + descripcion + ", alojamiento=" + alojamiento
				+ "]";
	}

   
    
}
