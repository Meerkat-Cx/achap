/*
 * Created on 26/06/2005
 */
package ar.org.sicel.persistence.util;

import java.io.StringReader;

import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Lote;

/**
 * @author ala
 */
public class ElimLote {
	public static void elimLote(String loteXML) throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Lote lote = (Lote) Lote.unmarshal(new StringReader(loteXML));
		long numero = lote.getIDLoteInte();
		long inte = lote.getInformante();
		try {
			ProcProces.elimProcesoYEventos(inte, numero);
		} catch (ExcepcionIntegridad e) {
			if (e.getCodigoError().compareTo(MENSAJES.PROC_LOTE_NOEX) != 0)
				throw e;
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		}
	}
}
