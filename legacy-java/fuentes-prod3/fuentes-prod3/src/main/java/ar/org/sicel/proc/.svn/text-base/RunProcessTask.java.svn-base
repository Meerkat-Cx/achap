package ar.org.sicel.proc;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Tarea ant para ejecutar un proceso.
 * Espera que se le setee el parametro loteXML con el path al archivo xml que se quiere procesar
 * (ant se encarga de transformar path al objeto File).
 * Los archivos resultado del procesamiento (reportes, xmls, etc) se depositan en la ubicacion
 * especificada en sicel3.con.xml , que debe estar dentro del classpath.
 * 
 * @author pablo
 *
 */
public class RunProcessTask extends Task {
	
	private File loteXML;

	
	
	public File getLoteXML() {
		return loteXML;
	}



	public void setLoteXML(File loteXML) {
		this.loteXML = loteXML;
	}



	public void execute() throws BuildException {
		Main.main(new String[]{getLoteXML().getAbsolutePath()} ); 
	}
	
	

}
