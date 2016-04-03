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
public class Imagen {
    private String RutaIMG;
    
    public Imagen(String ruta) {
        RutaIMG = "data/" + ruta + ".jpg";
    }
    
    public String getRutaIMG() {
        return RutaIMG;
    }
    
    public void setRutaIMG(String rutimg) {
        RutaIMG = rutimg;
    }
}
