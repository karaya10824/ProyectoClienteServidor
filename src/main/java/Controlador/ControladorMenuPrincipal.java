package Controlador;

import Clases.ComercianteNoEncontrado;
import Clases.Comerciantes;
import Clases.Servidor;
import static Controlador.ControladorComerciante.nuevoComerciante;
import static Controlador.ControladorComerciante.nuevoProducto;
import Vistas.ClienteMenu;
import Vistas.IniciarSesion;
import Vistas.ComercianteMenu;
import Vistas.RegistrarComerciante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorMenuPrincipal implements ActionListener{
    public Servidor servidor;
    public Comerciantes nuevocomerciante;
        
    public ControladorComerciante vistaComerciante;
    public static ControladorCliente vistaCliente;
    
    public IniciarSesion vistaPrincipal;
    public static RegistrarComerciante vistaRegistroComerciante;
    //public static ClienteMenu vistaCliente;
    Socket vSocketCliente;
    DataOutputStream vCanal;
    ObjectOutputStream vSerializador;
    
    public ControladorMenuPrincipal(){
        /*try {
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ControladorComerciante.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
        vistaPrincipal = new IniciarSesion();        
        
        servidor = new Servidor();
        nuevocomerciante = new Comerciantes();
                
        vistaRegistroComerciante = new RegistrarComerciante();
        
        
        
        vistaCliente = new ControladorCliente();  
        
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
        
        int respuesta = 0;
        
        try{          
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            vCanal.writeInt(4);
            vCanal.writeUTF(correoEncontrado);
            vCanal.writeUTF(contrasenaEncontrado);
                   
            DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            respuesta = vRespuesta.readInt();            
                        
            if(respuesta != 0){
                vistaComerciante = new ControladorComerciante(respuesta);
                vistaComerciante.mostrarVentanaComerciante();
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
            }else{
                throw new ComercianteNoEncontrado("No fue posible iniciar sesión"); 
            }                      
        } catch (IOException ex) {
            System.out.print("s" + ex);
        }           
        //servidor.iniciarSesion(correoEncontrado, contrasenaEncontrado);            
    }
    
    public void registrarComerciante(){
        nuevocomerciante.setCorreoElectronico(vistaRegistroComerciante.getTxtCorreoComerciante().getText());
        nuevocomerciante.setContrasena(vistaRegistroComerciante.getTxtContrasena().getText());
        nuevocomerciante.setNombre_empresa(vistaRegistroComerciante.getTxtNombreEmpresa().getText());
        nuevocomerciante.setDescripcion_empresa(vistaRegistroComerciante.getTxtDescripcion().getText());
        nuevocomerciante.setDireccion(vistaRegistroComerciante.getTxtDireccionComercio().getText());
        nuevocomerciante.setNumeroTelefonico(vistaRegistroComerciante.getTxtNumeroContacto().getText());
        int respuesta = 0;
        
        try {
            vSocketCliente = new Socket("localhost", 10579);
            vSerializador = new ObjectOutputStream(vSocketCliente.getOutputStream());
            vCanal = new DataOutputStream(vSocketCliente.getOutputStream());
            
            System.out.print(nuevocomerciante.getCorreoElectronico() + "\n");
            vCanal.writeInt(5);  
            vSerializador.writeObject(nuevocomerciante);  
            
            //DataInputStream vRespuesta = new DataInputStream(vSocketCliente.getInputStream());
            
            //respuesta = vRespuesta.readInt();    
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void limpiarPantallaRegistro(){
        vistaRegistroComerciante.getTxtCorreoComerciante().setText("");
        vistaRegistroComerciante.getTxtContrasena().setText("");
        vistaRegistroComerciante.getTxtNombreEmpresa().setText(""); 
        vistaRegistroComerciante.getTxtDescripcion().setText("");
        vistaRegistroComerciante.getTxtDireccionComercio().setText("");
        vistaRegistroComerciante.getTxtNumeroContacto().setText("");
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
                vCanal.close();
                vSerializador.close();              
            } catch (ComercianteNoEncontrado ex) {
                System.out.print("No fue posible iniciar sesión \n");
            }
            catch (IOException ex) {
                    Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                   
        //Método para iniciar sesión al presionar el botón
        if(e.getSource() == vistaPrincipal.getBtnCliente()){
            vistaCliente.mostrarVentanaCliente();
            vistaPrincipal.dispose();
        }
        
        //Método para registrar un comerciante al presionar el botón
        if(e.getSource() == vistaRegistroComerciante.getBtnRegistrar()){
            registrarComerciante();
            limpiarPantallaRegistro();
            vistaRegistroComerciante.dispose();
        }
    }
}
