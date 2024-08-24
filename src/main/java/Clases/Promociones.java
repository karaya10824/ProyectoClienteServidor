package Clases;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Promociones implements Serializable{
    public int id;
    public String codigoPromocional; 
    public int porcentajeDescuento;
    ArrayList<Promociones> promociones = new ArrayList<Promociones>();
    
    public Promociones(){
        this.porcentajeDescuento = 0;
    }
    
    public Promociones(int id, String codigoPromocional, int porcentajeDescuento) {
        this.id = id;
        this.codigoPromocional = codigoPromocional;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
    }

    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }        
    
    public ArrayList<Promociones> DesSerializarPromociones(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("promociones.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            promociones = (ArrayList) VDesSerializador.readObject();                           
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println(" Exception: "+ ex.getMessage());
        }
        return promociones;  
    }
    
     @Override
     public boolean equals (Object promocion) {
        Promociones producto_equal = (Promociones) promocion;
        return producto_equal.getCodigoPromocional().equals(this.codigoPromocional);
    }
}
