/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
        
/**
 * Clase que contiene todos los puntos de interes
 * @author Equipo #4
 */
public class ConjPuntosInteres {


    private ArrayList<PuntoInteres> ListaPuntosInteres;

    /**
     * Constructor de la Clase ConPuntosInteres
     */
    public ConjPuntosInteres() {
        ListaPuntosInteres = new ArrayList<>();
    }

    /**
     * Agrega un punto de interes al Conjunto de Puntos de interes
     * @param obra
     */
    public void addPuntoInteres(Obra obra) {
        ListaPuntosInteres.add(new PuntoInteres(obra));
    }

    /**
     * Retorna el id de la obra actual
     * @param iCJ
     * @return ID de la Obra
     */
    public String IDActual (int iCJ) {
        return ListaPuntosInteres.get(iCJ).getIDObra();
    }
    
    /**
     * Retorna el punto de interes actual
     * @param iCJ
     * @return Punto de Interes actual
     */
    public PuntoInteres getPuntoActual (int iCJ) {
        return ListaPuntosInteres.get(iCJ);
    }
}
