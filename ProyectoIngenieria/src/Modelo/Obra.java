/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase Obra, superClase de Obra de Arte y Arquitectura
 * @author Equipo #4
 */
public abstract class Obra {
    
    /**
     * Constructor que inicializa los datos de una obra
     * @param parts
     */
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
            return "La presente obra de arte se encuentra ubicada en " + Ubicacion + "\nFue creada por " + Autor + " en el año " + AnoCreacion + Descripcion + ". ";
        } else {
            return "La presente arquitectura se encuentra ubicada en " + Ubicacion + "\nFue creada por " + Autor + " en el año " + AnoCreacion + Descripcion + ". ";
        }
    }

    String getNombre() {
        return Nombre;
    }
    
    /**
     *
     * @return
     */
    public String getID () {
        return ID;
    }
    
    /**
     * Retorna numero de la obra
     * @return numero de la obra
     */
    public String getNumero() {
        return Numero;
    }
    
    /**
     *
     */
    protected String Numero;

    /**
     *
     */
    protected String ID;

    /**
     *
     */
    protected String Nombre;

    /**
     *
     */
    protected String Autor;

    /**
     *
     */
    protected String AnoCreacion;

    /**
     *
     */
    protected String Ubicacion;

    /**
     *
     */
    protected String Descripcion;

    /**
     *
     */
    protected String Categoria;

    /**
     *
     */
    protected Imagen Image;
}
