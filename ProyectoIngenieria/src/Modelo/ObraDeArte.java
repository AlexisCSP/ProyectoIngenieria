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
public class ObraDeArte extends Obra {
    public int Paneles;

    
    public ObraDeArte(String[] datos) {
        super(datos);
        // Logica para el resto
    }
    
    public int getPaneles() {
        return Paneles;
    }
    public void setPaneles(int p) {
        Paneles = p;
    }
}
