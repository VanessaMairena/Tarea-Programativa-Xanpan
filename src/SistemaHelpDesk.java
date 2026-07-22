import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SistemaHelpDesk {
    private List<Incidencia> incidencias;

    public SistemaHelpDesk() {
        this.incidencias = new ArrayList<>();
    }

    // Registrar nueva incidencia (HU-01)
    public Incidencia registrarIncidencia(String titulo, String descripcion, String impacto, String urgencia) {
        Incidencia nueva = new Incidencia(titulo, descripcion, impacto, urgencia);
        incidencias.add(nueva);
        return nueva;
    }

    // HU-04: Mostrar todas las incidencias
    public List<Incidencia> obtenerTodas() {
        return incidencias;
    }

    // HU-04: Buscar una incidencia por identificador
    public Optional<Incidencia> buscarPorId(String id) {
        return incidencias.stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}