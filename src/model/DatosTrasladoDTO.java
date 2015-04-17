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
public class DatosTrasladoDTO {
    private int id;
    private String desde;
    private String hasta;
    private DateManager fecha_hora_salida;
    private DateManager fecha_hora_regreso;
    private int id_comprobante;

    public DatosTrasladoDTO() {
    }

    public DatosTrasladoDTO(int id, String desde, String hasta, DateManager fecha_hora_salida, DateManager fecha_hora_regreso, int id_comprobante) {
        this.id = id;
        this.desde = desde;
        this.hasta = hasta;
        this.fecha_hora_salida = fecha_hora_salida;
        this.fecha_hora_regreso = fecha_hora_regreso;
        this.id_comprobante = id_comprobante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public DateManager getFecha_hora_salida() {
        return fecha_hora_salida;
    }

    public void setFecha_hora_salida(DateManager fecha_hora_salida) {
        this.fecha_hora_salida = fecha_hora_salida;
    }

    public DateManager getFecha_hora_regreso() {
        return fecha_hora_regreso;
    }

    public void setFecha_hora_regreso(DateManager fecha_hora_regreso) {
        this.fecha_hora_regreso = fecha_hora_regreso;
    }

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }
    
    
}
