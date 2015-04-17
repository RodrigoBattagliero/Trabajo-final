/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class DesignacionDocenteDTO {
    private int id;
    private String numero_resolucion;
    private String categoria;
    private DateManager desde;
    private DateManager hasta;
    private String dedicacion;
    private String observaciones;
    private int id_solicitud;

    public DesignacionDocenteDTO() {
    }

    public DesignacionDocenteDTO(int id, String numero_resolucion, String categoria, DateManager desde, DateManager hasta, String dedicacion, String observaciones, int id_solicitud) {
        this.id = id;
        this.numero_resolucion = numero_resolucion;
        this.categoria = categoria;
        this.desde = desde;
        this.hasta = hasta;
        this.dedicacion = dedicacion;
        this.observaciones = observaciones;
        this.id_solicitud = id_solicitud;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(String numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public DateManager getDesde() {
        return desde;
    }

    public void setDesde(DateManager desde) {
        this.desde = desde;
    }

    public DateManager getHasta() {
        return hasta;
    }

    public void setHasta(DateManager hasta) {
        this.hasta = hasta;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    
    
    
}
