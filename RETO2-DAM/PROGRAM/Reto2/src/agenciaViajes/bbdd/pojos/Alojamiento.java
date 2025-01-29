package agenciaViajes.bbdd.pojos;

import java.sql.Date;
import java.util.Objects;

public class Alojamiento {
	private String nombreHotel;
	private Date fechaEntrada, fechaSalida;
	private Ciudad ciudad;
	private TipoDormitorio tipoDormitorio;
	private Viaje viaje;
	private int id;
	private double precio;

	public Alojamiento() {

	}

	public Alojamiento(int id, String nombreHotel, Date fechaEntrada, Date fechaSalida, double precio, Viaje viaje,
			Ciudad ciudad, TipoDormitorio tipoDormitorio) {
		this.id = id;
		this.nombreHotel = nombreHotel;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
		this.viaje = viaje;
		this.ciudad = ciudad;
		this.tipoDormitorio = tipoDormitorio;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public TipoDormitorio getTipoDormitorio() {
		return tipoDormitorio;
	}

	public void setTipoDormitorio(TipoDormitorio tipoDormitorio) {
		this.tipoDormitorio = tipoDormitorio;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, fechaEntrada, fechaSalida, id, nombreHotel, precio, tipoDormitorio, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alojamiento other = (Alojamiento) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(fechaEntrada, other.fechaEntrada)
				&& Objects.equals(fechaSalida, other.fechaSalida) && id == other.id
				&& Objects.equals(nombreHotel, other.nombreHotel)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipoDormitorio, other.tipoDormitorio) && Objects.equals(viaje, other.viaje);
	}

	@Override
	public String toString() {
		return "Alojamiento [nombreHotel=" + nombreHotel + ", fechaEntrada=" + fechaEntrada + ", fechaSalida="
				+ fechaSalida + ", ciudad=" + ciudad + ", tipoDormitorio=" + tipoDormitorio + ", viaje=" + viaje
				+ ", id=" + id + ", precio=" + precio + "]";
	}

}
