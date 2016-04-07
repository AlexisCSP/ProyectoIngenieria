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
import java.util.Timer;
import java.util.TimerTask;
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
    private TourVirtual tourActual;
    private Indice iterator;
    private Timer Cronometro;
    private Timer Cronometro30s;
    private Timer Cronometro20s;
    private Timer Cronometro10s;
    private Creator obraCreator;
    // Modelos
    private ConjTourVirtuales conjunto_tours;
    private ConjPuntosInteres conjunto_puntos;
    
    
    public Ctrl_Patrimonio() throws IOException {
        numTours=0;
        totalPuntosCJ=0;
        conjunto_tours = new ConjTourVirtuales ();
        conjunto_puntos = new ConjPuntosInteres();
        obraCreator = new ConcreteCreator();
        instanciarVistas();
        centrarVistas();
        addListeners();
        agregarPuntosInteres();
        agregarTourCJ("t1_PI.txt","Áreas de las Facultades de Odontología, Farmacia y Ciencias");       //Agregando Los dos primeros tours
        agregarTourCJ("t2_PI.txt","Centro Directivo Cultural");
        mostrarIRol();
    }
    
    private void agregarPuntosInteres() throws UnsupportedEncodingException, IOException {
        try (InputStream in = new FileInputStream(new File(getClass().getClassLoader().getResource("data/obras.txt").getFile()));) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String datos;
            while ((datos = br.readLine()) != null) {
                String[] parts = datos.split("#", -1);
                Obra ob = obraCreator.factoryMethod(parts);
                conjunto_puntos.addPuntoInteres(ob);
                totalPuntosCJ++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
    private void SetTimer(int segundos){
        Cronometro = new Timer();
        Cronometro30s = new Timer();
        Cronometro20s = new Timer();
        Cronometro10s = new Timer();
        
        Cronometro30s.schedule(new Faltan30(),(segundos-30)*1000);
        Cronometro20s.schedule(new Faltan20(),(segundos-20)*1000);
        Cronometro10s.schedule(new Faltan10(),(segundos-10)*1000);
        Cronometro.schedule(new SeAcabo(), segundos*1000);
    }
    
    private void AcabarTimer(){
        Cronometro.cancel();
        Cronometro30s.cancel();
        Cronometro20s.cancel();
        Cronometro10s.cancel();
    }
    
    class SeAcabo extends TimerTask {
        @Override
        public void run() {
          Cronometro.cancel(); //Not necessary because we call System.exit
          SiguienteObra(); //Stops the AWT thread (and everything else)
        }
    }
    
    class Faltan30 extends TimerTask {
        @Override
        public void run() {
          Cronometro30s.cancel(); //Not necessary because we call System.exit
          alerta.ColocarAlerta("Le quedan 30 segundos.");
          mostrarIAlerta(); //Stops the AWT thread (and everything else)
        }
    }
    
    class Faltan20 extends TimerTask {
        @Override
        public void run() {
          Cronometro20s.cancel(); //Not necessary because we call System.exit
          alerta.ColocarAlerta("Le quedan 20 segundos.");
          mostrarIAlerta(); //Stops the AWT thread (and everything else)
        }
    }
    
    class Faltan10 extends TimerTask {
        @Override
        public void run() {
          Cronometro10s.cancel(); //Not necessary because we call System.exit
          alerta.ColocarAlerta("Le quedan 10 segundos.");
          mostrarIAlerta(); //Stops the AWT thread (and everything else)
        }
    }
    
    private void modificarTour(){
        String tourViejo=modificar_tour.tourSeleccionado();
        String nuevoNombre= modificar_tour.nuevoNombre();
        int contador=0;
        boolean disponibilidad = modificar_tour.getSelectedButton();
        boolean Repetido = conjunto_tours.nombreExiste(nuevoNombre);
        if(numTours > 0){
            if(Repetido == false){
                while((conjunto_tours.getnombre(contador) == null ? tourViejo != null : !conjunto_tours.getnombre(contador).equals(tourViejo)) && contador <=numTours){
                    contador++;
                }
                if(conjunto_tours.getnombre(contador) == null ? tourViejo == null : conjunto_tours.getnombre(contador).equals(tourViejo)){
                    conjunto_tours.Tour(contador).setDisponibilidad(disponibilidad);
                    if ("".equals(nuevoNombre)) {
                    } else {
                        conjunto_tours.Tour(contador).setNombre(nuevoNombre);
                        alerta.ColocarAlerta("Tour Modificado Exitosamente");
                    } 
                } else {
                    alerta.ColocarAlerta("Tour No Existente");
                }
            } else {
                alerta.ColocarAlerta("Nombre Del Tour Ya Existe");
            }
        }else{
            alerta.ColocarAlerta("No Hay Tour Para Modificar");
        }
        
    }
    
    private void eliminarTour(){
        String nombreTour;
        nombreTour= eliminar_tour.tourSeleccionado();
        int contador=0;
        if (TourVirtual.getCantTours() > 0) {
            while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
            contador++;
            }
            if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
                conjunto_tours.deleteTour(contador);
                numTours = numTours - 1;
                TourVirtual.decCantTours();
            }
            alerta.ColocarAlerta("Tour Eliminado Exitosamente");
        } else {
            alerta.ColocarAlerta("No hay Tours para Eliminar");
        }
        
    }
    
    private void agregarTourCJ(String nombreArchivo ,String nombreTourVirtual) throws UnsupportedEncodingException, IOException {
            String lecturaCJ;
            int iCJ;
            ArrayList<PuntoInteres> ListaPuntosInteresDeTour;
            ListaPuntosInteresDeTour = new ArrayList<>();
            if (!conjunto_tours.nombreExiste(nombreTourVirtual)) {
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
                    alerta.ColocarAlerta("Tour Agregado Exitosamente");

                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    alerta.ColocarAlerta("Error Al Agregar Tour");
                    Logger.getLogger(Ctrl_Patrimonio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }
 
    public ArrayList<TourVirtual> getToursDisponibles() {
        ArrayList<TourVirtual> toursDisponibles = new ArrayList<>();
        ArrayList<TourVirtual> tours = conjunto_tours.getListaToursVirtuales();
        
        for (int i = 0; i < tours.size(); ++i) {
            
            if (tours.get(i).getDisponibilidad() == true) {
                toursDisponibles.add(tours.get(i));
            }
        }
        return toursDisponibles;
    }
    
    public int getCantToursDisponibles() {
        int size = conjunto_tours.getListaToursVirtuales().size();
        int cantidad = 0;
        for (int i = 0; i < size; ++i) {
            
            if (conjunto_tours.getListaToursVirtuales().get(i).getDisponibilidad() == true) {
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public boolean contrasenaCorrecta() {
        return "12345678".equals(new String(contrasena.getPassword()));
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
            if (getCantToursDisponibles() > 0) {
                int lecturaCJ;
                lecturaCJ=seleccionar_tour.saberSeleccionCJ ();
                tourActual = conjunto_tours.Tour(lecturaCJ);
                iterator = tourActual.createIterator();
                
                
                ocultarISeleccionarTour();
                mostrarIRecorrer();
                iterator.prox();
            } else {
            }
        }    
    }

    class BotonFinalizarListenerRT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AcabarTimer();
            ocultarIRecorrer();
            mostrarIRol();
        }  
    }
    
    class BotonContinuarListenerRT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AcabarTimer();
            if (iterator.end()) {
                ocultarIRecorrer();
                mostrarIRol();
            }else{
                mostrarIRecorrer();
                iterator.prox();
            }
        }    
    }
    
    public void SiguienteObra(){
        if (iterator.end()) {
            ocultarIRecorrer();
            mostrarIRol();
        }else{
            mostrarIRecorrer();
            iterator.prox();
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
            if (contrasenaCorrecta()) {
                ocultarIContrasena();
                mostrarIOpciones();
            } else {
                alerta.ColocarAlerta("Contraseña Incorrecta");
                mostrarIAlerta();
                contrasena.limpiar();
            }
        }    
    }
    
    class PresionarEnterListenerIC implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (contrasenaCorrecta()) {
                ocultarIContrasena();
                mostrarIOpciones();
            } else {
                alerta.ColocarAlerta("Contraseña Incorrecta");
                mostrarIAlerta();
                contrasena.limpiar();
            }
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
            mostrarIAlerta();
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
                agregarTourCJ(agregar_tour.nombreDelArchivo(),agregar_tour.nombreTourVirtual());
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Patrimonio.class.getName()).log(Level.SEVERE, null, ex);
            }
            ocultarIAgregarTour();
            mostrarIOpciones(); 
            mostrarIAlerta();
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
            eliminarTour();
            ocultarIEliminarTour();
            mostrarIOpciones();
            mostrarIAlerta();
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
        contrasena.addEnterContraseña(new PresionarEnterListenerIC());
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
        alerta.getRootPane().setDefaultButton(alerta.getBotonOk());
        alerta.getBotonOk().requestFocus();
        alerta.setVisible(true);
    }
    private void mostrarIContrasena() {
        contrasena.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    private void mostrarIEliminarTour() {
        eliminar_tour.getComboBox().setModel(new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray()));
        eliminar_tour.setVisible(true);
    }
    @SuppressWarnings("unchecked")
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
        SetTimer(40);
        recorrer_tour.setBarraProgreso(iterator.getIndex(),iterator.getTamano());      //Para mostrar la cantidad de tours recorridos
        recorrer_tour.mostrarNombre(iterator.elemActual().getNombreObra());
        recorrer_tour.mostrarNombreTourActual(tourActual.getNombre());
        recorrer_tour.mostrarCaracteristicas (iterator.elemActual().getCaracteristicasObra());
        recorrer_tour.mostrarImagen(iterator.elemActual().getImagenObra().getRutaIMG());
        recorrer_tour.pack();
        recorrer_tour.setVisible(true);
    }
    private void mostrarIRol() {
        rol.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    private void mostrarISeleccionarTour() {
        seleccionar_tour.getComboBox().setModel(new DefaultComboBoxModel(getToursDisponibles().toArray()));
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
        contrasena.limpiar();
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
