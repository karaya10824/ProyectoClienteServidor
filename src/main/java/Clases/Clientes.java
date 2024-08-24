package Clases;

public class Clientes extends Usuarios{
    public String nombreCompleto;
    public String direccionCliente;

    public Clientes() {}

    public Clientes(String nombreCompletoCliente, String NumeroTelefonico, String correoElectronico, String direccionCliente) {
        this.nombreCompleto = nombreCompletoCliente;
        this.NumeroTelefonico= NumeroTelefonico;
        this.correoElectronico = correoElectronico;
        this.direccionCliente = direccionCliente;
    }

    public String getNumeroTelefonico() {
        return NumeroTelefonico;
    }

    public void setNumeroTelefonico(String NumeroTelefonico) {
        this.NumeroTelefonico = NumeroTelefonico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
