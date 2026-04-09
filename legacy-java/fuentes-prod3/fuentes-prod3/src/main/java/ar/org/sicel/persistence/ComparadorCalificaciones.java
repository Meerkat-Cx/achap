package ar.org.sicel.persistence;

import java.util.Comparator;

public class ComparadorCalificaciones implements Comparator {

	public int compare(Object o1, Object o2) {
		Calificacion calificacion1 = (Calificacion) o1;
		Calificacion calificacion2 = (Calificacion) o2;
		return calificacion1.getFecha().compareTo(calificacion2.getFecha());
	}

}
