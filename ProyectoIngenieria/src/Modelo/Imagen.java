/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Imagen
 * @author Equipo #4
 */
public class Imagen {
    private String RutaIMG;
    
    /**
     * Constructor que inicializa la ruta de la imagen
     * @param ruta
     */
    public Imagen(String ruta) {
        RutaIMG = "data/" + ruta + ".jpg";
    }
    
    /**
     * Retorna la ruta de la imagen
     * @return la ruta de la imagen
     */
    public String getRutaIMG() {
        return RutaIMG;
    }
    
    /**
     * Set la ruta de la imagen
     * @param rutimg
     */
    public void setRutaIMG(String rutimg) {
        RutaIMG = rutimg;
    }
}
