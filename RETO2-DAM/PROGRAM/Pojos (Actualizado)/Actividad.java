package agenciaViajes.bbdd.pojo;

import java.util.Objects;

    public class Actividad extends Evento {
        private String descripcion;

        
        public Actividad() {
            super(); 
        }
  
        public Actividad(
                int id,
                String nombre,
                String tipo,
                String fecha,
                double precio,
                String descripcion,
                Viaje idViaje
        ) {
            super(id, nombre, tipo, fecha, precio, idViaje); // Llama al constructor de Evento
            if (descripcion == null || descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripcion no puede estar vacia.");
            }
            this.descripcion = descripcion;
        }

        // Getter y Setter 
        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            if (descripcion == null || descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripcion no puede estar vacia.");
            }
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return "Actividad{" +
                    "descripcion='" + descripcion + '\'' +
                    ", " + super.toString() +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), descripcion);
        }

        @Override
        public boolean equals(Object obj) {
            if (!super.equals(obj)) return false;
            Actividad other = (Actividad) obj;
            return Objects.equals(descripcion, other.descripcion);
        }
    }
