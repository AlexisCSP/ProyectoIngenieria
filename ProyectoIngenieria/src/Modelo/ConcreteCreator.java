/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 * Clase que implementa el factory method
 * @author Equipo #4
 */
public class ConcreteCreator extends Creator {

    /**
     * Implementa el factory method, determina tipo de obra y crea la clase correspondiente
     * @param datos
     * @return Obra de Arte o Arquitectura dependiendo del tipo
     */
    public Obra factoryMethod(String[] datos) {
        String tipo = datos[1];
        if (tipo.startsWith("O")) {
            return new ObraDeArte(datos);
        } else {
            return new Arquitectura(datos);
        }
    }
}
