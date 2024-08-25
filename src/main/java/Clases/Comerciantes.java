package Clases;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Comerciantes extends Usuarios implements Serializable{
    public String contrasena;
    public String nombre_empresa;
    public String descripcion_empresa;

    public Comerciantes() {}

    public Comerciantes(String contrasena, String nombre_empresa, String descripcion_empresa) {
        this.contrasena = contrasena;
        this.nombre_empresa = nombre_empresa;
        this.descripcion_empresa = descripcion_empresa;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getNumeroTelefonico() {
        return NumeroTelefonico;
    }

    public void setNumeroTelefonico(String NumeroTelefonico) {
        this.NumeroTelefonico = NumeroTelefonico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }            
}
