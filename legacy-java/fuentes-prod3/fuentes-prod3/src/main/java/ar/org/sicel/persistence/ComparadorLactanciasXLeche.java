package ar.org.sicel.persistence;

import java.util.Comparator;

public class ComparadorLactanciasXLeche implements Comparator {

	//String limiteDias = null;
	
	/**
	 * Comparador de lactancias por valores de leche a un cierto numero de dias 
	 * @param limiteDias Posibles valores: "270", "305", "365"
	 */
	/*public ComparadorLactanciasXLeche(String limiteDias) {
		this.limiteDias = limiteDias;
	}*/
	public ComparadorLactanciasXLeche() {
	}
	
	public int compare(Object o1, Object o2) {
		Lactancia l1 = (Lactancia)o1;
		Lactancia l2 = (Lactancia)o2;
		return l1.getLeche().compareTo(l2.getLeche()); 
	}
	
	
	/*public int compare(Object o1, Object o2) {
		Lactancia l1 = (Lactancia)o1;
		Lactancia l2 = (Lactancia)o2;
		//se hacen las modificaciones ya que las lactancias migradas tanto la REAL, como 305 y 365 pueden ser nulas
		if((l1.getDias() < Integer.parseInt(limiteDias)) && (l2.getDias() < Integer.parseInt(limiteDias)))
			//return l1.getLactanciaA("REAL").getLeche().compareTo(l2.getLactanciaA("REAL").getLeche());
			return ((l1.getLactanciaA("REAL")==null && l2.getLactanciaA("REAL")==null)?0:((l1.getLactanciaA("REAL")==null)?-1:((l2.getLactanciaA("REAL")==null)?1:(l1.getLactanciaA("REAL").getLeche().compareTo(l2.getLactanciaA("REAL").getLeche())))));
		else if((l1.getDias() < Integer.parseInt(limiteDias)) && (l2.getDias() >= Integer.parseInt(limiteDias)))
			//return l1.getLactanciaA("REAL").getLeche().compareTo(l2.getLactanciaA(limiteDias).getLeche());
			return ((l1.getLactanciaA("REAL")==null && l2.getLactanciaA(limiteDias)==null)?0:((l1.getLactanciaA("REAL")==null)?-1:((l2.getLactanciaA(limiteDias)==null)?1:(l1.getLactanciaA("REAL").getLeche().compareTo(l2.getLactanciaA(limiteDias).getLeche())))));
		else if((l1.getDias() >= Integer.parseInt(limiteDias)) && (l2.getDias() < Integer.parseInt(limiteDias)))
			//return l1.getLactanciaA(limiteDias).getLeche().compareTo(l2.getLactanciaA("REAL").getLeche());
			return ((l1.getLactanciaA(limiteDias)==null && l2.getLactanciaA("REAL")==null)?0:((l1.getLactanciaA(limiteDias)==null)?-1:((l2.getLactanciaA("REAL")==null)?1:(l1.getLactanciaA(limiteDias).getLeche().compareTo(l2.getLactanciaA("REAL").getLeche())))));
		else 
			//return l1.getLactanciaA(limiteDias).getLeche().compareTo(l2.getLactanciaA(limiteDias).getLeche());
			return ((l1.getLactanciaA(limiteDias)==null && l2.getLactanciaA(limiteDias)==null)?0:((l1.getLactanciaA(limiteDias)==null)?-1:((l2.getLactanciaA(limiteDias)==null)?1:(l1.getLactanciaA(limiteDias).getLeche().compareTo(l2.getLactanciaA(limiteDias).getLeche())))));
		
	}
*/
}
