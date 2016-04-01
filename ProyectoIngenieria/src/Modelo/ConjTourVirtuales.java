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

    public void agregarNombre (int Tour, String nombreTourActual) {
        TourVirtual cambiarCJ;
        cambiarCJ =  ListaToursVirtuales.get(Tour);
        cambiarCJ.setNombre(nombreTourActual);
        ListaToursVirtuales.set(Tour, cambiarCJ);
    }
    
    public String getnombre (int Tour) {
        return ListaToursVirtuales.get(Tour).getNombre();
    }
    
    public void guardarPuntosInteresTour (int CJ , ArrayList<PuntoInteres> list) {
        TourVirtual aCJ;
        aCJ=ListaToursVirtuales.get(CJ);
        aCJ.setListaPuntosInteres(list);
        ListaToursVirtuales.set(CJ, aCJ);
    }
}
