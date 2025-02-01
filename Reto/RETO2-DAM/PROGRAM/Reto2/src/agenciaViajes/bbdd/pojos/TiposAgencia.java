package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class TiposAgencia {
    private String codigo, descripcion;
    private Agencia agencia;

    public TiposAgencia() {
    	
    }

	public TiposAgencia(String codigo, String descripcion, Agencia agencia) {
       this.codigo = codigo;
       this.descripcion = descripcion;
       this.agencia = agencia;
       
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

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, codigo, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TiposAgencia other = (TiposAgencia) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(descripcion, other.descripcion);
	}

	@Override
	public String toString() {
		return "TiposAgencia [codigo=" + codigo + ", descripcion=" + descripcion + ", agencia=" + agencia + "]";
	}
	

}
