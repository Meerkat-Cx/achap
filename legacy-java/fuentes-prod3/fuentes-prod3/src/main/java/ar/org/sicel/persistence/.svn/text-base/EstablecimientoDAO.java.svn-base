package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

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

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.Email;
import ar.org.sicel.util.EmailSender;
import ar.org.sicel.web.Tokens;


/**
 * <p>
 * Factory class. Is able to find and create objects of type Establecimiento.
 * Hibernate inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.Establecimiento
 */
public abstract class EstablecimientoDAO {
    
	static Logger log = Logger.getLogger(EstablecimientoDAO.class);

	//---------------- create method --------------------

    /**
     * Creates a(n) Establecimiento object.
     * 
     * @param s1Tbo
     * @param s1ECLO
     * @param s1Prop
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return Establecimiento the created object
     */
    public static Establecimiento create(java.lang.Integer s1Tbo,
            java.lang.Integer s1ECLO, java.lang.Integer s1Prop,
            java.lang.String nombreContacto, java.lang.String comentario,
            byte[] foto) {
        Establecimiento object = new Establecimiento();

        object.setS1Tbo(s1Tbo);
        object.setS1Eclo(s1ECLO);
        object.setS1Prop(s1Prop);
        object.setNombreContacto(nombreContacto);
        object.setComentario(comentario);
        //object.setFoto(foto);

        //object.setAnimals(new HashSet()); ?
     
        //  object.setEventos(new HashSet());
       // object.setEvtTransferencias(new HashSet());
       // object.setProcEstablecimientos(new HashSet());
        object.setUbicacions(new HashSet());
        object.setAtrVariablesEstab(new AtrVariables());

        return object;
    }
    
    public static Establecimiento createPersistent(String nombreContacto, String comentario, 
    		Propietario propietario, Eclo eclo) {
    	
    	Establecimiento establecimiento = EstablecimientoDAO.create(null, null, null, nombreContacto, comentario, null);
    	establecimiento.setPropietario(propietario);;
    	establecimiento.setEclo(eclo);
    	
    	HibernateFactory.getSession().save(establecimiento);
    	
    	return establecimiento;    	
    }
    

    // ---------------- finder methods ----------------------

    /**
     * 
     * Finds Establecimiento object by its primary key. In Hibernate, this is
     * just a call to get().
     * 
     */
   
    public static Establecimiento findByPrimaryKey(java.lang.Long id)
            throws org.hibernate.HibernateException {
    	
    	/*Criteria criteria = HibernateFactory.getSession().createCriteria(Establecimiento.class);
    	criteria.add(Expression.eq("id", id));
    	Hibernate.initialize(((Establecimiento) criteria.uniqueResult()).getBitacora());
    	return (Establecimiento) criteria.uniqueResult();*/
        Session session = HibernateFactory.getSession();
        Establecimiento object = (Establecimiento) session.get(Establecimiento.class, id);
        //Establecimiento object = (Establecimiento) o;

        return object;
    }

    public static List findAll(){
        Session session = HibernateFactory.getSession();
        Query query = session.createQuery("from Establecimiento");
        return query.list();
    }

    public static Establecimiento findExistentByPrimaryKey(Long idEst)
            throws ExcepcionIntegridad {
        Establecimiento result = null;
        try {
            result = findByPrimaryKey(idEst);
        } catch (HibernateException he) {
            throw new ErrorFatal(
                    "Error al querer buscar establecimiento - EstablecimientoDAO",
                    he);
        }
        if (result == null)
            throw new ExcepcionIntegridad(MENSAJES.ESTABLECIMIENTO_NO_EXISTE,
                    new String[] { idEst.toString() });
        return result;
    }
    /**
	 * Dado el cuig del tambo devuelve la tambo correspondiente
	 * @param cuig
	 * @return
	 */
	public static Establecimiento getEstablecimientoByCuig(String cuig) {
		if(cuig!=null && !cuig.equals("")){
			Criteria criteria =  HibernateFactory.getSession().createCriteria(Establecimiento.class);
			criteria.add(Expression.eq("cuig", cuig));
			return (Establecimiento)criteria.uniqueResult();
		}
		return null;
	}
	
	public static List getEstablecimientosByIdPropietario(Long id) {
		
			Criteria criteria =  HibernateFactory.getSession().createCriteria(Establecimiento.class);
			criteria.add(Expression.eq("propietario.id", id));
			return criteria.list();
	}
	
	public static List getEstablecimientosByIdPropietarioOrdered(Long id) {
		
		Criteria criteria =  HibernateFactory.getSession().createCriteria(Establecimiento.class);
		criteria.add(Expression.eq("propietario.id", id));
		criteria.addOrder(Order.asc("eclo.id"));
		criteria.addOrder(Order.asc("estancia.id"));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
}
	
    public static List findPrimerosCien()
            throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        Criteria crit = session.createCriteria(Establecimiento.class);
        crit.setFirstResult(1);
        crit.setMaxResults(100);
        return crit.list();
    }

	public static Criteria getCriteria() {
		 Session session = HibernateFactory.getSession();
	     return session.createCriteria(Establecimiento.class);
	}
	
	/**
	 * Devuelve los establecimientos filtrandolos por nombre, identificador, y eclo
	 * @param eclo - por ahora no se tiene en cuenta
	 * @param nombre - obligatorio
	 * @param id - opcional
	 * @return
	 * @throws org.hibernate.HibernateException
	 */
	public static List findEstablecimientos(Eclo eclo, String nombre, String id,ArrayList<String> orderProperties) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Establecimiento.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id", Long.valueOf(id)));
        if (eclo != null)
        	criterio.add(Expression.eq("eclo", eclo));
        //Lista de las columnas por las cuales va a ser ordenado el listado de establecimientos
        if (!orderProperties.isEmpty())
        	for(String prop : orderProperties)
        		criterio.addOrder(Order.asc(prop));
        return criterio.list();
		
		
	}
	public static List findEstablecimientosEclos(List eclos, String nombre, String id,ArrayList<String> orderProperties) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Establecimiento.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id", Long.valueOf(id)));
        if (!eclos.isEmpty())
        	criterio.add(Expression.in("eclo", eclos));
        //Lista de las columnas por las cuales va a ser ordenado el listado de establecimientos
        if (!orderProperties.isEmpty())
        	for(String prop : orderProperties)
        		criterio.addOrder(Order.asc(prop));
        return criterio.list();
		
		
	}
	/*List razasWithAtribute = new ArrayList();
    	String	querya = "select est.id from Establecimiento est, Valor v left join est.atrVariablesEstab.valors val" +
		" where val.id = v.id and v.valorAdmAtr.atributo.id = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", atributoId, Hibernate.LONG);
	    razasWithAtribute = query.list();
		Criteria criterio = session.createCriteria(Establecimiento.class);
	    if(!razasWithAtribute.isEmpty())
	    	criterio.add(Expression.not(Expression.in("id",razasWithAtribute)));*/


	public static List findEstablecimientos(String identificador, String eclo, String nombre, String propietario, Boolean activo,Long regional) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Establecimiento.class);
        if (StringUtils.isNotEmpty(identificador))
        	criterio.add(Expression.eq("id", Long.valueOf(identificador)));
        if (StringUtils.isNotEmpty(nombre))   	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(eclo) || regional != null){
        	Criteria c = criterio.createCriteria("eclo");
        	if (StringUtils.isNotEmpty(eclo))
        		c.add(Expression.like("nombreContacto","%"+eclo+"%").ignoreCase());
        	if (regional != null)
        		c.add(Expression.eq("regional.id",regional));
        }
        if (StringUtils.isNotEmpty(propietario)){
        	Criteria c2 = criterio.createCriteria("propietario");
        	c2.add(Expression.like("nombreContacto","%"+propietario+"%").ignoreCase());
        }
        criterio.add(Expression.eq("activoEstablecimiento",activo));
        return criterio.list();
	}
	/**
	 * Metodo que recupera los tambos segun los filtros pasados como parametro
	 * @param identificador
	 * @param eclo
	 * @param nombre
	 * @param propietario
	 * @return
	 * @throws org.hibernate.HibernateException
	 */
	public static List findEstablecimientos(String identificador, String eclo, String nombre, String propietario) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Establecimiento.class);
        if (StringUtils.isNotEmpty(identificador))
        	criterio.add(Expression.eq("id", Long.valueOf(identificador)));
        if (StringUtils.isNotEmpty(nombre))   	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(eclo)){
        	Criteria c = criterio.createCriteria("eclo");
        	c.add(Expression.like("nombreContacto","%"+eclo+"%").ignoreCase());
        	
        }
        if (StringUtils.isNotEmpty(propietario)){
        	Criteria c2 = criterio.createCriteria("propietario");
        	c2.add(Expression.like("nombreContacto","%"+propietario+"%").ignoreCase());
        	
        }
        return criterio.list();
	}

	public static Establecimiento findEstablecimientoIds(String idEclo, String idEstab, String idProp) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Establecimiento.class);
        
        if (StringUtils.isNotEmpty(idEstab))
        	criterio.add(Expression.eq("id", Long.valueOf(idEstab)));
        if (StringUtils.isNotEmpty(idEclo)){
        	Criteria c = criterio.createCriteria("eclo");
        	c.add(Expression.eq("id", Long.valueOf(idEclo)));
        }
        if (StringUtils.isNotEmpty(idProp)){
        	Criteria c = criterio.createCriteria("propietario");
        	c.add(Expression.eq("id", Long.valueOf(idProp)));
        }
        return (Establecimiento) criterio.uniqueResult();
	}
	 public static void save(Establecimiento estab)
 	throws HibernateException {
 	HibernateFactory.getSession().save(estab);   	
 	
 }
	public static void update(Establecimiento establecimiento) throws HibernateException {
		HibernateFactory.getSession().update(establecimiento);
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	}

	public static List findByNombreContacto(String nombreContacto) {
		/*Session session = HibernateFactory.getSession();
		nombreContacto = "%" + nombreContacto + "%";
		Query query = session.createQuery("from Establecimiento as e where e.nombreContacto like :nomContacto");
		query.setParameter("nomContacto",nombreContacto);
        return query.list();	*/
		Session session = HibernateFactory.getSession();
        Criteria criterio = session.createCriteria(Establecimiento.class);
        if (StringUtils.isNotEmpty(nombreContacto))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombreContacto+"%").ignoreCase());
        return criterio.list();
	}

	public static void sendEMail(Establecimiento establecimiento, String accion) throws EmailException {
        Set usuariosEcloEstab = establecimiento.getEclo().getUsuarios();
        if(!usuariosEcloEstab.isEmpty()){
        	ResourceBundle res = ResourceBundle.getBundle("Config");
        	Email mail = new Email();
        	mail.setHTMLMode(true);
        	//Envio mail a la eclo a la que pertenece el tambo
        	Iterator it_usuarioEcloEst = usuariosEcloEstab.iterator();
        	while(it_usuarioEcloEst.hasNext()){
        		Usuario usuario = (Usuario)it_usuarioEcloEst.next();
        		if(usuario!=null && usuario.getEmail()!=null){
        			mail.addTo(usuario.getEmail());
        			Eclo eclo = EcloDAO.findByPrimaryKey(establecimiento.getEclo().getId());
        			if(eclo.getRegional()!=null) {
	        			Set usuariosRegional = eclo.getRegional().getUsuarios();
	        			 if(!usuariosRegional.isEmpty()){
	        				 Iterator it_usuariosR = usuariosRegional.iterator();
	        		        	while(it_usuariosR.hasNext()){
	        		        		Usuario usuarioR = (Usuario)it_usuariosR.next();
	        		        		if(usuarioR!=null && usuarioR.getEmail()!=null)
	        		        			mail.addCC(usuarioR.getEmail());
	        			 }
	        		}
        		}
        	}
        	}
        	//Envio mail a todas las eclos en COPIA
        	/*List eclos = EcloDAO.findAllActivas();
        	eclos.remove(establecimiento.getEclo());
        	Iterator it_eclos = eclos.iterator();
        	while(it_eclos.hasNext()){
        		Eclo eclo = (Eclo)it_eclos.next();
        		if(!eclo.getUsuarios().isEmpty()){
        			Iterator it_usuarioEclo = eclo.getUsuarios().iterator();
        			while(it_usuarioEclo.hasNext()){
        				Usuario usuario = (Usuario)it_usuarioEclo.next();
                		if(usuario!=null && usuario.getEmail()!=null)
                			mail.addCC(usuario.getEmail());
        			}
        		}
        		
        	}*/
        	if("nuevo".equals(accion))
        		mail.setSubject(res.getString("email.nuevoTamboSubject")+" "+establecimiento.getId());
        	else
        		mail.setSubject(res.getString("email.modificacionTamboSubject")+" "+establecimiento.getId());
        	mail.setTextBody(res.getString("email.edicionTamboTextBody"));
        	String htmlBody1 = res.getString("email.edicionTamboHtmlBody1")+establecimiento.getId();
        	String htmlBody2 = res.getString("email.edicionTamboHtmlBody2")+establecimiento.getNombreContacto();
        	String htmlBody7 = res.getString("email.edicionTamboHtmlBody7")+establecimiento.getEstancia().getId();
        	String htmlBody3 = res.getString("email.edicionTamboHtmlBody3")+establecimiento.getEstancia().getNombreContacto();
        	String htmlBody8 = res.getString("email.edicionTamboHtmlBody8")+establecimiento.getPropietario().getId();
        	String htmlBody9 = res.getString("email.edicionTamboHtmlBody9")+establecimiento.getPropietario().getNombreContacto();
        	String htmlBody4 = res.getString("email.edicionTamboHtmlBody4")+establecimiento.getMetodoControl().getCodigo();
        	/*String v1 = establecimiento.getCuig()==null?"sin valor":establecimiento.getCuig();
        	String htmlBody7 = res.getString("email.edicionTamboHtmlBody7")+v1;
        	String v2 = establecimiento.getRenspa()==null?"sin valor":establecimiento.getRenspa();
        	String htmlBody8 = res.getString("email.edicionTamboHtmlBody8")+v2;
        	String v3 = establecimiento.getCuit()==null?"sin valor":establecimiento.getCuit();
        	String htmlBody9 = res.getString("email.edicionTamboHtmlBody9")+v3;*/
        	String valor= establecimiento.getCentroComputo()==null?"ninguno":establecimiento.getCentroComputo().getId().toString()+", "+establecimiento.getCentroComputo().getNombre();
	        String htmlBody6 = res.getString("email.edicionTamboHtmlBody6")+valor;
        	boolean activo = establecimiento.getActivoEstablecimiento().booleanValue();
        	String htmlBody5 = res.getString("email.edicionTamboHtmlBody5")+(activo?"Si":"No");
        	
        	String htmlBody10 = res.getString("email.edicionTamboHtmlBody10")+(establecimiento.getEclo().toString());
        	String htmlBody = htmlBody1+htmlBody2+htmlBody7+htmlBody3+htmlBody10+htmlBody8+htmlBody9+htmlBody4+htmlBody6+htmlBody5;
        	//log.warn("save/update:TO " +mail.getTo());
        	///log.warn("save/update:CC " +((mail.getCcList().isEmpty())?"":mail.getCcList().firstElement()));
        	//log.warn("save/update: " +htmlBody);
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
  }

	public static List findNombreNotAtributos(Long atributoId, String nombreContacto) throws HibernateException{
		Session session = HibernateFactory.getSession();
		List razasWithAtribute = new ArrayList();
    	String	querya = "select est.id from Establecimiento est, Valor v left join est.atrVariablesEstab.valors val" +
		" where val.id = v.id and v.valorAdmAtr.atributo.id = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", atributoId, Hibernate.LONG);
	    razasWithAtribute = query.list();
		Criteria criterio = session.createCriteria(Establecimiento.class);
	    if(!razasWithAtribute.isEmpty())
	    	criterio.add(Expression.not(Expression.in("id",razasWithAtribute)));
        if (StringUtils.isNotEmpty(nombreContacto))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombreContacto+"%").ignoreCase());
        return criterio.list();
	}

	public static Establecimiento findAtrVariable(Long idAtrVarialbe) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        String	querya = "from Establecimiento est where est.atrVariablesEstab = :id";
    	Query query = session.createQuery(querya);
	    query.setParameter("id", idAtrVarialbe, Hibernate.LONG);
        return (Establecimiento) query.uniqueResult();
	    /*Criteria criterio = session.createCriteria(Establecimiento.class);
       	criterio.add(Expression.eq("atrVariablesEstab", idAtrVarialbe));
       	return (Establecimiento) criterio.uniqueResult();*/
	}
	public static void actualizarAnimales(Establecimiento tambo, Estancia estancia ) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();	
		String	querya="";
		querya = "update Animal set estancia = :estancia, propietario = :propietario  where establecimiento =:tambo"; 
		Query query = session.createQuery(querya);
		query.setParameter("estancia", estancia);
		query.setParameter("tambo", tambo);
		query.setParameter("propietario", tambo.getPropietario());
		query.executeUpdate();
			
	}
	
	public static List findByIdPropietariosOrderByIdEstab(Long idProp){
		Criteria criteria = HibernateFactory.getSession().createCriteria(Establecimiento.class);
		criteria.createAlias("propietario", "propietario");
    	criteria.add(Expression.eq("propietario.id", idProp));
    	criteria.addOrder(Order.asc("id"));
    	return criteria.list();
	}
	
}
