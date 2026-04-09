package ar.org.sicel.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.proxy.HibernateProxy;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.Tokens;


/**
 * <p>
 * Factory class. Is able to find and create objects of type Animal. Hibernate
 * inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.Animal
 */
public class AnimalDAO {
	
	static Logger log = Logger.getLogger(AnimalDAO.class);
	// ---------------- finder methods ----------------------

    
    /**
     * 
     * Finds Animal object by its registry
     * 
     * método que buscar por los 4 parametros, en caso de uqe el sexo sea macho buscara en la tabla macho, 
     * caso contrario en la tabla hembra, cuando se buscar un animal usando alguna raza en particular
     * y dicho animal en la base no tiene raza Declara (caso de las crias) se hace caso omiso a la razo enviada
     * en el xml.
     */
	public static Animal findByRegistry(String tRegistro, String nRegistro,String raza,String sexo)
	throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();    	
		String	querya="";
		if(sexo.equals("M")){
			querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
			" where an.regOrigen = reg.id" +
			" and (rc is null or rc.id = :raza) and" +
			" reg.tipoRegistro.id = :tReg and reg.numero = :nReg" +
			" and an.class = Macho";
		}
		else{
		
			querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
							" where an.regOrigen = reg.id" +
							" and (rc is null or rc.id = :raza) and" +
							" reg.tipoRegistro.id = :tReg and reg.numero = :nReg" +
							" and an.class = Hembra";
		}
		Query query = session.createQuery(querya);
		query.setParameter("tReg", tRegistro, Hibernate.STRING);
		query.setParameter("nReg", nRegistro, Hibernate.STRING);
		query.setParameter("raza", raza, Hibernate.STRING);
		Object o = query.uniqueResult();
		if(o == null)
			return null;
		else{
			/*if(o instanceof HibernateProxy){
	    		HibernateProxy proxy = (HibernateProxy) a;
	    		return (Animal) proxy.getHibernateLazyInitializer().getImplementation();
	    	}*/
			/*if(ani instanceof HibernateProxy){
				if(this.getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(ani))){
					Animal an = (Animal)ani;*/
			if(sexo.equals("M")){
				/*if(o instanceof HibernateProxy){
					if(.getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(o))){
						return (Macho)o;*/
				if(o instanceof HibernateProxy){
		    		HibernateProxy proxy = (HibernateProxy) o;
		    		return (Macho) proxy.getHibernateLazyInitializer().getImplementation();
				}
				return (Macho)o;
				}
				
				//return (Macho)o;
			
			else{
				if(o instanceof HibernateProxy){
		    		HibernateProxy proxy = (HibernateProxy) o;
		    		return (Hembra) proxy.getHibernateLazyInitializer().getImplementation();
				}
				return (Hembra)o;
			}
				//return (Hembra)o;
		}
				
	   	
	}

    
    //or an.composicionRacial.razaDeclarada.id = :raza
	
	/**
	 * Busca un animal por tipo y numero de registro,raza y sexo, y si no lo encuentra, produce una
	 * Excepcion de Integridad (Animal_No_Existe). Es porque en casi todos los lugares
	 * en que empezamos a procesar el xml, necesitamos algo asi (al buscar los padres
	 * en un alta, al buscar el padre en un servicio, etc.) 
	 *   
	 * 
	 * @param tRegistro
	 * @param nRegistro
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Animal findExistentByRegistry(String tRegistro,String nRegistro,String raza,String sexo) throws ExcepcionIntegridad {
		Animal result = null;
		try {
			result = findByRegistry(tRegistro,nRegistro,raza,sexo);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ErrorFatal("Error al querer buscar un animal existente - AnimalDAO",he);
		}
		if (result == null)
			throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_EXISTE,new String[]{tRegistro,nRegistro,raza,sexo});
		
		return result;
	}
	
	
	/**
	 * Si no existe, o no es un Macho, tira una ExcepcionIntegridad (ver findExistentByRegistry) 
	 * @param tRegistro
	 * @param nRegistro
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Macho findExistentMachoByRegistry(String tRegistro,String nRegistro,String raza) throws ExcepcionIntegridad {

		Animal result = findExistentByRegistry(tRegistro,nRegistro,raza,"M");
		if (result.esHembra())
			throw new ExcepcionIntegridad(MENSAJES.NO_MACHO,new String[]{tRegistro,nRegistro});
		return (Macho)result;
		/*
		Session session = HibernateFactory.getSession();    	
        String querya = "from Animal an where an.id in (select reg.animal from Registro reg " +
        		"where reg.animal = an.id and an.composicionRacial.razaDeclarada.id = :raza and reg.tipoRegistro.id = :tReg and reg.numero = :nReg)";
    	Query query = session.createQuery(querya);
        query.setParameter("tReg", tRegistro, Hibernate.STRING);
        query.setParameter("nReg", nRegistro, Hibernate.STRING);
        query.setParameter("raza", raza, Hibernate.STRING);
        List results = query.list();
        if(results.isEmpty())
        	return null;
        
        Animal a = (Animal) results.get(0);
        if (a.esHembra())
			throw new ExcepcionIntegridad(MENSAJES.NO_MACHO,new String[]{tRegistro,nRegistro});
        if(a instanceof HibernateProxy){
        	HibernateProxy proxy = (HibernateProxy) a;
        	return (Macho) proxy.getHibernateLazyInitializer().getImplementation();
        }
        return (Macho)a;*/
	}
	
	public static Hembra findExistentHembraByRegistry(String tRegHembra, String nRegHembra,String raza) throws ExcepcionIntegridad  {
		Animal result = findExistentByRegistry(tRegHembra,nRegHembra,raza,"F");
		if (!result.esHembra())
			throw new ExcepcionIntegridad(MENSAJES.NO_HEMBRA,new String[]{tRegHembra,nRegHembra});
		return (Hembra)result;
	}

	/**
	 * 
	 * Finds Animal object by its registry
	 * 
	 
	 */
	//NO SE ESTA USANDO EN LA APLICACION- SOLO SE USA EN TEST VIEJOS
	public static List findByRP(Establecimiento est, String rp)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		String queryText = "from Animal an where an.RP= :rp and an.establecimiento = :est";
		Query query = session.createQuery(queryText);
		query.setParameter("rp", rp.trim(), Hibernate.STRING);
		query.setEntity("est", est);
		// TODO que pasa si hay mas de uno (seguro la base es incosistene en
		// este punto en particular)
		return query.list();
	}
	/**
	 * Metodo que buscar todos los animales que tengan el establecimiento "est", el rp "rp" obviando recuperar los animales id1 e id2
	 * ,  que tengan en su registro origen el cbaj = 1
	 * se hace la distincion de los id's para no traer el mismo animal
	 * @param est
	 * @param rp
	 * @return
	 * @throws org.hibernate.HibernateException
	 */
	public static Animal findByRPyRe(Long id,Long id2,Establecimiento est, String rp,Date fecha)
	throws org.hibernate.HibernateException {
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		String anio=null;
		if(fecha!=null)
			anio = formatAnio.format(fecha);
		Session session = HibernateFactory.getSession();
		String queryText="";
		Query query =null;
		if(id!=null && id2==null){
		queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and an.id <> :id and an.estancia = :est" +
				" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
		query = session.createQuery(queryText);
		query.setParameter("rp", rp.trim(), Hibernate.STRING);
		
		query.setParameter("id", id, Hibernate.LONG);
		query.setEntity("est", est.getEstancia());
		
		}
		else
			
		 if(id!=null && id2!=null){
				queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and an.id <> :id and an.id <> :id2 and an.estancia = :est" +
						" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
				query = session.createQuery(queryText);
				query.setParameter("rp", rp.trim(), Hibernate.STRING);
				
				query.setParameter("id", id, Hibernate.LONG);
				query.setParameter("id2", id2, Hibernate.LONG);
				query.setEntity("est", est.getEstancia());
				
				}
		 else{
			queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and " +
					" an.estancia = :est" +
			" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
			query = session.createQuery(queryText);
			query.setParameter("rp", rp.trim(), Hibernate.STRING);
			query.setEntity("est", est.getEstancia());
			
		}
		// TODO que pasa si hay mas de uno (seguro la base es incosistene en
		// este punto en particular)
		List animales = query.list();
		Iterator it = animales.iterator();
		while(it.hasNext()){
			Animal ani = (Animal)it.next();
			String anioo = formatAnio.format(ani.getFechaNac());
			if(anio!=null && anioo.equals(anio))
				return ani;
		}
		//Animal a = (Animal) query.uniqueResult();
		//session.lock(a,LockMode.READ);
		return null;
		/*Query query =null;
		if(id!=null && id2==null){
		queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and an.id <> :id and an.establecimiento.estancia = :est" +
				" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
		query = session.createQuery(queryText);
		query.setParameter("rp", rp, Hibernate.STRING);
		
		query.setParameter("id", id, Hibernate.LONG);
		query.setEntity("est", est.getEstancia());
		
		}
		else
			
		 if(id!=null && id2!=null){
				queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and an.id <> :id and an.id <> :id2 and an.establecimiento.estancia = :est" +
						" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
				query = session.createQuery(queryText);
				query.setParameter("rp", rp, Hibernate.STRING);
				
				query.setParameter("id", id, Hibernate.LONG);
				query.setParameter("id2", id2, Hibernate.LONG);
				query.setEntity("est", est.getEstancia());
				
				}
		 else{
			queryText = "select an from Animal an left join an.regOrigen re where an.RP= :rp and " +
					" an.establecimiento.estancia = :est" +
			" and (an.regOrigen.codigoBaja is null or an.regOrigen.codigoBaja <> 1)";
			query = session.createQuery(queryText);
			query.setParameter("rp", rp, Hibernate.STRING);
			query.setEntity("est", est.getEstancia());
			
		}
		// TODO que pasa si hay mas de uno (seguro la base es incosistene en
		// este punto en particular)
		List animales = query.list();
		Iterator it = animales.iterator();
		while(it.hasNext()){
			Animal ani = (Animal)it.next();
			String anioo = formatAnio.format(ani.getFechaNac());
			if(anio!=null && anioo.equals(anio))
				return ani;
		}
		//Animal a = (Animal) query.uniqueResult();
		//session.lock(a,LockMode.READ);
		return null;*/
		
		
}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws org.hibernate.HibernateException
	 */
	public static Animal findByPrimaryKey(java.lang.Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Animal object = (Animal) session.get(Animal.class, id);
		//Animal object = (Animal) session.get(Animal.class, id,LockMode.READ);
		return object;
	}
	
	public static Animal findHembraByPrimaryKeyWithSession(java.lang.Long id,Session session)
	throws org.hibernate.HibernateException {
		Animal object = (Animal)session.get(Animal.class, id);
		//Animal object = (Animal) session.get(Animal.class, id,LockMode.READ);
		return object;
		}

	public static List findAll() throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("from Animal");
		return query.list();
	}
	public static List findAnimalesMachosPedigreeRangos(String numero1,String numero2) throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();    	
    	String	querya = "select an from Animal an left join an.regOrigen re where an.class = Macho" +
		" and re.id = an.regOrigen " +
		" and re.tipoRegistro.id = :tReg" +
		" and re.numero > :nReg1" +
		" and re.numero <= :nReg2" ;
		Query query = session.createQuery(querya);
		
	    query.setParameter("tReg", "HBA", Hibernate.STRING);
	    query.setParameter("nReg1", numero1, Hibernate.STRING);
	    query.setParameter("nReg2", numero2, Hibernate.STRING);
	   // query.setParameter("year",year,Hibernate.STRING);
	    List results = query.list();
	    
	    /*	criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));*/
	    return results;
	}
	public static List findAnimalesMachosPedigreeRangosAnimalHBA(Integer numero1,Integer numero2) {
		Session session = HibernateFactory.getSession();    	
    	String	querya = "from AnimalHBA an " +
    			"where an.id > 0 and an.numero > :nReg1" +
		" and an.numero <= :nReg2" ;
		Query query = session.createQuery(querya);
		query.setParameter("nReg1", numero1, Hibernate.INTEGER);
	    query.setParameter("nReg2", numero2, Hibernate.INTEGER);
	   // query.setParameter("year",year,Hibernate.STRING);
	    List results = query.list();
	    
	    /*	criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));*/
	    return results;
	}
	public static List findAnimalesMachosPedigree() throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();    	
    	
		String	querya = "select an from Animal an left join an.regOrigen re where an.class = Macho" +
		" and re.id = an.regOrigen " +
		" and re.tipoRegistro.id = :tReg";
		
		Query query = session.createQuery(querya);
	    query.setParameter("tReg", "HBA", Hibernate.STRING);
	    List results = query.list();
	    
	   
	    return results;
	
	}

	/**
	 * @param inicioRango
	 * @param longRango
	 * @return
	 * @throws HibernateException 
	 */
	public static List findAll(int inicioRango, int finRango) throws HibernateException {
		Session session = HibernateFactory.getSession();
		String queryText = "from Animal where :inicioRango<= ID and ID < :finRango";
		Query query = session.createQuery(queryText);
		query.setParameter("inicioRango", inicioRango, Hibernate.INTEGER);
		query.setParameter("finRango", finRango, Hibernate.INTEGER);
		return query.list();
	}
	/**
	 * Metodo que recupera todos los animal del tambo establecimiento que tengan registro RC.
	 * Es utilizado para el reporte RCNUME
	 * @param establecimiento
	 * @return
	 * @throws HibernateException
	 */
	public static List findAnimalesWithRCByEstablecimiento(Establecimiento tambo) throws HibernateException {
		Session session = HibernateFactory.getSession();
		String queryText = "select an from Animal an where an.establecimiento = :establecimiento and an.regOrigen.tipoRegistro = :tipoRegistro";
		Query query = session.createQuery(queryText);
		query.setEntity("establecimiento",tambo);
		TipoRegistro tipoRegRC = TipoRegistroDAO.findByPrimaryKey("RC");
		query.setEntity("tipoRegistro",tipoRegRC);
		return query.list();
	}
	public static List findAnimalesPropietario(Propietario prop) throws HibernateException {
		Session session = HibernateFactory.getSession();
		String queryText = "select an from Animal an where an.propietario = :prop";
		Query query = session.createQuery(queryText);
		query.setEntity("prop",prop);
		
		return query.list();
	}
	
//	public static List findComRacialPorAnimal(Animal animal) throws HibernateException {
//		Session session = HibernateFactory.getSession();
//		String queryText = "from ComposicionRacial an where an.propietario = :prop";
//		Query query = session.createQuery(queryText);
//		query.setEntity("prop",prop);
//		
//		return query.list();
//	}
	
	/**
	 * 
	 * @param estab
	 * @param propietario
	 * @param rp
	 * @param razaDeclarada 
	 * @param compRacial no puede ser null
	 * @param msgs
	 * @return
	 */
	public static Animal create(Establecimiento estab, Propietario propietario, Registro reg,boolean esHembra,String rp, ComposicionRacial comp, Map compRacial, List msgs,Date fechaNacimiento,String rpSe,Integer cod)
	throws ExcepcionIntegridad {
		//no se verifica aca que se pueda especificar una raza declarada,etc.. porque se verifica en el evento alta,
		//cosa que se puedan crear animales por otro lado sin esa restriccion
		Animal a = createComun(estab,propietario,reg,esHembra,rp,msgs,fechaNacimiento,true);
		//ComposicionRacial comp = ComposicionRacial.nuevaInstancia(razaDeclarada);
		a.setComposicionRacial(comp);
		comp.setAnimal(a);
		comp.setearComposicionRacial(compRacial); //OJO cuando se llama a este metodo, el animal 
		a.setRpSenasa(rpSe);
		a.setCodigoVerificador(cod);
		//asociado ya tiene que tener seteado su conjunto de hijos y de clones
		return a;
	}
	
	/**
	 * metodo utilizado solo para animales de pedigree
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Animal createPedigree(Establecimiento estab, Propietario propietario, Registro reg,boolean esHembra,String rp, ComposicionRacial comp, Map compRacial, List msgs,Date fechaNacimiento)
	throws ExcepcionIntegridad {
		//no se verifica aca que se pueda especificar una raza declarada,etc.. porque se verifica en el evento alta,
		//cosa que se puedan crear animales por otro lado sin esa restriccion
		Animal a = createComun(estab,propietario,reg,esHembra,rp,msgs,fechaNacimiento, false);
		//ComposicionRacial comp = ComposicionRacial.nuevaInstancia(razaDeclarada);
		a.setComposicionRacial(comp);
		comp.setAnimal(a);
		comp.setearComposicionRacial(compRacial); //OJO cuando se llama a este metodo, el animal 
												//asociado ya tiene que tener seteado su conjunto de hijos y de clones
		return a;
	}
	
	public static Animal createCambioSexo(Establecimiento estab, Propietario propietario,boolean esHembra,String rp, ComposicionRacial comp, Map compRacial, List msgs,Date fechaNacimiento)
	throws ExcepcionIntegridad {
		Animal a= inicCreateSexo(estab,propietario,esHembra,rp);
		a.setComposicionRacial(comp);
		comp.setAnimal(a);
		comp.setearComposicionRacial(compRacial); 
		return a;
	}
	
	private static Animal inicCreateSexo(Establecimiento estab, Propietario propietario,boolean esHembra,String rp) throws ExcepcionIntegridad{
		Animal a;
		if (esHembra)
			a = HembraDAO.create();
		else
			a = MachoDAO.create();
		a.setEvtClons(new HashSet());
		a.setEstablecimiento(estab);
		a.setEstancia(estab!=null?estab.getEstancia():null);
		a.setEstablecimientoCriador(estab);
		a.setPropietario(propietario);
		a.setPropietarioCriador(propietario);
		a.setRP(rp.trim());
		a.setRegistros(new HashSet());
		
	a.setCalificacions(new HashSet());
	a.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
		return a;
	}
	/**
	 * IMPORTANTE: despues de llamar a este metodo 1º baja de ori y despues alta de a
	 * @param aniOrig
	 * @param msgs
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Animal createCambioSexoPadres(Animal aniOrig, List msgs)
	throws ExcepcionIntegridad {
		Animal a = inicCreateSexo(aniOrig.getEstablecimiento(), aniOrig.getPropietario(), !aniOrig.esHembra(),aniOrig.getRP());
		Especie especie = null;
		Raza razaDeclarada = aniOrig.getRaza();
		if (razaDeclarada != null)  
			especie = razaDeclarada.getEspecie();
		else
			if (aniOrig.getMadreGenetica() != null)	
				especie = aniOrig.getMadreGenetica().getEspecie();
			else
				if (aniOrig.getPadre() != null)
					especie = aniOrig.getPadre().getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		
		a.inicializarPadresGen(especie,aniOrig.getMadreGenetica(),aniOrig.getPadre());
		a.getComposicionRacial().setRazaDeclarada(razaDeclarada);
		if((razaDeclarada ==null)&&(aniOrig.getMadreGenetica()!=null && aniOrig.getPadre()!=null)&&(!aniOrig.getMadreGenetica().getRaza().getNombre().equals(aniOrig.getPadre().getRaza().getNombre()))){
			if(!a.getComposicionRacial().getRazaCalculada().getEsDesconocido())
				a.getComposicionRacial().setRazaDeclarada(a.getComposicionRacial().getRazaCalculada());
			else
				a.getComposicionRacial().setRazaDeclarada(especie.getCruza());
		}
		Registro regOri=RegistroDAO.create(aniOrig.getRegOrigen().getTipoRegistro().getId(),aniOrig.getRegOrigen().getNumero(),aniOrig.getRegOrigen().getCodigoBaja()==null?"":aniOrig.getRegOrigen().getCodigoBaja().toString(),aniOrig.getRegOrigen().getFechaBaja());
		Registro regId = RegistroDAO.create(aniOrig.getRegIdentificador().getTipoRegistro().getId(),aniOrig.getRegIdentificador().getNumero(),aniOrig.getRegIdentificador().getCodigoBaja()==null?"":aniOrig.getRegIdentificador().getCodigoBaja().toString(),aniOrig.getRegIdentificador().getFechaBaja());
		if(regId.equals(regOri)){
			a.setRegIdentificador(regId);
			a.setRegOrigen(regId);
			a.addRegistro(regId);
		}
		else{
			a.setRegOrigen(regOri);
			a.addRegistro(regOri);
			a.setRegIdentificador(regId);
			a.addRegistro(regId);
		}
		Iterator it = aniOrig.getRegistros().iterator();
		while(it.hasNext()){
			Registro d = (Registro)it.next();
			
			if(!a.getRegistros().contains(d)){
				Registro reg=RegistroDAO.create(d.getTipoRegistro().getId(),d.getNumero(),d.getCodigoBaja()==null ?null:d.getCodigoBaja().toString(),d.getFechaBaja());
				a.addRegistro(reg);
			}
		}
		
		a.setApodo(aniOrig.getApodo());
		a.setAsoc(aniOrig.getAsoc());
		a.setAsom(aniOrig.getAsoc());
		a.setAsop(aniOrig.getAsop());
		a.setCodigoVerificador(aniOrig.getCodigoVerificador());
		a.setDadorSemen(aniOrig.getDadorSemen());
		a.setDonante(aniOrig.getDonante());
		a.setFechaTransf(aniOrig.getFechaTransf());
		a.setFechaServicio(aniOrig.getFechaServicio());
		a.setFechaNac(aniOrig.getFechaNac());
		a.setFechaTransf(aniOrig.getFechaTransf());
		a.setFechaUltObs(aniOrig.getFechaUltObs());
		a.setNumeroTransf(aniOrig.getNumeroTransf());
		a.setNumeroAnalADN(aniOrig.getNumeroAnalADN());
		a.setMellizo(aniOrig.getMellizo());
		a.setRpti(aniOrig.getRpti());
		a.setSRAFesb(aniOrig.getSRAFesb());
		a.setTipoServicio(aniOrig.getTipoServicio());
		a.setMadreParto(aniOrig.getMadreParto());
		a.setPadre(aniOrig.getPadre());
		a.setTransferencia(aniOrig.getTransferencia());
		
		
		
		
		
		a.setearCategoria(true);
		return a;
	}
	
	/**
	 * metodo que actualiza un animal
	 * @param estab
	 * @param propietario
	 * @param reg
	 * @param esHembra
	 * @param rp
	 * @param comp
	 * @param compRacial
	 * @param msgs
	 * @param fechaNacimiento
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static void actualizar(Animal ani,Establecimiento estab, Propietario propietario, Registro reg,boolean esHembra,String rp, ComposicionRacial comp, Map compRacial, List msgs,Date fechaNacimiento)
	throws ExcepcionIntegridad {
		//no se verifica aca que se pueda especificar una raza declarada,etc.. porque se verifica en el evento alta,
		//cosa que se puedan crear animales por otro lado sin esa restriccion
		actualizarComun(ani,estab,propietario,reg,esHembra,rp.trim(),msgs,fechaNacimiento);
		//ComposicionRacial comp = ComposicionRacial.nuevaInstancia(razaDeclarada);
		ani.setComposicionRacial(comp);
		comp.setAnimal(ani);
		comp.setearComposicionRacial(compRacial); //OJO cuando se llama a este metodo, el animal 
												//asociado ya tiene que tener seteado su conjunto de hijos y de clones
		//return a;
	}
	/**
	 * metodo utilizado para crear un animal teniendo en cuenta el boolean crearRegistro q indica si hay
	 * que crear un registro o no, esto se debe a los casos de animales de pedigree y el animal que se crear
	 * para hacer sinonimos
	 */
	private static Animal createComun(Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp,List<ProcMsg> msgs, Date fechaNacimiento,boolean crearRegistro) throws ExcepcionIntegridad {
		if(objEst!=null && StringUtils.isNotEmpty(rp))
			Animal.checkUnicidadRPEnEstab(objEst,rp,msgs, fechaNacimiento,objEst,"[Sin Registro Asignado]");
		return  AnimalDAO.createAnimalComun(objEst,propietario,reg,esHembra,rp,msgs,fechaNacimiento,crearRegistro);
	}
	/**
	 * Es la parte comun de Crear un animal, ya sea con Padre y Madre o con Composicion Racial
	 * TENER CUIDADO QUE NO SE CHEQUEA LA UNICIDAD DE RP, ESO SE HACE EN CREATECOMUN
	 * @param objEst
	 * @param propietario
	 * @param reg
	 * @param esHembra
	 * @param rp
	 * @param razaDeclarada
	 * @return
	 */
	
	private static Animal createAnimalComun(Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp,List<ProcMsg> msgs, Date fechaNacimiento,boolean crearRegistro) throws ExcepcionIntegridad {
		
		Animal a;
		if (esHembra)
			a = HembraDAO.create();
		else
			a = MachoDAO.create();
		a.setEvtClons(new HashSet());
		a.setEstablecimiento(objEst);
		if(objEst!=null)
			a.setEstancia(objEst.getEstancia());
		a.setEstablecimientoCriador(objEst);

	//	objEst.getAnimals().add(a);
		if(propietario!=null){
			a.setPropietario(propietario);
			a.setPropietarioCriador(propietario);
			//propietario.getAnimals().add(a);
			
		}
		
		if(StringUtils.isNotEmpty(rp))
			a.setRP(rp.trim());
		a.setRegistros(new HashSet());
		//MODIF No se acepta por ahora pasar registros de origen
		if (reg != null) { //si se pasa un registro, ese es el origen y el identificador
			throw new ExcepcionIntegridad(MENSAJES.REG_ORIGEN_NO_SOPORTADO, new String[]{});
//			a.addRegistro(reg);
//			a.setRegIdentificador(reg);
//			a.setRegOrigen(reg);
		} 
		//si no se especifico ningun registro o la regla de crear siempre esta activa, se crea y asigna un RC
		if (crearRegistro && (reg == null || Configuracion.getValorReglaProceso(CONF.CREAR_REGISTRO_SIEMPRE, new Date()))) { 
			TipoRegistro rc;
			try {
				rc = TipoRegistroDAO.findByPrimaryKey("RC");
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new ErrorFatal("Error al buscar el Tipo de Registro RC - AnimalDAO",e);
			}
			String registro = rc.proximoNumero();
			Registro regAsignado = RegistroDAO.create(rc.getId(),registro);
//			ProcMsg msg = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {a.getRP(), regAsignado.getNumero(), regAsignado.getTipoRegistro().getId(), a.getCategoria()});
//			msgs.add(msg);
			if (a.getRegIdentificador() == null) { //si no tiene un identificador todabia 
				a.setRegIdentificador(regAsignado);
				a.setRegOrigen(regAsignado);
			}
			a.addRegistro(regAsignado);
		}
	a.setCalificacions(new HashSet());
//	a.setEsActivo(true);
//	a.setEsBaja(false);
	a.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
//	a.setProcAnimals(new HashSet());
	
	return a;	
	}
	
	
	private static void actualizarComun(Animal ani,Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp,List<ProcMsg> msgs, Date fechaNacimiento) throws ExcepcionIntegridad {
		if(objEst!=null && StringUtils.isNotEmpty(rp)&& fechaNacimiento!=null)
			Animal.checkUnicidadRPEnEstabForUpdate(ani.getId(),objEst,rp,msgs, fechaNacimiento,ani.getEstablecimiento(),ani.getRegistroOrigen(),ani.getCategoria());
		
		if(objEst!=null){
			ani.setEstablecimiento(objEst);
			ani.setEstablecimientoCriador(objEst);
			ani.setEstancia(objEst.getEstancia());
		}
	//	objEst.getAnimals().add(a);
		ani.setPropietario(propietario);
		//propietario.getAnimals().add(ani);
		if(StringUtils.isNotEmpty(rp))
			ani.setRP(rp.trim());
		else 
			ani.setRP(null);
	}
	
	
	public static Animal create(Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp, Raza razaDeclarada, Hembra madreGen, Macho padreGen, List<ProcMsg> msgs, Date fechaNacimiento) throws ExcepcionIntegridad {
		Animal a = createComun(objEst,propietario,reg,esHembra,rp.trim(),msgs, fechaNacimiento,false);
		Especie especie = null;
		if (razaDeclarada != null)  
			especie = razaDeclarada.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		
		a.inicializarPadresGen(especie,madreGen,padreGen);
		a.getComposicionRacial().setRazaDeclarada(razaDeclarada);
		if((razaDeclarada ==null)&&(madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getNombre().equals(padreGen.getRaza().getNombre()))){
			if(!a.getComposicionRacial().getRazaCalculada().getEsDesconocido())
				a.getComposicionRacial().setRazaDeclarada(a.getComposicionRacial().getRazaCalculada());
			else
				a.getComposicionRacial().setRazaDeclarada(especie.getCruza());
		}
		return a;
	}
	/**
	 * metodo que se utiliza para crear un animal que es sinonimo, se crea y luego se le asignan los eventos de
	 * los dos animales en comun
	 * */
	public static Animal createPadSin(Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp, Raza razaDeclarada, Hembra madreGen, Macho padreGen, List<ProcMsg> msgs, Date fechaNacimiento,Long idA1,Long idA2) throws ExcepcionIntegridad {
		if(objEst!=null && StringUtils.isNotEmpty(rp)){
			Animal.checkUnicidadRPSinonimo(idA1,idA2,objEst,rp,msgs, fechaNacimiento,objEst,"[Sin Registro Asignado]",Animal.CAT_PED);
		}
		Animal a = createAnimalComun(objEst,propietario,reg,esHembra,rp,msgs, fechaNacimiento,false);
		Especie especie = null;
		if (razaDeclarada != null)  
			especie = razaDeclarada.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		
		a.inicializarPadresGen(especie,madreGen,padreGen);
		a.getComposicionRacial().setRazaDeclarada(razaDeclarada);
		if((razaDeclarada ==null)&&(madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getNombre().equals(padreGen.getRaza().getNombre()))){
			if(!a.getComposicionRacial().getRazaCalculada().getEsDesconocido())
				a.getComposicionRacial().setRazaDeclarada(a.getComposicionRacial().getRazaCalculada());
			else
				a.getComposicionRacial().setRazaDeclarada(especie.getCruza());
		}
		return a;
	}
	
	public static Animal create(Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp, Raza razaDeclarada, Hembra madreGen, Macho padreGen, List<ProcMsg> msgs, Date fechaNacimiento,String rpSe,Integer cod,boolean fe) throws ExcepcionIntegridad {
		Animal a = createComun(objEst,propietario,reg,esHembra,rp,msgs, fechaNacimiento,true);
		Especie especie = null;
		if (razaDeclarada != null)  
			especie = razaDeclarada.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		
		if(fe){
			a.inicializarPadresGen(especie,madreGen,padreGen);
			a.getComposicionRacial().setRazaDeclarada(razaDeclarada);
			if((razaDeclarada ==null)&&(madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getNombre().equals(padreGen.getRaza().getNombre()))){
				if(!a.getComposicionRacial().getRazaCalculada().getEsDesconocido())
					a.getComposicionRacial().setRazaDeclarada(a.getComposicionRacial().getRazaCalculada());
				else
					a.getComposicionRacial().setRazaDeclarada(especie.getCruza());
			}
		}
		else{
			a.calcularComposicionRacial(especie);
			if(razaDeclarada == null)
				//esto ocurre cuando no da la varible del evento con la fecha de envio y 
				//la fecha del evento y los padres son razas<> por lo tanto no se carga 
				//la raza, no se hace antes pq puede q se calcule autmaticamente como es el caso de arriba
				a.getComposicionRacial().setRazaDeclarada(especie.getCruza());
			else
				a.getComposicionRacial().setRazaDeclarada(razaDeclarada);
		}
		a.setRpSenasa(rpSe);
		a.setCodigoVerificador(cod);
		return a;
	}
	
	public static void actualizar(Animal ani, Establecimiento objEst, Propietario propietario, Registro reg,boolean esHembra, String rp, Raza razaDeclarada, Hembra madreGen, Macho padreGen, List<ProcMsg> msgs, Date fechaNacimiento, Boolean usarRazaMadre) throws ExcepcionIntegridad {
		//estoy usando para cria
		
		actualizarComun(ani,objEst,propietario,reg,esHembra,rp,msgs, fechaNacimiento);
		Especie especie = null;
//		si se declara una raza el animal es de esa especie
//		sino se usa la especie del padre o de la madre
//		(no pueden padre y madre ser null y no especificarce una razaDeclarada, 
// 		porque sino no se puede determinar la especie del animal.
		if (razaDeclarada != null)  
			especie = razaDeclarada.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		if((ani.getMadreGenetica()!=null && madreGen==null)
				||(ani.getPadre()!=null && padreGen==null)
				||(ani.getMadreGenetica()==null && madreGen!=null)
				|| (ani.getPadre()==null && padreGen!=null)
				||(ani.getPadre()!=null && padreGen!=null && !ani.getPadre().equals(padreGen))
				|| (ani.getMadreGenetica()!=null && madreGen!=null && !ani.getMadreGenetica().equals(madreGen)))
			//si cambio algo en los padres--->cambio en los hijos
					ani.inicializarPadresGen(especie,madreGen,padreGen);
		
		ani.getComposicionRacial().setRazaDeclarada(razaDeclarada);
		if(usarRazaMadre !=null && padreGen == null){
			if(usarRazaMadre)//si puso usar raza de madre en true
				ani.getComposicionRacial().setRazaDeclarada(madreGen.getRaza());
			else
				if(!ani.getComposicionRacial().getRazaCalculada().getEsDesconocido())
					ani.getComposicionRacial().setRazaDeclarada(ani.getComposicionRacial().getRazaCalculada());
				else
					ani.getComposicionRacial().setRazaDeclarada(especie.getCruza());
				
			
		}
		else	
			if((madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getNombre().equals(padreGen.getRaza().getNombre()))&&(razaDeclarada ==null)){
					ani.getComposicionRacial().setRazaDeclarada(ani.getComposicionRacial().getRazaCalculada());
			}
			else{
				if(madreGen!=null || padreGen !=null){
					if(!ani.getComposicionRacial().getRazaCalculada().getEsDesconocido())
						ani.getComposicionRacial().setRazaDeclarada(ani.getComposicionRacial().getRazaCalculada());
					else
						ani.getComposicionRacial().setRazaDeclarada(especie.getCruza());
				}
			}
		
		
		
	}

	public static void updateAnimal(Animal animal) {
		HibernateFactory.getSession().update(animal);
	}
	
	public static Animal findAnimal(String rp, Establecimiento establecimiento, String year,String sexo){
		Session session = HibernateFactory.getSession();
		Criteria criterio = null;
		if (sexo.equals("Hembra"))
			criterio = session.createCriteria(Hembra.class);
		else
			criterio = session.createCriteria(Macho.class);
		criterio.add(Expression.eq("RP", rp));
		criterio.add(Expression.eq("establecimiento", establecimiento));
		criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));
		
		return (Animal) criterio.uniqueResult();//NO ANDA  NI PARA ATRAS*/
	}
	public static Animal findAnimalRpTamboProp(String rp, Establecimiento establecimiento, String year,Propietario prop){
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		criterio.add(Expression.eq("RP", rp.trim()));
		criterio.add(Expression.eq("establecimiento", establecimiento));
		criterio.add(Expression.eq("propietario", prop));
		criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));
		
		return (Animal) criterio.uniqueResult();//NO ANDA  NI PARA ATRAS*/
	}

	
	/**
	 * TODO esto se tiene que terminar cuando se asocie un usuario con un en_contacto
	 * @param rp
	 * @param establecimiento
	 * @param year
	 * @return
	 */
	public static List findAnimalesPorFiltro(String rp, Establecimiento establecimiento, String year, Usuario usuario,String sexo) {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		if (sexo.equals("Hembra")){
			criterio = session.createCriteria(Hembra.class);
		}
		else{
			criterio = session.createCriteria(Macho.class);
		}
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));
		if (establecimiento != null)
			criterio.add(Expression.eq("establecimiento", establecimiento));
		if (StringUtils.isNotEmpty(year)){
			if (Integer.parseInt(year)< 1900 || Integer.parseInt(year) > DateUtils.getAnio(new Date()))
					return new ArrayList();
		
		criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));
		}
		String nombreRol = usuario.getRol().getNombre();
		if (Tokens.NOMBREROLADMINISTRADOR.equals(nombreRol)) // es una especia de ADMINISTRADOR
			; // puede ver todos los animales
		else if (Tokens.NOMBREROLCASANOVA.equals(nombreRol)) // es una especie de ADMINISTRADOR
			; // puede ver todos los animales
		else if (Tokens.NOMBREROLGENERAL.equals(nombreRol)) // es una especie de ADMINISTRADOR
			;	// puede ver todos los animales
		else if (Tokens.NOMBREROLDATAENTRY.equals(nombreRol)) // es una especie de ADMINISTRADOR
			;	// puede ver todos los animales
		else if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
			criterio.add(Expression.eq("propietario", usuario.getContacto()));			
		}
		else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {
			Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
			criterioEstablecimiento.add(Expression.eq("eclo", usuario.getContacto()));
		}
		else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) {
			Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
			Criteria criterioEclo = criterioEstablecimiento.createCriteria("eclo");
			criterioEclo.add(Expression.eq("regional", usuario.getContacto()));
		} else if (Tokens.NOMBREROLSRA.equals(nombreRol)){
			criterio.add(Expression.eq("categoria", Animal.CAT_PED));
		}
		
		return criterio.list();	
	}
	/**
	 * Este metodo realiza la busqueda por rp, año de nacimiento y establecimiento (estancia) y 
	 * ademas se realiza otro filtro segun el rol del usuario que esta realizando la consulta, esto se 
	 * deb a que si es un usuario eclo, los animales que puede buscar deben perteneces a tambos
	 * que administra dicha eclo.
	 * Los mismo ocurre con el rol propietario, busca sobre sus animales solamente
	 * El rol administrador, general y tecnica buscan sobre todos los animales
	 * @param rp
	 * @param estancia
	 * @param year
	 * @param usuario
	 * @return
	 * @throws ExcepcionIntegridad 
	 * @throws HibernateException 
	 */
	public static List findAnimalesPorFiltro(String rp, Estancia estancia, String year, Usuario usuario,String delSistema) throws HibernateException, ExcepcionIntegridad {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);		
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));
		
		/*Criteria criterioTambo = criterio.createCriteria("establecimiento");
		criterioTambo.add(Expression.eq("estancia", estancia));*/
		/*if (establecimiento != null)
			criterio.add(Expression.eq("establecimiento", establecimiento));*/
		if (estancia != null)
			criterio.add(Expression.eq("estancia", estancia));
		if (StringUtils.isNotEmpty(year)){
			if (Integer.parseInt(year)< 1900 || Integer.parseInt(year) > DateUtils.getAnio(new Date()))
					return new ArrayList();
		
		criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", year, Hibernate.STRING));
		}
		String nombreRol = usuario.getRol().getNombre();
		if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)){
			if (delSistema.equals("no")||((delSistema.equals("si"))&&(!(Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date()))))){
				Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
				criterioEstablecimiento.add(Expression.eq("eclo", usuario.getContacto()));
				//si eligio de la eclo o eligio todos y no se acepta buscar de otras entonces pongo la condicion
			}
			
		}
		
		/*if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)&&!(Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date()))) {
			if (delSistema.equals("no")){
				Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
				criterioEstablecimiento.add(Expression.eq("eclo", usuario.getContacto()));
			}
		}*/
		else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) {
				if (delSistema.equals("no")){
					Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
					Criteria criterioEclo = criterioEstablecimiento.createCriteria("eclo");
					criterioEclo.add(Expression.eq("regional", usuario.getContacto()));
				}
		}
		List animals = criterio.list();
		Animal anim = null;
		if (!animals.isEmpty()){
			anim = (Animal)animals.get(0);
			if (Tokens.NOMBREROLADMINISTRADOR.equals(nombreRol)) // es una especia de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLCASANOVA.equals(nombreRol)) // es una especie de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLGENERAL.equals(nombreRol)) // es una especie de ADMINISTRADOR
				;	// puede ver todos los animales
			else if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
				//si el animal es hembra, esta debera pertencer al propietario logueado
				if (anim.esHembra() && !anim.getPropietario().getId().equals(usuario.getContacto().getId()))
					return new ArrayList();
			}
			/*else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)&&(!(Configuracion.getValorReglaProceso(CONF.ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL, new Date())))) {
				//si el animal es hembra, esta debera pertencer a la eclo logueada
				if (anim.esHembra() && !anim.getEstablecimiento().getEclo().getId().equals(usuario.getContacto().getId()))
					return new ArrayList();
			}*/
		}
		
		return animals;	
	}
	
	 public static Animal findByRegistryAndUser(String tRegistro, String nRegistro,String raza,String sexo, Usuario usuario)
		throws org.hibernate.HibernateException {
		 Session session = HibernateFactory.getSession();    	
			String	querya="";
			if(sexo.equals("M")){
				querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
				" where an.regOrigen = reg.id" +
				" and (rc is null or rc.id = :raza) and" +
				" reg.tipoRegistro.id = :tReg and reg.numero = :nReg" +
				" and an.class = Macho";
			}
			else{
			
				querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
								" where an.regOrigen = reg.id" +
								" and (rc is null or rc.id = :raza) and" +
								" reg.tipoRegistro.id = :tReg and reg.numero = :nReg" +
								" and an.class = Hembra";
			}
			String nombreRol = usuario.getRol().getNombre();
			if (Tokens.NOMBREROLADMINISTRADOR.equals(nombreRol)) // es una especia de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLCASANOVA.equals(nombreRol)) // es una especie de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLGENERAL.equals(nombreRol)) // es una especie de ADMINISTRADOR
				;	// puede ver todos los animales
			else if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
				//flag = true;
				querya = querya + " and an.propietario = :contacto ";			
			}
			else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {// establecimiento que pertenece a la eclo
				//flag = true;
				querya = querya + " and an.establecimiento.eclo = :contacto ";
			}
			else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) { // establecimiento en la regional
				//flag = true;
				querya = querya + " and an.establecimiento.eclo.regional = :contacto";
			}
			else if (Tokens.NOMBREROLSRA.equals(nombreRol)){
				querya = querya + " and an.categoria = 'PED'";
			}
			Query query = session.createQuery(querya);
			query.setParameter("tReg", tRegistro, Hibernate.STRING);
			query.setParameter("nReg", nRegistro, Hibernate.STRING);
			query.setParameter("raza", raza, Hibernate.STRING);
			if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
		    	Propietario propietario = PropietarioDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", propietario);
		    }else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {
		    	Eclo eclo = EcloDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", eclo);
		    }else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) {
		    	EntidadRegional regional = EntidadRegionalDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", regional);
		    }
			Object o = query.uniqueResult();
			if(o == null)
				return null;
			return (Animal)o;	
		 
		 
		 /*
		 Session session = HibernateFactory.getSession();    	
			String	querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
			" where an.regOrigen = reg.id and " +
			" (rc is null or rc.id = :raza) and " +
			" reg.tipoRegistro.id = :tReg and reg.numero = :nReg ";
			String nombreRol = usuario.getRol().getNombre();
			if (Tokens.NOMBREROLADMINISTRADOR.equals(nombreRol)) // es una especia de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLCASANOVA.equals(nombreRol)) // es una especie de ADMINISTRADOR
				; // puede ver todos los animales
			else if (Tokens.NOMBREROLGENERAL.equals(nombreRol)) // es una especie de ADMINISTRADOR
				;	// puede ver todos los animales
			else if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
				//flag = true;
				querya = querya + " and an.propietario = :contacto ";			
			}
			else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {// establecimiento que pertenece a la eclo
				//flag = true;
				querya = querya + " and an.establecimiento.eclo = :contacto ";
			}
			else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) { // establecimiento en la regional
				//flag = true;
				querya = querya + " and an.establecimiento.eclo.regional = :contacto";
			}	
			
			Query query = session.createQuery(querya);
		    query.setParameter("tReg", tRegistro, Hibernate.STRING);
		    query.setParameter("nReg", nRegistro, Hibernate.STRING);
		    query.setParameter("raza", raza, Hibernate.STRING);
		    if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
		    	Propietario propietario = PropietarioDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", propietario);
		    }else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {
		    	Eclo eclo = EcloDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", eclo);
		    }else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) {
		    	EntidadRegional regional = EntidadRegionalDAO.findByPrimaryKey(usuario.getContacto().getId());
		    	query.setParameter("contacto", regional);
		    }
		   
		    List results = query.list();
		    if(results.isEmpty())
		    	return null;
		    // como mucho 2 animales
		    
		    if (sexo.equals("M") && results.get(0) instanceof Macho)
		    	return (Macho) results.get(0);
		    if (sexo.equals("H") && results.get(0) instanceof Hembra)
		    	return (Hembra) results.get(0);
		    
		    if (results.size() > 1) {
		    	if (sexo.equals("M") && results.get(1) instanceof Macho)
			    	return (Macho) results.get(1);
			    if (sexo.equals("H") && results.get(1) instanceof Hembra)
			    	return (Hembra) results.get(1);
		    }
		    return null;	    	
		 */   
	    }
	 
	 public static Animal findByRegistryAndUserSinEclo(String tRegistro, String nRegistro,String raza,String sexo)
		throws org.hibernate.HibernateException {
		 Session session = HibernateFactory.getSession();    	
			String	querya="";
			querya = "select an from Registro reg left join reg.animal an left join an.composicionRacial.razaDeclarada rc" +
				" where an.regOrigen = reg.id" +
				" and (rc is null or rc.id = :raza) and" +
				" reg.tipoRegistro.id = :tReg and reg.numero = :nReg"+
				(sexo.equals("M") ? " and an.class = Macho" : " and an.class = Hembra");
			
			Query query = session.createQuery(querya);
			query.setParameter("tReg", tRegistro, Hibernate.STRING);
			query.setParameter("nReg", nRegistro, Hibernate.STRING);
			query.setParameter("raza", raza, Hibernate.STRING);
			//TODO PREGUNTAR SI ES MACHO O HEMBRA, Y HACER LO QUE CORRESPONDA
			Object o = query.uniqueResult();
			if(o == null)
				return null;
			return (Animal)o;
	 }
	    
    /**
     * Dada la madre genetica devuelve solo 5 hijos de esta madre 
     * @param madreGenetica
     * @return
     */
    public static Collection getHijasGeneticas(Animal madreGenetica) {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		criterio.add(Expression.eq("madreGenetica", madreGenetica));
		criterio.addOrder(Order.desc("fechaNac"));
		criterio.setMaxResults(5);
		return criterio.list();
    }
    /**
     * Dado un animal retorna sus hijos, en caso de que el animal pasado como
     * parametro es una hembra retorma solamente los 10 primeros hijos, en caso de que sea un macho
     * retornar todos sus hijos
     * @param madreGenetica
     * @return
     */
    public static Collection getHijosGeneticos(Animal animal) {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		if(animal.esHembra()){
			criterio.add(Expression.eq("madreGenetica", animal));
			criterio.setMaxResults(10);
		}
		else{
			criterio.add(Expression.eq("padre", animal));
		}
		criterio.addOrder(Order.desc("fechaNac"));
		
		return criterio.list();

    }

    /**
     * Dado el rp del animal, una fecha y un tambo, devuelve el animal con ese rp, con dia de nac
     * igual al dia de la fecha, con mes igual al mes de la fecha, con año igual al año de la fecha y con el mismo 
     * establecimiento (estancia)
     * 
     * @param rp
     * @param fecha
     * @param tambo
     * @return
     */
	public static Animal findByRPFNacEstab(String rp, Date fecha, Establecimiento establecimiento) {		
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		SimpleDateFormat formatDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		
		String dia = formatDia.format(fecha);
		String mes = formatMes.format(fecha);
		String anio = formatAnio.format(fecha);
		
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));
		//Criteria criterioTambo = criterio.createCriteria("establecimiento");
		//criterioTambo.add(Expression.eq("estancia", estancia));
		
		if (establecimiento != null){
			//criterio.add(Expression.eq("establecimiento", establecimiento));
			Criteria criterioTambo = criterio.createCriteria("establecimiento");
			criterioTambo.add(Expression.eq("estancia", establecimiento.getEstancia()));
		}
		
		if (StringUtils.isNotEmpty(anio))
			criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", anio, Hibernate.STRING));
		if (StringUtils.isNotEmpty(mes))
			criterio.add(Expression.sql("EXTRACT(MONTH FROM {alias}.fnac)=?", mes, Hibernate.STRING));
		if (StringUtils.isNotEmpty(dia))
			criterio.add(Expression.sql("EXTRACT(DAY FROM {alias}.fnac)=?", dia, Hibernate.STRING));
		List l = criterio.list();
		if ( l == null || l.isEmpty()) {
			return null;			
		}
		else 
			return (Animal) l.get(0); // devuelvo el primero 
	}
	public static Animal findByRPFNacMadrePadre(String rp, Date fecha, Hembra madreGenetica, Macho padre) {		
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		String anio = formatAnio.format(fecha);
		
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));
		criterio.add(Expression.eq("madreGenetica",madreGenetica));
		criterio.add(Expression.eq("padre",padre));
		//Criteria criterioTambo = criterio.createCriteria("establecimiento");
		//criterioTambo.add(Expression.eq("estancia", estancia));
		
		if (StringUtils.isNotEmpty(anio))
			criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.fnac)=?", anio, Hibernate.STRING));
		
		List l = criterio.list();
		if ( l == null || l.isEmpty()) {
			return null;			
		}
		else 
			return (Animal) l.get(0); // devuelvo el primero 
	}
	public static List findByNombre(String nombre,String sexo) throws org.hibernate.HibernateException {
        Session session = HibernateFactory.getSession();
        	nombre = "%" + nombre.toUpperCase() + "%";
    		Query query =null;
   		 if (sexo.equals("H"))        	
    		 query = session.createQuery("from Hembra as p where upper(p.nombre) like :nomContacto");
   		 else
   			query = session.createQuery("from Macho as p where upper(p.nombre) like :nomContacto");
   		 query.setParameter("nomContacto",nombre);
            return query.list();	
      
			
	}
	
	public static List findAllHembrasEntreIntervalo(String raza,Integer idStart,Integer idEnd,Session session) throws org.hibernate.HibernateException {
   		Query query =null;
   		query = session.createQuery("select a from Animal as a where "/*not exists (select r from Registro as r where r.tipoRegistro.id = 'RC' and to_number(r.numero) <= 4378413 and a.regOrigen.id = r.id) "*/ +
   												" a.class = Hembra and a.composicionRacial.razaDeclarada.id = '"+raza+"' and a.id >= 118000 and a.id <119000");
        return query.list();
        
	}
	
	public static List findHembrasInicianLactanciaEntreIntervalo(String raza,Integer idStart,Integer idEnd,Session session) throws org.hibernate.HibernateException {
   		SQLQuery query =null;
   		//query = session.createSQLQuery("select animal as ANIMAL from animal_inicia_lactancia where animal >= "+idStart.toString()+" and rownum <= "+idEnd.toString());
   		//query.addScalar("ANIMAL", Hibernate.LONG);
   		//query = session.createSQLQuery("select b.id_animal as ANIMAL from (select (a.*)b,rownum rnum from(select * from animales_lactancias)a where rownum <= "+idEnd.toString()+") where rnum > "+idStart.toString() +";");
   		query = session.createSQLQuery("select id_animal as ANIMAL from (select a.*,rownum rnum from(select * from animales_lactancias)a where rownum <="+idEnd.toString()+") where rnum > "+idStart.toString());
   		/*select * 
   	  from ( select a.*, ROWNUM rnum 
   	      from ( select * from ev_animal) a 
   	      where ROWNUM <= 
   	      1000 ) 
   	where rnum  >= 0;*/
   		
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//que
   		query.addScalar("ANIMAL", Hibernate.LONG);
        return query.list();
        
	}
	public static List findHembrasInicianLactanciaEntreIntervalo2(String raza,Integer idStart,Integer idEnd,Session session,Long desde, Long hasta) throws org.hibernate.HibernateException {
   		SQLQuery query =null;
   		//query = session.createSQLQuery("select animal as ANIMAL from animal_inicia_lactancia where animal >= "+idStart.toString()+" and rownum <= "+idEnd.toString());
   		//query.addScalar("ANIMAL", Hibernate.LONG);
   		//query = session.createSQLQuery("select b.id_animal as ANIMAL from (select (a.*)b,rownum rnum from(select * from animales_lactancias)a where rownum <= "+idEnd.toString()+") where rnum > "+idStart.toString() +";");
   		query = session.createSQLQuery("select id_animal as ANIMAL from (select a.*,rownum rnum from(select id_animal from animales_lactancias where id_animal >= " + desde.toString()+" and id_animal <"+hasta.toString()+")a where rownum <="+idEnd.toString()+") where rnum > "+idStart.toString());
   		/*select * 
   	  from ( select a.*, ROWNUM rnum 
   	      from ( select * from ev_animal) a 
   	      where ROWNUM <= 
   	      1000 ) 
   	where rnum  >= 0;*/
   		
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//que
   		query.addScalar("ANIMAL", Hibernate.LONG);
        return query.list();
        
	}
	public static List findHembrasInicianLactanciaEntreIntervaloDesde(String raza,Integer idStart,Integer idEnd,Session session,Long desde) throws org.hibernate.HibernateException {
   		SQLQuery query =null;
   		//query = session.createSQLQuery("select animal as ANIMAL from animal_inicia_lactancia where animal >= "+idStart.toString()+" and rownum <= "+idEnd.toString());
   		//query.addScalar("ANIMAL", Hibernate.LONG);
   		//query = session.createSQLQuery("select b.id_animal as ANIMAL from (select (a.*)b,rownum rnum from(select * from animales_lactancias)a where rownum <= "+idEnd.toString()+") where rnum > "+idStart.toString() +";");
   		query = session.createSQLQuery("select id_animal as ANIMAL from (select a.*,rownum rnum from(select id_animal from animales_lactancias where id_animal > " + desde.toString()+" order by id_animal asc)a where rownum <="+idEnd.toString()+") where rnum > "+idStart.toString());
   		/*select * 
   	  from ( select a.*, ROWNUM rnum 
   	      from ( select * from ev_animal) a 
   	      where ROWNUM <= 
   	      1000 ) 
   	where rnum  >= 0;*/
   		
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//query.addScalar("id_ANIMAL", Hibernate.LONG);
   		//que
   		query.addScalar("ANIMAL", Hibernate.LONG);
        return query.list();
        
	}
	public static List findAllHembrasConEventosColgados(Session session) throws org.hibernate.HibernateException {
   		SQLQuery query =null;
   		query = session.createSQLQuery("select animal as ANIMAL from ANIMAL_RECALCULOS_EVENTOS");
   		query.addScalar("ANIMAL", Hibernate.LONG);
        return query.list();
        
	}
	public static List findAllRecalculoComposicion(Session session) throws org.hibernate.HibernateException {
   		SQLQuery query =null;
   		query = session.createSQLQuery("select id as ANIMAL from animal_recalculos_composicion");
   		query.addScalar("ANIMAL", Hibernate.LONG);
        return query.list();
        
	}
	
	/**
	 * Creador de un animal de pedigree sin padres
	 * @param tamboCriador
	 * @param propCri
	 * @param raza
	 * @param tambo
	 * @param prop
	 * @param regOri
	 * @param esHembra
	 * @param msg
	 * @param fechaN
	 * @param rp
	 * @param nombre
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Animal createAnimalPedEmpadronar(Establecimiento tamboCriador,Propietario propCri,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre) throws ExcepcionIntegridad{
		Animal nuevoAnimal=null;
		Map comp = new HashMap(); 
		//comp.put(raza.getEspecie().getDesconocida(),new Float(1));
		//hago esta modificacion ya que un animal de pedigree la composicion debe ser 100% de la raza informante 
		comp.put(raza,new Float(1));
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(raza);
		nuevoAnimal = AnimalDAO.createPedigree(tambo, prop,regOri, esHembra, rp.trim(), compR, comp, msg, fechaN);
		nuevoAnimal.setRegIdentificador(null);
		nuevoAnimal.setRegOrigen(null);
		nuevoAnimal.setRegistros(new HashSet());
		if(tamboCriador!=null)
			 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
		if(propCri!=null)
			 nuevoAnimal.setPropietarioCriador(propCri);
		else
			nuevoAnimal.setPropietarioCriador(prop);
		nuevoAnimal.setNombre(nombre);
		nuevoAnimal.setFechaNac(fechaN);
		nuevoAnimal.setCategoria(Animal.CAT_PED);
		return nuevoAnimal;
	}
	
	public static Animal createAnimalPedEmpadronarSinonimo(Establecimiento tamboCriador,Propietario propCri,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre,Long idA1,Long idA2) throws ExcepcionIntegridad{
		Animal nuevoAnimal=null;
		Map comp = new HashMap(); 
		//comp.put(raza.getEspecie().getDesconocida(),new Float(1));
		//hago esta modificacion ya que un animal de pedigree la composicion debe ser 100% de la raza informante 
		comp.put(raza,new Float(1));
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(raza);
		//nuevoAnimal = AnimalDAO.create(tambo, prop,regOri, esHembra, rp, compR, comp, msg, fechaN);
		if(tambo!=null && StringUtils.isNotEmpty(rp)){
			Animal.checkUnicidadRPSinonimo(idA1,idA2,tambo,rp.trim(),msg, fechaN,tambo,"[Sin Registro Asignado]",Animal.CAT_PED);
		}
		nuevoAnimal = createAnimalComun(tambo,prop,regOri,esHembra,rp.trim(),msg,fechaN,false);
		//ComposicionRacial comp = ComposicionRacial.nuevaInstancia(razaDeclarada);
		nuevoAnimal.setComposicionRacial(compR);
		compR.setAnimal(nuevoAnimal);
		compR.setearComposicionRacial(comp);
		
		nuevoAnimal.setRegIdentificador(null);
		nuevoAnimal.setRegOrigen(null);
		nuevoAnimal.setRegistros(new HashSet());
		if(tamboCriador!=null)
			 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
		if(propCri!=null)
			 nuevoAnimal.setPropietarioCriador(propCri);
		else
			nuevoAnimal.setPropietarioCriador(prop);
		nuevoAnimal.setNombre(nombre);
		nuevoAnimal.setFechaNac(fechaN);
		nuevoAnimal.setCategoria(Animal.CAT_PED);
		return nuevoAnimal;
	}
	/**
	 * creacion de un animal de pedigree con padres o tambien usado para la realizacion de sinonimos
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static Animal createAnimalPedPadres(Establecimiento tamboCriador,Propietario propCri,Macho padre,Hembra madre,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre,Long idA1,Long idA2) throws ExcepcionIntegridad{
		Animal nuevoAnimal=null;
		if(madre != null && padre !=null){
			String razaM = madre.getRaza().getId();
			String razaP = padre.getRaza().getId();
			if(razaM.equals(razaP)){
				if(!razaM.equals(raza.getId())){//si razas igual P y M e hijo
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{rp});
				}
				//si los padres son de pedigree la cria es ped y 100% de la raza la composicion
				if(madre.getCategoria().equals("PED") && padre.getCategoria().equals("PED")){
					if(idA1!=null && idA2!=null)
						nuevoAnimal = AnimalDAO.createAnimalPedEmpadronarSinonimo(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,idA1,idA2);
					else
						nuevoAnimal = AnimalDAO.createAnimalPedEmpadronar(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre);
					nuevoAnimal.setPadre(padre);
					nuevoAnimal.setMadreGenetica(madre);
				}
				else{//sino, es categoria ped pero lacomposicion es de acuerdo a los padres 
					if(idA1!=null && idA2!=null)//si viene por sinonimo
						nuevoAnimal = AnimalDAO.createPadSin(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,idA1,idA2);
					else
						nuevoAnimal = AnimalDAO.create(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
					nuevoAnimal.setRegIdentificador(null);
					nuevoAnimal.setRegOrigen(null);
					nuevoAnimal.setRegistros(new HashSet());
					nuevoAnimal.setNombre(nombre);
					nuevoAnimal.setFechaNac(fechaN);
					 if(tamboCriador!=null)
						 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
					
					 if(propCri!=null)
						 nuevoAnimal.setPropietarioCriador(propCri);
					 else
						 if(nuevoAnimal.getEstablecimientoCriador()!=null)
							 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
				}
				
			}
			else{//si son !=las razas de los padres
				if(madre.getCategoria().equals("PED") && padre.getCategoria().equals("PED")){
					if(idA1!=null && idA2!=null)
						nuevoAnimal = AnimalDAO.createAnimalPedEmpadronarSinonimo(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,idA1,idA2);
					else
						nuevoAnimal = AnimalDAO.createAnimalPedEmpadronar(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre);
					
					
					nuevoAnimal.setPadre(padre);
					nuevoAnimal.setMadreGenetica(madre);
				}
				else{
					if(idA1!=null && idA2!=null)
						nuevoAnimal = AnimalDAO.createPadSin(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,idA1,idA2);
					else
						nuevoAnimal = AnimalDAO.create(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
					nuevoAnimal.setNombre(nombre);
					nuevoAnimal.setFechaNac(fechaN);
					 if(tamboCriador!=null)
						 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
					 if(propCri!=null)
						 nuevoAnimal.setPropietarioCriador(propCri);
					 else
						 if(nuevoAnimal.getEstablecimientoCriador()!=null)
							 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
					
					 if(nuevoAnimal.getComposicionRacial().getRazaCalculada().getEsDesconocido()&&(!nuevoAnimal.getComposicionRacial().getRazaDeclarada().getEsCruza()))
							throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,new String[] {});
						
						if ((!nuevoAnimal.getComposicionRacial().getRazaCalculada().equals(
								nuevoAnimal.getComposicionRacial().getRazaDeclarada())) 
								&&(!nuevoAnimal.getComposicionRacial().getRazaCalculada().getEsDesconocido())){
							throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
									new String[] {});
						}
				}
			}
		}
		else{
			if(madre!=null && padre==null){//si informo madre sola
				if(!raza.getId().equals(madre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{rp});
			}
			else{//si informo padre solo
				if(!raza.getId().equals(padre.getRaza().getId())){
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRE, new String[]{});
				}
			}
			if((madre!=null && madre.getCategoria().equals("PED")) || (padre!= null && padre.getCategoria().equals("PED"))){
				if(idA1!=null && idA2!=null)
					nuevoAnimal = AnimalDAO.createAnimalPedEmpadronarSinonimo(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,idA1,idA2);
				else
					nuevoAnimal = AnimalDAO.createAnimalPedEmpadronar(tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre);
				nuevoAnimal.setPadre(padre);
				nuevoAnimal.setMadreGenetica(madre);
			}
			else{
				if(idA1!=null && idA2!=null)
					nuevoAnimal = AnimalDAO.createPadSin(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,idA1,idA2);
				else
					nuevoAnimal = AnimalDAO.create(tambo, prop,	regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
				
				nuevoAnimal.setNombre(nombre);
				nuevoAnimal.setFechaNac(fechaN);
				nuevoAnimal.setRegIdentificador(null);
				nuevoAnimal.setRegOrigen(null);
				nuevoAnimal.setRegistros(new HashSet());
				if(tamboCriador!=null)
					 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
				
				 if(propCri!=null)
					 nuevoAnimal.setPropietarioCriador(propCri);
				 else
					 if(nuevoAnimal.getEstablecimientoCriador()!=null)
						 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
			}
		}
		int dias = 30*15;
		if(fechaN!=null){
			if(madre!=null){
				if(madre.getFechaNac()!=null){
					Date fechaMa= new Date(madre.getFechaNac().getTime());
			        if(!fechaMa.before(DateUtils.menos(fechaN, new Integer(dias) )))
			        	    	 throw new ExcepcionIntegridad(MENSAJES.MADRE_NO_MAYOR, new String[]{});
				}
		    }
			if(padre!=null){
			if(padre.getFechaNac()!=null){
					Date fechaPa= new Date(padre.getFechaNac().getTime());
		   	     if(!fechaPa.before(DateUtils.menos(fechaN, new Integer(dias) )))
		   	    	throw new ExcepcionIntegridad(MENSAJES.PADRE_NO_MAYOR, new String[]{});
				}
			}
		}
		nuevoAnimal.setCategoria(Animal.CAT_PED);
		/**/
		//Map comp = new HashMap(); 
		//comp.put(raza.getEspecie().getDesconocida(),new Float(1));
		//hago esta modificacion ya que un animal de pedigree la composicion debe ser 100% de la raza informante 
		/*comp.put(raza,new Float(1));
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(raza);
		nuevoAnimal.setComposicionRacial(compR);
		compR.setAnimal(nuevoAnimal);
		compR.setearComposicionRacial(comp);*/
		return nuevoAnimal;
	}
	public static Animal createAnimalPedPadres2(Establecimiento tamboCriador,Propietario propCri,Macho padre,Hembra madre,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre) throws ExcepcionIntegridad{
		Animal nuevoAnimal=null;
		if(madre != null && padre !=null){
			String razaM = madre.getRaza().getId();
			String razaP = padre.getRaza().getId();
			if(razaM.equals(razaP)){
				if(!razaM.equals(raza.getId())){//si razas igual P y M e hijo
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{rp});
				}
				nuevoAnimal = AnimalDAO.create(tambo, prop,
						regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
				nuevoAnimal.setRegIdentificador(null);
				nuevoAnimal.setRegOrigen(null);
				nuevoAnimal.setRegistros(new HashSet());
				nuevoAnimal.setNombre(nombre);
				nuevoAnimal.setFechaNac(fechaN);
				 if(tamboCriador!=null)
					 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
				
				 if(propCri!=null)
					 nuevoAnimal.setPropietarioCriador(propCri);
				 else
					 if(nuevoAnimal.getEstablecimientoCriador()!=null)
						 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
			}
			else{//si son !=las razas de los padres
				nuevoAnimal = AnimalDAO.create(tambo, prop,
						regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
				
				nuevoAnimal.setNombre(nombre);
				nuevoAnimal.setFechaNac(fechaN);
				 if(tamboCriador!=null)
					 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
				 if(propCri!=null)
					 nuevoAnimal.setPropietarioCriador(propCri);
				 else
					 if(nuevoAnimal.getEstablecimientoCriador()!=null)
						 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
				
				 if(nuevoAnimal.getComposicionRacial().getRazaCalculada().getEsDesconocido()&&(!nuevoAnimal.getComposicionRacial().getRazaDeclarada().getEsCruza()))
						throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,new String[] {});
					
					if ((!nuevoAnimal.getComposicionRacial().getRazaCalculada().equals(
							nuevoAnimal.getComposicionRacial().getRazaDeclarada())) 
							&&(!nuevoAnimal.getComposicionRacial().getRazaCalculada().getEsDesconocido())){
						throw new ExcepcionIntegridad(
								MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
								new String[] {});
					}
			}
		}
		else{
			if(madre!=null && padre==null){//si informo madre sola
				if(!raza.getId().equals(madre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{rp});
			}
			else{//si informo padre solo
				if(!raza.getId().equals(padre.getRaza().getId())){
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRE, new String[]{});
				}
			}
			nuevoAnimal = AnimalDAO.create(tambo, prop,
					regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN);
			
			nuevoAnimal.setNombre(nombre);
			nuevoAnimal.setFechaNac(fechaN);
			nuevoAnimal.setRegIdentificador(null);
			nuevoAnimal.setRegOrigen(null);
			nuevoAnimal.setRegistros(new HashSet());
			if(tamboCriador!=null)
				 nuevoAnimal.setEstablecimientoCriador(tamboCriador);
			
			 if(propCri!=null)
				 nuevoAnimal.setPropietarioCriador(propCri);
			 else
				 if(nuevoAnimal.getEstablecimientoCriador()!=null)
					 nuevoAnimal.setPropietarioCriador(nuevoAnimal.getEstablecimientoCriador().getPropietario());
		}
		int dias = 30*15;
		if(fechaN!=null){
			if(madre!=null){
				if(madre.getFechaNac()!=null){
					Date fechaMa= new Date(madre.getFechaNac().getTime());
			        if(!fechaMa.before(DateUtils.menos(fechaN, new Integer(dias) )))
			        	    	 throw new ExcepcionIntegridad(MENSAJES.MADRE_NO_MAYOR, new String[]{});
				}
		    }
			if(padre!=null){
			if(padre.getFechaNac()!=null){
					Date fechaPa= new Date(padre.getFechaNac().getTime());
		   	     if(!fechaPa.before(DateUtils.menos(fechaN, new Integer(dias) )))
		   	    	throw new ExcepcionIntegridad(MENSAJES.PADRE_NO_MAYOR, new String[]{});
				}
			}
		}
		nuevoAnimal.setCategoria(Animal.CAT_PED);
		/**/
		return nuevoAnimal;
	}
	/**
	 * 
	 * @param masiva es para determinar si es por carga masiva o por pantalla, en caso de que sea por carga masiva
	 * no se envian los tambos (null) pero no deben cambiarse en cambio si es por pantalla se pueden poner los tambos 
	 * en blanco (null) y esto si se debe actualizar en los animales
	 * @throws ExcepcionIntegridad
	 */
	public static void actualizarAnimalPedEmpadronar(Animal ani,Establecimiento tamboCriador,Propietario propCri,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre,boolean masiva) throws ExcepcionIntegridad{
		
		Map comp = new HashMap(); 
		//comp.put(raza.getEspecie().getDesconocida(),new Float(1));
//		hago esta modificacion ya que un animal de pedigree la composicion debe ser 100% de la raza informante 
		comp.put(raza,new Float(1));
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(raza);
		ani.setPadre(null);
		ani.setMadreGenetica(null);
		AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), compR, comp, msg, fechaN);
		if(tamboCriador!=null)
			 ani.setEstablecimientoCriador(tamboCriador);
		if(!masiva){
			 ani.setEstablecimientoCriador(tamboCriador);
			 ani.setEstablecimiento(tambo);
			 if (tambo != null)
				 ani.setEstancia(tambo.getEstancia());
			 else
				 ani.setEstancia(null);
		}
		if(propCri!=null)
			 ani.setPropietarioCriador(propCri);
		else
			ani.setPropietarioCriador(ani.getEstablecimientoCriador().getPropietario());
	}
	/**
	 * 
	 * @param masiva es para determinar si es por carga masiva o por pantalla, en caso de que sea por carga masiva
	 * no se envian los tambos (null) pero no deben cambiarse en cambio si es por pantalla se pueden poner los tambos 
	 * en blanco (null) y esto si se debe actualizar en los animales
	 * @throws ExcepcionIntegridad
	 */
	public static void actulizarAnimalPedPadres(Animal ani,Establecimiento tamboCriador,Propietario propCri,Macho padre,Hembra madre,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre,boolean masiva) throws ExcepcionIntegridad{
		
		if(madre != null && padre !=null){
			String razaM = madre.getRaza().getId();
			String razaP = padre.getRaza().getId();
			if(razaM.equals(razaP)){
				if(!razaM.equals(raza.getId())){//si razas no igual P y M e hijo
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{rp});
				}
				if(madre.getCategoria().equals("PED") && padre.getCategoria().equals("PED")){
					
					AnimalDAO.actualizarAnimalPedEmpadronar(ani,tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,masiva);
					ani.setPadre(padre);
					ani.setMadreGenetica(madre);
				}
				else{
					AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
					if(tamboCriador!=null)
						 ani.setEstablecimientoCriador(tamboCriador);
					 if(propCri!=null)
						 ani.setPropietarioCriador(propCri);
				}
				
			}
			else{//si son !=las razas de los padres
				if(madre.getCategoria().equals("PED") && padre.getCategoria().equals("PED")){
					AnimalDAO.actualizarAnimalPedEmpadronar(ani,tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,masiva);
					ani.setPadre(padre);
					ani.setMadreGenetica(madre);
				}
				else{
					AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
					if(tamboCriador!=null)
						 ani.setEstablecimientoCriador(tamboCriador);
					 if(propCri!=null)
						 ani.setPropietarioCriador(propCri);
					 else
						 ani.setPropietarioCriador(ani.getEstablecimientoCriador().getPropietario());
					 if(ani.getComposicionRacial().getRazaCalculada().getEsDesconocido()&&(!ani.getComposicionRacial().getRazaDeclarada().getEsCruza()))
							throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,new String[] {});
						
						if ((!ani.getComposicionRacial().getRazaCalculada().equals(
								ani.getComposicionRacial().getRazaDeclarada())) 
								&&(!ani.getComposicionRacial().getRazaCalculada().getEsDesconocido())){
							throw new ExcepcionIntegridad(
									MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
									new String[] {});
						}
					}
			}
		}
		else{
			if(madre!=null && padre==null){//si informo madre sola
				if(!raza.getId().equals(madre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{rp});
			}
			else{
				if(!raza.getId().equals(padre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRE, new String[]{});
				
			}
			if((madre!=null && madre.getCategoria().equals("PED")) || (padre!= null && padre.getCategoria().equals("PED"))){
				AnimalDAO.actualizarAnimalPedEmpadronar(ani,tamboCriador,propCri,raza,tambo,prop,regOri,esHembra,msg,fechaN,rp.trim(),nombre,masiva);
				ani.setPadre(padre);
				ani.setMadreGenetica(madre);
			}
			else{
				AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
				if(tamboCriador!=null)
					 ani.setEstablecimientoCriador(tamboCriador);
				if(propCri!=null)
					 ani.setPropietarioCriador(propCri);
				else
					ani.setPropietarioCriador(ani.getEstablecimientoCriador().getPropietario());
			}
		}
		int dias = 30*15;
		if(fechaN!=null){
			if(madre!=null && madre.getFechaNac()!=null){
				Date fechaMa= new Date(madre.getFechaNac().getTime());
		        if(!fechaMa.before(DateUtils.menos(fechaN, new Integer(dias) )))
		        	    	 throw new ExcepcionIntegridad(MENSAJES.MADRE_NO_MAYOR, new String[]{});
		    }
			if(padre!=null && padre.getFechaNac()!=null){
				Date fechaPa= new Date(padre.getFechaNac().getTime());
	   	     if(!fechaPa.before(DateUtils.menos(fechaN, new Integer(dias) )))
	   	    	throw new ExcepcionIntegridad(MENSAJES.PADRE_NO_MAYOR, new String[]{});
			}
		}
		if(!masiva){
			 ani.setEstablecimientoCriador(tamboCriador);
			 ani.setEstablecimiento(tambo);
			 if (tambo != null)
				 ani.setEstancia(tambo.getEstancia());
			 else
				 ani.setEstancia(null);
		}
		
	}
	public static void actulizarAnimalPedPadres2(Animal ani,Establecimiento tamboCriador,Propietario propCri,Macho padre,Hembra madre,Raza raza,Establecimiento tambo,Propietario prop,Registro regOri,
			boolean esHembra,List msg,Date fechaN,String rp,String nombre) throws ExcepcionIntegridad{
		
		if(madre != null && padre !=null){
			String razaM = madre.getRaza().getId();
			String razaP = padre.getRaza().getId();
			if(razaM.equals(razaP)){
				if(!razaM.equals(raza.getId())){//si razas no igual P y M e hijo
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{rp});
				}
				AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
				if(tamboCriador!=null)
					 ani.setEstablecimientoCriador(tamboCriador);
				 if(propCri!=null)
					 ani.setPropietarioCriador(propCri);
			}
			else{//si son !=las razas de los padres
				AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
				if(tamboCriador!=null)
					 ani.setEstablecimientoCriador(tamboCriador);
				 if(propCri!=null)
					 ani.setPropietarioCriador(propCri);
				 else
					 ani.setPropietarioCriador(ani.getEstablecimientoCriador().getPropietario());
				 if(ani.getComposicionRacial().getRazaCalculada().getEsDesconocido()&&(!ani.getComposicionRacial().getRazaDeclarada().getEsCruza()))
						throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,new String[] {});
					
					if ((!ani.getComposicionRacial().getRazaCalculada().equals(
							ani.getComposicionRacial().getRazaDeclarada())) 
							&&(!ani.getComposicionRacial().getRazaCalculada().getEsDesconocido())){
						throw new ExcepcionIntegridad(
								MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
								new String[] {});
					}
			}
		}
		else{
			if(madre!=null && padre==null){//si informo madre sola
				if(!raza.getId().equals(madre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{rp});
			}
			else{
				if(!raza.getId().equals(padre.getRaza().getId()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRE, new String[]{});
				
			}
			AnimalDAO.actualizar(ani,tambo, prop,regOri, esHembra, rp.trim(), raza, madre, padre, msg,fechaN,null);
			if(tamboCriador!=null)
				 ani.setEstablecimientoCriador(tamboCriador);
			if(propCri!=null)
				 ani.setPropietarioCriador(propCri);
			else
				ani.setPropietarioCriador(ani.getEstablecimientoCriador().getPropietario());
		}
		int dias = 30*15;
		if(fechaN!=null){
			if(madre!=null && madre.getFechaNac()!=null){
				Date fechaMa= new Date(madre.getFechaNac().getTime());
		        if(!fechaMa.before(DateUtils.menos(fechaN, new Integer(dias) )))
		        	    	 throw new ExcepcionIntegridad(MENSAJES.MADRE_NO_MAYOR, new String[]{});
		    }
			if(padre!=null && padre.getFechaNac()!=null){
				Date fechaPa= new Date(padre.getFechaNac().getTime());
	   	     if(!fechaPa.before(DateUtils.menos(fechaN, new Integer(dias) )))
	   	    	throw new ExcepcionIntegridad(MENSAJES.PADRE_NO_MAYOR, new String[]{});
			}
		}
		
	}
	
	public static List findAnimalesPorRpyPropietario(String rp, Propietario propietario, Usuario usuario) {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));

		if (propietario != null)
			criterio.add(Expression.eq("propietario", propietario));
		String nombreRol = usuario.getRol().getNombre();
		if (Tokens.NOMBREROLADMINISTRADOR.equals(nombreRol)) // es una especia de ADMINISTRADOR
			; // puede ver todos los animales
		else if (Tokens.NOMBREROLCASANOVA.equals(nombreRol)) // es una especie de ADMINISTRADOR
			; // puede ver todos los animales
		else if (Tokens.NOMBREROLGENERAL.equals(nombreRol)) // es una especie de ADMINISTRADOR
			;	// puede ver todos los animales
		else if (Tokens.NOMBREROLPROPIETARIO.equals(nombreRol)) {
			criterio.add(Expression.eq("propietario", usuario.getContacto()));			
		}
		else if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol)) {
			Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
			criterioEstablecimiento.add(Expression.eq("eclo", usuario.getContacto()));
		}
		else if (Tokens.NOMBREROLREGIONAL.equals(nombreRol)) {
			Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
			Criteria criterioEclo = criterioEstablecimiento.createCriteria("eclo");
			criterioEclo.add(Expression.eq("regional", usuario.getContacto()));
		}
		
		return criterio.list();		
	}
	public static List findAnimalesPorRpyPropietarioyEclo(String rp, Propietario propietario, Eclo eclo,Establecimiento tambo,Contacto contact,String delSistema) {
		Session session = HibernateFactory.getSession();
		Criteria criterio = session.createCriteria(Animal.class);
		if (StringUtils.isNotEmpty(rp))
			criterio.add(Expression.eq("RP", rp.trim()));

		if (propietario != null)
			criterio.add(Expression.eq("propietario", propietario));
		if (tambo != null)
			criterio.add(Expression.eq("establecimiento", tambo));	
		if (eclo!=null && delSistema.equals("no")) {
			Criteria criterioEstablecimiento = criterio.createCriteria("establecimiento");
			criterioEstablecimiento.add(Expression.eq("eclo", eclo));
		}
		if (contact != null){
			criterio.add(Expression.sql("class = Macho or (class = Hembra and (propietario.id = "+contact.getId()+" or establecimiento.eclo.id = "+contact.getId()));
		}
		return criterio.list();		
	}

	 public static List findByRazaSexoyUser(String raza,String sexo, Usuario usuario,Propietario propietario, Establecimiento tambo,String delSistema)
		throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		String querya;
  		 if (sexo.equals("H"))        	
    		 querya = "select an from Hembra an ";
   		 else
   			querya = "select an from Macho an ";

		querya += "inner join an.composicionRacial.razaDeclarada rc where rc.id = :raza";
		String nombreRol = usuario.getRol().getNombre();
		if (propietario != null)
			querya = querya + " and an.propietario = :prop ";
		if(tambo !=null)	
			querya = querya + " and an.establecimiento = :tambo ";
		//querya = querya + " and an.propietario = :contacto ";
		//query.setParameter("contacto", propietario);
		if (Tokens.NOMBREROLPROVEEDOR.equals(nombreRol) && sexo.equals("H"))// establecimiento que pertenece a la eclo
			querya += " and an.establecimiento.eclo = :contacto ";
		else if (delSistema.equals("si")){
				if(Tokens.NOMBREROLPROVEEDOR.equals(nombreRol))
					querya += " and an.establecimiento.eclo = :contacto ";
				else if(Tokens.NOMBREROLREGIONAL.equals(nombreRol))
					querya += " and an.establecimiento.eclo.reginal = :contacto ";
		}
		Query query = session.createQuery(querya);
		query.setParameter("raza", raza, Hibernate.STRING);
		//si se loguea una eclo o un propietario, y el animal es hembra, la eclo o propietario del animal, debera ser igual al logueado, respectivamente
		if ((Tokens.NOMBREROLPROVEEDOR.equals(nombreRol) && sexo.equals("H")) || delSistema.equals("si")) {
		 	Eclo eclo = EcloDAO.findByPrimaryKey(usuario.getContacto().getId());
		   	query.setParameter("contacto", eclo);
		}else if ((Tokens.NOMBREROLPROPIETARIO.equals(nombreRol) && sexo.equals("H"))){
			Propietario prop = PropietarioDAO.findByPrimaryKey(usuario.getContacto().getId());
		   	query.setParameter("contacto", prop);
		}
		if(propietario != null)
			query.setParameter("prop", propietario);
		if(tambo!=null)
			query.setParameter("tambo", tambo);
		return query.list();
	 }
	 public static List findAnimalesId() throws org.hibernate.HibernateException {
			Session session = HibernateFactory.getSession();    	
	    	//local
			//String	querya = "select an from Animal an where id in (6000001	, 6000002	, 6000003	, 6000004	, 6000005	, 6000006	, 6000007	, 6000008	, 6000009	, 6000010	, 6000011	, 6000012	, 6000013	, 6000014	, 6000015	, 6000016	, 6000017	, 6000018	, 6000019	, 6000020	, 6000021	, 6000022	, 6000023	, 6000024	, 6000025	, 6000026	, 6000027	, 6000028	, 6000029	, 6000030	, 6000031	, 6000032	, 6000033	, 6000034	, 6000035	, 6000036	, 6000037	, 6000038	, 6000039	, 6000040	, 6000041	, 6000042	, 6000043	, 6000044	, 6000045	, 6000046	, 6000047	, 6000048	, 6000049	, 6000050	, 6000051	, 6000052	, 6000053	, 6000054	, 6000055	, 6000056	, 6000057	, 6000058	, 6000059	, 6000060	, 6000061	, 6000062	, 6000063	, 6000064	, 6000065	, 6000066	, 6000067	, 6000068	, 6000069	, 6000070	, 6000071	, 6000072	, 6000073	, 6000074	, 6000075	, 6000076	, 6000077	, 6000078	, 6000079	, 6000080	, 6000081	, 6000082	, 6000083	, 6000084	, 6000085	, 6000086	, 6000087	, 6000088	, 6000089	, 6000090	, 6000091	, 6000092	, 6000093	, 6000094	, 6000095	, 6000096	, 6000097	, 6000098	, 6000099	, 6000100	, 6000101	, 6000102	, 6000103	, 6000104	, 6000105	, 6000106	, 6000107	, 6000108	, 6000109	, 6000110	, 6000111	, 6000112	, 6000113	, 6000114	, 6000115	, 6000116	, 6000117	, 6000118	, 6000119	, 6000120	, 6000121	, 6000122	, 6000123	, 6000124	, 6000125	, 6000126	, 6000127	, 6000128	, 6000129	, 6000130	, 6000131	, 6000132	, 6000133	, 6000134	, 6000135	, 6000136	, 6000137	, 6000138	, 6000139	, 6000140	, 6000141	, 6000142	, 6000143	, 6000144	, 6000145	, 6000146	, 6000147	, 6000148	, 6000149	, 6000150	, 6000151	, 6000152	, 6000153	, 6000154	, 6000155	, 6000156	, 6000157	, 6000158	, 6000159	, 6000160	, 6000161	, 6000162	, 6000163	, 6000164	, 6000165	, 6000166	, 6000167	, 6000168	, 6000169	, 6000170	, 6000171	, 6000172	, 6000173	, 6000174	, 6000175	, 6000176	, 6000177	, 6000178	, 6000179	, 6000180	, 6000181	, 6000182	, 6000183	, 6000184	, 6000185	, 6000186	, 6000187	, 6000188	, 6000189	, 6000190	, 6000191	, 6000192	, 6000193	, 6000194	, 6000195	, 6000196	, 6000197	, 6000198	, 6000199	, 6000200	, 6000201	, 6000202	, 6000203	, 6000204	, 6000205	, 6000206	, 6000207	, 6000208	, 6000209	, 6000210	, 6000211	, 6000212	, 6000213	, 6000214	, 6000215	, 6000216	, 6000217	, 6000218	, 6000219	, 6000220	, 6000221	, 6000222	, 6000223	, 6000224	, 6000225	, 6000226	, 6000227	, 6000228	, 6000229	, 6000230	, 6000231	, 6000232	, 6000233	, 6000234	, 6000235	, 6000236	, 6000237	, 6000238	, 6000239	, 6000240	, 6000241	, 6000242	, 6000243	, 6000244	, 6000245	, 6000246	, 6000247	, 6000248	, 6000249	, 6000250	, 6000251	, 6000252	, 6000253	, 6000254	, 6000255	, 6000256	, 6000257	, 6000258	, 6000259	, 6000260	, 6000261	, 6000262	, 6000263	, 6000264	, 6000265	, 6000266	, 6000267	, 6000268	, 6000269	, 6000270	, 6000271	, 6000272	, 6000273	, 6000274	, 6000275	, 6000276	, 6000277	, 6000278	, 6000279	, 6000280	, 6000281	, 6000282	, 6000283	, 6000284	, 6000285	, 6000286	, 6000287	, 6000288	, 6000289	, 6000290	, 6000291	, 6000292	, 6000293	, 6000294	, 6000295	, 6000296	, 6000297	, 6000298	, 6000299	, 6000300	, 6000301	, 6000302	, 6000303	, 6000304	, 6000305	, 6000306	, 6000307	, 6000308	, 6000309	, 6000310	, 6000311	, 6000312	, 6000313	, 6000314	, 6000315	, 6000316	, 6000317	, 6000318	, 6000319	, 6000320	, 6000321	, 6000322	, 6000323	, 6000324	, 6000325	, 6000326	, 6000327	, 6000328	, 6000329	, 6000330	, 6000331	, 6000332	, 6000333	, 6000334	, 6000335	, 6000336	, 6000337	, 6000338	, 6000339	, 6000340	, 6000341	, 6000342	, 6000343	, 6000344	, 6000345	, 6000346	, 6000347	, 6000348	, 6000349	, 6000350	, 6000351	, 6000352	, 6000353	, 6000354	, 6000355	, 6000356	, 6000357	, 6000358	, 6000359	, 6000360	, 6000361	, 6000362	, 6000363	, 6000364	, 6000365	, 6000366	, 6000367	, 6000368	, 6000369	, 6000370	, 6000371	, 6000372	, 6000373	, 6000374	, 6000375	, 6000376	, 6000377	, 6000378	, 6000379	, 6000380	, 6000381	, 6000382	, 6000383	, 6000384	, 6000385	, 6000386	, 6000387	, 6000388	, 6000389	, 6000390	, 6000391	, 6000392	, 6000393	, 6000394	, 6000395	, 6000396	, 6000397	, 6000398	, 6000399	, 6000400	, 6000401	, 6000402	, 6000403	, 6000404	, 6000405	, 6000406	, 6000407	, 6000408	, 6000409	, 6000410	, 6000411	, 6000412	, 6000413	, 6000414	, 6000415	, 6000416	, 6000417	, 6000418	, 6000419	, 6000420	, 6000421	, 6000422	, 6000423	, 6000424	, 6000425	, 6000426	, 6000427	, 6000428	, 6000429	, 6000430	, 6000431	, 6000432	, 6000433	, 6000434	, 6000435	, 6000436	, 6000437	, 6000438	, 6000439	, 6000440	, 6000441	, 6000442	, 6000443	, 6000444	, 6000445	, 6000446	, 6000447	, 6000448	, 6000449	, 6000450	, 6000451	, 6000452	, 6000453	, 6000454	, 6000455	, 6000456	, 6000457	, 6000458	, 6000459	, 6000460	, 6000461	, 6000462	, 6000463	, 6000464	, 6000465	, 6000466	, 6000467	, 6000468	, 6000469	, 6000470	, 6000471	, 6000472	, 6000473	, 6000474	, 6000475	, 6000476	, 6000477	, 6000478	, 6000479	, 6000480	, 6000481	, 6000482	, 6000483	, 6000484	, 6000485	, 6000486	, 6000487	, 6000488	, 6000489	, 6000490	, 6000491	, 6000492	, 6000493	, 6000494	, 6000495	, 6000496	, 6000497	, 6000498	, 6000499	, 6000500	, 6000501	, 6000502	, 6000503	, 6000504	, 6000505	, 6000506	, 6000507	, 6000508	, 6000509	, 6000510	, 6000511	, 6000512	, 6000513	, 6000514	, 6000515	, 6000516	, 6000517	, 6000518	, 6000519	, 6000520	, 6000521	, 6000522	, 6000523	, 6000524	, 6000525	, 6000526	, 6000527	, 6000528	, 6000529	, 6000530	, 6000531	, 6000532	, 6000533	, 6000534	, 6000535	, 6000536	, 6000537	, 6000538	, 6000539	, 6000540	, 6000541	, 6000542	, 6000543	, 6000544	, 6000545	, 6000546	, 6000547	, 6000548	, 6000549	, 6000550	, 6000551	, 6000552	, 6000553	, 6000554	, 6000555	, 6000556	, 6000557	, 6000558	, 6000559	, 6000560	, 6000561	, 6000562	, 6000563	, 6000564	, 6000565	, 6000566	, 6000567	, 6000568	, 6000569	, 6000570	, 6000571	, 6000572	, 6000573	, 6000574	, 6000575	, 6000576	, 6000577	, 6000578	, 6000579	, 6000580	, 6000581	, 6000582	, 6000583	, 6000584	, 6000585	, 6000586	, 6000587	, 6000588	, 6000589	, 6000590	, 6000591	, 6000592	, 6000593	, 6000594	, 6000595	, 6000596	, 6000597	, 6000598	, 6000599	, 6000600	, 6000601	, 6000602	, 6000603	, 6000604	, 6000605	, 6000606	, 6000607	, 6000608	, 6000609	, 6000610	, 6000611	, 6000612	, 6000613	, 6000614	, 6000615	, 6000616	, 6000617	, 6000618	, 6000619	, 6000620	, 6000621	, 6000622	, 6000623	, 6000624	, 6000625	, 6000626	, 6000627	, 6000628	, 6000629	, 6000630	, 6000631	, 6000632	, 6000633	, 6000634	, 6000635	, 6000636	, 6000637	, 6000638	, 6000639	, 6000640	, 6000641	, 6000642	, 6000643	, 6000644	, 6000645	, 6000646	, 6000647	, 6000648	, 6000649	, 6000650	, 6000651	, 6000652	, 6000653	, 6000654	, 6000655	, 6000656	, 6000657	, 6000658	, 6000659	, 6000660	, 6000661	, 6000662	, 6000663	, 6000664	, 6000665	, 6000666	, 6000667	, 6000668	, 6000669	, 6000670	, 6000671	, 6000672	, 6000673	, 6000674	, 6000675	, 6000676	, 6000677	, 6000678	, 6000679	, 6000680	, 6000681	, 6000682	, 6000683	, 6000684	, 6000685	, 6000686	, 6000687	, 6000688	, 6000689	, 6000690	, 6000691	, 6000692	, 6000693	, 6000694	, 6000695	, 6000696	, 6000697	, 6000698	, 6000699	, 6000700	, 6000701	, 6000702	, 6000703	, 6000704	, 6000705	, 6000706	, 6000707	, 6000708	, 6000709	, 6000710	, 6000711	, 6000712	, 6000713	, 6000714	, 6000715	, 6000716	, 6000717	, 6000718	, 6000719	, 6000720	, 6000721	, 6000722	, 6000723	, 6000724	, 6000725	, 6000726	, 6000727	, 6000728	, 6000729	, 6000730	, 6000731	, 6000732	, 6000733	, 6000734	, 6000735	, 6000736	, 6000737	, 6000738	, 6000739	, 6000740	, 6000741	, 6000742	, 6000743	, 6000744	, 6000745	, 6000746	, 6000747	, 6000748	, 6000749	, 6000750	, 6000751	, 6000752	, 6000753	, 6000754	, 6000755	, 6000756	, 6000757	, 6000758	, 6000759	, 6000760	, 6000761	, 6000762	, 6000763	, 6000764	, 6000765	, 6000766	, 6000767	, 6000768	, 6000769	, 6000770	, 6000771	, 6000772	, 6000773	, 6000774	, 6000775	, 6000776	, 6000777	, 6000778	, 6000779	, 6000780	, 6000781	, 6000782	, 6000783	, 6000784	, 6000785	, 6000786	, 6000787	, 6000788	, 6000789	, 6000790	, 6000791	, 6000792	, 6000793	, 6000794	, 6000795	, 6000796	, 6000797	, 6000798	, 6000799)";
			//test 
			//String	querya = "select an from Animal an where id in (6015345	, 6015346	, 6015347	, 6015348)";//	, 6015349	, 6015350	, 6015351	, 6015352	, 6015353	, 6015354	, 6015355	, 6015356	, 6015357	, 6015358	, 6015359	, 6015360	, 6015361	, 6015362	, 6015363	, 6015364	, 6015365	, 6015366	, 6015367	, 6015368	, 6015369	, 6015370	, 6015371	, 6015372	, 6015373	, 6015374	, 6015375	, 6015376	, 6015377	, 6015378	, 6015379	, 6015380	, 6015381	, 6015382	, 6015383	, 6015384	, 6015385	, 6015386	, 6015387	, 6015388	, 6015389	, 6015390	, 6015391	, 6015392	, 6015393	, 6015394	, 6015395	, 6015396	, 6015397	, 6015398	, 6015399	, 6015400	, 6015401	, 6015402	, 6015403	, 6015404	, 6015405	, 6015406	, 6015407	, 6015408	, 6015409	, 6015410	, 6015411	, 6015412	, 6015413	, 6015414	, 6015415	, 6015416	, 6015417	, 6015418	, 6015419	, 6015420	, 6015421	, 6015422	, 6015423	, 6015424	, 6015425	, 6015426	, 6015427	, 6015428	, 6015429	, 6015430	, 6015431	, 6015432	, 6015433	, 6015434	, 6015435	, 6015436	, 6015437	, 6015438	, 6015439	, 6015440	, 6015441	, 6015442	, 6015443	, 6015444	, 6015445	, 6015446	, 6015447	, 6015448	, 6015449	, 6015450	, 6015451	, 6015452	, 6015453	, 6015454	, 6015455	, 6015456	, 6015457	, 6015458	, 6015459	, 6015460	, 6015461	, 6015462	, 6015463	, 6015464	, 6015465	, 6015466	, 6015467	, 6015468	, 6015469	, 6015470	, 6015471	, 6015472	, 6015473	, 6015475	, 6015476	, 6015477	, 6015479	, 6015480	, 6015481	, 6015483	, 6015484	, 6015485	, 6015486	, 6015487	, 6015488	, 6015489	, 6015490	, 6015491	, 6015492	, 6015493	, 6015494	, 6015495	, 6015496	, 6015497	, 6015498	, 6015499	, 6015500	, 6015501	, 6015502	, 6015503	, 6015504	, 6015505	, 6015506	, 6015507	, 6015508	, 6015509	, 6015510	, 6015511	, 6015512	, 6015513	, 6015514	, 6015515	, 6015516	, 6015517	, 6015518	, 6015519	, 6015520	, 6015521	, 6015522	, 6015523	, 6015524	, 6015525	, 6015526	, 6015527	, 6015528	, 6015529	, 6015530	, 6015531	, 6015532	, 6015533	, 6015534	, 6015535	, 6015536	, 6015537	, 6015538	, 6015539	, 6015540	, 6015541	, 6015542	, 6015543	, 6015544	, 6015545	, 6015546	, 6015547	, 6015548	, 6015549	, 6015550	, 6015551	, 6015552	, 6015553	, 6015554	, 6015555	, 6015556	, 6015557	, 6015558	, 6015559	, 6015560	, 6015561	, 6020075	, 6020076	, 6020077	, 6020078	, 6020079	, 6020080	, 6020081	, 6020082	, 6020084	, 6020085	, 6020086	, 6020087	, 6020088	, 6020089	, 6020090	, 6020091	, 6020092	, 6020093	, 6020094	, 6020095	, 6020096	, 6020097	, 6020098	, 6020099	, 6020100	, 6020101	, 6020102	, 6020103	, 6020104	, 6020105	, 6020106	, 6020107	, 6020108	, 6020109	, 6020110	, 6020111	, 6020112	, 6020113	, 6020114)";
			
			//prod
			String	querya = "select an from Animal an where id in (6015345	, 6015346	, 6015347	, 6015348	, 6015349	, 6015350	, 6015351	, 6015352	, 6015353	, 6015354	, 6015355	, 6015356	, 6015357	, 6015358	, 6015359	, 6015360	, 6015361	, 6015362	, 6015363	, 6015364	, 6015365	, 6015366	, 6015367	, 6015368	, 6015369	, 6015370	, 6015371	, 6015372	, 6015373	, 6015374	, 6015375	, 6015376	, 6015377	, 6015378	, 6015379	, 6015380	, 6015381	, 6015382	, 6015383	, 6015384	, 6015385	, 6015386	, 6015387	, 6015388	, 6015389	, 6015390	, 6015391	, 6015392	, 6015393	, 6015394	, 6015395	, 6015396	, 6015397	, 6015398	, 6015399	, 6015400	, 6015401	, 6015402	, 6015403	, 6015404	, 6015405	, 6015406	, 6015407	, 6015408	, 6015409	, 6015410	, 6015411	, 6015412	, 6015413	, 6015414	, 6015415	, 6015416	, 6015417	, 6015418	, 6015419	, 6015420	, 6015421	, 6015422	, 6015423	, 6015424	, 6015425	, 6015426	, 6015427	, 6015428	, 6015429	, 6015430	, 6015431	, 6015432	, 6015433	, 6015434	, 6015435	, 6015436	, 6015437	, 6015438	, 6015439	, 6015440	, 6015441	, 6015442	, 6015443	, 6015444	, 6015445	, 6015446	, 6015447	, 6015448	, 6015449	, 6015450	, 6015451	, 6015452	, 6015453	, 6015454	, 6015455	, 6015456	, 6015457	, 6015458	, 6015459	, 6015460	, 6015461	, 6015462	, 6015463	, 6015464	, 6015465	, 6015466	, 6015467	, 6015468	, 6015469	, 6015470	, 6015471	, 6015472	, 6015473	, 6015475	, 6015476	, 6015477	, 6015479	, 6015480	, 6015481	, 6015483	, 6015484	, 6015485	, 6015486	, 6015487	, 6015488	, 6015489	, 6015490	, 6015491	, 6015492	, 6015493	, 6015494	, 6015495	, 6015496	, 6015497	, 6015498	, 6015499	, 6015500	, 6015501	, 6015502	, 6015503	, 6015504	, 6015505	, 6015506	, 6015507	, 6015508	, 6015509	, 6015510	, 6015511	, 6015512	, 6015513	, 6015514	, 6015515	, 6015516	, 6015517	, 6015518	, 6015519	, 6015520	, 6015521	, 6015522	, 6015523	, 6015524	, 6015525	, 6015526	, 6015527	, 6015528	, 6015529	, 6015530	, 6015531	, 6015532	, 6015533	, 6015534	, 6015535	, 6015536	, 6015537	, 6015538	, 6015539	, 6015540	, 6015541	, 6015542	, 6015543	, 6015544	, 6015545	, 6015546	, 6015547	, 6015548	, 6015549	, 6015550	, 6015551	, 6015552	, 6015553	, 6015554	, 6015555	, 6015556	, 6015557	, 6015558	, 6015559	, 6015560	, 6015561	, 6020075	, 6020076	, 6020077	, 6020078	, 6020079	, 6020080	, 6020081	, 6020082	, 6020084	, 6020085	, 6020086	, 6020087	, 6020088	, 6020089	, 6020090	, 6020091	, 6020092	, 6020093	, 6020094	, 6020095	, 6020096	, 6020097	, 6020098	, 6020099	, 6020100	, 6020101	, 6020102	, 6020103	, 6020104	, 6020105	, 6020106	, 6020107	, 6020108	, 6020109	, 6020110	, 6020111	, 6020112	, 6020113	, 6020114	, 6030947	, 6030948	, 6030949	, 6030950	, 6030951	, 6030952	, 6030953	, 6030954	, 6030955	, 6030956	, 6030957	, 6030958	, 6030959	, 6030960	, 6030961	, 6030962	, 6030963	, 6030964	, 6030965	, 6030966	, 6030967	, 6030968	, 6030969	, 6030970	, 6030971	, 6030972	, 6030973	, 6030974	, 6030975	, 6030976	, 6030977	, 6030978	, 6030979	, 6030980	, 6030981	, 6030982	, 6030983	, 6030984	, 6030985	, 6030986	, 6030987	, 6030988	, 6030989	, 6030990	, 6030991	, 6030992	, 6030993	, 6030994	, 6030995	, 6030996	, 6030997	, 6030998	, 6030999	, 6031000	, 6031001	, 6031002	, 6031003	, 6031004	, 6031005	, 6031006	, 6031007	, 6031008	, 6031009	, 6031010	, 6031011	, 6031012	, 6031013	, 6031014	, 6031015	, 6031016	, 6031017	, 6031018	, 6031019	, 6031020	, 6031021	, 6031022	, 6031023	, 6031024	, 6031025	, 6031026	, 6031027	, 6031028	, 6031029	, 6031030	, 6031031	, 6031032	, 6031033	, 6031034	, 6031035	, 6031036	, 6031037	, 6031038	, 6031039	, 6031040	, 6031041	, 6031042	, 6031043	, 6031044	, 6031045	, 6031046	, 6031047	, 6031048	, 6031049	, 6031050	, 6031051	, 6031052	, 6031053	, 6031054	, 6031055	, 6031056	, 6031057	, 6031058	, 6031059	, 6031060	, 6031061	, 6031062	, 6031063	, 6031064	, 6031065	, 6031066	, 6031067	, 6031068	, 6031069	, 6031070	, 6031071	, 6031072	, 6031073	, 6031074	, 6031075	, 6031076	, 6031077	, 6031078	, 6031079	, 6031080	, 6031081	, 6031082	, 6031083	, 6031084	, 6031085	, 6031086	, 6031087	, 6031088	, 6031089	, 6031090	, 6031091	, 6031092	, 6031093	, 6031094	, 6031095	, 6031096	, 6031097	, 6031098	, 6031099	, 6031100	, 6031101	, 6031102	, 6031103	, 6031104	, 6031105	, 6031106	, 6031107	, 6031108	, 6031109	, 6031110	, 6031111	, 6031112	, 6031113	, 6031114	, 6031115	, 6031116	, 6031117	, 6031118	, 6031119	, 6031120	, 6031121	, 6031122	, 6031123	, 6031124	, 6031125	, 6031126	, 6031127	, 6031128	, 6031129	, 6031130	, 6031131	, 6031132	, 6031133	, 6031134	, 6031135	, 6031136	, 6031137	, 6031138	, 6031139	, 6031140	, 6031141	, 6031142	, 6031143	, 6031144	, 6031145	, 6031146	, 6031147	, 6031148	, 6031149	, 6031150	, 6031151	, 6031152	, 6031153	, 6031154	, 6031155	, 6031156	, 6031157	, 6031158	, 6031159	, 6031160	, 6031161	, 6031162	, 6031163	, 6031164	, 6031165	, 6031166	, 6031167	, 6031168	, 6031169	, 6031170	, 6031171	, 6031172	, 6031173	, 6031174	, 6031175	, 6031176	, 6031177	, 6031178	, 6031179	, 6031180	, 6031181	, 6031182	, 6031183	, 6031184	, 6031185	, 6031186	, 6031187	, 6031188	, 6031189	, 6031190	, 6031191	, 6031192	, 6031193	, 6031194	, 6031195	, 6031196	, 6031197	, 6031198	, 6031199	, 6031200	, 6031201	, 6031202	, 6031203	, 6031204	, 6031205	, 6031206	, 6031207	, 6031208	, 6031209	, 6031210	, 6031211	, 6031212	, 6031213	, 6031214	, 6031215	, 6031216	, 6031217	, 6031218	, 6031219	, 6031220	, 6031221	, 6031222	, 6031223	, 6031224	, 6031225	, 6031226	, 6031227	, 6031228	, 6031229	, 6031230	, 6031231	, 6031232	, 6031233	, 6031234	, 6031235	, 6031237	, 6031238	, 6031239	, 6031240	, 6031241	, 6031242	, 6031243	, 6031244	, 6031245	, 6031246	, 6031247	, 6031248	, 6031249	, 6031250	, 6031251	, 6031252	, 6031253	, 6031254	, 6031255	, 6031256	, 6031257	, 6031258	, 6031259	, 6031260	, 6031261	, 6031262	, 6031263	, 6031264	, 6031265	, 6031266	, 6031267	, 6031268	, 6031269	, 6031270	, 6031271	, 6031272	, 6031273	, 6031274	, 6031275	, 6031276	, 6031277	, 6031278	, 6031279	, 6031280	, 6031281	, 6031282	, 6031283	, 6031284	, 6031285	, 6031286	, 6031287	, 6031288	, 6031289	, 6031290	, 6031291	, 6031292	, 6031293	, 6031294	, 6031295	, 6031296	, 6031297	, 6031298	, 6031299	, 6031300	, 6031301	, 6031302	, 6031303	, 6031304	, 6031305	, 6031306	, 6031307	, 6031308	, 6031309	, 6031310	, 6031311	, 6031312	, 6031313	, 6031314	, 6031315	, 6031316	, 6031317	, 6031318	, 6031319	, 6031320	, 6031321	, 6031322	, 6031323	, 6031324	, 6031325	, 6031326	, 6031327	, 6031328	, 6031329	, 6031330	, 6031331	, 6031332	, 6031333	, 6031334	, 6031335	, 6031336	, 6031337	, 6031338	, 6031339	, 6031340	, 6031341	, 6031342	, 6031343	, 6031345	, 6031346	, 6031347	, 6031348	, 6031360	, 6031361	, 6031362	, 6031363	, 6031364	, 6031365	, 6031366	, 6031367	, 6031368	, 6036278	, 6036279	, 6036280	, 6036282	, 6036283	, 6036284	, 6036286	, 6036287	, 6036288	, 6036289	, 6036290	, 6036291	, 6036292	, 6036293	, 6036294	, 6036296	, 6036297	, 6036298	, 6036299	, 6036300	, 6036301	, 6036302	, 6036303	, 6036304	, 6036305	, 6036306)";
			Query query = session.createQuery(querya);
			List results = query.list();
		    return results;
		}
	 public static void delete(Animal an){
		 HibernateFactory.getSession().delete(an);
	 }


	public static void save(Animal aniNuevo) {
		HibernateFactory.getSession().save(aniNuevo);
		
	}

	 
	 public static Hembra getHembra(Object o){
	 if(o instanceof HibernateProxy){
 		HibernateProxy proxy = (HibernateProxy) o;
 		return (Hembra) proxy.getHibernateLazyInitializer().getImplementation();
		}
	 return (Hembra)o;
	 }
	 public static Macho getMacho(Object o){
		 if(o instanceof HibernateProxy){
	 		HibernateProxy proxy = (HibernateProxy) o;
	 		return (Macho) proxy.getHibernateLazyInitializer().getImplementation();
			}
		 return (Macho)o;
		 }


	public static boolean esDeSicel1(Animal oldAnimal) {
		long regOri= Long.valueOf(oldAnimal.getRegOrigen().getNumero());
		
		if(regOri <= Animal.MAYOR_RC_SICEL1)
			return true;
		return false;
			
		
	}
	
	/**
	Metodo que dado un animal retorna la ultima lactancia, 
	* lactancia en curso, o evlactancia o  evlacmigrada
	**/
	public static Lactancia getUltimaLactancia(Hembra an){
		EvtLactancia lacCurso = (EvtLactancia) an.getLactanciaEnCurso();
		if(lacCurso!=null)//tiene lactancia en curso
			return lacCurso;
		List lactancias = an.getLactanciasCerradas();
    	Collections.sort(lactancias, new EventosPorFechaYTipo());
    	int desdes = lactancias.size();
    	if(desdes >0)
    		return (Lactancia) lactancias.get(desdes-1);
    	
    	return null;
	}
 
	
	public static Long getMaxIdHembraIniciaLactancia(Session session){
		SQLQuery query =null;
   		query = session.createSQLQuery("select max(animal) as MAX from animal_inicia_lactancia");
   		query.addScalar("MAX", Hibernate.LONG);
        return (Long)query.uniqueResult();
	}
	
	public static Long getMinIdHembraIniciaLactancia(Session session){
		SQLQuery query =null;
   		query = session.createSQLQuery("select min(animal) as MIN from animal_inicia_lactancia");
   		query.addScalar("MIN", Hibernate.LONG);
        return (Long)query.uniqueResult();
	}
public static Long getMaxId(){
		Session session = HibernateFactory.getSession();
		Criteria crit = session.createCriteria(Animal.class);
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.max("id"));
        crit.setProjection(projList);
        Long animal = (Long)crit.uniqueResult();
        return animal;
	}
	public static void validarEliminacion(Animal animal) throws ExcepcionIntegridad{
	if(animal!=null){
	 		 if(!animal.getAllEventos().isEmpty())//si es >0
	 			 throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_CRIA_EVENTOS_AOSCIADOS,new String[] {animal.getRegistroOrigen()});
	 		if(!animal.getCalificacions().isEmpty())
	 			 throw new ExcepcionIntegridad(MENSAJES.CRIA_TIENE_CALIFICACION,new String[] {animal.getRegistroOrigen()});
	 		List hijosDeTodasGen = new ArrayList();
			if(animal.esHembra()){
				hijosDeTodasGen.addAll(((Hembra)animal).getHijosGeneticos());
				hijosDeTodasGen.addAll(((Hembra)animal).getHijosParto());
							}
			else
				hijosDeTodasGen.addAll(((Macho)animal).getHijosGeneticos());
			if(!hijosDeTodasGen.isEmpty())
				throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_CRIA_EVENTOS_AOSCIADOS,new String[] {animal.getRegistroOrigen()});
				
			//traigo todos los animales-sinonimos q apuntan a este animal 
			Session session = HibernateFactory.getSession();    	
			Query query = session.createQuery("select an from AnimalSinonimo an where sinonimo = "+animal.getId());
			List results = query.list();
			Iterator it= results.iterator();
			while(it.hasNext())
			{
				AnimalSinonimo anSin = (AnimalSinonimo) it.next();
				anSin.setSinonimo(null);// le saco la asociacion al animal asi lo permite borrar
				HibernateFactory.getSession().update(anSin);
			}
	}
	}

}