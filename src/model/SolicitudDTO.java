/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import resources.DateManager;

/**
 *
 * @author rodrigo
 */
public class SolicitudDTO {
    private int id;
    private int numero_solicutd;
    private int tipo;
    private DateManager fecha_alta;
    private String observaciones;

    public SolicitudDTO() {
    }

    public SolicitudDTO(int id, int numero_solicutd, int tipo, DateManager fecha_alta,String observaciones) {
        this.id = id;
        this.numero_solicutd = numero_solicutd;
        this.tipo = tipo;
        this.fecha_alta = fecha_alta;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_solicutd() {
        return numero_solicutd;
    }

    public void setNumero_solicutd(int numero_solicutd) {
        this.numero_solicutd = numero_solicutd;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public DateManager getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(DateManager fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
