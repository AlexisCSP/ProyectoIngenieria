/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;

/**
 *
 * @author alexis
 */
public class IModificarTour extends javax.swing.JFrame {

    /**
     * Creates new form IModificarTour
     */
    public IModificarTour() {
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

        GrupoDisponibilidad = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tour_seleccionado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nuevo_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        radio_disponible = new javax.swing.JRadioButton();
        radio_noDisponible = new javax.swing.JRadioButton();
        boton_atras = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");

        jLabel1.setText("Modificar Tour");

        jLabel2.setText("Nuevo Nombre");

        jLabel3.setText("Disponibilidad");

        GrupoDisponibilidad.add(radio_disponible);
        radio_disponible.setText("Disponible");

        GrupoDisponibilidad.add(radio_noDisponible);
        radio_noDisponible.setText("No Disponible");

        boton_atras.setText("Atras");

        boton_modificar.setText("Modificar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radio_disponible)
                            .addComponent(radio_noDisponible)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tour_seleccionado, 0, 406, Short.MAX_VALUE)
                            .addComponent(nuevo_nombre))))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(boton_atras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_modificar)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tour_seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nuevo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radio_disponible)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_noDisponible))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_atras)
                    .addComponent(boton_modificar))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(IModificarTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IModificarTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IModificarTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IModificarTour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IModificarTour().setVisible(true);
            }
        });
    }
    
    public void addBotonAtrasListener(ActionListener listenForBotonAtras) {
        boton_atras.addActionListener(listenForBotonAtras);
    }

    public void addBotonModificarListener(ActionListener listenForBotonModificar) {
        boton_modificar.addActionListener(listenForBotonModificar);
    }
    
    public void addComboBoxListener(ItemListener listenForComboBox) {
        tour_seleccionado.addItemListener(listenForComboBox);
    }
    
    
    public JComboBox<String> getComboBox() {
        return tour_seleccionado;
    }
    
    public String tourSeleccionado(){
        String x = tour_seleccionado.getSelectedItem().toString();
        return x;
    }
    
    public JRadioButton getRadioDisponible() {
        return radio_disponible;
    }
    
    public JRadioButton getRadioNoDisponible() {
        return radio_noDisponible;
    }
    
    public String nuevoNombre () {
        String nuevoNombre;
        nuevoNombre = nuevo_nombre.getText();
        return nuevoNombre;
    }
    
    public boolean getSelectedButton()
    {  
        for (Enumeration<AbstractButton> buttons = GrupoDisponibilidad.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    if("Disponible".equals(button.getText())){
                        return true;
                    }
                    if("No Disponible".equals(button.getText())){
                        return false;
                    }                 
                }
            }
        return false;
    }
    
    public void limpiar () {
        nuevo_nombre.setText("");
        GrupoDisponibilidad.clearSelection();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoDisponibilidad;
    private javax.swing.JButton boton_atras;
    private javax.swing.JButton boton_modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nuevo_nombre;
    private javax.swing.JRadioButton radio_disponible;
    private javax.swing.JRadioButton radio_noDisponible;
    private javax.swing.JComboBox<String> tour_seleccionado;
    // End of variables declaration//GEN-END:variables
}
