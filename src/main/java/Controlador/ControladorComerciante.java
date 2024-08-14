package Controlador;

import Clases.Comerciantes;
import Clases.ComerciantesDAO;
import Clases.ProductoNoEncontrado;
import Clases.Productos;
import static Controlador.ControladorMenuPrincipal.vistaRegistroComerciante;
import Vistas.ComercianteMenu;
import Vistas.IniciarSesion;
import Vistas.RegistrarComerciante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorComerciante implements ActionListener{
    public int identificadorUsuario;
    public static ComercianteMenu vistaComerciante;
    public static ControladorMenuPrincipal vistaPrincipal;
    
    public static Comerciantes nuevoComerciante;
    public static Productos nuevoProducto;
    
    public ComerciantesDAO funciones;
    
    public ControladorComerciante(int id) {
        this.identificadorUsuario = id;
        vistaComerciante = new ComercianteMenu(id);
        vistaPrincipal = new ControladorMenuPrincipal();
        
        nuevoProducto = new Productos();
        nuevoComerciante = new Comerciantes();                
        
        funciones = new ComerciantesDAO();
        
        MiPerfil(id);
        
        vistaComerciante.getBtnAgregarProducto().addActionListener(this);
        vistaComerciante.getBtnEliminar().addActionListener(this);
        vistaComerciante.getBtnBuscarProducto().addActionListener(this);
        vistaComerciante.getBtnEditarProducto().addActionListener(this);
        vistaComerciante.getBtnCerrarSesion().addActionListener(this);
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
        JOptionPane.showMessageDialog(null, "Sesión Cerrada");
        vistaComerciante.dispose();    
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
            EditarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnModificarDevolu()){
            EditarProducto();
        }
        if(e.getSource() == vistaComerciante.getBtnCerrarSesion()){
            cerrarSesion();
        }
    }
}
