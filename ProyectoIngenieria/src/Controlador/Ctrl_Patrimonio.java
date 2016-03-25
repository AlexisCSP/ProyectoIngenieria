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

public class Ctrl_Patrimonio {
    // Vistas
    private final IAgregarTour agregar_tour;
    private final IAlerta alerta;
    private final IContrasena contrasena;
    private final IEliminarTour eliminar_tour;
    private final IModificarTour modificar_tour;
    private final IOpciones opciones;
    private final IRecorrerTour recorrer_tour;
    private IRol rol;
    private final ISeleccionarTour seleccionar_tour;
    // Modelos
    private ConjTourVirtuales conjunto_tours;
    private ConjPuntosInteres conjunto_puntos;
    
    
    public Ctrl_Patrimonio() {
        agregar_tour = new IAgregarTour();
        alerta = new IAlerta();
        contrasena = new IContrasena();
        eliminar_tour = new IEliminarTour();
        modificar_tour = new IModificarTour();
        opciones = new IOpciones();
        recorrer_tour = new IRecorrerTour();
        rol = new IRol();
        seleccionar_tour = new ISeleccionarTour();
        rol.addBotonVisitanteListener(new BotonVisitanteListener());
        rol.addBotonAdministradorListener(new BotonAdministradorListener());
        mostrarIRol();
    }
    
    class BotonVisitanteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarISeleccionarTour();
        }
        
    }
    
    class BotonAdministradorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarIContrasena();
        }
        
    }
    
    
    public void mostrarIRol() {
        rol.setVisible(true);
    }
    public void mostrarIContrasena() {
        contrasena.setVisible(true);
    }
    public void mostrarISeleccionarTour() {
        seleccionar_tour.setVisible(true);
    }
    
}
