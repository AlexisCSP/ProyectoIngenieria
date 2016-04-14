/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase que implementa el patron iterator
 * @author Equipo #4
 */
public class IndiceConcreto extends Indice {
    
    private ArrayList<PuntoInteres> lista_pi;
    private int index;
    private int act;
    
    /**
     * Constructor del IndiceConcreto, implementacion del patron iterator
     * @param listCJ
     * @param tamCJ
     */
    public IndiceConcreto (ArrayList<PuntoInteres> listCJ, int tamCJ ) {
        lista_pi = new ArrayList<>();
        int iCJ=0;
        
        while (iCJ < tamCJ) {
            lista_pi.add (listCJ.get(iCJ));
            iCJ++;
        }
        
        act=0;
        index=tamCJ;
    }
    
    /**
     * Inicializa el contador de la lista iterable
     */
    @Override
    public void inicializar() {
        act=0;
    }
    
    /**
     * Retorna tamaño de la lista iterable
     * @return tamaño de la lista iterable
     */
    @Override
    public int getTamano(){
        return index;
    }
    
    /**
     * Retorna indice actual del iterator
     * @return indice actual del iterator
     */
    @Override
    public int getIndex(){
        return act;
    }

    /**
     * Se mueve al siguiente elemento iterable
     */
    @Override
    public void prox() {
        act++;
    }

    /**
     * Retorna true si es el final de la lista iterable, falso en caso contrario
     * @return true si es el final de la lista iterable, falso en caso contrario
     */
    @Override
    public boolean end() {
        if (act>=index) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Retorna el elemento actual iterable
     * @return el elemento actual iterable
     */
    @Override
    public PuntoInteres elemActual() {
        return lista_pi.get(act);
    }
    
}
