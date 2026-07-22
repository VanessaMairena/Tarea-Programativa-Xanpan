package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaTest {

    @Test
    void deberiaCrearIncidenciaConIdYPrioridadNormal() {
        Incidencia inc = new Incidencia("Falla de red", "No hay conexión a internet en la oficina", "BAJO", "BAJA");
        
        assertNotNull(inc.getId());
        assertEquals("NORMAL", inc.getPrioridad());
        assertEquals("REGISTRADA", inc.getEstado());
    }

    @Test
    void deberiaCalcularPrioridadCritica() {
        Incidencia inc = new Incidencia("Servidor caido", "El servidor principal no responde a las peticiones", "ALTO", "ALTA");
        
        assertEquals("CRÍTICA", inc.getPrioridad());
    }

    @Test
    void deberiaFallarSiDescripcionEsMuyCortas() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Incidencia("Prueba", "Corto", "ALTO", "ALTA");
        });
        
        assertEquals("La descripción debe contener al menos diez caracteres.", exception.getMessage());
    }
}