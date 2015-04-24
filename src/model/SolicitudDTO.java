/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private int id_sede;

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public SolicitudDTO() {
    }

    public SolicitudDTO(int id, int numero_solicutd, int tipo, DateManager fecha_alta,String observaciones,int id_sede) {
        this.id = id;
        this.numero_solicutd = numero_solicutd;
        this.tipo = tipo;
        this.fecha_alta = fecha_alta;
        this.observaciones = observaciones;
        this.id_sede = id_sede;
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
    
    public String getNumeroSolicitudCompleto(){
        Calendar fecha = new GregorianCalendar();
        fecha.setTime(getFecha_alta().getFechaDate());
        return String.format("%s-%s",getNumero_solicutd(),fecha.get(Calendar.YEAR));
    }
    
    
}
