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
public class ConjPuntosInteres {
    public ArrayList<PuntoInteres> ListaPuntosInteres;
    public ConjPuntosInteres() {
        ListaPuntosInteres = new ArrayList<>();
    }
    public void addPuntoInteres(String datos) {
        ListaPuntosInteres.add(new PuntoInteres(datos));
    }
}
