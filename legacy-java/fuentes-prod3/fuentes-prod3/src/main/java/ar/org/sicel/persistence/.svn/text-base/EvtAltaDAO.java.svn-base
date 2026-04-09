package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.v1.lote.Senasa;
import ar.org.sicel.proc.v1.lote.TEvtAltaChoice;

/**
 * <p>
 * Factory class. Is able to find and create objects of type EvtAlta. Hibernate
 * inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.EvtAlta
 */
public abstract class EvtAltaDAO {
	// ---------------- create method --------------------

	/**
	 * Si el registro es null, se le asigna un nuevo RC, sino se usa el que se
	 * da
	 */
	public static EvtAlta create(TEvtAltaChoice choice,Establecimiento estab, Date fecha,
			Registro reg, boolean esHembra, String RP, String razaDeclarada,
			Map compRacial, String nombre, Date fechaNacimiento,
			List<ProcMsg> msg, ProcLote procLote,Senasa se) throws ExcepcionIntegridad {
		//Hoy en dia para contemplar si se acepta composicion racial en alta se meneja por esta variable
		if(choice.getCompoRacial()!=null && (!Configuracion.getValorReglaProceso(
				CONF.SE_ACEPTA_COMP_RACIAL_EN_ALTA, fecha))){
			throw new ExcepcionIntegridad(
					MENSAJES.NO_ACEPTA_COMP_RACIAL_ALTA, new String[0]);
		}
		/*if (compRacial != null)
			if (!Configuracion.getValorReglaProceso(
					CONF.SE_ACEPTA_COMP_RACIAL_EN_ALTA, fecha))
				throw new ExcepcionIntegridad(
						MENSAJES.NO_ACEPTA_COMP_RACIAL_ALTA, new String[0]);*/

		/*if (razaDeclarada != null)
			if (!Configuracion.getValorReglaProceso(
					CONF.SE_ACEPTA_RAZA_DECLARADA_EN_ALTA, fecha))
				throw new ExcepcionIntegridad(
						MENSAJES.NO_ACEPTA_RAZA_DECLARADA_ALTA,
						new String[] { razaDeclarada });*/

		// las reglas las controla en el Alta por eventos, cosa que si se dejen
		// hacer por otra interfaz.

		EvtAlta object = new EvtAlta(estab, fecha);

		if (razaDeclarada == null)
			throw new ExcepcionIntegridad(MENSAJES.NO_SETEA_RAZA,
					new String[] {});
		Raza rd = null;
		try {
			rd = RazaDAO.findByPrimaryKey(razaDeclarada);
		} catch (HibernateException e) {
			throw new ErrorFatal(
					"Error al buscar la razaDeclarada - EvtAltaDAO");
		}
		if (rd == null)
			throw new ExcepcionIntegridad(MENSAJES.RAZA_NO_EXISTE,
					new String[] { razaDeclarada });
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(rd);
		//cuando es composicion racial se hace caso omiso a la raza informada y entonces la raza
		//y entonces la raza declara es igual a la calculada
		String rpSe = null;
		Integer cod = null;
		if(se!=null){
			rpSe = se.getRpSenasa();
			cod = new Integer(se.getDigitoVerficador());
		}
		Animal nuevoAnimal = AnimalDAO.create(estab, estab.getPropietario(),
				reg, esHembra, RP, compR, compRacial, msg, fechaNacimiento,rpSe,cod);
		nuevoAnimal.setNombre(nombre);
		nuevoAnimal.setFechaNac(fechaNacimiento);
		if(choice.getCompoRacial()!=null){
			if(!nuevoAnimal.getComposicionRacial().getRazaCalculada().getEsDesconocido())
				nuevoAnimal.getComposicionRacial().setRazaDeclarada(nuevoAnimal.getComposicionRacial().getRazaCalculada());
			else
				nuevoAnimal.getComposicionRacial().setRazaDeclarada(rd.getEspecie().getCruza());
		}
			
		//		si es empadronar o padres que hace de empadronar no debe entrar aca pq la raza declarada ya fue cargada antes
//		y puede no ser posible que la raza declarada sea igual a la calculada
	/*	if(choice.getCompoRacial()!=null){
			if (!nuevoAnimal.getComposicionRacial().getRazaCalculada().equals(
					nuevoAnimal.getComposicionRacial().getRazaDeclarada())) {
				throw new ExcepcionIntegridad(
						MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
						new String[] {});
			}
		}*/
		//guardo la ficha para tirarla alguna vez
		//if(!nuevoAnimal.getRegOrigen().getTipoRegistro().equals("HBA")){
		if(!nuevoAnimal.getRegistroOrigen().contains("HBA")){
		FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
			fichaAnimal.setAnimal(nuevoAnimal);
			//fichaAnimal.setEcloId(nuevoAnimal.getEstablecimiento().getEclo().getId());
			//fichaAnimal.setLoteId(procLote.getNumLote());
			//nuevoAnimal.agregarFichaAnimal(fichaAnimal);
			procLote.agregarFichaAnimal(fichaAnimal);
		}
		object.setAnimal(nuevoAnimal);

		try {
			HibernateFactory.getSession().save(nuevoAnimal);
		} catch (HibernateException he) {
			throw new ErrorFatal(
					"No se pudo guardar el animal creado EvtAltaDAO", he);
		}

		

		return object;
	}

	/**
	 * 
	 * @param objEst
	 * @param fecha
	 * @param esHembra
	 * @param reg
	 *            si es Null se asigna un nuevo RC, sino se usa el que se da
	 * @param rp
	 * @param razaDeclarada
	 * @param madreGen
	 * @param padreGen
	 * @param nombre
	 * @param fechaNacimiento
	 * @param msgs
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static EvtAlta create(Establecimiento objEst, Date fecha,
			Registro reg, boolean esHembra, String rp, String razaDeclarada,
			Hembra madreGen, Macho padreGen, String nombre,
			Date fechaNacimiento, List<ProcMsg> msgs, ProcLote procLote,Senasa se,boolean fe)
			throws ExcepcionIntegridad {
		/*if (razaDeclarada != null)
			if (!Configuracion.getValorReglaProceso(
					CONF.SE_ACEPTA_RAZA_DECLARADA_EN_ALTA, fecha))
				throw new ExcepcionIntegridad(
						MENSAJES.NO_ACEPTA_RAZA_DECLARADA_ALTA,
						new String[] { razaDeclarada });*/

		EvtAlta object = new EvtAlta(objEst, fecha);
		if (razaDeclarada == null)
			throw new ExcepcionIntegridad(MENSAJES.NO_SETEA_RAZA,
					new String[] {});
		Raza rd = null;
		try {
			rd = RazaDAO.findByPrimaryKey(razaDeclarada);
		} catch (HibernateException e) {
			throw new ExcepcionIntegridad(MENSAJES.RAZA_NO_EXISTE,
					new String[] { razaDeclarada });
		}
		String rpSe = null;
		Integer cod = null;
		if(se!=null){
			rpSe = se.getRpSenasa();
			cod = new Integer(se.getDigitoVerficador());
		}
		Animal nuevoAnimal = AnimalDAO.create(objEst, objEst.getPropietario(),
				reg, esHembra, rp, rd, madreGen, padreGen, msgs,fechaNacimiento,rpSe,cod,fe);
		nuevoAnimal.setNombre(nombre);
		nuevoAnimal.setFechaNac(fechaNacimiento);
	
		//si no tienen la misma raza si hay que preguntar ...... punto 2.1 y 2.2
		if(padreGen!=null){
		if(!madreGen.getRaza().getId().equals(padreGen.getRaza().getId())){
		
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
		
		//if(!nuevoAnimal.getRegOrigen().getTipoRegistro().equals("HBA")){
		if(!nuevoAnimal.getRegistroOrigen().contains("HBA")){
			FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
			fichaAnimal.setAnimal(nuevoAnimal);
			procLote.agregarFichaAnimal(fichaAnimal);
	}
		
		
		/*FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
		fichaAnimal.setAnimal(nuevoAnimal);
		procLote.agregarFichaAnimal(fichaAnimal);*/
		object.setAnimal(nuevoAnimal);
		//fichaAnimal.setEcloId(nuevoAnimal.getEstablecimiento().getEclo().getId());
		//fichaAnimal.setLoteId(procLote.getNumLote());
		//nuevoAnimal.agregarFichaAnimal(fichaAnimal);
		// object.setEstablecimiento(objEst);
		// object.setFecha(fecha);
		try {
			HibernateFactory.getSession().save(nuevoAnimal);
		} catch (HibernateException he) {
			throw new ErrorFatal(
					"No se pudo guardar el animal creado EvtAltaDAO", he);
		}
		
		
		// object.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
		return object;
	}

	// ---------------- finder methods ----------------------

	/**
	 * 
	 * Finds EvtAlta object by its primary key. In Hibernate, this is just a
	 * call to get().
	 * 
	 */
	public static EvtAlta findByPrimaryKey(java.lang.Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		EvtAlta object = (EvtAlta) session.get(EvtAlta.class, id);

		return object;
	}

	/**
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public static EvtAlta findByAnimal(Long animalId) throws HibernateException {
		try {
		Session session = HibernateFactory.getSession();
		String queryText = "from EvtAlta where animal = :animalId";
		Query query = session.createQuery(queryText);
		query.setParameter("animalId", animalId, Hibernate.LONG);
		//List l = query.list();
		Object o = query.uniqueResult();
		if(o!=null)
			return (EvtAlta) (o);
		return null;
		
		} catch (HibernateException he) {
			throw new ErrorFatal(
					"Imposible calcular eventos. Error de capa de persistencia: "
							+ he.toString());
		}
	}
	
	public static void updateEventoAlta(EvtAlta evtAlta) {
		HibernateFactory.getSession().update(evtAlta);
	}

}
