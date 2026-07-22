import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncidenciaTest {

    @Test
    void testCreacionIncidenciaValida() {
        Incidencia inc = new Incidencia("Falla de red", "No hay conexión al switch principal del piso 2", "ALTO", "ALTA");
        assertNotNull(inc.getId());
        assertEquals("REGISTRADA", inc.getEstado());
        assertEquals("CRÍTICA", inc.getPrioridad());
    }

    @Test
    void testCalculoPrioridadCritica() {
        assertEquals("CRÍTICA", Incidencia.calcularPrioridad("ALTO", "ALTA"));
    }

    @Test
    void testCalculoPrioridadAlta() {
        assertEquals("ALTA", Incidencia.calcularPrioridad("ALTO", "BAJA"));
        assertEquals("ALTA", Incidencia.calcularPrioridad("BAJO", "ALTA"));
    }

    @Test
    void testCalculoPrioridadNormal() {
        assertEquals("NORMAL", Incidencia.calcularPrioridad("MEDIO", "MEDIA"));
    }

    @Test
    void testFlujoEstadosValidoYRestriccionCierre() {
        Incidencia inc = new Incidencia("Error impresora", "La impresora del departamento no responde", "MEDIO", "MEDIA");
        
        // Transiciones válidas iniciales
        assertTrue(inc.cambiarEstado("LISTA", ""));
        assertTrue(inc.cambiarEstado("EN_DESARROLLO", ""));
        assertTrue(inc.cambiarEstado("EN_VALIDACION", ""));
        
        // Intentar finalizar SIN solución (Debe fallar y mantenerse en EN_VALIDACION)
        assertFalse(inc.cambiarEstado("FINALIZADA", ""));
        assertEquals("EN_VALIDACION", inc.getEstado());

        // Finalizar CON solución válida (Debe pasar exitosamente)
        assertTrue(inc.cambiarEstado("FINALIZADA", "Se reinició el servicio de cola de impresión."));
        assertEquals("FINALIZADA", inc.getEstado());
    }
}