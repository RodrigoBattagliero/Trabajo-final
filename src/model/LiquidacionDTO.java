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
public class LiquidacionDTO {
    
    private int id;
    private int id_solicitud;
    private Double reconocimientoGastoComida;
    private Double reconocimientoGastoAlojamiento;
    private Double reconocimientoGastoCombustible;
    private Double importeDeclarado;
    private Double reconocimientoImporteTotal;
    private String observaciones;

    public LiquidacionDTO() {
    }

    public LiquidacionDTO(int id, int id_solicitud, Double reconocimientoGastoComida, Double reconocimientoGastoAlojamiento, Double reconocimientoGastoCombustible, Double importeDeclarado, Double reconocimientoImporteTotal, String observaciones) {
        this.id = id;
        this.id_solicitud = id_solicitud;
        this.reconocimientoGastoComida = reconocimientoGastoComida;
        this.reconocimientoGastoAlojamiento = reconocimientoGastoAlojamiento;
        this.reconocimientoGastoCombustible = reconocimientoGastoCombustible;
        this.importeDeclarado = importeDeclarado;
        this.reconocimientoImporteTotal = reconocimientoImporteTotal;
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

    public Double getReconocimientoGastoComida() {
        return reconocimientoGastoComida;
    }

    public void setReconocimientoGastoComida(Double reconocimientoGastoComida) {
        this.reconocimientoGastoComida = reconocimientoGastoComida;
    }

    public Double getReconocimientoGastoAlojamiento() {
        return reconocimientoGastoAlojamiento;
    }

    public void setReconocimientoGastoAlojamiento(Double reconocimientoGastoAlojamiento) {
        this.reconocimientoGastoAlojamiento = reconocimientoGastoAlojamiento;
    }

    public Double getReconocimientoGastoCombustible() {
        return reconocimientoGastoCombustible;
    }

    public void setReconocimientoGastoCombustible(Double reconocimientoGastoCombustible) {
        this.reconocimientoGastoCombustible = reconocimientoGastoCombustible;
    }

    public Double getImporteDeclarado() {
        return importeDeclarado;
    }

    public void setImporteDeclarado(Double importeDeclarado) {
        this.importeDeclarado = importeDeclarado;
    }

    public Double getReconocimientoImporteTotal() {
        return reconocimientoImporteTotal;
    }

    public void setReconocimientoImporteTotal(Double reconocimientoImporteTotal) {
        this.reconocimientoImporteTotal = reconocimientoImporteTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
