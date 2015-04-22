/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rodrigo
 */
public class DatosAlojamientoComidaDTO {
    private int id;
    private int tipo; // 1: alojamiento o comida; 2: Combustible
    private String descripcion;
    private int id_comprobante;

    public DatosAlojamientoComidaDTO() {
    }

    public DatosAlojamientoComidaDTO(int id, int tipo, String descripcion, int id_comprobante) {
        this.id = id;
        this.tipo = tipo; 
        this.descripcion = descripcion;
        this.id_comprobante = id_comprobante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }
        
}
