package Clases;

public class CarritodeCompras {
    public Productos producto;
    public int cantidad;

    public CarritodeCompras() {}

    public CarritodeCompras(Productos producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }            
}
