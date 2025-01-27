package agenciaViajes.bbdd.pojo;

import java.util.Objects;

public class TipoDormitorio {
    private String codigo;
    private String descripcion;

    public TipoDormitorio() {
	
	}

	public TipoDormitorio(String codigo, String descripcion) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo o vacio.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede ser nula o vacia.");
        }
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

   
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo o vacio.");
        }
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede ser nula o vacia.");
        }
        this.descripcion = descripcion;
    }

    
  
    @Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion);
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
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion);
	}


	@Override
    public String toString() {
        return "TipoDormitorio [codigo=" + codigo + ", descripcion=" + descripcion + "]";
    }
}
