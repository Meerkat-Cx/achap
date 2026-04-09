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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.org.sicel.excel.excepciones.CellError;
import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.Email;
import ar.org.sicel.util.EmailSender;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Eclo.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Eclo
 */
public abstract class EcloDAO {
    // ---------------- create method --------------------
	
	static Logger log = Logger.getLogger(UsuarioDAO.class);

    /**
     * Creates a(n) Eclo object.
     *
     * @param nombreContacto
     * @param comentario
     * @param foto
     * @return Eclo the created object
     */
     public static Eclo create (java.lang.String nombreContacto, java.lang.String comentario, byte[] foto)
     {
         Eclo object = new Eclo();

         object.setNombreContacto (nombreContacto);
         object.setComentario (comentario);
         //object.setFoto (foto);
         object.setUltimoNumeroEvento(new Long(0));
         object.setAtrVariables(new AtrVariables());
         

        object.setEstablecimientos(new HashSet());
        object.setProcLotes(new HashSet());
        object.setUbicacions(new HashSet());

         return object;
     }
     
     
     public static Eclo createPersistent(java.lang.String nombreContacto, java.lang.String comentario, 
    		 EntidadRegional entidadRegional, ResponsableEclo responsableEclo, byte[] foto, Set sistemasAgregados,Boolean activo) {
    	 Eclo eclo = create(nombreContacto, comentario, null);
    	 eclo.setSistemas(sistemasAgregados);
    	 eclo.setRegional(entidadRegional);
    	 if (foto != null) {
    		 Foto imagen = FotoDAO.createPersistent(foto);
 	         eclo.setFoto(imagen);
    	 }
    	 //eclo.getSistemas().add(sistema);
    	 eclo.setResponsable(responsableEclo);   
    	 eclo.setActivo(activo);
    	 HibernateFactory.getSession().save(eclo);
    	 return eclo;    	 
     }
     
     

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Eclo object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Eclo findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	Criteria criteria = session.createCriteria(Eclo.class);
    	criteria.add(Expression.eq("id",id));
        //Eclo object = (Eclo) session.get(Eclo.class, id);

        //return object;
    	return (Eclo)criteria.uniqueResult();
    }

	/**
	 * Retorna todos las eclos del sistema ordenadas ascendentemento por el nombre del contacto
	 * @return
	 */
	public static List findAll() throws HibernateException{
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Eclo as e order by e.nombreContacto asc");
        return query.list();		
	}
	
	public static List findAllOrderById() throws HibernateException{
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Eclo as e order by e.id asc");
        return query.list();		
	}
	
	public static List findAllOrderByIdAndRegional(Contacto contac) throws HibernateException{
		Session session = HibernateFactory.getSession();
		if(contac !=null){
			Query query = session.createQuery("from Eclo as e where e.regional = "+contac.getId().toString()+" order by e.id asc");
	        return query.list();	
		}
		else{
			return findAllOrderById();
		}
	}
	
	public static Eclo findEcloByIdAndRegional(Long id,Contacto contac) throws HibernateException{
		Session session = HibernateFactory.getSession();
		Criteria c = session.createCriteria(Eclo.class);
		c.add(Expression.eq("id", id));
		c.createAlias("regional","regional");
		c.add(Expression.eq("regional.id", contac.getId()));
        return (Eclo)c.uniqueResult();
	}
	
	public static List findAllActivas() throws HibernateException{
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Eclo as e where activo = true order by e.nombreContacto asc");
        return query.list();		
	}
	public static List findByIds(String ids) throws HibernateException{
		Session session = HibernateFactory.getSession();
		String q = "from Eclo as e where e.id in (";
		String[] vector = ids.split(",");
		int i = 0;
		while(i<= vector.length - 1){
			q = q+vector[i];
			q = q + ",";
			i++;
		}
		q = q.substring(0,q.length()-1);
		q = q +") order by e.id asc";
		
			
		Query query = session.createQuery(q);
		
		//query.setParameter("eclos", ids, Hibernate.STRING);
		
		// TODO que pasa si hay mas de uno (seguro la base es incosistene en
		// este punto en particular)
		return query.list();
		
    
	}
	
	
	public static List findBy(String nombre,Long sistema, Long regional,String ciudad, String provincia) {
		Criteria criteria =  HibernateFactory.getSession().createCriteria(Eclo.class);
		if (nombre != null) {
			String nombrePattern = "%" + nombre.replace(" ","%") + "%";
			criteria.add(Restrictions.like("nombreContacto",nombrePattern)); 
				
		}
		return criteria.list();
		
	}
	
	public static Criteria getCriteria() {
		 Session session = HibernateFactory.getSession();
	     return session.createCriteria(Eclo.class);
	}
	/**
	 * Devuelve las eclo filtrandolos por nombre, identificador, 
	 * @param nombre - 
	 * @param id - 
	 * @return
	 * @throws org.hibernate.HibernateException
	 */
	public static List findEclos(String nombre, String id) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Eclo.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(id))
        	criterio.add(Expression.eq("id", Long.valueOf(id)));  
        return criterio.list();
	}

	public static List findByNombreContacto(String nombreContacto) {
		Session session = HibernateFactory.getSession();
		nombreContacto = "%" + nombreContacto.toUpperCase() + "%";
		Query query = session.createQuery("from Eclo as e where upper(e.nombreContacto) like :nomContacto");
		query.setParameter("nomContacto",nombreContacto);
        return query.list();	
	}
	
	public static Eclo findByIdAndNombreContacto(Long id,String nombreContacto) {
		Session session = HibernateFactory.getSession();
		Criteria criteria = session.createCriteria(Eclo.class);
    	criteria.add(Expression.eq("id",id));
    	criteria.add(Expression.like("nombreContacto", "%"+nombreContacto+"%"));
    	return (Eclo)criteria.uniqueResult();
     	
	}
	
	public static void updateEclo(Eclo eclo) throws HibernateException {
		HibernateFactory.getSession().update(eclo);
	}
	
	public static List findEclosPorFiltro(String nombre, String identificador, String nombreRegional) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Eclo.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(identificador))
        	criterio.add(Expression.eq("id", Long.valueOf(identificador)));
        if (StringUtils.isNotEmpty(nombreRegional)) {
        	Criteria criterioRegional = criterio.createCriteria("regional");
        	criterioRegional.add(Expression.like("nombreContacto", "%"+nombreRegional+"%").ignoreCase());
        }
        Order asc = new Order("id",true) {
			private static final long serialVersionUID = 1L;
		};        
        criterio.addOrder(asc);
        return criterio.list();
	}

	public static List findEclosPorFiltro(String nombre, String nombreRegional) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        
        Criteria criterio = session.createCriteria(Eclo.class);
        
        if (StringUtils.isNotEmpty(nombre))        	
        	criterio.add(Expression.like("nombreContacto", "%"+nombre+"%").ignoreCase());
        if (StringUtils.isNotEmpty(nombreRegional)) {
        	Criteria criterioRegional = criterio.createCriteria("regional");
        	criterioRegional.add(Expression.like("nombreContacto", "%"+nombreRegional+"%").ignoreCase());
        }
        return criterio.list();
	}
	
	public static void sendEmailCambioRegistro(Animal animal,String tipoRegAnterior,String nroRegAnterior) throws EmailException{
		ResourceBundle res = ResourceBundle.getBundle("Config");
        Email mail = new Email();
        Eclo eclo = animal.getEstablecimiento() != null ? animal.getEstablecimiento().getEclo() : null;
        if(eclo != null && eclo.getEmail() != null){
	        mail.setHTMLMode(true);
	        mail.setTo(eclo.getEmail());
	        mail.setSubject(res.getString("email.cambioAnimalSubject"));
	        mail.setHtmlBody(res.getString("email.cambioAnimalTituloHtmlBody")+
	        				
	        		res.getString("email.cambioAnimalRPHtmlBody")+(animal.getRP() != null ? animal.getRP() : "No disponible")+
	        				 res.getString("email.cambioAnimalNombreHtmlBody")+(animal.getNombre() != null ? animal.getNombre() : "No disponible")+
	        				 res.getString("email.cambioAnimalTamboHtmlBody")+(animal.getEstablecimiento() != null ? animal.getEstablecimiento().getId() : "No disponible")+
	        				 res.getString("email.cambioAnimalRegAnteriorHtmlBody")+(tipoRegAnterior+" "+nroRegAnterior)+
	        				 res.getString("email.cambioAnimalRegNuevoHtmlBody")+animal.getRegOrigen().getTipoRegistro().getId() + " " +animal.getRegOrigen().getNumero());
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

}