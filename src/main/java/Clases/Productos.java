package Clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Productos implements Serializable{
    //Variables para la información de cada producto.
    public int id;
    public String nombre_producto;
    public String descipcion_producto;
    public int precio;
    public String categoria_producto;
    //String imagen; FALTA AGREGAR ESTO AL CONSTRUCTOR, GETTER Y SETTER  
    ArrayList<Productos> productosColeccion = new ArrayList<Productos>();

    //Sección de Constructores de las Clases
    public Productos(){}
    
    public Productos(String nombre_producto, String descipcion_producto, int precio, String categoria_producto) {
        this.nombre_producto = nombre_producto;
        this.descipcion_producto = descipcion_producto;
        this.precio = precio;
        this.categoria_producto = categoria_producto;
    }
    //Fin de Constructores de las Clases
    
    //Sección de Getters y Setters
    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescipcion_producto() {
        return descipcion_producto;
    }

    public void setDescipcion_producto(String descipcion_producto) {
        this.descipcion_producto = descipcion_producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoria_producto() {
        return categoria_producto;
    }

    public void setCategoria_producto(String categoria_producto) {
        this.categoria_producto = categoria_producto;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }        
    //Fin de Getters y Setters
    
    //Método para obtener los productos de la colección    
    public ArrayList<Productos> DesSerializar(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("productos.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            productosColeccion = (ArrayList) VDesSerializador.readObject();                           
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println(" Exception: "+ ex.getMessage());
        }
        return productosColeccion;  
    }
    
    @Override
     public boolean equals (Object producto) {
        Productos producto_equal = (Productos) producto;
        return producto_equal.getNombre_producto().equals(this.nombre_producto);
    }
}
