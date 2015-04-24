/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sedeinterior;

import controller.ogagtd.*;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.DatosTrasladoDAO;
import model.DatosTrasladoDTO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SedesDAO;
import model.SedesDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import resources.DateManager;
import view.PanelCompletarDatosDeDocentes;
import view.PanelCompletarDatosDeTraslado;
import view.PanelCrearRegistroUnico;
import view.PanelIniciarSolicitud;

/**
 *
 * @author rodrigo
 */
public class IniciarSolicitudController {
    
    // Iniciar solicitud
    private PanelIniciarSolicitud view;
    private SolicitudDAO model;
    private SolicitudDTO solicitudDTO;
    
    // Datos docentes
    private DatosDocentesController datosDocentesController;
    
    // Comprobante de traslado
    private DatosTrasladoController datosTrasladoController;
    
    // Registro Unico
    private CrearRegistroUnicoController crearRegitroUnicoController;
    
    // Variables propias
    private int numeroSolicitud;
    private DateManager fechaPresentacion;
    private String observaciones;
    private DateFormat dateFormat;
    private int idSede;
    private final int DIAS_DE_PRESENTACION = 14;

    public IniciarSolicitudController(PanelIniciarSolicitud v, SolicitudDAO m) {
        this.view = v;
        this.model = m;
        this.solicitudDTO = null;
        this.numeroSolicitud = 0;
        this.fechaPresentacion = null;
        this.observaciones = null;
        this.idSede = 0;
        this.dateFormat = DateFormat.getDateInstance();
        
        // Datos docentes
        this.datosDocentesController = null;
        
        // Comprobante de traslado
        this.datosTrasladoController = null;
        
        // Crear registro unico
        this.crearRegitroUnicoController = null;
        
        
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.txtFechaDePresentacion.setDate(new Date());
        this.setSedes();
        this.view.txtNumeroDeSolicitud.setText(String.valueOf(this.calularNumeroSolicitud()));
        this.view.btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        this.view.cbxSedes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSedesItemStateChanged(evt);
            }
        });
    }
    
    public void btnGuardarActionPerformed(java.awt.event.ActionEvent evt){
        if(verificarDatosEntrada()){
            this.datosTempotales();
            this.datosDocentes();
        }else{
            JOptionPane.showMessageDialog(view, "Error en los datos ingresados");
        } 
    }
    
    private void cbxSedesItemStateChanged(ItemEvent evt) {
        this.view.txtNumeroDeSolicitud.setText(String.valueOf(this.calularNumeroSolicitud()));
    }
    
    private void setSedes(){
        ArrayList<SedesDTO> sedes = (ArrayList<SedesDTO>) new SedesDAO().selectAll();
        int cant = sedes.size();
        for (int i = 0; i < cant; i++) {
            this.view.cbxSedes.addItem(sedes.get(i).getNombre());
        }
    }
    
    private int calularNumeroSolicitud(){
        idSede = this.view.cbxSedes.getSelectedIndex();
        idSede++;
        solicitudDTO = model.selectLast(idSede);
        int numSol = 0;
        if(solicitudDTO != null)
            numSol = solicitudDTO.getNumero_solicutd();
        return ++numSol;
    }
    
    private boolean vefiricarDiasPresentacion(){
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día  
        
        //Fecha de hoy 
        java.util.Date hoy = new Date(); 
        Calendar h = Calendar.getInstance();
        h.setTime(hoy);
        int anioA = h.get(Calendar.YEAR);
        int mesA = h.get(Calendar.MONTH);
        int diaA = h.get(Calendar.DAY_OF_MONTH);
        mesA++;
        Calendar calendarA = new GregorianCalendar(anioA, mesA, diaA); 
        java.sql.Date fechaA = new java.sql.Date(calendarA.getTimeInMillis());
        // Fecha de presentacion
        Calendar ca = Calendar.getInstance();
        ca.setTime(fechaPresentacion.getFechaDate());
        int anioP = ca.get(Calendar.YEAR);
        int mesP = ca.get(Calendar.MONTH);
        int diaP = ca.get(Calendar.DAY_OF_MONTH);
        mesP++;
        Calendar calendarP = new GregorianCalendar(anioP, mesP, diaP); 
        java.sql.Date fechaP = new java.sql.Date(calendarP.getTimeInMillis());
        
        // Diferencia
        long diferencia = ( fechaA.getTime() - fechaP.getTime() )/MILLSECS_PER_DAY; 
        return diferencia >= 0 && diferencia <= this.DIAS_DE_PRESENTACION;
    }
    
    private boolean verificarDatosEntrada(){
        boolean bData = false;
        String numeroSolicitudStr = this.view.txtNumeroDeSolicitud.getText();
        try{
           this.numeroSolicitud = Integer.parseInt(numeroSolicitudStr); 
        }catch(Exception e){
            bData = false;
        }
        
        this.idSede = this.view.cbxSedes.getSelectedIndex();
        this.idSede++;
        try{
            this.fechaPresentacion = new DateManager(this.view.txtFechaDePresentacion.getDate());
        }catch(NullPointerException e){
            bData = false;
            this.fechaPresentacion = new DateManager("00-00-0000 00:00:00");
        }
        
        observaciones = this.view.txtObservaciones.getText();
        
        boolean b = vefiricarDiasPresentacion();
        if(bData && b){
            return true;
        }else{
            if(this.observaciones.length() > 0){
                int op = JOptionPane.showConfirmDialog(this.view, 
                        "La fecha de presentación es mayor a "+this.DIAS_DE_PRESENTACION+" días. \n¿Desea continuar con el proceso de la solicitud?",
                        "Fecha de presentación incorrecta.",
                        0);
                
                if(op == 0)
                    return true;
                if(op == 1)
                    return false;
            }else{
                JOptionPane.showMessageDialog(view, "La fecha de presentación es mayor a "+this.DIAS_DE_PRESENTACION+" días.\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                return false;
            }
        }
        return false;
    }
    
    private void datosTempotales(){
        this.solicitudDTO = new SolicitudDTO(0, numeroSolicitud, 2, fechaPresentacion, observaciones,idSede);
    }
    
    private void guardarDatos(){
        int idSolicitud = this.model.create(solicitudDTO);
        this.datosDocentesController.guardarDatos(idSolicitud);
        this.datosTrasladoController.guardarDatos(idSolicitud);
        this.crearRegitroUnicoController.guardarDatos(idSolicitud);
    }
    
    private void disableFields(){
        this.view.txtFechaDePresentacion.setEnabled(false);
        this.view.txtNumeroDeSolicitud.setEnabled(false);
        this.view.txtObservaciones.setEditable(false);
        this.view.cbxSedes.setEnabled(false);
        this.view.btnGuardar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
     private void enableFields(){
        this.view.txtFechaDePresentacion.setEnabled(true);
        this.view.txtNumeroDeSolicitud.setEnabled(true);
        this.view.txtObservaciones.setEditable(true);
        this.view.cbxSedes.setEnabled(true);
        this.view.btnGuardar.setEnabled(true);
        this.view.btnCancelar.setEnabled(true);
    }
    
    private void reset(){
        this.datosDocentesController.setEnable();
        this.datosTrasladoController.setEnable();
        this.crearRegitroUnicoController.setEnable();
        this.enableFields();
        this.init();
        this.view.updateUI();
        
    }
    
    public void datosDocentes(){
        datosDocentesController = new DatosDocentesController(new PanelCompletarDatosDeDocentes(),new DatosDocentesDAO(),this);
        
        datosDocentesController.init();
        this.view.pnlDatosDocente.add(datosDocentesController.getView());
        this.view.pnlDatosDocente.setVisible(true);
        disableFields();
        this.view.updateUI();
    }
    
    
    public void comprobanteDeTraslado(){
        datosTrasladoController = new DatosTrasladoController(new PanelCompletarDatosDeTraslado(),new DatosTrasladoDAO(),this);
        datosTrasladoController.init();
        
        this.view.pnlComprobanteTraslado.add(datosTrasladoController.getView());
        this.view.pnlComprobanteTraslado.setVisible(true);
        this.view.updateUI();
    }
    
    
    public void crearRegistroUnico(){
        this.crearRegitroUnicoController = new CrearRegistroUnicoController(new PanelCrearRegistroUnico(), new RegistroUnicoDAO(),this);
        this.crearRegitroUnicoController.init();
        
        this.view.pnlRegistroUnico.add(this.crearRegitroUnicoController.getView());
        this.view.pnlRegistroUnico.setVisible(true);
        this.view.updateUI();
    }
    
    
    public void confirmar(){
        int op = JOptionPane.showConfirmDialog(null,
                "¿Esta seguro que desea guardar la solicitud?",
                "Confirmar",
                2);
        if(op == 0){
            this.guardarDatos();
            JOptionPane.showMessageDialog(null, "Solicitud iniada con éxito.");
            reset();
        }
    }    
    
    public DateManager getFechaPresentacion(){
        return this.solicitudDTO.getFecha_alta();
    }
    
    
}
