/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ComprobantesDAO;
import model.ComprobantesDTO;
import model.DatosAlojamientoComidaDAO;
import model.DatosAlojamientoComidaDTO;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.DatosTrasladoDAO;
import model.DatosTrasladoDTO;
import model.DesignacionDocenteDAO;
import model.DesignacionDocenteDTO;
import model.HistorialSolicitudesDAO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import view.PanelHistorialSolicitudesProcesadas;

/**
 *
 * @author rodrigo
 */
public class historialSolicitudesController {
    
    private PanelHistorialSolicitudesProcesadas view;
    private HistorialSolicitudesDAO model;
    
    private ArrayList<SolicitudDTO> solicitudes;
    private ArrayList<RegistroUnicoDTO> registros;
    private ArrayList<DatosDocentesDTO> docentes;
    
    public historialSolicitudesController(PanelHistorialSolicitudesProcesadas view, HistorialSolicitudesDAO model) {
        this.view = view;
        this.model = model;
        
        this.view.tblSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolicitudesMouseClicked(evt);
            }
        });
        
    }
    
    public PanelHistorialSolicitudesProcesadas getView(){
        return this.view;
    }
    
    public void init(){
        this.view.setVisible(true);
        
        this.view.pnlDatosSolicitud.setVisible(false);
        this.view.pnlDatosDocente.setVisible(false);
        this.view.pnlDatosTraslado.setVisible(false);
        this.view.pnlDatosAlojamiento.setVisible(false);
        this.view.pnlDatosDesignacion.setVisible(false);
        
        this.setHistorial();
        
        int cant =  this.registros.size();
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblSolicitudes.getModel();
        for (int i = 0; i < cant; i++) {
            String row[] = {
              String.valueOf(solicitudes.get(i).getNumero_solicutd()),
              String.valueOf(docentes.get(i).getApellido()+", "+docentes.get(i).getNombre()),
              String.valueOf(solicitudes.get(i).getFecha_alta().getFechaString()),
              String.valueOf(registros.get(i).getFecha_entrada().getFechaString()),
              String.valueOf(registros.get(i).getFecha_salida().getFechaString()),
              String.valueOf(registros.get(i).getId_estado()),
              String.valueOf(solicitudes.get(i).getId())
            };
            modelo.addRow(row);
        }
    }
    
    private void setHistorial(){
        registros = (ArrayList<RegistroUnicoDTO>) new RegistroUnicoDAO().selectHistorial(getIdArea());
        solicitudes = new ArrayList();
        docentes = new ArrayList();
        ArrayList<DatosDocentesDTO> docenteAux = new ArrayList();
        
        int cant = registros.size();
        for (int i = 0; i < cant; i++) {
            try{
                solicitudes.add(
                        new SolicitudDAO().selectOne(registros.get(i).getId_solicitud())
                );
            }catch(IndexOutOfBoundsException | NullPointerException e){
                solicitudes.add(new SolicitudDTO(0, 0, 0, null, ""));
            }
            try{
                docenteAux = (ArrayList) new DatosDocentesDAO().selectRelated(registros.get(i).getId_solicitud());   
                docentes.add(docenteAux.get(0));
            }catch(IndexOutOfBoundsException | NullPointerException ee){
                docentes.add(new DatosDocentesDTO(0, "", "", "", "", "", "", "", null, null, 0, 0, ""));
            }
        }
    }
    
    private int getIdArea(){
        return 1;
    }
    
    private void tblSolicitudesMouseClicked(MouseEvent evt) {
        
        int selectedRow = this.view.tblSolicitudes.getSelectedRow();
        int idSolicitud = Integer.parseInt(String.valueOf(this.view.tblSolicitudes.getModel().getValueAt(selectedRow, 6)));
        
        setDatosSolicitud(idSolicitud);
        setDatosDocentes(idSolicitud);
        setDatosTraslado(idSolicitud);
        setDatosAlojamiento(idSolicitud);
        setDatosDesignacion(idSolicitud);
        
        
    }
    
    private void setDatosSolicitud(int idSolicitud){
        SolicitudDTO solicitud = null;
        solicitud = new SolicitudDAO().selectOne(idSolicitud);
        
        this.view.txtNumeroSolicitud.setText(String.valueOf(solicitud.getNumero_solicutd()));
        this.view.txtFechaPresetacion.setText(solicitud.getFecha_alta().getFechaString());
        this.view.txtSolicitudObservaciones.setText(solicitud.getObservaciones());
        
        this.view.pnlDatosSolicitud.setVisible(true);
        
    }
   
    private void setDatosDocentes(int idSolicitud){
        DatosDocentesDTO docente = null;
        try{
            docente = new DatosDocentesDAO().selectRelated(idSolicitud).get(0);
            
            this.view.txtDocente.setText(docente.getApellido() + ", "+docente.getNombre());
            this.view.txtDni.setText(docente.getDni());
            this.view.txtTelefono.setText(docente.getTelefono());
            this.view.txtEmail.setText(docente.getEmail());
            this.view.txtMotivoComision.setText(docente.getMotivi_comision());
            this.view.txtInicio.setText(docente.getFecha_hora_inicio().getFechaString());
            this.view.txtFin.setText(docente.getFecha_hora_finalizacion().getFechaString());
            this.view.txtDeptoAcademico.setText(String.valueOf(docente.getId_departamento_academico()));
            this.view.txtLugarResidencia.setText(docente.getLugar_residencia());
            this.view.txtDatosDocentesObservaciones.setText(docente.getObservaciones());
            
            this.view.pnlDatosDocente.setVisible(true);
            
        }catch(IndexOutOfBoundsException e){
            
        }
    }
    
    private void setDatosTraslado(int idSolicitud){
        ComprobantesDTO comprobante;
        DatosTrasladoDTO datosTraslado;
        
        try{
            comprobante = new ComprobantesDAO().selectRelated(idSolicitud).get(0);
        }catch(IndexOutOfBoundsException e){
            comprobante = null;
            return;
        }
        
        try{
            datosTraslado = new DatosTrasladoDAO().selectRelated(comprobante.getId()).get(0);
            
            this.view.txtNumeroComprobante.setText(comprobante.getNumero_comprobante());
            this.view.txtProveedor.setText(comprobante.getProveedor());
            this.view.txtImporte.setText(String.valueOf(comprobante.getImporte()));
            this.view.txtDatosDocentesObservaciones.setText(comprobante.getObservaciones());
            
            this.view.txtDesde.setText(datosTraslado.getDesde());
            this.view.txtSalida.setText(datosTraslado.getFecha_hora_salida().getFechaString());
            this.view.txtHasta.setText(datosTraslado.getHasta());
            this.view.txtRegreso.setText(datosTraslado.getFecha_hora_regreso().getFechaString());
            
            this.view.pnlDatosTraslado.setVisible(true);
        }catch(IndexOutOfBoundsException | NullPointerException e){
            datosTraslado = null;
            return;
        }
    }
    
    private void setDatosAlojamiento(int idSolicitud){
        ComprobantesDTO comprobante;
        DatosAlojamientoComidaDTO datosAlojamiento;
        
        try{
            comprobante = new ComprobantesDAO().selectRelated(idSolicitud).get(0);
        }catch(IndexOutOfBoundsException e){
            comprobante = null;
            return;
        }
        
        try{
            datosAlojamiento = new DatosAlojamientoComidaDAO().selectRelated(comprobante.getId()).get(0);
            
            this.view.txtNumeroComprobante.setText(comprobante.getNumero_comprobante());
            this.view.txtProveedor.setText(comprobante.getProveedor());
            this.view.txtImporte.setText(String.valueOf(comprobante.getImporte()));
            this.view.txtDatosDocentesObservaciones.setText(comprobante.getObservaciones());
            
            this.view.txtDescripcion.setText(datosAlojamiento.getDescripcion());
            
            this.view.pnlDatosAlojamiento.setVisible(true);
            
        }catch(IndexOutOfBoundsException | NullPointerException e){
            datosAlojamiento = null;
            return;
        }
    }
    
    private void setDatosDesignacion(int idSolicitud){
        DesignacionDocenteDTO designacion = null;
        try{
            designacion = new DesignacionDocenteDAO().selectRelated(idSolicitud).get(0);
            
            this.view.txtNumeroResolucion.setText(designacion.getNumero_resolucion());
            this.view.txtCategoria.setText(designacion.getCategoria());
            this.view.txtDedicacion.setText(designacion.getDedicacion());
            this.view.txtDesignacionDesde.setText(designacion.getDesde().getFechaString());
            this.view.txtDesignacionHasta.setText(designacion.getHasta().getFechaString());
            
            this.view.pnlDatosDesignacion.setVisible(true);
            
        }catch(IndexOutOfBoundsException e){
            
        }
    }
}

