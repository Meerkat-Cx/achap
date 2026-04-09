package ar.org.sicel.web.altaEdicion.actions;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.EvtLactanciaMigrada;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.LactanciaGeneticaDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

public class ActualizarLactanciasThread extends Thread{
	
	private static Logger log = Logger.getLogger(EvtLactancia.class);
	private static Long a_partir = null;
	

	public ActualizarLactanciasThread(int priority){
		this.setPriority(priority);
		
	}
	public ActualizarLactanciasThread(int priority,Long aPartir){
		this.setPriority(priority);
		this.a_partir = aPartir;
		
	}
	
	public void run() {
		log.warn("DEPLOYO BIEN miercoles 10/8");
		Session session = HibernateFactory.getSession();
		//Long maxIdAnimal = AnimalDAO.getMaxIdHembraIniciaLactancia(session);
		//Long id = AnimalDAO.getMinIdHembraIniciaLactancia(session);
		Long ultimo = null;
		int cantCommits = 0;
		Integer errores = new Integer(0);
		List<Lactancia> lactancias = null;
		Integer cotaInf= 0;
		Integer cotaSup =1000;
		//List<Long> animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervalo("HOLA",cotaInf,cotaSup,session);

		List<Long> animales = null;
		if(a_partir!= null)
			animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervaloDesde("HOLA",cotaInf,cotaSup,session,a_partir);
		else
			animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervalo("HOLA",cotaInf,cotaSup,session);

		while (animales!=null && !animales.isEmpty()){
			Iterator it = animales.iterator();
			while (it.hasNext()){
				Hembra hembra = (Hembra)AnimalDAO.findByPrimaryKey((Long)it.next());
				log.warn("Procesando hembra "+ hembra.toString());
				lactancias = hembra.getLactancias();
				EvtLactancia enCurso = (EvtLactancia)hembra.getLactanciaEnCursoParaGeneticas();
				if (enCurso != null)
					lactancias.add(enCurso);
				for(int i = 0;i<lactancias.size();i++){
					Evento lactancia = (Evento)lactancias.get(i);
					try{
						if (lactancia instanceof EvtLactancia){
							EvtLactancia lac = (EvtLactancia)lactancia;
							Evento ev = lac.isEsCerrada() ? EventoDAO.findEventoIdEventoDeDependiente(hembra, lac) : null;
							if ((lac.isEsCerrada() && ev!=null) || (!lac.isEsCerrada())){
								log.warn("Lactancia nro "+(new Integer(i)).toString()+" de la Hembra ID: "+hembra.getId().toString()+" ID Lactancia: "+(lac.getId() != null ? lac.getId().toString() : "lactancia en curso"));
								LactanciaGeneticaDAO.insertNew(lac,hembra,enCurso,lactancias);
								cantCommits++;
								log.warn((new Integer(cantCommits)).toString());
							}
						}
						else{
							EvtLactanciaMigrada lac = (EvtLactanciaMigrada)lactancia;
							/*if (lac.getLeche() != null && lac.getLeche() != 0 &&
								lac.getProteinasAbsoluto() != null && lac.getProteinasAbsoluto() != 0 &&
								lac.getGrasaAbsoluto() != null && lac.getGrasaAbsoluto() != 0){*/
							if (lac.getLeche() != null && lac.getLeche() != 0 ){
								log.warn("Lactancia nro "+(new Integer(i)).toString()+" de la Hembra ID: "+hembra.getId().toString()+" ID Lactancia: "+(lac.getId() != null ? lac.getId().toString() : "lactancia en curso"));
								LactanciaGeneticaDAO.insertMigradaNew(lac,hembra,enCurso,lactancias);
								cantCommits++;
								log.warn((new Integer(cantCommits)).toString());
							}
						}
					} catch (Exception e){
						e.printStackTrace();
						log.warn("No se pudo insertar la lactancia con id "+lactancia.getId().toString()+" de la hembra "+hembra.getId().toString());
						errores++;
					}
				}
			}
			log.warn("Commit start desde "+ cotaInf + "hasta "+cotaSup );
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
			log.warn("Commit end");
			session = HibernateFactory.getSession();
			cantCommits = 0;
			System.gc();
			log.warn("Errores acumulados: "+errores.toString());
			
			cotaInf = cotaSup;
			cotaSup = cotaSup+1000;
			if(a_partir!= null)
				animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervaloDesde("HOLA",cotaInf,cotaSup,session,a_partir);
			else
				animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervalo("HOLA",cotaInf,cotaSup,session);
			//animales = AnimalDAO.findHembrasInicianLactanciaEntreIntervaloDesde("HOLA",cotaInf,cotaSup,session,6075107L);

			
		}
			
		
	}
	
	/*Este es para probar de un solo animal*/
	public void run5() {
		log.warn("DEPLOYO BIEN miercoles 10/8");
		Session session = HibernateFactory.getSession();
		int cantCommits = 0;
		Integer errores = new Integer(0);
		List<Lactancia> lactancias = null;
		Hembra hembra = (Hembra)AnimalDAO.findByPrimaryKey(4820237L);
				log.warn("Procesando hembra "+ hembra.toString());
				lactancias = hembra.getLactancias();
				EvtLactancia enCurso = (EvtLactancia)hembra.getLactanciaEnCursoParaGeneticas();
				if (enCurso != null)
					lactancias.add(enCurso);
				for(int i = 0;i<lactancias.size();i++){
					Evento lactancia = (Evento)lactancias.get(i);
					try{
						if (lactancia instanceof EvtLactancia){
							EvtLactancia lac = (EvtLactancia)lactancia;
							Evento ev = lac.isEsCerrada() ? EventoDAO.findEventoIdEventoDeDependiente(hembra, lac) : null;
							if ((lac.isEsCerrada() && ev!=null) || (!lac.isEsCerrada())){
								log.warn("Lactancia nro "+(new Integer(i)).toString()+" de la Hembra ID: "+hembra.getId().toString()+" ID Lactancia: "+(lac.getId() != null ? lac.getId().toString() : "lactancia en curso"));
								LactanciaGeneticaDAO.insertNew(lac,hembra,enCurso,lactancias);
								cantCommits++;
								log.warn((new Integer(cantCommits)).toString());
							}
						}
						else{
							EvtLactanciaMigrada lac = (EvtLactanciaMigrada)lactancia;
							if (lac.getLeche() != null && lac.getLeche() != 0 &&
								lac.getProteinasAbsoluto() != null && lac.getProteinasAbsoluto() != 0 &&
								lac.getGrasaAbsoluto() != null && lac.getGrasaAbsoluto() != 0){
								log.warn("Lactancia nro "+(new Integer(i)).toString()+" de la Hembra ID: "+hembra.getId().toString()+" ID Lactancia: "+(lac.getId() != null ? lac.getId().toString() : "lactancia en curso"));
								LactanciaGeneticaDAO.insertMigradaNew(lac,hembra,enCurso,lactancias);
								cantCommits++;
								log.warn((new Integer(cantCommits)).toString());
							}
						}
					} catch (Exception e){
						e.printStackTrace();
						log.warn("No se pudo insertar la lactancia con id "+lactancia.getId().toString()+" de la hembra "+hembra.getId().toString());
						errores++;
					}
				}
		
			
			
		}
}

