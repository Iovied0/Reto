package agenciaViajes.bbdd.pojo;

import java.util.Objects;

public class Aerolineas {
    private String codigo;
    private String nombre;
    private String pais;

    public Aerolineas() {
		
	}

	public Aerolineas(String codigo, String nombre, String pais) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (pais == null || pais.isEmpty()) {
            throw new IllegalArgumentException("El pais no puede estar vacio");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        if (pais == null || pais.isEmpty()) {
            throw new IllegalArgumentException("El pais no puede estar vacio");
        }
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
        return "Aerolineas{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
