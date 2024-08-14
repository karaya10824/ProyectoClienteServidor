package Controlador;

import Clases.ComercianteNoEncontrado;
import Clases.Comerciantes;
import Clases.ComerciantesDAO;
import static Controlador.ControladorComerciante.nuevoComerciante;
import static Controlador.ControladorComerciante.vistaComerciante;
import Vistas.IniciarSesion;
import Vistas.ComercianteMenu;
import Vistas.RegistrarComerciante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorMenuPrincipal implements ActionListener{
    public IniciarSesion vistaPrincipal;
    public ComerciantesDAO funciones;
    public Comerciantes nuevocomerciante;
    
    public static RegistrarComerciante vistaRegistroComerciante;
    
    
    public ControladorMenuPrincipal(){
        vistaPrincipal = new IniciarSesion();
        funciones = new ComerciantesDAO();
        nuevocomerciante = new Comerciantes();
        vistaRegistroComerciante = new RegistrarComerciante();
        
        vistaRegistroComerciante.getBtnRegistrar().addActionListener(this);
        
        vistaPrincipal.getBtnIniciarSesion().addActionListener(this);
        vistaPrincipal.getBtnIrARegistro().addActionListener(this);
        vistaPrincipal.getBtnCliente().addActionListener(this);
    }
    
    public void mostrarVentanaMenuPrincipal(){   
        vistaPrincipal.setVisible(true);
    }    
    
    public void iniciarSesion() throws ComercianteNoEncontrado{
        String correoEncontrado = vistaPrincipal.getTxtCorreoElectronico().getText();
        String contrasenaEncontrado = vistaPrincipal.getTxtContrasena().getText();        
        funciones.iniciarSesion(correoEncontrado, contrasenaEncontrado);            
    }
    
    public void registrarComerciante(){
        nuevocomerciante.setCorreoElectronico(vistaRegistroComerciante.getTxtCorreoComerciante().getText());
        nuevocomerciante.setContrasena(vistaRegistroComerciante.getTxtContrasena().getText());
        nuevocomerciante.setNombre_empresa(vistaRegistroComerciante.getTxtNombreEmpresa().getText());
        nuevocomerciante.setDescripcion_empresa(vistaRegistroComerciante.getTxtDescripcion().getText());
        nuevocomerciante.setDireccion_empresa(vistaRegistroComerciante.getTxtDireccionComercio().getText());
        nuevocomerciante.setContacto(vistaRegistroComerciante.getTxtNumeroContacto().getText());
        funciones.registrarUsuarios(nuevocomerciante);
    }
    
    public void irComoCliente(){
        
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Método para enviar la vista de registrar al presionar el botón
        if(e.getSource() == vistaPrincipal.getBtnIrARegistro()){
            vistaRegistroComerciante.setVisible(true);            
        }
        
        //Método para iniciar sesión al presionar el botón
        if(e.getSource() == vistaPrincipal.getBtnIniciarSesion()){
            try {
                iniciarSesion();
                vistaPrincipal.dispose();
            } catch (ComercianteNoEncontrado ex) {
                Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                   
        //Método para iniciar sesión al presionar el botón
        if(e.getSource() == vistaPrincipal.getBtnCliente()){
            irComoCliente();
        }
        
        //Método para registrar un comerciante al presionar el botón
        if(e.getSource() == vistaRegistroComerciante.getBtnRegistrar()){
            registrarComerciante();
            vistaRegistroComerciante.dispose();
        }
    }
}
