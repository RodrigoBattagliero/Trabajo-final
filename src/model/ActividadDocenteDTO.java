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
public class ActividadDocenteDTO {
    private int id;
    private int id_solicitud;
    private String asignatura;
    private DateManager fecha;
    private String observaciones;

    public ActividadDocenteDTO() {
    }

    public ActividadDocenteDTO(int id, int id_solicitud, String asignatura, DateManager fecha, String observaciones) {
        this.id = id;
        this.id_solicitud = id_solicitud;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public DateManager getFecha() {
        return fecha;
    }

    public void setFecha(DateManager fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
