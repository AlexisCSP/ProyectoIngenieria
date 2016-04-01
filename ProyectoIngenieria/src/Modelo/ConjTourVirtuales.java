/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class ConjTourVirtuales {
    private ArrayList<TourVirtual> ListaToursVirtuales;
    
    public ConjTourVirtuales() {
        ListaToursVirtuales = new ArrayList<>();
    }
    public void addTourVirtual () {
        ListaToursVirtuales.add(new TourVirtual());
    }
    
    public TourVirtual Tour(int ID){                //Nuevo para obtener el Tour Virtual
        return ListaToursVirtuales.get(ID);
    }
    
    public void deleteTour(int ID){
        ListaToursVirtuales.remove(ID);
        ListaToursVirtuales.trimToSize();
    }
    
    
    public ArrayList<TourVirtual> getListaToursVirtuales() {
        return ListaToursVirtuales;
    }

    public void agregarNombre (int Tour, String nombreTourActual) {
        TourVirtual cambiarCJ;
        cambiarCJ =  ListaToursVirtuales.get(Tour);
        cambiarCJ.setNombre(nombreTourActual);
        ListaToursVirtuales.set(Tour, cambiarCJ);
    }
    
    public String getnombre (int Tour) {
        return ListaToursVirtuales.get(Tour).getNombre();
    }
    
    public boolean getDisponibilidad (int Tour) {
        return ListaToursVirtuales.get(Tour).getDisponibilidad();
    }
    
    public void guardarPuntosInteresTour (int CJ , ArrayList<PuntoInteres> list) {
        TourVirtual aCJ;
        aCJ=ListaToursVirtuales.get(CJ);
        aCJ.setListaPuntosInteres(list);
        ListaToursVirtuales.set(CJ, aCJ);
    }
}
