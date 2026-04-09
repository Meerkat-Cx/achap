/*
 * Creado el 13/07/2005
 */
package ar.org.sicel.proc.services.impl;

import java.util.HashSet;
import java.util.Set;

import ar.org.sicel.persistence.Animal;

/**
 * @author ala
 */
public class AnimalesModificados {
    static Set animalesModificados;
    
    public static void inicializar() {
        animalesModificados = new HashSet();
    }
    
    @SuppressWarnings("unchecked")
	public static void animalModificado(Animal a) {
        animalesModificados.add(a);
    }

}
