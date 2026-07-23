import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaHelpDesk sistema = new SistemaHelpDesk();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- HELPDESK FLOW - MENU PRINCIPAL ---");
            System.out.println("1. Registrar nueva incidencia");
            System.out.println("2. Cambiar estado de incidencia");
            System.out.println("3. Filtrar incidencias por estado");
            System.out.println("4. Ver métricas (Lead Time y Throughput)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Título de la incidencia: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Descripción detallada: ");
                        String desc = scanner.nextLine();
                        System.out.print("Impacto (ALTO, MEDIO, BAJO): ");
                        String impacto = scanner.nextLine();
                        System.out.print("Urgencia (ALTA, MEDIA, BAJA): ");
                        String urgencia = scanner.nextLine();

                        try {
                            Incidencia inc = new Incidencia(titulo, desc, impacto, urgencia);
                            sistema.agregarIncidencia(inc);
                            System.out.println("¡Incidencia creada con éxito! ID: " + inc.getId() + " | Prioridad calculada: " + inc.getPrioridad());
                        } catch (Exception e) {
                            System.out.println("Error al crear: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese el ID de la incidencia: ");
                        String searchId = scanner.nextLine();
                        Incidencia encontrada = sistema.getListaIncidencias().stream()
                                .filter(i -> i.getId().equals(searchId))
                                .findFirst()
                                .orElse(null);

                        if (encontrada != null) {
                            System.out.println("Estado actual: " + encontrada.getEstado());
                            System.out.print("Nuevo estado (LISTA, EN_DESARROLLO, EN_VALIDACION, FINALIZADA): ");
                            String nuevoEst = scanner.nextLine();
                            
                            String solucion = "";
                            if (nuevoEst.equalsIgnoreCase("FINALIZADA")) {
                                System.out.print("Ingrese la descripción de la solución aplicada: ");
                                solucion = scanner.nextLine();
                            }

                            boolean exito = encontrada.cambiarEstado(nuevoEst.toUpperCase(), solucion);
                            if (exito) {
                                System.out.println("¡Estado actualizado correctamente!");
                            }
                        } else {
                            System.out.println("Incidencia no encontrada.");
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese el estado a filtrar (REGISTRADA, LISTA, EN_DESARROLLO, EN_VALIDACION, FINALIZADA): ");
                        String estadoFiltro = scanner.nextLine();
                        List<Incidencia> filtradas = sistema.filtrarPorEstado(estadoFiltro);
                        
                        System.out.println("\n--- RESULTADOS DEL FILTRO ---");
                        if (filtradas.isEmpty()) {
                            System.out.println("No hay incidencias con ese estado.");
                        } else {
                            for (Incidencia i : filtradas) {
                                System.out.println("ID: " + i.getId() + " | Título: " + i.getTitulo() + " | Prioridad: " + i.getPrioridad() + " | Estado: " + i.getEstado());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n--- MÉTRICAS DEL SISTEMA ---");
                        System.out.println("Throughput (Incidencias Finalizadas): " + sistema.calcularThroughput());
                        System.out.println("Lead Time Promedio (Días): " + sistema.calcularLeadTimePromedio());
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Por favor ingrese un número válido.");
                scanner.next();
            }
        } while (opcion != 5);

        scanner.close();
    }
}