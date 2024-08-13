package Controlador;

import Clases.Comerciantes;
import Clases.ComerciantesDAO;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import Vistas.ComercianteMenu;
import Vistas.IniciarSesion;
import Vistas.RegistrarComerciante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorComerciante implements ActionListener{
    public int identificadorUsuario;
    public static ComercianteMenu vistaComerciante;   
    public static RegistrarComerciante vistaRegistroComerciante;
    
    public static Comerciantes nuevoComerciante;
    public static Productos nuevoProducto;
    
    public ComerciantesDAO funciones;
    
    public ControladorComerciante(int id) {
        this.identificadorUsuario = id;
        vistaComerciante = new ComercianteMenu(id);
        vistaRegistroComerciante = new RegistrarComerciante();
        
        nuevoProducto = new Productos();
        nuevoComerciante = new Comerciantes();
        
        funciones = new ComerciantesDAO();
        
        vistaComerciante.getBtnAgregarProducto().addActionListener(this);
        vistaComerciante.getBtnEliminar().addActionListener(this);
        vistaComerciante.getBtnBuscarProducto().addActionListener(this);
        vistaComerciante.getBtnEditarProducto().addActionListener(this);
        vistaRegistroComerciante.getBtnRegistrar().addActionListener(this);
    }    
    
    public void mostrarVentanaComerciante(){   
        vistaComerciante.setVisible(true);
    }    
    
    public void iniciarSesion(){}
    
    public void RegistrarComerciante(){
        nuevoComerciante.setCorreoElectronico(vistaRegistroComerciante.getTxtCorreoComerciante().getText());
        nuevoComerciante.setContrasena(vistaRegistroComerciante.getTxtContrasena().getText());
        nuevoComerciante.setNombre_empresa(vistaRegistroComerciante.getTxtNombreEmpresa().getText());
        nuevoComerciante.setDescripcion_empresa(vistaRegistroComerciante.getTxtDescripcion().getText());
        nuevoComerciante.setDireccion_empresa(vistaRegistroComerciante.getTxtDireccionComercio().getText());
        nuevoComerciante.setContacto(vistaRegistroComerciante.getTxtNumeroContacto().getText());
        funciones.registrarUsuarios(nuevoComerciante);
        vaciarEspacios();
    }
    
    public void AgregarProducto(){
        int precio = (Integer) vistaComerciante.getSpinnerAgregarPrecio().getValue();
        
        nuevoProducto.setNombre_producto(vistaComerciante.getTxtAgregarNombre().getText());
        nuevoProducto.setDescipcion_producto(vistaComerciante.getTxtAgregarDescripcion().getText());
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria_producto(vistaComerciante.getTxtAgregarCategoria().getText());
        nuevoProducto.setId(identificadorUsuario);
        
        funciones.agregarProducto(nuevoProducto); 
        vaciarEspacios();
    }
    
    public void BuscarProducto(){
        String buscarProducto = vistaComerciante.getTxtBuscarProducto().getText();
        
        Productos modificarProducto = funciones.devolverProductos(buscarProducto);
        
        vistaComerciante.getTxtEditarNombre().setText(modificarProducto.getNombre_producto());
        vistaComerciante.getTxtEditarCategoria().setText(modificarProducto.getCategoria_producto());
        vistaComerciante.getSpinnerEditarPrecio().setValue(modificarProducto.getPrecio());
        vistaComerciante.getTxtEditarDescripcion().setText(modificarProducto.getDescipcion_producto());        
    }
    
    public void EditarProducto(){
        Productos productoOriginal = funciones.devolverProductos(vistaComerciante.getTxtBuscarProducto().getText());

        int precio = (Integer) vistaComerciante.getSpinnerEditarPrecio().getValue();
        
        nuevoProducto.setNombre_producto(vistaComerciante.getTxtEditarNombre().getText());
        nuevoProducto.setDescipcion_producto(vistaComerciante.getTxtEditarDescripcion().getText());
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria_producto(vistaComerciante.getTxtEditarCategoria().getText());
        
        funciones.editarProducto(nuevoProducto, productoOriginal); 
    }
    
    public void EliminarProducto(){
        boolean eliminado = false;   
        String buscarProducto = vistaComerciante.getTxtEliminar().getText();
        
        try {
            funciones.eliminarProducto(buscarProducto);
        } catch (ProductoNoEncontrado ex) {
            System.out.print(ex);
        }
    }
    
    public void GestionarPedidos(){}
    
    public static void MiPerfil(){
    
    
    }
    
    public void vaciarEspacios(){
        vistaComerciante.getTxtAgregarNombre().setText("");
        vistaComerciante.getTxtAgregarCategoria().setText("");
        vistaComerciante.getSpinnerAgregarPrecio().setValue(0);
        vistaComerciante.getTxtAgregarDescripcion().setText("");
        vistaRegistroComerciante.getTxtCorreoComerciante().setText("");
        vistaRegistroComerciante.getTxtContrasena().setText("");
        vistaRegistroComerciante.getTxtNombreEmpresa().setText("");
        vistaRegistroComerciante.getTxtDescripcion().setText("");
        vistaRegistroComerciante.getTxtDireccionComercio().setText("");
        vistaRegistroComerciante.getTxtNumeroContacto().setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaRegistroComerciante.getBtnRegistrar()){
            RegistrarComerciante();        
        }
        if(e.getSource() == vistaComerciante.getBtnAgregarProducto()){
            AgregarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnEliminar()){
            EliminarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnBuscarProducto()){
            BuscarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnEditarProducto()){
            EditarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnEditarProducto()){
            EditarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnEditarProducto()){
            EditarProducto();
        }
    }
}
