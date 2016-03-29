/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public final class Ctrl_Patrimonio {
    // Vistas
    private IAgregarTour agregar_tour;
    private IAlerta alerta;
    private IContrasena contrasena;
    private IEliminarTour eliminar_tour;
    private IModificarTour modificar_tour;
    private IOpciones opciones;
    private IRecorrerTour recorrer_tour;
    private IRol rol;
    private ISeleccionarTour seleccionar_tour;
    // Modelos
    private ConjTourVirtuales conjunto_tours;
    private ConjPuntosInteres conjunto_puntos;
    
    
    public Ctrl_Patrimonio() throws IOException {
        instanciarVistas();
        centrarVistas();
        addListeners();
        agregarPuntosInteres();
        mostrarIRol();
    }
    
    private void agregarPuntosInteres() throws UnsupportedEncodingException, IOException {
        InputStream in = new FileInputStream(new File(getClass().getClassLoader().getResource("Resources/obras.txt").getFile()));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        conjunto_puntos = new ConjPuntosInteres();
        String datos;
        while ((datos = br.readLine()) != null) {
            System.out.println(datos);
            conjunto_puntos.addPuntoInteres(datos);
        }
    }
    
    class BotonVisitanteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIRol();
            mostrarISeleccionarTour();
        }     
    }
    
    class BotonAdministradorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIRol();
            mostrarIContrasena();
        }
    }
    
    class BotonAtrasListenerST implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarISeleccionarTour();
            mostrarIRol();
        }  
    }
    
    class BotonContinuarListenerST implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarISeleccionarTour();
            mostrarIRecorrer();
        }    
    }

    class BotonFinalizarListenerRT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIRecorrer();
            mostrarIRol();
        }  
    }
    
    class BotonContinuarListenerRT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar logica de busqueda siguiente punto
        }    
    }
    
    class BotonOkListenerIA implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIAlerta();
        }    
    }
    
    class BotonAtrasListenerIC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIContrasena();
            mostrarIRol();
        }  
    }
    
    class BotonContinuarListenerIC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // if contraseña correcta seguir
            // else atras o quedarse
            ocultarIContrasena();
            mostrarIOpciones();
        }    
    }

    class BotonAnadirListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIAgregarTour();
        }    
    }
    
    class BotonEliminarListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIEliminarTour();
        }    
    }
    
    class BotonModificarListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIModificarTour();
        }  
    }
    
    class BotonVolverListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIRol();
        }    
    }
    
    class BotonAtrasListenerMT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIModificarTour();
            mostrarIOpciones();
        }  
    }
    
    class BotonModificarListenerMT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIModificarTour();
            mostrarIOpciones();
        }    
    }
    
    class BotonAtrasListenerAT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIAgregarTour();
            mostrarIOpciones();
        }  
    }
    
    class BotonAnadirListenerAT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica para agregar tour
            ocultarIAgregarTour();
            mostrarIOpciones();
        }    
    }
    
    class BotonAtrasListenerET implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIEliminarTour();
            mostrarIOpciones();
        }  
    }
    
    class BotonEliminarListenerET implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica para eliminar tour
            ocultarIEliminarTour();
            mostrarIOpciones();
        }    
    }
    
    private void addListeners() {
        rol.addBotonVisitanteListener(new BotonVisitanteListener());
        rol.addBotonAdministradorListener(new BotonAdministradorListener());
        seleccionar_tour.addBotonAtrasListener(new BotonAtrasListenerST());
        seleccionar_tour.addBotonContinuarListener(new BotonContinuarListenerST());
        recorrer_tour.addBotonFinalizarListener(new BotonFinalizarListenerRT());
        recorrer_tour.addBotonContinuarListener(new BotonContinuarListenerRT());
        contrasena.addBotonAtrasListener(new BotonAtrasListenerIC());
        contrasena.addBotonContinuarListener(new BotonContinuarListenerIC());
        opciones.addBotonAnadirListener(new BotonAnadirListenerIO());
        opciones.addBotonEliminarListener(new BotonEliminarListenerIO());
        opciones.addBotonModificarListener(new BotonModificarListenerIO());
        opciones.addBotonVolverListener(new BotonVolverListenerIO());
        modificar_tour.addBotonAtrasListener(new BotonAtrasListenerMT());
        modificar_tour.addBotonModificarListener(new BotonModificarListenerMT());
        agregar_tour.addBotonAtrasListener(new BotonAtrasListenerAT());
        agregar_tour.addBotonAnadirListener(new BotonAnadirListenerAT());
        eliminar_tour.addBotonAtrasListener(new BotonAtrasListenerET());
        eliminar_tour.addBotonEliminarListener(new BotonEliminarListenerET());
        alerta.addBotonOKListener(new BotonOkListenerIA());
    }
    
    private void instanciarVistas() {
        agregar_tour = new IAgregarTour();
        alerta = new IAlerta();
        contrasena = new IContrasena();
        eliminar_tour = new IEliminarTour();
        modificar_tour = new IModificarTour();
        opciones = new IOpciones();
        recorrer_tour = new IRecorrerTour();
        rol = new IRol();
        seleccionar_tour = new ISeleccionarTour();
    }
    
    private void centrarVistas() {
        agregar_tour.pack();
        agregar_tour.setLocationRelativeTo(null);
        alerta.pack();
        alerta.setLocationRelativeTo(null);
        contrasena.pack();
        contrasena.setLocationRelativeTo(null);
        eliminar_tour.pack();
        eliminar_tour.setLocationRelativeTo(null);
        modificar_tour.pack();
        modificar_tour.setLocationRelativeTo(null);
        opciones.pack();
        opciones.setLocationRelativeTo(null);
        recorrer_tour.pack();
        recorrer_tour.setLocationRelativeTo(null);
        rol.pack();
        rol.setLocationRelativeTo(null);
        seleccionar_tour.pack();
        seleccionar_tour.setLocationRelativeTo(null);
    }

    private void mostrarIAgregarTour() {
        agregar_tour.setVisible(true);
    }
    private void mostrarIAlerta() {
        alerta.setVisible(true);
    }
    private void mostrarIContrasena() {
        contrasena.setVisible(true);
    }
    private void mostrarIEliminarTour() {
        eliminar_tour.setVisible(true);
    }
    private void mostrarIModificarTour() {
        modificar_tour.setVisible(true);
    }
    private void mostrarIOpciones() {
        opciones.setVisible(true);
    }
    private void mostrarIRecorrer() {
        recorrer_tour.setVisible(true);
    }
    private void mostrarIRol() {
        rol.setVisible(true);
    }
    private void mostrarISeleccionarTour() {
        seleccionar_tour.setVisible(true);
    }
    
    private void ocultarIAgregarTour() {
        agregar_tour.setVisible(false);
    }
    private void ocultarIAlerta() {
        alerta.setVisible(false);
    }
    private void ocultarIContrasena() {
        contrasena.setVisible(false);
    }
    private void ocultarIEliminarTour() {
        eliminar_tour.setVisible(false);
    }
    private void ocultarIModificarTour() {
        modificar_tour.setVisible(false);
    }
    private void ocultarIOpciones() {
        opciones.setVisible(false);
    }
    private void ocultarIRecorrer() {
        recorrer_tour.setVisible(false);
    }
    private void ocultarIRol() {
        rol.setVisible(false);
    }
    private void ocultarISeleccionarTour() {
        seleccionar_tour.setVisible(false);
    }
    
}
