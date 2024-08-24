package Clases;

import javax.swing.JOptionPane;

public class ProductoNoEncontrado extends Exception{
    public ProductoNoEncontrado(String string){
        super(string);
        JOptionPane.showMessageDialog(null, string);
    }
}
