package ar.org.sicel.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

/**
 * <p>
 * Factory class. Is able to find and create objects of type EvtReproduccion.
 * Hibernate inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.EvtReproduccion
 */
public abstract class EvtReproduccionDAO {
	// ---------------- create method --------------------
	static Logger log = Logger.getLogger(EvtReproduccionDAO.class);
	private static void procesarCria(Establecimiento est, EvtCria cria,
			Date fechaNacimiento, boolean usarRazaMadre, Hembra madreParto,
			Hembra madreGen, Macho padreGen, List<ProcMsg> msgs, boolean fechaCorrecta, EvtReproduccion evtReproduccion)
			throws ExcepcionIntegridad {
		evtReproduccion.setAsociaSicel1(false);	
		if (cria.inscribir() && org.apache.commons.lang.StringUtils.isNotEmpty(cria.getRP())) { 
			// regla posibilidad de inscribir(macho)
			boolean esMacho = cria.getSexo().equals("M"); //ya en este paso cria.getSexo() es F o M ya que se pregunto antes por inscribir
			if (esMacho) {
				boolean aceptamacho = Configuracion.getValorReglaProceso(
						CONF.INSCRIBIR_MACHO_ACEPTA, fechaNacimiento);
				if (!aceptamacho)
					throw new ExcepcionIntegridad(
							MENSAJES.INSCRIBIR_MACHO_ACEPTA, new String[0]);
			}
			Raza razaDeclarada = null;
			if (padreGen == null){ // si el padre genetico se desconoce
				if (usarRazaMadre) { // si usarRazaMadre esta en true en el
					// xml, se usa la raza de la
					// madreGenetica (que es la madre de
					// parto salvo en las TE)
					razaDeclarada = madreParto.getRaza();
				}
				else//si no tengo servicio y usarRazaMadre es false entonces es cruza
					razaDeclarada = madreParto.getRaza().getEspecie().getCruza();
			}
			else{//tengo servicio
				if(madreGen.getRaza().getId().equals(padreGen.getRaza().getId())){
					razaDeclarada = madreGen.getRaza();
				}
			}
			if (madreParto.getPropietario() != est.getPropietario()) {
				ProcMsg msg = ProcMsgDAO
						.create(
								MENSAJES.CRIA_PROP,
								ProcMsg.WARNING,
								new String[] {
										est.getPropietario().getId().toString(),
										madreParto.getPropietario().getId()
												.toString() });
				msgs.add(msg);
			}
			
			// La cría existe en la base de SICEL V3 porque se migró desde SICEL V1
			// Este chequeo se utiliza en la carga de eventos inciales
//			IMPORTANTE!!!!tambien se debe chequear que el oldAnimal no este en ningun evtCria
			Animal oldAnimal = AnimalDAO.findByRPFNacEstab(cria.getRP(), evtReproduccion.getFecha(), est);
			
				
			//AnimalDAO.findByRPFNacMadrePadre(cria.getRP(), evtReproduccion.getFecha(),madreGen,padreGen);
			if (oldAnimal != null) {
				//if(oldAnimal.getEvCria()!=null||EvtAltaDAO.findByAnimal(oldAnimal.getId())!=null){
				if(oldAnimal.getEvCria()!=null){
					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(evtReproduccion.getFecha());
					String evento = (oldAnimal.getEvCria()!=null)?"reproducción":"alta";
					throw new ExcepcionIntegridad(MENSAJES.CRIA_EXISTENTE,
							new String[] { "RP="+cria.getRP(),"nacimiento="+fecha,"Tambo="+est.getId().toString(),"evento="+evento,"RP="+oldAnimal.getRP()
							,"nacimiento="+fecha,"registro="+oldAnimal.getRegistroOrigen(),"Tambo="+oldAnimal.getEstablecimiento().getId().toString()});
					//System.out.println("EL ANIMAL YA FUE DADO DE ALTA POR UN EVENTO REPRODUCCION O EVENTO ALTA");
					//La cria ya fue dada de alta en sicel3, El animal informado con rp:, fechaNac: y tambo:, ya se encuentra dada de alta bajo un evento: con rp: fechaNac: registro: y tambo:
					//return error, el animal ya fue dado de alta por un evento reproduccion o un evento Alta
				}
				//si el animal es HBA entonces error
				if(oldAnimal.getRegOrigen().getTipoRegistro().getId().equals("HBA")){
					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(evtReproduccion.getFecha());
					throw new ExcepcionIntegridad(MENSAJES.NO_BAJA_HBA,
							new String[] { "RP="+oldAnimal.getRP(),"nacimiento="+fecha,"Tambo="+est.getId().toString(),oldAnimal.getRegistroOrigen()});
				}	
				if(AnimalDAO.esDeSicel1(oldAnimal))
					evtReproduccion.setAsociaSicel1(true);	
				else{
				//actualizar los padres y la categoria
					evtReproduccion.setAsociaSicel1(false);	
					Hembra madreOLD = (oldAnimal.getMadreGenetica()!=null)?oldAnimal.getMadreGenetica():oldAnimal.getMadreParto();
					boolean actualizarMadre = (madreOLD==null)?true:((madreOLD.equals(madreGen))?true:false);
					boolean actualizarPadre = (oldAnimal.getPadre()==null)?true:((oldAnimal.getPadre().equals(padreGen))?true:false);
					if(actualizarMadre && actualizarPadre){		
						oldAnimal.setMadreGenetica(oldAnimal.getMadreGenetica()==null?oldAnimal.getMadreParto():oldAnimal.getMadreGenetica());
						AnimalDAO.actualizar(oldAnimal, est, oldAnimal.getPropietario(), oldAnimal.getRegOrigen(), oldAnimal.esHembra()
								, oldAnimal.getRP(), oldAnimal.getRaza(), madreGen, padreGen, msgs, evtReproduccion.getFecha(),usarRazaMadre);
						oldAnimal.setearCategoria(true);
						oldAnimal.setMadreParto(oldAnimal.getMadreParto()==null?oldAnimal.getMadreGenetica():oldAnimal.getMadreParto());
					}
					else{
						String rpP = (padreGen!=null)?padreGen.getRP():"No Informado";
						String rpM = (madreGen!=null)?madreGen.getRP():"No Informada";
						String fecha = new SimpleDateFormat("dd/MM/yyyy").format(evtReproduccion.getFecha());
						String fechaO =new SimpleDateFormat("dd/MM/yyyy").format(oldAnimal.getFechaNac());
						String rpPB = (oldAnimal.getPadre()!=null)?oldAnimal.getPadre().getRP():"No Informado";
						String rpMB = (oldAnimal.getMadreGenetica()!=null)?oldAnimal.getMadreGenetica().getRP():"No Informada";
						throw new ExcepcionIntegridad(MENSAJES.CRIA_EXISTENTE_NO_COINCIDE,
								new String[] {"RP="+cria.getRP(),"Tambo="+est.getId().toString(),"nacimiento="+fecha,
								"Madre Genetica ="+rpM,"Padre Genetico="+rpP,"RP="+oldAnimal.getRP(),"Id Estab="+oldAnimal.getEstablecimiento().getId(),
								"nacimiento ="+fechaO,"Madre Genetica ="+rpMB,"Padre Genetico ="+rpPB});
						
						//System.out.println("EL ANIMAL YA FUE DADO DE ALTA EN SICEL Y NO COINCIDEN LOS PADRES");
					}
				//si el animal es de sicel1
				}
				cria.setCria(oldAnimal);
			}
			else { // si no hay animal entonces NO seguimos como siempre
				log.warn("Comienza chequeo si las madres tienen crias "+ new Date());
				List hijosDeTodasParto = new ArrayList();
				if(madreParto!=null){
				hijosDeTodasParto.addAll(madreParto.getHijosGeneticos());
				hijosDeTodasParto.addAll(madreParto.getHijosParto());
				}
				
				String fecha = new SimpleDateFormat("dd/MM/yyyy").format(evtReproduccion.getFecha());
				if(!hijosDeTodasParto.isEmpty()){
					Iterator it =hijosDeTodasParto.iterator();
					while(it.hasNext()){
						Animal cria1 = (Animal) it.next();
						if(cria1.getRP().equalsIgnoreCase(cria.getRP())&& cria1.getFechaNac().equals(evtReproduccion.getFecha())){
							
							throw new ExcepcionIntegridad(MENSAJES.CRIA_EXISTENTE_ESTAB,
									new String[] {madreParto.getRP(), cria.getRP(),fecha,est.getEstancia().getId().toString(),cria1.getRegistroOrigen()});
							
							//System.out.println("LA MADRE YA TUVO ESTA CRIA Y SE ENCUENTRA EN OTRO ESTABLECIMIENTO");
							//trwoooooooooerror,la madre ya tuvo una cria con ese rp y FN en el el tambo XXXX y RC XXXX
						}
					}
				}
				List hijosDeTodasGen = new ArrayList();
				if(madreGen!=null){
				hijosDeTodasGen.addAll(madreGen.getHijosGeneticos());
				hijosDeTodasGen.addAll(madreGen.getHijosParto());
				}
				if(!hijosDeTodasGen.isEmpty()){
					Iterator it =hijosDeTodasGen.iterator();
					while(it.hasNext()){
						Animal cria1 = (Animal) it.next();
						if(cria1.getRP().equalsIgnoreCase(cria.getRP())&& cria1.getFechaNac().equals(evtReproduccion.getFecha())){
							throw new ExcepcionIntegridad(MENSAJES.CRIA_EXISTENTE_ESTAB,
									new String[] {madreGen.getRP(), cria.getRP(),fecha,est.getEstancia().getId().toString(),cria1.getRegistroOrigen()});
							
							//System.out.println("LA MADRE YA TUVO ESTA CRIA Y SE ENCUENTRA EN OTRO ESTABLECIMIENTO");
							//trwoooooooooerror,la madre ya tuvo una cria con ese rp y FN en el el tambo XXXX y RC XXXX
						}
					}
					
				}
				log.warn("Finaliza chequeo si las madres tienen crias "+ new Date());
				evtReproduccion.setAsociaSicel1(false);	
				boolean esHembra = cria.getSexo().equals("F");
				Animal an = AnimalDAO.create(est, est.getPropietario(), null, esHembra, cria.getRP(), razaDeclarada, madreGen,
						padreGen, msgs, fechaNacimiento,cria.getRpSenasa(),cria.getCodigo(),fechaCorrecta);
				an.setNombre(cria.getNombre());
				an.setRP(cria.getRP());
				an.setFechaNac(fechaNacimiento);
				madreParto.addHijoParto(an); // setea la madre de parto
				cria.setCria(an);
				//MODIF Seteo la categoria al momento de crear la cria y no la calculo todo el tiempo			
				an.setearCategoria(fechaCorrecta);
				//MODIF
				Registro regAsignado = an.getRegOrigen();
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {an.getRP(), regAsignado.getNumero(), regAsignado.getTipoRegistro().getId(), an.getCategoria()});
				msgs.add(msg);						
			}
		} else 
			// no inscribir
			// regla obliga a inscribir hembras
			if (cria.getEstadoPerinatalEsVivo() && 
					cria.getSexo().equals("F") && 
					org.apache.commons.lang.StringUtils.isNotEmpty(cria.getRP())){//&& cria.getRP() != null) {
				boolean obligahembra = Configuracion.getValorReglaProceso(
						CONF.INSCRIBIR_HEMBRA_OBLIGA, fechaNacimiento);
				//si la configuracion obliga a la inscripcion de hembras 
				//y no se quiere informar un parto de un animal de pedigree
				//se rechaza ya que es obligatorio
				//para que sea el caso de q sea un parto de un animal de pedigree 
				//debe tener padre y madre con hba en el regori--->Esta es la unica manera
				//de que se permita no inscribir una hembra
				if (obligahembra)
					//if(madreGen==null || padreGen==null || !madreGen.getRegOrigen().getTipoRegistro().getId().equals("HBA") || !padreGen.getRegOrigen().getTipoRegistro().getId().equals("HBA"))
					if(madreGen==null || padreGen==null || !madreGen.getRegistroOrigen().contains("HBA") || !padreGen.getRegistroOrigen().contains("HBA"))
					
						throw new ExcepcionIntegridad(MENSAJES.INSCRIBIR_HEMBRA_OBLIGA,
							new String[0]);
					else
						evtReproduccion.setAsociaSicel1(true);//un animal de pedigree debe tener padre y madre con hba en el regori entonces la cria va a ser hba por lo tanto no se debe borrar el animal
			}
			
	}
	
	private static boolean animalesIguales(Animal a1, Animal a2) {
		if (a1 == null && a2 ==null) return true;
		if (a1 != null && a2 != null && a1.equals(a2)) return true;
		return false;
	}

	/**
	 * Creates a(n) EvtReproduccion object.
	 * 
	 * @param idEvt
	 * 
	 * @param fecha
	 * @param crias
	 * @return EvtReproduccion the created object
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public static EvtReproduccion create(Establecimiento est,
			Hembra madreParto, java.util.Date fechaEvento,
			java.util.Date fechaServicio, Boolean usarRazaMadre,
			Boolean esAbortoLargo, EvtCria[] crias, 
			List<ProcMsg> msgs, ProcLote procLote, boolean fechaCorrecta,Integer nroLac,Date fechaEnvioLote)
			throws ExcepcionIntegridad {

		// TODO un ev reprod "elimina" todos los servicios anteriores a él id
		// est: cdo se busca servicio hay que parar si para atras hay un reprod

		// lo tendremos que controlar tambien o alcanza con el periodo minimo de
		// descanzo
		// y el intervalo entre partos.
		// el intervalo entre partos, es entre Partos con crias vivas, no?
		EvtReproduccion object = new EvtReproduccion(est, fechaEvento,
				madreParto, msgs);
		
		if(esAbortoLargo==null)
			object.setAbortoLargo(false);
		else
			object.setAbortoLargo(esAbortoLargo);
		object.setUsarRazaMadre(usarRazaMadre);
		object.setNroLactancia(nroLac);
		// object.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
		List listaCrias = new LinkedList();

		for (int i = 0; i < crias.length; i++) {
			listaCrias.add(crias[i]);
		}
		object.setEvtCrias(listaCrias);
		
		Integer edadFertil = Integer.parseInt(madreParto.getRaza().getParametro(
				Raza.EDAD_MAX_FERTIL));
		if (madreParto.getEdadEnDiasAl(fechaEvento) > edadFertil)
			throw new ExcepcionIntegridad(MENSAJES.EDAD_MAX_FERTIL,
					new String[] { edadFertil.toString() });

		// si este es un parto (con crias vivas), entonces debe haber una
		// distancia
		// minima entre este y un evento reproduccion (aborto/parto) anterior
		// MIRAR Esta bien preguntar aca si hay crias vivas o no o habria que
		// controlar igual la distancia entre partos ?

		// MODIF Esta es una modificacion pedida por ACHA pto. 2.3.1
		// if (object.criasVivas()) {
		int diasMinima = 0;
		if (crias.length > 0){ 
			//se toma la gestación mínima más la cantidad de días que debe haber entre partos
			diasMinima = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.GESTACION_MINIMA));
			int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.EDAD_MINIMA_SERVICIO_NATURAL));
			Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
			Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
			if (fechaMinParto.after(fechaEvento))
				throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
		}
		else{//si no hay crias 
			if(esAbortoLargo == null)
				throw new ExcepcionIntegridad(MENSAJES.EDAD_MAX_FERTIL,	new String[] { edadFertil.toString() });
			if(esAbortoLargo){
			//se toma la cantidad de días mínima para considerar aborto largo más la cantidad de días que debe haber entre reproducciones
			diasMinima = Integer.parseInt(madreParto.getRaza().getParametro(Raza.DIAS_MIN_ABL));
			int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.EDAD_MINIMA_SERVICIO_NATURAL));
			Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
			Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
			if (fechaMinParto.after(fechaEvento))
				throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
			}
			else{//es aborto corto
				diasMinima = 1;//dias minimo para aborto corto
				int edadServMin = Integer.parseInt(madreParto.getRaza().getParametro(
						Raza.EDAD_MINIMA_SERVICIO_NATURAL));
				Date fechaMinimaServicio = DateUtils.mas(madreParto.getFechaNac(), edadServMin);
				Date fechaMinParto = DateUtils.mas(fechaMinimaServicio, diasMinima);
				if (fechaMinParto.after(fechaEvento))
					throw new ExcepcionIntegridad(MENSAJES.NO_RESPETA_PERIODO_SERVICIO_PARTO,new String[] {madreParto.getRegistroOrigen()});
			}
		}

		if (object.getAbortoLargo() || (crias.length > 0)) {
			object.setIniciaLactancia(true);
			int diasEntreParto = diasMinima + Integer.parseInt(madreParto.getRaza().getParametro(
					Raza.PERIDODO_DESCANZO_MINIMO));
			Date minParto = DateUtils.menos(fechaEvento, diasEntreParto);
			List partosConflictos = madreParto.getEventosEntre(minParto,
				fechaEvento, Evento.EVT_TIPO_REP);
			if (!partosConflictos.isEmpty()) {
				//EvtReproduccion posibleConf = (EvtReproduccion) partosConflictos.get(0);
				EvtAnimal posibleConf = (EvtAnimal) partosConflictos.get(0);
				throw new ExcepcionIntegridad(MENSAJES.PERIODO_PARTOS_INVALIDO,
					new String[] { StringUtils.formatDate(posibleConf
							.getFecha()) });
			}
		}else{
            int periodioDes = Integer.parseInt(madreParto.getRaza().getParametro(Raza.PERIDODO_DESCANZO_MINIMO));
            Date minParto = DateUtils.menos(fechaEvento, periodioDes);
            Date maxParto = DateUtils.mas(fechaEvento, periodioDes);
            List partosConflictosAbortosCortos= madreParto.getEventosEntre(minParto,
                    maxParto, Evento.EVT_TIPO_REP);
                if (!partosConflictosAbortosCortos.isEmpty()) {
                    //EvtReproduccion posibleConf = (EvtReproduccion) partosConflictos.get(0);
                    EvtAnimal posibleConf = (EvtAnimal) partosConflictosAbortosCortos.get(0);
                    throw new ExcepcionIntegridad(MENSAJES.PERIODO_PARTOS_INVALIDO,
                        new String[] { StringUtils.formatDate(posibleConf
                                .getFecha()) });
                }
        }	


		// hay que llamar a esto ANTES de procesar las crias, porque se necesita
		// saber
		// el servicio, para poder calcular madre y padre geneticos
		object.setFechaServicio(fechaServicio);
		madreParto.completarConServicioPara(object, msgs,fechaEnvioLote);
		// aca el evento reproduccion (object) tiene el servicio

		EvtServicio serv = object.getEvtServicio();
		Hembra madreGen = madreParto;
		Macho padreGen = null;
		if (serv == null) {
			if (!Configuracion.getValorReglaProceso(
					CONF.ACEPTA_PARTO_SIN_SERVICIO, fechaEvento))
				throw new ExcepcionIntegridad(MENSAJES.PARTO_SIN_SERVICIO,
						new String[] {
								madreParto.getRaza().getParametro(
										Raza.PERIODO_GESTACION_PROMEDIO),

								madreParto.getRaza().getParametro(
										Raza.CICLO_CELO_MINIMO) });
		} else {
			// si hay servicio
			padreGen = serv.getPadreGenetico();
			//if((serv.getMadreGenetica()!=null))
			if((serv instanceof EvtTransEmb) && (serv.getMadreGenetica()!=null))
				madreGen = serv.getMadreGenetica();
			//
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_UTILIZADO,
					ProcMsg.INFO, new String[] { StringUtils.formatDate(serv
							.getFecha()) });
			msgs.add(msg);
			serv.setEvtReproduccion(object);
		}
		if (object.getFechaServicio()!=null&&
			(DateUtils.diasEntre(object.getFechaServicio(), object.getFecha()) >
			Integer.parseInt(object.getAnimal().getRaza().getParametro(Raza.GESTACION_MINIMA)))&&
			(object.getAbortoLargo() || object.getEvtCrias() == null || object
						.getEvtCrias().isEmpty())) {
				throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_ABORTO_LARGO_LUEGO_DE_X_DIAS,
						new String[] { ""+Integer.parseInt(object.getAnimal().getRaza()
								.getParametro(Raza.GESTACION_MINIMA)) });
									
		}
		if (padreGen == null) {//si no tengo servicio seria
			object.setFechaServicio(null);
			if (usarRazaMadre)
				if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_USAR_RAZA_MADRE_EN_PARTO, fechaEvento))
					throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_USAR_RAZA_MADRE_PARTO,new String[0]);
		} /*else {comentado por reunion 12/4
			if (usarRazaMadre) {
				ProcMsg msg = ProcMsgDAO.create(
						MENSAJES.RAZA_MADRE_CON_SERVICIO_VALIDO,
						ProcMsg.WARNING, new String[] {
								padreGen.getRegistroID(),
								serv.getFecha().toString() });
				msgs.add(msg);
			}
		}*/

		// se procesan todas las crias. Notar que los objetos animales se crean
		// pero no se guardan,
		// por lo tanto si alguna cria falla, las anteriores tampoco apareceran
		for (int i = 0; i < crias.length; i++)
			procesarCria(est, crias[i], fechaEvento, usarRazaMadre, madreParto,
					madreGen, padreGen, msgs, fechaCorrecta, object);
			// ahora necesito mandar la reproduccion

		// object.setAnimal(madreParto);
		madreParto.addEventoAnimal(object, msgs);
		//madreParto.setEstadoRetroactivo(object, msgs);
		madreParto.setEstadoRetroactivo(object.getFecha(), msgs,object.getNombreTipo());
		Session session = HibernateFactory.getSession();
		try {			
			for (int i = 0; i < crias.length; i++) {// guardamos en la base
				// todos los animales creados (aca ya sabemos que todos se pudieron procesar)
				EvtCria cria = crias[i];
				if (cria.inscribir() && cria.getCria()!= null && cria.getCria().getId()==null){
					//if(!cria.getCria().getRegOrigen().getTipoRegistro().equals("HBA")){
						FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
						fichaAnimal.setAnimal(cria.getCria());
						procLote.agregarFichaAnimal(fichaAnimal);
					//}
					session.save(cria.getCria());
					//Agregado para poner la cria muerta como baja
					if(!cria.getEstadoPerinatalEsVivo()){
						EvtBaja evtBaja = EvtBajaDAO.create(est,cria.getCria(),"MUER","REPR",null,fechaEvento,msgs);
				        
						try {
				  			session.save(evtBaja);
				  		} catch (HibernateException e) {
				  			throw new ErrorFatal("No se pudo guardar el evento",e);
				  		}
					}
				}	
			}
		} catch (HibernateException he) {
			throw new ErrorFatal(
					"Error al querer guardar los nuevos animales nacidos - EvtReproduccionDAO");
		}
		return object;
	}

	// ---------------- finder methods ----------------------

	/**
	 * 
	 * Finds EvtReproduccion object by its primary key. In Hibernate, this is
	 * just a call to get().
	 * 
	 */
	public static EvtReproduccion findByPrimaryKey(java.lang.Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		EvtReproduccion object = (EvtReproduccion) session.get(
				EvtReproduccion.class, id);

		return object;
	}
	/**
	 * metodo que retorna el evento reproduccion en donde nacio la cria informada como parametro
	 * @param cria
	 * @return
	 */
	public static EvtReproduccion findNacimientoCria(Animal cria) {
		Session session = HibernateFactory.getSession();
		Query query = session.createQuery("select repr from EvtReproduccion as repr join repr.evtCrias as criae  where criae.cria = :cria ");
		query.setParameter("cria", cria);
		List result = query.list();
		if (result.isEmpty())
			return null;
		else
			return (EvtReproduccion) result.get(0);
	}
	public static void eliminarCria(Animal cria) {
		EvtReproduccion rep = findNacimientoCria(cria);
    	if(rep!=null){
    		if(!rep.getEvtCrias().isEmpty()){
    			Iterator it = rep.getEvtCrias().iterator();
    			while(it.hasNext()){
    				EvtCria ec = (EvtCria)it.next();
    				if(ec.getCria()!=null && ec.getCria().equals(cria)){
    					ec.setCria(null);
    					updateEventoReproduccion(rep);
    					break;
    				}
    			}
    		}
    			
		}
    	
		
	}
	

	public static void updateEventoReproduccion(EvtReproduccion evtReproduccion) {
		HibernateFactory.getSession().update(evtReproduccion);
	}
}
