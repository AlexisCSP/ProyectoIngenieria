/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Obra de arte
 * @author Equipo #4
 */
public class ObraDeArte extends Obra {

    /**
     *
     */
    public int Paneles;

    /**
     * Construcor de Obra de arte, llama a la Super Clase para inicializar los atributos heredados
     * @param datos
     */
    public ObraDeArte(String[] datos) {
        super(datos);
        // Logica para el resto
    }
    
    /**
     *
     * @return
     */
    public int getPaneles() {
        return Paneles;
    }

    /**
     *
     * @param p
     */
    public void setPaneles(int p) {
        Paneles = p;
    }
}
