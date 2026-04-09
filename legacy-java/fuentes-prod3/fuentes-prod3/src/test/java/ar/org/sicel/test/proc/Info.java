package ar.org.sicel.test.proc;

import java.util.Iterator;

import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Evento;

public class Info extends BaseProcTest {
	static String file = "resources/sicel/test/altaInfo";

	
	protected String[] getPathArhivosAProcesar() {
		return new String[]{file};
	}
	
	

	
	protected void finProceso(Session session) throws Exception {
		//con sexo
		Animal a = AnimalDAO.findByRegistry("RC","-3","HOLA","F");
		Iterator it = a.getAllEventos().iterator();
		while (it.hasNext()) {
			Evento e = (Evento)it.next();
			System.out.println(e.getResumenYEncab());
		}
		super.finProceso(session);
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Info i = new Info();
		i.deshacerTodos();

	}

}
