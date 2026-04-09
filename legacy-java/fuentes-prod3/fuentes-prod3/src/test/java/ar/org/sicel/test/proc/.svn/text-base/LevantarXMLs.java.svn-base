package ar.org.sicel.test.proc;

import java.io.File;
import java.io.FileReader;

import junit.framework.TestCase;
import ar.org.sicel.proc.v1.lote.Lote;

public class LevantarXMLs extends TestCase {
	
	private class TestXMLFileFilter implements java.io.FileFilter {
	    public boolean accept(File f) {
	        if (f.isDirectory()) 
	        	return false;
	        String name = f.getName().toLowerCase();
	        if ((name.endsWith("xml") && !name.endsWith("_resultado.xml")) &&
	        	!name.equals("formatoarchivo.xml"))
	        	return true;
	        return false;
	    }
	}

	public void testLevantarTodos() throws Exception {
		File f = new File("resources/sicel/test");
		File[] files = f.listFiles(new TestXMLFileFilter());
		for (int i=0;i< files.length;i++) 
			levantar(files[i]);
	}
	
	
	public void levantar(File file) throws Exception {
		System.out.println("Cargando: " + file.getName());
		Lote.unmarshal(new FileReader(file));
	}
	

}
