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
    public String nombre_producto;
    public String descipcion_producto;
    public int precio;
    public String categoria_producto;
    //String imagen; FALTA AGREGAR ESTO AL CONSTRUCTOR, GETTER Y SETTER  
    ArrayList<Productos> vagregarProductos = new ArrayList<Productos>();

    public Productos(){}
    
    public Productos(String nombre_producto, String descipcion_producto, int precio, String categoria_producto) {
        this.nombre_producto = nombre_producto;
        this.descipcion_producto = descipcion_producto;
        this.precio = precio;
        this.categoria_producto = categoria_producto;
    }

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
    
    public void Serializar(){
        try(                
            FileOutputStream fileSalida = new FileOutputStream("productos.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(vagregarProductos);
            
            Vserializador.close();
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException i) {
            System.out.print(i);
        }
    }
    
    public ArrayList<Productos> DesSerializar(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("productos.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            vagregarProductos = (ArrayList) VDesSerializador.readObject();                           
            for (Productos Producto : vagregarProductos){
                System.out.print("Producto: "+ Producto.getNombre_producto() + "\n");
            }
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println("Exception: "+ ex.getMessage());
        }
        return vagregarProductos;  
    }
    
    public void agregarProducto(Productos p){
        DesSerializar(); 
        vagregarProductos.add(p);
        Serializar();    
    }
    
    public boolean eliminarProducto(String buscarProducto){
        DesSerializar(); 
        String productoEncontrado = buscarProducto;
        boolean eliminado = false;
        
        for (int i = 0; i < vagregarProductos.size(); i++){
            if(vagregarProductos.get(i).getNombre_producto().equals(buscarProducto)){
                vagregarProductos.remove(vagregarProductos.get(i));
                eliminado = true;
            }
        }
       Serializar(); 
       
       if(eliminado == true){
            return true;
        }else{
            return false;
        }         
    }
    
    public boolean editarProducto(String buscarProducto){
        String productoEncontrado = "";
        
        for (Productos Producto : vagregarProductos){
            if(Producto.getNombre_producto().equals(buscarProducto)){
                productoEncontrado = Producto.getNombre_producto();
            }
        }
        
        if(productoEncontrado.equals(buscarProducto)){
                return true;
        }else{
            return false;
        }            
    }
}
