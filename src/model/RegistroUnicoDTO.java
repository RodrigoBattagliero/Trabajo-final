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
public class RegistroUnicoDTO {
    private int id;
    private DateManager fecha_entrada;
    private DateManager fecha_salida;
    private boolean confirmado;
    private String observaciones;
    private int id_solicitud;
    private int id_area;
    private int id_estado;

    public RegistroUnicoDTO() {
    }

    public RegistroUnicoDTO(int id, DateManager fecha_entrada, DateManager fecha_salida, boolean confirmado, String observaciones, int id_solicitud, int id_area, int id_estado) {
        this.id = id;
        if(fecha_entrada != null)
            this.fecha_entrada = fecha_entrada;
        else
            this.fecha_entrada = new DateManager("00-00-000 00:00:00");
        if(fecha_salida != null)
            this.fecha_salida = fecha_salida;
        else
            this.fecha_salida = new DateManager("00-00-0000 00:00:00");
        
        this.confirmado = confirmado;
        this.observaciones = observaciones;
        this.id_solicitud = id_solicitud;
        this.id_area = id_area;
        this.id_estado = id_estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateManager getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(DateManager fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public DateManager getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(DateManager fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
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

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
    
    
}
