/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.DepartamentosAcademicosDAO;
import model.DepartamentosAcademicosDTO;
import resources.DateManager;
import view.PanelCompletarDatosDeDocentes;

/**
 *
 * @author rodrigo
 */
public class DatosDocentesController {
    // Solicitud
    private IniciarSolicitudController solicitudController;
    // Model - View Datos docentes
    private PanelCompletarDatosDeDocentes view;
    private DatosDocentesDAO model;
    private DatosDocentesDTO datosDocentesDTO;
    // Datos docentes
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String lugar_residencia;
    private int id_sede;
    private String motivo_comision;
    private DateManager fecha_inicio;
    private DateManager fecha_finalizacion;
    private int id_depto_academico;
    private int id_solicitud;
    private String observaciones;
    private int id;
    
    // Deptos academicos
    private DepartamentosAcademicosDAO DeptosAcademivosDAO;
    
            
    public DatosDocentesController(PanelCompletarDatosDeDocentes v, DatosDocentesDAO m, IniciarSolicitudController solicitudController) {
        this.view = v;
        this.model = m;
        this.solicitudController = solicitudController;
        this.datosDocentesDTO = null;
                
        nombre = null;
        apellido = null;
        dni = null;
        telefono = null;
        email  = null;
        lugar_residencia = null;
        id_sede = 0;
        motivo_comision = null;
        fecha_inicio = null;
        fecha_finalizacion = null;
        id_depto_academico = 0;
        id_solicitud = 0;
        observaciones = null;
        id = 0;
        
        DeptosAcademivosDAO = new DepartamentosAcademicosDAO();
    }
    
    public PanelCompletarDatosDeDocentes getView(){
        return view;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        ArrayList<DepartamentosAcademicosDTO> dptos = (ArrayList<DepartamentosAcademicosDTO>) DeptosAcademivosDAO.selectAll();
        int lenght = dptos.size();
        for(int i = 0; i < lenght; i++){
            this.view.cbxDeptoAcademico.addItem(dptos.get(i).getNombre());
        }
        
        
    }
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt){
        this.setDatos();
        if(this.verificarDatosDeEntrada()){
            this.datosTemporales();
            disableFields();
            this.solicitudController.comprobanteDeTraslado();
        }
    }
    
    private boolean verificarDatosDeEntrada(){
        boolean b = true;
        if(this.nombre.isEmpty())
            b = false;
        if(this.apellido.isEmpty())
            b = false;
        if(this.dni.isEmpty())
            b = false;
        if(this.telefono.isEmpty())
            b = false;
        if(this.email.isEmpty())
            b = false;
        if(this.lugar_residencia.isEmpty())
            b = false;
        if(this.motivo_comision.isEmpty())
            b = false;
        if(this.fecha_inicio == null)
            b = false;
        if(this.fecha_finalizacion == null)
            b = false;
        if(this.id_depto_academico == 0)
            b = false;
        System.out.println(b);
        if(b){
            return true;
        }else{
            if(this.observaciones.length() > 0){
                int op = JOptionPane.showConfirmDialog(this.view, 
                        "Uno o más campos obligatorios están vacíos. ¿Desea continuar?",
                        "Fecha de presentación incorrecta.",
                        0);
                
                if(op == 0)
                    return true;
                if(op == 1)
                    return false;
            }else{
                JOptionPane.showMessageDialog(view,
                        "Uno o más campos obligatorios están vacíos.\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                return false;
            }
        }
        return true;
    }
    
    private void setDatos(){
        this.nombre = this.view.txtNombre.getText();
        this.apellido = this.view.txtApellido.getText();
        this.dni = this.view.txtDni.getText();
        this.telefono = this.view.txtTelefono.getText();
        this.email  = this.view.txtEmail.getText();
        this.lugar_residencia = this.view.txtLugarDeResidencia.getText();
        this.motivo_comision = this.view.txtMotivoDeComision.getText();
        
        try {
            this.fecha_inicio = new DateManager(this.view.dateFechaHoraIncio.getDate());
        }catch (NullPointerException e){
            this.fecha_inicio = new DateManager("00-00-0000 00:00:00");
        }
        
        try{
            this.fecha_finalizacion = new DateManager(this.view.dateFechaHoraFinalizacion.getDate());
        }catch(NullPointerException e) {
            this.fecha_finalizacion = new DateManager("00-00-0000 00:00:00");
        }
        
        this.id_depto_academico = this.view.cbxDeptoAcademico.getSelectedIndex();
        ++this.id_depto_academico;
        this.observaciones = this.view.txtObservaciones.getText();
    }
    
    private void datosTemporales(){
        this.datosDocentesDTO = new DatosDocentesDTO(0, nombre, apellido, dni, telefono, email, lugar_residencia, motivo_comision, fecha_inicio, fecha_finalizacion, id_depto_academico, 0, observaciones);
    }
    
    private void disableFields(){
        this.view.cbxDeptoAcademico.setEnabled(false);
        this.view.txtApellido.setEnabled(false);
        this.view.txtNombre.setEnabled(false);
        this.view.txtDni.setEnabled(false);
        this.view.txtTelefono.setEnabled(false);
        this.view.txtEmail.setEnabled(false);
        this.view.txtLugarDeResidencia.setEnabled(false);
        this.view.txtMotivoDeComision.setEnabled(false);
        this.view.dateFechaHoraIncio.setEnabled(false);
        this.view.dateFechaHoraFinalizacion.setEnabled(false);
        this.view.txtObservaciones.setEnabled(false);
        this.view.btnAceptar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
    public int guardarDatos(int idSolicitud){
        this.datosDocentesDTO.setId_solicitud(idSolicitud);
        return this.model.create(this.datosDocentesDTO);
    }
    
}
