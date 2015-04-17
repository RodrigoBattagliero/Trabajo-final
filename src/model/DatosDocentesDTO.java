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
public class DatosDocentesDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String lugar_residencia;
    private String motivi_comision;
    private DateManager fecha_hora_inicio;
    private DateManager fecha_hora_finalizacion;
    private int id_departamento_academico;
    private int id_solicitud;
    private String observaciones;

    public DatosDocentesDTO() {
    }

    public DatosDocentesDTO(int id, String nombre, String apellido, String dni, String telefono, String email, String lugar_residencia, String motivi_comision, DateManager fecha_hora_inicio, DateManager fecha_hora_finalizacion, int id_departamento_academico, int id_solicitud, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.lugar_residencia = lugar_residencia;
        this.motivi_comision = motivi_comision;
        if(fecha_hora_inicio != null)
            this.fecha_hora_inicio = fecha_hora_inicio;
        else
            this.fecha_hora_inicio = new DateManager("00-00-0000 00:00:00");
        
        if(fecha_hora_finalizacion != null)
            this.fecha_hora_finalizacion = fecha_hora_finalizacion;
        else
            this.fecha_hora_finalizacion = new DateManager("00-00-0000 00:00:00");
        
        this.id_departamento_academico = id_departamento_academico;
        this.id_solicitud = id_solicitud;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLugar_residencia() {
        return lugar_residencia;
    }

    public void setLugar_residencia(String lugar_residencia) {
        this.lugar_residencia = lugar_residencia;
    }

    public String getMotivi_comision() {
        return motivi_comision;
    }

    public void setMotivi_comision(String motivi_comision) {
        this.motivi_comision = motivi_comision;
    }

    public DateManager getFecha_hora_inicio() {
        return fecha_hora_inicio;
    }

    public void setFecha_hora_inicio(DateManager fecha_hora_inicio) {
        this.fecha_hora_inicio = fecha_hora_inicio;
    }

    public DateManager getFecha_hora_finalizacion() {
        return fecha_hora_finalizacion;
    }

    public void setFecha_hora_finalizacion(DateManager fecha_hora_finalizacion) {
        this.fecha_hora_finalizacion = fecha_hora_finalizacion;
    }

    public int getId_departamento_academico() {
        return id_departamento_academico;
    }

    public void setId_departamento_academico(int id_departamento_academico) {
        this.id_departamento_academico = id_departamento_academico;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
}
