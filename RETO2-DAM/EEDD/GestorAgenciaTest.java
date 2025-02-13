package agenciaViajes.gestores;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;


import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.NumeroEmpleados;
import agenciaViajes.bbdd.pojos.TiposAgencia;

public class GestorAgenciaTest {

    private GestorAgencia gestorAgencia;

    @Before
    public void setUp() {
        gestorAgencia = new GestorAgencia(); 
    }

    @After
    public void tearDown() {
        gestorAgencia = null; 
    }

    @Test
    public void testInsertAgencia() {
        
        TiposAgencia tipoAgencia = new TiposAgencia();
        tipoAgencia.setCodigo("A1");

        NumeroEmpleados numeroEmpleados = new NumeroEmpleados();
        numeroEmpleados.setCodigo("L1");

        // intintamos agregar una agencia
        gestorAgencia.insertAgencia("agencia test", "pass111", "#FFFFFF", numeroEmpleados, tipoAgencia, "logo.png");

        //  tenemos la lista de agencias
        ArrayList<Agencia> agencias = gestorAgencia.getAgencias();

        // verificamos que la agencia se inserto correctamente
        assertNotNull("La lista de agencias no deberia ser null", agencias);
        assertFalse("La lista de agencias no deberia estar vacia", agencias.isEmpty());
    }

    @Test
    public void testGetAgencias() {
        // obtener la lista de agencias y comprobar que no es null
        ArrayList<Agencia> agencias = gestorAgencia.getAgencias();
        assertNotNull("La lista de agencias no deberia ser null", agencias);
    }

    @Test
    public void testGetAgenciaPorId() {
        // intentamos obtener una agencia con id 1 (suponiendo que existe)
        Agencia agencia = gestorAgencia.getAgenciaPorId(1);
        assertNotNull("La agencia con id 1 no deberia ser null", agencia);
        assertEquals("El id de la agencia deberia ser 1", 1, agencia.getId());
    }

    @Test
    public void testGetAgenciaPorIdInexistente() {
        // buscamos una agencia con un id que no existe
        Agencia agencia = gestorAgencia.getAgenciaPorId(99);
        assertNull("Si el id no existe, la agencia deberia ser null", agencia);
    }
}
