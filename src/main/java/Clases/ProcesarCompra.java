package Clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProcesarCompra extends Thread{ 
    Clientes cliente = new Clientes();
    Pedidos pedido;
    String correoCliente = "";
    
    public ProcesarCompra(String correo) {
        pedido = new Pedidos();
        this.correoCliente = correo;
    }
        
    @Override
    public void run(){
        try{
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelectCorreo = "SELECT * FROM proyectoClienteServidor.ClientesPedidos WHERE correoElectronicoCliente = ?;";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelectCorreo);
        
            comandoSelectPreparado.setString(1, correoCliente);
            //Definimos los parametros
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                pedido.setNumero_pedido(datos.getInt("id"));
            }   
            JOptionPane.showMessageDialog(null, pedido.getNumero_pedido());
            
            Connection nuevaConexionPedidos = ConexionBD.Conexion();
            
            String comandoSelectFinal = "SELECT * from proyectoClienteServidor.Pedidos where id = " + pedido.getNumero_pedido() + ";";
            PreparedStatement comandoSelect = nuevaConexionPedidos.prepareStatement(comandoSelectFinal);
            
            ResultSet datosPedido = comandoSelect.executeQuery();
            
            while(datosPedido.next()){                
                pedido.setProductosSeleccionados(datosPedido.getString("productos"));
                pedido.setEstado(datosPedido.getBoolean("estadoPedido"));                
            }                        
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }        
}
