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
public class ComprobantesDTO {
    private int id;
    private Double importe;
    private int id_solicitud;
    private String numero_comprobante;
    private String proveedor;
    private String observaciones;

    public ComprobantesDTO() {
    }

    public ComprobantesDTO(int id, Double importe, int id_solicitud, String numero_comprobante, String proveedor, String observaciones) {
        this.id = id;
        this.importe = importe;
        this.id_solicitud = id_solicitud;
        this.numero_comprobante = numero_comprobante;
        this.proveedor = proveedor;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
