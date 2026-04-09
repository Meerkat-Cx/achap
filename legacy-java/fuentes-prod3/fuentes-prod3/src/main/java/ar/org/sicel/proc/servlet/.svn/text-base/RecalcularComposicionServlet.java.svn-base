package ar.org.sicel.proc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import ar.org.sicel.proc.thread.RecalcularComposicionMasivaThread;
import ar.org.sicel.proc.thread.RecalcularLactanciasThread;

public class RecalcularComposicionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6851876006860664438L;
	
	static Logger log = Logger.getLogger(RecalcularComposicionServlet.class);
	
	public void init() throws ServletException {
		
		try {
			RecalcularComposicionMasivaThread thread = RecalcularComposicionMasivaThread.getInstance();
			log.warn("Comienzo a procesar las lactancias.");
			thread.start();
		} catch (Exception e) {
			log.error(e);
		}		
	}

}
