package com.helpdesk;

import java.time.LocalDateTime;
import java.util.UUID;

public class Incidencia {
    private String id;
    private String titulo;
    private String descripcion;
    private String impacto; // BAJO, MEDIO, ALTO
    private String urgencia; // BAJA, MEDIA, ALTA
    private String prioridad; // Calculada automáticamente
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;
    private String descripcionSolucion;

    public Incidencia(String titulo, String descripcion, String impacto, String urgencia) {
        // Validaciones básicas de HU-01
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (descripcion == null || descripcion.length() < 10) {
            throw new IllegalArgumentException("La descripción debe contener al menos diez caracteres.");
        }

        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // Identificador único corto
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.impacto = impacto.toUpperCase();
        this.urgencia = urgencia.toUpperCase();
        this.prioridad = calcularPrioridad(this.impacto, this.urgencia); // HU-02
        this.estado = "REGISTRADA";
        this.fechaCreacion = LocalDateTime.now();
    }

    // HU-02: Reglas automáticas de prioridad
    private String calcularPrioridad(String imp, String urg) {
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

    // Getters y setters básicos para consultas (HU-04)
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getImpacto() { return impacto; }
    public String getUrgencia() { return urgencia; }
    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}