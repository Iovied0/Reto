package agenciaViajes.bbdd.pojos;

import java.util.Objects;

public class NumeroEmpleados {
	private String codigo;
	private int minEmpleados, maxEmpleados;
	private Agencia agencia;

	public NumeroEmpleados() {

	}

	public NumeroEmpleados(String codigo, int minEmpleados, int maxEmpleados, Agencia agencia) {
		this.codigo = codigo;
		this.minEmpleados = minEmpleados;
		this.maxEmpleados = maxEmpleados;
		this.agencia = agencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getMinEmpleados() {
		return minEmpleados;
	}

	public void setMinEmpleados(int minEmpleados) {
		this.minEmpleados = minEmpleados;
	}

	public int getMaxEmpleados() {
		return maxEmpleados;
	}

	public void setMaxEmpleados(int maxEmpleados) {
		this.maxEmpleados = maxEmpleados;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, codigo, maxEmpleados, minEmpleados);
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
				&& maxEmpleados == other.maxEmpleados && minEmpleados == other.minEmpleados;
	}

	@Override
	public String toString() {
		return "NumeroEmpleados [codigo=" + codigo + ", minEmpleados=" + minEmpleados + ", maxEmpleados=" + maxEmpleados
				+ ", agencia=" + agencia + "]";
	}
	
}
