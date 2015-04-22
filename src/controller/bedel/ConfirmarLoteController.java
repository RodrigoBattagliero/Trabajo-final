/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bedel;

import controller.ogagtd.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.DepartamentosAcademicosDAO;
import model.DepartamentosAcademicosDTO;
import model.EstadosDAO;
import model.EstadosDTO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import resources.DateManager;
import view.PanelConfirmarLoteDeSolicitudesProcesadas;

/**
 *
 * @author rodrigo
 */
public class ConfirmarLoteController {
    
    private PanelConfirmarLoteDeSolicitudesProcesadas view;
    private RegistroUnicoDAO model;
    
    private ArrayList<RegistroUnicoDTO> registros;
    
    private ArrayList<RegistroUnicoDTO> confirmar;
    
    private SolicitudDTO solicitud;

    public ConfirmarLoteController(PanelConfirmarLoteDeSolicitudesProcesadas view, RegistroUnicoDAO model) {
        this.view = view;
        this.model = model;
        this.registros = null;
        this.confirmar = null;
        this.solicitud = null;
    }
    
    public PanelConfirmarLoteDeSolicitudesProcesadas getView(){
        return this.view;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnConfirmarSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarSolicitudesActionPerformed(evt);
            }
        });
        this.setTable();
    }
    
    private void setTable(){
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatos.getModel();
        setRegistros();
        int cant = registros.size();
        for (int i = 0; i < cant; i++) {
            solicitud = new SolicitudDAO().selectOne(registros.get(i).getId_solicitud());
            ArrayList<DatosDocentesDTO> docentes = (ArrayList) new DatosDocentesDAO().selectRelated(registros.get(i).getId_solicitud());
            String row[] = {
              String.valueOf(solicitud.getNumero_solicutd()),
              String.valueOf(docentes.get(0).getApellido()+", "+docentes.get(0).getNombre()),
              String.valueOf(solicitud.getFecha_alta().getFechaString()),
              String.valueOf(getDeptoAcademico(docentes.get(0).getId_departamento_academico())),
              String.valueOf(getEstado(registros.get(i).getId_estado())),
              String.valueOf(registros.get(i).getId())
            };
            modelo.addRow(row);
        }
    }
    
    private String getDeptoAcademico(int idDeptoAcademico){
        DepartamentosAcademicosDTO depto = new DepartamentosAcademicosDAO().selectOne(idDeptoAcademico);
        return depto.getNombre();
    }
    
    private String getEstado(int idEstado){
        EstadosDTO estado = new EstadosDAO().selectOne(idEstado);
        return estado.getNombre();
    }
    
    private int getIdEstadoEnProceso(){
        return 1;
    }
    
    private int proxIdArea(){
        return 5;
    }
    
    private void setRegistros(){
        registros = (ArrayList) this.model.selectLoteConfirmar(getIdArea(),getEstadoAprobado());
    }
    
    private int getIdArea(){
        return 4;
    }
    
    private int getEstadoAprobado(){
        return 2;
    }
    
    private void btnConfirmarSolicitudesActionPerformed(ActionEvent evt) {
        confirmar = new ArrayList();
        RegistroUnicoDTO modif,nuevo;
        int rows[] = view.tblDatos.getSelectedRows();
        System.out.println(new DateManager(new Date()));
        int cant = rows.length;
        for (int i = 0; i < cant; i++) {
            modif = model.selectOne(Integer.parseInt(String.valueOf(this.view.tblDatos.getValueAt(i, 5))));
            modif.setConfirmado(true);
            modif.setFecha_salida(new DateManager(new Date()));
            nuevo = new RegistroUnicoDTO(0, new DateManager(new Date()), null, false, null, modif.getId_solicitud(), proxIdArea(), getIdEstadoEnProceso());
            model.update(modif);
            model.create(nuevo);
        }
        
    }
    
}
