package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

/**
 * 
 * @hibernate.subclass discriminator-value="true" lazy="true"
 */
public class Hembra extends ar.org.sicel.persistence.Animal {



	public static int EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA = 42 * 30; // 42
	public static int EDAD_MAXIMA_MADRE_CON_BAJA_PARA_CATEGORIA = 50 * 30; // 42

	// meses

	// --------------- attributes ---------------------
	private java.util.Set hijosGeneticos = new HashSet();

	private java.util.Set hijosParto;

	private java.util.Set evtTransEmbs;
	/**
	 * numero utilizado para el reporte de lactancia en curso o cerrada, cuando se pide una reporte de una lactancia en particular
	 * se invoca al metodo getLactanciaNumero() desde el reporte teniendo en cuenta dicho numero
	 */
	private int numeroLactanciaReporte;

	

	public int getNumeroLactanciaReporte() {
		return numeroLactanciaReporte;
	}

	public void setNumeroLactanciaReporte(int numeroLactanciaReporte) {
		this.numeroLactanciaReporte = numeroLactanciaReporte;
	}

	public Hembra() {
	}

	// ------------- relations ------------------

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="madreGen"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.Animal"
	 * 
	 */
	public java.util.Set getHijosGeneticos() {
		return hijosGeneticos;
	}

	protected void setHijosGeneticos(java.util.Set hijosGeneticos) {
		this.hijosGeneticos = hijosGeneticos;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="madreParto"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.Animal"
	 * 
	 */
	public java.util.Set getHijosParto() {
		return this.hijosParto;
	}

	protected void setHijosParto(java.util.Set hijosParto) {
		this.hijosParto = hijosParto;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="madreGen"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.EvtTransEmb"
	 * 
	 */
	public java.util.Set getEvtTransEmbs() {
		return this.evtTransEmbs;
	}

	protected void setEvtTransEmbs(java.util.Set evtTransEmbs) {
		this.evtTransEmbs = evtTransEmbs;
	}

	// ---------------- business methods ----------------------

	public boolean esHembra() {
		return true;
	}

	/**
	 * Agrega una transferencia embrionaria, realizada con un embrion que tiene
	 * como madre genetica a esta hembra.
	 * 
	 * @param serv
	 * @param msgs
	 */
	@SuppressWarnings("unchecked")
	public void addEvtTransfEmbrinaria(EvtTransEmb serv, List msgs)
			throws ExcepcionIntegridad {
		if (this.getFechaNac() != null) { // si no tenemos la fecha de
			// nacimiento no controlamos nada?
			int edadMinima = this
					.getParametroRazaAsInteger(Raza.EDAD_MINIMA_PRODUCIR_EMBRIONES);
			int edadEnEsaFecha = this.getEdadEnDiasAl(serv.getFecha());
			if (edadEnEsaFecha < edadMinima) {
				if (Configuracion.getValorReglaProceso(
						CONF.REGLA_EDAD_MINIMA_PRODUCIR_EMBRIONES, serv
								.getFecha())) {
					throw new ExcepcionIntegridad(
							MENSAJES.EDAD_MINIMA_INVALIDA_PRODUCCION_EMBRIONES,
							new String[] { String.valueOf(edadMinima),
									String.valueOf(edadEnEsaFecha) });
				} else {
					ProcMsg msg = ProcMsgDAO.create(
							MENSAJES.EDAD_MINIMA_INVALIDA_PRODUCCION_EMBRIONES,
							ProcMsg.WARNING, new String[] {
									String.valueOf(edadMinima),
									String.valueOf(edadEnEsaFecha) });
					msgs.add(msg);
				}
			}
		}
		this.evtTransEmbs.add(serv);
		serv.setMadreGenetica(this);
	}

	/**
	 * Retorna el servicio que por fecha seria el mas apropiado para el
	 * <strong>Parto</strong> dado. Este servicio puede no ser el utilizado
	 * luego, ya que el usuario puede tambien suministrar uno cuando informa el
	 * evento reproduccion.
	 * 
	 * Aclaracion: Gestacion maxima y minima la puedo sacar por attr. variable
	 * en lugar de calcularla, pero como se usa en varias partes del algoritmo,
	 * una ves que la tengo la paso por parametro para liberar un poco a
	 * hibernate.
	 * 
	 * @param repr
	 * @param gMax
	 * @param gMin
	 * @return
	 */
	private EvtServicio getServicioMasApropiadoParaParto(EvtReproduccion repr,
			int gMax, int gMin) {
		EvtServicio masApropiado = null;
		Date diaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
		Date diaMasCercano = DateUtils.menos(repr.getFecha(), gMin);
		// Date diaPromedio = DateUtils.menos(repr.getFecha(), (gMax + gMin) /
		// 2);
		Iterator eventos = this.getServiciosEfectivosEntre(diaMasLejano,diaMasCercano).iterator();
		while (eventos.hasNext()) {
			EvtAnimal eAnimal = (EvtAnimal) eventos.next();
			if (eAnimal.getNombreTipo().equals(EvtAnimal.EVT_TIPO_SVC))
				// MODIF Pto 2.3.5 Se toma el servicio mas cercano al final del
				// periodo valido de gestacion, no al promedio
				// if ((masApropiado == null)
				// || (Math.abs(DateUtils.diasEntre(diaPromedio,
				// masApropiado.getFecha())) > Math.abs(DateUtils
				// .diasEntre(diaPromedio, eAnimal.getFecha()))))
				if ((masApropiado == null)|| (Math.abs(DateUtils.diasEntre(diaMasCercano,masApropiado.getFecha())) > Math.abs(DateUtils.diasEntre(diaMasCercano, eAnimal.getFecha()))))
					masApropiado = (EvtServicio) eAnimal;
		}
		return masApropiado;
	}
	/**
	 * En este metodo se chequea que si el serivcio es a campo no puede haber otro servicio
	 * a campo que se solape, entre fecha servicio - 25 hasta fecha fin corral + 25
	 * @param servicio
	 * @throws ExcepcionIntegridad
	 */
	private void checkServicioACampo(EvtServicio servicio) throws ExcepcionIntegridad{
		
		if(servicio instanceof EvtNuevoInd){
			EvtNuevoInd evt = (EvtNuevoInd)servicio;
			Date finCorral = evt.getFinCorral();
			if(finCorral!=null){//es un servicio a campo
				//chequeo que no haya servicio a campo que entre en el rango fecha servicio - 25 hasta fecha fin corral + 25
				//y se chequea que no haya ningun servicio a campo con fecha fin de corral dentro del
				//rango fecha servicio - 25 hasta fecha fin corral + 25
				List servicios = this.getEventosEntre(DateUtils.menos(evt.getFecha(),25),DateUtils.mas(evt.getFinCorral(),25),servicio.getNombreTipo());
				if(!servicios.isEmpty()){
					servicios.remove(evt);
					Iterator it = servicios.iterator();
					while(it.hasNext()){
						EvtServicio ev = (EvtServicio)it.next();
						if(ev instanceof EvtNuevoInd){
							EvtNuevoInd ev2 = (EvtNuevoInd)ev;
							Date finCorral2 = ev2.getFinCorral();
							if(finCorral2!=null)//es un servicio a campo
								throw new ExcepcionIntegridad(MENSAJES.SERVICIO_CAMPO_SOLAPADO, new String[]{StringUtils.formatDate(servicio.getFecha()),StringUtils.formatDate(finCorral),StringUtils.formatDate(ev2.getFecha()),StringUtils.formatDate(finCorral2)});
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param repr
	 * @param msgs
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	@SuppressWarnings( { "unchecked", "unchecked", "unchecked" })
	public void completarConServicioPara(EvtReproduccion repr, List msgs,Date fechaEntradaServicio)throws ExcepcionIntegridad {
		EvtServicio servicioResultado = null;
		int diasGestacion = -1;

		boolean criasVivas = repr.criasVivas();
		
		//TODO el parametro paso a la Raza (29/03/2007)
		int gMin = Integer.parseInt(this.getRaza().getParametro(Raza.GESTACION_MINIMA));
		int gMax = Integer.parseInt(this.getRaza().getParametro(Raza.GESTACION_MAXIMA));
		boolean aceptaPartoSinServicio = true;
		if (repr.getFechaServicio() != null) { // el usuario especifica una
			// fecha de servicio
			if (!Configuracion.getValorReglaProceso(CONF.ACEPTA_FECHA_SERVICIO_EN_PARTO, repr.getFecha()))
				throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_FECHA_SERVICIO_EN_PARTO,new String[0]);
			servicioResultado = (EvtServicio) getEventoEnFecha(repr.getFechaServicio(), EvtServicio.EVT_TIPO_SVC);
			if (servicioResultado == null){
				//throw new ExcepcionIntegridad(MENSAJES.SERVICIO_ESPECIFICADO_NO_EXISTE, new String[]{StringUtils.formatDate(repr.getFechaServicio())});
				ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_ESPECIFICADO_PROBLEMAS_IGUAL_ACEPTA,ProcMsg.WARNING_SPECIAL,
						new String[] { StringUtils.formatDate(repr.getFechaServicio())+" no existe"});
				msgs.add(msg);
				aceptaPartoSinServicio = false;
			}
			else{
				if(repr.getProcEvtAnimal()!=null){//si es una modifcacion de evento seria
					Date fechaGeneracionS = servicioResultado.getProcEvtAnimal().getProcAnimal().getProcEstablecimiento()==null?fechaEntradaServicio:servicioResultado.getProcEvtAnimal().getLote().getProcProces().getFecha();
					//Date fechaGeneracionS = servicioResultado.getProcEvtAnimal().getLote().getProcProces().getFecha();
					Date fechaGeneracionR = repr.getProcEvtAnimal().getLote().getProcProces().getFechaEntrada();
					//int dias = DateUtils.diasEntre(fechaGeneracionR,fechaGeneracionS);
					int diasP = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MAX_PORTERIORIDAD_INFORMAR_SERVICIO_A_PARTO));
					if(DateUtils.diasEntre(fechaGeneracionR,fechaGeneracionS) > diasP){//puede pasar solo en un evtModificacion cuando modifican la fecha de servicio
						throw new ExcepcionIntegridad(MENSAJES.SERVICIO_POSTERIOR_NO_CUMPLE_FECHA, new String[]{StringUtils.formatDate(repr.getFechaServicio()),String.valueOf(diasP)});
					}
				}
				if (this.dentroDePeriodoDescanzo(servicioResultado)){ // si el
					// servicio especificado esta en el periodo de descanzo,es un error
					//throw new ExcepcionIntegridad(MENSAJES.SERVICIO_EN_PERIODO_DESCANZO, new String[0]);//a partir de ahora se toman los partos
					ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_ESPECIFICADO_PROBLEMAS_IGUAL_ACEPTA,ProcMsg.WARNING_SPECIAL,
							new String[] { StringUtils.formatDate(repr.getFechaServicio())+" cae en el periodo de descanso"});
					msgs.add(msg);
					aceptaPartoSinServicio = false;
				}
				else{
					diasGestacion = DateUtils.diasEntre(servicioResultado.getFecha(),repr.getFecha())
					+ servicioResultado.getCantidadDiasGestacionADescontar();
					if (criasVivas) {
						Date diaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
						Date diaMasCercano = DateUtils.menos(repr.getFecha(), gMin);
						if (!DateUtils.entre(repr.getFechaServicio(), diaMasLejano,diaMasCercano)){
							//throw new ExcepcionIntegridad(MENSAJES.SERVICIO_ESPECIFICADO_FUERA_DE_PERIODO,new String[] { DateUtils.format(diaMasLejano,null),DateUtils.format(diaMasCercano,null) });
							ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_ESPECIFICADO_FUERA_PERIODO_IGUAL_ACEPTA,ProcMsg.WARNING_SPECIAL,
									new String[] { StringUtils.formatDate(servicioResultado.getFecha()),DateUtils.format(diaMasLejano,null),DateUtils.format(diaMasCercano,null) });
							msgs.add(msg);
							aceptaPartoSinServicio = false;
						}
						else{
							EvtServicio masApropiado = getServicioMasApropiadoParaParto(repr, gMax, gMin);
							if (masApropiado!= null && masApropiado != servicioResultado) { // el que informan
								// no es el que esta
								// mas cerca del
								// promedio
								if (Configuracion.getValorReglaProceso(CONF.ACEPTA_SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,repr.getFecha())) {
									ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,ProcMsg.WARNING,
													new String[] { StringUtils.formatDate(repr.getFecha()),StringUtils.formatDate(masApropiado.getFecha())});
									msgs.add(msg);
								} else
									throw new ExcepcionIntegridad(MENSAJES.SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO,
											new String[]{StringUtils.formatDate(masApropiado.getFecha())});
							}
						}
			
					} else { // me dan la fecha de servicio, todas las crias muertas
						Date diaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
						Date diaMasCercano = DateUtils.menos(repr.getFecha(), gMin);
						if (!DateUtils.entre(repr.getFechaServicio(), diaMasLejano,	repr.getFecha())){
							//throw new ExcepcionIntegridad(MENSAJES.SERVICIO_ESPECIFICADO_FUERA_DE_PERIODO,new String[] { DateUtils.format(diaMasLejano,null),DateUtils.format(repr.getFecha(),null) });
							ProcMsg msg = ProcMsgDAO.create(MENSAJES.SERVICIO_ESPECIFICADO_FUERA_PERIODO_IGUAL_ACEPTA,ProcMsg.WARNING_SPECIAL,
									new String[] { StringUtils.formatDate(servicioResultado.getFecha()),DateUtils.format(diaMasLejano,null),DateUtils.format(diaMasCercano,null) });
							msgs.add(msg);
							aceptaPartoSinServicio = false;
						}
					}
				}
			}
		} else {
			// no me dan la fecha del servicio
			if (Configuracion.getValorReglaProceso(CONF.OBLIGA_FECHA_SERVICIO_EN_PARTO, repr.getFecha()))
				throw new ExcepcionIntegridad(MENSAJES.NO_ESPECIFICA_FECHA_SERVICIO, new String[0]);
			if(!repr.getEvtCrias().isEmpty()){
			//if (criasVivas) {
				Date diaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
				Date diaMasCercano = DateUtils.menos(repr.getFecha(), gMin);
				servicioResultado = getServicioMasApropiadoParaParto(repr,gMax, gMin);
				if (servicioResultado != null) {
					diasGestacion = DateUtils.diasEntre(servicioResultado.getFecha(), repr.getFecha())
							+ servicioResultado.getCantidadDiasGestacionADescontar();
					Iterator posibles = getServiciosEfectivosEntre(diaMasLejano, diaMasCercano).iterator();
					boolean aceptaMasdeUno = Configuracion.getValorReglaProceso(CONF.ACEPTA_SERVICIOS_AMBIGUOS_EN_PARTO,repr.getFecha());
					while (posibles.hasNext()) {
						EvtServicio e = (EvtServicio) posibles.next();
						if (e != servicioResultado)
							if (aceptaMasdeUno) {
								ProcMsg msg = ProcMsgDAO.create(MENSAJES.POSIBLE_SERVICIO_DESCARTADO,ProcMsg.WARNING, new String[] { e.getFecha().toString() });
								msgs.add(msg);
							} else
								throw new ExcepcionIntegridad(MENSAJES.POSIBLE_SERVICIO_DESCARTADO,new String[] { StringUtils.formatDate(e.getFecha()) });
					}
				} else { // no hay servicio entre gMin y gMax, se usa el
					// promedio
					diasGestacion = (gMax + gMin) / 2;
				}
			//} else { // todas las crias muertas -> tratado como un aborto
			} else { // es un aborto
				
				Date fechaMasLejano = DateUtils.menos(repr.getFecha(), gMax);
				int diasMinAbortoLargo = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MIN_ABL));
				Date fechaMinAbortoLargo = DateUtils.menos(repr.getFecha(),diasMinAbortoLargo);
				if (repr.getAbortoLargo()) { // busco en fecha de aborto largo
					// largo
					servicioResultado = getServicioMasApropiadoParaParto(repr,gMax, diasMinAbortoLargo);
					//List serviciosPosibles = this.getServiciosEfectivosEntre(fechaMasLejano, fechaMinAbortoLargo);
					if (servicioResultado!=null) {
					//if (!serviciosPosibles.isEmpty()) {
						//servicioResultado = (EvtServicio) serviciosPosibles.get(0);
						diasGestacion = DateUtils.diasEntre(servicioResultado.getFecha(), repr.getFecha())
								+ servicioResultado.getCantidadDiasGestacionADescontar();
					} else { // es un aborto largo, pero sin servicio
						checkEsAbortoLargo(repr.getFecha(),repr.getNroLactancia());
						diasGestacion = diasMinAbortoLargo + 2;//ACA ES DONDE HAY QUE MODIFICAR
					}
				} else { // busco en fecha de aborto corto
					servicioResultado = getServicioMasApropiadoParaParto(repr,diasMinAbortoLargo, 0);
					//List serviciosPosibles = this.getServiciosEfectivosEntre(fechaMinAbortoLargo, repr.getFecha());
					//if (!serviciosPosibles.isEmpty()) {
					if(servicioResultado!=null){
						//servicioResultado = (EvtServicio) serviciosPosibles.get(0);
						diasGestacion = DateUtils.diasEntre(servicioResultado.getFecha(), repr.getFecha())
								+ servicioResultado.getCantidadDiasGestacionADescontar();
					} else { // es un aborto corto, pero sin servicio
						diasGestacion = diasMinAbortoLargo - 2;
					}
				}
			}
		}
		//mando el parto como paramentro, si servicioResultado.getEvtReproduccion() != del parametro
		if((servicioResultado!= null && servicioResultado.getEvtReproduccion()!=null && !(servicioResultado.getEvtReproduccion().equals(repr)))){
			
		throw new ExcepcionIntegridad(MENSAJES.NO_ACEPTA_SERVICIO_PARA_DOS_PARTOS,new String[] { StringUtils.formatDate(servicioResultado.getFecha()),StringUtils.formatDate(servicioResultado.getEvtReproduccion().getFecha()) });
		}
		this.checkServicioACampo(servicioResultado);
		if(aceptaPartoSinServicio){
			repr.setEvtServicio(servicioResultado);
			
		}
		repr.setDiasDeGestacion(diasGestacion);
	}


	/**
	 * metodo que se chequea en el caso de que no se informe servicio y ademas no se encuentre un servicio valido para
	 * el aborto largo si es posible que en el evento reproduccion se informe que es un aborto largo.
	 * La forma de chequear es que la hembra debe llevar minimamente 200 dias (puede variar) lactando, sino es es asi se
	 * informa el error
	 * en caso de que no este lactando se debe chequear que minimamente tenga de diferencia 200 dias con el ultimo parto
	 * @throws ExcepcionIntegridad 
	 */
	public void checkEsAbortoLargo(Date fechaRep,int numLac) throws ExcepcionIntegridad{
		Lactancia lacCurso = EvtLactancia.calcularUltimaLactanciaAbierta(this);
		int diasLactandoParaAL = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MIN_LACT_ABL));
		Lactancia ultimaCerrada = this.getUltimaLactanciaCerrada();
		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())){//si no es carga inicial
			if(lacCurso == null){
				if(ultimaCerrada!=null){
					int dias  = DateUtils.diasEntre(ultimaCerrada.getFechaInicio(),fechaRep);
					if(dias < diasLactandoParaAL)
						throw new ExcepcionIntegridad(MENSAJES.NO_ABORTO_LARGO_DIAS_LACTANDO,new String[] { String.valueOf(diasLactandoParaAL),String.valueOf(dias) });
				}
				else//si es null
					if(numLac != 1)
						throw new ExcepcionIntegridad(MENSAJES.NO_ABORTO_LARGO_DIAS_LACTANDO,new String[] { String.valueOf(diasLactandoParaAL),String.valueOf(0) });
				
			}else{
				int dias  = DateUtils.diasEntre(lacCurso.getFechaInicio(),fechaRep);
				if(dias < diasLactandoParaAL)
					throw new ExcepcionIntegridad(MENSAJES.NO_ABORTO_LARGO_DIAS_LACTANDO,new String[] { String.valueOf(diasLactandoParaAL),String.valueOf(dias) });
				
					
			}
		}
			
	}
	/**
	 * Obtiene una lista de eventos que se produjeron de este animal entre las
	 * fechas dadas y el tipo de evento pasado como parametro.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getEventosEntre(Date fechaInicio, Date fechaFin,
			String tipoEvento) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			// evt.getFecha() es timestamp y no date, bug
			if(tipoEvento.equals(EvtAnimal.EVT_TIPO_SVC)&&evt instanceof EvtNuevoInd){
				EvtNuevoInd evtSvc = (EvtNuevoInd)evt;
				if((evtSvc.getFinCorral() != null && DateUtils.entre(evtSvc.getFinCorral(), fechaInicio, fechaFin))
						||DateUtils.entre(evt.getFecha(), fechaInicio, fechaFin))
						resultado.add(evt);
			}
			else	
				if (DateUtils.entre(evt.getFecha(), fechaInicio, fechaFin)
						&& evt.getNombreTipo().equals(tipoEvento))
					resultado.add(evt);
		}
		return resultado;
	}

	/**
	 * No es lo mismo que para eventos en general, porque aca hay que tener en
	 * cuenta el periodo de gestacion que ya tienen, por ejemplo las
	 * transferencias embrionarias.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getServiciosEfectivosEntre(Date fechaInicio, Date fechaFin) {
		List resultado = new LinkedList();
		SortedSet evs = this.getEvtAnimals();
		if(!evs.isEmpty()){
		Iterator it = evs.iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getNombreTipo().equals(EvtAnimal.EVT_TIPO_SVC)) {
				EvtServicio serv = null;
				//EvtNuevoInd serv = null;
				if((evt instanceof EvtNuevoInd))
					serv = (EvtNuevoInd) evt;
				//if((evt.getClass().equals(EvtTransEmb.class)))
				if(evt instanceof EvtTransEmb)
					serv = (EvtTransEmb) evt; 
				
				if(evt instanceof HibernateProxy)
					if(EvtServicio.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(evt)))
						serv = (EvtServicio)evt;
				if(serv!=null){
					int aDescontar = serv.getCantidadDiasGestacionADescontar();
					if (DateUtils.entre(evt.getFecha(), DateUtils.mas(fechaInicio,
							aDescontar), DateUtils.mas(fechaFin, aDescontar)))
						if (!dentroDePeriodoDescanzo(serv)) // los servicios que estan dentro del
							// periodo de descanzo se descartan
							resultado.add(evt);
				}
			}
		}
	}
		return resultado;
	}

	/**
	 * Agrega un hijo de parto, y setea esta hembra como la madre de parto de
	 * ese hijo. No realiza controles, los controles del EventoReproduccion se
	 * realizan ahi.
	 * 
	 * @param hijo
	 */
	@SuppressWarnings("unchecked")
	public void addHijoParto(Animal hijo) {
		this.hijosParto.add(hijo);
		hijo.setMadreParto(this);
	}

	/**
	 * Metodo que al SortedSet pasado como parametro le agrega si es que tiene la lactancia en 
	 * curso de la hembra
	 */
	@SuppressWarnings("unchecked")
	public void agregarLactancia(SortedSet result) {
		// Lactancia actual
		EvtLactancia l = EvtLactancia.calcularUltimaLactanciaAbierta(this);
		if (l != null)
			result.add(l);
	}

	/**
	 * Verdadero si el servicio se encuentra dentro del periodo de descanzo
	 * posterior a un parto.
	 * 
	 * @param servicio
	 * @return
	 */
	public boolean dentroDePeriodoDescanzo(EvtServicio servicio) {
		//TODO el parametro paso a la Raza (29/03/2007)
		int descanzoMinimo = Integer.parseInt(getRaza().getParametro(
				Raza.PERIDODO_DESCANZO_MINIMO));
		Date fechaPartoReciente = DateUtils.menos(servicio.getFecha(),
				descanzoMinimo);
		List eventos = this.getEventosEntre(fechaPartoReciente, servicio
				.getFecha(), Evento.EVT_TIPO_REP);
		return !eventos.isEmpty();
	}
	

	/**
	 * Si la Madre tiene categoria CL o RCD y tiene calificacion cumple el requisito de pura
	 * 
	 * 
	 * 
	 * Calcula si la hembra cumple con los requisitos de lactancia para ser
	 * madre de una hembra de categoria PURA
	 */
	private boolean cumpleRequisitosMadrePura(Hembra hija) {

		
		if((this.getCategoria().contains("CL")||this.getCategoria().contains("RCD"))&&(!getCalificacions().isEmpty()))
			return true;
		if(getEdadEnDiasAl(hija.getFechaNac()) < EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA)
			return true;
		
		if((!getCalificacions().isEmpty()) && (this.tieneLactConMuestra()))
			return true;
		
		return false;
	
		// ----- Antes se calculaba asi ---------------------
		// String cat = getCategoria();
		// if (!(cat.equals(Hembra.CAT_PU) || cat.equals(Hembra.CAT_RC))) // si
		// la
		// // madre no es pura o RC
		// return false;
		//
		// boolean cumple = false;
		// if (!getEventos(Evento.EVT_TIPO_PRO).isEmpty())
		// cumple = true; // si tiene alguna produccion
		// else if (!getEventos(Evento.EVT_TIPO_LMI).isEmpty())
		// cumple = true; // si tiene alguna lactancia migrada
		// else if (getEdadEnDiasAl(hija.getFechaNac()) <
		// EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA)
		// cumple = true;
		// return cumple;
	}
	/**
	 * * Si la Abuela y bisabuela tienen categoria CL o RCD y tiene calificacion cumple el requisito de pura
	 * 
	 * 
	 * Estos chequeos se hacen para la abuela y bisabuela
	 * Si tiene calificacion y tiene controles cumple el requisito de pureza
	 * si tiene menos de 42 meses para que sea pura debe tener un evento baja por muerte
	 * @param hija
	 * @return
	 */
	private boolean cumpleRequisitosMadrePura2(Hembra hija) {

		if((this.getCategoria().contains("CL")||this.getCategoria().contains("RCD"))&&(!getCalificacions().isEmpty()))
			return true;
		// MODIF La madre cumple con los requisitos si (tiene menos de 42 meses) O
		// (tiene una produccion y una calificacion)
		if((!getCalificacions().isEmpty()) && (this.tieneLactConMuestra()))
			return true;
		if(getEdadEnDiasAl(hija.getFechaNac()) < EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA){
			List baja = this.getEventos(Evento.EVT_TIPO_BAJ);
				if(!baja.isEmpty()){
					EvtBaja evt = (EvtBaja) baja.get(0);//baja por muerte
					if((evt.getDestino().equals("MUERT"))&&(getEdadEnDiasAl(evt.getFecha())< EDAD_MAXIMA_MADRE_CON_BAJA_PARA_CATEGORIA))
						return true;
				}
				return false;
			}
	return false;
	}
	/**
	 * 
	 * @return
	 */
	private boolean tieneLactConMuestra(){
		//if(!getEventos(Evento.EVT_TIPO_LMI).isEmpty())
		if(!getLactanciasMigradasCerradas().isEmpty())
			return true;
		List lactanciasCerradas = getEventos(Evento.EVT_TIPO_LAC);
		Iterator it = lactanciasCerradas.iterator();
		while(it.hasNext()){
			EvtLactancia lac = (EvtLactancia)it.next();
			if(!lac.getControl().contains("SM"))
				return true;
		}
		EvtLactancia lac = EvtLactancia.calcularUltimaLactanciaAbierta(this);
		if(lac!=null)
			if(!lac.getControl().contains("SM"))
				return true;
		return false;
	}
	/**
	 * Devuelve todas las lactancias del animal, las del tipo LMI y las LAC
	 * 
	 */

	@SuppressWarnings( { "unchecked", "unchecked" })
	public List getLactancias() {
		List ev = getEventos(Evento.EVT_TIPO_LMI);
		ev.addAll(getEventos(Evento.EVT_TIPO_LAC));
		return ev;
	}
	
	public List getLactanciasSicelTres() {
		List ev = getEventos(Evento.EVT_TIPO_LAC);
		return ev;
		
	}
	
	
	/**
	 * Metodo que recupera la lactancia en curso
	 * @return
	 */
	public Lactancia getLactanciaEnCurso(){
		SortedSet lac = new TreeSet(new EventosPorFechaYTipo());
		this.agregarLactancia(lac);
		if(!lac.isEmpty())
			return  (Lactancia) lac.first();
		return null;
		
		
	}
	public List getLactanciasMigradasCerradas() {
		List evLm = getEventos(Evento.EVT_TIPO_LMI);
		List ev = new LinkedList();
		if(!evLm.isEmpty()){
		Iterator it = evLm.iterator();
		while(it.hasNext()){
			Lactancia la = (Lactancia)it.next();
			if(la.getDias().intValue()!=999 && la.getLeche()!=null)
				ev.add(la);
		}
		}
		return ev;
	}
	/**
	 * Metodo que retorna todas las lactancias cerradas de un animal, la diferencia que hay con el
	 * metodos getLactancias es que este tra las lactancias migradas con dias en 999 que esto significa que
	 * no estan cerradas
	 * @return
	 */
	public List getLactanciasCerradas() {
		
		List ev = getLactanciasMigradasCerradas();
		ev.addAll(getEventos(Evento.EVT_TIPO_LAC));
		return ev;
	}
	/**
	 * Devuelve las lactancias ordenadas por kgs. de leche tomando como valores
	 * de comparacion aquellos dentro del periodo de los limiteDias dias
	 * 
	 * @param limiteDias
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLactanciasOrdenadasXLeche() {
		//List l = getLactancias();
		List l = getLactanciasCerradas();
		//Collections.sort(l, new ComparadorLactanciasXLeche(limiteDias));
		Collections.sort(l, new ComparadorLactanciasXLeche());
		return l;
	}
	

	/**
	 * Devuelve las lactancias ordenadas en orden cronologico.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection getLactanciasOrdenadasXFecha() {
		List l = getLactancias();
		Collections.sort(l, new EventosPorFechaYTipo());
		return l;
	}
	
	public Lactancia getUltimaLactancia() {
		LinkedList lactan = (LinkedList) this.getLactanciasOrdenadasXFecha();
		if(lactan.isEmpty())
			return null;
		return (Lactancia)lactan.getLast();
		
	}

	/**
	 * Devuelve la ultima calificacion del animal. Se utiliza en la generacion
	 * de reportes.
	 * 
	 * @see ar.org.sicel.persistence.Animal#getUltimaCalificacion()
	 */
	@SuppressWarnings("unchecked")
	public Calificacion getUltimaCalificacion() {
		List calificaciones = new LinkedList();
		calificaciones.addAll(this.getCalificacions());
		if (calificaciones.size() > 0) {
			Collections.sort(calificaciones, new ComparadorCalificaciones());
			return (Calificacion) calificaciones.get(calificaciones.size() - 1);
		}
		return null;
	}
	/**
	 * 
	 * @return ultima lactancia cerrada del animal, en caso de que no tenga lactancia retorna null
	 */
	public Lactancia getUltimaLactanciaCerrada(){
		Lactancia ultimaLactanciaCerrada = null;
		List lactancias = this.getLactanciasCerradas();
		if(!lactancias.isEmpty()){
			Collections.sort(lactancias, new EventosPorFechaYTipo());
			ultimaLactanciaCerrada = (Lactancia) ((List) lactancias).get(lactancias.size() - 1);
		}
		return ultimaLactanciaCerrada;
	}
	/**
	 * Devuelve todos los controles de la ultima lactancia cerrada. Se utiliza
	 * en la generacion del reporte "Certificado de Lactancia".
	 * 
	 * @see ar.org.sicel.persistence.Hembra#getControlesUltimaLactanciaAbierta()
	 */
	@SuppressWarnings("unchecked")
	public Collection getControlesUltimaLactanciaCerrada() {
		/*List lactancias = this.getLactancias();
		Collections.sort(lactancias, new EventosPorFechaYTipo());
		Lactancia ultimaLactanciaCerrada = (Lactancia) ((List) lactancias)
				.get(lactancias.size() - 1);*/
		Lactancia ultimaLactanciaCerrada = this.getUltimaLactanciaCerrada();
//		Date fechaInicio = ultimaLactanciaCerrada.getFechaInicio();
//		Date fechaFin = DateUtils.mas(fechaInicio, ultimaLactanciaCerrada
//				.getDias());
		if(ultimaLactanciaCerrada !=null)
			return this.getEventosEntre(ultimaLactanciaCerrada.getFechaInicio(),
				DateUtils.mas(ultimaLactanciaCerrada.getFechaInicio(),
						ultimaLactanciaCerrada.getDias()),
				EvtAnimal.EVT_TIPO_CONTROL_ANIMAL);
		return null;

	}

	/**
	 * Devuelve todos los controles de la ultima lactancia abierta. Se utiliza
	 * en la generacion del reporte "Certificado de Lactancia en Curso".
	 * 
	 * @see ar.org.sicel.persistence.Hembra#getControlesUltimaLactanciaCerrada()
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public Collection getControlesUltimaLactanciaAbierta() {
		List lactancias = this.getLactancias();
		Collections.sort(lactancias, new EventosPorFechaYTipo());
		Lactancia ultimaLactanciaCerrada=null;
		Date fechaFin=null;
		if(lactancias.size()>0){
				ultimaLactanciaCerrada = (Lactancia) ((List) lactancias).get(lactancias.size() - 1);
				if(ultimaLactanciaCerrada.getDias()!=999)
					fechaFin = DateUtils.mas(ultimaLactanciaCerrada.getFechaInicio(),ultimaLactanciaCerrada.getDias());
				else
					fechaFin = ultimaLactanciaCerrada.getFechaInicio();
		}
		else
			fechaFin=this.getFechaNac();
		return this.getEventosEntre(fechaFin, new Date(),EvtAnimal.EVT_TIPO_CONTROL_ANIMAL);
	}
	/**
	 * metodo encargado de retornar la lactancia que tiene dicho numero de informacion
	 * @param numero
	 * @return la lactancia con dicho numero, caso que 
	 * no tenga lactancias o no exista la lactancia con dicho numero retorna null
	 */
	public Lactancia getLactanciaNumero(int numero){
		List lactancias = this.getLactancias();
		Lactancia lactancia=null;
		if(lactancias.size()>0){
			Iterator it = lactancias.iterator();
			while(it.hasNext()){
				lactancia = (Lactancia)it.next();
				if(lactancia.getNroLact().intValue()==numero && lactancia.getDias()!=999 && lactancia.getLeche()!=null)
					return lactancia;
			}
		}
		return null;
		
	}
	/**
	 * Devuelve todos los controles de la lactancia del numero cargado en numeroLactanciaReporte. 
	 */
	public Collection getControlesLactanciaNumero() {
		List lactancias = this.getLactancias();
		Lactancia lactancia=null;
		Date fechaFin=null;
		Iterator it = lactancias.iterator();
		while(it.hasNext()){
				lactancia = (Lactancia)it.next();
				if(lactancia.getNroLact().intValue()==this.getNumeroLactanciaReporte()){
					if( lactancia instanceof EvtLactancia){
						EvtLactancia evt = (EvtLactancia) lactancia;
						fechaFin =evt.getFecha();
					}
					else
						fechaFin = DateUtils.mas(lactancia.getFechaInicio(),lactancia.getDias());
					break;
			}
		}
			//fechaFin = DateUtils.mas(lactancia.getFechaInicio(),lactancia.getDias());
		
		//fechaFin = DateUtils.mas(lactancia.getFechaInicio(),lactanc);
		return this.getEventosEntre(lactancia.getFechaInicio(), fechaFin,EvtAnimal.EVT_TIPO_CONTROL_ANIMAL);
	}
	/**
	 * Metodo usado para reporte de lactancia
	 * @return
	 */
	public Lactancia getLactanciaNumero() {
		List lactancias = this.getLactancias();
		Lactancia lactancia=null;
		Date fechaFin=null;
		Iterator it = lactancias.iterator();
		while(it.hasNext()){
				lactancia = (Lactancia)it.next();
				if(lactancia.getNroLact().intValue()==this.getNumeroLactanciaReporte())
					return lactancia;
			}
			
		return null;
	}
	
	/**
	 * Calcula todas las lactancias del animal a x dias
	 * 
	 * @param dias
	 */
	@SuppressWarnings( { "unchecked", "unchecked", "unchecked", "unchecked" })
	public List getLactanciasParaVitalicia(Integer dias) {
		List resultado = new ArrayList();
		Iterator iterator_lact_migradas = getEventos(Evento.EVT_TIPO_LMI)
				.iterator();
		while (iterator_lact_migradas.hasNext()) {
			EvtLactanciaMigrada evtLactanciaMigrada = (EvtLactanciaMigrada) iterator_lact_migradas
					.next();
			if(evtLactanciaMigrada.getDias().intValue()>305)
				return null;//si tienen mas de 305 dias las migradas no se puede calcular la vitalica ya que no tienen real
			if (evtLactanciaMigrada.getDias() > dias)
				resultado.add(evtLactanciaMigrada.getLactancia(dias));
			else
				resultado.add(evtLactanciaMigrada);
		}

		Iterator iterator_lactancias = getEventos(Evento.EVT_TIPO_LAC)
				.iterator();
		while (iterator_lactancias.hasNext()) {
			EvtLactancia evtLactancia = (EvtLactancia) iterator_lactancias
					.next();

			if (evtLactancia.getDias() > dias)
				resultado.add(evtLactancia.getLactancia(dias));
			else
				resultado.add(evtLactancia);
		}
		return resultado;

	}

	/**
	 * Devuelve una estructura de tipo evtLactancia con la lactancia vitalicia
	 * del animal.
	 */

	public EvtLactancia getLactanciaVitalicia() {
		EvtLactancia evtLactancia = null;
		List lactancias = this.getLactanciasParaVitalicia(9999); // todas las lactancias a
		// valores reales
		if (getLactanciaEnCurso()!= null)
			lactancias.add(getLactanciaEnCurso());

		if (lactancias.size() != 0) {
			Iterator iterator = lactancias.iterator();
			evtLactancia = new EvtLactancia();
			evtLactancia.setProduccionesLactancia(new HashMap());
			int dias = 0;
			float lecheAbsoluto = 0, grasaAbsoluto = 0, proteinaAbsoluto = 0, solidosTotalesAbsoluto = 0, celulasAbsoluto = 0;

			while (iterator.hasNext()) {
				Lactancia lactancia = (Lactancia) iterator.next();
				if (lactancia.getDias() != null)
					dias += lactancia.getDias();
				if (lactancia.getLactanciaA("REAL").getLeche() != null) {
					lecheAbsoluto += lactancia.getLactanciaA("REAL").getLeche();

					if (lactancia.getLactanciaA("REAL").getPorcentajeGrasa() != null)
						grasaAbsoluto += lactancia.getLactanciaA("REAL")
								.getPorcentajeGrasa()
								* lactancia.getLactanciaA("REAL").getLeche()
								/ 100f;

					if (lactancia.getLactanciaA("REAL")
							.getPorcentajeProteinas() != null)
						proteinaAbsoluto += lactancia.getLactanciaA("REAL")
								.getPorcentajeProteinas()
								* lactancia.getLactanciaA("REAL").getLeche()
								/ 100f;

					if (lactancia.getLactanciaA("REAL")
							.getPorcentajeSolidosTotales() != null)
						solidosTotalesAbsoluto += lactancia.getLactanciaA(
								"REAL").getPorcentajeSolidosTotales()
								* lactancia.getLactanciaA("REAL").getLeche()
								/ 100f;

					if (lactancia.getLactanciaA("REAL").getCelulas() != null)
						celulasAbsoluto += lactancia.getLactanciaA("REAL")
								.getCelulas();

				}
			}

			ProduccionLactancia produccionLactancia = new ProduccionLactancia();
			if (lecheAbsoluto != 0)
				produccionLactancia.setLeche(lecheAbsoluto);
			if (grasaAbsoluto != 0)
				produccionLactancia.setPorcentajeGrasa(grasaAbsoluto
						/ lecheAbsoluto * 100);
			if (proteinaAbsoluto != 0)
				produccionLactancia.setPorcentajeProteinas(proteinaAbsoluto
						/ lecheAbsoluto * 100);
			if (solidosTotalesAbsoluto != 0)
				produccionLactancia
						.setPorcentajeSolidosTotales(solidosTotalesAbsoluto
								/ lecheAbsoluto * 100);
			if (celulasAbsoluto != 0)
				produccionLactancia.setCelulas(celulasAbsoluto);

			evtLactancia.addProduccionLactancia("VITALICIA",
					produccionLactancia);
			evtLactancia.setDias(dias);
			evtLactancia.setNroLact(lactancias.size());
		}

		return evtLactancia;
	}

	/**
	 * Devuelve la mejor lactancia respecto de los Kgs. de leche
	 * Las lactancias migradas con las de 305 dias los valores de de leches y demas corresponden a 
	 * 305 dias no a la cantidad de dias que figuran en la lactancia
	 * En caso de que la mejor lactancias sea una migrada no se va a retornar con los dias en que esta 
	 * almacenada, se va a cambiar para las que superen los 305 dias por el valor de 305 dias
	 * 
	 * @param limiteDias
	 * @return
	 */
	public Lactancia getMejorLactanciaXLeche(/*String limiteDias*/) {
		//Collection lactanciasOrdenadasXLeche = getLactanciasOrdenadasXLeche(limiteDias);
		Collection lactanciasOrdenadasXLeche = getLactanciasOrdenadasXLeche();
		if (lactanciasOrdenadasXLeche.size() > 0) {
			Lactancia l = (Lactancia)((List) lactanciasOrdenadasXLeche)
			.get(lactanciasOrdenadasXLeche.size() - 1);
			if(l instanceof EvtLactanciaMigrada ){
				if(l.getDias().intValue() > 305){
					EvtLactanciaMigrada ev =(EvtLactanciaMigrada)l;
					ev.setDias(305);
					return ev;
				}
					
			}
				
			return l ;
		}

		return null;
	}

	public String getCategoriaCalculada() {
		String cat = Animal.CAT_CR;
		if (!getRaza().getEsCruza()){
			cat = Animal.CAT_PB;
			if(!"CRIA".equals(this.getEstablecimiento().getMetodoControl().getCodigo())){
				//cat = Animal.CAT_PBI;
				//cat = Animal.CAT_PB;
			/*else if (!getRaza().getEsCruza()) {
				// si su raza no es cruza, entonces al menos es plantel base
				cat = Animal.CAT_PB;*/
	
				Macho padre = getPadre();
				Hembra madre = getMadreGenetica();
				if (padre != null && madre != null) {
					if (padre.getRaza().getId().equals(madre.getRaza().getId())
							&& padre.getRaza().getId().equals(getRaza().getId()))
						// si ambos padres son puros y de la misma raza, entonces el
						// animal es RC
						cat = Animal.CAT_RC;
					if(!(madre.getEstablecimiento().getMetodoControl().getCodigo().contains("SM"))){
						//si es con muestra miro la genealogia, sino no, queda con pb o rc. no hago la comparacion
						//con cria pq ya se hizo mas arriba
						Hembra abuela = madre.getMadreGenetica();
						//si o si tiene que tener abuelo y bisabuelo maternos
						Macho abuelo = madre.getPadre();
						if (abuela != null && abuelo !=null) {
							Hembra bisAbuela = abuela.getMadreGenetica();
							Macho bisAbuelo = abuela.getPadre();
							if (bisAbuela != null && bisAbuelo!=null) {
								// tiene por lo menos hasta bisabuela registrada
								if (madre.cumpleRequisitosMadrePura(this)
										&& abuela.cumpleRequisitosMadrePura2(madre)
										&& bisAbuela.cumpleRequisitosMadrePura2(abuela)) {
									
									if(this.getRaza().equals(abuela.getRaza()) && (this.getRaza().equals(bisAbuela.getRaza()))){
										cat = this.getRaza().getCategoriaPura();
										/*if(this.getRaza().getId().equals("HOLA"))
											cat = Animal.CAT_PURA_HOLANDO;
										else 
											if (this.getRaza().getId().equals("JERY"))
											cat = Animal.CAT_PURA_JERSEY;
											else
												if (this.getRaza().getId().equals("SWBO"))
													cat = Animal.CAT_PURA_PARDO;
												else
													if (this.getRaza().getId().equals("SRBO"))
														cat = Animal.CAT_PURA_SUECA;
													else
														if (this.getRaza().getEspecie().getId().equals("BUFA"))
															cat = Animal.CAT_PURA_BUFALO;*/
									}
									
									//cat = Animal.CAT_PU; // si 3 generaciones
									// hacia atras son
									// correctas
								}
							}
						}
				}
				}
			}
			}
		return cat;
	}
	

	public String getCategoriaCalculadaSinGenealogia(){
		String cat = Animal.CAT_CR;
		if("CRIA".equals(this.getEstablecimiento().getMetodoControl().getCodigo()))
			//cat = Animal.CAT_PBI;
			cat = Animal.CAT_PB;
		else if (!getRaza().getEsCruza()) {
			// si su raza no es cruza, entonces al menos es plantel base
			cat = Animal.CAT_PB;			
		}
		return cat;
	}
	/**
	 * Para la fecha pasada como parametro (fecha de inicio de lactancia) retorna el 
	 * numero de lactancia que deberia ser
	 * @param fechaEvento
	 * @return
	 */
	public int getNumeroLactanciaCorrectoParaParto(Date fechaEvento){
		
		int meses = DateUtils.mesesEntre(this.getFechaNac(), fechaEvento);//me da la cantidad de meses del animal para la fecha del evento
		
		int min = 16;
		int max = 60;
		int indiceLac = 1;
		
		while(meses > min && meses < max){
			indiceLac++;
			min=min+4;
			max=max+15;
		}
		return indiceLac;
	}
	/**
	 *  Metodo por el cual se chequea que el numero de lactancia se corresponda con la edad del
	 * animal
	 * @param numeroLac
	 * @param fechaEvento
	 * @return
	 */
	public boolean cumpleTablaLactancias(int numeroLac,Date fechaEvento){
		
		int meses = DateUtils.mesesEntre(this.getFechaNac(), fechaEvento);//me da la cantidad de meses del animal para la fecha del evento
		
		int min = 16;
		int max = 60;
		int indiceLac = 1;
		
		while(indiceLac !=numeroLac){
			indiceLac++;
			min=min+4;
			max=max+15;
		}
		if(meses <=min || meses >=max)//si no cumple la tabla
			return false;
		return true;
	}
	/**
	 * Metodo por el cual se chequea que el numero de lactancia se corresponda con la edad del
	 * animal
	 * @param numeroLac
	 * @param fechaEvento
	 */
	public ProcMsg chequearNumLactanciaYEdad(int numeroLac,Date fechaEvento){
		ProcMsg msg = null;
		if(!this.cumpleTablaLactancias(numeroLac,fechaEvento)){//si no cumple la tabla
			 msg = ProcMsgDAO.create(MENSAJES.NUMERO_LACTANCIA_NO_CORRESPONDE_EDAD, ProcMsg.WARNING_SPECIAL, new String[] {
					this.getRP(),this.getRegistroOrigen(),String.valueOf(DateUtils.mesesEntre(this.getFechaNac(), fechaEvento)),String.valueOf(numeroLac)});
		}
		return msg;
	}

	@Override
	public EvtServicio getUltimoServicio() {
		EvtServicio ser=null;	
		LinkedList servicios = (LinkedList) this.getEventos(Evento.EVT_TIPO_SVC);
		Collections.sort(servicios, new EventosPorFechaYTipo());
		if(!servicios.isEmpty())
			ser= (EvtServicio)servicios.get(servicios.size()-1);
		return ser;
	}
	public Lactancia getLactanciaEnCursoParaGeneticas(){
		SortedSet lac = new TreeSet(new EventosPorFechaYTipo());
		this.agregarLactanciaParaGeneticas(lac);
		if(!lac.isEmpty())
			return  (Lactancia) lac.first();
		return null;
		
		
	}
	public void agregarLactanciaParaGeneticas(SortedSet result) {
		// Lactancia actual
		EvtLactancia l = EvtLactancia.calcularUltimaLactanciaAbiertaSinFiltroLeche(this);
		if (l != null)
			result.add(l);
	}
	public Lactancia getLactanciaNumeroSinFiltroLeche(int numero){
		List lactancias = this.getLactancias();
		Lactancia lactancia=null;
		if(lactancias.size()>0){
			Iterator it = lactancias.iterator();
			while(it.hasNext()){
				lactancia = (Lactancia)it.next();
				if(lactancia.getNroLact().intValue()==numero && lactancia.getDias()!=999)
					return lactancia;
			}
		}
		return null;
		
	}

}
