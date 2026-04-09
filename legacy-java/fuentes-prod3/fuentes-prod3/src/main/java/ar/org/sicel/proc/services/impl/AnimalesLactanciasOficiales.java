package ar.org.sicel.proc.services.impl;

import java.util.HashSet;
import java.util.Set;

import ar.org.sicel.persistence.Animal;

public class AnimalesLactanciasOficiales {
	public static Set animales;
	
	public static void init() {
		animales = new HashSet();
	}
	
	@SuppressWarnings("unchecked")
	public static void addAnimal(Animal a) {
		animales.add(a.getId());
	}
	
	public static Set getAnimales() {
		return animales;
	}
	

}
