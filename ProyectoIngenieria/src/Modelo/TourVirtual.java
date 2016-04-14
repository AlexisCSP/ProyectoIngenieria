/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase Tour Virtual
 * @author Equipo #4
 */
public class TourVirtual {

    private static int cantTours;
    private static int numID;
    private int ID;
    private String Nombre;
    private boolean Disponibilidad;
    private ArrayList<PuntoInteres> ListaPuntosInteres;
    private Indice iterador;

    /**
     * Constructor de la clase tour virtual
     */
    public TourVirtual() {
        ++cantTours;
        ++numID;
        ID = numID;
        Disponibilidad = true;
        ListaPuntosInteres=new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return this.Nombre;
    }
    
    /**
     * Retorna cantidad de tours virtuales creados
     * @return cantidad de tours virtuales creados
     */
    public static int getCantTours() {
        return cantTours;
    }
    
    /**
     * Retorna ID del tour virtual
     * @return ID del Tour Virtual 
     */
    public static int getNumeroID() {
        return numID;
    }
    
    /**
     * Disminuye la cantidad de tour creados en 1
     */
    public static void decCantTours() {
        cantTours--;
    }

    /**
     * incrementa la cantidad de tour creados en 1
     */
    public static void incCantTours() {
        cantTours++;
        numID++;
    }

    /**
     * Retorna el ID del Tour
     * @return el ID del Tour
     */
    public int getID() {
        return ID;
    }
/*
    public void setID(int id) {
        ID = id;
    }
*/   

    /**
     * Retorna el nombre del tour
     * @return nombre del tour
     */
   
    public String getNombre() {
        return Nombre;
    }
    
    /**
     * Set nombre del Tour
     * @param n
     */
    public void setNombre(String n) {
        Nombre = n;
    }
    
    /**
     * Retorna la disponibilidad del tour
     * @return Disponibilidad del tour
     */
    public boolean getDisponibilidad() {
        return Disponibilidad;
    }
    
    /**
     * Set disponibilidad del tour
     * @param d
     */
    public void setDisponibilidad(boolean d) {
        Disponibilidad = d;
    }
    
    /**
     * Set ID del Tour
     * @param z
     */
    public void setID (int z) {
        ID=z;
    }
    
    /**
     * Retorna Lista de Puntos de Interes del Tour
     * @return Lista de Puntos de Interes del Tour
     */
    public ArrayList<PuntoInteres> getListaPuntosInteres() {
        return ListaPuntosInteres;
    }
    
    /**
     * Set Lista de Puntos de Interes del Tour
     * @param list
     */
    public void setListaPuntosInteres(ArrayList<PuntoInteres> list) {
        int ICJ=0;
        int detenerCJ=list.size();
        while (ICJ<detenerCJ) {
        ListaPuntosInteres.add(list.get(ICJ)) ; 
        ICJ++;
        }
    }
    
    /**
     * Retorna iterador del Tour
     * @return iterador del Tour
     */
    public Indice createIterator() {
        return iterador = new IndiceConcreto (ListaPuntosInteres, ListaPuntosInteres.size());
    }
}
