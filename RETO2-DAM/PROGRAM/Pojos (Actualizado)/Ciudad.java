package agenciaViajes.bbdd.pojo;

import java.util.Objects;

public class Ciudad {
    private int id;
    private String nombre;
    private Pais pais; 

    public Ciudad() {
		
	}

	public Ciudad(int id, String nombre, Pais pais) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede ser nulo o vacio.");
        }
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede ser nulo o vacio.");
        }
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
		return Objects.hash(id, nombre, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais);
	}

	@Override
    public String toString() {
        return "Ciudad [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
    }
}
