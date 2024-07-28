package Clases;

public class Comerciantes {
    public String nombre_empresa;
    public String descripcion_empresa;
    public String direccion_empresa;
    public String contacto;

    public Comerciantes() {}
    
    public Comerciantes(String nombre_empresa, String descripcion_empresa, String direccion_empresa, String contacto) {
        this.nombre_empresa = nombre_empresa;
        this.descripcion_empresa = descripcion_empresa;
        this.direccion_empresa = direccion_empresa;
        this.contacto = contacto;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDescripcion_empresa() {
        return descripcion_empresa;
    }

    public void setDescripcion_empresa(String descripcion_empresa) {
        this.descripcion_empresa = descripcion_empresa;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    
    
    
}
