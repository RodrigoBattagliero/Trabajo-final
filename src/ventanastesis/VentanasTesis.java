package ventanastesis;

import resources.Login;
import model.LoginDAO;
import resources.DateManager;
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
          
//          DateManager f = new DateManager("10-10-2011 10:00:00");
//          System.out.println(f);
        
        FrameLogin LoginView = new FrameLogin();
        LoginDAO Login = new LoginDAO();
        Login LoginController = new Login(LoginView, Login);
        LoginController.init();
        
        
    }
    
}