import java.time.LocalDate;

public class Incidencia {
    private String id;
    private String titulo;
    private String descripcion;
    private String impacto;
    private String urgencia;
    private String prioridad;
    private String estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private String solucionAplicada;

    // Constructor
    public Incidencia(String titulo, String descripcion, String impacto, String urgencia) {
        this.id = "INC-" + System.currentTimeMillis();
        setTitulo(titulo);
        setDescripcion(descripcion);
        setImpacto(impacto);
        setUrgencia(urgencia);
        this.prioridad = calcularPrioridad(impacto, urgencia);
        this.estado = "REGISTRADA";
        this.fechaCreacion = LocalDate.now();
        this.solucionAplicada = "";
    }

    // Lógica de cálculo automático de prioridad (HU-02)
    public static String calcularPrioridad(String impacto, String urgencia) {
        if (impacto == null || urgencia == null) return "NORMAL";
        
        String imp = impacto.toUpperCase();
        String urg = urgencia.toUpperCase();

        if (imp.equals("ALTO") && urg.equals("ALTA")) {
            return "CRÍTICA";
        } else if (imp.equals("ALTO") && (urg.equals("MEDIA") || urg.equals("BAJA"))) {
            return "ALTA";
        } else if ((imp.equals("MEDIO") || imp.equals("BAJO")) && urg.equals("ALTA")) {
            return "ALTA";
        } else {
            return "NORMAL";
        }
    }

    // Validación y cambio de estados con restricciones (HU-03)
    public boolean cambiarEstado(String nuevoEstado, String solucion) {
        if (nuevoEstado == null) return false;

        if (nuevoEstado.equals("FINALIZADA")) {
            if (solucion == null || solucion.trim().length() < 5) {
                System.out.println("Error: No se puede finalizar una incidencia sin una solución válida.");
                return false;
            }
            this.solucionAplicada = solucion;
            this.fechaCierre = LocalDate.now();
        }

        if (esTransicionValida(this.estado, nuevoEstado)) {
            this.estado = nuevoEstado;
            return true;
        } else {
            System.out.println("Error: Transición de estado no permitida de " + this.estado + " a " + nuevoEstado);
            return false;
        }
    }

    private boolean esTransicionValida(String actual, String nuevo) {
        if (actual.equals("REGISTRADA") && nuevo.equals("LISTA")) return true;
        if (actual.equals("LISTA") && nuevo.equals("EN_DESARROLLO")) return true;
        if (actual.equals("EN_DESARROLLO") && nuevo.equals("EN_VALIDACION")) return true;
        if (actual.equals("EN_VALIDACION") && nuevo.equals("FINALIZADA")) return true;
        return false; 
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().length() < 10) {
            throw new IllegalArgumentException("La descripción debe tener al menos diez caracteres.");
        }
        this.descripcion = descripcion;
    }

    public String getImpacto() { return impacto; }
    public void setImpacto(String impacto) { this.impacto = impacto; }

    public String getUrgencia() { return urgencia; }
    public void setUrgencia(String urgencia) { this.urgencia = urgencia; }

    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public String getSolucionAplicada() { return solucionAplicada; }
    
    // Métodos de fecha requeridos para las métricas (HU-05)
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public LocalDate getFechaCierre() { return fechaCierre; }
}