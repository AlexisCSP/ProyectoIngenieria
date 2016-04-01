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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

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
    private int numTours;
    private int totalPuntosCJ;
    // Modelos
    private ConjTourVirtuales conjunto_tours;
    private ConjPuntosInteres conjunto_puntos;
    
    
    public Ctrl_Patrimonio() throws IOException {
        numTours=0;
        totalPuntosCJ=0;
        conjunto_tours = new ConjTourVirtuales ();
        conjunto_puntos = new ConjPuntosInteres();
        instanciarVistas();
        centrarVistas();
        addListeners();
        agregarPuntosInteres();
        mostrarIRol();
    }
    
    private void agregarPuntosInteres() throws UnsupportedEncodingException, IOException {
        try (InputStream in = new FileInputStream(new File(getClass().getClassLoader().getResource("data/obras.txt").getFile()));) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String datos;
            while ((datos = br.readLine()) != null) {
                System.out.println(datos);
                conjunto_puntos.addPuntoInteres(datos);
                totalPuntosCJ++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
    private void modificarTour(){
        String tourViejo=modificar_tour.tourSeleccionado();
        String nuevoNombre= modificar_tour.nuevoNombre();
        int contador=0;
        boolean disponibilidad = modificar_tour.getSelectedButton();
        boolean Repetido = conjunto_tours.nombreExiste(nuevoNombre);
        if(numTours > 0){
            if(Repetido == false && !"".equals(nuevoNombre)){
                while((conjunto_tours.getnombre(contador) == null ? tourViejo != null : !conjunto_tours.getnombre(contador).equals(tourViejo)) && contador <=numTours){
                    contador++;
                }
                if(conjunto_tours.getnombre(contador) == null ? tourViejo == null : conjunto_tours.getnombre(contador).equals(tourViejo)){
                   conjunto_tours.Tour(contador).setDisponibilidad(disponibilidad);
                   conjunto_tours.Tour(contador).setNombre(nuevoNombre);
                } 
            }else{
                System.out.println("Error");               //ACA DEBERIAMOS MOSTRAR UNA PESTAÑA DE ERROR
            }
        }
    }
    
    
    private void eliminarTour(){
       String nombreTour;
       nombreTour= eliminar_tour.tourSeleccionado();
       int contador=0;
       while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
           contador++;
       }
       if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
          conjunto_tours.deleteTour(contador);
          numTours = numTours - 1;
          TourVirtual.decCantTours();
       }
   }
    
    
    private void agregarTourCJ() throws UnsupportedEncodingException, IOException {
         
            String nombreArchivo;
            String nombreTourVirtual;
            String lecturaCJ;
            int iCJ=0;
            boolean existeElNombre=true;
            ArrayList<PuntoInteres> ListaPuntosInteresDeTour;
            ListaPuntosInteresDeTour = new ArrayList<>();
            nombreArchivo = agregar_tour.nombreDelArchivo();
            nombreTourVirtual = agregar_tour.nombreTourVirtual();
           
            try (InputStream reCJ = new FileInputStream(new File(getClass().getClassLoader().getResource("data/" + nombreArchivo).getFile()));){
                BufferedReader readCJ= new BufferedReader(new InputStreamReader(reCJ, "UTF-8"));
                
                if (numTours==0) {
                conjunto_tours.addTourVirtual();
                conjunto_tours.agregarNombre(numTours, nombreTourVirtual);
                
                while ((lecturaCJ = readCJ.readLine()) != null) {
                String[] parts = lecturaCJ.split("#", -1);
                lecturaCJ = parts[1];
                iCJ=0;       
                    while (iCJ<totalPuntosCJ && (lecturaCJ == null ? conjunto_puntos.IDActual(iCJ) != null : !lecturaCJ.equals(conjunto_puntos.IDActual(iCJ)))) {
                        iCJ++;
                    }
                    
                if (iCJ<totalPuntosCJ){
                ListaPuntosInteresDeTour.add(conjunto_puntos.getPuntoActual(iCJ));
                
                
                }
                }
                
                conjunto_tours.guardarPuntosInteresTour(numTours , ListaPuntosInteresDeTour);
                
                }else{
                conjunto_tours.addTourVirtual();
                conjunto_tours.agregarNombre(numTours, nombreTourVirtual);
                
                while ((lecturaCJ = readCJ.readLine()) != null) {
                String[] parts = lecturaCJ.split("#", -1);
                lecturaCJ = parts[1];
                iCJ=0;       
                    while (iCJ<totalPuntosCJ && (lecturaCJ == null ? conjunto_puntos.IDActual(iCJ) != null : !lecturaCJ.equals(conjunto_puntos.IDActual(iCJ)))) {
                        iCJ++;
                    }
                    
                if (iCJ<totalPuntosCJ){
                ListaPuntosInteresDeTour.add(conjunto_puntos.getPuntoActual(iCJ));
                
                
                }
                }
                
                conjunto_tours.guardarPuntosInteresTour(numTours , ListaPuntosInteresDeTour);
                
                }
                numTours++;
                
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Ctrl_Patrimonio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void setRadioButton(String nombreTour) {
       int contador=0;
       while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
           contador++;
       }
       if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
          boolean disponible = conjunto_tours.getDisponibilidad(contador);
          if (disponible) {
              modificar_tour.getRadioDisponible().setSelected(true);
          } else {
              modificar_tour.getRadioNoDisponible().setSelected(true);
          }
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
            modificarTour();
            ocultarIModificarTour();
            mostrarIOpciones();
        }    
    }
    
    class ComboBoxListenerMT implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String nombreTour = String.valueOf(modificar_tour.getComboBox().getSelectedItem());
                setRadioButton(nombreTour);
            }
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
            try {
                agregarTourCJ();
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Patrimonio.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            eliminarTour();
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
        modificar_tour.addComboBoxListener(new ComboBoxListenerMT());
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
        // determinar si llevarlo en ctrl o en la clase tour virtual
        agregar_tour.getLabelID().setText(Integer.toString(TourVirtual.getCantTours()));
        agregar_tour.setVisible(true);
    }
    private void mostrarIAlerta() {
        alerta.setVisible(true);
    }
    private void mostrarIContrasena() {
        contrasena.setVisible(true);
    }
    private void mostrarIEliminarTour() {
        eliminar_tour.getComboBox().setModel(new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray()));
        eliminar_tour.setVisible(true);
    }
    private void mostrarIModificarTour() {
        modificar_tour.getComboBox().setModel(new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray()));
        String nombreTour = String.valueOf(modificar_tour.getComboBox().getSelectedItem());
        if (modificar_tour.getComboBox().getSelectedItem() != null) {
            setRadioButton(nombreTour);
        }
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
        // Modificar para seleccionar solo los tours disponibles
        seleccionar_tour.getComboBox().setModel(new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray()));
        seleccionar_tour.setVisible(true);
    }
    
    private void ocultarIAgregarTour() {
        agregar_tour.setVisible(false);
        agregar_tour.limpiar();  
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
        modificar_tour.limpiar();
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
