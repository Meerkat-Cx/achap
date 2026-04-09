package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type Registro.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.Registro
 */
public abstract class RegistroDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) Registro object.
     *
     * @param numero
     * @return Registro the created object
     */
    public static Registro create(String tReg , java.lang.String numero) throws ExcepcionIntegridad {
    	TipoRegistro tipo = null;
    	try {
        	tipo = TipoRegistroDAO.findByPrimaryKey(tReg);
        } catch (HibernateException he) {
        	throw new ErrorFatal(he);
        }
        if (tipo == null) {
        	throw new ExcepcionIntegridad(MENSAJES.TIPO_REGISTRO_NO_EXISTE, new String[] {tReg});
        }
    	Registro object = new Registro();

        object.setNumero(numero.trim());
        object.setTipoRegistro(tipo);
        return object;
    }
    public static Registro create(String tReg , java.lang.String numero,String cBaj,Date fBaj) throws ExcepcionIntegridad {
    	TipoRegistro tipo = null;
    	try {
        	tipo = TipoRegistroDAO.findByPrimaryKey(tReg);
        } catch (HibernateException he) {
        	throw new ErrorFatal(he);
        }
        if (tipo == null) {
        	throw new ExcepcionIntegridad(MENSAJES.TIPO_REGISTRO_NO_EXISTE, new String[] {tReg});
        }
    	Registro object = new Registro();

        object.setNumero(numero.trim());
        object.setTipoRegistro(tipo);
       
        if(StringUtils.isNotEmpty(cBaj)){
        	if(cBaj.equals("0"))
        		object.setCodigoBaja(null);
        	else{
	        	object.setCodigoBaja(new Integer(cBaj));
	        	if(cBaj.equals("1"))
	        		object.setFechaBaja(new Date());
        	}
        }
        else
        	object.setCodigoBaja(null);
        
        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds Registro object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Registro findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Registro object = (Registro) session.get(Registro.class, id);

        return object;
    }
    public static  void remove(Registro r) throws org.hibernate.HibernateException {
    	HibernateFactory.getSession().delete(r);
            
        }
    public static Registro findByTipoNum(String tipo, String num) throws org.hibernate.HibernateException {
        	Session session = HibernateFactory.getSession();
        	TipoRegistro tipoR = null;
        	tipoR = TipoRegistroDAO.findByPrimaryKey(tipo); 
        	Criteria criterio = session.createCriteria(Registro.class);
             
           	
             	criterio.add(Restrictions.eq("tipoRegistro", tipoR));
             	criterio.add(Restrictions.eq("numero", num));
             	
             return (Registro) criterio.uniqueResult();

          
        }
    public static List findByTipo(String tipo) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	TipoRegistro tipoR = null;
    	tipoR = TipoRegistroDAO.findByPrimaryKey(tipo); 
    	Criteria criterio = session.createCriteria(Registro.class);
         criterio.add(Restrictions.eq("tipoRegistro", tipoR));
         	
         	
         return criterio.list();

      
    }
    public static Registro find(String tipo, String num,String razaa,String sexo) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
    	
    	Raza raza = RazaDAO.findByPrimaryKey(razaa);
    	
    	TipoRegistro tipoR = null;
    	tipoR = TipoRegistroDAO.findByPrimaryKey(tipo); 
    	Criteria criterio = session.createCriteria(Registro.class);
    	//criterio.setLockMode(LockMode.READ);
    	boolean esHembra = sexo.equals("H");	
    	Criteria criterioAnimal = criterio.createCriteria("animal");
    	criterioAnimal.add(Expression.eq("composicionRacial.razaDeclarada", raza));
		//criterioAnimal.add(Expression.eq("esHembra", esHembra));
       	
         	criterio.add(Restrictions.eq("tipoRegistro", tipoR));
         	criterio.add(Restrictions.eq("numero", num));
         
         List registros = criterio.list();
        Iterator it = registros.iterator();
        while(it.hasNext()){
        	Registro r = (Registro)it.next();
        	if(r.getAnimal().esHembra()== esHembra)
        		return r;
        }
         return null;
       // return (Registro)criterio.uniqueResult();
      
    }
}
