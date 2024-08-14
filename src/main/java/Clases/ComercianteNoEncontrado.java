package Clases;

import javax.swing.JOptionPane;

public class ComercianteNoEncontrado extends Exception{
    public ComercianteNoEncontrado(String string){
        super(string);
        JOptionPane.showMessageDialog(null, string);
    }
}
