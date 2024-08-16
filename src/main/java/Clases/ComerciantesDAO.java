package Clases;

import Controlador.ControladorComerciante;
import Vistas.IniciarSesion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ComerciantesDAO {    
    ArrayList<Productos> productosColeccion = new ArrayList<Productos>();
    ArrayList<Promociones> promociones = new ArrayList<Promociones>();

    public ComerciantesDAO() {}       
    
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
    
    //Método para guardar los productos de la colección
    public void SerializarPromociones(){
        try(                
            FileOutputStream fileSalida = new FileOutputStream("promociones.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(promociones);
            
            for (Promociones promocion : promociones){
                System.out.print("ID: "+ promocion.getId() + "Promoción: " + promocion.getCodigoPromocional() + "\n");
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
    public ArrayList<Promociones> DesSerializarPromociones(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("promociones.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            promociones = (ArrayList) VDesSerializador.readObject();                           
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println(" Exception: "+ ex.getMessage());
        }
        return promociones;  
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
        productosColeccion = DesSerializar(); 
        boolean productoEncontrado = false;        
        
        if(productosColeccion.contains(viejo)){
            int index = productosColeccion.indexOf(viejo);
            
            productosColeccion.get(index).setNombre_producto(modificado.getNombre_producto());
            productosColeccion.get(index).setCategoria_producto(modificado.getCategoria_producto());
            productosColeccion.get(index).setDescipcion_producto(modificado.getDescipcion_producto());
            productosColeccion.get(index).setPrecio(modificado.getPrecio());
            productoEncontrado = true;
            JOptionPane.showMessageDialog(null, "Producto Modificado");
        }else{
                JOptionPane.showMessageDialog(null, "No soy encontrado");
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
    public Productos devolverProductos(String ProductoEncontrar){
        DesSerializar();
        
        Productos productoEncontrado = null;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(ProductoEncontrar)){
                return productosColeccion.get(i);
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
    
    //Método para mostrar la información del comerciante
    public Comerciantes mostrarInformacion(int identificadorUsuario){ 
        Comerciantes NuevoComerciante = new Comerciantes();
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.comerciantes WHERE id = " + identificadorUsuario;
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                NuevoComerciante.setCorreoElectronico(datos.getString("correoElectronico"));          
                NuevoComerciante.setNombre_empresa(datos.getString("nombreEmpresa"));
                NuevoComerciante.setDescripcion_empresa(datos.getString("descripcion"));
                NuevoComerciante.setDireccion_empresa(datos.getString("direccionComercio"));
                NuevoComerciante.setContacto(datos.getString("NumeroContacto"));                 
            }   
            System.out.print(NuevoComerciante.getNombre_empresa());
            return NuevoComerciante;
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return NuevoComerciante;
    }
    
    //Método para registrar comerciantes a la base de datos
    public void registrarUsuarios(Comerciantes nuevoComerciante){
        try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "insert into proyectoClienteServidor.comerciantes (correoElectronico, contrasena, nombreEmpresa, descripcion, direccionComercio, NumeroContacto) values (?,?,?,?,?,?)";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, nuevoComerciante.getCorreoElectronico());
            comandoInsertPreparado.setString(2, nuevoComerciante.getContrasena());
            comandoInsertPreparado.setString(3, nuevoComerciante.getNombre_empresa());
            comandoInsertPreparado.setString(4, nuevoComerciante.getDescripcion_empresa());
            comandoInsertPreparado.setString(5, nuevoComerciante.getDireccion_empresa());
            comandoInsertPreparado.setString(6, nuevoComerciante.getContacto());

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito, inicie sesión");
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////Método para que los comerciantes inicien sesión
    public void iniciarSesion(String correoIngresado, String contrasenaIngresado) throws ComercianteNoEncontrado{
        String correoEncontrado = "";
        String contrasenaEncontrado = "";
        int idEncontrado = 0;
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
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
            }
            
            if(correoIngresado.equals(correoEncontrado) && correoIngresado.isBlank() == false){
                if(contrasenaIngresado.equals(contrasenaEncontrado) && idEncontrado != 0){
                    ControladorComerciante mc = new ControladorComerciante(idEncontrado);
                    mc.mostrarVentanaComerciante();
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                    //System.out.print("Id: " + idEncontrado + "/nCorreo: " + correoEncontrado + "\n Contraseña: " + contrasenaEncontrado);
                }else{
                    throw new ComercianteNoEncontrado("Contraseña inválida"); 
                }
            }else{
                throw new ComercianteNoEncontrado("Correo eléctrónico inválido"); 
            }         
            //}
        } catch (SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
        }           
    }
    
    public void CrearPromocion(Promociones nuevaPromocion){
        DesSerializarPromociones();
        promociones.add(nuevaPromocion);
        JOptionPane.showMessageDialog(null, "Promoción " + nuevaPromocion.getCodigoPromocional() + " fue agregado con éxito");
        SerializarPromociones();
    }
        
    public void modificarContacto(String nuevoContacto, int id){
        try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "update proyectoClienteServidor.comerciantes set NumeroContacto = ? where id = ?";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, nuevoContacto);
            comandoInsertPreparado.setInt(2, id);

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            JOptionPane.showMessageDialog(null, "Número de contacto actualizado con éxito");
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void modificarDireccion(String nuevoContacto, int id){
    
        
    }
}
