package Controlador;

import Clases.CarritodeCompras;
import Clases.Clientes;
import Clases.Pedidos;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import Clases.Servidor;
import static Controlador.ControladorComerciante.nuevaPromocion;
import static Controlador.ControladorComerciante.vistaPrincipal;
import Vistas.ClienteMenu;
import Vistas.ClienteProcesarPago;
import Vistas.ComercianteMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ControladorCliente implements ActionListener{
    public ClienteMenu vistaCliente; 
    public Servidor servidorPeticiones;
    public ControladorMenuPrincipal vistaPrincipal;
    public ClienteProcesarPago vistaProcesarPago;
    
    public CarritodeCompras AgregarProductoCarrito;
    public ArrayList<CarritodeCompras> productosCarrito;
    
    public Clientes cliente;    
    public Pedidos nuevoPedido; 
    
    Socket vSocketCliente;
    DataOutputStream vCanal;
    ObjectOutputStream vSerializador;
    
    public ControladorCliente(){        
        vistaCliente = new ClienteMenu();
        vistaProcesarPago = new ClienteProcesarPago();
        
        productosCarrito = new ArrayList<>();        
        cliente = new Clientes();
        nuevoPedido = new Pedidos();
        servidorPeticiones = new Servidor();
        
        vistaCliente.getBtnAgregarProductoCarrito().addActionListener(this);
        vistaCliente.getBtnBuscarProducto().addActionListener(this);
        vistaCliente.getBtnDescuento().addActionListener(this);
        vistaCliente.getBtnProcesarCompra().addActionListener(this);
        vistaCliente.getBtnCarritoVacio().addActionListener(this);
        vistaCliente.getBtnSalir().addActionListener(this);
        vistaCliente.getBtnBuscarPedido().addActionListener(this);
        
        vistaProcesarPago.getBtnCerrar().addActionListener(this);
        vistaProcesarPago.getBtnProcesarPago().addActionListener(this);
        //vistaCliente.getBtnProcesarCompra().addActionListener(this);
    }
    
    public void mostrarVentanaCliente(){   
        vistaCliente.setVisible(true);
    } 
    
    public void agregarAlCarrito() throws ProductoNoEncontrado{        
        AgregarProductoCarrito = new CarritodeCompras();
        
        int cantidad = (Integer) vistaCliente.getTxtCantidad().getValue();
        
        AgregarProductoCarrito.setProducto(servidorPeticiones.buscarProductosPorNombre(vistaCliente.getTxtNombreProducto().getText()));    
        AgregarProductoCarrito.setCantidad(cantidad);    
                
        try{      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(14);
            vSerializador.writeObject(AgregarProductoCarrito);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
            productosCarrito = (ArrayList<CarritodeCompras>) vRespuesta.readObject();
            
            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //productosCarrito = servidorPeticiones.agregarAlCarrito(AgregarProductoCarrito);  
        mostrarInformacion(productosCarrito);             
    }
    
    public void buscarProductoNombre() {
        String NombreProducto = vistaCliente.getTxtBuscarProducto().getText();
        
        Productos respuesta;

        try {      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(11);
            vCanal.writeUTF(NombreProducto);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
            try { 
                respuesta = (Productos) vRespuesta.readObject();
                vistaCliente.setDatosBuscar(respuesta);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void buscarProductoCategoria(){
        String CategoriaProducto = vistaCliente.getTxtBuscarProducto().getText();
        
        Productos respuesta;

        try {      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(12);
            vCanal.writeUTF(CategoriaProducto);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
            try { 
                respuesta = (Productos) vRespuesta.readObject();
                vistaCliente.setDatosBuscar(respuesta);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }    
    
    public void mostrarPedidos(){        
        Pedidos respuesta;
        
        try {      
            int CorreoOid = Integer.parseInt(vistaCliente.getTxtCorreoOid().getText());
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(19);
            vCanal.writeInt(CorreoOid);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
                try { 
                    respuesta = (Pedidos) vRespuesta.readObject();
                    if(respuesta.getNumero_pedido() != 0){
                        vistaCliente.setDatosPedidos(respuesta);
                    }else{
                        JOptionPane.showMessageDialog(null, "Pedido no encontrado");
                    }      
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }                      

            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        }  
    }
    
    public void mostrarPedidosPorCorreo(){
        Pedidos respuesta;
        
        try {      
            String CorreoOid = vistaCliente.getTxtCorreoOid().getText();
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(20);
            vCanal.writeUTF(CorreoOid);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
                try { 
                    respuesta = (Pedidos) vRespuesta.readObject();
                    if(respuesta.getNumero_pedido() != 0){                        
                        vistaCliente.setDatosPedidos(respuesta);
                    }else{
                        JOptionPane.showMessageDialog(null, "Pedido no encontrado");
                    }      
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }                      

            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        }  
    }
    
    public void mostrarInformacion(ArrayList<CarritodeCompras> carritoMetodos){
        nuevoPedido = servidorPeticiones.calcularTotalCarrito(carritoMetodos);
        //nuevoPedido = funcionesCliente.calcularTotalCarrito(carritoMetodos);
        vistaCliente.setDatosCarrito(carritoMetodos);
        
        
        vistaCliente.setLblTotal(nuevoPedido.getPrecioNeto());
        vistaCliente.setLblSubtotal(Double.toString(nuevoPedido.getPrecioNeto() - nuevoPedido.getPrecio_descuento()));
    }    
    
    public void aplicarDescuento(){       
        int respuesta = 0;
        
        try{      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(15);
            vCanal.writeUTF(vistaCliente.getTxtDescuento().getText());
            vSerializador.writeObject(nuevoPedido);

            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            ObjectInputStream vRespuestaO = new ObjectInputStream(vSocketCliente.getInputStream());                        
            
            respuesta = vRespuesta.readInt();
            nuevoPedido = (Pedidos) vRespuestaO.readObject();                        
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(respuesta == 1){
            nuevoPedido.setCodigo_promocional(vistaCliente.getTxtDescuento().getText());
            vistaCliente.setLblDescuento(Double.toString(nuevoPedido.getPrecioNeto() * nuevoPedido.getPrecio_descuento() / 100));
            vistaCliente.setLblSubtotal(Double.toString(nuevoPedido.getPrecioNeto() - (nuevoPedido.getPrecioNeto() * nuevoPedido.getPrecio_descuento() / 100)));
            JOptionPane.showMessageDialog(null, "Promoción aplicada");
        }else{
            JOptionPane.showMessageDialog(null, "Promoción no disponible");
        }
        
        //servidorPeticiones.AplicarDescuento(vistaCliente.getTxtDescuento().getText());                
    }
    
    public void vaciarCarrito(){
        //productosCarrito = servidorPeticiones.vaciarCarrito();
        
        try{      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(17);

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
            productosCarrito = (ArrayList<CarritodeCompras>) vRespuesta.readObject();
            
            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        vistaCliente.setLblDescuento("0.00");
        vistaCliente.setLblTotal(0.00);
        mostrarInformacion(productosCarrito);  
    }
    
    public void procesarCompra(){
        cliente.setNombreCompleto(vistaProcesarPago.getTxtNombreCompleto().getText());
        cliente.setCorreoElectronico(vistaProcesarPago.getTxtCorreoCliente().getText());
        cliente.setDireccion(vistaProcesarPago.getTxtDireccion().getText());
        cliente.setNumeroTelefonico(vistaProcesarPago.getTxtNumeroTelefono().getText());
        Pedidos pedidoMostrar = new Pedidos();
        
        try {      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(16);
            vSerializador.writeObject(productosCarrito);
            vSerializador.writeObject(cliente);
            vSerializador.writeObject(nuevoPedido);

            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        } 
        JOptionPane.showMessageDialog(null, "Su pago ha sido procesado con éxito");
        
        try {      
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());

            vCanal.writeInt(21);
            vCanal.writeUTF(cliente.getCorreoElectronico());            

            ObjectInputStream vRespuesta = new ObjectInputStream(vSocketCliente.getInputStream());
            
            pedidoMostrar = (Pedidos) vRespuesta.readObject();//respuesta = vRespuesta.read

            vCanal.close();
            vSerializador.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        } catch (ClassNotFoundException ex) {          
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Número de envío: " + pedidoMostrar.getNumeroEnvio() + "\n" + "La información será enviada a su correo: " + cliente.getCorreoElectronico() + "\nProductos: " + pedidoMostrar.getProductosSeleccionados());
    }
    
    public void cerrarSesion(){     
        vistaPrincipal = new ControladorMenuPrincipal();
        vistaPrincipal.mostrarVentanaMenuPrincipal();
        if(vSerializador != null){
            try {
                vSerializador.close();
                vCanal.close();                                
            } catch (IOException ex) {
                Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        vistaCliente.dispose();   
        JOptionPane.showMessageDialog(null, "Bienvenido al menú principal"); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaCliente.getBtnAgregarProductoCarrito()){
            try {
                agregarAlCarrito();
                JOptionPane.showMessageDialog(null, "Producto agregado al carrito con éxito");
            } catch (ProductoNoEncontrado ex) {
                System.out.print("No fue posible encontrar el producto \n");
            }
        }   
        
        if(e.getSource() == vistaCliente.getBtnBuscarProducto()){
            ButtonModel selectedModel = vistaCliente.buttonGroup1.getSelection();
            
            if(selectedModel != null){
                String SelectedOption = selectedModel.getActionCommand();
                if(SelectedOption.equals("Nombre")){
                    buscarProductoNombre();                
                }else if(SelectedOption.equals("Categoria")){
                    buscarProductoCategoria();
                }
            }                       
        }   
        
        if(e.getSource() == vistaCliente.getBtnDescuento()){
            aplicarDescuento();
        }   
        
        if(e.getSource() == vistaCliente.getBtnCarritoVacio()){
            vaciarCarrito();
        } 
        
        if(e.getSource() == vistaCliente.getBtnProcesarCompra()){
            if(nuevoPedido.getPrecioNeto() == null){
                JOptionPane.showMessageDialog(null, "Carrito Vacío");
            }
            else{
                vistaProcesarPago.setVisible(true);
            }
        }  
        
        if(e.getSource() == vistaCliente.getBtnBuscarPedido()){
            ButtonModel selectedModel = vistaCliente.buttonGroup2.getSelection();
            
            if(selectedModel != null){
                String SelectedOption = selectedModel.getActionCommand();
                if(SelectedOption.equals("pedido")){
                    mostrarPedidos();                
                }else if(SelectedOption.equals("correoelectronico")){
                    mostrarPedidosPorCorreo();
                }
            }            
        }  
        
        if(e.getSource() == vistaCliente.getBtnSalir()){
            cerrarSesion();
        }  
        
        //Métodos para procesar las compras
        if(e.getSource() == vistaProcesarPago.getBtnProcesarPago()){
            procesarCompra();
        }
        if(e.getSource() == vistaProcesarPago.getBtnCerrar()){
            vistaProcesarPago.dispose();
        }  
        
    }
}
