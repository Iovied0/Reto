package agenciaViajes.bbdd.pojos;

import java.sql.Time;
import java.util.Objects;
import java.sql.Date;

public class Vuelo {
	private String codigoVueloIda, tipoVuelo, codigoVueloVuelta;
	private Date fechaIda, fechaVuelta;
	private Time horaSalidaIda, horaSalidaVuelta, duracionIda, duracionVuelta;
	private Aeropuerto aeropuertoOrigen, aeropuertoDestino;
	private Aerolineas aerolineaIda, aerolineaVuelta;
	private Viaje viaje;
	private double precio;

	public Vuelo() {

	}

	public Vuelo(String tipoVuelo, String codigoVueloIda, Date fechaIda, Time horaSalidaIda, Time duracionIda,
			Aerolineas aerolineaIda, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino,
			String codigoVueloVuelta, Date fechaVuelta, Time horaSalidaVuelta, Time duracionVuelta,
			Aerolineas aerolineaVuelta, Viaje viaje, double precio) {

		this.tipoVuelo = tipoVuelo;
		this.codigoVueloIda = codigoVueloIda;
		this.fechaIda = fechaIda;
		this.horaSalidaIda = horaSalidaIda;
		this.duracionIda = duracionIda;
		this.aerolineaIda = aerolineaIda;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.codigoVueloVuelta = codigoVueloVuelta;
		this.fechaVuelta = fechaVuelta;
		this.horaSalidaVuelta = horaSalidaVuelta;
		this.duracionVuelta = duracionVuelta;
		this.aerolineaVuelta = aerolineaVuelta;
		this.viaje = viaje;
		this.precio = precio;

	}

	public String getCodigoVueloIda() {
		return codigoVueloIda;
	}

	public void setCodigoVueloIda(String codigoVueloIda) {
		this.codigoVueloIda = codigoVueloIda;
	}

	public String getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}

	public String getCodigoVueloVuelta() {
		return codigoVueloVuelta;
	}

	public void setCodigoVueloVuelta(String codigoVueloVuelta) {
		this.codigoVueloVuelta = codigoVueloVuelta;
	}

	public Date getFechaIda() {
		return fechaIda;
	}

	public void setFechaIda(Date fechaIda) {
		this.fechaIda = fechaIda;
	}

	public Date getFechaVuelta() {
		return fechaVuelta;
	}

	public void setFechaVuelta(Date fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}

	public Time getHoraSalidaIda() {
		return horaSalidaIda;
	}

	public void setHoraSalidaIda(Time horaSalidaIda) {
		this.horaSalidaIda = horaSalidaIda;
	}

	public Time getHoraSalidaVuelta() {
		return horaSalidaVuelta;
	}

	public void setHoraSalidaVuelta(Time horaSalidaVuelta) {
		this.horaSalidaVuelta = horaSalidaVuelta;
	}

	public Time getDuracionIda() {
		return duracionIda;
	}

	public void setDuracionIda(Time duracionIda) {
		this.duracionIda = duracionIda;
	}

	public Time getDuracionVuelta() {
		return duracionVuelta;
	}

	public void setDuracionVuelta(Time duracionVuelta) {
		this.duracionVuelta = duracionVuelta;
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

	public Aerolineas getAerolineaIda() {
		return aerolineaIda;
	}

	public void setAerolineaIda(Aerolineas aerolineaIda) {
		this.aerolineaIda = aerolineaIda;
	}

	public Aerolineas getAerolineaVuelta() {
		return aerolineaVuelta;
	}

	public void setAerolineaVuelta(Aerolineas aerolineaVuelta) {
		this.aerolineaVuelta = aerolineaVuelta;
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
		return Objects.hash(aerolineaIda, aerolineaVuelta, aeropuertoDestino, aeropuertoOrigen, codigoVueloIda,
				codigoVueloVuelta, duracionIda, duracionVuelta, fechaIda, fechaVuelta, horaSalidaIda, horaSalidaVuelta,
				precio, tipoVuelo, viaje);
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
		return Objects.equals(aerolineaIda, other.aerolineaIda)
				&& Objects.equals(aerolineaVuelta, other.aerolineaVuelta)
				&& Objects.equals(aeropuertoDestino, other.aeropuertoDestino)
				&& Objects.equals(aeropuertoOrigen, other.aeropuertoOrigen)
				&& Objects.equals(codigoVueloIda, other.codigoVueloIda)
				&& Objects.equals(codigoVueloVuelta, other.codigoVueloVuelta)
				&& Objects.equals(duracionIda, other.duracionIda)
				&& Objects.equals(duracionVuelta, other.duracionVuelta) && Objects.equals(fechaIda, other.fechaIda)
				&& Objects.equals(fechaVuelta, other.fechaVuelta) && Objects.equals(horaSalidaIda, other.horaSalidaIda)
				&& Objects.equals(horaSalidaVuelta, other.horaSalidaVuelta)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipoVuelo, other.tipoVuelo) && Objects.equals(viaje, other.viaje);
	}

	@Override
	public String toString() {
		return "Vuelo [codigoVueloIda=" + codigoVueloIda + ", tipoVuelo=" + tipoVuelo + ", codigoVueloVuelta="
				+ codigoVueloVuelta + ", fechaIda=" + fechaIda + ", fechaVuelta=" + fechaVuelta + ", horaSalidaIda="
				+ horaSalidaIda + ", horaSalidaVuelta=" + horaSalidaVuelta + ", duracionIda=" + duracionIda
				+ ", duracionVuelta=" + duracionVuelta + ", aeropuertoOrigen=" + aeropuertoOrigen
				+ ", aeropuertoDestino=" + aeropuertoDestino + ", aerolineaIda=" + aerolineaIda + ", aerolineaVuelta="
				+ aerolineaVuelta + ", viaje=" + viaje + ", precio=" + precio + "]";
	}

}