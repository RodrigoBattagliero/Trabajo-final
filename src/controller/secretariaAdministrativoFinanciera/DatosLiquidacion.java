/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.secretariaAdministrativoFinanciera;

import controller.rendicionDeCuentas.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ComprobantesDAO;
import model.ComprobantesDTO;
import model.DatosAlojamientoComidaDAO;
import model.DatosAlojamientoComidaDTO;
import model.DatosTrasladoDAO;
import model.DatosTrasladoDTO;
import model.LiquidacionDAO;
import model.LiquidacionDTO;
import view.PanelRendicionDeCuentas;

/**
 *
 * @author rodrigo
 */
public class DatosLiquidacion {
    
    private CompletarDatosSAF rendicionDeCuentas;
    private PanelRendicionDeCuentas view;
    private LiquidacionDAO model;
    
    private int id;
    private int idSolicitud;
    private Double reconocimientoGastoComida;
    private Double reconocimientoGastoTraslado;
    private Double reconocimientoGastoCombustible;
    private Double importeDeclarado;
    private Double reconocimientoImporteTotal;
    private String observaciones;
    
    private LiquidacionDTO liquidacion;

    public DatosLiquidacion(PanelRendicionDeCuentas view, LiquidacionDAO model,CompletarDatosSAF bedel) {
        this.view = view;
        this.model = model;
        this.rendicionDeCuentas = bedel;
        
        id = 0;
        idSolicitud = 0;
        reconocimientoGastoComida = null;
        reconocimientoGastoTraslado = null;
        reconocimientoGastoCombustible = null;
        importeDeclarado = null;
        reconocimientoImporteTotal = null;
        observaciones = null;
        liquidacion = null;

       
    }
    
    public void init(){
        this.view.setVisible(true);
        setDatosIniciales();
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }

        });
    }
    
    public PanelRendicionDeCuentas getView(){
        return this.view;
    }
    
    public void setIdSolicitud(int setIdSolicitud){
        this.idSolicitud = setIdSolicitud;
    }
    
    public void guardarDatos(){
        model.create(liquidacion);
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
        if(verificarDatos()){
            //sedeInterior.setEnableBotones();
            enableFields();
            datosTemporales();
            guardarDatos();
            rendicionDeCuentas.setRegistroUnico();
        }
    }
    
    private boolean verificarDatos(){
        boolean b = true;
        
        String importeDeclaradoStr = view.txtDeclarado.getText();
        String importeAbonarStr = view.txtAbonar.getText();
        String importeCombustibleStr = view.txtCombustible.getText();
        String importeComidaStr = view.txtComida.getText();
        String importeTrasladoStr = view.txtTraslado.getText();
        
        observaciones = view.txtObservaciones.getText();
        
        try{
            importeDeclarado = Double.parseDouble(importeDeclaradoStr);
            reconocimientoImporteTotal = Double.parseDouble(importeAbonarStr);
            reconocimientoGastoCombustible = Double.parseDouble(importeCombustibleStr);
            reconocimientoGastoComida = Double.parseDouble(importeComidaStr);
            reconocimientoGastoTraslado = Double.parseDouble(importeTrasladoStr);
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(view, "Los campos deben ser númericos.");
            return false;
        }
        
        if(b){
            return true;
        }else{
            if(this.observaciones.length() > 0){
                int op = JOptionPane.showConfirmDialog(this.view, 
                        "No completo los campos obligatorios. ¿Desea continuar?",
                        "Fecha de presentación incorrecta.",
                        0);
                
                if(op == 0)
                    return true;
                if(op == 1)
                    return false;
            }else{
                JOptionPane.showMessageDialog(view, "No completo los campos obligatorios.\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                return false;
            }
        }
        
        return b;
    }
    
    private void enableFields(){
        view.txtCombustible.setEnabled(false);
        view.txtComida.setEnabled(false);
        view.txtTraslado.setEnabled(false);
        view.txtDeclarado.setEnabled(false);
        view.txtAbonar.setEnabled(false);
        view.txtObservaciones.setEnabled(false);
        view.btnCancelar.setEnabled(false);
        view.btnAceptar.setEnabled(false);
        
    }
    
    private void datosTemporales(){
        liquidacion = new LiquidacionDTO(0, idSolicitud, reconocimientoGastoComida, reconocimientoGastoTraslado, reconocimientoGastoCombustible, importeDeclarado, reconocimientoImporteTotal, observaciones);
    }
    
    private void setDatosIniciales(){
        Double traslado = 0.0, comida = 0.0, combustible = 0.0, declarado = 0.0, abonar = 0.0;
        ArrayList<ComprobantesDTO> comprobantes = (ArrayList<ComprobantesDTO>) new ComprobantesDAO().selectRelated(idSolicitud);
        int cant = comprobantes.size();
        for (int i = 0; i < cant; i++) {
            ArrayList<DatosTrasladoDTO> trasladoDTO = (ArrayList<DatosTrasladoDTO>) new DatosTrasladoDAO().selectRelated(comprobantes.get(i).getId());
            if(trasladoDTO.size()> 0){
                traslado = comprobantes.get(i).getImporte();
                declarado += comprobantes.get(i).getImporte();
                abonar += comprobantes.get(i).getImporte();
            }else{
                ArrayList<DatosAlojamientoComidaDTO> comidaDTO = (ArrayList<DatosAlojamientoComidaDTO>) new DatosAlojamientoComidaDAO().selectRelated(comprobantes.get(i).getId());
                if(comidaDTO.get(0).getTipo() == 1)
                    comida = comprobantes.get(i).getImporte();
                else
                    combustible =  comprobantes.get(i).getImporte();
                declarado += comprobantes.get(i).getImporte();
                abonar += comprobantes.get(i).getImporte();
            }
                
        }
        view.txtAbonar.setText(String.valueOf(abonar));
        view.txtCombustible.setText(String.valueOf(combustible));
        view.txtComida.setText(String.valueOf(comida));
        view.txtDeclarado.setText(String.valueOf(declarado));
        view.txtTraslado.setText(String.valueOf(traslado));
    }
    
}
