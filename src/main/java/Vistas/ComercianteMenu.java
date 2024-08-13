package Vistas;

import Clases.Productos;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ComercianteMenu extends javax.swing.JFrame {
    //Variable para mantener la sesión del usuario que inicia sesión
    public int identificadorUsuario;
    
    //Variables para mostrar los productos del comerciante
    DefaultTableModel dtmForm = new DefaultTableModel();
    Productos misProductos = new Productos();

    public ComercianteMenu(int id) {                
        this.identificadorUsuario = id;
        initComponents();
        setModelo();
        setDatos();
    }

    private void setModelo(){
        String[] cabecera = {"ID", "Nombre","Precio","Categoria","Descripción"};
        dtmForm.setColumnIdentifiers(cabecera);
        tblProductos.setModel(dtmForm);
    }
    
    private void setDatos(){
        Object[] datos = new Object[dtmForm.getColumnCount()];
        ArrayList<Productos> NuevosproductosColeccion = misProductos.DesSerializar();
        
        for(Productos producto : NuevosproductosColeccion){
            if(producto.getId() == identificadorUsuario){
            datos[0] = producto.getId();
            datos[1] = producto.getNombre_producto();
            datos[2] = producto.getPrecio();
            datos[3] = producto.getCategoria_producto();
            datos[4] = producto.getDescipcion_producto();
            dtmForm.addRow(datos);
            }
        }    
        tblProductos.setModel(dtmForm);
    }
    
    public int getIdentificadorUsuario() {
        return identificadorUsuario;
    }

    public void setIdentificadorUsuario(int identificadorUsuario) {
        this.identificadorUsuario = identificadorUsuario;
    }

    public JSpinner getSpinnerAgregarPrecio() {
        return spinnerAgregarPrecio;
    }

    public void setSpinnerAgregarPrecio(JSpinner spinnerAgregarPrecio) {
        this.spinnerAgregarPrecio = spinnerAgregarPrecio;
    }

    public JSpinner getSpinnerEditarPrecio() {
        return spinnerEditarPrecio;
    }

    public void setSpinnerEditarPrecio(JSpinner spinnerEditarPrecio) {
        this.spinnerEditarPrecio = spinnerEditarPrecio;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JTextField getTxtAgregarCategoria() {
        return txtAgregarCategoria;
    }

    public void setTxtAgregarCategoria(JTextField txtAgregarCategoria) {
        this.txtAgregarCategoria = txtAgregarCategoria;
    }

    public JTextArea getTxtAgregarDescripcion() {
        return txtAgregarDescripcion;
    }

    public void setTxtAgregarDescripcion(JTextArea txtAgregarDescripcion) {
        this.txtAgregarDescripcion = txtAgregarDescripcion;
    }

    public JTextField getTxtAgregarNombre() {
        return txtAgregarNombre;
    }

    public void setTxtAgregarNombre(JTextField txtAgregarNombre) {
        this.txtAgregarNombre = txtAgregarNombre;
    }

    public JTextField getTxtCodPromo() {
        return txtCodPromo;
    }

    public void setTxtCodPromo(JTextField txtCodPromo) {
        this.txtCodPromo = txtCodPromo;
    }

    public JTextField getTxtEditarCategoria() {
        return txtEditarCategoria;
    }

    public void setTxtEditarCategoria(JTextField txtEditarCategoria) {
        this.txtEditarCategoria = txtEditarCategoria;
    }

    public JTextArea getTxtEditarDescripcion() {
        return txtEditarDescripcion;
    }

    public void setTxtEditarDescripcion(JTextArea txtEditarDescripcion) {
        this.txtEditarDescripcion = txtEditarDescripcion;
    }

    public JTextField getTxtEditarNombre() {
        return txtEditarNombre;
    }

    public void setTxtEditarNombre(JTextField txtEditarNombre) {
        this.txtEditarNombre = txtEditarNombre;
    }

    public JTextField getTxtEliminar() {
        return txtEliminar;
    }

    public void setTxtEliminar(JTextField txtEliminar) {
        this.txtEliminar = txtEliminar;
    }

    public JButton getBtnAgregarProducto() {
        return btnAgregarProducto;
    }

    public void setBtnAgregarProducto(JButton btnAgregarProducto) {
        this.btnAgregarProducto = btnAgregarProducto;
    }

    public JButton getBtnEditarProducto() {
        return btnEditarProducto;
    }

    public JButton getBtnBuscarProducto() {
        return btnBuscarProducto;
    }

    public void setBtnBuscarProducto(JButton btnBuscarProducto) {
        this.btnBuscarProducto = btnBuscarProducto;
    }

    public void setBtnEditarProducto(JButton btnEditarProducto) {
        this.btnEditarProducto = btnEditarProducto;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnModificarContacto() {
        return btnModificarContacto;
    }

    public void setBtnModificarContacto(JButton btnModificarContacto) {
        this.btnModificarContacto = btnModificarContacto;
    }

    public JButton getBtnModificarDevolu() {
        return btnModificarDevolu;
    }

    public void setBtnModificarDevolu(JButton btnModificarDevolu) {
        this.btnModificarDevolu = btnModificarDevolu;
    }    

    public JTextField getTxtBuscarProducto() {
        return txtBuscarProducto;
    }

    public void setTxtBuscarProducto(JTextField txtBuscarProducto) {
        this.txtBuscarProducto = txtBuscarProducto;
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarProducto = new javax.swing.JButton();
        btnAgregarCerrar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        txtAgregarCategoria = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblImagenes = new javax.swing.JLabel();
        txtAgregarNombre = new javax.swing.JTextField();
        spinnerAgregarPrecio = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAgregarDescripcion = new javax.swing.JTextArea();
        lblTituloDos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtEditarCategoria = new javax.swing.JTextField();
        lblCategoria1 = new javax.swing.JLabel();
        lblPrecio1 = new javax.swing.JLabel();
        lblDescripcion1 = new javax.swing.JLabel();
        lblImagenes1 = new javax.swing.JLabel();
        txtEditarNombre = new javax.swing.JTextField();
        spinnerEditarPrecio = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEditarDescripcion = new javax.swing.JTextArea();
        lblTituloDos1 = new javax.swing.JLabel();
        btnEditarProducto = new javax.swing.JButton();
        lblNombre1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnEliminarCerrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEliminar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblCodPromo = new javax.swing.JLabel();
        txtCodPromo = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnCodPromo = new javax.swing.JButton();
        btnCerrar4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnModificarContacto = new javax.swing.JButton();
        btnModificarDevolu = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Menú de Inventario | Comerciante");

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnAgregarCerrar.setText("Cerrar");
        btnAgregarCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCerrarActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre:");

        lblCategoria.setText("Categoria:");

        lblPrecio.setText("Precio:");

        lblDescripcion.setText("Descripción:");

        lblImagenes.setText("Imagenes:");

        txtAgregarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarNombreActionPerformed(evt);
            }
        });

        spinnerAgregarPrecio.setValue(000);

        txtAgregarDescripcion.setColumns(20);
        txtAgregarDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtAgregarDescripcion);

        lblTituloDos.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblTituloDos.setText("Agregar Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagenes)
                        .addContainerGap(643, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTituloDos)
                                .addGap(286, 286, 286))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregarProducto)
                                .addGap(26, 26, 26)
                                .addComponent(btnAgregarCerrar)
                                .addGap(237, 237, 237))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDescripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerAgregarPrecio))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloDos)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(txtAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(spinnerAgregarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagenes)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnAgregarCerrar))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar Producto", jPanel2);

        txtEditarCategoria.setText("xxxx");

        lblCategoria1.setText("Categoria:");

        lblPrecio1.setText("Precio:");

        lblDescripcion1.setText("Descripción:");

        lblImagenes1.setText("Imagenes:");

        txtEditarNombre.setText("xxxx");

        txtEditarDescripcion.setColumns(20);
        txtEditarDescripcion.setRows(5);
        txtEditarDescripcion.setText("xxxx");
        jScrollPane2.setViewportView(txtEditarDescripcion);

        lblTituloDos1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblTituloDos1.setText("Editar Producto");

        btnEditarProducto.setText("Editar Producto");
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        lblNombre1.setText("Nombre:");

        jLabel12.setText("Ingrese el nombre del producto que desea modificar:");

        btnBuscarProducto.setText("Buscar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTituloDos1)
                .addGap(298, 298, 298))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblDescripcion1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblPrecio1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerEditarPrecio))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCategoria1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEditarCategoria))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblNombre1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEditarNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(btnEditarProducto)
                                .addGap(58, 58, 58))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagenes1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProducto)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloDos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarProducto)
                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(txtEditarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria1)
                    .addComponent(txtEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio1)
                    .addComponent(spinnerEditarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnEditarProducto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDescripcion1)
                        .addGap(33, 33, 33)
                        .addComponent(lblImagenes1)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar Producto", jPanel3);

        btnEliminar.setText("Eliminar Producto");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEliminarCerrar.setText("Cerrar");
        btnEliminarCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCerrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        jLabel4.setText("Ingrese el nombre del producto que desea eliminar:");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("Eliminar Producto");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(btnEliminar)
                .addGap(26, 26, 26)
                .addComponent(btnEliminarCerrar)
                .addGap(0, 244, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(294, 294, 294))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEliminarCerrar))
                .addContainerGap(297, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eliminar Producto", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gestionar Pedidos", jPanel5);

        lblCodPromo.setText("Código Promocional:");

        lblTitulo.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblTitulo.setText("Promociones y Códigos Promocionales");

        btnCodPromo.setText("Agregar Promoción");
        btnCodPromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodPromoActionPerformed(evt);
            }
        });

        btnCerrar4.setText("Cerrar");
        btnCerrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodPromo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodPromo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCodPromo)
                        .addGap(26, 26, 26)
                        .addComponent(btnCerrar4))
                    .addComponent(lblTitulo))
                .addGap(226, 226, 226))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodPromo)
                    .addComponent(txtCodPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCodPromo)
                    .addComponent(btnCerrar4))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crear Promociones", jPanel1);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblProductos);

        jLabel6.setText("Mis Productos:");

        jLabel7.setText("Número de contacto:");

        jLabel8.setText("txtNumero");

        jLabel9.setText("Política de Devolución:");

        btnModificarContacto.setText("Modificar Contacto");
        btnModificarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarContactoActionPerformed(evt);
            }
        });

        btnModificarDevolu.setText("Modificar Devolución");
        btnModificarDevolu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDevoluActionPerformed(evt);
            }
        });

        jLabel10.setText("txtPoliticas");

        btnCerrarSesion.setText("Cerrar Sesión");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel11.setText("Mi Perfil");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(329, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion)
                    .addComponent(jLabel11))
                .addGap(272, 272, 272))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(212, 212, 212)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10))
                        .addComponent(btnModificarDevolu)
                        .addComponent(btnModificarContacto)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(122, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnModificarContacto)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnModificarDevolu)
                    .addGap(41, 41, 41)))
        );

        jTabbedPane1.addTab("Mi Perfil", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnAgregarCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCerrarActionPerformed

    }//GEN-LAST:event_btnAgregarCerrarActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed

    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCerrarActionPerformed
  
    }//GEN-LAST:event_btnEliminarCerrarActionPerformed

    private void btnModificarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarContactoActionPerformed

    }//GEN-LAST:event_btnModificarContactoActionPerformed

    private void btnModificarDevoluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDevoluActionPerformed

    }//GEN-LAST:event_btnModificarDevoluActionPerformed

    private void btnCodPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodPromoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCodPromoActionPerformed

    private void btnCerrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar4ActionPerformed

    }//GEN-LAST:event_btnCerrar4ActionPerformed

    private void txtAgregarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarNombreActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComercianteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComercianteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComercianteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComercianteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCerrar;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCerrar4;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCodPromo;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCerrar;
    private javax.swing.JButton btnModificarContacto;
    private javax.swing.JButton btnModificarDevolu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCategoria1;
    private javax.swing.JLabel lblCodPromo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcion1;
    private javax.swing.JLabel lblImagenes;
    private javax.swing.JLabel lblImagenes1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecio1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloDos;
    private javax.swing.JLabel lblTituloDos1;
    private javax.swing.JSpinner spinnerAgregarPrecio;
    private javax.swing.JSpinner spinnerEditarPrecio;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtAgregarCategoria;
    private javax.swing.JTextArea txtAgregarDescripcion;
    private javax.swing.JTextField txtAgregarNombre;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCodPromo;
    private javax.swing.JTextField txtEditarCategoria;
    private javax.swing.JTextArea txtEditarDescripcion;
    private javax.swing.JTextField txtEditarNombre;
    private javax.swing.JTextField txtEliminar;
    // End of variables declaration//GEN-END:variables
}
