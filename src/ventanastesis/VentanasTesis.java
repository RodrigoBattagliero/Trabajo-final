package ventanastesis;

import controller.LoginController;
import controller.OgagtdController;
import model.LoginDAO;
import model.OgagtdDAO;
import view.FrameLogin;
import view.VentanaOgagtd;

/**
 *
 * @author rodrigo
 */
public class VentanasTesis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaOgagtd ventana = new VentanaOgagtd();
        OgagtdDAO ogagtdDAO = new OgagtdDAO();
        OgagtdController ogagtdController = new OgagtdController(ventana, ogagtdDAO);
        ogagtdController.init();
        /*
        FrameLogin LoginView = new FrameLogin();
        LoginDAO Login = new LoginDAO();
        LoginController LoginController = new LoginController(LoginView, Login);
        LoginController.init();
        */
        
    }
    
}