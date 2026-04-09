package ar.org.sicel.proc.thread;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.ProcessWork;
import ar.org.sicel.proc.RollbackProcessTask;
import ar.org.sicel.proc.services.impl.ProcesadorLote;
import ar.org.sicel.proc.servlet.ProcessThreadServlet;
import ar.org.sicel.web.login.LoginAction;

public class ProcessThread extends Thread {
	static Logger log = Logger.getLogger(ProcessThread.class);

    private static ProcessThread instance;
    private boolean procesando;
    private ProcessThreadServlet servlet;
    //public Thread thread; 
    
    public ProcessThread() {
    	procesando = false;
    	this.setName(ProcessThread.class.getSimpleName());
    }
    
    public static synchronized ProcessThread getInstance(){    	
    	if(instance == null)
    		instance = new ProcessThread();
    	return instance;
    }

	public void run() {
    	while(true){
   		//Si no tengo ninguno es la base para procesar espero
    		synchronized (this) {
    			try {
    			boolean existe= ProcProcesDAO.getExistProcessing();
    			Logger.getLogger(ProcessThread.class).warn("Variables: procesando= "+procesando+" existe= "+existe);
    			//System.out.println("ProcessThread - Variables: procesando= "+procesando+" existe= "+existe);
        		if(procesando || !existe){
    				try {
    					Logger.getLogger(ProcessThread.class).warn("Se queda esperando");
    					instance.wait();
    					//instance.sleep(1000);
    				} catch (InterruptedException e) {
    					log.error(e);
    				}
    				Logger.getLogger(ProcessThread.class).warn("Se levanto por la llegada de un evento");
        		} else {
        			//Cambio el estado para que no se pisen
        			Logger.getLogger(ProcessThread.class).warn("Comienza a procesar");
        			procesando = true;
        			Logger.getLogger(ProcessThread.class).warn("Variable procesando: "+procesando);
        			ProcessWork proceso = new ProcessWork(servlet);
        			Thread thread = new Thread(proceso);
        			//thread = new Thread(proceso);
        			thread.setName(ProcessWork.class.getSimpleName());
        			thread.start();
        		}
    			} catch (HibernateException e) {
    				log.error("No hay coneccion con la base, se limpia el ultimo lote y la session");
    				ProcesadorLote.conjuntoIdsEclo.clear();
    				StandaloneHibernateStrategy.getInstance().destroyTransactions();
    				
				}
			}
    	}
	}

	public boolean isProcesando() {
		return procesando;
	}

	public void setProcesando(boolean estado) {
		Logger.getLogger(ProcessThread.class).warn("ProcessThread - Cambio la variable procesando de "+procesando+" a "+estado);
		procesando = estado;
	}

	public synchronized void despertar() {
		Logger.getLogger(ProcessThread.class).warn("ProcessThread - Despertar. Variable procesando "+procesando);
		if(!procesando){
			Logger.getLogger(ProcessThread.class).warn("ProcessThread - Hace el notify");
			instance.notify();
		}
	}

	public void setServlet(ProcessThreadServlet servlet) {
		this.servlet= servlet;
	}
	/*public void stopProceso(){
		ProcessThread.getInstance().thread.stop();
		//this.thread.stop();
		ProcessThread.getInstance().setProcesando(false);
		ProcessThread.getInstance().despertar();
		System.gc();
	}*/
	
}
