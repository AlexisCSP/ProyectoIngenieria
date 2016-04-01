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
public abstract class Obra {
    
    public Obra(String datos) {
        String[] parts = datos.split("#", -1);
        Numero = parts[0];
        ID = parts[1];
        Autor = parts[2];
        Nombre = parts[3];
        AnoCreacion = parts[4];
        Ubicacion = parts[5];
        Descripcion = parts[6];
    }

    Imagen getImagen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String ObtenerCaracteristicas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getID () {
        return ID;
    }
    
    protected String Numero;
    protected String ID;
    protected String Nombre;
    protected String Autor;
    protected String AnoCreacion;
    protected String Ubicacion;
    protected String Descripcion;
    protected String Categoria;
    protected Imagen Image;
}
