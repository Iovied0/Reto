package agenciaViajes.bbdd.pojo;

import java.util.Date;
import java.util.Objects;

public class Viaje {
    private int id;
    private String nombreViaje;
    private String descViaje;
    private Date inicioViaje;
    private Date finViaje;
    private int numeroDias;
    private String servNoIncluidos;
    private Agencia idAgencia; 
    private TipoViaje tipoViaje; 
    private Pais paisDestino; 
   
    public Viaje() {
	
	}

	public Viaje(int id, String nombreViaje, String descViaje, Date inicioViaje, Date finViaje, int numeroDias, String servNoIncluidos, Agencia idAgencia, TipoViaje tipoViaje, Pais paisDestino) {
        if (nombreViaje == null || nombreViaje.isEmpty()) {
            throw new IllegalArgumentException("El nombre del viaje no puede ser nulo o vacio.");
        }
        if (inicioViaje == null || finViaje == null || !finViaje.after(inicioViaje)) {
            throw new IllegalArgumentException("Las fechas del viaje son invalidas.");
        }
        this.id = id;
        this.nombreViaje = nombreViaje;
        this.descViaje = descViaje;
        this.inicioViaje = inicioViaje;
        this.finViaje = finViaje;
        this.numeroDias = numeroDias;
        this.servNoIncluidos = servNoIncluidos;
        this.idAgencia = idAgencia;
        this.tipoViaje = tipoViaje;
        this.paisDestino = paisDestino;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreViaje() {
        return nombreViaje;
    }

    public void setNombreViaje(String nombreViaje) {
        if (nombreViaje == null || nombreViaje.isEmpty()) {
            throw new IllegalArgumentException("El nombre del viaje no puede ser nulo o vacio.");
        }
        this.nombreViaje = nombreViaje;
    }

    public String getDescViaje() {
        return descViaje;
    }

    public void setDescViaje(String descViaje) {
        this.descViaje = descViaje;
    }

    public Date getInicioViaje() {
        return inicioViaje;
    }

    public void setInicioViaje(Date inicioViaje) {
        this.inicioViaje = inicioViaje;
    }

    public Date getFinViaje() {
        return finViaje;
    }

    public void setFinViaje(Date finViaje) {
        if (finViaje == null || !finViaje.after(this.inicioViaje)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }
        this.finViaje = finViaje;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getServNoIncluidos() {
        return servNoIncluidos;
    }

    public void setServNoIncluidos(String servNoIncluidos) {
        this.servNoIncluidos = servNoIncluidos;
    }

    public Agencia getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Agencia idAgencia) {
        this.idAgencia = idAgencia;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public Pais getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(Pais paisDestino) {
        this.paisDestino = paisDestino;
    }

    
 
    
    @Override
	public int hashCode() {
		return Objects.hash(descViaje, finViaje, id, idAgencia, inicioViaje, nombreViaje, numeroDias, paisDestino,
				servNoIncluidos, tipoViaje);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(descViaje, other.descViaje) && Objects.equals(finViaje, other.finViaje) && id == other.id
				&& Objects.equals(idAgencia, other.idAgencia) && Objects.equals(inicioViaje, other.inicioViaje)
				&& Objects.equals(nombreViaje, other.nombreViaje) && numeroDias == other.numeroDias
				&& Objects.equals(paisDestino, other.paisDestino)
				&& Objects.equals(servNoIncluidos, other.servNoIncluidos) && Objects.equals(tipoViaje, other.tipoViaje);
	}


	@Override
    public String toString() {
        return "Viaje [id=" + id + ", nombreViaje=" + nombreViaje + ", descViaje=" + descViaje + ", inicioViaje=" + inicioViaje + ", finViaje=" + finViaje + ", numeroDias=" + numeroDias + ", servNoIncluidos=" + servNoIncluidos + ", idAgencia=" + idAgencia + ", tipoViaje=" + tipoViaje + ", paisDestino=" + paisDestino + "]";
    }
}
