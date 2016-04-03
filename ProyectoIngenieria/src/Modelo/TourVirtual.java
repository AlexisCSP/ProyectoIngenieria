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
    private Indice iterador;

    public TourVirtual() {
        ++cantTours;
        ID = cantTours;
        Disponibilidad = true;
        ListaPuntosInteres=new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return this.Nombre;
    }
    
    public static int getCantTours() {
        return cantTours;
    }
    
    public static void decCantTours() {
        cantTours--;
    }

    public static void incCantTours() {
        cantTours++;
    }
    public int getID() {
        return ID;
    }
/*
    public void setID(int id) {
        ID = id;
    }
*/   
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
        int ICJ=0;
        int detenerCJ=list.size();
        while (ICJ<detenerCJ) {
        ListaPuntosInteres.add(list.get(ICJ)) ; 
        ICJ++;
        }
    }
    
    public Indice createIterator() {
        return iterador = new IndiceConcreto (ListaPuntosInteres, ListaPuntosInteres.size());
    }
}
