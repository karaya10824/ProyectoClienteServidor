package Clases;

import java.io.Serializable;

public class Pedidos implements Serializable{
    public int Numero_pedido;
    public String codigo_promocional;  
    public int cantidad;
    public Double precio_descuento;
    public Double subtotal;
    public String productosSeleccionados;
    public Double precioNeto;
    public Double precioComerciante;
    public String reseña;
    public int numeroEnvio;
    public boolean estado;

    public Pedidos() {
        this.precio_descuento = 0.0;
    }
    
    public Pedidos(int Numero_pedido, String codigo_promocional, int cantidad, Double precio_descuento, Double subtotal, String productosSeleccionados, Double precioNeto, String reseña, boolean estado) {
        this.Numero_pedido = Numero_pedido;
        this.codigo_promocional = codigo_promocional;
        this.cantidad = cantidad;
        this.precio_descuento = precio_descuento;
        this.subtotal = subtotal;
        this.productosSeleccionados = productosSeleccionados;
        this.precioNeto = precioNeto;
        this.reseña = reseña;
        this.estado = estado;
    }

    public Double getPrecioComerciante() {
        return precioComerciante;
    }

    public void setPrecioComerciante(Double precioComerciante) {
        this.precioComerciante = precioComerciante;
    }

    
    public int getNumeroEnvio() {
        return numeroEnvio;
    }

    public void setNumeroEnvio(int numeroEnvio) {
        this.numeroEnvio = numeroEnvio;
    }
    
    public int getNumero_pedido() {
        return Numero_pedido;
    }

    public void setNumero_pedido(int Numero_pedido) {
        this.Numero_pedido = Numero_pedido;
    }

    public String getCodigo_promocional() {
        return codigo_promocional;
    }

    public void setCodigo_promocional(String codigo_promocional) {
        this.codigo_promocional = codigo_promocional;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_descuento() {
        return precio_descuento;
    }

    public void setPrecio_descuento(Double precio_descuento) {
        this.precio_descuento = precio_descuento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setProductosSeleccionados(String productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }

    public Double getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(Double precioNeto) {
        this.precioNeto = precioNeto;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }        
}
