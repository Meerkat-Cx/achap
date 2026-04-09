/*
 * Created on 18/05/2005
 */
package ar.org.sicel.test.old;

import junit.framework.TestCase;

/**
 * @author ala
 */
public class FormatoArchivo extends TestCase {
    /*
	
	public void testFormato() throws Exception{
		String file = "resources/sicel/test/formatoarchivo";
		IProcesadorXML proc = ServiceLocator.getProcesadorXML();
		File f = new File(file + ".xml");
		String loteXML = IOUtils.toString(new FileInputStream(f));
		StandaloneHibernateStrategy.getInstance().openNewSession();
		String resultado = proc.procesar(loteXML);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		Lote loteResultado =(Lote) Lote.unmarshal(new StringReader(resultado));
		loteResultado.getIDLoteSicel();
		
			//tiene que haber un unico resultado, y ser XML_NO_VALIDO
		assertEquals(MENSAJES.XML_NO_VALIDO, loteResultado.getRdos().getRdo(0).getCodigoMsg());
		
		Long idProcess = loteResultado.getIDLoteSicel();
		Session session = StandaloneHibernateStrategy.getInstance().openNewSession();
		ProcProces procProces = ProcProcesDAO.findByPrimaryKey(idProcess);
		
			//el ProcLote tiene que ser null
		assertNull(procProces.getProcLote());
			//tiene que tener un mensaje
		assertEquals(1, procProces.getProcMsgsses().size());
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		//lote.marshal(new OutputStreamWriter(System.out));
		File salida = new File(file + "_resultado.xml");
		FileOutputStream out = new FileOutputStream(salida);
		out.write(resultado.getBytes());
		out.flush();
		out.close();
	}
    */

}
