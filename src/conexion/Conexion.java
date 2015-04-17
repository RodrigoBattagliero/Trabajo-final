
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public class Conexion {
    private static Conexion instance;
    private Connection cnn;
    
    private Conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            cnn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/gasto_traslado_docente", "postgres", "123");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
    }
    
    public synchronized static Conexion estado(){
        if(instance == null) instance = new Conexion();
        return instance;
    }
    
    public Connection getCnn(){
        return cnn;
    }
    
    public void closeConecxion(){
        instance = null;
    }
    
}
