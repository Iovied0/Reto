package agenciaViajes.bbdd.pojo;

import java.util.Date;
import java.util.Objects;

public class Alojamiento extends Evento {
    private String nombreHotel;
    private String fechaEntrada;
    private Date fechaSalida;
    private Ciudad idCiudad;
    private TipoDormitorio tipoDormitorio;
  
    public Alojamiento() {
		super();	
	}

	public Alojamiento(
            int id,
            String nombre,
            String tipo,
            String fechaEntrada,
            Date fechaSalida,
            double precio, // Se pasa como precio en el constructor de Evento
            String nombreHotel,
            Ciudad idCiudad,
            TipoDormitorio tipoDormitorio,
            Viaje idViaje
    ) {
        super(id, nombre, tipo, fechaEntrada, precio, idViaje); // Llama al constructor de Evento
        if (nombreHotel == null || nombreHotel.isEmpty()) {
            throw new IllegalArgumentException("El nombre del hotel no puede ser nulo o vacio.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombreHotel = nombreHotel;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idCiudad = idCiudad;
        this.tipoDormitorio = tipoDormitorio;
    }

    // Getters y Setters

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        if (nombreHotel == null || nombreHotel.isEmpty()) {
            throw new IllegalArgumentException("El nombre del hotel no puede ser nulo o vacio.");
        }
        this.nombreHotel = nombreHotel;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        if (fechaEntrada == null) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser nula.");
        }
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        
        this.fechaSalida = fechaSalida;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public TipoDormitorio getTipoDormitorio() {
        return tipoDormitorio;
    }

    public void setTipoDormitorio(TipoDormitorio tipoDormitorio) {
        this.tipoDormitorio = tipoDormitorio;
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "nombreHotel='" + nombreHotel + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", idCiudad=" + idCiudad +
                ", tipoDormitorio=" + tipoDormitorio +
                ", " + super.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombreHotel, fechaEntrada, fechaSalida, idCiudad, tipoDormitorio);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Alojamiento other = (Alojamiento) obj;
        return Objects.equals(nombreHotel, other.nombreHotel)
                && Objects.equals(fechaEntrada, other.fechaEntrada)
                && Objects.equals(fechaSalida, other.fechaSalida)
                && Objects.equals(idCiudad, other.idCiudad)
                && Objects.equals(tipoDormitorio, other.tipoDormitorio);
    }
}
