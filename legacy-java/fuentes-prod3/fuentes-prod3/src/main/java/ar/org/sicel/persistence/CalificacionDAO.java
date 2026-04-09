package ar.org.sicel.persistence;


import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.web.Tokens;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Calificacion.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.calificacion.Calificacion
 */
public abstract class CalificacionDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Calificacion object.
     *
     * @param boleta
     * @param fecha
     * @param numeroLactancia
     * @param inicioLactancia
     * @param catPuntaje
     * @param puntaje
     * @param comentarios
     * @param especial
     * @return Calificacion the created object
     */
     public static Calificacion create (java.lang.Integer boleta, java.util.Date fecha, java.lang.Integer numeroLactancia, java.util.Date inicioLactancia, java.lang.String catPuntaje, java.lang.Integer puntaje, java.lang.String comentarios, java.lang.Boolean especial)
     {
         Calificacion object = new Calificacion();

         object.setBoleta (boleta);
         object.setFecha (fecha);
         object.setNumeroLactancia (numeroLactancia);
         object.setInicioLactancia (inicioLactancia);
         object.setCatPuntaje (catPuntaje);
         object.setPuntaje (puntaje);
         object.setComentarios (comentarios);
         object.setEspecial (especial);

        object.setCalificacionDeCaracteristicas(new HashMap());
        object.setCalificacionDeDefectos(new HashMap());
        object.setCalificacionDePartes(new HashMap());
        
         return object;
     }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Calificacion object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Calificacion findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Calificacion object = (Calificacion) session.get(Calificacion.class, id);

        return object;
    }
    public static List findByBoleta(String boleta,Usuario user){
    	Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Calificacion.class);
		criterio.add(Expression.eq("boleta", new Integer(boleta)));
		if (user.getRol().getNombre().equals(Tokens.NOMBREROLSRA)){
			Criteria c2 = criterio.createCriteria("animal");
	        c2.add(Expression.eq("categoria",Animal.CAT_PED));
		}
		return criterio.list();
    }
    public static List findByBoletaYProp(String boleta,Propietario prop){
    	Session session = HibernateFactory.getSession();
    	Criteria criterio = session.createCriteria(Calificacion.class);
		criterio.add(Expression.eq("boleta", new Integer(boleta)));
		if (prop!=null){
	        Criteria c2 = criterio.createCriteria("animal");
	        c2.add(Expression.eq("propietario",prop));
        }
		return criterio.list();
    }
    public static List findByFechaEstancia(Date fecha, Estancia estancia, Calificador calificador, 
    		Usuario dataEntry,Usuario usuario){
    	Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Calificacion.class);
		if (fecha != null){
			criterio.add(Expression.eq("fecha", fecha));
		}
		if (estancia != null){
			criterio.add(Expression.eq("estancia.id", estancia.getId()));
		}
		if (calificador != null){
			criterio.add(Expression.eq("calificador.id", calificador.getId()));
		}
		if (dataEntry != null){
			criterio.add(Expression.eq("dataEntry.id", dataEntry.getId()));
		}
		String nombreRol = usuario.getRol().getNombre();
		if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
			Criteria c2 = criterio.createCriteria("animal");
	        	c2.add(Expression.eq("propietario",usuario.getContacto()));
	        	
		}
		if (Tokens.NOMBREROLSRA.equals(nombreRol)) {
			Criteria c2 = criterio.createCriteria("animal");
	        	c2.add(Expression.eq("categoria",Animal.CAT_PED));
		}
			//criterio.add(Expression.eq("animal.propietario", usuario.getContacto()));		
		return criterio.list();
    }
    public static void update(Calificacion calif) throws HibernateException {
		HibernateFactory.getSession().update(calif);
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
    public static void save(Calificacion calif) throws HibernateException {
		HibernateFactory.getSession().save(calif);
		StandaloneHibernateStrategy.getInstance().commitCurrentSessionCalif();
	}
    public static Integer getPuntajeAnterior(Calificacion ca,Animal animal){
    	 
    			Criteria c =HibernateFactory.getSession().createCriteria(Calificacion.class);
    			c.add(Expression.eq("animal",animal));
    			c.addOrder(Order.desc("fecha"));
    			
    			List calificaciones = c.list();
    			Collections.sort(calificaciones, new ComparadorCalificaciones());
    			Iterator it = calificaciones.iterator();
    			Calificacion anterior=new Calificacion();
    			boolean grabo = false;
    			while(it.hasNext()){
    				Calificacion cali = (Calificacion)it.next();
    				//if((cali.getFecha().before(ca.getFecha()))||cali.getFecha().equals(ca.getFecha())){
    				if(cali.getFecha().before(ca.getFecha())){//||cali.getFecha().equals(ca.getFecha())){
    					anterior = cali;
    					grabo=true;
    				}
    			}
    			if(grabo)
    				return anterior.getPuntaje();
    			else
    				return null;//no tiene anterior
    }
    public static List entreFechasYModelo(Date fechaDesde,Date fechaHasta,String modelo){
    	Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Calificacion.class);
		if (fechaDesde != null){
			criterio.add(Expression.ge("fecha", fechaDesde));
		}
		if (fechaHasta!= null){
			criterio.add(Expression.le("fecha", fechaHasta));
		}
		if (modelo!= null){
			ModeloCalificacion mCalif = ModeloCalificacionDAO.findByPrimaryKey(new Long(modelo));
			criterio.add(Expression.eq("modeloCalificacion", mCalif));
		}
		return criterio.list();
    }
}
