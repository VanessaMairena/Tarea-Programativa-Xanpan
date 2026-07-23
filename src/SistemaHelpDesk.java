import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaHelpDesk {
    private List<Incidencia> listaIncidencias;

    public SistemaHelpDesk() {
        this.listaIncidencias = new ArrayList<>();
    }

    public void agregarIncidencia(Incidencia incidencia) {
        if (incidencia != null) {
            listaIncidencias.add(incidencia);
        }
    }

    public List<Incidencia> getListaIncidencias() {
        return listaIncidencias;
    }

    // HU-04: Filtrar por Estado
    public List<Incidencia> filtrarPorEstado(String estado) {
        if (estado == null) return new ArrayList<>();
        return listaIncidencias.stream()
                .filter(i -> i.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    // HU-04: Filtrar por Prioridad
    public List<Incidencia> filtrarPorPrioridad(String prioridad) {
        if (prioridad == null) return new ArrayList<>();
        return listaIncidencias.stream()
                .filter(i -> i.getPrioridad().equalsIgnoreCase(prioridad))
                .collect(Collectors.toList());
    }

    // HU-05: Calcular Throughput (Cantidad de incidencias finalizadas)
    public long calcularThroughput() {
        return listaIncidencias.stream()
                .filter(i -> "FINALIZADA".equalsIgnoreCase(i.getEstado()))
                .count();
    }

    // HU-05: Calcular Lead Time Promedio (Días promedio desde creación hasta cierre)
    public double calcularLeadTimePromedio() {
        List<Incidencia> finalizadas = listaIncidencias.stream()
                .filter(i -> "FINALIZADA".equalsIgnoreCase(i.getEstado()) && i.getFechaCierre() != null)
                .collect(Collectors.toList());

        if (finalizadas.isEmpty()) return 0.0;

        long totalDias = 0;
        for (Incidencia i : finalizadas) {
            totalDias += ChronoUnit.DAYS.between(i.getFechaCreacion(), i.getFechaCierre());
        }

        return (double) totalDias / finalizadas.size();
    }
}