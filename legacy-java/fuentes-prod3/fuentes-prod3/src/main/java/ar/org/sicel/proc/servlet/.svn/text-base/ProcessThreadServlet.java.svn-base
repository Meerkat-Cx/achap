package ar.org.sicel.proc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import ar.org.sicel.proc.thread.ProcessThread;

public class ProcessThreadServlet extends HttpServlet {

	static Logger log = Logger.getLogger(ProcessThreadServlet.class);

	public void init() throws ServletException {
		
		try {
			ProcessThread thread = ProcessThread.getInstance();
			thread.setServlet(this);
            log.warn("Comienzo de chequeos para procesar");
			thread.start();
            
		} catch (Exception e) {
			log.error(e);
		}		
	}
}
