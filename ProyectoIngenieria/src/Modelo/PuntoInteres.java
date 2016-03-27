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
public class PuntoInteres {
    private Obra ob;
    
    public PuntoInteres(String datos) {
        ob = new ObraDeArte(datos);
    }
    
    public Imagen getImagenObra() {
        return ob.getImagen();
    }
    
    public String getCaracteristicasObra() {
        return ob.ObtenerCaracteristicas();
    }
    
    public String getNombreObra() {
        return ob.getNombre();
    }
    
}
