/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author Ramiro Trachsel
 *
 */
public class PruebaAtributos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Establecimiento est = EstablecimientoDAO.findByPrimaryKey(new Long(8813));
				
		//Raza raza = RazaDAO.findByPrimaryKey("HOLA");
		//raza.get
		
		System.out.println(est.getAtrVariablesEstab().getConjuntoAtributos().getNombre());
		//System.out.println(est.getAtrVariablesEstab().getValors());
		System.out.println(est.getAtrVariablesEstab().getValors());
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

}
