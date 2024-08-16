package Controlador;

import Clases.Comerciantes;
import Clases.ComerciantesDAO;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import Clases.Promociones;
import static Controlador.ControladorMenuPrincipal.vistaRegistroComerciante;
import Vistas.ComercianteMenu;
import Vistas.IniciarSesion;
import Vistas.RegistrarComerciante;
import Vistas.modificarContacto;
import Vistas.modificarPoliticas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorComerciante implements ActionListener{
    public int identificadorUsuario;
    public static ComercianteMenu vistaComerciante;
    public static ControladorMenuPrincipal vistaPrincipal;
    public static modificarContacto vistaContacto;
    public static modificarPoliticas vistaDireccion;
    
    public static Comerciantes nuevoComerciante;
    public static Productos nuevoProducto;
    public static Promociones nuevaPromocion;
    
    public ComerciantesDAO funciones;
    
    public ControladorComerciante(int id) {
        this.identificadorUsuario = id;
        vistaComerciante = new ComercianteMenu(id);
        vistaPrincipal = new ControladorMenuPrincipal();
        vistaContacto = new modificarContacto();
        vistaDireccion = new modificarPoliticas();
        
        nuevoProducto = new Productos();
        nuevoComerciante = new Comerciantes();
        nuevaPromocion = new Promociones();
        
        funciones = new ComerciantesDAO();
        
        MiPerfil(id);
        
        vistaComerciante.getBtnAgregarProducto().addActionListener(this);
        vistaComerciante.getBtnEliminar().addActionListener(this);
        vistaComerciante.getBtnBuscarProducto().addActionListener(this);
        vistaComerciante.getBtnEditarProducto().addActionListener(this);
        vistaComerciante.getBtnCerrarSesion().addActionListener(this);
        vistaComerciante.getBtnCodPromo().addActionListener(this);
        vistaComerciante.getBtnModificarContacto().addActionListener(this);
        vistaComerciante.getBtnModificarDevolu().addActionListener(this);       
        
        vistaContacto.getBtnEditarContacto().addActionListener(this);
        vistaDireccion.getBtnEditarDireccion().addActionListener(this);
    }    
    
    public static void mostrarVentanaComerciante(){   
        vistaComerciante.setVisible(true);
    }    
    
    public void AgregarProducto(){
        int precio = (Integer) vistaComerciante.getSpinnerAgregarPrecio().getValue();
        
        nuevoProducto.setNombre_producto(vistaComerciante.getTxtAgregarNombre().getText());
        nuevoProducto.setDescipcion_producto(vistaComerciante.getTxtAgregarDescripcion().getText());
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria_producto(vistaComerciante.getTxtAgregarCategoria().getText());
        nuevoProducto.setId(identificadorUsuario);
        
        funciones.agregarProducto(nuevoProducto); 
        vistaComerciante.setDatos();
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
        vistaComerciante.setDatos();
    }
    
    public void EliminarProducto(){
        boolean eliminado = false;   
        String buscarProducto = vistaComerciante.getTxtEliminar().getText();
        
        try {
            funciones.eliminarProducto(buscarProducto);
            vistaComerciante.setDatos();
        } catch (ProductoNoEncontrado ex) {
            System.out.print(ex);
        }
    }
    
    public void GestionarPedidos(){}
        
    public void modificarContacto(){        
        String nuevoContacto = vistaContacto.getTxtNuevoContacto().getText();
        funciones.modificarContacto(nuevoContacto, identificadorUsuario);
        vistaComerciante.setTxtContacto(nuevoContacto);
        vistaContacto.dispose();
    }
    
    public void modificarDireccion(){
        String nuevaDireccion = vistaDireccion.getTxtNuevaDireccion().getText();
        funciones.modificarDireccion(nuevaDireccion, identificadorUsuario);
        vistaComerciante.setTxtDireccionEmpresa(nuevaDireccion);
        vistaDireccion.dispose();    
    }
    
    public void CrearPromociones(){
        int PorcentajeDescuento = (Integer) vistaComerciante.getSpinnerPorcentajeDescuento().getValue();
        
        nuevaPromocion.setId(identificadorUsuario);
        nuevaPromocion.setCodigoPromocional(vistaComerciante.getTxtCodPromo().getText());
        nuevaPromocion.setPorcentajeDescuento(PorcentajeDescuento);
        
        funciones.CrearPromocion(nuevaPromocion);
        vistaComerciante.setDatosPromociones();
    }
    
    public void MiPerfil(int id){        
        Comerciantes comercianteEncontrado = funciones.mostrarInformacion(id);
        
        vistaComerciante.setTxtNombreEmpresa(comercianteEncontrado.getNombre_empresa());
        vistaComerciante.setTxtCorreoElectronico(comercianteEncontrado.getCorreoElectronico());
        vistaComerciante.setTxtDescripcion(comercianteEncontrado.getDescripcion_empresa());
        vistaComerciante.setTxtContacto(comercianteEncontrado.getContacto());
        vistaComerciante.setTxtDireccionEmpresa(comercianteEncontrado.getDireccion_empresa());                        
    }
    
    public void vaciarEspacios(){
        vistaComerciante.getTxtAgregarNombre().setText("");
        vistaComerciante.getTxtAgregarCategoria().setText("");
        vistaComerciante.getSpinnerAgregarPrecio().setValue(0);
        vistaComerciante.getTxtAgregarDescripcion().setText("");
    }
    
    public void cerrarSesion(){
        vistaPrincipal.mostrarVentanaMenuPrincipal();
        vistaComerciante.dispose();   
        JOptionPane.showMessageDialog(null, "Sesión Cerrada"); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
        
        //Botones para modificar información del cliente
        if(e.getSource() == vistaContacto.getBtnEditarContacto()){
            modificarContacto();
        }
        if(e.getSource() == vistaDireccion.getBtnEditarDireccion()){
            modificarDireccion();
        }
        
    }
}