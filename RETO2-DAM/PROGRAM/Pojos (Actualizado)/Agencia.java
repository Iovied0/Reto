package agenciaViajes.bbdd.pojo;

import java.util.Objects;

public class Agencia {
    private int id;               
    private String nombre;         
    private String logo;           
    private String color;         
    private int numeroEmpleados;  
    private NumeroEmpleados limiteEmpleados; 
    private TiposAgencia tipoAgencia;        

    
    public Agencia() {
        
    }

    public Agencia(int id, String nombre, String logo, String color, int numeroEmpleados, NumeroEmpleados limiteEmpleados, TiposAgencia tipoAgencia) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (logo == null || logo.isEmpty()) {
            throw new IllegalArgumentException("El logo no puede estar vacio.");
        }
        if (color == null || !color.matches("^#([A-Fa-f0-9]{6})$")) {
            throw new IllegalArgumentException("El color debe estar en formato hexadecimal (#RRGGBB).");
        }
        if (numeroEmpleados < 0) {
            throw new IllegalArgumentException("El numero de empleados debe ser mayor o igual a 0.");
        }
        if (limiteEmpleados == null) {
            throw new IllegalArgumentException("El limite de empleados no puede ser nulo.");
        }
        if (tipoAgencia == null) {
            throw new IllegalArgumentException("El tipo de agencia no puede ser nulo.");
        }

        this.id = id;
        this.nombre = nombre;
        this.logo = logo;
        this.color = color;
        this.numeroEmpleados = numeroEmpleados;
        this.limiteEmpleados = limiteEmpleados;
        this.tipoAgencia = tipoAgencia;
    }

    // Getters y Setters

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
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        if (logo == null || logo.isEmpty()) {
            throw new IllegalArgumentException("El logo no puede estar vacio.");
        }
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || !color.matches("^#([A-Fa-f0-9]{6})$")) {
            throw new IllegalArgumentException("El color debe estar en formato hexadecimal (#RRGGBB).");
        }
        this.color = color;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        if (numeroEmpleados < 0) {
            throw new IllegalArgumentException("El numero de empleados debe ser mayor o igual a 0.");
        }
        this.numeroEmpleados = numeroEmpleados;
    }

    public NumeroEmpleados getLimiteEmpleados() {
        return limiteEmpleados;
    }

    public void setLimiteEmpleados(NumeroEmpleados limiteEmpleados) {
        if (limiteEmpleados == null) {
            throw new IllegalArgumentException("El limite de empleados no puede ser nulo.");
        }
        this.limiteEmpleados = limiteEmpleados;
    }

    public TiposAgencia getTipoAgencia() {
        return tipoAgencia;
    }

    public void setTipoAgencia(TiposAgencia tipoAgencia) {
        if (tipoAgencia == null) {
            throw new IllegalArgumentException("El tipo de agencia no puede ser nulo.");
        }
        this.tipoAgencia = tipoAgencia;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", logo='" + logo + '\'' +
                ", color='" + color + '\'' +
                ", numeroEmpleados=" + numeroEmpleados +
                ", limiteEmpleados=" + limiteEmpleados +
                ", tipoAgencia=" + tipoAgencia +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, logo, color, numeroEmpleados, limiteEmpleados, tipoAgencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Agencia other = (Agencia) obj;
        return id == other.id &&
                numeroEmpleados == other.numeroEmpleados &&
                Objects.equals(nombre, other.nombre) &&
                Objects.equals(logo, other.logo) &&
                Objects.equals(color, other.color) &&
                Objects.equals(limiteEmpleados, other.limiteEmpleados) &&
                Objects.equals(tipoAgencia, other.tipoAgencia);
    }
}
