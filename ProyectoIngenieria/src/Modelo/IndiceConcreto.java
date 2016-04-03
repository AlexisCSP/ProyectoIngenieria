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
public class IndiceConcreto extends Indice {
    
    private ArrayList<PuntoInteres> lista_pi;
    private int index;
    private int act;
    
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
    
    @Override
    public void inicializar() {
        act=0;
    }

    @Override
    public void prox() {
        act++;
    }

    @Override
    public boolean end() {
        if (act>=index) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public PuntoInteres elemActual() {
        return lista_pi.get(act);
    }
    
}
