package Controlador;

import Clases.Comerciantes;
import Clases.Pedidos;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import Clases.Promociones;
import Clases.Servidor;
import static Controlador.ControladorMenuPrincipal.vistaRegistroComerciante;
import Vistas.ComercianteMenu;
import Vistas.IniciarSesion;
import Vistas.RegistrarComerciante;
import Vistas.modificarContacto;
import Vistas.modificarPoliticas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorComerciante implements ActionListener{
    public int identificadorUsuario;
    public ComercianteMenu vistaComerciante;    
    public static ControladorMenuPrincipal vistaPrincipal;
    
    public static modificarContacto vistaContacto;
    public static modificarPoliticas vistaDireccion;
    
    public static Comerciantes nuevoComerciante;
    public static Productos nuevoProducto;
    public static Promociones nuevaPromocion;
    public Pedidos nuevopedido;
    
    public Servidor servidorPeticiones;
    Socket vSocketCliente;
    DataOutputStream vCanal;
    ObjectOutputStream vSerializador;
    
    public ControladorComerciante(int id){
        this.identificadorUsuario = id;
        vistaComerciante = new ComercianteMenu(id);
        vistaPrincipal = new ControladorMenuPrincipal();
        vistaContacto = new modificarContacto();
        vistaDireccion = new modificarPoliticas();
        
        nuevoProducto = new Productos();
        nuevoComerciante = new Comerciantes();
        nuevaPromocion = new Promociones();
        nuevopedido = new Pedidos();
        
        servidorPeticiones = new Servidor();
        
        vistaComerciante.setDatos();
        MiPerfil(id);
        vistaComerciante.setDatosPedidos(servidorPeticiones.obtenerMisPedidos(id));
        vistaComerciante.setDatosClientes(servidorPeticiones.obtenerClientes(id));
        
        vistaComerciante.getBtnAgregarProducto().addActionListener(this);
        vistaComerciante.getBtnEliminar().addActionListener(this);
        vistaComerciante.getBtnBuscarProducto().addActionListener(this);
        vistaComerciante.getBtnEditarProducto().addActionListener(this);
        vistaComerciante.getBtnCerrarSesion().addActionListener(this);
        vistaComerciante.getBtnCodPromo().addActionListener(this);
        vistaComerciante.getBtnModificarContacto().addActionListener(this);
        vistaComerciante.getBtnModificarDevolu().addActionListener(this); 
        vistaComerciante.getBtnActualizarPedidos().addActionListener(this); 
        vistaComerciante.getBtnActualizar().addActionListener(this); 
        
        vistaContacto.getBtnEditarContacto().addActionListener(this);
        vistaDireccion.getBtnEditarDireccion().addActionListener(this);
    }    
    
    public void mostrarVentanaComerciante(){   
        vistaComerciante.setVisible(true);
    }    
    
    public void BuscarProducto(){
        String buscarProducto = vistaComerciante.getTxtBuscarProducto().getText();
        
        Productos modificarProducto = servidorPeticiones.devolverProductos(buscarProducto);
        
        if(modificarProducto != null){
            vistaComerciante.getTxtEditarNombre().setText(modificarProducto.getNombre_producto());
            vistaComerciante.getTxtEditarCategoria().setText(modificarProducto.getCategoria_producto());
            vistaComerciante.getSpinnerEditarPrecio().setValue(modificarProducto.getPrecio());
            vistaComerciante.getTxtEditarDescripcion().setText(modificarProducto.getDescipcion_producto()); 
        }else{
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }
    
    public void AgregarProducto(){
        int precio = (Integer) vistaComerciante.getSpinnerAgregarPrecio().getValue();
        
        nuevoProducto.setNombre_producto(vistaComerciante.getTxtAgregarNombre().getText());
        nuevoProducto.setDescipcion_producto(vistaComerciante.getTxtAgregarDescripcion().getText());
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria_producto(vistaComerciante.getTxtAgregarCategoria().getText());
        nuevoProducto.setId(identificadorUsuario);               
        
        boolean respuesta = false;
        
        try{   
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            vCanal.writeInt(1);
            vSerializador.writeObject(nuevoProducto);      
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();  
            vSerializador.close();
            
        } catch (IOException ex) {
            System.out.print("s" + ex);
        }       
        
        if(respuesta == true){
            JOptionPane.showMessageDialog(null, "El producto " + nuevoProducto.getNombre_producto() + " fue agregado con éxito.");
        }   
        
        vaciarEspacios();
    }    
    
    public void EditarProducto(){
        Productos productoOriginal = servidorPeticiones.devolverProductos(vistaComerciante.getTxtBuscarProducto().getText());

        int precio = (Integer) vistaComerciante.getSpinnerEditarPrecio().getValue();
        
        nuevoProducto.setNombre_producto(vistaComerciante.getTxtEditarNombre().getText());
        nuevoProducto.setDescipcion_producto(vistaComerciante.getTxtEditarDescripcion().getText());
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria_producto(vistaComerciante.getTxtEditarCategoria().getText());
        
        try {       
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            
            vCanal.writeInt(2);
            vSerializador.writeObject(nuevoProducto);    
            vSerializador.writeObject(productoOriginal);
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //servidorPeticiones.editarProducto(nuevoProducto, productoOriginal);
    }
    
    public void EliminarProducto() throws ProductoNoEncontrado{ 
        String buscarProducto = vistaComerciante.getTxtEliminar().getText();       
        boolean respuesta = false;
        
        try {   
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            
            vCanal.writeInt(3);
            vCanal.writeUTF(buscarProducto);
            
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();  
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(respuesta == true){
            JOptionPane.showMessageDialog(null, "El producto " + buscarProducto + " fue eliminado con éxito.");
        }else{
            throw new ProductoNoEncontrado("El producto " + buscarProducto + " no fue encontrado.");
        }
    }
    
    public void GestionarPedidos(){        
        int numeroPedido = Integer.parseInt(vistaComerciante.getTxtNumeroPedido().getText());
        int numeroEnvio = Integer.parseInt(vistaComerciante.getTxtNumeroRastreo().getText());       
        String estadoPedido = (String) vistaComerciante.getJcbEstadoEnvio().getSelectedItem(); 
        
        boolean respuesta = false;
        
        nuevopedido.setNumero_pedido(numeroPedido);                
        if(estadoPedido.equals("Enviado")){
            nuevopedido.setEstado(true);
        }else{
            nuevopedido.setEstado(false);
        }                
        nuevopedido.setNumeroEnvio(numeroEnvio);    
        
        System.out.print(nuevopedido.getNumero_pedido() + " " + nuevopedido.getNumeroEnvio() + " " + nuevopedido.isEstado());
        
        try{  
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            
            vCanal.writeInt(9);
            vSerializador.writeObject(nuevopedido);   
            
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();  
            
            vSerializador.close();
        } catch (IOException ex) {
            System.out.print("s" + ex);
        }       
        
        if(respuesta == true){
            JOptionPane.showMessageDialog(null, "Pedido Enviado con éxito");
        } else{
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        }                
        
        vistaComerciante.getTxtNumeroPedido().setText("");
        vistaComerciante.getTxtNumeroRastreo().setText("");        
    }
        
    public void actualizarPedidos(){
        vistaComerciante.setDatosPedidos(servidorPeticiones.obtenerMisPedidos(identificadorUsuario));
        vistaComerciante.setDatosClientes(servidorPeticiones.obtenerClientes(identificadorUsuario));
    }
    
    public void modificarContacto(){        
        String nuevoContacto = vistaContacto.getTxtNuevoContacto().getText();
        boolean respuesta = false;
        
        try {   
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            vCanal.writeInt(7);
            vCanal.writeUTF(nuevoContacto);
            vCanal.writeInt(identificadorUsuario);
            
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();   
            
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(respuesta == true){
            JOptionPane.showMessageDialog(null, "El contacto fue actualizado con éxito.");
            vistaComerciante.setTxtContacto(nuevoContacto);
        }else{
            JOptionPane.showMessageDialog(null, "El contacto no se pudo actualizar.");
        }
        vistaContacto.dispose();
    }
    
    public void modificarDireccion(){
        String nuevaDireccion = vistaDireccion.getTxtNuevaDireccion().getText();
        servidorPeticiones.modificarDireccion(nuevaDireccion, identificadorUsuario);
        
        boolean respuesta = false;
        
        try {   
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            vCanal.writeInt(8);
            vCanal.writeUTF(nuevaDireccion);
            vCanal.writeInt(identificadorUsuario);
            
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();  
            
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(respuesta == true){
            JOptionPane.showMessageDialog(null, "La dirección fue actualizado con éxito.");
            vistaComerciante.setTxtDireccionEmpresa(nuevaDireccion);
        }else{
            JOptionPane.showMessageDialog(null, "La dirección no se pudo actualizar.");
        }
        vistaDireccion.dispose();    
    }
    
    public void CrearPromociones(){
        int PorcentajeDescuento = (Integer) vistaComerciante.getSpinnerPorcentajeDescuento().getValue();
        
        nuevaPromocion.setId(identificadorUsuario);
        nuevaPromocion.setCodigoPromocional(vistaComerciante.getTxtCodPromo().getText());
        nuevaPromocion.setPorcentajeDescuento(PorcentajeDescuento);        
        boolean respuesta = false;
        
        try {      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            vCanal.writeInt(6);
            vSerializador.writeObject(nuevaPromocion);
            vistaComerciante.setDatos();
            
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readBoolean();   
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(respuesta){
            JOptionPane.showMessageDialog(null, "Promoción " + nuevaPromocion.getCodigoPromocional() + " fue agregado con éxito");
            vistaComerciante.setDatosPromociones();
        }
    }
    
    public void MiPerfil(int id){        
        Comerciantes comercianteEncontrado = servidorPeticiones.mostrarInformacion(id);
        
        vistaComerciante.setTxtNombreEmpresa(comercianteEncontrado.getNombre_empresa());
        vistaComerciante.setTxtCorreoElectronico(comercianteEncontrado.getCorreoElectronico());
        vistaComerciante.setTxtDescripcion(comercianteEncontrado.getDescripcion_empresa());
        vistaComerciante.setTxtContacto(comercianteEncontrado.getNumeroTelefonico());
        vistaComerciante.setTxtDireccionEmpresa(comercianteEncontrado.getDireccion());                        
    }
    
    public void vaciarEspacios(){
        vistaComerciante.getTxtAgregarNombre().setText("");
        vistaComerciante.getTxtAgregarCategoria().setText("");
        vistaComerciante.getSpinnerAgregarPrecio().setValue(0);
        vistaComerciante.getTxtAgregarDescripcion().setText("");
    }
    
    public void cerrarSesion(){        
        vistaPrincipal.mostrarVentanaMenuPrincipal();
        
        if(vSerializador != null){
            try {
                vSerializador.close();
                vCanal.close();
            } catch (IOException ex) {
                Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        vistaComerciante.dispose();   
        JOptionPane.showMessageDialog(null, "Sesión Cerrada"); 
    }
    
    public void crearCliente(){   
        Socket vSocketCliente;       

        try{            
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
;
        } catch (IOException ex) {
            System.out.print("s" + ex);
        }  
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaComerciante.getBtnAgregarProducto()){
            AgregarProducto();
            vistaComerciante.setDatos();
        }
        if(e.getSource() == vistaComerciante.getBtnEliminar()){
            try {
                EliminarProducto();
            } catch (ProductoNoEncontrado ex) {
                System.out.print("Producto no encontrado");
            }
            vistaComerciante.setDatos();
        }
        if(e.getSource() == vistaComerciante.getBtnBuscarProducto()){
            BuscarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnEditarProducto()){
            EditarProducto();            
            JOptionPane.showMessageDialog(null, "Producto modificado con éxito"); 
            vistaComerciante.setDatos();
        }
        if(e.getSource() == vistaComerciante.getBtnModificarContacto()){
            vistaContacto.setVisible(true);
        }
        if(e.getSource() == vistaComerciante.getBtnModificarDevolu()){
            vistaDireccion.setVisible(true);
        }
        if(e.getSource() == vistaComerciante.getBtnCerrarSesion()){
            cerrarSesion();
        }
        if(e.getSource() == vistaComerciante.getBtnCodPromo()){
            CrearPromociones();
        }
        if(e.getSource() == vistaComerciante.getBtnActualizarPedidos()){
            actualizarPedidos();
        }
        if(e.getSource() == vistaComerciante.getBtnActualizar()){
            GestionarPedidos();
        }
        
        //Botones para modificar información del cliente
        if(e.getSource() == vistaContacto.getBtnEditarContacto()){
            modificarContacto();
        }
        if(e.getSource() == vistaDireccion.getBtnEditarDireccion()){
            modificarDireccion();
        }        
        
    }
}