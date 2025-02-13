package agenciaViajes.bbdd.pojos;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Objects;

public class Viaje {

	private int id = 0;
	private int numeroDias = 0;
	private String nombreViaje = null;
	private String descViaje;
	private String servNoIncluidos;
	private Date inicioViaje;
	private Date finViaje;
	private Agencia agencia;
	private TipoViaje tipoViaje;
	private Pais pais;
	private ArrayList<Actividad> actividades = null;
	private ArrayList<Alojamiento> alojamientos = null;
	private ArrayList<Vuelo> vuelos = null;

	public Viaje() {

	}

	public Viaje(int id, String nombreViaje, String descViaje, Date inicioViaje, Date finViaje, int numeroDias,
			String servNoIncluidos, Agencia agencia, TipoViaje tipoViaje, Pais pais) {
		this.id = id;
		this.nombreViaje = nombreViaje;
		this.descViaje = descViaje;
		this.inicioViaje = inicioViaje;
		this.finViaje = finViaje;
		this.numeroDias = numeroDias;
		this.servNoIncluidos = servNoIncluidos;
		this.agencia = agencia;
		this.tipoViaje = tipoViaje;
		this.pais = pais;
	}

	public Viaje(int id, String nombreViaje, String descViaje, Date inicioViaje, Date finViaje, int numeroDias,
			String servNoIncluidos, Agencia agencia, TipoViaje tipoViaje, Pais pais, ArrayList<Actividad> actividades,
			ArrayList<Alojamiento> alojamientos, ArrayList<Vuelo> vuelos) {
		this.id = id;
		this.nombreViaje = nombreViaje;
		this.descViaje = descViaje;
		this.inicioViaje = inicioViaje;
		this.finViaje = finViaje;
		this.numeroDias = numeroDias;
		this.servNoIncluidos = servNoIncluidos;
		this.agencia = agencia;
		this.tipoViaje = tipoViaje;
		this.pais = pais;
		this.actividades = actividades;
		this.alojamientos = alojamientos;
		this.vuelos = vuelos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	public String getNombreViaje() {
		return nombreViaje;
	}

	public void setNombreViaje(String nombreViaje) {
		this.nombreViaje = nombreViaje;
	}

	public String getDescViaje() {
		return descViaje;
	}

	public void setDescViaje(String descViaje) {
		this.descViaje = descViaje;
	}

	public String getServNoIncluidos() {
		return servNoIncluidos;
	}

	public void setServNoIncluidos(String servNoIncluidos) {
		this.servNoIncluidos = servNoIncluidos;
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
		this.finViaje = finViaje;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public TipoViaje getTipoViaje() {
		return tipoViaje;
	}

	public void setTipoViaje(TipoViaje tipoViaje) {
		this.tipoViaje = tipoViaje;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public ArrayList<Alojamiento> getAlojamientos() {
		return alojamientos;
	}

	public void setAlojamientos(ArrayList<Alojamiento> alojamientos) {
		this.alojamientos = alojamientos;
	}

	public ArrayList<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actividades, agencia, alojamientos, descViaje, finViaje, id, inicioViaje, nombreViaje,
				numeroDias, pais, servNoIncluidos, tipoViaje, vuelos);
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
		return Objects.equals(actividades, other.actividades) && Objects.equals(agencia, other.agencia)
				&& Objects.equals(alojamientos, other.alojamientos) && Objects.equals(descViaje, other.descViaje)
				&& Objects.equals(finViaje, other.finViaje) && id == other.id
				&& Objects.equals(inicioViaje, other.inicioViaje) && Objects.equals(nombreViaje, other.nombreViaje)
				&& numeroDias == other.numeroDias && Objects.equals(pais, other.pais)
				&& Objects.equals(servNoIncluidos, other.servNoIncluidos) && Objects.equals(tipoViaje, other.tipoViaje)
				&& Objects.equals(vuelos, other.vuelos);
	}

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", numeroDias=" + numeroDias + ", nombreViaje=" + nombreViaje + ", descViaje="
				+ descViaje + ", servNoIncluidos=" + servNoIncluidos + ", inicioViaje=" + inicioViaje + ", finViaje="
				+ finViaje + ", agencia=" + agencia + ", tipoViaje=" + tipoViaje + ", pais=" + pais + ", actividades="
				+ actividades + ", alojamientos=" + alojamientos + ", vuelos=" + vuelos + "]";
	}

}
