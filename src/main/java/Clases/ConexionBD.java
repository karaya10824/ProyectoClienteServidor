package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    public static Connection Conexion(){
        Connection nuevaConexion = null;
        
        try {
            nuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/proyectoClienteServidor?serverTimezone=UTC", "root", "Ar4y4.24");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return nuevaConexion;
    }
}
