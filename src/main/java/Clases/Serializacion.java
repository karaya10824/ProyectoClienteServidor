package Clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializacion {
    public Serializacion() {}    
    
    /*public void Serializar(votaciones Votaciones){            
        try(
            FileOutputStream fileSalida = new FileOutputStream("basedatos.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(Votaciones);
            
            Vserializador.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public votaciones DesSerializar(votaciones Votaciones){
        try(
            FileInputStream fileEntrada = new FileInputStream("basedatos.bd");
            ObjectInputStream Vserializador = new ObjectInputStream(fileEntrada);){
            
            Votaciones = (votaciones) Vserializador.readObject();
            
            Vserializador.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Votaciones;
    }*/    
    
}
