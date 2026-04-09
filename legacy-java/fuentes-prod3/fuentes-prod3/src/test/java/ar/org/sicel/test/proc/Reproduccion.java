package ar.org.sicel.test.proc;

import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;


public class Reproduccion extends BaseProcTest {
	  String file1 = "resources/sicel/test/altaServicioReproduccionConPadre";
	  String file2 = "resources/sicel/test/serviciosReproducciones";

	 /**
	  * Ejecutando el main se elimina el anterior procesamiento.
	  * Es necesario definirlo en cada test porque es un metodo estatico.
	  */
	  /* (non-Javadoc)
	 * @see ar.org.sicel.test.proc.BaseProcTest#main(java.lang.String[])
	 */
	public static void main(String[] args) throws Exception {
	        BaseProcTest t = new Reproduccion();
			t.deshacerTodos();
            //t.MostrarAnimal("RC", "-1",true);
		}  

	/**
	   * Unicamente muestro los eventos del animal.
	   */
	  protected void finProceso(Session session) throws Exception {
		Animal an1 = AnimalDAO.findByRegistry("RC", "-1","HOLA","F");
		MostrarAnimal(an1);
	}

	/**
	 * En este caso 1 solo, si fueran mas archivos irian en ese arreglo en orden.
	 */
	protected String[] getPathArhivosAProcesar() {
		return new String[]{file1,file2};
	}

	
	/**
	 * Necesario definirlo asi para que lo vea Eclipse y lo
	 * ponga en el menu de run as... JUnit Test
	 */
	public void testProcesamiento() throws Exception {
		super.testProcesamiento();
	}
	  
}
