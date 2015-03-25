package ventanastesis;

import ventanastesis.view.PanelSolicitudesACompletarOGAGTD;
import ventanastesis.view.VentanaPrincipal;
import ventanastesis.view.controller.SolicitudesACompletarController;

/**
 *
 * @author rodrigo
 */
public class VentanasTesis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaPrincipal Ventana = new VentanaPrincipal();
        Ventana.setVisible(true);
        PanelSolicitudesACompletarOGAGTD vista = new PanelSolicitudesACompletarOGAGTD();
        SolicitudesACompletarController controlador = new SolicitudesACompletarController(vista);
    }
    
}
