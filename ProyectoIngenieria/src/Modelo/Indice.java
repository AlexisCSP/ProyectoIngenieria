/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * SuperClase base para implementar el patron iterator
 * @author Equipo #4
 */
public abstract class Indice {
    
    /**
     *
     */
    public Indice () {
    
    }
    
    /**
     *
     */
    public abstract void inicializar ();

    /**
     *
     */
    public abstract void prox ();

    /**
     *
     * @return
     */
    public abstract boolean end ();

    /**
     *
     * @return
     */
    public abstract PuntoInteres elemActual ();

    /**
     *
     * @return
     */
    public abstract int getTamano();

    /**
     *
     * @return
     */
    public abstract int getIndex();
}
