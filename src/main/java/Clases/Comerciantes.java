package Clases;

import Vistas.menuComerciante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Comerciantes {
    public String nombre_empresa;
    public String descripcion_empresa;
    public String direccion_empresa;
    public String contacto;

    public Comerciantes() {}
    
    public Comerciantes(String nombre_empresa, String descripcion_empresa, String direccion_empresa, String contacto) {
        this.nombre_empresa = nombre_empresa;
        this.descripcion_empresa = descripcion_empresa;
        this.direccion_empresa = direccion_empresa;
        this.contacto = contacto;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDescripcion_empresa() {
        return descripcion_empresa;
    }

    public void setDescripcion_empresa(String descripcion_empresa) {
        this.descripcion_empresa = descripcion_empresa;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public boolean IniciarSesion(String correoIngresado, String contrasenaIngresado) throws ComercianteNoEncontrado{
        String correoEncontrado = "";
        String contrasenaEncontrado = "";
        int idEncontrado = 0;
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = DriverManager.getConnection("jdbc:mysql://localhost/proyectoClienteServidor?serverTimezone=UTC", "root", "Ar4y4.24");
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.comerciantes WHERE correoElectronico = ?";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros
            comandoSelectPreparado.setString(1, correoIngresado);
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                idEncontrado = datos.getInt("id");
                correoEncontrado = datos.getString("correoElectronico");
                contrasenaEncontrado = datos.getString("contrasena");            
            }//else{
            
            if(correoIngresado.equals(correoEncontrado)){
                if(contrasenaIngresado.equals(contrasenaEncontrado)){
                    System.out.print("Id: " + idEncontrado + "/nCorreo: " + correoEncontrado + "\n Contraseña: " + contrasenaEncontrado);
                   return true;
                }else{
                    throw new ComercianteNoEncontrado("Correo eléctrónico o contraseña inválidos");
                }
            }else{
                throw new ComercianteNoEncontrado("Correo eléctrónico o contraseña inválidos");
            }            
            //}
        } catch (SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
            return false;
        }             
    } 
    
    public void mostrarProductos(){
    
    }
}
