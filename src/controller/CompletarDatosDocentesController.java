/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CompletarDatosDocentesDAO;
import view.PanelCompletarDatosDeDocentes;

/**
 *
 * @author rodrigo
 */
public class CompletarDatosDocentesController {
    
    PanelCompletarDatosDeDocentes View;
    CompletarDatosDocentesDAO Model;
    
    public CompletarDatosDocentesController(PanelCompletarDatosDeDocentes v, CompletarDatosDocentesDAO m) {
        this.View = v;
        this.Model = m;
    }
    
    public void init(){
        this.View.setVisible(true);
    }
    
    
}
