package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class NumeroEmpleados {
	private String codigo, numeroEmpleados;
	private Agencia agencia;

	public NumeroEmpleados() {

	}

	public NumeroEmpleados(String codigo,String numeroEmpleados, Agencia agencia) {
		this.codigo = codigo;
		this.numeroEmpleados = numeroEmpleados;
		this.agencia = agencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(String numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, codigo, numeroEmpleados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumeroEmpleados other = (NumeroEmpleados) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(numeroEmpleados, other.numeroEmpleados);
	}

	@Override
	public String toString() {
		return "NumeroEmpleados [codigo=" + codigo + ", numeroEmpleados=" + numeroEmpleados + ", agencia=" + agencia
				+ "]";
	}

	
	
}
