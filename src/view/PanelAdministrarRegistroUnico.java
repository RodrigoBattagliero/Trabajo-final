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
public class PanelAdministrarRegistroUnico extends javax.swing.JPanel {

    /**
     * Creates new form PanelAdministrarRegistroUnico
     */
    public PanelAdministrarRegistroUnico() {
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroSolicitud = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPerfil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        btnAceptar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Registro único");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 11, -1, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, 577, 10));

        jLabel2.setText("N° de solicitud");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, -1, -1));

        txtNumeroSolicitud.setText("01-2015");
        txtNumeroSolicitud.setEnabled(false);
        add(txtNumeroSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 60, 100, -1));

        jLabel3.setText("Perfil");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 63, -1, -1));

        txtPerfil.setText("OGAGTD");
        txtPerfil.setEnabled(false);
        add(txtPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 60, 101, -1));

        jLabel4.setText("Fecha Entrada");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 63, -1, -1));

        txtFecha.setEnabled(false);
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 60, 98, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, 577, 10));

        jLabel5.setText("Observaciones");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 150, 488, -1));

        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 118, -1, -1));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "En verificación", "Aprobado", "Rechazado", "Imputable a la administración" }));
        add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 112, -1, -1));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 266, 577, 10));

        btnAceptar.setText("Aceptar");
        add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 287, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAceptar;
    public javax.swing.JComboBox cbxEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtNumeroSolicitud;
    public javax.swing.JTextArea txtObservaciones;
    public javax.swing.JTextField txtPerfil;
    // End of variables declaration//GEN-END:variables
}
