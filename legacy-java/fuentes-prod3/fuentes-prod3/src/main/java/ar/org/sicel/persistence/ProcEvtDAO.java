/**
 * Attention: Generated source! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;

/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcEvt.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcEvt
 */
public abstract class ProcEvtDAO {
   // ---------------- create method --------------------

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcEvt object by its primary key.
     * In Hibernate, this is just a call to load().
     *
     */
    public static ProcEvt findByPrimaryKey ( java.lang.Long id)
        throws org.hibernate.HibernateException
    {
    	Session session = HibernateFactory.getSession();
        ProcEvt object = (ProcEvt) session.load(ProcEvt.class, id);
        return object;
    }
    
    /**
     * Esta implementacion seguramente cambiara despues de la primera entrega.
     * 
     * @param eclo
     * @param idEvtEclo
     * @return
     * @throws org.hibernate.HibernateException
     */
    public static ProcEvt findByEclo(Eclo eclo,Sistema sistema,CentroDeComputo centro, Long idEvtEclo) throws org.hibernate.HibernateException {
    	ProcEvt p = null;
    	p = ProcEvtEstDAO.findByEclo(eclo,sistema,centro,idEvtEclo);
    	if (p == null)
    		p = ProcEvtAnimalDAO.findByEclo(eclo,sistema,centro,idEvtEclo);
    	return p;
    }
    public static Long cantidadEventos(List eclos,Establecimiento unEstablecimiento,Date f1,Date f2, String nombreEvento) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	    	
    	 try {
			String queryText = "";
			if(nombreEvento.equalsIgnoreCase("EvtAlta") || nombreEvento.equalsIgnoreCase("EvtBajaEvento") || nombreEvento.equalsIgnoreCase("EvtControlEstablecimiento"))
			{//eventos establecimiento
	        	if(unEstablecimiento!=null){//si es un tambo especifico
	        	/*	queryText = "select count(*) from Evento e, "+nombreEvento+" es " +
	    			"where e.id= es.id and e.establecimiento = :estab and e.fecha >= :f1 and e.fecha <=:f2";
	        	*/
	        	  	queryText = "select count(*) from Evento e, "+nombreEvento+" es, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
	        	  				"where e.id= es.id " +
	        	  				"and e.establecimiento = :estab " +
	        	  				"and pee.evtEstablecimiento = e.id " +
	        	  				"and pee.procEstablecimiento=pe.id " +
	        	  				"and pe.procLote=pl.id " +
	        	  				"and pp.procLote=pl.id " +
	        	  				" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
	        	  				"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
	        	
	        	}
	        	else
	        	//una o varias eclo y todos sus tambos
	        /*		queryText = "select count(*) from Evento e, "+nombreEvento+" es " +
				"where e.id= es.id and  e.fecha >= :f1 and e.fecha <=:f2 and e.establecimiento in(select id from Establecimiento where eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +"))";
	        	*/
	        		queryText = "select count(*) from Evento e, "+nombreEvento+" es, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
	    						"where e.id= es.id " +
	    						"and e.establecimiento in(select id from Establecimiento where eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +")) " +
	    						"and pee.evtEstablecimiento = e.id " +
	    						"and pee.procEstablecimiento=pe.id " +
	    						"and pe.procLote=pl.id " +
	    						"and pp.procLote=pl.id " +
	    						" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
	    						"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
			}
			else
			{//eventos animal
				if(unEstablecimiento!=null){//si es un tambo especifico
		        	  	queryText = "select count(*) from Evento e, "+nombreEvento+" es, ProcEvtAnimal pea, ProcAnimal pa, ProcEstablecimiento pe,ProcLote pl,ProcProces pp "+
		        	  				" where e.id= es.id " +
		        	  				" and e.establecimiento = :estab " +
		        	  				" and e.id = pea.evtAnimal" +
		        	  		        " and pea.procAnimal= pa.id " +
		        	  		        " and pa.procEstablecimiento=pe.id" +
		        	  		        " and pe.procLote=pl.id " +
		    						" and pp.procLote=pl.id " +
		        	  		        " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
		        	  		        "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
		        	}
				else
		        		queryText = "select count(*) from Evento e, "+nombreEvento+" es, ProcEvtAnimal pea, ProcAnimal pa, ProcEstablecimiento pe,ProcLote pl,ProcProces pp "+
		    						" where e.id= es.id " +
		    						" and e.establecimiento in(select id from Establecimiento where eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +")) " +
		    						" and e.id = pea.evtAnimal" +
		        	  		        " and pea.procAnimal= pa.id " +
		        	  		        " and pa.procEstablecimiento=pe.id" +
		    						" and pe.procLote=pl.id " +
		    						" and pp.procLote=pl.id " +
		    						" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
		    						"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
			}
        		
        		
        		
			Query query = session.createQuery(queryText);
			if(unEstablecimiento!=null)
				query.setEntity("estab", unEstablecimiento);			
			return  (Long)((Integer) query.uniqueResult()).longValue();
		} catch (HibernateException e) {
			
			throw new ErrorFatal(e);
		}
    }
    public static Long cantidadSexoEnEventos(List eclos,Establecimiento estab, Date f1, Date f2,boolean esHembra,boolean tipoAlta) {
    	Session session = HibernateFactory.getSession();
    	//Long altas = 0L;
    	//Long partos = 0L;
   	 try {
			String queryText1 = "";
			String queryText2 = "";
			SQLQuery query = null;
       	if(estab!=null){//si es un tambo especifico
       		if(esHembra )
	       		{
       			if(tipoAlta){
	       		/*	queryText1 = "select count(*) as cantidad from Animal an, Evento e, "+"EvtAlta"+" es " +
		   			"where an.class = Hembra and an.id = es.animal and e.id= es.id and e.establecimiento = :estab and e.fecha >= :f1 and e.fecha <=:f2"; */
       				queryText1 = "select count(*) as cantidad from Animal an, Evento e, "+"EvtAlta"+" es, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
       								"where an.class = Hembra" +
       								" and an.id = es.animal" +
       								" and e.id= es.id " +
       								" and e.establecimiento = :estab " +
       								" and pee.evtEstablecimiento = e.id " +
       								" and pee.procEstablecimiento=pe.id " +
       								" and pe.procLote=pl.id " +
       								" and pp.procLote=pl.id " +
       								" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
       								"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
 	       			}
       			else{
		       	/*	query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, ev_evento ee, ev_reproduccion ea, ev_cria ec" +
		                    " where an.eshembra = 1" +
				    " and  ee.establecimiento ="+ estab.getId() +
				    " and  ea.id = ee.id " +
				    " and ea.id = ec.EVT_REPRODUCCION" +
				    " and  ec.cria = an.id" +
				    " and ea.ASOCIASICEL1 = 0" +
				    " and to_char(ee.fecha,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
				    "' and to_char(ee.fecha,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'" );*/
       				query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, ev_evento ee, ev_reproduccion ea, ev_cria ec, pr_ev_animal pea, pr_animal pa, pr_establecimiento pe, pr_lote pl, pr_proces pp "+
		                    " where an.eshembra = 1" +
		                    " and  ee.establecimiento ="+ estab.getId() +
		                    " and  ea.id = ee.id " +
		                    " and ea.id = ec.EVT_REPRODUCCION" +
		                    " and  ec.cria = an.id" +
		                    " and ea.ASOCIASICEL1 = 0" +
		                    " and not exists (select * from ev_alta where animal = an.id)" +
		                    " and ea.id = pea.EV_ANIM" +
		                    " and pea.PR_ANIMAL= pa.ID " +
		                    " and pa.PR_ESTAB=pe.ID" +
		                    " and pe.lote=pl.id " +
		                    " and pp.pr_lote=pl.id " +
		                    " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
		                    "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'");
       			}
       		}
       		else{
       			if(tipoAlta){
       			/*queryText1 = "select count(*) as cantidad from Animal an, Evento e, "+"EvtAlta"+" es " +
	   			"where an.class = Macho and an.id = es.animal and e.id= es.id and e.establecimiento = :estab and e.fecha >= :f1 and e.fecha <=:f2";*/
       				queryText1 = "select count(*) as cantidad from Animal an, Evento e, "+"EvtAlta"+" es, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
    	   			"where an.class = Macho " +
    	   			"and an.id = es.animal " +
    	   			"and e.id= es.id " +
    	   			"and e.establecimiento = :estab " +
    	   			"and pee.evtEstablecimiento = e.id " +
	    			"and pee.procEstablecimiento=pe.id " +
	    			"and pe.procLote=pl.id " +
	    			"and pp.procLote=pl.id " +
	    		    " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
				    "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
       			}
       			else{
	       		/*query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, ev_evento ee, ev_reproduccion ea, ev_cria ec" +
	                    " where an.eshembra = 0" +
			    " and  ee.establecimiento ="+ estab.getId() +
			    " and  ea.id = ee.id " +
			    " and ea.id = ec.EVT_REPRODUCCION" +
			    " and  ec.cria = an.id" +
			    " and ea.ASOCIASICEL1 = 0" +
			    " and to_char(ee.fecha,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
			    "' and to_char(ee.fecha,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'" );*/
       				query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, ev_evento ee, ev_reproduccion ea, ev_cria ec, pr_ev_animal pea, pr_animal pa, pr_establecimiento pe, pr_lote pl, pr_proces pp "+
    	                    " where an.eshembra = 0" +
    	                    " and  ee.establecimiento ="+ estab.getId() +
    	                    " and  ea.id = ee.id " +
    	                    " and ea.id = ec.EVT_REPRODUCCION" +
    	                    " and  ec.cria = an.id" +
    	                    " and ea.ASOCIASICEL1 = 0" +
    	                    " and not exists (select * from ev_alta where animal = an.id)" +
		                    " and ea.id = pea.EV_ANIM" +
		                    " and pea.PR_ANIMAL= pa.ID " +
		                    " and pa.PR_ESTAB=pe.ID" +
		                    " and pe.lote=pl.id " +
		                    " and pp.pr_lote=pl.id " +
		                    " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
		                    "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'");
       			}
       		}
       		
       	}
       	else{
       		//una o varias eclo y todos sus tambos
       		if(esHembra){
       			if(tipoAlta){
       	/*	queryText1 = "select count(*) from Animal a, Establecimiento est, EvtAlta ea, Evento e" +
       		" where a.class = Hembra and e.establecimiento = est.id and ea.id = e.id and ea.animal = a.id and  e.fecha >= :f1 and e.fecha <=:f2 and est.eclo  in("+ eclos.toString().substring(1,eclos.toString().length()-1) +")";*/
       				queryText1 = "select count(*) from Animal a, Establecimiento est, EvtAlta ea, Evento e, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
       	       		" where a.class = Hembra " +
       	       		"and e.establecimiento = est.id " +
       	       		"and ea.id = e.id " +
       	       		"and ea.animal = a.id " +
       	       		"and est.eclo  in("+ eclos.toString().substring(1,eclos.toString().length()-1) +") "+
       	       		"and pee.evtEstablecimiento = e.id " +
       	       		"and pee.procEstablecimiento=pe.id " +
       	       		"and pe.procLote=pl.id " +
       	       		"and pp.procLote=pl.id " +
       	       		" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
       	       		"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";

       			}
       			else{
	       		/*query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, en_establecimiento tam,ev_evento ee, ev_reproduccion ea, ev_cria ec" +
	                    " where an.eshembra = 1" +
			    " and  ee.establecimiento = tam.id" +
			    " and  ea.id = ee.id " +
			    " and ea.id = ec.EVT_REPRODUCCION" +
			    " and  ec.cria = an.id" +
			    " and ea.ASOCIASICEL1 = 0" +
			    " and to_char(ee.fecha,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
			    "' and to_char(ee.fecha,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd") +
			    "' and  tam.eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +")" );*/
       				query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, en_establecimiento tam,ev_evento ee, ev_reproduccion ea, ev_cria ec, pr_ev_animal pea, pr_animal pa, pr_establecimiento pe, pr_lote pl, pr_proces pp "+
    	                    " where an.eshembra = 1" +
    	                    " and  ee.establecimiento = tam.id" +
    	                    " and  ea.id = ee.id " +
    	                    " and ea.id = ec.EVT_REPRODUCCION" +
    	                    " and  ec.cria = an.id" +
    	                    " and ea.ASOCIASICEL1 = 0" +
    	                    " and  tam.eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +") " +
    	                    " and not exists (select * from ev_alta where animal = an.id)" +
		                    " and ea.id = pea.EV_ANIM" +
		                    " and pea.PR_ANIMAL= pa.ID " +
		                    " and pa.PR_ESTAB=pe.ID" +
		                    " and pe.lote=pl.id " +
		                    " and pp.pr_lote=pl.id " +
		                    " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
		                    "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'");
       			}
       		}
       		else{
       			if(tipoAlta){
       		/*	queryText1 = "select count(*) from Animal a, Establecimiento est, EvtAlta ea, Evento e" +
           		" where a.class = Macho and e.establecimiento = est.id and ea.id = e.id and ea.animal = a.id and  e.fecha >= :f1 and e.fecha <=:f2 and est.eclo  in("+ eclos.toString().substring(1,eclos.toString().length()-1) +")";*/
       				queryText1 = "select count(*) from Animal a, Establecimiento est, EvtAlta ea, Evento e, ProcEvtEst pee,ProcEstablecimiento pe,ProcLote pl,ProcProces pp " +
               		" where a.class = Macho " +
               		"and e.establecimiento = est.id " +
               		"and ea.id = e.id " +
               		"and ea.animal = a.id " +
               		"and est.eclo  in("+ eclos.toString().substring(1,eclos.toString().length()-1) +") "+
               		"and pee.evtEstablecimiento = e.id " +
       	       		"and pee.procEstablecimiento=pe.id " +
       	       		"and pe.procLote=pl.id " +
       	       		"and pp.procLote=pl.id " +
       	       		" and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
       	       		"' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'";
       			}
       			else{
	           		query  = session.createSQLQuery("select count(*) as cantidad from an_animal an, en_establecimiento tam,ev_evento ee, ev_reproduccion ea, ev_cria ec, pr_ev_animal pea, pr_animal pa, pr_establecimiento pe, pr_lote pl, pr_proces pp "+
	                        " where an.eshembra = 0" +
	    		    " and  ee.establecimiento = tam.id" +
	    		    " and  ea.id = ee.id " +
	    		    " and ea.id = ec.EVT_REPRODUCCION" +
	    		    " and  ec.cria = an.id" +
	    		    " and ea.ASOCIASICEL1 = 0" +
	    		    " and  tam.eclo in ("+ eclos.toString().substring(1,eclos.toString().length()-1) +") " +
	    		    " and not exists (select * from ev_alta where animal = an.id)" +
                    " and ea.id = pea.EV_ANIM" +
                    " and pea.PR_ANIMAL= pa.ID " +
                    " and pa.PR_ESTAB=pe.ID" +
                    " and pe.lote=pl.id " +
                    " and pp.pr_lote=pl.id " +
                    " and to_char(pp.fechaEntrada,'yyyy/MM/dd')>= '"+ DateUtils.format(f1, "yyyy/MM/dd") + 
                    "' and to_char(pp.fechaEntrada,'yyyy/MM/dd')<='"+ DateUtils.format(f2, "yyyy/MM/dd")+"'");
       			}
       		}
       		
       	}
       	if(tipoAlta){
       		Query query1 = session.createQuery(queryText1);
			if(estab!=null)
				query1.setEntity("estab", estab);			
			return(Long)((Integer) query1.uniqueResult()).longValue(); 
       	}
       	else{
			query.addScalar("cantidad", Hibernate.LONG);
			return (Long) query.uniqueResult();
			
			//System.out.println("HA"+ altas + " HP"+ partos);
			//return altas + partos;
			//cantidad =  altas + partos;
       	}
		} catch (HibernateException e) {
			
			throw new ErrorFatal(e);
		}
	}

	    
  

}
