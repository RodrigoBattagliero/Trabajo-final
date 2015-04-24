/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sedeinterior;

import controller.ogagtd.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.AreasDAO;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.EstadosDAO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import resources.DateManager;
import view.PanelConsultarRegistroUnico;

/**
 *
 * @author rodrigo
 */
public class ConsultarRegistroUnicoController {
    
    private PanelConsultarRegistroUnico view;
    private RegistroUnicoDAO model;
    
    private ArrayList<SolicitudDTO> solicitudes;
    private ArrayList<RegistroUnicoDTO> registros;
    private ArrayList<DatosDocentesDTO> docentes;

    public ConsultarRegistroUnicoController(PanelConsultarRegistroUnico view, RegistroUnicoDAO model) {
        this.view = view;
        this.model = model;
        
        this.registros = null;
    }
    
    public void init(){
        view.setVisible(true);
        this.view.btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }
    
    public PanelConsultarRegistroUnico getView(){
        return view;
    }
    
    private void btnBuscarActionPerformed(ActionEvent evt) {
        System.out.println("ad");
        String numeroSolicitudStr = view.txtNumeroSolicitud.getText();
        
        boolean b = false,bb = true;
        int numeroSolicitud = 0;
                
        
        if("".equals(numeroSolicitudStr)){ b = true;bb=false;}
        
        
        if(bb){
            try{
                numeroSolicitud = Integer.parseInt(numeroSolicitudStr);
                
            }catch(Exception e){
                return;
            }
        }
        
        setDatosTabla(numeroSolicitud);
        setTable();
    }


    private void setDatosTabla(int numeroSolicitud){
        solicitudes = (ArrayList<SolicitudDTO>) new SolicitudDAO().selectRelated(numeroSolicitud);
        registros = (ArrayList<RegistroUnicoDTO>) this.model.selectRelated(solicitudes.get(0).getId());
        docentes = (ArrayList<DatosDocentesDTO>) new DatosDocentesDAO().selectRelated(solicitudes.get(0).getId());
                
    }
    
    public void setTable(){
        int cant =  this.registros.size();
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblSolicitudes.getModel();
        for (int i = 0; i < cant; i++) {
            String row[] = {
              String.valueOf(solicitudes.get(0).getNumero_solicutd()),
              String.valueOf(docentes.get(0).getApellido()+", "+docentes.get(0).getNombre()),
              String.valueOf(solicitudes.get(0).getFecha_alta().getFechaString()),
              String.valueOf(registros.get(i).getFecha_entrada().getFechaString()),
              String.valueOf(registros.get(i).getFecha_salida().getFechaString()),
              String.valueOf(getEstado(registros.get(i).getId_estado())),
              String.valueOf(getArea(registros.get(i).getId_area()))          
            };
            modelo.addRow(row);
        }
    }
    
    private String getEstado(int idEstado){
        return new EstadosDAO().selectOne(idEstado).getNombre();
    }
    private String getArea(int idArea){
        return new AreasDAO().selectOne(idArea).getNombre();
    }
    
}
