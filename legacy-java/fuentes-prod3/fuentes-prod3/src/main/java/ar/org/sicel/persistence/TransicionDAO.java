package ar.org.sicel.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * <p>
 * Factory class. Is able to find and create objects of type Transicion.
 * Hibernate inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.Transicion
 */

public class TransicionDAO {
    // ---------------- create method --------------------
	
	static Map mapa_transiciones; 
	

    public static Transicion create(String estadoOrigen, String estadoDestino,
            String evento, ProcCodMsg codigoMensaje, Byte nivelError,
            Boolean finalizaLactancia, Boolean iniciaLactancia) {
        Transicion object = new Transicion();
        object.setEstadoOrigen(estadoOrigen);
        object.setEstadoDestino(estadoDestino);
        object.setEvento(evento);
        object.setCodigoMensaje(codigoMensaje);
        object.setNivelError(nivelError);
        object.setIniciaLactancia(iniciaLactancia);
        object.setFinalizaLactancia(finalizaLactancia);
        return object;
    }

    // ---------------- finder methods --------------------

    public static Transicion findByPrimaryKey(java.lang.Long id)
            throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        Transicion object = (Transicion) session.get(Transicion.class, id);
        return object;
    }
    
    /*

    public static Transicion findByOriEvtIniciaLact(String estadoOrigen,
            String evento, boolean iniciaLactancia) throws HibernateException {
        Session session = HibernateFactory.getSession();
        String queryText = "from Transicion t where t.estadoOrigen= :estadoOrigen and t.evento = :evento and t.iniciaLactancia=:iniciaLactancia";
        Query query = session.createQuery(queryText);
        query.setParameter("estadoOrigen", estadoOrigen, Hibernate.STRING);
        query.setParameter("evento", evento, Hibernate.STRING);
        query.setParameter("iniciaLactancia", iniciaLactancia,
                Hibernate.BOOLEAN);
        return (Transicion) query.uniqueResult();
    }

    public static Transicion findByOriEvt(String estadoOrigen,
            String evento) throws HibernateException {
        Session session = HibernateFactory.getSession();
        String queryText = "from Transicion t where t.estadoOrigen= :estadoOrigen and t.evento = :evento";
        Query query = session.createQuery(queryText);
        query.setParameter("estadoOrigen", estadoOrigen, Hibernate.STRING);
        query.setParameter("evento", evento, Hibernate.STRING);
        return (Transicion) query.uniqueResult();
    }
    
    */

    /**
     * @return
     * @throws HibernateException 
     */
    public static List findAll() throws HibernateException {
        Session session = HibernateFactory.getSession();
        Query query = session.createQuery("from Transicion");
        return query.list();       
    }
    
    
    
    
    @SuppressWarnings("unchecked")
	public static void buildCacheTransiciones() {
    	Iterator it;
		try {
			it = findAll().iterator();
		} catch (HibernateException e) {
			throw new ErrorFatal("Error al querer armar el cache de transiciones - TRANSICIONDAO",e);
		}
    	mapa_transiciones = new HashMap();
    	while (it.hasNext()) {
    		Transicion t = (Transicion)it.next();
    		List tr; 
    		if (!mapa_transiciones.containsKey(t.getEstadoOrigen())) {
    			tr = new LinkedList();
    			mapa_transiciones.put(t.getEstadoOrigen(), tr);
    		} else {
    			tr = (List)mapa_transiciones.get(t.getEstadoOrigen());
    		}
    		tr.add(t);
    	}
    }
    
    
    public static Transicion findByOriEvtIniciaLact(String estadoOrigen,
            String evento, boolean iniciaLactancia) throws HibernateException {
    	if (mapa_transiciones == null)
    		buildCacheTransiciones();
    	List l = (List)mapa_transiciones.get(estadoOrigen);
    	if (l == null)
    		return null;
    	
    	Iterator it = l.iterator();
    	Transicion encontrada = null;
    	while (it.hasNext() && (encontrada == null)) {
    		Transicion t = (Transicion)it.next();
    		if ((t.getEvento().equals(evento)) && (t.getIniciaLactancia() == iniciaLactancia))
    			encontrada = t;
    	}
    	return encontrada;
    	
    }

    public static Transicion findByOriEvt(String estadoOrigen,
            String evento) throws HibernateException {
    	if (mapa_transiciones == null)
    		buildCacheTransiciones();
    	
    	List l = (List)mapa_transiciones.get(estadoOrigen);
    	if (l == null)
    		return null;
    	
    	Iterator it = l.iterator();
    	Transicion encontrada = null;
    	while (it.hasNext() && (encontrada == null)) {
    		Transicion t = (Transicion)it.next();
    		if ((t.getEvento().equals(evento)))
    			encontrada = t;
    	}
    	return encontrada;
    }

	public static Transicion findByEventoEstado(String estadoOrigen, String estadoDestino) {
    	if (mapa_transiciones == null)
    		buildCacheTransiciones();
    	
    	List l = (List)mapa_transiciones.get(estadoOrigen);
    	if (l == null)
    		return null;
    	
    	Iterator it = l.iterator();
    	Transicion encontrada = null;
    	// String clazzAlPedo = (new EvtEstado()).getClazzParaTransicion();
    	String clazzAlPedo = Evento.EVT_TIPO_EST;
    	while (it.hasNext() && (encontrada == null)) {
    		Transicion t = (Transicion)it.next();
    		if (t.getEvento().equals(clazzAlPedo) && t.getEstadoDestino().equals(estadoDestino) )
    			encontrada = t; //si es un evento estado y va al destino especificado, es este
    	}
		return encontrada;
	}

	public static Transicion findByEvtPrenez(String estadoOrigen, Boolean esPositivo) {
		if (mapa_transiciones == null)
    		buildCacheTransiciones();
		
		List l = (List)mapa_transiciones.get(estadoOrigen);
    	if (l == null)
    		return null;
    	
    	Iterator it = l.iterator();
    	Transicion encontrada = null;
    	String clazzAlPedo = Evento.EVT_TIPO_PRE;
    	String estadoEsperado;
    	if (estadoOrigen.equals(Animal.SECA_SERVIDA)||estadoOrigen.equals(Animal.SECA_VACIA)||estadoOrigen.equals(Animal.SECA_PRENIADA)) {
    		if (esPositivo)
    			estadoEsperado = Animal.SECA_PRENIADA;
    		else
    			estadoEsperado = Animal.SECA_VACIA;
    	} else {
    		if (esPositivo)
    			estadoEsperado = Animal.PRODUCCION_PRENIADA;
    		else
    			estadoEsperado = Animal.PRODUCCION_VACIA;
    	}
    	
    	
    	
    	while (it.hasNext() && (encontrada == null)) {
    		Transicion t = (Transicion)it.next();
    		if (t.getEvento().equals(clazzAlPedo) && t.getEstadoDestino().equals(estadoEsperado) )
    			encontrada = t; //si es un evento prenez y va al destino especificado, es este
    	}
    	
    	return encontrada;
    	
	}

}
