/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author rodrigo
 */
public class VentanaSedeInterior extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    
    public VentanaSedeInterior() {
        initComponents();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mItemIniciarNuevaSolicitud = new javax.swing.JMenuItem();
        mItemSolicitudesACompletar = new javax.swing.JMenuItem();
        mAdministracion = new javax.swing.JMenu();
        mItemConfirmarLoteDeSolicitudesProcesadas = new javax.swing.JMenuItem();
        mItemListarHistorialDeSolicitudesProcesadas = new javax.swing.JMenuItem();
        mItemConsultarRegistroUnico = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu2.setText("Solicitudes");

        mItemIniciarNuevaSolicitud.setText("Iniciar nueva solicitud");
        jMenu2.add(mItemIniciarNuevaSolicitud);

        mItemSolicitudesACompletar.setText("Solicitudes a completar");
        jMenu2.add(mItemSolicitudesACompletar);

        jMenuBar1.add(jMenu2);

        mAdministracion.setText("Administración");

        mItemConfirmarLoteDeSolicitudesProcesadas.setText("Confirmar lote de solicitudes procesadas");
        mAdministracion.add(mItemConfirmarLoteDeSolicitudesProcesadas);

        mItemListarHistorialDeSolicitudesProcesadas.setText("Listar historlal de solicitudes procesadas");
        mAdministracion.add(mItemListarHistorialDeSolicitudesProcesadas);

        mItemConsultarRegistroUnico.setText("Consultar registro unico");
        mItemConsultarRegistroUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConsultarRegistroUnicoActionPerformed(evt);
            }
        });
        mAdministracion.add(mItemConsultarRegistroUnico);

        jMenuBar1.add(mAdministracion);

        jMenu3.setText("Usuario");

        menuItemSalir.setText("Salir");
        jMenu3.add(menuItemSalir);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mItemConsultarRegistroUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemConsultarRegistroUnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mItemConsultarRegistroUnicoActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaSedeInterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSedeInterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSedeInterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSedeInterior.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSedeInterior().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mAdministracion;
    public javax.swing.JMenuItem mItemConfirmarLoteDeSolicitudesProcesadas;
    public javax.swing.JMenuItem mItemConsultarRegistroUnico;
    public javax.swing.JMenuItem mItemIniciarNuevaSolicitud;
    public javax.swing.JMenuItem mItemListarHistorialDeSolicitudesProcesadas;
    public javax.swing.JMenuItem mItemSolicitudesACompletar;
    public javax.swing.JMenuItem menuItemSalir;
    // End of variables declaration//GEN-END:variables
}
