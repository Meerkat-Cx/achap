package ar.org.sicel.test.old;

import junit.framework.TestCase;

public class ReproduccionUsarRazaMadre extends TestCase {
	 String file = "resources/sicel/test/altaServicioReproduccionUsarRazaMadre";
	    
	   /*

	    public void testAltaServReprUsarRazaMadre() throws Exception {
	        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
	        File f = new File(file + ".xml");
	        String loteXML = IOUtils.toString(new FileInputStream(f));
	        StandaloneHibernateStrategy.getInstance().openNewSession();
	        String resultado = proc.procesar(loteXML);
	        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	        File salida = new File(file + "_resultado.xml");
	        FileOutputStream out = new FileOutputStream(salida);
	        out.write(resultado.getBytes());
	        out.flush();
	        out.close();
	    }
	    
	    
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

		public void testVerEventos() throws Exception {
			StandaloneHibernateStrategy.getInstance().openNewSession();
			Animal an1 = AnimalDAO.findByRegistry("RC", "-1",true);
			System.out.println(an1.toString());
			System.out.println("EVTs.");
			System.out.println("=====");
			Set evs = an1.getAllEventos();
			for (Iterator iter = evs.iterator(); iter.hasNext();) {
				Evento ev = (Evento) iter.next();
				System.out.println(ev.getResumenYEncab());
			}
			
			Animal an2 = AnimalDAO.findByRegistry("RC", "-2",true);
			System.out.println(an2.toString());
			System.out.println("EVTs.");
			System.out.println("=====");
			Set evs2 = an1.getAllEventos();
			for (Iterator iter = evs2.iterator(); iter.hasNext();) {
				Evento ev = (Evento) iter.next();
				System.out.println(ev.getResumenYEncab());
			}
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		}
        */
}
