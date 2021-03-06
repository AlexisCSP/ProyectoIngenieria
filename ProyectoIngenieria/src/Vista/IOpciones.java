/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionListener;

/**
 * Interfaz para seleccionar tipo de accion a realizar por el Comite
 * @author Equipo #4
 */
public class IOpciones extends javax.swing.JFrame {

    /**
     * Creates new form IOpciones
     */
    public IOpciones() {
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
        boton_anadir = new javax.swing.JButton();
        boton_eliminar = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();
        boton_volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comité de Obras de Arte");

        jLabel1.setText("Seleccione una opcion");

        boton_anadir.setText("Añadir Tour");

        boton_eliminar.setText("Eliminar Tour");

        boton_modificar.setText("Modificar Tour");

        boton_volver.setText("Volver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)
                        .addComponent(boton_anadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton_eliminar)
                        .addGap(0, 43, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boton_volver)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_modificar)
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boton_anadir)
                    .addComponent(boton_eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_modificar)
                .addGap(18, 18, 18)
                .addComponent(boton_volver)
                .addContainerGap(18, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(IOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IOpciones().setVisible(true);
            }
        });
    }
    
    /**
     * Agrega escuchador del evento de presionar el boton
     * @param listenForBotonAnadir
     */
    public void addBotonAnadirListener(ActionListener listenForBotonAnadir) {
        boton_anadir.addActionListener(listenForBotonAnadir);
    }

    /**
     * Agrega escuchador del evento de presionar el boton
     * @param listenForBotonEliminar
     */
    public void addBotonEliminarListener(ActionListener listenForBotonEliminar) {
        boton_eliminar.addActionListener(listenForBotonEliminar);
    }

    /**
     * Agrega escuchador del evento de presionar el boton
     * @param listenForBotonModificar
     */
    public void addBotonModificarListener(ActionListener listenForBotonModificar) {
        boton_modificar.addActionListener(listenForBotonModificar);
    }

    /**
     * Agrega escuchador del evento de presionar el boton
     * @param listenForBotonVolver
     */
    public void addBotonVolverListener(ActionListener listenForBotonVolver) {
        boton_volver.addActionListener(listenForBotonVolver);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_anadir;
    private javax.swing.JButton boton_eliminar;
    private javax.swing.JButton boton_modificar;
    private javax.swing.JButton boton_volver;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
