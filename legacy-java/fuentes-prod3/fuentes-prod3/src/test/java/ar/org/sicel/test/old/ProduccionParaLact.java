/*
 * Created on 27/05/2005
 */
package ar.org.sicel.test.old;

import junit.framework.TestCase;

/**
 * @author ala
 */
public class ProduccionParaLact extends TestCase {
	String file = "resources/sicel/test/produccionParaLact";
    /*

	public void testElimAnterior() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();

		File f = new File(file + ".xml");
		FileInputStream fis = new FileInputStream(f);
		String loteXML = IOUtils.toString(fis);

		Lote lote = (Lote) Lote.unmarshal(new StringReader(loteXML));
		long numero = lote.getIDLoteInte();
		long inte = lote.getInformante();
		try {
			ProcProces.elimProcesoYEventos(inte, numero);
		} catch (ExcepcionIntegridad e) {
			if (e.getCodigoError().compareTo(MENSAJES.PROC_LOTE_NOEX) == 0)
				System.out.println(e);
			else
				throw e;
		}
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

	public void elimLote(String loteXML) throws Exception {
		Lote lote = (Lote) Lote.unmarshal(new StringReader(loteXML));
		long numero = lote.getIDLoteInte();
		long inte = lote.getInformante();
		try {
			ProcProces.elimProcesoYEventos(inte, numero);
		} catch (ExcepcionIntegridad e) {
			if (e.getCodigoError().compareTo(MENSAJES.PROC_LOTE_NOEX) != 0)
				throw e;
		}
	}

	public void testProduccionyLact() throws Exception {

		File f = new File(file + ".xml");
		FileInputStream fis = new FileInputStream(f);
		String loteXML = IOUtils.toString(fis);

		StandaloneHibernateStrategy.getInstance().openNewSession();

		elimLote(loteXML);

		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		StandaloneHibernateStrategy.getInstance().openNewSession();

		IProcesadorXML proc = ServiceLocator.getProcesadorXML();
		String resultado = proc.procesar(loteXML);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();

		Lote loteResultado = (Lote) Lote.unmarshal(new StringReader(resultado));

		Long idProcess = loteResultado.getIDLoteSicel();
		Session session = StandaloneHibernateStrategy.getInstance()
				.openNewSession();
		ProcProces procProces = ProcProcesDAO.findByPrimaryKey(idProcess);

		ProcLote procLote = procProces.getProcLote();
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();

		File salida = new File(file + "_resultado.xml");
		FileOutputStream out = new FileOutputStream(salida);
		out.write(resultado.getBytes());
		out.flush();
		out.close();
	}

	public void testVerEventos() throws Exception {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		Animal an1 = AnimalDAO.findByRegistry("RC", "98041",true);
		System.out.println(an1.toString());
		System.out.println("EVTs.");
		System.out.println("=====");
		Set evs = an1.getAllEventos();
		for (Iterator iter = evs.iterator(); iter.hasNext();) {
			EvtAnimal ev = (EvtAnimal) iter.next();
			System.out.println(ev.getResumenYEncab());
		}
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
    */
}
