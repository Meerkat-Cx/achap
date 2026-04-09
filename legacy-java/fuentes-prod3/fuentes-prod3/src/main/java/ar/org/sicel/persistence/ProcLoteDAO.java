package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.StringUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcLote.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcLote
 */
public abstract class ProcLoteDAO {
    // ---------------- create method --------------------
   static Logger log = Logger.getLogger(ProcLoteDAO.class);
	
	
	
	
	
    /**
     * 
     * @param session
     * @param numLoteEclo
     * @param tEnvio
     * @param tInicioProc
     * @param idEcloInformante
     * @param numLote
     * @return
     * @throws ExcepcionIntegridad
     */ 
    public static ProcLote create(Long numLoteEclo, Date tEnvio, Date tInicioProc, Long idEcloInformante, String nombreSistema, Long idCentroComputo) 
    	throws ExcepcionIntegridad, ErrorFatal {
    	if (tEnvio.after(tInicioProc)&&!(tEnvio.equals(tInicioProc))) 
    		throw new ExcepcionIntegridad(MENSAJES.FECHA_LOTE_INVALIDA, new String[]{StringUtils.formatDate(tEnvio), StringUtils.formatDate(tInicioProc)});
    	CentroDeComputo centroInformante = null;
    	if(idCentroComputo!=null && idCentroComputo.longValue()!=0) {
    		centroInformante = CentroDeComputoDAO.findExistentByPrimaryKey(idCentroComputo);
    		if(!centroInformante.getActivo()) {
    			throw new ExcepcionIntegridad(MENSAJES.CENTRO_INACTIVO, new String[]{centroInformante.getId().toString()});
    		}
    	}
    	Eclo ecloInformante = buscarEclo(idEcloInformante);
    	if(!ecloInformante.getActivo())
    		throw new ExcepcionIntegridad(MENSAJES.ECLO_INACTIVO, new String[]{ecloInformante.getId().toString()});
    	ProcLote p = new ProcLote();
    	Set sistemasEclo = ecloInformante.getSistemas();
    	Sistema sistemaLote =null;
        if(sistemasEclo != null){
        	sistemaLote = SistemaDAO.findByName(nombreSistema);
        	if(sistemasEclo.contains(sistemaLote))
        		p.setSistema(sistemaLote);
        	else 
        		throw new ExcepcionIntegridad(MENSAJES.ECLO_SIN_SISTEMA, new String[]{ecloInformante.getId().toString(),ecloInformante.getNombreContacto(), "no posee el sistema "+nombreSistema+" asociado. Debería pertenecer al sistema que informa el lote de procesamiento"});
        		//throw new ErrorFatal("La eclo ["+ecloInformante.getId().toString()+","+ecloInformante.getNombreContacto()+"], no posee el sistema "+nombreSistema+" asociado. Debería pertenecer al sistema que informa el lote de procesamiento");
        }else
        	throw new ExcepcionIntegridad(MENSAJES.ECLO_SIN_SISTEMA, new String[]{ecloInformante.getId().toString(),ecloInformante.getNombreContacto(), "no posee sistema asociado. Debería pertenecer al sistema que informa el lote de procesamiento"});
        	
        	//throw new ErrorFatal("La eclo ["+ecloInformante.getId().toString()+","+ecloInformante.getNombreContacto()+"], no posee sistema asociado. Debería pertenecer al sistema que informa el lote de procesamiento");
       	
        //p.setIdLoteEclo(numLoteEclo);
        //p.setIdLoteEclo(ecloInformante,numLoteEclo);
    	p.setIdLoteEclo(ecloInformante,sistemaLote,centroInformante,numLoteEclo);
    	p.setEclo(ecloInformante);
    	p.setCentroComputo(centroInformante);
    	
    	p.setTEnvio(tEnvio);
        p.setTInicioProc(tInicioProc);
        p.setProcEstablecimientos(new HashSet());
        p.setProcMsgsses(new HashSet());
        p.setFichasAnimal(new HashSet<FichaAnimalPendiente>());
       return p;
    }
    
    /**
	 * Obtiene la Eclo que envio el lote. 
	 * @param session
	 * @param lote
	 * @return
	 * @throws ExcepcionIntegridad si la eclo no existe en el sistema
	 */
	private static Eclo buscarEclo( Long idEclo) throws ExcepcionIntegridad{
//		Session session = HibernateFactory.getSession();
		Eclo ecloInformante = null;
		try {
			ecloInformante = EcloDAO.findByPrimaryKey(idEclo);
		} catch (HibernateException e) { 
			String msgError ="Error al buscar la eclo: " + idEclo; 
			log.error(msgError,e);
			throw new ErrorFatal(msgError,e);
		}
		if (ecloInformante == null) {
			String msgError ="Eclo no encontrada: " + idEclo; 
			log.error(msgError);
			throw new ExcepcionIntegridad(MENSAJES.ECLO_NO_VALIDA,new String[]{String.valueOf(idEclo)});
		}
		return ecloInformante;
	}
	

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcLote object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcLote findByPrimaryKey(java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	ProcLote object = (ProcLote) session.get(ProcLote.class, id);

        return object;
    }
    
    public static ProcLote findByNumeroLote(Long numero) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcLote.class);
		c.add(Expression.eq("numLote",numero));
		return (ProcLote) c.uniqueResult();
		
	}
    
    public static ProcLote findByNumeroResultado(Long numeroRes) {
		Criteria c = null;
		Evento evt = EventoDAO.findByPrimaryKey(numeroRes);
		if (!(evt instanceof EvtControlAnimal) && !(evt instanceof EvtControlEstablecimiento)) 
			if (evt instanceof EvtAnimal){
				c =HibernateFactory.getSession().createCriteria(ProcEvtAnimal.class);
				c.add(Expression.eq("evtAnimal",evt));
				ProcEvtAnimal p =(ProcEvtAnimal)c.uniqueResult();
				if (p != null)
					return  p.getProcAnimal().getProcEstablecimiento().getProcLote();
				return null;
			}
			else{
				c =HibernateFactory.getSession().createCriteria(ProcEvtEst.class);
				c.add(Expression.eq("evtEstablecimiento",evt));
				ProcEvtEst p =(ProcEvtEst)c.uniqueResult();
				if (p != null)
					return  p.getProcEstablecimiento().getProcLote();
				return null;
			}
		return null;
	}
    
    public static List findByNumeroInformadoAndEclo(Long numeroInf,Eclo eclo) {
    	/*primero prueba de que sea un numero informado de un evento establecimiento*/
		Criteria c =HibernateFactory.getSession().createCriteria(ProcEvtEst.class);
		Criteria c3 = c.createCriteria("procEstablecimiento");
		Criteria c4 = c3.createCriteria("establecimiento");
		c4.add(Expression.eq("eclo", eclo));
		c.add(Expression.eq("idEvtECLO",numeroInf));
		List<ProcEvt> list = c.list();
		if (!list.isEmpty()){
			List<ProcLote> listProc = new ArrayList<ProcLote>();
			for(ProcEvt p:list){
				ProcLote pr = p.getLote();
				listProc.add(pr);
			}
			return  listProc;
		}
		/*si la busqueda anterior da vacia, prueba de que sea un numero informado de un evento animal*/
		c =HibernateFactory.getSession().createCriteria(ProcEvtAnimal.class);
		Criteria c2 = c.createCriteria("procAnimal");
		c3 = c2.createCriteria("procEstablecimiento");
		c4 = c3.createCriteria("establecimiento");
		c4.add(Expression.eq("eclo", eclo));
		c.add(Expression.eq("idEvtECLO",numeroInf));
		list = c.list();
		if (!list.isEmpty()){
			List<ProcLote> listProc = new ArrayList<ProcLote>();
			for(ProcEvt p:list){
				ProcLote pr = p.getLote();
				listProc.add(pr);
			}
			return  listProc;
		}
		return null;
	}
    
    /*select * from PR_LOTE
where id in(select lote_id from AN_FICHA_ANIMAL_PENDIENTE
                   where pendiente = 1)*/
    public static List findByFichas()
    	throws ErrorFatal {
    	Session session = HibernateFactory.getSession();
    	try {
    		final String queryText = "from ProcLote p where p in (select procLote from FichaAnimalPendiente " +
				//"where pendiente= 1 ) order by p.TiFinProc desc";
    		"where pendiente= 1 ) order by p.TiFinProc asc";
    		Query query = session.createQuery(queryText);
    		//query.setEntity("eclo", eclo);
    		//query.setLong("nLote", nLote);
    		return  query.list();
    	} catch (HibernateException e) {
    		log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
			throw new ErrorFatal(e);
		}
    }
    
    public static ProcLote findByECLO( Eclo eclo, Long nLote)
        throws ErrorFatal {
    	Session session = HibernateFactory.getSession();
        try {
			final String queryText = "from ProcLote p where p.eclo = :eclo and p.numLote = :nLote";
			Query query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setLong("nLote", nLote);
			return (ProcLote) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
			throw new ErrorFatal(e);
		}
    }
    
    public static List findAllByECLO( Eclo eclo, Long nLote)
    throws ErrorFatal {
	Session session = HibernateFactory.getSession();
    try {
    	String queryText = null;
		if (nLote != null)
			queryText = "from ProcLote p where p.eclo = :eclo and p.numLote = :nLote";
		else
			queryText = "from ProcLote p where p.eclo = :eclo";
		Query query = session.createQuery(queryText);
		if (nLote != null)
			query.setLong("nLote", nLote);
		query.setEntity("eclo", eclo);
		return query.list();
	} catch (HibernateException e) {
		log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
		throw new ErrorFatal(e);
	}
}
    
    public static ProcLote findByEcloSistemaCentro( Eclo eclo,Sistema sistema,CentroDeComputo centro, Long nLote)
    throws ErrorFatal {
	Session session = HibernateFactory.getSession();
	Query query=null;
    try {
    	if(centro!=null){
			final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.centroComputo = :centro and p.numLote = :nLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setEntity("centro", centro);
			query.setLong("nLote", nLote);
    	}
    	else{
    		final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.numLote = :nLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setLong("nLote", nLote);
    	}
		return (ProcLote) query.uniqueResult();
	} catch (HibernateException e) {
		log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
		throw new ErrorFatal(e);
	}
}
    
    /**Este metodo se usa para "Buscar Lotes Procesados". 
     * Es una copia del metodo "findByEcloSistemaCentro", solo que
     * que la diferencia es que este nuevo devuelve un listado de ProcLotes, y el 
     * viejo solo retornaba uno solo
     * */
    public static List findListByEcloSistemaCentro( Eclo eclo,Sistema sistema,CentroDeComputo centro, Long nLote)
    throws ErrorFatal {
	Session session = HibernateFactory.getSession();
	Query query=null;
    try {
    	if(centro!=null){
			final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.centroComputo = :centro and p.numLote = :nLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setEntity("centro", centro);
			query.setLong("nLote", nLote);
    	}
    	else{
    		final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.numLote = :nLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setLong("nLote", nLote);
    	}
		return query.list();
	} catch (HibernateException e) {
		log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
		throw new ErrorFatal(e);
	}
    }
    
    public static ProcLote findByEcloSistemaCentroId( Eclo eclo,Sistema sistema,CentroDeComputo centro, Long idLote)
    throws ErrorFatal {
	Session session = HibernateFactory.getSession();
	Query query=null;
    try {
    	if(centro!=null){
			final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.centroComputo = :centro and p.id = :iLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setEntity("centro", centro);
			query.setLong("iLote", idLote);
    	}
    	else{
    		final String queryText = "from ProcLote p where p.eclo = :eclo and p.sistema = :sistema and p.id = :iLote";
			query = session.createQuery(queryText);
			query.setEntity("eclo", eclo);
			query.setEntity("sistema", sistema);
			query.setLong("iLote", idLote);
    	}
		return (ProcLote) query.uniqueResult();
	} catch (HibernateException e) {
		log.error("",e);	//error al querer hacer la busqueda, no se puede hacer nada
		throw new ErrorFatal(e);
	}
}
    public static List findAll()
    	throws ErrorFatal {
    	Session session = HibernateFactory.getSession();
    	try {
    		Query query = session.createQuery("from ProcLote");
    		return query.list();    	
    	} catch (HibernateException e) {
    		log.error("",e);    //error al querer hacer la busqueda, no se puede hacer nada
    		throw new ErrorFatal(e);
    	}
    }
    
    public static List<ProcLote> findLoteControl(String ordenieInformado,String controlInformado,String idResultado){
    	try{
	    	String queryText = "select p.id as ID "+ 
							"from pr_ev_estab p,ev_establecimiento a,ev_controlanimal c "+
							"where p.EV_estab = a.id and a.id = c.controlestablecimiento "+
							 (ordenieInformado != null && !ordenieInformado.equals("")  ? "and c.id_ordenie = "+ordenieInformado : "")+
						     (idResultado != null && !idResultado.equals("") ? " and c.CONTROLESTABLECIMIENTO = "+idResultado : "");
			SQLQuery query = HibernateFactory.getSession().createSQLQuery(queryText);
			query.addScalar("ID",Hibernate.LONG);
	        List<Long> listLotes = query.list();
	        List<ProcLote> l = new ArrayList<ProcLote>();
	        for(Long idLote : listLotes){
	        	Criteria c =HibernateFactory.getSession().createCriteria(ProcEvt.class);
	        	c.add(Expression.eq("id",idLote));
	        	if (controlInformado != null && !controlInformado.equals(""))
	        		c.add(Expression.eq("idEvtECLO",new Long(controlInformado)));
	        	ProcEvt proc = ((ProcEvt)c.uniqueResult());
	        	if (proc != null)
	        		l.add(proc.getLote());
	        }
			return  l;
    	}catch(Exception e){
    		e.printStackTrace();
    		return  new ArrayList<ProcLote>();
    	}
    }
    
    


    
}
