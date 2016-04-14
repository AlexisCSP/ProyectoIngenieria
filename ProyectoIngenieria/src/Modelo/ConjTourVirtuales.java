/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase que contiene todos los tours virtuales
 * @author Equipo #4
 */
public class ConjTourVirtuales {
    private ArrayList<TourVirtual> ListaToursVirtuales;
    
    /**
     * Constructor de la clase ConjTourVirtuales
     */
    public ConjTourVirtuales() {
        ListaToursVirtuales = new ArrayList<>();
    }

    /**
     * Agrega un Tour Virtual al Conjunto de Tours Virtuales
     */
    public void addTourVirtual () {
        ListaToursVirtuales.add(new TourVirtual());
    }
    
    /**
     * Retorna el tour virtual correspondiente al ID pasado por parametro
     * @param ID
     * @return Tour Virtual con el ID del paramentro
     */
    public TourVirtual Tour(int ID){                //Nuevo para obtener el Tour Virtual
        return ListaToursVirtuales.get(ID);
    }
    
    /**
     * Determina si un nombre ya esta en uso por algun otro tour virtual
     * @param nombre
     * @return true si el nombre del tour virtual ya existe en el conjunto de tours virtuales, falso en caso contrario
     */
    public boolean nombreExiste(String nombre){
        int contador=0;
        while(contador <ListaToursVirtuales.size()){
                if(!(ListaToursVirtuales.get(contador).getNombre() == null ? nombre == null : ListaToursVirtuales.get(contador).getNombre().equals(nombre))){
                } else {
                    return true;
                }
                contador=contador+1;
            }
        return false;
    }
    
    /**
     * Determina si un ID ya esta en uso por algun otro tour virtual
     * @param IDa
     * @return true si el ID ya esta en uso, falso en caso contrario
     */
    public boolean IDExiste(int IDa){
        int contador=0;
        
        if (IDa!=-1000) {
        while(contador <ListaToursVirtuales.size()){
                if(ListaToursVirtuales.get(contador).getID()!=IDa){
                } else {
                    return true;
                }
                contador=contador+1;
            }
        return false;
        }else{
            return true;
        }
    }
    
    /**
     * Elimina el tour que tiene el ID pasado por parametro
     * @param ID
     */
    public void deleteTour(int ID){
        ListaToursVirtuales.remove(ID);
        ListaToursVirtuales.trimToSize();
    }
    
    /**
     * Retorna Lista de tours virtuales existentes
     * @return lista de tours virtuales existentes
     */
    public ArrayList<TourVirtual> getListaToursVirtuales() {
        return ListaToursVirtuales;
    }

    /**
     * Agrega el nombre al tour virtual especificado
     * @param Tour
     * @param nombreTourActual
     */
    public void agregarNombre (int Tour, String nombreTourActual) {
        TourVirtual cambiarCJ;
        cambiarCJ =  ListaToursVirtuales.get(Tour);
        cambiarCJ.setNombre(nombreTourActual);
        ListaToursVirtuales.set(Tour, cambiarCJ);
    }
    
    /**
     * Agrega ID al tour virtual especificado
     * @param Tour
     * @param IDTourActual
     */
    public void agregarID (int Tour, int IDTourActual) {
        TourVirtual cambiarCJ;
        cambiarCJ =  ListaToursVirtuales.get(Tour);
        cambiarCJ.setID(IDTourActual);
        ListaToursVirtuales.set(Tour, cambiarCJ);
    }
    
    /**
     * Retorna el nombre del tour virtual especificado
     * @param Tour
     * @return nombre del tour virtual especificado
     */
    public String getnombre (int Tour) {
        return ListaToursVirtuales.get(Tour).getNombre();
    }
    
    /**
     * Retorna la disponibilidad del tour virtual especificado
     * @param Tour
     * @return disponibilidad del tour
     */
    public boolean getDisponibilidad (int Tour) {
        return ListaToursVirtuales.get(Tour).getDisponibilidad();
    }
    
    /**
     * Guarda el punto de interes en el conjunto de puntos de interes
     * @param CJ
     * @param list
     */
    public void guardarPuntosInteresTour (int CJ , ArrayList<PuntoInteres> list) {
        TourVirtual aCJ;
        aCJ=ListaToursVirtuales.get(CJ);
        aCJ.setListaPuntosInteres(list);
        ListaToursVirtuales.set(CJ, aCJ);
    }
}
