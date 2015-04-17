package ventanastesis;

import controller.OgagtdController;
import model.OgagtdDAO;
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
        /*
        try{
            Class.forName("org.postgresql.Driver");
            Connection cnn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/gasto_traslado_docente", "postgres", "123");
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM estados");
            ResultSet res = ps.executeQuery();
            while(res.next()){
                System.out.printf("%d -> %s\n",res.getInt(1),res.getString(2));
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
        */
        
        VentanaOgagtd ventana = new VentanaOgagtd();
        OgagtdDAO ogagtdDAO = new OgagtdDAO();
        OgagtdController ogagtdController = new OgagtdController(ventana, ogagtdDAO);
        ogagtdController.init();
        
        /*
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día  
        DateManager fechaActual = new DateManager("14-04-2015 00:00:00");
        Date d = new Date(fechaActual.getFechaLong());
        System.out.println(d);
        */
        
        /*
        FrameLogin LoginView = new FrameLogin();
        LoginDAO Login = new LoginDAO();
        LoginController LoginController = new LoginController(LoginView, Login);
        LoginController.init();
        */
        
    }
    
}