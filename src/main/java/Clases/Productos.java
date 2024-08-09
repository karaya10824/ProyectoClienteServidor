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
    
    //Método para guardar los productos de la colección
    public void Serializar(){
        try(                
            FileOutputStream fileSalida = new FileOutputStream("productos.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(productosColeccion);
            
            for (Productos Producto : productosColeccion){
                System.out.print("Producto: "+ Producto.getNombre_producto() + "\n");
            }
            
            fileSalida.close();
            Vserializador.close();
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException i) {
            System.out.print(i);
        }
    }
    
    //Método para obtener los productos de la colección
    public ArrayList<Productos> DesSerializar(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("productos.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            productosColeccion = (ArrayList) VDesSerializador.readObject();                           
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println("Exception: "+ ex.getMessage());
        }
        return productosColeccion;  
    }
    
    //Método para agregar productos a la colección
    public void agregarProducto(Productos p){
        DesSerializar();         
        productosColeccion.add(p);        
        Serializar();    
    }
    
    //Método para eliminar productos a la colección
    public void eliminarProducto(String buscarProducto) throws ProductoNoEncontrado{
        DesSerializar(); 
        boolean productoEncontrado = false;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(buscarProducto)){
                productosColeccion.remove(productosColeccion.get(i));
                productoEncontrado = true;
            }
        }
        
        if(productoEncontrado){
            JOptionPane.showMessageDialog(null, "El producto " + buscarProducto + " fue eliminado con éxito.");
        }else{
            throw new ProductoNoEncontrado("El producto " + buscarProducto + " no fue encontrado.");
        }
        Serializar();                
    }
    
    //Método para editar productos a la colección
    public boolean editarProducto(Productos modificado, Productos viejo){
        ArrayList<Productos> NuevosproductosColeccion = DesSerializar(); 
        boolean productoEncontrado = false;
        
        System.out.println("Nombre Viejo: " + viejo.getNombre_producto());
        
        System.out.print("Nombre nuevo: " + modificado.getNombre_producto());
        System.out.print("Descripcion nuevo: " + modificado.getDescipcion_producto() + "\n");
        
        //for (int i = 0; i < productosColeccion.size(); i++){
            if(NuevosproductosColeccion.contains(viejo)){
                //productosColeccion.get(i).setNombre_producto(modificado.getNombre_producto());
                //productosColeccion.get(i).setCategoria_producto(modificado.getCategoria_producto());
                //productosColeccion.get(i).setDescipcion_producto(modificado.getDescipcion_producto());
                //productosColeccion.get(i).setPrecio(modificado.getPrecio());
                productoEncontrado = true;
                JOptionPane.showMessageDialog(null, "Soy encontrado");
            }
            //JOptionPane.showMessageDialog(null, "Soy loop");
        //}        
        
        /*if(productoEncontrado){
            JOptionPane.showMessageDialog(null, "El producto " + buscarProducto + " fue eliminado con éxito.");
        }else{
            throw new ProductoNoEncontrado("El producto " + buscarProducto + " no fue encontrado.");
        }*/
        Serializar(); 
        if(productoEncontrado){
            return true;
        }else{
            return false;
        }            
    }
    
    //Método para devolver busqueda de un producto
    public Productos devolverProductos(){
        DesSerializar();
        String ProductoEncontrar = JOptionPane.showInputDialog(null, "Ingrese el producto a buscar:");  
        
        Productos productoEncontrado = null;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(ProductoEncontrar)){
                productoEncontrado = productosColeccion.get(i);
            }
        }
        
        return productoEncontrado;
    }
    
    //Método para devolver busqueda de un producto para los clientes
    public Productos ClientesdevolverProductos(String ProductoEncontrar){
        DesSerializar();        
        Productos productoEncontrado = null;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(ProductoEncontrar)){
                productoEncontrado = productosColeccion.get(i);
            }
        }              
                
        return productoEncontrado;
    }
}
