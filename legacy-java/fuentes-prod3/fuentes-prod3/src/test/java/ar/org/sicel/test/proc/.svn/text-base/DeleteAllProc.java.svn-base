/*
 * Created on 20/05/2005
 */
package ar.org.sicel.test.proc;

import java.util.Iterator;

import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

/**
 * @author ala
 */
public class DeleteAllProc {

	public static void main(String[] args) {
		 
		try {
//			Session session = StandaloneHibernateStrategy.getInstance().openNewSession();
			//Iterator it = ProcProcesDAO.findAll().iterator();
            Iterator it = ProcLoteDAO.findAll().iterator();
			while (it.hasNext()) {
                ProcLote lote = (ProcLote) it.next();
                System.out.println("Eliminando lote:" + lote.getId());
                try {
                    //if (proc.getProcLote() != null)
                     ProcProces.elimProcesoYEventos(lote);
                    //session.delete(proc);
                } catch (RuntimeException e1) {
                    System.out.println("Fallo la eliminacion del proceso " + e1.getMessage());
                    //e1.printStackTrace();
                }
			}
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		} catch (ExcepcionIntegridad e) {
           e.printStackTrace();
        }
		
		
				
	}
}
