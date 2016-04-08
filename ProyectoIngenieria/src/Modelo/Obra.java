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
    
    public Obra(String[] parts) {
        Numero = parts[0];
        ID = parts[1];
        Autor = parts[2];
        Nombre = parts[3];
        AnoCreacion = parts[4];
        Ubicacion = parts[5];
        Descripcion = parts[6];
        Image = new Imagen(Numero);
        if ("".equals(Descripcion)) {
        } else {
            Descripcion = "\nConsta de " + Descripcion;
        }
    }

    Imagen getImagen() {
        return Image;
    }

    String ObtenerCaracteristicas() {
        if (ID.startsWith("O")) {
            return "La presente obra de arte que se encuentra ubicada en " + Ubicacion + "\nFue creada por " + Autor + " en el año " + AnoCreacion + Descripcion + ". ";
        } else {
            return "La presente arquitectura que se encuentra ubicada en " + Ubicacion + "\nFue creada por " + Autor + " en el año " + AnoCreacion + Descripcion + ". ";
        }
    }

    String getNombre() {
        return Nombre;
    }
    
    public String getID () {
        return ID;
    }
    
    public String getNumero() {
        return Numero;
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
