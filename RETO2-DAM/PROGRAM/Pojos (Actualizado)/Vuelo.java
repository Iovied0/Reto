package agenciaViajes.bbdd.pojo;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Vuelo extends Evento {
    private String codigoVuelo;
    private String fechaIda;
    private Date fechaVuelta;
    private double precioTotal; // Representa siempre el precio total (ida o ida/vuelta)
    private Time horaSalida;
    private Time duracion;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private Aerolineas aerolinea;

    public Vuelo() {
		super();
	}

	public Vuelo(
            int id,
            String nombre,
            String tipo,
            String fechaIda,
            Date fechaVuelta,
            String codigoVuelo,
            double precioTotal, // precioTotal como unico valor para el costo
            Time horaSalida,
            Time duracion,
            Aeropuerto aeropuertoOrigen,
            Aeropuerto aeropuertoDestino,
            Aerolineas aerolinea,
            Viaje idViaje
    ) {
        super(id, nombre, tipo, fechaIda, precioTotal, idViaje); // precioTotal pasa como precio en el constructor de Evento
        if (codigoVuelo == null || codigoVuelo.isEmpty()) {
            throw new IllegalArgumentException("El codigo de vuelo no puede ser nulo o vacio.");
        }
        if (precioTotal < 0) {
            throw new IllegalArgumentException("El precio total no puede ser negativo.");
        }

        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.codigoVuelo = codigoVuelo;
        this.precioTotal = precioTotal;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.aerolinea = aerolinea;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        if (codigoVuelo == null || codigoVuelo.isEmpty()) {
            throw new IllegalArgumentException("El codigo de vuelo no puede ser nulo o vacio.");
        }
        this.codigoVuelo = codigoVuelo;
    }

    public String getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(String fechaIda) {
        if (fechaIda == null) {
            throw new IllegalArgumentException("La fecha de ida no puede ser nula.");
        }
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        if (precioTotal < 0) {
            throw new IllegalArgumentException("El precio total no puede ser negativo.");
        }
        this.precioTotal = precioTotal;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        if (horaSalida == null) {
            throw new IllegalArgumentException("La hora de salida no puede ser nula.");
        }
        this.horaSalida = horaSalida;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        if (duracion == null) {
            throw new IllegalArgumentException("La duracion no puede ser nula.");
        }
        this.duracion = duracion;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        if (aeropuertoOrigen == null) {
            throw new IllegalArgumentException("El aeropuerto de origen no puede ser nulo.");
        }
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
        if (aerolinea == null) {
            throw new IllegalArgumentException("La aerolinea no puede ser nula.");
        }
        this.aerolinea = aerolinea;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "codigoVuelo='" + codigoVuelo + '\'' +
                ", fechaIda=" + fechaIda +
                ", fechaVuelta=" + fechaVuelta +
                ", precioTotal=" + precioTotal +
                ", horaSalida=" + horaSalida +
                ", duracion=" + duracion +
                ", aeropuertoOrigen=" + aeropuertoOrigen +
                ", aeropuertoDestino=" + aeropuertoDestino +
                ", aerolinea=" + aerolinea +
                ", " + super.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigoVuelo, fechaIda, fechaVuelta, precioTotal, horaSalida, duracion, aeropuertoOrigen, aeropuertoDestino, aerolinea);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Vuelo other = (Vuelo) obj;
        return Objects.equals(codigoVuelo, other.codigoVuelo)
                && Objects.equals(fechaIda, other.fechaIda)
                && Objects.equals(fechaVuelta, other.fechaVuelta)
                && Double.compare(precioTotal, other.precioTotal) == 0
                && Objects.equals(horaSalida, other.horaSalida)
                && Objects.equals(duracion, other.duracion)
                && Objects.equals(aeropuertoOrigen, other.aeropuertoOrigen)
                && Objects.equals(aeropuertoDestino, other.aeropuertoDestino)
                && Objects.equals(aerolinea, other.aerolinea);
    }
}

