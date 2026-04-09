package ar.org.sicel.test.proc;

import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;

public class TransferenciaAutomatica extends BaseProcTest {
	static String file = "resources/sicel/test/altaTransferenciaAutomatica"; 

	
	public static void main(String args[]) throws Exception {
		TransferenciaAutomatica t= new TransferenciaAutomatica();
		t.deshacerTodos();
	}
	
	protected String[] getPathArhivosAProcesar() {
		return new String[] {file};
	}


	
	protected void finProceso(Session session) throws Exception {
		Animal a = AnimalDAO.findByRegistry("RC","-4","HOLA","F");
		assertEquals(8801,(long)a.getEstablecimiento().getId());
		assertEquals(1001,(long)a.getPropietario().getId());
		assertEquals("-46",a.getRP());
		super.finProceso(session);
	}



	/**
	 * Con el test anterior al finalizar el proceso alcanza para fijarce
	 * si andubo o no, asi que pongo que siempre despues del test elimine el
	 * proceso.
	 */
	protected boolean desacerDespuesDeTest() {
		return false;
	}
	
	

}
