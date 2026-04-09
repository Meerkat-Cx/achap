package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.util.Email;
import ar.org.sicel.util.EmailSender;
import ar.org.sicel.web.Tokens;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Propietario.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Propietario
 */
public abstract class PropietarioDAO {
    // ---------------- create method --------------------
	static Logger log = Logger.getLogger(PropietarioDAO.class);
    /**
     * Creates a(n) Propietario object.
     *
     * @param socio
     * @param har
     * @param sra
     * @param s1Eclo
     * @param s1Prop
     * @param esPersonaFisica
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return Propietario the created object
     */
     public static Propietario create (java.lang.Integer socio, java.lang.Integer har, java.lang.Integer sra, java.lang.Integer s1Eclo, java.lang.Integer s1Prop, java.lang.Boolean esPersonaFisica, java.lang.String nombreContacto, java.lang.String comentario, byte[] foto)
     {
         Propietario object = new Propietario();

         object.setSocio (socio);
         object.setHar (har);
         //object.setSra (sra);
         object.setS1Eclo (s1Eclo);
         object.setS1Prop (s1Prop);
         object.setEsPersonaFisica (esPersonaFisica);
         object.setNombreContacto (nombreContacto);
         object.setComentario (comentario);
         //object.setFoto (foto);

       // object.setAnimals(new HashSet());
        object.setEstablecimientos(new HashSet());
        object.setEvtTransferencias(new HashSet());
        object.setUbicacions(new HashSet());
        
        object.setAtrVariables(new AtrVariables());

         return object;
     }
     
     
     public static Propietario createPersistent(java.lang.Integer socio, java.lang.Integer har, java.lang.Integer sra, java.lang.Boolean esPersonaFisica, java.lang.String nombreContacto, java.lang.String comentario) {
    	 Propietario propietario = PropietarioDAO.create(socio, har, sra, null, null, esPersonaFisica, nombreContacto, comentario, null);
    	 
    	 HibernateFactory.getSession().save(propietario);
    	 return propietario;    	 
     }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Propietario object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Propietario findByPrimaryKey(
        java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	Propietario object = null;
        try{
    	 object = (Propietario) session.get(Propietario.class, id);
        }
        catch (ClassCastException e) {
        	object = null;
			//no hago nada y retorno null
		}

        return object;
    }

	public static Propietario findExistentByPrimaryKey(Long idProp) throws ExcepcionIntegridad {
		Propietario result = null;
		try {
			result = findByPrimaryKey(idProp);
		} catch (HibernateException he) {
			throw new ErrorFatal("Error al querer buscar propietario - PropietarioDAO", he);
		}
		if (result == null)
			throw new ExcepcionIntegridad(MENSAJES.PROPIETARIO_NO_EXISTE,new String[] {idProp.toString()});
		return result;
	}
	
	public static Criteria getCriteria() {
		 Session session = HibernateFactory.getSession();
	     return session.createCriteria(Propietario.class);
	}


	/**
	 * Retorna todos los propietarios del sistema  ordenados ascendentemente por el nombre de contacto
	 * @return
	 */
	public static List findAll() {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Propietario as p order by p.id asc");
        return query.list();
		
    }
	public static List findAllActivos() {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Propietario as p where activo = true order by p.nombreContacto asc");
        return query.list();	
    }
	public static List findAllActivosPorId() {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Propietario as p where activo = true order by p.id asc");
        return query.list();	
    }

	public static List findPropietarios(String sra, Long idProp, String nombre, Long idEstablecimiento, String nombreEstancia, Usuario user,Boolean delSistema) { 
		Session session = HibernateFactory.getSession();    	
		String	select = "select distinct p ";
		String from  = " from Propietario p ";
		String where = " where ";
		boolean andAgregado = false;
		if (idProp!=null){
			where +="(p.id = :idProp) and";
			andAgregado = true;
		}
		
				if (StringUtils.isNotEmpty(nombre)) {
					where += " (upper(p.nombreContacto) like :nombre) and ";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(nombreEstancia)) {
					from  += " , Estancia e ";
					where += " (e.propietario is not null) and ";
					where += " (e.propietario = p) and ";
					where += " (upper(e.nombreContacto) like :nombreEstancia) and ";
					andAgregado = true;
				}
				if (idEstablecimiento != null) {
					from  += " , Establecimiento estab ";
					where += " (estab.propietario is not null) and ";
					where += " (estab.propietario = p) and ";
					where += " (estab.id = :idEstablecimiento) and ";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(sra)) {
					where += "(p.sraExpd.numero = :numSra) and ";
					andAgregado = true;
				}
		
				
		if (andAgregado) { // sacar del where los 4 ultimos char
			where = where.substring(0, where.length() - 4);
		}
		else { // tengo que sacar el where
			where = " ";
		}
		
		String order = " order by p.id";
	
		Query query = session.createQuery(select+from+where+order);
		if (idProp!=null)
			query.setParameter("idProp", idProp, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombre))
			query.setParameter("nombre", "%"+nombre.toUpperCase()+"%", Hibernate.STRING);		
		if (StringUtils.isNotEmpty(sra))
			query.setParameter("numSra", sra, Hibernate.STRING);		
		if (idEstablecimiento != null)
			query.setParameter("idEstablecimiento", idEstablecimiento, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombreEstancia))
			query.setParameter("nombreEstancia", "%"+nombreEstancia.toUpperCase()+"%", Hibernate.STRING);
		
		return query.list();
	}
	
	public static List findPropietariosBajoEclo(String sra, Long idProp, String nombre, Long idEstablecimiento, String nombreEstancia, Usuario user,Boolean delSistema) {
		Session session = HibernateFactory.getSession();    	
		String	select = "select distinct p ";
		String from  = " from Propietario p ";
		String where = " where ";
		boolean andAgregado = false;
		if (idProp!=null){
			where +="(p.id = :idProp) and";
			andAgregado = true;
		}
		
				if (StringUtils.isNotEmpty(nombre)) {
					where += " (upper(p.nombreContacto) like :nombre) and ";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(nombreEstancia)) {
					from  += " , Estancia e ";
					where += " (e.propietario is not null) and ";
					where += " (e.propietario = p) and ";
					where += " (upper(e.nombreContacto) like :nombreEstancia) and ";
					andAgregado = true;
				}
				if (idEstablecimiento != null) {
					from  += " , Establecimiento estab ";
					where += " (estab.propietario is not null) and ";
					where += " (estab.propietario = p) and ";
					where += " (estab.id = :idEstablecimiento) and ";
					if (!delSistema)
						where += " (estab.eclo is not null and estab.eclo.id = "+user.getContacto().getId().toString()+") and ";
					andAgregado = true;
				}
				else{
					if (!delSistema)
						where += " 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p and estab.eclo is not null and estab.eclo.id = "+user.getContacto().getId().toString()+") and";
					else
						where += " 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p) and";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(sra)) {
					where += "(p.sraExpd.numero = :numSra) and ";
					andAgregado = true;
				}
			
		if (andAgregado) { // sacar del where los 4 ultimos char
			where = where.substring(0, where.length() - 4);
		}
		else { // tengo que sacar el where
			where = " ";
		}
		
		String order = " order by p.id";
	
		Query query = session.createQuery(select+from+where+order);
		if (idProp!=null)
			query.setParameter("idProp", idProp, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombre))
			query.setParameter("nombre", "%"+nombre.toUpperCase()+"%", Hibernate.STRING);		
		if (StringUtils.isNotEmpty(sra))
			query.setParameter("numSra", sra, Hibernate.STRING);		
		if (idEstablecimiento != null)
			query.setParameter("idEstablecimiento", idEstablecimiento, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombreEstancia))
			query.setParameter("nombreEstancia", "%"+nombreEstancia.toUpperCase()+"%", Hibernate.STRING);
		
		return query.list();
	} 
	
	public static List findPropietariosBajoRegional(String sra, Long idProp, String nombre, Long idEstablecimiento, String nombreEstancia, Usuario user,Boolean delSistema) {
		Session session = HibernateFactory.getSession();    	
		String	select = "select distinct p ";
		String from  = " from Propietario p ";
		String where = " where ";
		boolean andAgregado = false;
		if (idProp!=null){
			where +="(p.id = :idProp) and";
			andAgregado = true;
		}
		
				if (StringUtils.isNotEmpty(nombre)) {
					where += " (upper(p.nombreContacto) like :nombre) and ";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(nombreEstancia)) {
					from  += " , Estancia e ";
					where += " (e.propietario is not null) and ";
					where += " (e.propietario = p) and ";
					where += " (upper(e.nombreContacto) like :nombreEstancia) and ";
					andAgregado = true;
				}
				if (idEstablecimiento != null) {
					from  += " , Establecimiento estab ";
					where += " (estab.propietario is not null) and ";
					where += " (estab.propietario = p) and ";
					where += " (estab.id = :idEstablecimiento) and ";
					if (!delSistema)
						where += " (estab.eclo is not null and estab.eclo.regional is not null and estab.eclo.regional.id = "+user.getContacto().getId().toString()+") and ";
					andAgregado = true;
				}
				else{
					if (!delSistema)
						where += " 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p and estab.eclo is not null and estab.eclo.regional is not null and estab.eclo.regional.id = "+user.getContacto().getId().toString()+") and";
					else
						where += " 0 < (select count(*) from Establecimiento estab where estab.propietario is not null and estab.propietario = p) and";
					andAgregado = true;
				}
				
				if (StringUtils.isNotEmpty(sra)) {
					where += "(p.sraExpd.numero = :numSra) and ";
					andAgregado = true;
				}
		
		if (andAgregado) { // sacar del where los 4 ultimos char
			where = where.substring(0, where.length() - 4);
		}
		else { // tengo que sacar el where
			where = " ";
		}
		
		String order = " order by p.id";
	
		Query query = session.createQuery(select+from+where+order);
		if (idProp!=null)
			query.setParameter("idProp", idProp, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombre))
			query.setParameter("nombre", "%"+nombre.toUpperCase()+"%", Hibernate.STRING);		
		if (StringUtils.isNotEmpty(sra))
			query.setParameter("numSra", sra, Hibernate.STRING);		
		if (idEstablecimiento != null)
			query.setParameter("idEstablecimiento", idEstablecimiento, Hibernate.LONG);
		if (StringUtils.isNotEmpty(nombreEstancia))
			query.setParameter("nombreEstancia", "%"+nombreEstancia.toUpperCase()+"%", Hibernate.STRING);
		
		return query.list();
	}
	
	public static void save(Propietario prop)throws HibernateException {
		HibernateFactory.getSession().save(prop); 
	}
	
	public static void update(Propietario prop) throws HibernateException {
		HibernateFactory.getSession().update(prop);
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}
	
	public static List findByNombreContacto(String nombreContacto) {
		Session session = HibernateFactory.getSession();		
		nombreContacto = "%" + nombreContacto.toUpperCase() + "%";		
		Query query = session.createQuery("from Propietario as p where upper(p.nombreContacto) like :nomContacto");
		query.setParameter("nomContacto",nombreContacto);
        return query.list();	
    }
	public static Propietario findPropietarioInt() throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Propietario.class);
        criterio.add(Restrictions.eq("s1Eclo", new Integer(990)));
        criterio.add(Restrictions.eq("s1Prop", new Integer(2829)));
         return (Propietario) criterio.uniqueResult();
		
	}
	public static List findPropietariosPorIDyNmbre(String id, String nombre) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Propietario.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))        	
        	criterio.add(Restrictions.eq("id", Long.valueOf(id)));
        return criterio.list();
	}
	public static Propietario getPropietarioByPrefijo(String prefijo) {
		if(prefijo!=null && !prefijo.equals("")){
			Criteria criteria =  HibernateFactory.getSession().createCriteria(Propietario.class);
			criteria.add(Expression.eq("prefijo", prefijo));
			return (Propietario)criteria.uniqueResult();
		}
		return null;
	}
	/**
	 * Metodo que dado el id de una eclo, el id de un propietario y el nombre de contacto recupera los propietarios 
	 * que tengan tambos en la eclo pasada como paramentro
	 * Los 
	 * @param idEclo
	 * @param idProp
	 * @param nombreContacto
	 * @return
	 */
	public static List findPropietariosPorEclo(String idEclo, String idProp, String nombreContacto) {
		//select * from EN_PROPIETARIO as prop
		//where prop.id in(select est.PROPIETARIO from EN_ESTABLECIMIENTO as est where est.ECLO = 100)
		Session session = HibernateFactory.getSession();		
		nombreContacto = "%" + nombreContacto.toUpperCase() + "%";
		Query query=null;
		if(!StringUtils.isEmpty(idProp)){
			query = session.createQuery("from Propietario as p where upper(p.nombreContacto) like :nomContacto and p.id=:idProp " +
				"and p.id in (select est.propietario from Establecimiento as est where est.eclo=:eclo)");
			query.setParameter("idProp",idProp);
		}
		else{
			query = session.createQuery("from Propietario as p where upper(p.nombreContacto) like :nomContacto and " +
					" p.id in (select est.propietario from Establecimiento as est where est.eclo=:eclo)");
			
		}
		query.setParameter("nomContacto",nombreContacto);
		
		query.setParameter("eclo",idEclo);
        return query.list();	
	}
	/**
	 * metodo que recupera los exps activos de un propietario y los trasnforma en una cadena de string, la cual 
	 * contiene raza,numero
	 * @param p
	 * @return
	 */
	public static String getSRAActivosToString(Propietario p){
			Set expd = p.getExpds();
			Iterator it = expd.iterator();
			String result = "";
			while(it.hasNext()){
				PropietarioExpd ex = (PropietarioExpd)it.next();
					if(ex.getFechaBaja()==null)
						result += "["+ex.getRaza().getNombre()+","+ex.getNumero()+"]; ";
				
			}
		return result;
	}
	public static void sendEMail(Propietario p, String accion) throws EmailException {
       // List eclos = EcloDAO.findAllActivas();
		
		ResourceBundle res = ResourceBundle.getBundle("Config");
        	Email mail = new Email();
        	mail.setHTMLMode(true);
    
        	//Envio mail a la eclo a la que pertenece el tambo
        	if ( p.getEstablecimientos()!=null){
	        	Iterator it_establ = p.getEstablecimientos().iterator();
	        	while(it_establ.hasNext()){
	        		Establecimiento establ= (Establecimiento) it_establ.next();
	        		Eclo eclo = establ.getEclo();
	        		if (eclo.getActivo()){
	        			Iterator it_usuarioEclo = eclo.getUsuarios().iterator();
	        			while(it_usuarioEclo.hasNext()){
	        				Usuario usuario = (Usuario)it_usuarioEclo.next();
	                		if(usuario!=null && usuario.getEmail()!=null)
	                			mail.addTo(usuario.getEmail());
	        			}
	        		}		
	        	}
	        }
        	if("nuevo".equals(accion))
        		mail.setSubject(res.getString("email.nuevoPropietarioSubject")+p.getId());
        	else
        		mail.setSubject(res.getString("email.modificacionPropietarioSubject")+p.getId());
        	mail.setTextBody(res.getString("email.edicionPropietarioTextBody"));
        	String htmlBody1 = res.getString("email.edicionPropietarioHtmlBody1")+p.getId();
        	String htmlBody2 = res.getString("email.edicionPropietarioHtmlBody2")+p.getNombreContacto();
        	boolean activo = p.getActivo().booleanValue();
        	String htmlBody3 = res.getString("email.edicionPropietarioHtmlBody3")+(activo?"Si":"No");
        	String htmlBody;
        	if(p.getExpds()!=null){
        		String htmlBody4 = res.getString("email.edicionPropietarioHtmlBody4")+getSRAActivosToString(p);
        		htmlBody = htmlBody1+htmlBody2+htmlBody3+htmlBody4;
        	}
        	else
        		htmlBody = htmlBody1+htmlBody2+htmlBody3;
       		mail.setHtmlBody(htmlBody);
        	try{
        		new EmailSender().sendEmailForUser(mail);
        	}catch(AddressException e){
        		log.error("Error en la direccion de email: "+e);
        		throw new EmailException("Error en la dirección de correo electrónico indicada");
        	}catch(MessagingException m){
        		log.error("Error en la construccion del email: "+m);
        		throw new EmailException("Error en la construcción del correo electrónico a ser enviado");
        	}
        	
  }


	public static List finByCuit(String cuit) {
		 Session session = HibernateFactory.getSession();
	        
	        Criteria criterio = session.createCriteria(Propietario.class);
	        
	        if (StringUtils.isNotEmpty(cuit)){        	
	        		criterio.add(Restrictions.eq("cuit", cuit));
	        		criterio.add(Restrictions.eq("activo", true));
	        		 return criterio.list();
	        }
	        else
	        	return new ArrayList();
	   }


	public static List finByTambosRenspa(String renspa) {
		Session session = HibernateFactory.getSession();		
		
		Query query=null;
		if(!StringUtils.isEmpty(renspa)){
			
			query = session.createQuery("select distinct p from Propietario as p, Establecimiento es " +
					"where es.renspa =:renspa" +
					" and es.activoEstablecimiento = true " +
					" and es.propietario = p.id" );
		}
		query.setParameter("renspa",renspa);
        return query.list();	
	}
}
