public class Main {
    public static void main(String[] args) {
        SistemaHelpDesk sistema = new SistemaHelpDesk();

        // Probando HU-01 y HU-02 (Registro y cálculo automático de prioridad)
        Incidencia inc1 = sistema.registrarIncidencia(
            "Falla de red", 
            "No hay conexión a internet en el departamento de ventas", 
            "Alto", 
            "Alta"
        );
        
        System.out.println("Incidencia registrada con éxito:");
        System.out.println("ID: " + inc1.getId());
        System.out.println("Título: " + inc1.getTitulo());
        System.out.println("Prioridad calculada: " + inc1.getPrioridad()); // Debería ser CRÍTICA
        System.out.println("Estado: " + inc1.getEstado());

        // Probando HU-04 (Buscar por ID)
        sistema.buscarPorId(inc1.getId()).ifPresentOrElse(
            i -> System.out.println("\n[✔] Encontrado por ID: " + i.getTitulo()),
            () -> System.out.println("\n[✖] No encontrado")
        );
    }
}