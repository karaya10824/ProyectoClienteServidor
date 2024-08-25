package Vistas;

import Clases.ComercianteNoEncontrado;
import Clases.Comerciantes;
import Controlador.ControladorComerciante;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class IniciarSesion extends javax.swing.JFrame {    
    public IniciarSesion() {    
        initComponents();     
    }

    public JButton getBtnCliente() {
        return btnCliente;
    }

    public void setBtnCliente(JButton btnCliente) {
        this.btnCliente = btnCliente;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnIrARegistro() {
        return btnIrARegistro;
    }

    public void setBtnIrARegistro(JButton btnIrARegistro) {
        this.btnIrARegistro = btnIrARegistro;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JTextField getTxtCorreoElectronico() {
        return txtCorreoElectronico;
    }

    public void setTxtCorreoElectronico(JTextField txtCorreoElectronico) {
        this.txtCorreoElectronico = txtCorreoElectronico;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNombreEmpresa = new javax.swing.JLabel();
        txtCorreoElectronico = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        btnCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnIrARegistro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseExited(evt);
            }
        });
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("Menú de Inicio de Sesión para Comerciante");

        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNombreEmpresa.setText("Correo Eléctronico:");

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDescripcion.setText("Contraseña:");

        btnCliente.setText("Soy un Cliente");

        jLabel2.setText("¿Aún no tienes una cuenta como comerciante?");

        btnIrARegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIrARegistro.setText("Registrarme");
        btnIrARegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIrARegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIrARegistroMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("¡Bienvenido a FydePymeShop!");

        jLabel4.setText("Haz click en el botón para iniciar a comprar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(111, 111, 111))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreEmpresa)
                            .addComponent(lblDescripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCorreoElectronico)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnIniciarSesion))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnIrARegistro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel2)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCliente)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreEmpresa)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIrARegistro)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnIniciarSesionMouseEntered

    private void btnIniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnIniciarSesionMouseExited

    private void btnIrARegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIrARegistroMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIrARegistroMouseEntered

    private void btnIrARegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIrARegistroMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIrARegistroMouseExited

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IniciarSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnIrARegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreoElectronico;
    // End of variables declaration//GEN-END:variables
}
