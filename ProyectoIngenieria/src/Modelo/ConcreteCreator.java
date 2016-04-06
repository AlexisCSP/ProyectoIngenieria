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
public class ConcreteCreator extends Creator {
    public Obra factoryMethod(String[] datos) {
        String tipo = datos[1];
        if (tipo.startsWith("O")) {
            return new ObraDeArte(datos);
        } else {
            return new Arquitectura(datos);
        }
    }
}
