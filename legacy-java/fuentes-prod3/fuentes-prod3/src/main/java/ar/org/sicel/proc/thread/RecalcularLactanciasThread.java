package ar.org.sicel.proc.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtAnimalDAO;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

public class RecalcularLactanciasThread extends Thread {
	
	static Logger log = Logger.getLogger(RecalcularLactanciasThread.class);

    private static RecalcularLactanciasThread instance;
        
    public RecalcularLactanciasThread() {
       	this.setName(RecalcularLactanciasThread.class.getSimpleName());
    }
    
    public static synchronized RecalcularLactanciasThread getInstance(){    	
    	if(instance == null)
    		instance = new RecalcularLactanciasThread();
    	return instance;
    }
    public void run() {
		Session session = HibernateFactory.getSession();
		List<Long> animales = AnimalDAO.findAllHembrasConEventosColgados(session);
		log.warn("cantidad total a actualizar "+ animales.size());
		Iterator it = animales.iterator();
		int contador = 1;
		int limite = 1;
		while(it.hasNext()){
			Hembra h = (Hembra)AnimalDAO.findByPrimaryKey((Long)it.next());
			
			EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(h);
			if(ev!=null){
				try {
					log.warn("contador = "+contador+" RC "+h.getRegistroOrigen());
					h.setEstadoRetroactivo(ev.getFecha(),new ArrayList(), null);
					
				} catch (ExcepcionIntegridad e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				contador ++;
				animales.remove(h);
				limite++;
				if(limite == 1000){
					log.warn("Commit start");
					StandaloneHibernateStrategy.getInstance().commitCurrentSession();
					log.warn("Commit end");
					limite =1;
				}
				
				
			}
		}
		
		log.warn("Commit start");
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		log.warn("Commit end");
		
    }
    public void run2() {
		Session session = HibernateFactory.getSession();
		Long maxIdAnimal = AnimalDAO.getMaxIdHembraIniciaLactancia(session);
		//Long id = AnimalDAO.getMinIdHembraIniciaLactancia(session);
		Long id =  new Long(4687198L);
		List<EvtLactancia> lactancias = null;
		Long ultimo = null;
		Long cantCommits = new Long(0);
		Long errores = new Long(0);
		while (id <= maxIdAnimal){
			log.warn("Recuperando hembra con ID " + id.toString());
			session = HibernateFactory.getSession();
			List<Long> animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervalo("HOLA",new Integer(id.toString()),new Integer(1000),session);
			ultimo = animales.get(animales.size()-1);
			for(int k = 0;k<animales.size();k++){
					//Hembra hembra = (Hembra)AnimalDAO.findByPrimaryKey(new Long(907823));
					Hembra hembra = (Hembra)AnimalDAO.findByPrimaryKey(animales.get(k));
					log.warn("Procesando hembra "+ hembra.toString());
					lactancias = hembra.getLactanciasSicelTres();
					for(int i = 0;i<lactancias.size();i++){
						EvtLactancia lac = lactancias.get(i);
						try{
							if(hembra.borrarLactanciasColgadas(lac)){
								
								//recalcularEventos
								EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(hembra);
								//Lactancia l = he.getLactanciaNumero(numeroLactancia.intValue());
								if(ev!=null){
									log.warn("recalculando eventos ");
									hembra.setEstadoRetroactivoParticular(ev.getFecha(), new ArrayList(), null);
									log.warn("fin recalculando eventos ");
									log.warn("Commit start recalculados");
									StandaloneHibernateStrategy.getInstance().commitCurrentSession();
									log.warn("Commit end recalculados");
									break;
								}
							}
							else
								lac.recalcularLactanciaParticular(hembra,lac);
							cantCommits++;
						} catch (Exception e){
							e.printStackTrace();
							log.warn("Se recalculan los eventos de la hembra con id "+hembra.getId().toString());
							EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(hembra);
							if(ev!=null)
								try {
									hembra.setEstadoRetroactivo(ev.getFecha(), new ArrayList(), null);
									lac.recalcularLactancia(hembra, lac);
									cantCommits++;
								} catch (ExcepcionIntegridad e1) {
									e1.printStackTrace();
									errores++;
									log.warn("No se pudo recalcular la lactancia con id "+lac.getId().toString()+" de la hembra "+hembra.getId().toString());
								}
							errores++;
							log.warn("No se pudo recalcular la lactancia con id "+lac.getId().toString()+" de la hembra "+hembra.getId().toString());
						}
					}
					lactancias.clear();//limpio la memoria para que no tire StackOverflow ¿esta bien? ¿anda esto?
			}
			log.warn("Commit start");
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			log.warn("Commit end");
			//session = HibernateFactory.getSession();
			System.gc();
			log.warn("Errores acumulados: "+errores.toString());
			id = ultimo+1;
		}
	}
}
