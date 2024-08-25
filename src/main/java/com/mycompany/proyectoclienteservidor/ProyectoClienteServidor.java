package com.mycompany.proyectoclienteservidor;

import Controlador.ControladorMenuPrincipal;
import Vistas.IniciarSesion;

/*
Integrantes:
- Kevin Araya Jácamo
-
-
-
*/

public class ProyectoClienteServidor {

    public static void main(String[] args) {
        ControladorMenuPrincipal CmP = new ControladorMenuPrincipal();        
        CmP.mostrarVentanaMenuPrincipal();
        
        /*IniciarSesion is = new IniciarSesion();
        is.setVisible(true);*/
    }
}
