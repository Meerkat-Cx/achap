package ar.org.sicel.persistence.util;

import java.util.Iterator;

import ar.org.sicel.persistence.Contacto;
import ar.org.sicel.persistence.EcloDAO;

public class PruebaBusqueda {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		//Iterator it = ContactoDAO.findBySimilar("pAiR").iterator();
		Iterator it = EcloDAO.findBy("coop",null,null,null,null).iterator();
		while (it.hasNext()) {
			Contacto c = (Contacto)it.next();
			System.out.println(c.getNombreContacto());
		}
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();

	}

}
