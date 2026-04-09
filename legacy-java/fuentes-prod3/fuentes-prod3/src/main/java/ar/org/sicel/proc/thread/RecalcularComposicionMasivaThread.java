package ar.org.sicel.proc.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

public class RecalcularComposicionMasivaThread extends Thread {
	
	static Logger log = Logger.getLogger(RecalcularComposicionMasivaThread.class);

    private static RecalcularComposicionMasivaThread instance;
        
    public RecalcularComposicionMasivaThread() {
       	this.setName(RecalcularLactanciasThread.class.getSimpleName());
    }
    
    public static synchronized RecalcularComposicionMasivaThread getInstance(){    	
    	if(instance == null)
    		instance = new RecalcularComposicionMasivaThread();
    	return instance;
    }
    public void run() {
		Session session = HibernateFactory.getSession();
		List<Long> animales = AnimalDAO.findAllRecalculoComposicion(session);
		log.warn("cantidad total a actualizar "+ animales.size());
		Iterator it = animales.iterator();
		int contador = 1;
		int limite = 1;
		while(it.hasNext()){
					
			Animal an = AnimalDAO.findByPrimaryKey((Long)it.next());   
			an.calcularComposicionRazaConEventos();
			AnimalDAO.updateAnimal(an);
			
		}
		
		log.warn("Commit start");
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		log.warn("Commit end");
		
    }
    /**es para un animal solo
     * */
    public void run2() {
    	
		Session session = HibernateFactory.getSession();
	
					
			Animal an = AnimalDAO.findByPrimaryKey(5984803L);   
			an.calcularComposicionRazaConEventos();
			AnimalDAO.updateAnimal(an);
			
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		
		
    }
    
}
