/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Punto de Interes
 * @author Equipo #4
 */
public class PuntoInteres {
    private Obra ob;
    
    /**
     * Asocia el Punto de Interes a la Obra correspondiente
     * @param o
     */
    public PuntoInteres(Obra o) {
        ob = o;
    }
    
    /**
     * Retorna la imagen de la obra asociada al punto de interes
     * @return la imagen de la obra asociada al punto de interes
     */
    public Imagen getImagenObra() {
        return ob.getImagen();
    }
    
    /**
     * Retorna las caracteristicas de la obra asociada al punto de interes
     * @return las caracteristicas de la obra asociada al punto de interes
     */
    public String getCaracteristicasObra() {
        return ob.ObtenerCaracteristicas();
    }
    
    /**
     * Retorna nombre de la obra asociada al punto de interes
     * @return nombre de la obra asociada al punto de interes
     */
    public String getNombreObra() {
        return ob.getNombre();
    }
    
    /**
     * Retorna ID de la obra asociada al punto de interes
     * @return ID de la obra asociada al punto de interes
     */
    public String getIDObra () {
        return ob.getID();
    }
    
}
