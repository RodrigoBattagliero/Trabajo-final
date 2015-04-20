/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import model.ExpedienteDAO;
import view.PanelGenerarExpediente;

/**
 *
 * @author rodrigo
 */
public class ExpedienteController {
    private PanelGenerarExpediente view;
    private ExpedienteDAO model;

    public ExpedienteController(PanelGenerarExpediente view, ExpedienteDAO model) {
        this.view = view;
        this.model = model;
    }
    
    public void init(){
        this.view.setVisible(true);
    }
}
