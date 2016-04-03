/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author alexis
 */
public abstract class Indice {
    
    public Indice () {
    
    }
    
    public abstract void inicializar ();
    public abstract void prox ();
    public abstract boolean end ();
    public abstract PuntoInteres elemActual (); 
    
}
