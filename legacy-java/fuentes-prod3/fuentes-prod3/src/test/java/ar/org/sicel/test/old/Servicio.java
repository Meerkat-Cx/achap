package ar.org.sicel.test.old;

import junit.framework.TestCase;

public class Servicio extends TestCase {
    String fileConPadreDistintoProp = "resources/sicel/test/servicioConPadreDistintoProp";
    String fileConTE = "resources/sicel/test/servicioConTE";
    /*
   

	    public void testServicioConPadreDistintoProp() throws Exception {
	        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
	        File f = new File(fileConPadreDistintoProp + ".xml");
	        String loteXML = IOUtils.toString(new FileInputStream(f));
	        StandaloneHibernateStrategy.getInstance().openNewSession();
	        String resultado = proc.procesar(loteXML);
	        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	        File salida = new File(fileConPadreDistintoProp + "_resultado.xml");
	        FileOutputStream out = new FileOutputStream(salida);
	        out.write(resultado.getBytes());
	        out.flush();
	        out.close();
	    }

	    public void testServicioConTE() throws Exception {
	        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
	        File f = new File(fileConTE + ".xml");
	        String loteXML = IOUtils.toString(new FileInputStream(f));
	        StandaloneHibernateStrategy.getInstance().openNewSession();
	        String resultado = proc.procesar(loteXML);
	        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	        File salida = new File(fileConTE + "_resultado.xml");
	        FileOutputStream out = new FileOutputStream(salida);
	        out.write(resultado.getBytes());
	        out.flush();
	        out.close();
	    }
	    
	    */
	    
}
