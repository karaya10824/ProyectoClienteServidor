package com.mycompany.proyectoclienteservidor;

import Controlador.ControladorMenuPrincipal;

/*
Integrantes:
- Kevin Araya Jácamo
- OVIEDO TRIGUEROS JAFET ARTURO
- REYES LORIA GABRIEL
-
*/

public class ProyectoClienteServidor {

    public static void main(String[] args) {
        ControladorMenuPrincipal CmP = new ControladorMenuPrincipal();        
        CmP.mostrarVentanaMenuPrincipal();
        
        /*IniciarSesion is = new IniciarSesion();
        is.setVisible(true);*/
        
//        ProcesarCompra pc = new ProcesarCompra("kevin87@gmail.com");
//        pc.start();
//        System.out.print(pc.getPedido().getNumero_pedido());
//        System.out.print(pc.getPedido().getProductosSeleccionados());
    }
}
