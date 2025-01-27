package agenciaViajes.bbdd.pojo;

import java.util.Objects;

public class NumeroEmpleados {
    private String codigo;
    private int minEmpleados;
    private int maxEmpleados;
  
    public NumeroEmpleados() {
	
	}

	public NumeroEmpleados(String codigo, int minEmpleados, int maxEmpleados) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede ser nulo o vacio.");
        }
        if (minEmpleados < 0 || maxEmpleados < 0 || minEmpleados > maxEmpleados) {
            throw new IllegalArgumentException("Los valores de empleados son invalidos.");
        }
        this.codigo = codigo;
        this.minEmpleados = minEmpleados;
        this.maxEmpleados = maxEmpleados;
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

    public int getMinEmpleados() {
        return minEmpleados;
    }

    public void setMinEmpleados(int minEmpleados) {
        if (minEmpleados < 0) {
            throw new IllegalArgumentException("El numero minimo de empleados no puede ser negativo.");
        }
        this.minEmpleados = minEmpleados;
    }

    public int getMaxEmpleados() {
        return maxEmpleados;
    }

    public void setMaxEmpleados(int maxEmpleados) {
        if (maxEmpleados < 0 || maxEmpleados < this.minEmpleados) {
            throw new IllegalArgumentException("El numero maximo de empleados no puede ser menor que el minimo.");
        }
        this.maxEmpleados = maxEmpleados;
    }

    
    
    @Override
	public int hashCode() {
		return Objects.hash(codigo, maxEmpleados, minEmpleados);
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
		return Objects.equals(codigo, other.codigo) && maxEmpleados == other.maxEmpleados
				&& minEmpleados == other.minEmpleados;
	}


	@Override
    public String toString() {
        return "NumeroEmpleados [codigo=" + codigo + ", minEmpleados=" + minEmpleados + ", maxEmpleados=" + maxEmpleados + "]";
    }
}
