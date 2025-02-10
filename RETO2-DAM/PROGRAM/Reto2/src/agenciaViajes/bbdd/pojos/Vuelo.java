package agenciaViajes.bbdd.pojos;

import java.sql.Time;
import java.util.Objects;
import java.sql.Date;

public class Vuelo {
	private String codigo, tipoVuelo, codigoAsociado;
	private Date fecha;
	private Time horaSalida, duracion;
	private Aeropuerto aeropuertoOrigen, aeropuertoDestino;
	private Aerolineas aerolinea;
	private Viaje viaje;
	private double precio;

	public Vuelo() {

	}

	public Vuelo(String tipoVuelo, String codigo, Date fecha, Time horaSalida, Time duracion, Aerolineas aerolinea,
			Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, Viaje viaje, double precio, String codigoAsociado) {

		this.tipoVuelo = tipoVuelo;
		this.codigo = codigo;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.duracion = duracion;
		this.aerolinea = aerolinea;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.viaje = viaje;
		this.precio = precio;
		this.codigoAsociado = codigoAsociado;

	}

	public String getCodigoAsociado() {
		return codigoAsociado;
	}

	public void setCodigoAsociado(String codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Time getDuracion() {
		return duracion;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public Aerolineas getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(Aerolineas aerolinea) {
		this.aerolinea = aerolinea;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aerolinea, aeropuertoDestino, aeropuertoOrigen, codigo, codigoAsociado, duracion, fecha,
				horaSalida, precio, tipoVuelo, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vuelo other = (Vuelo) obj;
		return Objects.equals(aerolinea, other.aerolinea) && Objects.equals(aeropuertoDestino, other.aeropuertoDestino)
				&& Objects.equals(aeropuertoOrigen, other.aeropuertoOrigen) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(codigoAsociado, other.codigoAsociado) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(horaSalida, other.horaSalida)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipoVuelo, other.tipoVuelo) && Objects.equals(viaje, other.viaje);
	}

	@Override
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", tipoVuelo=" + tipoVuelo + ", codigoAsociado=" + codigoAsociado
				+ ", fecha=" + fecha + ", horaSalida=" + horaSalida + ", duracion=" + duracion + ", aeropuertoOrigen="
				+ aeropuertoOrigen + ", aeropuertoDestino=" + aeropuertoDestino + ", aerolinea=" + aerolinea
				+ ", viaje=" + viaje + ", precio=" + precio + "]";
	}

}