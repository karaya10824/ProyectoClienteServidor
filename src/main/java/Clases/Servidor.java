package Clases;

import Controlador.ControladorComerciante;
import Controlador.ControladorMenuPrincipal;
import Vistas.IniciarSesion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Servidor extends Thread{
   //Variables de las colecciones (Productos y Promociones)
    ArrayList<Productos> productosColeccion = new ArrayList<Productos>();
    ArrayList<Promociones> promociones = new ArrayList<Promociones>();
    ArrayList<Productos> productosEncontrados = new ArrayList<Productos>();   
    ArrayList<CarritodeCompras> productosCarrito = new ArrayList<>();    
        
    Pedidos nuevoPedido = new Pedidos();          
    public JTextArea bitacora;
    
    public Servidor() {}    
    
    public Servidor(JTextArea bitacora) {
        this.bitacora = bitacora;
    }        
    
    public void run(){
        ServerSocket vSocketServidor;
        try {
            this.bitacora.append("Iniciando Servidor...\n");
            vSocketServidor = new ServerSocket(10579);
            while(true){
                Socket vClienteRecibido;
                this.bitacora.append("Esperando conexion...\n");
                vClienteRecibido = vSocketServidor.accept();                
                this.bitacora.append("Conexion recibida...\n");
                
                new Thread(new ClienteHilo(vClienteRecibido, bitacora)).start();
            }
        } catch (IOException ex) {
            System.out.print(this + "s" + ex.getMessage());
        } catch (Exception e) {
            System.out.print(this + "s" + e.getMessage());
        }
    }
    
    class ClienteHilo implements Runnable{
        private Socket vClienteRecibido;
        public JTextArea bitacora;
        
        public ClienteHilo(Socket socket, JTextArea bitacora){
            this.vClienteRecibido = socket;
            this.bitacora = bitacora;
        }
        
        public void run(){
            try{
                DataInputStream vCanal = new DataInputStream(vClienteRecibido.getInputStream());
                DataOutputStream vRespuesta = new DataOutputStream(vClienteRecibido.getOutputStream());
                ObjectOutputStream vRespuestaO = new ObjectOutputStream(vClienteRecibido.getOutputStream());
                ObjectInputStream vDeserializador = new ObjectInputStream(vClienteRecibido.getInputStream());
                
                while(true){                    
                    int numeroTransaccion = vCanal.readInt();

                    //Transacciones de comerciante
                    //1 = Agregar Producto
                    if(numeroTransaccion == 1){
                        this.bitacora.append("Método agregar producto\n");
                        Productos productoagregar = (Productos) vDeserializador.readObject();
                        agregarProducto(productoagregar);                        
                        vRespuesta.writeBoolean(true);
                    }
                    //2 = Editar Producto
                    else if(numeroTransaccion == 2){
                        this.bitacora.append("Método editar producto\n");
                        Productos modificado = (Productos) vDeserializador.readObject();
                        Productos viejo = (Productos) vDeserializador.readObject();   
                        boolean editado = editarProducto(modificado, viejo);
                        vRespuesta.writeBoolean(editado);
                    }
                    //3 = Eliminar Producto
                    else if(numeroTransaccion == 3){
                        this.bitacora.append("Método eliminar producto\n");
                        String palabraEliminar = vCanal.readUTF();
                        int eliminado = eliminarProducto(palabraEliminar);
                        this.bitacora.append(eliminado + "");
                        vRespuesta.writeInt(eliminado);
                    }
                    //4 = Iniciar Sesión
                    else if(numeroTransaccion == 4){                          
                        int idEncontrado = iniciarSesion(vCanal.readUTF(), vCanal.readUTF());
                        this.bitacora.append(idEncontrado + "");
                        if(idEncontrado != 0){
                           vRespuesta.writeInt(idEncontrado);
                        }else{
                           vRespuesta.writeInt(idEncontrado);
                        }
                    }
                    //5 = Registrar Comerciante
                    else if(numeroTransaccion == 5){
                        Comerciantes nuevoComerciante = (Comerciantes) vDeserializador.readObject();
                        System.out.print(nuevoComerciante.getCorreoElectronico());
                        registrarUsuarios(nuevoComerciante);
                    }
                    //6 = Crear Promociones
                    else if(numeroTransaccion == 6){
                        Promociones nuevaPromocion = (Promociones) vDeserializador.readObject();
                        CrearPromocion(nuevaPromocion);
                        vRespuesta.writeBoolean(true);
                    }
                    //7 = Modificar Contacto
                    else if(numeroTransaccion == 7){                        
                        modificarContacto(vCanal.readUTF(), vCanal.readInt());
                        vRespuesta.writeBoolean(true);
                    }
                    //8 = Modificar Direccion
                    else if(numeroTransaccion == 8){
                        modificarDireccion(vCanal.readUTF(), vCanal.readInt());
                        vRespuesta.writeBoolean(true);
                    }
                    //9 = Modificar o gestionar Pedidos desde el lado del comerciante
                    else if(numeroTransaccion == 9){
                        Pedidos nuevoPedido = (Pedidos) vDeserializador.readObject();

                        System.out.print("(" + nuevoPedido.getNumero_pedido() + " " + nuevoPedido.getNumeroEnvio() + " " + nuevoPedido.isEstado() + "\n");

                        if(nuevoPedido.isEstado()){
                            this.bitacora.append("Soy verdadero " + nuevoPedido.isEstado() + "\n");
                            agregarNumeroEnvio(nuevoPedido.getNumero_pedido(), nuevoPedido.getNumeroEnvio(), 1);
                        }else{
                            this.bitacora.append("Soy falso " + nuevoPedido.isEstado() + "\n");
                            agregarNumeroEnvio(nuevoPedido.getNumero_pedido(), nuevoPedido.getNumeroEnvio(), 0);
                        }
                        vRespuesta.writeBoolean(true);
                    }
                    
                    //Transacciones de comerciante
                    //10 = Mostrar Productos
                    else if(numeroTransaccion == 10){

                    }                    
                    //11 = Buscar Productos por nombre 
                    else if(numeroTransaccion == 11){
                        Productos RespuestaProductos = buscarProductosPorNombre(vCanal.readUTF());
                        vRespuestaO.writeObject(RespuestaProductos);                        
                    }
                    //12 = Buscar Productos por Categoria
                    else if(numeroTransaccion == 12){
                        Productos RespuestaProductos = buscarProductosPorCategoria(vCanal.readUTF());
                        vRespuestaO.writeObject(RespuestaProductos);  
                    }
                    //13 = Filtrar por Precio
                    else if(numeroTransaccion == 13){


                    }
                    //14 = Agregar productos al carrito
                    else if(numeroTransaccion == 14){


                    }
                    //15 = Aplicar promocion
                    else if(numeroTransaccion == 15){


                    }
                    //16 = Procesar Compra
                    else if(numeroTransaccion == 16){


                    }
                    //17 = Vaciar Carrito
                    else if(numeroTransaccion == 17){


                    }
                    //18 = Buscar Pedidos
                    else if(numeroTransaccion == 18){


                    }
                    //19 = Mostrar Pedidos por ID
                    else if(numeroTransaccion == 19){
                        Pedidos RespuestaProductos = mostrarPedidos(vCanal.readInt());
                        vRespuestaO.writeObject(RespuestaProductos); 
                    }
                    //20 = Mostrar Pedidos por Correo
                    else if(numeroTransaccion == 20){
                        Pedidos RespuestaProductos = mostrarPedidosCorreo(vCanal.readUTF());
                        vRespuestaO.writeObject(RespuestaProductos); 
                    }
                    //21
                    else if(numeroTransaccion == 21){


                    }   
                    
                    vCanal.close();
                    vRespuesta.close();
                    vRespuestaO.close();
                    vDeserializador.close();
                }                              
            }catch (IOException ex) {
               System.out.print(this + "s" + ex.getMessage());
            } catch (Exception e) {
               System.out.print(this + "s" + e.getMessage());
            }finally{
                try {         
                    vClienteRecibido.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }                              
        }        
    }
    
    //--Métodos y peticiones creadas por el comerciante
    //Método para agregar productos a la colección
    public void agregarProducto(Productos p){
        DesSerializar();         
        productosColeccion.add(p);        
        Serializar();    
    }
    
    //Método para eliminar productos a la colección
    public int eliminarProducto(String buscarProducto) throws ProductoNoEncontrado{
        productosColeccion = DesSerializar(); 
        int eliminado = 0;
        
        Productos nuevoProducto = new Productos();
        nuevoProducto.setNombre_producto(buscarProducto);
        
        if(productosColeccion.contains(nuevoProducto)){
            int index = productosColeccion.indexOf(nuevoProducto);
            productosColeccion.remove(productosColeccion.get(index));
            eliminado = 1;           
        }
        
        Serializar();                
        return eliminado;
    }
    
    //Método para editar productos a la colección
    public boolean editarProducto(Productos modificado, Productos viejo){
        productosColeccion = DesSerializar(); 
        boolean productoEncontrado = false;        
        
        if(productosColeccion.contains(viejo)){
            int index = productosColeccion.indexOf(viejo);
            
            productosColeccion.get(index).setNombre_producto(modificado.getNombre_producto());
            productosColeccion.get(index).setCategoria_producto(modificado.getCategoria_producto());
            productosColeccion.get(index).setDescipcion_producto(modificado.getDescipcion_producto());
            productosColeccion.get(index).setPrecio(modificado.getPrecio());
            productoEncontrado = true;
        }        
        Serializar(); 
        return productoEncontrado;
    }
    
    //Método para devolver busqueda de un producto
    public Productos devolverProductos(String ProductoEncontrar){
        DesSerializar();
        
        Productos productoEncontrado = null;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(ProductoEncontrar)){
                return productosColeccion.get(i);
            }
        }
        
        return productoEncontrado;
    }
    
    //Método para devolver busqueda de un producto para los clientes
    public Productos ClientesdevolverProductos(String ProductoEncontrar){
        DesSerializar();        
        Productos productoEncontrado = null;
        
        for (int i = 0; i < productosColeccion.size(); i++){
            if(productosColeccion.get(i).getNombre_producto().equals(ProductoEncontrar)){
                productoEncontrado = productosColeccion.get(i);
            }
        }              
                
        return productoEncontrado;
    }
    
    //Método para mostrar la información del comerciante
    public Comerciantes mostrarInformacion(int identificadorUsuario){ 
        Comerciantes NuevoComerciante = new Comerciantes();
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.comerciantes WHERE id = " + identificadorUsuario;
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                NuevoComerciante.setCorreoElectronico(datos.getString("correoElectronico"));          
                NuevoComerciante.setNombre_empresa(datos.getString("nombreEmpresa"));
                NuevoComerciante.setDescripcion_empresa(datos.getString("descripcion"));
                NuevoComerciante.setDireccion(datos.getString("direccionComercio"));
                NuevoComerciante.setNumeroTelefonico(datos.getString("NumeroContacto"));                 
            }   
            System.out.print(NuevoComerciante.getNombre_empresa());
            return NuevoComerciante;
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return NuevoComerciante;
    }       
    
    //Método para registrar comerciantes a la base de datos
    public void registrarUsuarios(Comerciantes nuevoComerciante){
        System.out.print(nuevoComerciante.getCorreoElectronico());
        try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "insert into proyectoClienteServidor.comerciantes (correoElectronico, contrasena, nombreEmpresa, descripcion, direccionComercio, NumeroContacto) values (?,?,?,?,?,?)";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, nuevoComerciante.getCorreoElectronico());
            comandoInsertPreparado.setString(2, nuevoComerciante.getContrasena());
            comandoInsertPreparado.setString(3, nuevoComerciante.getNombre_empresa());
            comandoInsertPreparado.setString(4, nuevoComerciante.getDescripcion_empresa());
            comandoInsertPreparado.setString(5, nuevoComerciante.getDireccion());
            comandoInsertPreparado.setString(6, nuevoComerciante.getNumeroTelefonico());

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito, inicie sesión");
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////Método para que los comerciantes inicien sesión
    public int iniciarSesion(String correoIngresado, String contrasenaIngresado) throws ComercianteNoEncontrado{
        String correoEncontrado = "";
        String contrasenaEncontrado = "";
        int idEncontrado = 0;
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.comerciantes WHERE correoElectronico = ?";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros
            comandoSelectPreparado.setString(1, correoIngresado);
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                idEncontrado = datos.getInt("id");
                correoEncontrado = datos.getString("correoElectronico");
                contrasenaEncontrado = datos.getString("contrasena");            
            }
            
            if(correoIngresado.equals(correoEncontrado) && correoIngresado.isBlank() == false){
                if(contrasenaIngresado.equals(contrasenaEncontrado) && idEncontrado != 0){                                   
                    idEncontrado = idEncontrado;
                    //System.out.print("Id: " + idEncontrado + "/nCorreo: " + correoEncontrado + "\n Contraseña: " + contrasenaEncontrado);
                }else{
                    idEncontrado = 0;
                }
            }else{
                idEncontrado = 0;
            }   
            //}
        } catch (SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
        
        return idEncontrado;
    }
    
    public ArrayList<Pedidos> obtenerMisPedidos(int id){        
        //Productos nombreEncontrar = new Productos();

        ArrayList<Pedidos> misPedidos = new ArrayList<Pedidos>();
        ArrayList<Productos> Productos = DesSerializar();

        String misproductos = "";
        Double nuevoPrecio = 0.0;
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.Pedidos";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                Pedidos pedidoEncontrado = new Pedidos();
                Productos nombreEncontrar = new Productos();
                
                pedidoEncontrado.setNumero_pedido(datos.getInt("id"));
                String productosEncontrados = datos.getString("productos");
                
                String[] partes = productosEncontrados.split(",");
                
                for(String parte : partes){
                    nombreEncontrar.setNombre_producto(parte);
                    if(Productos.contains(nombreEncontrar)){
                        int index = Productos.indexOf(nombreEncontrar);
                        if(Productos.get(index).getId() == id){
                            //System.out.print("encontrado" + nombreEncontrar.getNombre_producto());
                            misproductos += nombreEncontrar.getNombre_producto() + ", ";   
                            nuevoPrecio += Productos.get(index).getPrecio();
                            pedidoEncontrado.setProductosSeleccionados(misproductos);
                            pedidoEncontrado.setPrecioComerciante(nuevoPrecio);
                            pedidoEncontrado.setCodigo_promocional(datos.getString("CodigoPromocional"));
                            pedidoEncontrado.setEstado(datos.getBoolean("estadoPedido"));
                            pedidoEncontrado.setNumeroEnvio(datos.getInt("numeroEnvio"));
                            misPedidos.add(pedidoEncontrado);                              
                            break;
                        }                                               
                    }
                }
                
            }
        } catch (SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
        }    
        return misPedidos; 
    }
    
    public ArrayList<Clientes> obtenerClientes(int id){
        ArrayList<Clientes> clientes = new ArrayList<Clientes>();
        ArrayList<Pedidos> misPedidos = obtenerMisPedidos(id);

        String misproductos = "";
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelect = "SELECT * FROM proyectoClienteServidor.ClientesPedidos;";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);
        
            //Definimos los parametros            
            ResultSet datos = comandoSelectPreparado.executeQuery();

            
            while(datos.next()){
                Clientes clienteEncontrado = new Clientes();
                
                for(Pedidos pedido : misPedidos){
                   if(pedido.getNumero_pedido() == datos.getInt("id")){
                        clienteEncontrado.setNombreCompleto(datos.getString("nombreCompletoCliente"));
                        clienteEncontrado.setCorreoElectronico(datos.getString("correoElectronicoCliente"));
                        clienteEncontrado.setNumeroTelefonico(datos.getString("numeroTelefonoCliente"));
                        clienteEncontrado.setDireccion(datos.getString("direccionCliente"));
                        clientes.add(clienteEncontrado); 
                    } 
                }
            }                                    
            
        } catch (SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
        }            
        
        return clientes;               
    }
    
    public void agregarNumeroEnvio(int numeroPedido, int numeroEnvio, int Estado) throws SQLException{
        try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "update proyectoClienteServidor.Pedidos set numeroEnvio = ?, estadoPedido = ? where id = " + numeroPedido;
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, "" + numeroEnvio);
            comandoInsertPreparado.setString(2, "" + Estado);

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CrearPromocion(Promociones nuevaPromocion){
        DesSerializarPromociones();
        promociones.add(nuevaPromocion);        
        SerializarPromociones();
    }
        
    public String devolverNombreTienda(int id){
        String nombreEmpresa = "";
        
            try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoSelect = "SELECT nombreEmpresa FROM proyectoClienteServidor.comerciantes WHERE id = ?";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelect);

            //Definimos los parametros
            comandoSelectPreparado.setInt(1, id);

            //Ejecutamos el comando            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
            nombreEmpresa = datos.getString("nombreEmpresa");
            }
               
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        return nombreEmpresa;
    }
        
    public void modificarContacto(String nuevoContacto, int id){
        try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "update proyectoClienteServidor.comerciantes set NumeroContacto = ? where id = ?";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, nuevoContacto);
            comandoInsertPreparado.setInt(2, id);

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }   
    
    public void modificarDireccion(String nuevaDireccion, int id){
            try{
            Connection nuevaConexion = ConexionBD.Conexion();

            String comandoInsert = "update proyectoClienteServidor.comerciantes set direccioncomercio = ? where id = ?";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);

            //Definimos los parametros
            comandoInsertPreparado.setString(1, nuevaDireccion);
            comandoInsertPreparado.setInt(2, id);

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            System.out.print("Se ha ingresado el registro correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    //--Métodos y peticiones creadas por el cliente
    public Productos buscarProductosPorNombre(String NombreProducto) throws ProductoNoEncontrado{        
        ArrayList<Productos> productosEncontrados = DesSerializar();

        Productos productoEncontrado = null;
        
        Productos nuevoProducto = new Productos();
        nuevoProducto.setNombre_producto(NombreProducto);
        
        if(productosEncontrados.contains(nuevoProducto)){
            int index = productosEncontrados.indexOf(nuevoProducto);
            productoEncontrado = productosEncontrados.get(index);

        }else{            
            throw new ProductoNoEncontrado("El producto no éxiste");
        }
        
        return productoEncontrado;
    }
    
    public Productos buscarProductosPorCategoria(String NombreCategoria) throws ProductoNoEncontrado{
        ArrayList<Productos> productosEncontrados = DesSerializar();

        Productos productoEncontrado = null;
        
        Productos nuevoProducto = new Productos();
        nuevoProducto.setCategoria_producto(NombreCategoria);
        
        if(productosEncontrados.contains(nuevoProducto)){
            int index = productosEncontrados.indexOf(nuevoProducto);
            productoEncontrado = productosEncontrados.get(index);

        }else{            
            throw new ProductoNoEncontrado("El producto no éxiste");
        }
        
        return productoEncontrado;
    }
    
    public void buscarProductosPorPalabra(String palabraClave){}
    
    public ArrayList<CarritodeCompras> agregarAlCarrito(CarritodeCompras nuevoProducto){                                 
        productosCarrito.add(nuevoProducto);                
        return productosCarrito;
    }
    
    public Pedidos calcularTotalCarrito(ArrayList<CarritodeCompras> carrito){
        Double subtotal = 0.0;
       /* for(CarritodeCompras calcularCarrito : carrito){
            subtotal =+ (double) calcularCarrito.getProducto().getPrecio() * calcularCarrito.getCantidad();
        }*/
        for(CarritodeCompras calcularCarrito : carrito){
            subtotal = (double) subtotal + (calcularCarrito.getProducto().getPrecio() * calcularCarrito.getCantidad());        
        }
        
        nuevoPedido.setSubtotal(subtotal);    
        nuevoPedido.setPrecioNeto(nuevoPedido.getSubtotal() - (nuevoPedido.getSubtotal() * nuevoPedido.getPrecio_descuento() / 100));    
        return nuevoPedido;
    }
    
    public void AplicarDescuento(String CodigoPromocional){
        promociones = DesSerializarPromociones();
        
        Promociones aplicarPromocion = new Promociones();
        aplicarPromocion.setCodigoPromocional(CodigoPromocional);
        
        if(promociones.contains(aplicarPromocion)){
            int index = promociones.indexOf(aplicarPromocion);
            Double porcentajeDescuento = (double) promociones.get(index).getPorcentajeDescuento();
            nuevoPedido.setPrecio_descuento(porcentajeDescuento);  
            nuevoPedido.setPrecioNeto(nuevoPedido.getPrecioNeto() - (nuevoPedido.getPrecioNeto() * nuevoPedido.getPrecio_descuento() / 100));
        }else{
            System.out.print("Promocion no dispoinble");
        }
        
    }
    public ArrayList<CarritodeCompras> vaciarCarrito(){
        productosCarrito.clear();
        return productosCarrito;
    }
    
    public void ProcesarCompra(ArrayList<CarritodeCompras> carrito, Clientes cliente, Pedidos pedido){
        try{
            Connection nuevaConexion = ConexionBD.Conexion();
            String comandoInsert = "insert into proyectoClienteServidor.pedidos (CodigoPromocional, precioDescuento, precioBruto, precioNeto, productos, estadoPedido, idTienda) values (?,"+ pedido.getPrecio_descuento() +", "+ pedido.getSubtotal() + "," + pedido.getPrecioNeto() + ",?,?,?);";
            PreparedStatement comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsert);
            String productosLista = "";
            String idTienda = "";
            
            for(CarritodeCompras productosCarrito : carrito){
                productosLista += productosCarrito.getProducto().getNombre_producto() + ",";
            }
            
            for(CarritodeCompras productosCarrito : carrito){
                idTienda += productosCarrito.getProducto().getId() + ",";
            }
            
            //Definimos los parametros
            comandoInsertPreparado.setString(1, pedido.getCodigo_promocional());
            comandoInsertPreparado.setString(2, productosLista);
            comandoInsertPreparado.setString(3, "0");
            comandoInsertPreparado.setString(4, idTienda);

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();
            
            String comandoInsertCliente = "insert into proyectoClienteServidor.ClientesPedidos (nombreCompletoCliente, numeroTelefonoCliente, correoElectronicoCliente, direccionCliente) values (?, ?, ?, ?);";
            comandoInsertPreparado = nuevaConexion.prepareStatement(comandoInsertCliente);            
            
            //Definimos los parametros
            comandoInsertPreparado.setString(1, cliente.getNombreCompleto());
            comandoInsertPreparado.setString(2, cliente.getNumeroTelefonico());
            comandoInsertPreparado.setString(3, cliente.getCorreoElectronico());
            comandoInsertPreparado.setString(4, cliente.getDireccion());

            //Ejecutamos el comando
            comandoInsertPreparado.executeUpdate();

            //Mensaje final
            JOptionPane.showMessageDialog(null, "Pedido registrado con éxito");
            System.out.print("Se ha ingresado el pedido correctamente");    
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Pedidos mostrarPedidos(int correoONumero){
        Pedidos nuevoPedido = new Pedidos();
        
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelectPrimero = "SELECT * from proyectoClienteServidor.pedidos where id = " + correoONumero;
            //String comandoSelectPrimero = "SELECT FROM proyectoClienteServidor.pedidos UNION ALL SELECT correoElectronicoCliente FROM proyectoClienteServidor.ClientesPedidos; ";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelectPrimero);
        
            //Definimos los parametros
            
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                nuevoPedido.setNumeroEnvio(datos.getInt("numeroEnvio"));
                nuevoPedido.setNumero_pedido(datos.getInt("id"));
                nuevoPedido.setEstado(datos.getBoolean("estadoPedido"));
                nuevoPedido.setProductosSeleccionados(datos.getString("productos"));
            }   
            
           // String comandoSelectFinal = "SELECT * from proyectoClienteServidor.Pedidos where id = " + ;
            //PreparedStatement comandoSelect = nuevaConexion.prepareStatement(comandoSelectFinal);
        
            //Definimos los parametros                        
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return nuevoPedido;
    }
    
        public Pedidos mostrarPedidosCorreo(String correoONumero){
        Pedidos nuevoPedido = new Pedidos();
        try {
            //Conexión con la base de datos
            Connection nuevaConexion = ConexionBD.Conexion();
            
            //Comando
            String comandoSelectPrimero = "SELECT * FROM proyectoClienteServidor.ClientesPedidos WHERE correoElectronicoCliente = ?";
            PreparedStatement comandoSelectPreparado = nuevaConexion.prepareStatement(comandoSelectPrimero);
        
            //Definimos los parametros
            
            comandoSelectPreparado.setString(1, correoONumero);
            ResultSet datos = comandoSelectPreparado.executeQuery();
            
            while(datos.next()){
                nuevoPedido.setNumero_pedido(datos.getInt("id"));
            }   
            
            Connection nuevaConexionPedidos = ConexionBD.Conexion();
            
            String comandoSelectFinal = "SELECT * from proyectoClienteServidor.Pedidos where id = " + nuevoPedido.getNumero_pedido()+";";
            PreparedStatement comandoSelect = nuevaConexionPedidos.prepareStatement(comandoSelectFinal);
            
            ResultSet datosPedido = comandoSelect.executeQuery();
            
            while(datosPedido.next()){
                
                this.bitacora.append("" + datosPedido.getInt("numeroEnvio"));
                this.bitacora.append("" + datosPedido.getString("productos"));
                
                nuevoPedido.setNumeroEnvio(datosPedido.getInt("numeroEnvio"));
                nuevoPedido.setNumero_pedido(datosPedido.getInt("id"));
                nuevoPedido.setEstado(datosPedido.getBoolean("estadoPedido"));
                nuevoPedido.setProductosSeleccionados(datosPedido.getString("productos"));      
            }
            //Definimos los parametros                        
        }catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return nuevoPedido;
    }
    
    public static void mostrarInformacion(ArrayList<CarritodeCompras> Carrito ){
        
    }
    
    
    
    //Serilizadores y deserializadores
    public void Serializar(){
        try(                
            FileOutputStream fileSalida = new FileOutputStream("productos.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(productosColeccion);
            
            for (Productos Producto : productosColeccion){
                System.out.print("Producto: "+ Producto.getNombre_producto() + "\n");
            }
            
            fileSalida.close();
            Vserializador.close();
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException i) {
            System.out.print(i);
        }
    }
    
    //Método para obtener los productos de la colección
    public ArrayList<Productos> DesSerializar(){
        try {                   
            FileInputStream fileEntrada = new FileInputStream("productos.bd");
            ObjectInputStream VDesSerializador = new ObjectInputStream(fileEntrada);  
            
            productosColeccion = (ArrayList) VDesSerializador.readObject();                           
            fileEntrada.close();
            VDesSerializador.close();
        } catch (Exception ex) {
            System.out.println("Exception: "+ ex.getMessage());
        }
        return productosColeccion;  
    }
    
    //Método para guardar los productos de la colección
    public void SerializarPromociones(){
        try(                
            FileOutputStream fileSalida = new FileOutputStream("promociones.bd");
            ObjectOutputStream Vserializador = new ObjectOutputStream(fileSalida);){
            
            Vserializador.writeObject(promociones);
            
            for (Promociones promocion : promociones){
                System.out.print("ID: "+ promocion.getId() + "Promoción: " + promocion.getCodigoPromocional() + "\n");
            }
            
            fileSalida.close();
            Vserializador.close();
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException i) {
            System.out.print(i);
        }
    }
    
    //Método para obtener los productos de la colección
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
}
