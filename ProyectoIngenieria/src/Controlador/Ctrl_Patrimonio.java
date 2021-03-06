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

/**
 * Clase que define al controlador
 * @author Equipo #4
 */
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
    
    /**
     * Constructor del controlador, inicia toda la logica para ejecutar el programa
     * @throws IOException
     */
    public Ctrl_Patrimonio() throws IOException {
        numTours=0;
        totalPuntosCJ=0;
        conjunto_tours = new ConjTourVirtuales ();
        conjunto_puntos = new ConjPuntosInteres();
        obraCreator = new ConcreteCreator();
        instanciarVistas();
        centrarVistas();
        // Agrega los escuchadores de los eventos de las interfaces
        addListeners();
        agregarPuntosInteres();
        mostrarIRol();
    }
    
    private void agregarPuntosInteres() throws UnsupportedEncodingException, IOException {
        // Intenta abrir el archivo que contiene las obras
        try (InputStream in = new FileInputStream(new File(getClass().getClassLoader().getResource("data/obras.txt").getFile()));) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String datos;
            // Mientras no sea el final de archivo
            while ((datos = br.readLine()) != null) {
                // Divide los datos de la obra en sus diferentes partes y los pone en un arreglo
                String[] parts = datos.split("#", -1);
                Obra ob = obraCreator.factoryMethod(parts);
                conjunto_puntos.addPuntoInteres(ob);
                totalPuntosCJ++;
            }
        } catch (IOException ioe) {
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
          Cronometro.cancel();
          SiguienteObra();
        }
    }
    
    class Faltan30 extends TimerTask {
        @Override
        public void run() {
          Cronometro30s.cancel();
          alerta.ColocarAlerta("Le quedan 30 segundos.");
          mostrarIAlerta();
        }
    }
    
    class Faltan20 extends TimerTask {
        @Override
        public void run() {
          Cronometro20s.cancel();
          alerta.ColocarAlerta("Le quedan 20 segundos.");
          mostrarIAlerta();
        }
    }
    
    class Faltan10 extends TimerTask {
        @Override
        public void run() {
          Cronometro10s.cancel();
          alerta.ColocarAlerta("Le quedan 10 segundos.");
          mostrarIAlerta();
        }
    }
    
    private void modificarTour(){
        String tourViejo=modificar_tour.tourSeleccionado();
        String nuevoNombre= modificar_tour.nuevoNombre();
        int NuevoID = modificar_tour.nuevoID();
        String textoCJ=nuevoNombre;
        textoCJ=textoCJ.replaceAll(" ", "");
        int contador=0;
        boolean disponibilidad = modificar_tour.getSelectedButton();
        boolean Repetido = conjunto_tours.nombreExiste(nuevoNombre);
        boolean IDRepetido = conjunto_tours.IDExiste(NuevoID);
        if(TourVirtual.getCantTours() > 0){
            if(Repetido == false && (IDRepetido==false || NuevoID==-1000)){
                // Mientras hayan tours en el conjunto de tours, buscar la posicion del tour con el mismo nombre
                while((conjunto_tours.getnombre(contador) == null ? tourViejo != null : !conjunto_tours.getnombre(contador).equals(tourViejo)) && contador <=numTours){
                    contador++;
                }
                // Se modifica la disponibilidad y si no se coloca nombre nuevo se deja el anterior, sino se cambia por el nuevo
                if(conjunto_tours.getnombre(contador) == null ? tourViejo == null : conjunto_tours.getnombre(contador).equals(tourViejo)){
                    conjunto_tours.Tour(contador).setDisponibilidad(disponibilidad);
                    if ("".equals(nuevoNombre) || "".equals(textoCJ)) {
                        alerta.ColocarAviso("Tour Modificado Exitosamente");
                        if (NuevoID!=-1000) {
                        conjunto_tours.Tour(contador).setID(NuevoID);
                        }
                    } else {
                        conjunto_tours.Tour(contador).setNombre(nuevoNombre);
                        alerta.ColocarAviso("Tour Modificado Exitosamente");
                         if (NuevoID!=-1000) {
                        conjunto_tours.Tour(contador).setID(NuevoID);
                         }
                    } 
                } else {
                    alerta.ColocarAdvertencia("Error: Nombre anteriormente asignado");
                }
            } else {
                alerta.ColocarAdvertencia("Error: El ID o el Nombre estan en uso");
            }
        }else{
            alerta.ColocarAdvertencia("Error: No hay tours para modificar");
        }
    }
    
    private void eliminarTour(){
        String nombreTour;
        nombreTour= eliminar_tour.tourSeleccionado();
        int contador=0;
        if (TourVirtual.getCantTours() > 0) {
            // Mientras hayan tours en el conjunto de tours, buscar la posicion del tour con el mismo nombre
            while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
            contador++;
            }
            if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
                conjunto_tours.deleteTour(contador);
                numTours = numTours - 1;
                TourVirtual.decCantTours();
            }
            alerta.ColocarAviso("Tour Eliminado Exitosamente");
        } else {
            alerta.ColocarAdvertencia("Error: No hay Tours para Eliminar");
        }
        
    }
    
    private void agregarTourCJ(String nombreArchivo ,String nombreTourVirtual, int IDActual) throws UnsupportedEncodingException, IOException {
            String lecturaCJ;
            int iCJ;
            String textoCJ=nombreTourVirtual;
            textoCJ=textoCJ.replaceAll(" ", "");
            ArrayList<PuntoInteres> ListaPuntosInteresDeTour;
            ListaPuntosInteresDeTour = new ArrayList<>();
            if(!"".equals(nombreArchivo) && !"".equals(nombreTourVirtual)&& IDActual!=-1000 && !"".equals(textoCJ)){
                if (!conjunto_tours.nombreExiste(nombreTourVirtual) && !conjunto_tours.IDExiste(IDActual)) {
                    // Se intenta abrir el archivo que contiene los puntos de interes
                    try (InputStream reCJ = new FileInputStream(new File(getClass().getClassLoader().getResource("data/" + nombreArchivo).getFile()));){
                        BufferedReader readCJ= new BufferedReader(new InputStreamReader(reCJ, "UTF-8"));
                        if (numTours==0) {
                            conjunto_tours.addTourVirtual();
                            conjunto_tours.agregarNombre(numTours, nombreTourVirtual);
                            conjunto_tours.agregarID(numTours, IDActual);
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
                            conjunto_tours.agregarID(numTours, IDActual);
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
                        alerta.ColocarAviso("Tour Agregado Exitosamente");

                    } catch (NullPointerException | FileNotFoundException | UnsupportedEncodingException ex) {
                        alerta.ColocarAdvertencia("Error: No se consiguió el archivo");
                    }
                }else{
                  alerta.ColocarAdvertencia("Error: Nombre del Tour o ID está en uso");  
                }
            }else{
                alerta.ColocarAdvertencia("Error: Debe llenar todos los campos correspondientes");
            }
    }
 
    /**
     * Crea y retorna una lista de los Tours Virtuales Disponibles
     * @return Lista de Tours Virtuales Disponibles
     */
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
    
    /**
     * Retorna la cantidad de Tours Virtuales Disponibles
     * @return cantidad de tours virtuales disponibles
     */
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
    
    /**
     * Verifica la contraseña ingresada por el comité
     * @return true si la contraseña es correcta y falso en caso contrario
     */
    public boolean contrasenaCorrecta() {
        return "12345678".equals(new String(contrasena.getPassword()));
    }
    
    /**
     * Selecciona el radio button correspondiente a la disponibilidad actual del tour seleccionado
     * @param nombreTour
     */
    public void setRadioButton(String nombreTour) {
       int contador=0;
       // Mientras hayan tours en el conjunto de tours, buscar la posicion del tour con el mismo nombre
       while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
           contador++;
       }
       // Coloca el radio buton con la disponibilidad actual del tour seleccionado
       if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
          boolean disponible = conjunto_tours.getDisponibilidad(contador);
          if (disponible) {
              modificar_tour.getRadioDisponible().setSelected(true);
          } else {
              modificar_tour.getRadioNoDisponible().setSelected(true);
          }
       }
    }
    
    // Escucha el boton Visitante en IRol y realiza la logica necesaria
    class BotonVisitanteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIRol();
            mostrarISeleccionarTour();
        }     
    }
    
   // Escucha el boton Administrador en IRol y realiza la logica necesaria
    class BotonAdministradorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIRol();
            mostrarIContrasena();
        }
    }
    
   // Escucha el boton Atras en ISeleccionarTour y realiza la logica necesaria
    class BotonAtrasListenerST implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarISeleccionarTour();
            mostrarIRol();
        }  
    }

   // Escucha el boton Continuar en ISeleccionarTour y realiza la logica necesaria    
    class BotonContinuarListenerST implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int contador=0;
            if (getCantToursDisponibles() > 0) {
                String nombreTour;
                nombreTour=seleccionar_tour.saberSeleccionCJ();
                // Mientras hayan tours en el conjunto de tours, buscar la posicion del tour con el mismo nombre
                while((conjunto_tours.getnombre(contador) == null ? nombreTour != null : !conjunto_tours.getnombre(contador).equals(nombreTour)) && contador <=numTours){
                    contador++;
                }
                if(conjunto_tours.getnombre(contador) == null ? nombreTour == null : conjunto_tours.getnombre(contador).equals(nombreTour)){
                    tourActual = conjunto_tours.Tour(contador);
                }
                iterator = tourActual.createIterator();
                ocultarISeleccionarTour();
                mostrarIRecorrer();
                iterator.prox();
            } else {
          alerta.ColocarAdvertencia("Disculpe, no hay Tours disponibles");   
          mostrarIAlerta();
            }
        }    
    }

    // Escucha el boton Finalizar en IRecorrerTour y realiza la logica necesaria
    class BotonFinalizarListenerRT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AcabarTimer();
            ocultarIRecorrer();
            mostrarIRol();
        }  
    }
    
    // Escucha el boton Continuar en IRecorrerTour y realiza la logica necesaria
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
    
    /**
     * Determina accion a tomar al pasar al siguiente punto de interes
     */
    public void SiguienteObra(){
        if (iterator.end()) {
            ocultarIRecorrer();
            mostrarIRol();
        }else{
            mostrarIRecorrer();
            iterator.prox();
        }
    }
    
    // Escucha el boton Ok en IAlerta y realiza la logica necesaria
    class BotonOkListenerIA implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIAlerta();
        }    
    }
    
    // Escucha el boton Atras en IContrasena y realiza la logica necesaria
    class BotonAtrasListenerIC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIContrasena();
            mostrarIRol();
        }  
    }
    
    // Escucha el boton Continuar en IContrasena y realiza la logica necesaria
    class BotonContinuarListenerIC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (contrasenaCorrecta()) {
                ocultarIContrasena();
                mostrarIOpciones();
            } else {
                alerta.ColocarAdvertencia("Contraseña Incorrecta");
                mostrarIAlerta();
                contrasena.limpiar();
            }
        }    
    }
    
    // Escucha el si se presiona enter en IContrasena y realiza la logica necesaria
    class PresionarEnterListenerIC implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (contrasenaCorrecta()) {
                ocultarIContrasena();
                mostrarIOpciones();
            } else {
                alerta.ColocarAdvertencia("Contraseña Incorrecta");
                mostrarIAlerta();
                contrasena.limpiar();
            }
        }
    }

    // Escucha el boton Añadir en IOpciones y realiza la logica necesaria
    class BotonAnadirListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIAgregarTour();
        }    
    }
    
   // Escucha el boton Eliminar en IOpciones y realiza la logica necesaria
    class BotonEliminarListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIEliminarTour();
        }    
    }

    // Escucha el boton Modificar en IOpciones y realiza la logica necesaria    
    class BotonModificarListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIModificarTour();
        }  
    }

    // Escucha el boton Volver en IOpciones y realiza la logica necesaria
    class BotonVolverListenerIO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIOpciones();
            mostrarIRol();
        }    
    }
    
    // Escucha el boton Atras en IModificarTour y realiza la logica necesaria
    class BotonAtrasListenerMT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIModificarTour();
            mostrarIOpciones();
        }  
    }

    // Escucha el boton Modificar en IModificarTour y realiza la logica necesaria
    class BotonModificarListenerMT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modificarTour();
            ocultarIModificarTour();
            mostrarIOpciones();
            mostrarIAlerta();
        }    
    }
    
    // Escucha el ComboBox en IModificarTour para saber la seleccion y realiza la logica necesaria
    class ComboBoxListenerMT implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String nombreTour = String.valueOf(modificar_tour.getComboBox().getSelectedItem());
                setRadioButton(nombreTour);
            }
        }
    }
    
    // Escucha el boton Atras en IAgregarTour y realiza la logica necesaria
    class BotonAtrasListenerAT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIAgregarTour();
            mostrarIOpciones();
        }  
    }
    
    // Escucha el boton Añadir en IAgregarTour y realiza la logica necesaria
    class BotonAnadirListenerAT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                agregarTourCJ(agregar_tour.nombreDelArchivo(),agregar_tour.nombreTourVirtual(), agregar_tour.nombreID());
            } catch (IOException ex) {
                Logger.getLogger(Ctrl_Patrimonio.class.getName()).log(Level.SEVERE, null, ex);
            }
            ocultarIAgregarTour();
            mostrarIOpciones(); 
            mostrarIAlerta();
        }    
    }
    
    // Escucha el boton Atras en IEliminarTour y realiza la logica necesaria
    class BotonAtrasListenerET implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ocultarIEliminarTour();
            mostrarIOpciones();
        }  
    }
    
    // Escucha el boton Eliminar en IEliminarTour y realiza la logica necesaria
    class BotonEliminarListenerET implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarTour();
            ocultarIEliminarTour();
            mostrarIOpciones();
            mostrarIAlerta();
        }    
    }
    
    // Agrega los diferentes listeners a los respectivos botones en las vistas
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
        DefaultComboBoxModel cb = new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray());
        String temp = "No existen tours para eliminar";
        if (cb.getSize() == 0) {
            cb.addElement(temp);
        }
        eliminar_tour.getComboBox().setModel(cb);
        eliminar_tour.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    private void mostrarIModificarTour() {
        DefaultComboBoxModel cb = new DefaultComboBoxModel(conjunto_tours.getListaToursVirtuales().toArray());
        String temp = "No existen tours para modificar";
        if (cb.getSize() == 0) {
            cb.addElement(temp);
        }
        modificar_tour.getComboBox().setModel(cb);
        String nombreTour = String.valueOf(modificar_tour.getComboBox().getSelectedItem());
        if (!nombreTour.equals(temp) && modificar_tour.getComboBox().getSelectedItem() != null) {
            setRadioButton(nombreTour);
        }
        modificar_tour.setVisible(true);
    }
    
    private void mostrarIOpciones() {
        opciones.setVisible(true);
    }
    
    private void mostrarIRecorrer() {
        SetTimer(300);
        ocultarIAlerta();
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
        DefaultComboBoxModel cb = new DefaultComboBoxModel(getToursDisponibles().toArray());
        if (cb.getSize() == 0) {
            String temp = "No hay tours disponibles actualmente";
            cb.addElement(temp);
        }
        seleccionar_tour.getComboBox().setModel(cb);
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
