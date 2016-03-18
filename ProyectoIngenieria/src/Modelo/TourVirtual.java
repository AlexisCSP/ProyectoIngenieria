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
public class TourVirtual {

    private static int cantTours;
    private int ID;
    private String Nombre;
    private boolean Disponibilidad;
    private ArrayList<PuntoInteres> ListaPuntosInteres;
    private IndiceConcreto iterador;

    public TourVirtual() {
        ++cantTours;
        ID = cantTours;
        Disponibilidad = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }
    
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String n) {
        Nombre = n;
    }
    
    public boolean getDisponibilidad() {
        return Disponibilidad;
    }
    
    public void setDisponibilidad(boolean d) {
        Disponibilidad = d;
    }
    
    public ArrayList<PuntoInteres> getListaPuntosInteres() {
        return ListaPuntosInteres;
    }
    
    public void setListaPuntosInteres(ArrayList<PuntoInteres> list) {
        ListaPuntosInteres = list;
    }
    
    public IndiceConcreto createIterator() {
        return iterador;
    }
}
