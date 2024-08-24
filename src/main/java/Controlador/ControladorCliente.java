package Controlador;

import Clases.CarritodeCompras;
import Clases.Clientes;
import Clases.Pedidos;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import Clases.Servidor;
import static Controlador.ControladorComerciante.vistaComerciante;
import static Controlador.ControladorMenuPrincipal.vistaPrincipal;
import Vistas.ClienteMenu;
import Vistas.ClienteProcesarPago;
import Vistas.ComercianteMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorCliente implements ActionListener{
    public ClienteMenu vistaCliente; 
    public Servidor servidorPeticiones;
    public ClienteProcesarPago vistaProcesarPago;
    
    public CarritodeCompras AgregarProductoCarrito;
    public ArrayList<CarritodeCompras> productosCarrito;
    
    public Clientes cliente;    
    public Pedidos nuevoPedido; 
    
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
          
        
        productosCarrito = servidorPeticiones.agregarAlCarrito(AgregarProductoCarrito);  
        mostrarInformacion(productosCarrito);             
    }
    
    public static void buscarProducto(){}
    
    public void mostrarInformacion(ArrayList<CarritodeCompras> carritoMetodos){
        nuevoPedido = servidorPeticiones.calcularTotalCarrito(carritoMetodos);
        //nuevoPedido = funcionesCliente.calcularTotalCarrito(carritoMetodos);
        vistaCliente.setDatosCarrito(carritoMetodos);
        
        
        vistaCliente.setLblTotal(nuevoPedido.getPrecioNeto());
        vistaCliente.setLblSubtotal(Double.toString(nuevoPedido.getPrecioNeto() - nuevoPedido.getPrecio_descuento()));
    }    
    
    public void aplicarDescuento(){
        System.out.print(vistaCliente.getTxtDescuento().getText());
        servidorPeticiones.AplicarDescuento(vistaCliente.getTxtDescuento().getText());
        
        nuevoPedido.setCodigo_promocional(vistaCliente.getTxtDescuento().getText());
        vistaCliente.setLblDescuento(Double.toString(nuevoPedido.getPrecioNeto() * nuevoPedido.getPrecio_descuento() / 100));
        vistaCliente.setLblSubtotal(Double.toString(nuevoPedido.getPrecioNeto() - (nuevoPedido.getPrecioNeto() * nuevoPedido.getPrecio_descuento() / 100)));
    }
    
    public void vaciarCarrito(){
        productosCarrito = servidorPeticiones.vaciarCarrito();
        mostrarInformacion(productosCarrito);  
    }
    
    public void procesarCompra(){
        cliente.setNombreCompleto(vistaProcesarPago.getTxtNombreCompleto().getText());
        cliente.setCorreoElectronico(vistaProcesarPago.getTxtCorreoCliente().getText());
        cliente.setDireccion(vistaProcesarPago.getTxtDireccion().getText());
        cliente.setNumeroTelefonico(vistaProcesarPago.getTxtNumeroTelefono().getText());
        
        servidorPeticiones.ProcesarCompra(productosCarrito, cliente, nuevoPedido);
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
            buscarProducto();
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
        if(e.getSource() == vistaProcesarPago.getBtnProcesarPago()){
            procesarCompra();
        }
        if(e.getSource() == vistaProcesarPago.getBtnCerrar()){
            vistaProcesarPago.dispose();
        }  
        
    }
}
