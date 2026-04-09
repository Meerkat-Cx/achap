/**
 * Attention: Generated source! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.thread.ProcessThread;
import ar.org.sicel.upload.dao.BajadaDAO;
import ar.org.sicel.upload.dao.ConstantsUpload;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;



/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcProces.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcProces
 */
public abstract class ProcProcesDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) ProcProces object.
     *
     * @param usuario
     * @param fecha
     * @return ProcProces the created object
     */
  /*  public static ProcProces create(java.lang.String usuario,
        java.util.Date fecha) {
        ProcProces object = new ProcProces();

        object.setUsuario(usuario);
        object.setFecha(fecha);

        object.setProcMsgsses(new HashSet());

        return object;
        
    }*/
	public static ProcProces create(Usuario usuario/*,
	        java.util.Date fecha*/) {
	        ProcProces object = new ProcProces();

	        object.setUsuario(usuario);
	   //     object.setFecha(fecha);

	        object.setProcMsgsses(new HashSet());

	        return object;
	}
	
    
    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcProces object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcProces findByPrimaryKey(java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcProces object = (ProcProces) session.get(ProcProces.class, id);
        Hibernate.initialize(object.getProcMsgsses());
        return object;
    }
    public static ProcProces load(Long i) {
		return (ProcProces)HibernateFactory.getSession().load(ProcProces.class,i);
		
	}
    
    public static List getAllProcess() {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.addOrder(Order.desc("fechaSalida"));
		c.addOrder(Order.desc("fechaEntrada"));
		return c.list();
		
	} 
    public static List getAllProcessEntreFechas(Date inicio, Date fin,/* Usuario user,*/Set users, String rol) {
    	
    	Criteria c=HibernateFactory.getSession().createCriteria(ProcProces.class);
    	c.add(Expression.between("fecha", inicio, fin));
    	//c.add(Expression.eq("estado",ConstantsUpload.DOWNLOADED));
    	Set states = new HashSet();
   	   	states.add(ConstantsUpload.PROCESSED);
   	   	states.add(ConstantsUpload.DOWNLOADED);
   	   	
		c.add(Expression.in("estado",states));
    	
    	/*if (rol==Tokens.NOMBREROLADMINISTRADOR)
    		c.add(Expression.eq("descargadaAdmin",true));
		else{
			c.add(Expression.eq("usuario",user));
			c.add(Expression.eq("descargadaEclo",true));
		}*/
		//if (rol==Tokens.NOMBREROLPROVEEDOR)
			//c.add(Expression.eq("usuario",user));
		if(!users.isEmpty())
			c.add(Expression.in("usuario",users));
    	c.addOrder(Order.asc("fecha"));
    	return c.list();
 
			
	} 
    /**
     * Metodo qeu se usa para traer de la base los lotes procesados y que todavia no fueron 
     * descargados por la eclo
     * @param user
     * @return
     */
    public static List getAllProcessProcesados(Usuario user) {
    	Criteria c=HibernateFactory.getSession().createCriteria(ProcProces.class);
    	//c.add(Expression.eq("estado",ConstantsUpload.PROCESSED));
    	Set states = new HashSet();
   	   	states.add(ConstantsUpload.PROCESSED);
   	   	states.add(ConstantsUpload.DOWNLOADED);
   	   	
		c.add(Expression.in("estado",states));
		if(user == null)
			c.add(Expression.eq("descargadaAdmin",false));
		else{
			c.add(Expression.eq("descargadaEclo",false));
			c.add(Expression.eq("usuario",user));
		}
		c.addOrder(Order.asc("fecha"));
    		
		return c.list(); 
	}
    /**
     * Metodo que devuelve la lista de archivos procesas y los que no fueron descargados por el administrador 
     * @param user
     * @return
     *///no se esta usando
    public static List getAllProcessProcesadosyNoDescargAdmin() {
    	Criteria c = HibernateFactory.getSession().createCriteria(ProcProces.class); 
    	
    
    	Set states = new HashSet();
   	   	states.add(ConstantsUpload.PROCESSED);
   	   	states.add(ConstantsUpload.DOWNLOADED);
   	   	
		c.add(Expression.in("estado",states));
		c.add(Expression.eq("descargadaAdmin",false));
		c.addOrder(Order.asc("fecha"));
   	   	return c.list(); 
	}
    
	public static List getAllProcess(Usuario user) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("usuario",user));
		c.addOrder(Order.desc("fechaSalida"));
		c.addOrder(Order.desc("fechaEntrada"));
		return c.list();
		
	}
	/**
	 * Si es usuario  eclo, le sumo el filtro para que sea efectiva la busqueda 
	 * 
	 * @param user
	 * @param state
	 * @return
	 */
	public static List getAllProcess(Usuario user,String state) {
		if(user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
			Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
			c.add(Expression.eq("usuario",user));
			c.add(Expression.eq("estado",state));	
			if(state.equals(ConstantsUpload.UPLOADED)){
				c.addOrder(Order.desc("fechaSalida"));
				//c.addOrder(Order.desc("fechaEntrada"));
				c.addOrder(Order.asc("fechaEntrada"));
			}
			else
				c.addOrder(Order.asc("fecha"));
			return c.list();
		}else if(user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL)){
			Session session = HibernateFactory.getSession();    	
			String	querya="";
			Query query = null;
			querya="select p from ProcProces as p,Eclo as e where p.estado = '"+state+"' and p.usuario.contacto.id = e.id and e.regional ="+user.getContacto().getId().toString();
			if(state.equals(ConstantsUpload.UPLOADED))
				querya = querya+" order by p.fechaSalida desc, p.fechaEntrada asc";
			else
				querya = querya+" order by p.fecha asc";
			query = session.createQuery(querya);
			query = session.createQuery(querya);
			return query.list();
		}else{
				Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
				c.add(Expression.eq("estado",state));	
				if(state.equals(ConstantsUpload.UPLOADED)){
					c.addOrder(Order.desc("fechaSalida"));
					c.addOrder(Order.asc("fechaEntrada"));
				}
				else
					c.addOrder(Order.asc("fecha"));
				return c.list();
		}
	}
	public static List getAllProcessTwoStates(Usuario user,Set states) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("usuario",user));
		c.add(Expression.in("estado",states));
		c.addOrder(Order.asc("id"));
		//c.addOrder(Order.desc("fechaSalida"));
		//c.addOrder(Order.desc("fechaEntrada"));
		return c.list();
		
	}
	public static List getAllProcessTwoStates2(Usuario user,Set states) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("usuario",user));
		c.add(Expression.in("estado",states));
		c.addOrder(Order.asc("fecha"));
		
		return c.list();
		
	}
	public static List getAllProcessState(String state) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("estado",state));
		//c.addOrder(Order.desc("fechaSalida"));
		c.addOrder(Order.asc("fechaEntrada"));
		
		return c.list();
	}
	
	public static List getAllProcessStateAndRegional(String state,Contacto contac) {
		/*Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("estado",state));
		c.createAlias("usuario","usuario");
		c.add(Expression.eq("usuario.contacto.regional.id", contac.getId()));
		//c.addOrder(Order.desc("fechaSalida"));
		c.addOrder(Order.asc("fechaEntrada"));*/
		
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("select p from ProcProces as p,Eclo as e where p.estado = '"+state+"' and p.usuario.contacto.id = e.id and e.regional.id ="+contac.getId().toString());
        return query.list();
		
		//return c.list();
	}
	
	public static List getAllProcessByEcloAndState(Eclo eclo,String state){
		Session session = HibernateFactory.getSession();    	
		String	querya="";
		Query query = null;
		querya="select p from ProcProces as p,Eclo as e where p.estado = '"+state+"' and p.usuario.contacto.id = e.id and e.id ="+eclo.getId().toString();
		if(state.equals(ConstantsUpload.UPLOADED))
			querya = querya+" order by p.fechaSalida desc, p.fechaEntrada asc";
		else
			querya = querya+" order by p.fecha asc";
		query = session.createQuery(querya);
		query = session.createQuery(querya);
		return query.list();
	}
	
	public static List getAllProcessByStateAndIdEclo(String state,Long contac) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("estado",state));
		c.createAlias("usuario","usuario");
		c.add(Expression.eq("usuario.contacto.id",contac));
		//c.addOrder(Order.desc("fechaSalida"));
		c.addOrder(Order.asc("fechaEntrada"));
		
		return c.list();
	}

	public static List getAllProcessTwoState(Set states) {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.in("estado",states));
		
		c.addOrder(Order.asc("fecha"));
		//c.addOrder(Order.desc("fechaSalida"));
		//c.addOrder(Order.desc("fechaEntrada"));
		
		return c.list();
		
	}
	/* public static List findAll() throws org.hibernate.HibernateException{
    	Session session = HibernateFactory.getSession();
    	Query query = session.createQuery("from ProcProces order by id");
        return query.list();   	
    }*/
    public static void updateProcProces(ProcProces proc)throws HibernateException{
    	HibernateFactory.getSession().update(proc);
    	//HibernateFactory.getSession().flush();
    }
    public static void save(ProcProces proc)throws HibernateException{
    	HibernateFactory.getSession().save(proc);
    }

    public static void deleteProcProces(ProcProces proc)throws HibernateException{
    	BajadaDAO bDa = new BajadaDAO();
    	bDa.borrarBajadas(proc);
    	HibernateFactory.getSession().delete(proc);
    }

	/**
	 * Devuelve el primer Proceso a procesar segun el campo fecha (la mas antigua).
	 * @return
	 * @author Sebastian Garcia
	 */
    public static ProcProces getFirstProcesseing2() {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("estado",ConstantsUpload.PROCESSING));
		c.addOrder(Order.asc("fecha"));
		c.setMaxResults(1);
		List l = c.list();
		if (l.isEmpty())
			return null;
		return (ProcProces)l.get(0); 
	}
    public static boolean getExistProcessing2() {
		Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
		c.add(Expression.eq("estado",ConstantsUpload.PROCESSING));
		return c.list().size()>0;
	}
    public static ProcProces getFirstProcesseing() {
    	
    	Session session = HibernateFactory.getSession();    	
		String	querya="";
		Query query = null;
		List l = null;
		try{
			/*querya = "from ProcProces p1 " +
					"where p1.estado = 'processing' " +
					"and p1.id not in ( select p.id from ProcProces p2, ProcProces p, Usuario u1, Usuario u2" +
					" where p.estado = 'processing'" +
					" and p2.fecha < p.fecha" +
					" and p.id != p2.id " +
					" and p.usuario.contacto.id = p2.usuario.contacto.id"+
					" and (p2.estado = 'standBy' or p2.estado = 'processing'))" +
					" order by p1.fechaEntrada";*/
			querya = "from ProcProces p1 " +
			"where p1.estado = 'processing' " +
			"and not exists ( select p.id from ProcProces p2, ProcProces p, Usuario u1, Usuario u2" +
			" where p.estado = 'processing'" +
			" and p2.fecha < p.fecha" +
			" and p.id != p2.id " +
			" and p.usuario.contacto.id = p2.usuario.contacto.id"+
			" and (p2.estado = 'standBy' or p2.estado = 'processing')" +
			"  and p1.id = p.id)" +
			" order by p1.fecha";
		
		query = session.createQuery(querya);
		query.setFirstResult(0);
		query.setMaxResults(1);
		l = query.list();
		//System.out.println("Archivos a procesar->"+l.size());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		if(l.isEmpty())
			return null;
		return (ProcProces)l.get(0);
	}
    
    public static boolean getExistProcessing() {
    	
    	ProcProces p = ProcProcesDAO.getFirstProcesseing();
    	Logger.getLogger(ProcProcesDAO.class).warn("Existe alguno para procesar?:"+(p != null));
    	return (p != null);
	}
    
    public static List getAllProcessByEcloAndUser(Eclo eclo,Usuario user){
		Session session = HibernateFactory.getSession();    	
		String	querya="";
		Query query = null;
		querya="select p from ProcProces p,Eclo e where (p.estado = '"+ConstantsUpload.PROCESSED+"' or p.estado = '"+ConstantsUpload.DOWNLOADED+"') and p.usuario.contacto.id = e.id and e.id ="+eclo.getId().toString();
		if(user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL) ||
		   user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR))
			querya = querya + " and p.descargadaEclo = " + Boolean.FALSE.toString();
		else if(user.getRol().getNombre().equals(Tokens.NOMBREROLADMINISTRADOR))
			querya = querya + " and p.descargadaAdmin = " + Boolean.FALSE.toString();
		/*Sea el rol que sea, se ordena por fecha*/
		querya = querya + " order by p.fecha";
		query = session.createQuery(querya);
		return query.list();
	}
    
    public static List getAllProcessByUser(Usuario user) {
		Set<String> states = new HashSet<String>();
   	   	states.add(ConstantsUpload.PROCESSED);
   	   	states.add(ConstantsUpload.DOWNLOADED);
		if(user.getRol().getNombre().equals(Tokens.NOMBREROLPROVEEDOR)){
			Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
			c.add(Expression.eq("usuario",user));
			c.add(Expression.in("estado",states));
			c.add(Expression.eq("descargadaEclo",false));
			c.addOrder(Order.asc("fecha"));
			return c.list();
		}else if(user.getRol().getNombre().equals(Tokens.NOMBREROLREGIONAL)){
			Session session = HibernateFactory.getSession();    	
			String	querya="";
			Query query = null;
			querya="select p from ProcProces as p,Eclo as e where (p.estado ='"+ConstantsUpload.PROCESSED+"' or p.estado = '"+ConstantsUpload.DOWNLOADED+"') and p.usuario.contacto.id = e.id and e.regional ="+user.getContacto().getId().toString()+
					" and p.descargadaEclo = " + Boolean.FALSE.toString() + " order by p.fecha";
			query = session.createQuery(querya);
			return query.list();
		}else{/*ADMINISTRADOR OR GENERAL*/
				Criteria c =HibernateFactory.getSession().createCriteria(ProcProces.class);
				c.add(Expression.eq("descargadaAdmin",false));
				c.add(Expression.in("estado",states));	
				c.addOrder(Order.asc("fecha"));
				return c.list();
		}
	}
    
}