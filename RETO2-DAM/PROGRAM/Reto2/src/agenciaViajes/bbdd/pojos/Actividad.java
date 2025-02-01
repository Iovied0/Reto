package agenciaViajes.bbdd.pojos;

import java.sql.Date;
import java.util.Objects;

public class Actividad {
	private String nombre, descripcion;
	private Date fecha;
	private double precio;
	private Viaje viaje;

	public Actividad() {
		
	}

	public Actividad(String nombre, String descripcion, Date fecha, double precio, Viaje viaje) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.precio = precio;
		this.viaje = viaje;
	}

	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + ", precio="
				+ precio + ", viaje=" + viaje + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, nombre, precio, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actividad other = (Actividad) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(viaje, other.viaje);
	}
}
