package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.exception.ConstraintViolationException;
import org.apache.log4j.Logger;

import ar.org.sicel.excel.excepciones.CellError;
import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.Email;
import ar.org.sicel.util.EmailSender;
import ar.org.sicel.web.Tokens;

/**
 * <p>
 * Factory class. Is able to find and create objects of type Usuario. Hibernate
 * inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.Usuario
 * @author kjacobsen
 */
public class UsuarioDAO {
	
	static Logger log = Logger.getLogger(UsuarioDAO.class);
	// ---------------- finder methods ----------------------

    
    /**
     * Retorna la entidad Usuario a partir de su username
     * @param username - String
     * @return Usuario
     */
    public static Usuario findByUsername(String username)
            throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Expression.eq("username",username));
        criteria.setLockMode(LockMode.READ);
       	return (Usuario) criteria.uniqueResult();
    }
    
	public static List findByNombre(String nombre) throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Usuario where upper(nombre) like '%"+nombre.toUpperCase()+"%'");
		return query.list();
	}
	
	public static List findByApellido(String apellido) throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Usuario where upper(apellido) like '%"+apellido.toUpperCase()+"%'");
		return query.list();
	}
	
	public static List findByFiltros(String nombre, String apellido, Rol rol) throws org.hibernate.HibernateException {
		//criterio.add(Expression.like("nombre", "%"+nombreRaza+"%").ignoreCase());
		Session session = HibernateFactory.getSession();
		String query = "select u from Usuario u ";
		boolean where= false;
		if(!nombre.equalsIgnoreCase(""))
		{
			query += "where upper(u.nombre) like '%"+nombre.toUpperCase()+"%' ";
			where= true;
		}
		if(!apellido.equalsIgnoreCase(""))
		{
			if(where)
				query+= "and upper(u.apellido) like '%"+apellido.toUpperCase()+"%' ";
			else 
			{
				query+= "where upper(u.apellido) like '%"+apellido.toUpperCase()+"%' ";
				where = true;
			}
		}
		if(rol != null)
		{
			if(where)
				query+= "and u.rol.id="+ rol.getId();
			else 
				query+= "where u.rol.id="+ rol.getId();
		}
		Query usuarios= session.createQuery(query);
		return usuarios.list();
	}
    
    /**
	 * Retorna el usuario dado su clave primaria
	 * @param id
	 * @return Usuario
	 * @throws org.hibernate.HibernateException
	 */
	public static Usuario findByPrimaryKey(Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		return (Usuario) session.get(Usuario.class, id);
	}
    
    /**
     * Retorna las entidades Usuario que tienen el rol dado
     * @param rol - Rol
     * @return List<Usuario>
     */
    @SuppressWarnings("unchecked")
	public static List<Usuario> findByRol(Rol rol)
            throws HibernateException {
    	Session session = HibernateFactory.getSession();
    	Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Expression.eq("rol.id",rol.getId()));
        criteria.setLockMode(LockMode.READ);
       	return (List<Usuario>) criteria.list();
    }
	
    /**
     * Guarda en la base de datos una instancia de la entidad usuario
     * @param usuario - Usuario
     * @throws HibernateException
     */
    public static void createUser(Usuario usuario)
    	throws HibernateException,ConstraintViolationException {
    	HibernateFactory.getSession().save(usuario);
    }
    
    /**
     * Actualiza en la base de datos una instancia de la entidad usuario
     * @param usuario - Usuario
     * @throws HibernateException
     */
    public static void updateUser(Usuario usuario)
    	throws HibernateException {
    	HibernateFactory.getSession().update(usuario);
    }
    
	/**
	 * Obtiene los usuarios disponibles
	 * @return List
	 * @throws org.hibernate.HibernateException
	 */
	public static List findAll() throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Usuario");
		return query.list();
	}
	
	/**
	 * 
	 * @param usuario
	 * @param passwordSinEncriptar
	 */
	public static void sendSignUpMail(Usuario usuario, String passwordSinEncriptar) throws EmailException {
	    	ResourceBundle res = ResourceBundle.getBundle("Config");
	        Email mail = new Email();
	        mail.setHTMLMode(true);
	        mail.setTo(usuario.getEmail());
	        mail.setSubject(res.getString("email.signUpSubject"));
	        //mail.setTextBody(res.getString("email.signUpTextBody"));
	        if(usuario.getContacto()==null)
	        	mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+usuario.getUsername()+res.getString("email.signUpHtmlBody2")+/*usuario.getClave()*/passwordSinEncriptar);
	        else
	        	if (usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
	        		mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+usuario.getUsername()
	        			+res.getString("email.signUpHtmlBody2")+passwordSinEncriptar+res.getString("email.signUpHtmlBody3")+usuario.getContacto().getId()+
	        			res.getString("email.signUpHtmlBody4")+"<url>http://sicel.sahost.com/sicel3/login.do</url>"+res.getString("email.signUpHtmlBody5"));
	        	else
	        		mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+usuario.getUsername()
	        				+res.getString("email.signUpHtmlBody2")+passwordSinEncriptar+res.getString("email.signUpHtmlBody3")+usuario.getContacto().getId());
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
	/**
	 * Realiza el envio al usuario informando la cantidad de errores, y de que tipo. Asi como tambien la cantida de cargas oks
	 * @param usuario
	 * @param listaErrores
	 * @param cantidadOk
	 * @throws EmailException
	 */
	public static void sendCargaMasivaMail(Usuario usuario, Set<CellError> listaErrores, int cantidadOk,String nombreArch,boolean esRp) throws EmailException {
    	ResourceBundle res = ResourceBundle.getBundle("Config");
        Email mail = new Email();
        mail.setHTMLMode(true);
        mail.setTo(usuario.getEmail());
        if(esRp)
        	 mail.setSubject(res.getString("email.cargaMasivaRPSubject"));
        else
        	mail.setSubject(res.getString("email.cargaMasivaSubject"));
        List mensajesError = new ArrayList();
        CellError error = new CellError(0, null);
        System.out.println("------NOMBRE->"+nombreArch+ " CANTIDAD OK-> "+cantidadOk+"------------");
        for (Iterator it = listaErrores.iterator();it.hasNext();){
        	error = (CellError)it.next();
        	mensajesError.add("Fila: " + error.getFila() + "  |  " + error.getMensajeError() + "<br>" );
        	System.out.println("----Fila: " + error.getFila() + "  |  " + error.getMensajeError()+"----");
        }	
        System.out.println("-----ERRORESSS->"+mensajesError.size()+"-------");
        if(usuario.getContacto()==null){
        	mail.setHtmlBody(res.getString("email.cargaMasivaNombreArchivo")+ nombreArch + res.getString("email.cargaMasivaOk")+ cantidadOk +
        			res.getString("email.listaErrores")+ mensajesError.size() + "<br>" +
        			mensajesError.toString().replace(',',' ').replace('[',' ').replace(']',' '));
        }
       
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
	/**
	 * Cuando se envia la clave al usuario creado se envia una copia con otro formato para el administrador
	 * @param usuario
	 * @param passwordSinEncriptar
	 * @throws EmailException
	 */
	public static void sendSignUpMailCopia(Usuario usuario, String passwordSinEncriptar,Usuario userReal) throws EmailException {
    	ResourceBundle res = ResourceBundle.getBundle("Config");
        Email mail = new Email();
        mail.setHTMLMode(true);
        mail.setTo(usuario.getEmail());
        mail.setSubject(res.getString("email.signUpSubject"));
      
        if(usuario.getContacto()==null)
        	mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+userReal.getUsername()+res.getString("email.signUpHtmlBody2")+/*usuario.getClave()*/passwordSinEncriptar);
        else
        	if (usuario.getRol().getNombre().equals(Tokens.NOMBREROLPROPIETARIO))
        		mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+userReal.getUsername()
        			+res.getString("email.signUpHtmlBody2")+passwordSinEncriptar+res.getString("email.signUpHtmlBody3")+userReal.getContacto().getId()+
        			res.getString("email.signUpHtmlBody4")+"<url>http://sicel.sahost.com/sicel3/login.do</url>"+res.getString("email.signUpHtmlBody5"));
        	else
        		mail.setHtmlBody(res.getString("email.signUpHtmlBody1")+userReal.getUsername()
        				+res.getString("email.signUpHtmlBody2")+passwordSinEncriptar+res.getString("email.signUpHtmlBody3")+userReal.getContacto().getId());
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
