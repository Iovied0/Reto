package agenciaViajes.bbdd.pojo;

import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class Evento {
    private int id;
    private String nombre;
    private String tipo; 
    private Date fecha;
    private double precio;
    private Viaje idViaje; 
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-mm-dd");
    
    public Evento() {
    	
	}

	public Evento(int id, String nombre, String tipo, String fecha, double precio, Viaje idViaje) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio.");
        }
        if (tipo == null || (!tipo.equals("vuelo") && !tipo.equals("alojamiento") && !tipo.equals("Actividad"))) {
            throw new IllegalArgumentException("El tipo debe ser 'vuelo', 'alojamiento' o 'Actividad'.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (idViaje == null) {
            throw new IllegalArgumentException("El idViaje no puede ser nulo.");
        }
       
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        try {
			this.fecha = new Date(formatoFecha.parse(fecha).getTime());
		} catch (Exception e) {
			System.err.println("Formato de fecha incorrecto, introduzca: 'yyyy-mm-dd'");
		}
        this.precio = precio;
        this.idViaje = idViaje;
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
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio.");
        }
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || (!tipo.equals("vuelo") && !tipo.equals("alojamiento") && !tipo.equals("Actividad"))) {
            throw new IllegalArgumentException("El tipo debe ser 'vuelo', 'alojamiento' o 'Actividad'.");
        }
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        try {
			this.fecha = new Date(formatoFecha.parse(fecha).getTime());
		} catch (Exception e) {
			System.err.println("Formato de fecha incorrecto, introduzca: 'yyyy-mm-dd'");
		}
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public Viaje getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Viaje idViaje) {
        if (idViaje == null) {
            throw new IllegalArgumentException("El idViaje no puede ser nulo.");
        }
        this.idViaje = idViaje;
    }
 
    
    @Override
	public int hashCode() {
		return Objects.hash(fecha, id, idViaje, nombre, precio, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(fecha, other.fecha) && id == other.id && Objects.equals(idViaje, other.idViaje)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo, other.tipo);
	}


	@Override
    public String toString() {
        return "Evento [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", fecha=" + fecha + ", precio=" + precio + ", idViaje=" + idViaje + "]";
    }
}
