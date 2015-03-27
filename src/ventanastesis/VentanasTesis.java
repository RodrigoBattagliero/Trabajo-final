package ventanastesis;

import controller.LoginController;
import model.LoginDAO;
import view.FrameLogin;

/**
 *
 * @author rodrigo
 */
public class VentanasTesis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrameLogin LoginView = new FrameLogin();
        LoginDAO Login = new LoginDAO();
        LoginController LoginController = new LoginController(LoginView, Login);
        LoginController.init();
        
    }
    
}
